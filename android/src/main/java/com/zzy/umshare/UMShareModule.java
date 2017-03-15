package com.zzy.umshare;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.facebook.react.bridge.*;
import com.umeng.socialize.*;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import java.util.*;

/**
 * Created by zzy on 2017/3/9.
 * Date : 2017/3/9 17:10
 */
public class UMShareModule extends ReactContextBaseJavaModule implements ActivityEventListener {

    private Context mContext;
    private ArrayList<SHARE_MEDIA> mDisplayList;

    public UMShareModule(ReactApplicationContext reactContext) {
        super(reactContext);
        mContext = reactContext;
    }

    @Override
    public String getName() {
        return "UMShareModule";
    }

    @ReactMethod
    public void share(String title, String desc, String thumb, String link, final Promise promise) {

        UMImage image = new UMImage(getCurrentActivity(), thumb);
        UMWeb web = new UMWeb(link);
        web.setTitle(title);//标题
        web.setThumb(image);  //缩略图
        web.setDescription(desc);//描述
        new ShareAction(getCurrentActivity()).withMedia(web)
            .setDisplayList((SHARE_MEDIA[]) mDisplayList.toArray(new SHARE_MEDIA[mDisplayList.size()]))
            .setCallback(new UMShareListener() {
                @Override
                public void onStart(SHARE_MEDIA share_media) {

                }

                @Override
                public void onResult(SHARE_MEDIA share_media) {
                    promise.resolve("分享成功");
                }

                @Override
                public void onError(SHARE_MEDIA share_media, Throwable throwable) {
                    promise.reject(throwable);
                }

                @Override
                public void onCancel(SHARE_MEDIA share_media) {
                }
            }).open();
    }

    @ReactMethod
    public void initShare(String appkey, ReadableMap sharePlatforms, boolean debug) {
        UMShareAPI.init(mContext, appkey);

        UMShareConfig config = new UMShareConfig();
        config.isOpenShareEditActivity(true);
        UMShareAPI.get(getCurrentActivity()).setShareConfig(config);

        ReadableMapKeySetIterator readableMapKeySetIterator = sharePlatforms.keySetIterator();
        String[] keys = new String[3];
        int i = 0;
        while (readableMapKeySetIterator.hasNextKey()) {
            String key = readableMapKeySetIterator.nextKey();
            keys[i] = key;
            ReadableMap obj = sharePlatforms.getMap(key);
            if (key.endsWith("weixin")) {
                PlatformConfig.setWeixin(obj.getString("appKey"), obj.getString("appSecret"));
            } else if (key.endsWith("qq")) {
                PlatformConfig.setQQZone(obj.getString("appKey"), obj.getString("appSecret"));
            } else if (key.endsWith("sina")) {
                PlatformConfig.setSinaWeibo(obj.getString("appKey"), obj.getString("appSecret"), obj.getString("redirectURL"));
            }
            i++;
        }

        Arrays.sort(keys);
        mDisplayList = new ArrayList<SHARE_MEDIA>();
        for (i = 0; i < keys.length; i++) {
            if (keys[i].endsWith("weixin")) {
                mDisplayList.add(SHARE_MEDIA.WEIXIN);
                mDisplayList.add(SHARE_MEDIA.WEIXIN_CIRCLE);
            } else if (keys[i].endsWith("qq")) {
                mDisplayList.add(SHARE_MEDIA.QQ);
            } else if (keys[i].endsWith("sina")) {
                mDisplayList.add(SHARE_MEDIA.SINA);
            }
        }

        Config.DEBUG = debug;
        UMShareAPI.get(mContext);
    }

    @ReactMethod
    public void login(String platform, final Promise promise) {

        SHARE_MEDIA share_media = SHARE_MEDIA.QQ;
        if (platform.equals("weixin")) {
            share_media = SHARE_MEDIA.WEIXIN;
        }
        UMShareAPI.get(getCurrentActivity()).getPlatformInfo(getCurrentActivity(), share_media, new UMAuthListener() {
            @Override
            public void onStart(SHARE_MEDIA share_media) {

            }

            @Override
            public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {

                WritableMap writableMap = Arguments.createMap();

                Set<String> keySet = map.keySet();
                Iterator<String> iter = keySet.iterator();
                while (iter.hasNext()) {
                    String key = iter.next();
                    writableMap.putString(key, map.get(key));
                }

                promise.resolve(writableMap);

            }

            @Override
            public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
                promise.reject(throwable);

            }

            @Override
            public void onCancel(SHARE_MEDIA share_media, int i) {

            }
        });
    }

    @Override
    public void onActivityResult(Activity activity, int requestCode, int resultCode, Intent data) {
        UMShareAPI.get(getCurrentActivity()).onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onNewIntent(Intent intent) {

    }
}
