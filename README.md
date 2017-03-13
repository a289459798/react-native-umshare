# 集成友盟分享与第三方登录

分享功能：微信、QQ、新浪微博
登录功能：微信、QQ

### 作者

QQ: 289459798
QQ群: 161263093
欢迎更多的喜欢开源的小伙伴加入

### 友盟SDK版本

Android：v6.4.0(精简版)

### 准备工作

1. 到友盟平台申请账号 [http://www.umeng.com/](http://www.umeng.com/)
2. 微信开发平台申请 [http://open.weixin.qq.com/](http://open.weixin.qq.com/)
3. QQ开放平台申请 [http://open.qq.com/](http://open.qq.com/)
4. 新浪开放平台申请[http://open.weibo.com/](http://open.weibo.com/)

### 安装

### Android

设置友盟平台的appkey
```
<meta-data android:name="UMENG_APPKEY"
           android:value="58c4e48a82b6350290000f20" > </meta-data>
```
### IOS


1. 在项目的的`Build Settings` 中搜索 `header` 找到 `Framework Search Paths` 添加 `$(SRCROOT)/../node_modules/react-native-umshare/ios/RCTUMShareModule/RCTUMShareModule/UMSocial/SocialFrameworks`

2. 在项目的的`Build Settings` 中搜索 `header` 找到 `Header Search Paths` 添加 `$(SRCROOT)/../node_modules/react-native-umshare/ios/RCTUMShareModule/RCTUMShareModule`

3. 将 `Libraries` -> `RCTUMShareModule.xcodeproj` -> `RCTUMShareModule` -> `UMSocial` -> `SocialFrameworks` 下面的所有`.framework` 文件拖到您的主工程下，不需要勾选 `Copy items if needed`

4. 添加依赖库
	- ibsqlite3.tbd
	- CoreGraphics.framework
	- SystemConfiguration.framework
	- libc++.tbd
	- libz.tbd
	- ImageIO.framework

5. 配置SSO白名单

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

6. URL Scheme
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

7. 初始化分享

```
//AppDelegate.m

#import "UMShareManage.h"

- (BOOL)application:(UIApplication *)application 	didFinishLaunchingWithOptions:(NSDictionary *)launchOptions
{
	// 1_,2_,3_ 设置分享平台排列的顺序
    ...
    [UMShareManage initShare:@"您的友盟appkey"
              SharePlatforms:@{
                               @"1_weixin": @{@"appkey": @"微信appkey", @"appSecret": @"微信appSecret", @"redirectURL": @""},
                               @"2_qq": @{@"appkey": @"QQappkey", @"appSecret": @"QQappSecret", @"redirectURL": @""},
                               @"3_sina": @{@"appkey": @"新浪appkey", @"appSecret": @"新浪appSecret", @"redirectURL": @""}
                               }
               OpenLog:YES];
   ...
}
```
