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
}

UMShare = new UMShare();

module.exports = UMShare;