package net.b2bafrica.virtualmeeting;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hbb20.CountryCodePicker;

import net.b2bafrica.virtualmeeting.BackEnd.BusinessInterests;
import net.b2bafrica.virtualmeeting.BackEnd.DataHandler;
import net.b2bafrica.virtualmeeting.BackEnd.LegalLinks;
import net.b2bafrica.virtualmeeting.BackEnd.UploadImageBehindScene;
import net.b2bafrica.virtualmeeting.PersonalClass.CompanyDetails;
import net.b2bafrica.virtualmeeting.PersonalClass.PersonalInfo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Pattern;

import in.galaxyofandroid.spinerdialog.OnSpinerItemClick;
import in.galaxyofandroid.spinerdialog.SpinnerDialog;

public class FullFormActivity extends AppCompatActivity {
    private static final int FILE_UPLOAD = 12;
    private static final int IMAGE_UPLOAD = 22;
    private static final String messageSuccess = " Uploaded";
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private Spinner co_industry;
    private SpinnerDialog mSpinnerDialog;
    private Spinner co_TMarket;
    private Spinner co_TAudience;
    private Spinner co_NMeeting;
    private Spinner co_TypeMeeting;
    private EditText myName;
    private EditText companyName;
    private EditText myRole;
    private EditText myEmail;
    private EditText myAlternateEmail;
    private EditText companyWebsite;
    private EditText myMobileNumber;
    private EditText myTelephone;
    private EditText companyAddress;
    private EditText tartgetMarket;
    private EditText targetAudience;
    private CalendarView proposedDates;
    private String successProfile;
    private String successImages;
    private Button co_country;
    private Button profileButton;
    private Button productImage;
    private Button submitInfo;
    private List<Uri> links;
    private Uri mFileLoc;
    private String marketList;
    private String audienceList;
    private String[] mIndustryData;
    private String[] mCountryData;
    private String[] mMarketData;
    private String[] mAudienceData;
    private String[] mNumberData;
    private String[] mMeetingTypeData;
    private CountryCodePicker mCountryCodePicker;
    private CountryCodePicker mCountryCodePicker2;
    private int mNumCountry;
    private String mCurDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_form);
        //Firebase reference
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference().child(LegalLinks.NEW_USERS);
        //initiate list
        links = new ArrayList<>();
        marketList = "Market:";
        audienceList = "Audience";
        //references
        co_industry = findViewById(R.id.full_form_co_industry);
        co_TMarket = findViewById(R.id.full_form_target_market);
        co_TAudience = findViewById(R.id.full_form_target_audience);
        co_NMeeting = findViewById(R.id.full_form_number_meetings);
        co_TypeMeeting = findViewById(R.id.full_form_type_meeting);
        myName = findViewById(R.id.full_form_name);
        companyName = findViewById(R.id.full_form_co_name);
        myRole = findViewById(R.id.full_form_co_designation);
        myEmail = findViewById(R.id.full_form_co_email_1);
        myAlternateEmail = findViewById(R.id.full_form_co_email_2);
        companyWebsite = findViewById(R.id.full_form_co_website);
        myMobileNumber = findViewById(R.id.full_form_co_mobile_1);
        myTelephone = findViewById(R.id.full_form_co_mobile_2);
        companyAddress = findViewById(R.id.full_form_co_address);
        tartgetMarket = findViewById(R.id.full_form_co_target_market_edit_text);
        targetAudience = findViewById(R.id.full_form_co_target_audience_edit_text);
        proposedDates = findViewById(R.id.full_form_co_proposed_dates_calendar);
        co_country = findViewById(R.id.full_form_co_country);
        profileButton = findViewById(R.id.full_form_upload_profile_button);
        productImage = findViewById(R.id.full_form_upload_product_images_button);
        submitInfo = findViewById(R.id.full_form_submit_information);
        mCountryCodePicker = findViewById(R.id.country_code_picker_full_form_1);
        mCountryCodePicker2 = findViewById(R.id.country_code_picker_full_form_2);
        //attach country code with edit text
        mCountryCodePicker.registerCarrierNumberEditText(myMobileNumber);
        mCountryCodePicker2.registerCarrierNumberEditText(myTelephone);
        mCountryCodePicker.setAutoDetectedCountry(true);
        mCountryCodePicker2.setAutoDetectedCountry(true);
        //calender listener
        proposedDates.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month,
                                            int dayOfMonth) {
                mCurDate = String.valueOf(dayOfMonth)+ "/" + String.valueOf(month)+ "/" + String.valueOf(year);
            }
        });
        //first step populate spinner
        mIndustryData = new DataHandler().returnIndustries();
        ArrayAdapter<String[]> industrySp = new ArrayAdapter(this,android.R.layout.simple_spinner_item, mIndustryData);
        spinnerUploadData(industrySp,co_industry);
        mCountryData = new DataHandler().returnWorldCountries();
        //put countryData to string array
        List<String> manyCountries = new ArrayList<>();
        for(String country : mCountryData){
            manyCountries.add(country);
        }
        mSpinnerDialog = new SpinnerDialog(this, (ArrayList<String>) manyCountries,"Select Country");
        mSpinnerDialog.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String s, int i) {
                co_country.setText(s);
                mNumCountry = i;
            }
        });
        mMarketData = new DataHandler().returnCountries();
        ArrayAdapter<String[]> marketAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, mMarketData);
        spinnerUploadData(marketAdapter,co_TMarket);
        mAudienceData = new DataHandler().returnTargetAudience();
        ArrayAdapter<String[]> audienceAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, mAudienceData);
        spinnerUploadData(audienceAdapter,co_TAudience);
        mNumberData = new DataHandler().returnMeetingNo();
        ArrayAdapter<String[]> numberAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, mNumberData);
        spinnerUploadData(numberAdapter,co_NMeeting);
        mMeetingTypeData = new DataHandler().returnTypeMeeting();
        ArrayAdapter<String[]> meetingAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, mMeetingTypeData);
        spinnerUploadData(meetingAdapter,co_TypeMeeting);
        //spinner
        co_TMarket.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if(position > 0){
                    marketList = tartgetMarket.getText().toString() + " " + mMarketData[position];
                    tartgetMarket.setText(marketList);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        co_TAudience.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if(position > 0){
                    audienceList = targetAudience.getText().toString() + " " + mAudienceData[position];
                    targetAudience.setText(audienceList);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
        //button on click
        co_country.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSpinnerDialog.showSpinerDialog();
            }
        });
        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPDFFile();
            }
        });

        productImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFiveImages();
            }
        });
        submitInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //connection
                ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(FullFormActivity.CONNECTIVITY_SERVICE);
                if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                        connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                    //we are connected to a network
                    confirmEveryThing();
                }
                else {
                    ToastShower("Internet Connection Failed");
                }
            }
        });
    }

    private void confirmEveryThing() {
        //perform work in stages
        //start with the spinner
        int numIndustry = co_industry.getSelectedItemPosition();
        int numMarket =  co_TMarket.getSelectedItemPosition();
        int numAudience = co_TAudience.getSelectedItemPosition();
        int numberMeetings = co_NMeeting.getSelectedItemPosition();
        int numType = co_TypeMeeting.getSelectedItemPosition();
        //Edit text
        String name = myName.getText().toString();
        String coName = companyName.getText().toString();
        String myRle = myRole.getText().toString();
        String oneEmail = myEmail.getText().toString();
        String secEmail = myAlternateEmail.getText().toString();
        String web = companyWebsite.getText().toString();
        String tel_1 = mCountryCodePicker.getFullNumberWithPlus();
        String tel_2 = mCountryCodePicker2.getFullNumberWithPlus();
        String addr = companyAddress.getText().toString();
        String market = tartgetMarket.getText().toString();
        String audience = targetAudience.getText().toString();
        String dateIn = mCurDate;
        String profile = successProfile;
        String images = successImages;
        if( numIndustry != 0 &&  mNumCountry != 0 && numMarket != 0 && numAudience != 0 && numType != 0 && numberMeetings != 0){
            //head to Edit Text
            if(!name.isEmpty() && !coName.isEmpty() && !myRle.isEmpty() &&
                    !oneEmail.isEmpty() && !secEmail.isEmpty() && !web.isEmpty() &&
                    !tel_1.isEmpty() && !tel_2.isEmpty() && !addr.isEmpty() && !market.isEmpty() &&
                    !audience.isEmpty() && !dateIn.isEmpty()){
                if(isValidDomain(oneEmail) && isValidDomain(secEmail) && oneEmail.equals(secEmail) == false){
                    //head to textviews
                    if(profile.equals(messageSuccess) && images.equals(messageSuccess)){
                        //here is the defining moment
                        createUserSignIn(name,myRle,oneEmail,secEmail,tel_1,tel_2,LegalLinks.PASSCODE,
                                coName,web,addr,market,audience,dateIn,numIndustry,mNumCountry,numberMeetings,numType);

                    }else {
                        ToastShower2("Kindly upload required files");
                    }
                }else {
                    myEmail.setError("write email correctly");
                    myAlternateEmail.setError("do not repeat email and check for errors");
                    myEmail.requestFocus();
                    myAlternateEmail.requestFocus();
                }
            }else {
                ToastShower2("Fill all spaces");
            }

        }else {
            ToastShower2("one or more drop down menus not selected");
        }
    }

    private void createUserSignIn(final String name, final String myRle, final String oneEmail,
                                  final String secEmail, final String tel_1, final String tel_2,
                                  String passcode, final String coName, final String web, final String address, final String market, final String audience, final String dates,
                                  final int numIndustry, final int numCountry, final int numberMeeting, final int numType) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(oneEmail,passcode).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    submitInfo.setEnabled(false);
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    PersonalInfo personalInfo = new PersonalInfo(name,myRle,oneEmail,secEmail,tel_1,tel_2);
                    mDatabaseReference.child(user.getUid()).child(LegalLinks.EXPORTERS).push().setValue(personalInfo);
                    sendLinksToFirebase(user,coName,mIndustryData[numIndustry],mCountryData[numCountry],web,address);
                    addToFirebase(market,audience,mNumberData[numberMeeting],mMeetingTypeData[numType],dates);
                    workOnThread(user);
                    String data = "User ID: "+ user.getUid() + "\n" +"Name: " + name + "\n" + "Designation: " + myRle + "\n" +
                            "Email: " + oneEmail + "\n" + "Alternate Email: " + secEmail + "\n" +
                            "Mobile: " + tel_1 + "\n" + "Telephone: " + tel_2 + "\n" + "Company: " + coName
                            + "\n" + "Industry: " + mIndustryData[numIndustry] + "\n" + "Country: " + mCountryData[numCountry]
                            + "\n" + "Website: " + web + "\n" + "Images and profile are uploaded" + "\n" +
                            "Target Market: " + market + "\n" + "Audience: " + audience + "\n" + "Number of Meetings: "
                            + mNumberData[numberMeeting] + "\n" + "Preferred Meeting: " + mMeetingTypeData[numType] + "\n" +
                            "Proposed Dates" + dates;
                    sendEmail(data);



                }else {
                    ToastShower2("Your form was already submitted");
                }
            }
        });
    }

    private void workOnThread(FirebaseUser user) {
        UploadImageBehindScene uploadImageBehindScene = new UploadImageBehindScene(FullFormActivity.this,user,links,mFileLoc);
        uploadImageBehindScene.execute();
    }

    private void sendLinksToFirebase(FirebaseUser user,String coName, String expInd, String expCount, String coWebSite, String coAddrees){
        CompanyDetails companyDetails = new CompanyDetails(
                coName, expInd,expCount,coWebSite,coAddrees);
        mDatabaseReference.child(user.getUid()).child(LegalLinks.EXPORTERS).push().setValue(companyDetails);
        mDatabaseReference.child(user.getUid()).child(LegalLinks.PASSCODEMAIN).setValue(user.getUid().substring(5));
    }
    private void addToFirebase(String countries, String audience, String numberMeet, String typeMeet,String dates) {
        BusinessInterests businessInterests = new BusinessInterests(countries,audience,numberMeet,typeMeet, dates);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference mDatabaseReference = FirebaseDatabase.getInstance().getReference().child(LegalLinks.NEW_USERS);
        mDatabaseReference.child(user.getUid()).child(LegalLinks.EXPORTERS).push().setValue(businessInterests);
    }

    private void getFiveImages() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE,true);
        startActivityForResult(intent, IMAGE_UPLOAD);
    }

    private void getPDFFile() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("application/pdf");
        intent.putExtra(Intent.EXTRA_LOCAL_ONLY,true);
        startActivityForResult(Intent.createChooser(intent,"Insert PDF"), FILE_UPLOAD);
    }

    private void spinnerUploadData(ArrayAdapter<String[]> arrayAdapter, Spinner spinner) {
        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == FILE_UPLOAD && resultCode == RESULT_OK){
            mFileLoc = data.getData();
            successProfile = messageSuccess;
            profileButton.setText("Profile"+messageSuccess);
        }
        if(requestCode == IMAGE_UPLOAD && resultCode == RESULT_OK){
            if(data.getClipData() != null){
                int countClip = data.getClipData().getItemCount();
                if(countClip <= 5) {
                    int currentImageSelect = 0;
                    while (currentImageSelect < countClip) {
                        Uri imageUri_1 = data.getClipData().getItemAt(currentImageSelect).getUri();
                        links.add(imageUri_1);
                        currentImageSelect += 1;
                    }
                    successImages= messageSuccess;
                    productImage.setText("Images"+messageSuccess);
                }else {
                    productImage.requestFocus();
                }
            }else{
                ToastShower2("Kindly choose images");
            }
        }
    }

    private void ToastShower(String info){
        Toast.makeText(this,info,Toast.LENGTH_LONG).show();
    }
    private void ToastShower2(String info){
        Toast.makeText(this,info,Toast.LENGTH_SHORT).show();
    }

    private boolean isValidDomain(String email){
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);

        return pat.matcher(email).matches();
    }

    private void sendEmail(String message) {
            String email = "trevismurithi@gmail.com";
            String subject = "New Signup From APP";

            //Creating SendMail object
            SendMail sm = new SendMail(this, email, subject, message);

            //Executing sendmail to send email
            sm.execute();
    }

}

