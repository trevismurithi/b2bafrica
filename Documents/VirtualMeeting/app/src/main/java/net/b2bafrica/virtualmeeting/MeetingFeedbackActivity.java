package net.b2bafrica.virtualmeeting;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import net.b2bafrica.virtualmeeting.BackEnd.GiveConfirmedMeetings;
import net.b2bafrica.virtualmeeting.BackEnd.LegalLinks;
import net.b2bafrica.virtualmeeting.PersonalClass.BuyerInformation;

public class MeetingFeedbackActivity extends AppCompatActivity {

    private EditText message;
    private Button sendMessage;
    private BuyerInformation mBuyerInformation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting_feedback);

        message = findViewById(R.id.send_meeting_feedback);
        sendMessage = findViewById(R.id.send_meeting_feedback_button);

        getMyIntent();

        sendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPreparing();
            }
        });
    }

    private void startPreparing() {
        if(!message.getText().toString().isEmpty()){
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            new GiveConfirmedMeetings().sendToFirebase2(user.getUid()+"\n"+
                    "Company"+mBuyerInformation.getCompany()+"\n"+"Country"+ mBuyerInformation.getEmail_1()
                    +"\n"+message.getText().toString(),user,this);
        }else {
            ToastShower("Message not sent. Try again");
        }
    }

    private void getMyIntent() {
        Intent intent = getIntent();
        mBuyerInformation = intent.getExtras().getParcelable(LegalLinks.TRANSFEROBJECT2);
    }


    private void ToastShower(String info){
        Toast.makeText(this,info,Toast.LENGTH_LONG).show();
    }
}
