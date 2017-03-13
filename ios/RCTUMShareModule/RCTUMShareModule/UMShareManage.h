//
//  UMShareManage.h
//  RCTUMShareModule
//
//  Created by zhangzy on 2017/3/13.
//  Copyright © 2017年 zzy. All rights reserved.
//

#import <Foundation/Foundation.h>


@interface UMShareManage : NSObject

+ (void) initShare:(NSString *)umAppKey SharePlatforms:(NSDictionary *) sharePlatforms OpenLog:(BOOL)openLog;

+(NSDictionary *)sharePlatforms;

@end
