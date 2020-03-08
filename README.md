# 集成友盟统计、分享与第三方登录

分享功能：微信（支持小程序）、QQ、新浪微博
登录功能：微信、QQ


为开源事业做一份绵薄之力，欢迎加入群：161263093

作者：zhangzy  QQ：289459798  微信：zhangzy816

react-native 版本 > 0.6


### 准备工作

1. 到友盟平台申请账号 [http://www.umeng.com/](http://www.umeng.com/)
2. 微信开发平台申请 [http://open.weixin.qq.com/](http://open.weixin.qq.com/)
3. QQ开放平台申请 [http://open.qq.com/](http://open.qq.com/)
4. 新浪开放平台申请[http://open.weibo.com/](http://open.weibo.com/)

## Getting started

`$ npm install git+https://github.com/a289459798/react-native-umshare.git --save`

### Mostly automatic installation

`$ react-native link react-native-umshare`

### Manual installation


#### iOS

1. In XCode, in the project navigator, right click `Libraries` ➜ `Add Files to [your project's name]`
2. Go to `node_modules` ➜ `react-native-umshare` and add `RNSy.xcodeproj`
3. In XCode, in the project navigator, select your project. Add `libRNSy.a` to your project's `Build Phases` ➜ `Link Binary With Libraries`
4. Run your project (`Cmd+R`)<

#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`
  - Add `import com.ichong.zzy.umshare.RNSyPackage;` to the imports at the top of the file
  - Add `new RNSharePackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':react-native-umshare'
  	project(':react-native-umshare').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-umshare/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':react-native-umshare')
  	```



### Android


添加Activity

1. QQ

```xml
<activity
        android:name="com.tencent.tauth.AuthActivity"
        android:launchMode="singleTask"
        android:noHistory="true" >
        <intent-filter>
        <action android:name="android.intent.action.VIEW" />
        <category android:name="android.intent.category.DEFAULT" />
        <category android:name="android.intent.category.BROWSABLE" />
        <data android:scheme="tencent+您QQ appid" />
        </intent-filter>
        </activity>
        <activity
        android:name="com.tencent.connect.common.AssistActivity"
        android:theme="@android:style/Theme.Translucent.NoTitleBar"
        android:configChanges="orientation|keyboardHidden|screenSize"/>
```

2. 微信

```xml
<activity
    android:name=".wxapi.WXEntryActivity"
    android:configChanges="keyboardHidden|orientation|screenSize"
    android:exported="true"
    android:screenOrientation="portrait"
    android:theme="@android:style/Theme.Translucent.NoTitleBar" />
```
需要手动在包名下添加`wxapi.WXEntryActivity`文件，继承`WXCallbackActivity`

3. 新浪

```xml
<activity
        android:name="com.umeng.socialize.media.WBShareCallBackActivity"
        android:configChanges="keyboardHidden|orientation"
        android:theme="@android:style/Theme.Translucent.NoTitleBar"
        android:exported="false"
        android:screenOrientation="portrait" >
    </activity>
    <activity android:name="com.sina.weibo.sdk.web.WeiboSdkWebActivity"
              android:configChanges="keyboardHidden|orientation"
              android:exported="false"
              android:windowSoftInputMode="adjustResize"
    >
    </activity>
    <activity
        android:theme="@android:style/Theme.Translucent.NoTitleBar.Fullscreen"
        android:launchMode="singleTask"
        android:name="com.sina.weibo.sdk.share.WbShareTransActivity">
        <intent-filter>
            <action android:name="com.sina.weibo.sdk.action.ACTION_SDK_REQ_ACTIVITY" />
            <category android:name="android.intent.category.DEFAULT" />
        </intent-filter>
    </activity>
```


android 需要编译出来的apk文件的签名和申请微信和QQ时填写的一致，开发过程中可以在build.gradle 文件加入以下代码

```
signingConfigs {
        debug {
            storeFile file('keystore 文件路径')
            storePassword "keydtore 密码"
            keyAlias "keydtore 别名"
            keyPassword "keydtore 别名密码"
        }
    }
```

### IOS


1. 在项目的的`Build Settings` 中搜索 `header` 找到 `Framework Search Paths` 添加 `$(SRCROOT)/../node_modules/react-native-umshare/ios/RCTUMShareModule/RCTUMShareModule/UMSocial/SocialFrameworks`


2. 将 `Libraries` -> `RCTUMShareModule.xcodeproj` -> `RCTUMShareModule` -> `UMSocial` -> `SocialFrameworks` 和 `UMSocialUI` 文件夹拖到您的主工程下，不需要勾选 `Copy items if needed`

3. 添加依赖库
	- ibsqlite3.tbd
	- CoreGraphics.framework
	- SystemConfiguration.framework
	- libc++.tbd
	- libz.tbd
	- ImageIO.framework

4. 配置SSO白名单

```
<key>LSApplicationQueriesSchemes</key>
<array>
    <!-- 微信 URL Scheme 白名单-->
    <string>wechat</string>
    <string>weixin</string>

    <!-- 新浪微博 URL Scheme 白名单-->
    <string>sinaweibohd</string>
    <string>sinaweibo</string>
    <string>sinaweibosso</string>
    <string>weibosdk</string>
    <string>weibosdk2.5</string>

    <!-- QQ、Qzone URL Scheme 白名单-->
    <string>mqqapi</string>
    <string>mqq</string>
    <string>mqqOpensdkSSoLogin</string>
    <string>mqqconnect</string>
    <string>mqqopensdkdataline</string>
    <string>mqqopensdkgrouptribeshare</string>
    <string>mqqopensdkfriend</string>
    <string>mqqopensdkapi</string>
    <string>mqqopensdkapiV2</string>
    <string>mqqopensdkapiV3</string>
    <string>mqqopensdkapiV4</string>
    <string>mqzoneopensdk</string>
    <string>wtloginmqq</string>
    <string>wtloginmqq2</string>
    <string>mqqwpa</string>
    <string>mqzone</string>
    <string>mqzonev2</string>
    <string>mqzoneshare</string>
    <string>wtloginqzone</string>
    <string>mqzonewx</string>
    <string>mqzoneopensdkapiV2</string>
    <string>mqzoneopensdkapi19</string>
    <string>mqzoneopensdkapi</string>
    <string>mqqbrowser</string>
    <string>mttbrowser</string>

</array>
```

5. URL Scheme
	- 微信  
		微信appKey -> wxdc1e388c3822c80b
	- QQ
		需要添加两项URL Scheme：
        1、"tencent"+腾讯QQ互联应用appID  
        2、“QQ”+腾讯QQ互联应用appID转换成十六进制（不足8位前面补0）
        如appID：100424468 
        1、tencent100424468  
        2、QQ05fc5b14| QQ05fc5b14为100424468转十六进制而来，因不足8位向前补0，然后加"QQ"前缀
    - 新浪微博
    	“wb”+新浪appKey -> wb3921700954

6. 修改 `AppDelegate.m`

    ```oc
    #import <UMShare/UMShare.h>
    
    ...
    
    //#define __IPHONE_10_0    100000
    #if __IPHONE_OS_VERSION_MAX_ALLOWED > 100000
    - (BOOL)application:(UIApplication *)app openURL:(NSURL *)url options:(NSDictionary<UIApplicationOpenURLOptionsKey, id> *)options
    {
      //6.3的新的API调用，是为了兼容国外平台(例如:新版facebookSDK,VK等)的调用[如果用6.2的api调用会没有回调],对国内平台没有影响。
      BOOL result = [[UMSocialManager defaultManager]  handleOpenURL:url options:options];
      if (!result) {
        // 其他如支付等SDK的回调
        // result = other;
      }
      return result;
    }
    
    #endif
    
    - (BOOL)application:(UIApplication *)application openURL:(NSURL *)url sourceApplication:(NSString *)sourceApplication annotation:(id)annotation
    {
      //6.3的新的API调用，是为了兼容国外平台(例如:新版facebookSDK,VK等)的调用[如果用6.2的api调用会没有回调],对国内平台没有影响
      BOOL result = [[UMSocialManager defaultManager] handleOpenURL:url sourceApplication:sourceApplication annotation:annotation];
      if (!result) {
        // 其他如支付等SDK的回调
        // result = other;
      }
      return result;
    }
    
    - (BOOL)application:(UIApplication *)application handleOpenURL:(NSURL *)url
    {
      BOOL result = [[UMSocialManager defaultManager] handleOpenURL:url];
      if (!result) {
        // 其他如支付等SDK的回调
        // result = other;
      }
      return result;
    }
    ```

### 使用方式

先初始化参数

```js
import {Share, Analytics} from 'react-native-umshare';

// 初始化统计
Analytics.init(Config.UM.Appkey, Config.DEBUG);

// 初始化分享，第二个参数决定在分享界面的排序1_、2_、3_为前缀
Share.init(Config.UM.Appkey, {
                             '1_weixin': {
                                 appKey: 'wxa40f9f472xxxxx',
                                 appSecret: 'ec5967d279c414xxxxxxx',
                                 redirectURL: '',
                             },
                             "2_qq": {
                                 appKey: "",
                                 appSecret: "",
                                 redirectURL: "",
                             },
                         }, Config.DEBUG);

```

调用分享（带面板）

```js
import {Share} from 'react-native-umshare';
Share.share("标题", "简介", "缩略图地址", "链接地址")
.then(() => {
	// 成功
}, (error) => {
	// 失败
})
```

调用分享（不带面板）

```js
import {Share} from 'react-native-umshare';
Share.shareWX("标题", "简介", "缩略图地址", "链接地址")
.then(() => {
	// 成功
}, (error) => {
	// 失败
})
```

调用登录
```js
import {Share} from 'react-native-umshare';
Share.loginQQ()
    .then((data) => {
        console.log(data);
    }, (error) => {
        console.log(error)
    })

Share.loginWX()
    .then((data) => {
        console.log(data);
    }, (error) => {
        console.log(error)
    })
```

统计
```js
import {Analytics} from 'react-native-umshare';
Analytics.event("事件名称")
```

### 统计API

```js

/**
* 初始化
*/
init(appkey, debug);

/**
* 统计点击事件
*/
event(name);

/**
* 统计页面打开
*/
pageBegin(name);

/**
* 统计页面关闭
*/

pageEnd(name);
```


### 分享与登录API

```js

/**
 * 初始化分享参数
 * @param appkey
 * @param sharePlatforms
 * @param debug
 */
init(appkey: string, sharePlatforms: Object, debug: boolean);

/**
 * 友盟默认UI分享
 * @param title
 * @param desc
 * @param thumb
 * @param link
 */
share(title, desc, thumb, link);

/**
 * 友盟默认UI分享小程序
 * @param name   微信小程序的id，gc_xxxxx
 * @param title
 * @param desc
 * @param path	小程序页面路径 /page/xxx
 * @param thumb
 * @param link  当微信不支持小程序时跳转的h5页面地址
 * @oaram mode	小程序的模式（开发、体验、正式）仅对ios游泳
 */
share(name, title, desc, path, thumb, link, mode);

/**
 * 自定义UI, 微信分享
 * @param title
 * @param desc
 * @param thumb
 * @param link
 */
shareWX(title, desc, thumb, link);

/**
 * 自定义UI, 微信朋友圈分享
 * @param title
 * @param desc
 * @param thumb
 * @param link
 */
shareWXTimeLine(title, desc, thumb, link);

/**
 * 自定义UI, QQ分享
 * @param title
 * @param desc
 * @param thumb
 * @param link
 */
shareQQ(title, desc, thumb, link);

/**
 * 自定义UI, QQ空间分享
 * @param title
 * @param desc
 * @param thumb
 * @param link
 */
shareQzone(title, desc, thumb, link);

/**
 * 自定义UI, 新浪分享
 * @param title
 * @param desc
 * @param thumb
 * @param link
 */
shareSina(title, desc, thumb, link);

/**
* 分销到小程序
*/
shareMiniProgram(name, title, desc, path, image, link, type, mode);

/**
 * 微信登录
 * @returns {Promise}
 */
loginWX();

/**
 * QQ登录
 * @returns {Promise}
 */
loginQQ();
```
