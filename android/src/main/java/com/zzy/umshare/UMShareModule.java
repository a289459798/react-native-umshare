package com.zzy.umshare;

import android.content.Context;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.utils.Log;

/**
 * Created by zzy on 2017/3/9.
 * Date : 2017/3/9 17:10
 */
public class UMShareModule extends ReactContextBaseJavaModule implements UMShareListener {

    private Context mContext;

    public UMShareModule(ReactApplicationContext reactContext) {
        super(reactContext);
        mContext = reactContext;
    }

    @Override
    public String getName() {
        return "UMShareModule";
    }

    @ReactMethod
    public void share(String title) {

        new ShareAction(getCurrentActivity()).withText("hello")
            .setDisplayList(SHARE_MEDIA.SINA,SHARE_MEDIA.QQ,SHARE_MEDIA.WEIXIN)
            .setCallback(this).open();
    }

    @Override
    public void onStart(SHARE_MEDIA share_media) {

        Log.d("zzy", "onStart:" + share_media + "");
    }

    @Override
    public void onResult(SHARE_MEDIA share_media) {

        Log.d("zzy", "onResult:" + share_media + "");
    }

    @Override
    public void onError(SHARE_MEDIA share_media, Throwable throwable) {

        Log.d("zzy", "onError:" + share_media + "");
        throwable.printStackTrace();
    }

    @Override
    public void onCancel(SHARE_MEDIA share_media) {

        Log.d("zzy", "onCancel:" + share_media + "");
    }
}
