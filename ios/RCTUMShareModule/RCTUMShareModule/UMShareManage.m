//
//  UMShareManage.m
//  RCTUMShareModule
//
//  Created by zhangzy on 2017/3/13.
//  Copyright © 2017年 zzy. All rights reserved.
//

#import "UMShareManage.h"
#import <UMSocialCore/UMSocialCore.h>
#import <UShareUI/UShareUI.h>

static NSDictionary *mSharePlatforms;

@implementation UMShareManage

+ (void)initShare:(NSString *)umAppKey SharePlatforms:(NSDictionary *)sharePlatforms OpenLog:(BOOL)openLog {
    
    [[UMSocialManager defaultManager] openLog:openLog];
    
    /* 设置友盟appkey */
    [[UMSocialManager defaultManager] setUmSocialAppkey:umAppKey];
    
    NSDictionary *weixin = [sharePlatforms objectForKey:@"weixin"];
    if (weixin && [weixin isKindOfClass:[NSDictionary class]]) {
        [[UMSocialManager defaultManager] setPlaform:UMSocialPlatformType_WechatSession appKey:[weixin objectForKey:@"appKey"] appSecret:[weixin objectForKey:@"appSecret"] redirectURL:[weixin objectForKey:@"redirectURL"]];
    }
    
    NSDictionary *qq = [sharePlatforms objectForKey:@"qq"];
    if (qq && [qq isKindOfClass:[NSDictionary class]]) {
        [[UMSocialManager defaultManager] setPlaform:UMSocialPlatformType_QQ appKey:[qq objectForKey:@"appKey"] appSecret:[qq objectForKey:@"appSecret"] redirectURL:[qq objectForKey:@"redirectURL"]];
    }

    
    NSDictionary *sina = [sharePlatforms objectForKey:@"sina"];
    if (sina && [sina isKindOfClass:[NSDictionary class]]) {
        [[UMSocialManager defaultManager] setPlaform:UMSocialPlatformType_Sina appKey:[sina objectForKey:@"appKey"] appSecret:[sina objectForKey:@"appSecret"] redirectURL:[sina objectForKey:@"redirectURL"]];
    }
    
    mSharePlatforms = sharePlatforms;
}

+ (NSDictionary *)sharePlatforms {
    return mSharePlatforms;
}



@end
