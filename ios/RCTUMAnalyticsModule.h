//
//  UMengAnalytics.h
//  CloudMarket
//
//  Created by zhangzy on 2017/8/7.
//  Copyright © 2017年 Facebook. All rights reserved.
//

#import <Foundation/Foundation.h>
#if __has_include(<React/RCTBridgeModule.h>)
#import <React/RCTBridgeModule.h>
#else // Compatibility for RN version < 0.40
#import "RCTBridgeModule.h"
#endif

#import <UMAnalytics/MobClick.h>

@interface RCTUMAnalyticsModule : NSObject <RCTBridgeModule>

@end
