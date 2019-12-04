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
