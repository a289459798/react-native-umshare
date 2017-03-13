/**
 * Created by zhangzy on 16/7/27.
 */

'use strict';

import {
    NativeModules,
    Platform,
    NativeEventEmitter
} from 'react-native';

const UMShareModule = NativeModules.UMShareModule;


class UMShare extends NativeEventEmitter {

    // 构造
    constructor(props) {
        super(UMShareModule);

        // 初始状态
        this.state = {};
    }

    /**
     * 初始化分享参数
     * @param appkey
     * @param sharePlatforms
     * @param debug
     */
    initShare(appkey: string, sharePlatforms: Object, debug: boolean) {

        UMShareModule.initShare(appkey, sharePlatforms, debug);
    }

    /**
     *
     * @param title
     * @param desc
     * @param thumb
     * @param link
     */
    share(title, desc, thumb, link) {

        return new Promise(function (callback, errorCallback) {
            UMShareModule.share(title, desc, thumb, link)
                .then((data) => {
                callback(data);
            }, (error) => {
                errorCallback(error);
            });
        });

    }

    /**
     * 微信登录
     * @returns {Promise}
     */
    loginWX() {
        return new Promise(function(callback, errorCallback) {
            UMShareModule.login("weixin")
                .then((data) => {
                callback(data);
            }, (error) => {
                errorCallback(error);
            });
        });
    }

    /**
     * QQ登录
     * @returns {Promise}
     */
    loginQQ() {
        return new Promise(function(callback, errorCallback) {
            UMShareModule.login("qq")
                .then((data) => {
                callback(data);
            }, (error) => {
                errorCallback(error);
            });
        });
    }
}

UMShare = new UMShare();

module.exports = UMShare;