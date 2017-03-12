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

1. 到友盟平台申请账号
2. 微信开发平台申请
3. QQ开放平台申请
4. 新浪开放平台申请

### 安装

### Android

设置友盟平台的appkey
```
<meta-data android:name="UMENG_APPKEY"
           android:value="58c4e48a82b6350290000f20" > </meta-data>
```
### IOS

1. 首先在`Info.plist`文件中添加字段 `TencentMapSDK` 值为您在腾讯地图开放平台申请的key

    ```
    <key>TencentMapSDK</key>
    <string>您的key</string>
    ```

2. 在项目的的`Build Settings` 中搜索 `header` 找到 `Framework Search Paths` 添加 `$(SRCROOT)/../node_modules/react-native-umshare/ios/RCTUMShareModule/RCTUMShareModule/UMSocial/SocialFrameworks`

3. 将 `Libraries` -> `RCTUMShareModule.xcodeproj` -> `RCTUMShareModule` -> `UMSocial` -> `SocialFrameworks` 下面的所有`.framework` 文件拖到您的主工程下，不需要勾选 `Copy items if needed`

4. 添加依赖库
	- ibsqlite3.tbd
	- CoreGraphics.framework
	- SystemConfiguration.framework
	- libc++.tbd
	- libz.tbd
	- ImageIO.framework
