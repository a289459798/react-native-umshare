package com.zzy.umshare;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;

/**
 * Created by zzy on 16/8/13.
 * Date : 16/8/13 16:18
 */
public class Helper {


    public static String getValue(Context context, String key) throws Exception {
        if (context == null) {
            return "";
        }
        if (TextUtils.isEmpty(key)) {
            return "";
        }

        ApplicationInfo appInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);

        if (appInfo != null && appInfo.metaData != null && appInfo.metaData.containsKey(key)) {

            return appInfo.metaData.getString(key).replace(":", "");
        } else {
            return "";
        }
    }
}
