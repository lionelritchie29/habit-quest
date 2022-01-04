package com.habit_quest.huawei;

import android.app.Application;

import com.huawei.hms.ads.HwAds;

public class AdSampleApplication extends Application {
    public void onCreate() {
        super.onCreate();
        // Initialize the Ads SDK.
        HwAds.init(this);
    }
}
