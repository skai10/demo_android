package com.test.ad.demo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.anythink.core.api.ATAdInfo;
import com.anythink.core.api.AdError;
import com.anythink.rewardvideo.api.ATRewardVideoAd;
import com.anythink.rewardvideo.api.ATRewardVideoListener;

public class RewardVideoAdActivity extends Activity {

    private static String TAG = "RewardVideoAdActivity";
    String unitIds[] = new String[]{
            DemoApplicaion.mPlacementId_rewardvideo_all
            , DemoApplicaion.mPlacementId_rewardvideo_facebook
            , DemoApplicaion.mPlacementId_rewardvideo_admob
            , DemoApplicaion.mPlacementId_rewardvideo_inmobi
            , DemoApplicaion.mPlacementId_rewardvideo_flurry
            , DemoApplicaion.mPlacementId_rewardvideo_applovin
            , DemoApplicaion.mPlacementId_rewardvideo_mintegral
            , DemoApplicaion.mPlacementId_rewardvideo_mopub
            , DemoApplicaion.mPlacementId_rewardvideo_CHARTBOOST
            , DemoApplicaion.mPlacementId_rewardvideo_TAPJOY
            , DemoApplicaion.mPlacementId_rewardvideo_IRONSOURCE
            , DemoApplicaion.mPlacementId_rewardvideo_UNITYAD
            , DemoApplicaion.mPlacementId_rewardvideo_vungle
            , DemoApplicaion.mPlacementId_rewardvideo_adcolony
            , DemoApplicaion.mPlacementId_rewardvideo_appnext
            , DemoApplicaion.mPlacementId_rewardvideo_nend
            , DemoApplicaion.mPlacementId_rewardvideo_maio
            , DemoApplicaion.mPlacementId_rewardvideo_startapp
            , DemoApplicaion.mPlacementId_rewardvideo_superawesome
            , DemoApplicaion.mPlacementId_rewardvideo_myoffer
            , DemoApplicaion.mPlacementId_rewardvideo_ogury
    };

    String unitGroupName[] = new String[]{
            "All network",
            "facebook",
            "admob",
            "inmobi",
            "flurry",
            "applovin",
            "mintegral",
            "mopub",
            "chartboost",
            "tapjoy",
            "ironsource",
            "unity3d",
            "vungle",
            "adcolony",
            "appnext",
            "nend",
            "maio",
            "startApp",
            "superAwesome",
            "myoffer",
            "ogury"
    };

    RadioGroup mRadioGroup;


    int mCurrentSelectIndex;


    ATRewardVideoAd mRewardVideoAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        mRadioGroup = (RadioGroup) findViewById(R.id.placement_select_group);

        for (int i = 0; i < unitIds.length; i++) {
            RadioButton radioButton = new RadioButton(this);
            radioButton.setPadding(20, 20, 20, 20);                 // 设置文字距离按钮四周的距离
            radioButton.setText(unitGroupName[i]);
            radioButton.setId(i);
            mRadioGroup.addView(radioButton);
        }

        mRadioGroup.check(0);

        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                mCurrentSelectIndex = i;
                init();
            }
        });

//        mCurrentSelectIndex = 9;
        init();

        findViewById(R.id.is_ad_ready_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isReady = mRewardVideoAd.isAdReady();
                Toast.makeText(RewardVideoAdActivity.this, "video ad ready status:" + isReady, Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.loadAd_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRewardVideoAd.load();
            }
        });

        findViewById(R.id.show_ad_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRewardVideoAd.show();
            }
        });

        findViewById(R.id.clean_ad_view_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRewardVideoAd.clean();
            }
        });

    }


    private void init() {
        if (mRewardVideoAd != null) {
            mRewardVideoAd.onDestory();
            mRewardVideoAd = null;
        }
        mRewardVideoAd = new ATRewardVideoAd(this, unitIds[mCurrentSelectIndex]);
        String userid = "test_userid_001";
        mRewardVideoAd.setUserData(userid, "");
        mRewardVideoAd.setAdListener(new ATRewardVideoListener() {
            @Override
            public void onRewardedVideoAdLoaded() {
                Log.i(TAG, "onRewardedVideoAdLoaded");
                Toast.makeText(RewardVideoAdActivity.this, "onRewardedVideoAdLoaded", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRewardedVideoAdFailed(AdError errorCode) {
                Log.i(TAG, "onRewardedVideoAdFailed error:" + errorCode.printStackTrace());
                Toast.makeText(RewardVideoAdActivity.this, "onRewardedVideoAdFailed:" + errorCode.printStackTrace(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRewardedVideoAdPlayStart(ATAdInfo entity) {
                Log.i(TAG, "onRewardedVideoAdPlayStart");
                Toast.makeText(RewardVideoAdActivity.this, "onRewardedVideoAdPlayStart", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRewardedVideoAdPlayEnd(ATAdInfo entity) {
                Log.i(TAG, "onRewardedVideoAdPlayEnd");
                Toast.makeText(RewardVideoAdActivity.this, "onRewardedVideoAdPlayEnd", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRewardedVideoAdPlayFailed(AdError errorCode, ATAdInfo entity) {
                Log.i(TAG, "onRewardedVideoAdPlayFailed error:" + errorCode.printStackTrace());
                Toast.makeText(RewardVideoAdActivity.this, "onRewardedVideoAdPlayFailed:" + errorCode.printStackTrace(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRewardedVideoAdClosed(ATAdInfo adInfo) {
                Log.i(TAG, "onRewardedVideoAdClosed " );
                Toast.makeText(RewardVideoAdActivity.this, "onRewardedVideoAdClosed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRewardedVideoAdPlayClicked(ATAdInfo entity) {
                Log.i(TAG, "onRewardedVideoAdPlayClicked");
                Toast.makeText(RewardVideoAdActivity.this, "onRewardedVideoAdPlayClicked", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onReward(ATAdInfo atAdInfo) {
                Log.i(TAG, "onReward" );
                Toast.makeText(RewardVideoAdActivity.this, "onReward", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        mRewardVideoAd.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mRewardVideoAd.onPause();
    }
}

