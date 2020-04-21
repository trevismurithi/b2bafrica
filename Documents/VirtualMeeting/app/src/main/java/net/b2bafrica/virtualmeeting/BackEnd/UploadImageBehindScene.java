package net.b2bafrica.virtualmeeting.BackEnd;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.List;

public class UploadImageBehindScene extends AsyncTask {
    private ProgressDialog mProgressDialog;
    private Context mContext;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private FirebaseUser mUser;
    private int upload_count;
    private List<Uri> mList;
    private Uri mUri;

    public UploadImageBehindScene(Context context, FirebaseUser user, List<Uri> links, Uri pdf) {
        this.mContext = context;
        this.mUser = user;
        this.mList = links;
        this.mUri = pdf;
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference().child(LegalLinks.NEW_USERS);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        //mProgressDialog.show(mContext, "Sending Information", "please wait......", false, false);
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        FirebaseStorage mFirebaseStorage = FirebaseStorage.getInstance();
        StorageReference mStorageReference = mFirebaseStorage.getReference().child(LegalLinks.EXPIMAGES);
        for (upload_count = 0; upload_count < mList.size(); upload_count++) {
            Uri individualImage = mList.get(upload_count);
            final StorageReference ImageName = mStorageReference.child(mUser.getUid() + individualImage.getLastPathSegment());
            ImageName.putFile(individualImage).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    ImageName.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            String imageLink = uri.toString();
                            addNewLinks(imageLink);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            ToastShower2("Internet connection is low");
                        }
                    });
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    ToastShower2("Internet connection is low");
                }
            });
        }


        FirebaseStorage mFirebaseStorage2 = FirebaseStorage.getInstance();
        StorageReference mStorageReference2 = mFirebaseStorage2.getReference().child(LegalLinks.EXPPDF);
        final StorageReference pdfName = mStorageReference2.child(mUser.getUid() + mUri.getLastPathSegment());
        pdfName.putFile(mUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                pdfName.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        String imageLink = uri.toString();
                        addNewLinks2(imageLink);
                    }
                });
            }
        });
        return null;
    }

    private void addNewLinks2(String imageLink) {
        mDatabaseReference.child(mUser.getUid()).child(LegalLinks.EXPPDF).push().setValue(imageLink);
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        //mProgressDialog.dismiss();
    }

    private void addNewLinks(String imageLink) {
        HashMap<String, String> mHashMap = new HashMap<>();
        mHashMap.put("ImageLink", imageLink);
        mDatabaseReference.child(mUser.getUid()).child(LegalLinks.DATABASEIMAAGELINKS).push().setValue(mHashMap);
    }

    private void ToastShower2(String info){
        Toast.makeText(mContext,info,Toast.LENGTH_SHORT).show();
    }
}
