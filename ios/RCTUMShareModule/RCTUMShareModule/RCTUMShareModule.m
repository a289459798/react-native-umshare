//
//  RCTUMShareModule.m
//  RCTUMShareModule
//
//  Created by zhangzy on 2017/3/12.
//  Copyright © 2017年 zzy. All rights reserved.
//

#import "RCTUMShareModule.h"
#import <UShareUI/UShareUI.h>

@implementation RCTUMShareModule

RCT_EXPORT_MODULE();


RCT_EXPORT_METHOD(share:(NSString *) title)
{
    
    dispatch_async(dispatch_get_main_queue(), ^{
        
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
