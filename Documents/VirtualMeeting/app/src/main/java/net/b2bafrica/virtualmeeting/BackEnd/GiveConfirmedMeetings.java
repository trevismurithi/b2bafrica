package net.b2bafrica.virtualmeeting.BackEnd;

import android.content.Context;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import net.b2bafrica.virtualmeeting.PersonalClass.BuyerInformation;
import net.b2bafrica.virtualmeeting.SendMail;

public class GiveConfirmedMeetings {


    public void populateData(){
        sendToFirebase(new BuyerInformation(
                "RJ Tz Importers","Importers",
                "https://firebasestorage.googleapis.com/v0/b/virtualmeeting-f9cdc.appspot.com/o/potentialmages%2Fspiderman.jpg?alt=media&token=35264123-7488-4f79-a69c-ee9f3fbcd702",
                "Tanzania","Comic boook Importers","www.comictz.com","Dares Salaam",
                "https://firebasestorage.googleapis.com/v0/b/virtualmeeting-f9cdc.appspot.com/o/potentialmages%2Fbatman.jpg?alt=media&token=935556f0-fc4f-472f-b8e8-df11f196b4e6",
                "https://firebasestorage.googleapis.com/v0/b/virtualmeeting-f9cdc.appspot.com/o/potentialmages%2Fdog.jpg?alt=media&token=f2031c88-5ede-40b2-b39c-895eebf1bda4",
                "https://firebasestorage.googleapis.com/v0/b/virtualmeeting-f9cdc.appspot.com/o/potentialmages%2Fdogmain.jpg?alt=media&token=0b314359-2147-4750-915b-a1e737737713",
                "https://firebasestorage.googleapis.com/v0/b/virtualmeeting-f9cdc.appspot.com/o/potentialmages%2Feye.png?alt=media&token=96788201-36e0-4c86-9c6f-de34e189b1bc",
                "https://firebasestorage.googleapis.com/v0/b/virtualmeeting-f9cdc.appspot.com/o/potentialmages%2Fafrica3.jpg?alt=media&token=4e8ae3de-b157-4342-ad14-04c75f0ec772",
                "Swale Mohammed", "Director","swale@comictz.com","swaleM1970@yahoo.com",
                "254 773 543 564","254 768 543 222",
                "What I love about DC is that we're not a one-note business. We can be on-line, we can be CD-ROM, we can be video games and interactive toys, or we can be movies, television, animation. So the opportunities technology brings are all open to us, and each one of those different areas helps all the others&mdash long as we never lose sight of the fact that the comics made all these other things possible.\n" +
                        "\n" +
                        "Read more: https://www.referenceforbusiness.com/history2/70/DC-Comics-Inc.html#ixzz6Jf6XWrh9", "none confirmed",
                "none confirmed"));
    }
    private void sendToFirebase(BuyerInformation buyerInformation){
        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference().child(LegalLinks.NEW_USERS);
        databaseReference.child("3nwt8kwt5HTJssGYqjCmAfDkhRQ2")
                .child(LegalLinks.CONFIRMEDCLIENTS).push().setValue(buyerInformation);
    }

    public void sendToFirebase2(String feedback, FirebaseUser user, Context context){
        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference().child(LegalLinks.NEW_USERS);
        databaseReference.child(user.getUid())
                .child(LegalLinks.CONFIRMEDCLIENTSFEEDBAK).push().setValue(feedback);

        sendEmail(feedback,context);
    }

    private void sendEmail(String message,Context mContext) {
        //Getting content for email
        String email = "trevismurithi@gmail.com";
        String subject = "New Meeting Feedback From APP";

        //Creating SendMail object
        SendMail3 sm = new SendMail3(mContext, email, subject, message);

        //Executing sendmail to send email
        sm.execute();
    }
}
