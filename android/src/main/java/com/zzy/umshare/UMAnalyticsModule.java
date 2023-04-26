package com.zzy.umshare;

import android.content.Context;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;

/**
 * Created by zzy on 16/8/11.
 * Date : 16/8/11 13:22
 */
public class UMAnalyticsModule extends ReactContextBaseJavaModule {

    private Context mContext;

    public UMAnalyticsModule(ReactApplicationContext reactContext) {
        super(reactContext);
        mContext = reactContext;
    }

    @Override
    public String getName() {
        return "UMAnalyticsModule";
    }


    @ReactMethod
    public void init(final String appkey, boolean openLog) {

        String channel = "umeng";
        try {
            channel = Helper.getValue(mContext, "UMENG_CHANNEL");
        } catch (Exception e) {
            e.printStackTrace();
        }
        UMConfigure.preInit(mContext, appkey, channel);
        UMConfigure.init(mContext, appkey
            , channel, UMConfigure.DEVICE_TYPE_PHONE, "");
        UMConfigure.setLogEnabled(openLog);
    }

    @ReactMethod
    public void pageBegin(final String name) {

        MobclickAgent.onPageStart(name);
        MobclickAgent.onResume(mContext);
    }

    @ReactMethod
    public void pageEnd(final String name) {

        MobclickAgent.onPageEnd(name);
        MobclickAgent.onPause(mContext);
    }

    @ReactMethod
    public void event(final String name) {
        MobclickAgent.onEvent(mContext, name);
    }
}
