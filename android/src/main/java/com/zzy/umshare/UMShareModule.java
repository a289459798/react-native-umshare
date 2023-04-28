package com.zzy.umshare;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.facebook.react.bridge.*;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.*;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMMin;
import com.umeng.socialize.media.UMWeb;
import com.umeng.socialize.shareboard.SnsPlatform;
import com.umeng.socialize.utils.ShareBoardlistener;
import android.util.Base64;

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
    public void share(final String title, final String desc, final String thumb, final String link, final Promise promise) {

        new ShareAction(getCurrentActivity()).setDisplayList(
            (SHARE_MEDIA[]) mDisplayList.toArray(new SHARE_MEDIA[mDisplayList.size()]))
            .setShareboardclickCallback(new ShareBoardlistener() {
                @Override
                public void onclick(SnsPlatform snsPlatform, SHARE_MEDIA share_media) {

                    UMImage image = new UMImage(mContext, thumb);
                    UMWeb web = new UMWeb(link);
                    web.setTitle(title);//标题
                    web.setThumb(image);  //缩略图
                    web.setDescription(desc);//描述
                    new ShareAction(getCurrentActivity()).withMedia(web)
                        .setPlatform(share_media)
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
                        })
                        .share();
                }
            }).open();
    }

    @ReactMethod
    public void shareMiniProgramWithPanel(final String name, final String title, final String desc, final String path, final String thumb, final String link, final int type, final int mode, final Promise promise) {


        ArrayList<SHARE_MEDIA> displayList = new ArrayList<>();

        displayList.add(SHARE_MEDIA.WEIXIN);
        displayList.add(SHARE_MEDIA.WEIXIN_CIRCLE);


        new ShareAction(getCurrentActivity()).setDisplayList(
            (SHARE_MEDIA[]) displayList.toArray(new SHARE_MEDIA[displayList.size()]))
            .setShareboardclickCallback(new ShareBoardlistener() {
                @Override
                public void onclick(SnsPlatform snsPlatform, SHARE_MEDIA share_media) {

                    UMImage image = new UMImage(mContext, thumb);


                    if (share_media == SHARE_MEDIA.WEIXIN) {

                        UMMin mediaObject = new UMMin(link);
                        mediaObject.setThumb(image);
                        mediaObject.setTitle(title);
                        mediaObject.setDescription(desc);
                        mediaObject.setPath(path);
                        mediaObject.setUserName(name);
                        new ShareAction(getCurrentActivity()).withMedia(mediaObject)
                            .setPlatform(share_media)
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
                            })
                            .share();
                    } else {

                        UMWeb web = new UMWeb(link);
                        web.setTitle(title);//标题
                        web.setThumb(image);  //缩略图
                        web.setDescription(desc);//描述
                        new ShareAction(getCurrentActivity()).withMedia(web)
                            .setPlatform(share_media)
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
                            })
                            .share();
                    }


                }
            }).open();
    }

    @ReactMethod
    public void shareMiniProgram(final String name, final String title, final String desc, final String path, final String thumb, final String link, final int mode, final Promise promise) {

        UMImage image = new UMImage(mContext, thumb);

        UMMin mediaObject = new UMMin(link);
        mediaObject.setThumb(image);
        mediaObject.setTitle(title);
        mediaObject.setDescription(desc);
        mediaObject.setPath(path);
        mediaObject.setUserName(name);
        new ShareAction(getCurrentActivity()).withMedia(mediaObject)
            .setPlatform(SHARE_MEDIA.WEIXIN)
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
            })
            .share();

    }

    @ReactMethod
    public void shareImage(final String thumb, final Promise promise) {

        new ShareAction(getCurrentActivity()).setDisplayList(
            (SHARE_MEDIA[]) mDisplayList.toArray(new SHARE_MEDIA[mDisplayList.size()]))
            .setShareboardclickCallback(new ShareBoardlistener() {
                @Override
                public void onclick(SnsPlatform snsPlatform, SHARE_MEDIA share_media) {

                    UMImage image;
                    if (thumb.startsWith("http")) {
                        image = new UMImage(mContext, thumb);
                    } else {
                        image = new UMImage(mContext, Base64.decode(thumb, Base64.DEFAULT));
                    }

                    new ShareAction(getCurrentActivity()).withMedia(image)
                        .setPlatform(share_media)
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
                        })
                        .share();
                }
            }).open();
    }

    @ReactMethod
    public void shareWithPlatformType(int platform, String title, String desc, String thumb, String link, final Promise promise) {

        SHARE_MEDIA plat = SHARE_MEDIA.WEIXIN;
        switch (platform) {
            case 1:
                plat = SHARE_MEDIA.WEIXIN;
                break;
            case 2:
                plat = SHARE_MEDIA.WEIXIN_CIRCLE;
                break;
            case 4:
                plat = SHARE_MEDIA.QQ;
                break;
            case 5:
                plat = SHARE_MEDIA.QZONE;
                break;
            case 13:
                plat = SHARE_MEDIA.SMS;
                break;
            case 14:
                plat = SHARE_MEDIA.EMAIL;
                break;
            case 26:
                plat = SHARE_MEDIA.WHATSAPP;
                break;
            case 0:
                plat = SHARE_MEDIA.SINA;
                break;
        }

        if (link != null) {
            this.shareWithLink(plat, title, desc, thumb, link, promise);
        } else if (thumb != null) {
            this.shareWithImage(plat, thumb, promise);
        } else {
            this.shareWithText(plat, desc, promise);
        }
    }

    public void shareWithText(SHARE_MEDIA platform, String desc, final Promise promise) {

        new ShareAction(getCurrentActivity()).setPlatform(platform)
            .withText(desc)
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
            }).share();
    }

    public void shareWithLink(SHARE_MEDIA platform, String title, String desc, String thumb, String link, final Promise promise) {

        UMImage image = new UMImage(mContext, thumb);
        UMWeb web = new UMWeb(link);
        web.setTitle(title);//标题
        web.setThumb(image);  //缩略图
        web.setDescription(desc);//描述
        new ShareAction(getCurrentActivity()).setPlatform(platform)
            .withMedia(web)
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
            }).share();
    }

    public void shareWithImage(SHARE_MEDIA platform, String thumb, final Promise promise) {

        UMImage image;
        if (thumb.startsWith("http")) {
            image = new UMImage(mContext, thumb);
        } else {
            image = new UMImage(mContext, Base64.decode(thumb, Base64.DEFAULT));
        }
        new ShareAction(getCurrentActivity()).setPlatform(platform)
            .withMedia(image)
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
            }).share();
    }

    @ReactMethod
    public void init(String appkey, ReadableMap sharePlatforms, boolean debug) {

        String channel = "umeng";
        try {
            channel = Helper.getValue(mContext, "UMENG_CHANNEL");
        } catch (Exception e) {
            e.printStackTrace();
        }
        UMConfigure.init(mContext, appkey
            , channel, UMConfigure.DEVICE_TYPE_PHONE, "");
        UMShareConfig config = new UMShareConfig();
        config.isOpenShareEditActivity(true);
        UMShareAPI.get(mContext).setShareConfig(config);

        ReadableMapKeySetIterator readableMapKeySetIterator = sharePlatforms.keySetIterator();
        String[] keys = new String[3];
        int i = 0;
        while (readableMapKeySetIterator.hasNextKey()) {
            String key = readableMapKeySetIterator.nextKey();
            keys[i] = key;
            ReadableMap obj = sharePlatforms.getMap(key);
            if (key.endsWith("weixin")) {
                PlatformConfig.setWXFileProvider("com.zzy.umshare.fileprovider");
                PlatformConfig.setWeixin(obj.getString("appKey"), obj.getString("appSecret"));
            } else if (key.endsWith("qq")) {
                PlatformConfig.setQQZone(obj.getString("appKey"), obj.getString("appSecret"));
            } else if (key.endsWith("sina")) {
                PlatformConfig.setSinaWeibo(obj.getString("appKey"), obj.getString("appSecret"), obj.getString("redirectURL"));
            }
            i++;
        }

        if (i == 3) {
            Arrays.sort(keys);
        }
        mDisplayList = new ArrayList<SHARE_MEDIA>();
        for (i = 0; i < keys.length; i++) {
            if (keys[i] == null) {
                continue;
            }
            if (keys[i].endsWith("weixin")) {
                mDisplayList.add(SHARE_MEDIA.WEIXIN);
                mDisplayList.add(SHARE_MEDIA.WEIXIN_CIRCLE);
            } else if (keys[i].endsWith("qq")) {
                mDisplayList.add(SHARE_MEDIA.QQ);
            } else if (keys[i].endsWith("sina")) {
                mDisplayList.add(SHARE_MEDIA.SINA);
            }
        }

        UMConfigure.setLogEnabled(debug);
        UMShareAPI.get(mContext);
    }

    @ReactMethod
    public void login(String platform, final Promise promise) {

        SHARE_MEDIA share_media = SHARE_MEDIA.QQ;
        if (platform.equals("weixin")) {
            share_media = SHARE_MEDIA.WEIXIN;
        }
        UMShareAPI.get(mContext).getPlatformInfo(getCurrentActivity(), share_media, new UMAuthListener() {
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

    @ReactMethod
    public void addListener(String eventName) {

    }

    @ReactMethod
    public void removeListeners(Integer count) {

    }

    @Override
    public void onActivityResult(Activity activity, int requestCode, int resultCode, Intent data) {
        UMShareAPI.get(mContext).onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onNewIntent(Intent intent) {

    }
}
