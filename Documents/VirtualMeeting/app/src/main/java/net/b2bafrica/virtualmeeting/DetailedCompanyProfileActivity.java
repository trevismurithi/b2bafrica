package net.b2bafrica.virtualmeeting;

import android.content.Intent;
import android.content.res.Resources;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import net.b2bafrica.virtualmeeting.BackEnd.LegalLinks;
import net.b2bafrica.virtualmeeting.PersonalClass.BuyerInformation;

import de.hdodenhof.circleimageview.CircleImageView;

public class DetailedCompanyProfileActivity extends AppCompatActivity {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private AppBarLayout mAppBarLayout;
    //own tab
    private CircleImageView mCircleImageView;
    private TextView mainCompanyDis;
    private TextView mainIndustryDis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_company_profile);

        mTabLayout = findViewById(R.id.android_tab_layout_1);
        mViewPager = findViewById(R.id.android_viewpager_1);
        mAppBarLayout = findViewById(R.id.appbar_brid_1);


        FragmentProfile fragmentProfile = new FragmentProfile();
        FragmentRequestView fragmentRequestView = new FragmentRequestView();

        ViewPagerAdapter mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mViewPagerAdapter.AddFragment(fragmentProfile, "Profile");
        mViewPagerAdapter.AddFragment(fragmentRequestView, "Person");

        mViewPager.setAdapter(mViewPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        //own reference
        mCircleImageView = findViewById(R.id.display_confirmed_co_image_1);
        mainCompanyDis = findViewById(R.id.display_confirmed_co_name_1);
        mainIndustryDis = findViewById(R.id.display_confirmed_co_industry_1);
        getContents();


    }

    private void getContents() {
        Intent intent = getIntent();
        BuyerInformation buyerInformation = intent.getExtras().getParcelable(LegalLinks.TRANSFEROBJECT2);
        mainCompanyDis.setText(buyerInformation.getCompany());
        mainIndustryDis.setText(buyerInformation.getIndustry());
        showImage(buyerInformation.getCoImage());
    }

    public void showImage(String url){
        if(url != null && !url.isEmpty()){
            int width = Resources.getSystem().getDisplayMetrics().widthPixels;
            Picasso.get()
                    .load(url)
                    .resize(width/4, width/4)
                    .centerInside()
                    .into(mCircleImageView);
        }

    }

}

