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
    shareMiniProgram(name, title, desc, path, image, link, type, mode) {

        return new Promise(function (callback, errorCallback) {
            UMShareModule.shareMiniProgram(name, title, desc, path, image, link, type, mode)
                .then((data) => {
                callback(data);
        }, (error) => {
                errorCallback(error);
            });
        });

    }

    /**
     *
     * @param thumb
     */
    shareImage(thumb) {

        return new Promise(function (callback, errorCallback) {
            UMShareModule.shareImage(thumb)
                .then((data) => {
                    callback(data);
                }, (error) => {
                    errorCallback(error);
                });
        });

    }

    /**
     * 微信分享
     * @param title
     * @param desc
     * @param thumb
     * @param link
     */
    shareWX(title, desc, thumb, link) {

        return new Promise(function (callback, errorCallback) {
            UMShareModule.shareWithPlatformType(1, title, desc, thumb, link)
                .then((data) => {
                callback(data);
        }, (error) => {
                errorCallback(error);
            });
        });

    }

    /**
     * 微信朋友圈分享
     * @param title
     * @param desc
     * @param thumb
     * @param link
     */
    shareWXTimeLine(title, desc, thumb, link) {

        return new Promise(function (callback, errorCallback) {
            UMShareModule.shareWithPlatformType(2, title, desc, thumb, link)
                .then((data) => {
                callback(data);
        }, (error) => {
                errorCallback(error);
            });
        });

    }

    /**
     * QQ分享
     * @param title
     * @param desc
     * @param thumb
     * @param link
     */
    shareQQ(title, desc, thumb, link) {

        return new Promise(function (callback, errorCallback) {
            UMShareModule.shareWithPlatformType(4, title, desc, thumb, link)
                .then((data) => {
                callback(data);
        }, (error) => {
                errorCallback(error);
            });
        });

    }

    /**
     * QQ空间分享
     * @param title
     * @param desc
     * @param thumb
     * @param link
     */
    shareQzone(title, desc, thumb, link) {

        return new Promise(function (callback, errorCallback) {
            UMShareModule.shareWithPlatformType(5, title, desc, thumb, link)
                .then((data) => {
                callback(data);
        }, (error) => {
                errorCallback(error);
            });
        });

    }

    /**
     * 新浪分享
     * @param title
     * @param desc
     * @param thumb
     * @param link
     */
    shareSina(title, desc, thumb, link) {

        return new Promise(function (callback, errorCallback) {
            UMShareModule.shareWithPlatformType(0, title, desc, thumb, link)
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