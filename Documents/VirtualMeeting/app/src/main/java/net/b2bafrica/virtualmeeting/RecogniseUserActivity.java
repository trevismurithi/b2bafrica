package net.b2bafrica.virtualmeeting;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RecogniseUserActivity extends AppCompatActivity {
    private EditText email;
    private Button submit;
    private ProgressDialog mProgressDialog;
    private static final String PASSCODE = "passcode";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recognise_user);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.9),(int)(height*.4));

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y = -20;

        getWindow().setAttributes(params);
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        //initialize dialog
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Please Wait....");
        //reference
        email = findViewById(R.id.email_recognition);
        submit = findViewById(R.id.email_recognition_button);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailAdd = email.getText().toString();
                ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(FullFormActivity.CONNECTIVITY_SERVICE);
                if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                        connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                    //we are connected to a network
                    if(!emailAdd.isEmpty())
                        signUserIn(emailAdd);
                    else
                        email.requestFocus();
                }else {
                    ToastShower("There is no internet connection");
                }
            }
        });

    }

    private void signUserIn(String emailAdd) {
        mProgressDialog.show();
        FirebaseAuth.getInstance().signInWithEmailAndPassword(emailAdd,PASSCODE).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    ToastShower("Log In Successful");
                    mProgressDialog.dismiss();
                    finish();
                    startActivity(new Intent(RecogniseUserActivity.this,SignUpActivity.class));
                }else {
                    mProgressDialog.dismiss();
                    ToastShower("Log In Unsuccessful");
                }
            }
        });
    }
    private void ToastShower(String info){
        Toast.makeText(this,info,Toast.LENGTH_LONG).show();
    }
}

