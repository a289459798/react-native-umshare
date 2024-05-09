/**
 * Created by zhangzy on 16/7/27.
 */

"use strict";

import { NativeModules, Platform } from "react-native";

const UMShareModule = NativeModules.UMShareModule;

class UMShare {
  /**
   * 初始化分享参数
   * @param appkey
   * @param sharePlatforms
   * @param debug
   */
  init(appkey: string, sharePlatforms: Object, debug: boolean) {
    UMShareModule.init(appkey, sharePlatforms, debug);
  }

  /**
   *
   * @param title
   * @param desc
   * @param thumb
   * @param link
   */
  share(title: string, desc: string, thumb: string, link?: string) {
    return new Promise(function (callback, errorCallback) {
      UMShareModule.share(title, desc, thumb, link).then(
        (data: any) => {
          callback(data);
        },
        (error: any) => {
          if (typeof error == "string") {
            errorCallback(error);
          } else {
            errorCallback(error.message);
          }
        }
      );
    });
  }

  /**
   *
   * @param name
   * @param title
   * @param desc
   * @param path
   * @param image
   * @param link
   * @param type
   * @param mode
   */
  shareMiniProgramWithPanel(
    name: string,
    title: string,
    desc: string,
    path: string,
    image: string,
    link: string,
    type: string,
    mode: string
  ) {
    return new Promise(function (callback, errorCallback) {
      UMShareModule.shareMiniProgramWithPanel(
        name,
        title,
        desc,
        path,
        image,
        link,
        type,
        mode
      ).then(
        (data: any) => {
          callback(data);
        },
        (error: any) => {
          if (typeof error == "string") {
            errorCallback(error);
          } else {
            errorCallback(error.message);
          }
        }
      );
    });
  }

  /**
   *
   * @param name
   * @param title
   * @param desc
   * @param path
   * @param image
   * @param link
   * @param mode
   */
  shareMiniProgram(
    name: string,
    title: string,
    desc: string,
    path: string,
    image: string,
    link: string,
    mode: string
  ) {
    return new Promise(function (callback, errorCallback) {
      UMShareModule.shareMiniProgram(
        name,
        title,
        desc,
        path,
        image,
        link,
        mode
      ).then(
        (data: any) => {
          callback(data);
        },
        (error: any) => {
          if (typeof error == "string") {
            errorCallback(error);
          } else {
            errorCallback(error.message);
          }
        }
      );
    });
  }

  /**
   *
   * @param thumb
   */
  shareImage(thumb: string) {
    return new Promise(function (callback, errorCallback) {
      UMShareModule.shareImage(thumb).then(
        (data: any) => {
          callback(data);
        },
        (error: any) => {
          if (typeof error == "string") {
            errorCallback(error);
          } else {
            errorCallback(error.message);
          }
        }
      );
    });
  }

  /**
   * 微信分享
   * @param title
   * @param desc
   * @param thumb
   * @param link
   */
  shareWX(title: string, desc: string, thumb: string, link?: string) {
    return new Promise(function (callback, errorCallback) {
      UMShareModule.shareWithPlatformType(1, title, desc, thumb, link).then(
        (data: any) => {
          callback(data);
        },
        (error: any) => {
          if (typeof error == "string") {
            errorCallback(error);
          } else {
            errorCallback(error.message);
          }
        }
      );
    });
  }

  /**
   * 微信朋友圈分享
   * @param title
   * @param desc
   * @param thumb
   * @param link
   */
  shareWXTimeLine(title: string, desc: string, thumb: string, link?: string) {
    return new Promise(function (callback, errorCallback) {
      UMShareModule.shareWithPlatformType(2, title, desc, thumb, link).then(
        (data: any) => {
          callback(data);
        },
        (error: any) => {
          if (typeof error == "string") {
            errorCallback(error);
          } else {
            errorCallback(error.message);
          }
        }
      );
    });
  }

  /**
   * QQ分享
   * @param title
   * @param desc
   * @param thumb
   * @param link
   */
  shareQQ(title: string, desc: string, thumb: string, link: string) {
    return new Promise(function (callback, errorCallback) {
      UMShareModule.shareWithPlatformType(4, title, desc, thumb, link).then(
        (data: any) => {
          callback(data);
        },
        (error: any) => {
          if (typeof error == "string") {
            errorCallback(error);
          } else {
            errorCallback(error.message);
          }
        }
      );
    });
  }

  /**
   * QQ空间分享
   * @param title
   * @param desc
   * @param thumb
   * @param link
   */
  shareQzone(title: string, desc: string, thumb: string, link: string) {
    return new Promise(function (callback, errorCallback) {
      UMShareModule.shareWithPlatformType(5, title, desc, thumb, link).then(
        (data: any) => {
          callback(data);
        },
        (error: any) => {
          if (typeof error == "string") {
            errorCallback(error);
          } else {
            errorCallback(error.message);
          }
        }
      );
    });
  }

  /**
   * 新浪分享
   * @param title
   * @param desc
   * @param thumb
   * @param link
   */
  shareSina(title: string, desc: string, thumb: string, link: string) {
    return new Promise(function (callback, errorCallback) {
      UMShareModule.shareWithPlatformType(0, title, desc, thumb, link).then(
        (data: any) => {
          callback(data);
        },
        (error: any) => {
          if (typeof error == "string") {
            errorCallback(error);
          } else {
            errorCallback(error.message);
          }
        }
      );
    });
  }

  /**
   * 微信登录
   * @returns {Promise}
   */
  loginWX() {
    return new Promise(function (callback, errorCallback) {
      UMShareModule.login("weixin").then(
        (data: any) => {
          if (Platform.OS == "android") {
            let originalResponse: any = {};
            originalResponse.openid = data.openid;
            originalResponse.province = data.province;
            originalResponse.city = data.city;
            originalResponse.unionid = data.unionid;
            originalResponse.headimgurl = data.profile_image_url;
            originalResponse.nickname = data.screen_name;
            originalResponse.sex =
              data.gender == "男" ? 1 : data.gender == "女" ? 2 : 0;
            data.originalResponse = originalResponse;
          }
          callback(data);
        },
        (error: any) => {
          if (typeof error == "string") {
            errorCallback(error);
          } else {
            errorCallback(error.message);
          }
        }
      );
    });
  }

  /**
   * QQ登录
   * @returns {Promise}
   */
  loginQQ() {
    return new Promise(function (callback, errorCallback) {
      UMShareModule.login("qq").then(
        (data: any) => {
          callback(data);
        },
        (error: any) => {
          if (typeof error == "string") {
            errorCallback(error);
          } else {
            errorCallback(error.message);
          }
        }
      );
    });
  }
}

export default new UMShare();
