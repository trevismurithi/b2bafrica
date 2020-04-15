package net.b2bafrica.virtualmeeting.CompanyFragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.b2bafrica.virtualmeeting.BackEnd.LegalLinks;
import net.b2bafrica.virtualmeeting.PersonalClass.BuyerInformation;
import net.b2bafrica.virtualmeeting.R;

public class FragmentCompanyBio extends Fragment {
    private View mView;
    private TextView mTextView;
    private BuyerInformation mBuyerInformation;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_company_bio,container,false);
        mTextView = mView.findViewById(R.id.company_bio_pot);
        getMyIntent();
        return mView;
    }

    private void getMyIntent() {
        Intent intent = getActivity().getIntent();
        mBuyerInformation = intent.getExtras().getParcelable(LegalLinks.TRANSFEROBJECT);
        mTextView.setText(mBuyerInformation.getCompanyBio());
    }
}
