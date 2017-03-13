//
//  RCTUMShareModule.m
//  RCTUMShareModule
//
//  Created by zhangzy on 2017/3/12.
//  Copyright © 2017年 zzy. All rights reserved.
//

#import "RCTUMShareModule.h"
#import <UShareUI/UShareUI.h>
#import "UMShareManage.h"

@implementation RCTUMShareModule

RCT_EXPORT_MODULE();


RCT_EXPORT_METHOD(share:(NSString *) title)
{
    
    dispatch_async(dispatch_get_main_queue(), ^{
    
        if([UMShareManage sharePlatforms] == nil) {
            
            [self sendEventWithName:@"share_error" body:@"请先在AppDelegate.m中初始化分享设置"];
            return;
        }
        // 设置顺序
        NSMutableArray *sort = [[NSMutableArray alloc] init];
    
        [[UMShareManage sharePlatforms] enumerateKeysAndObjectsUsingBlock:^(id key, id obj, BOOL *stop) {
            if([key rangeOfString:@"weixin"].location != NSNotFound) {
                [sort addObject:@(UMSocialPlatformType_WechatSession)];
                [sort addObject:@(UMSocialPlatformType_WechatTimeLine)];
            } else if([key rangeOfString:@"qq"].location != NSNotFound) {
                [sort addObject:@(UMSocialPlatformType_QQ)];
            } else if([key rangeOfString:@"sina"].location != NSNotFound) {
                [sort addObject:@(UMSocialPlatformType_Sina)];
            }
        }];
                
        [UMSocialUIManager setPreDefinePlatforms:sort];
        
        [UMSocialUIManager showShareMenuViewInWindowWithPlatformSelectionBlock:^(UMSocialPlatformType platformType, NSDictionary *userInfo) {
            // 根据获取的platformType确定所选平台进行下一步操作
        }];
        
    });
    
}

- (NSArray<NSString *> *)supportedEvents {
    NSMutableArray *arr = [[NSMutableArray alloc] init];
    
    [arr addObject:@"share_start"];
    [arr addObject:@"share_cancel"];
    [arr addObject:@"share_success"];
    [arr addObject:@"share_error"];
    return arr;
}

@end
