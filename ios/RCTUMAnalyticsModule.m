//
//  UMengAnalytics.m
//  CloudMarket
//
//  Created by zhangzy on 2017/8/7.
//  Copyright © 2017年 Facebook. All rights reserved.
//

#import "RCTUMAnalyticsModule.h"

@implementation RCTUMAnalyticsModule

RCT_EXPORT_MODULE();

RCT_EXPORT_METHOD(init:(NSString *)umAppKey OpenLog:(BOOL)openLog)
{
    /* 设置友盟appkey */
    [UMConfigure initWithAppkey:umAppKey channel:@"App Store"];
    [UMConfigure setLogEnabled:openLog];
}

RCT_EXPORT_METHOD(pageBegin: (NSString *) name) {

  [MobClick beginLogPageView:name];
}

RCT_EXPORT_METHOD(pageEnd: (NSString *) name) {

  [MobClick endLogPageView:name];
}

RCT_EXPORT_METHOD(event: (NSString *) name) {
  [MobClick event:name];
}

@end
