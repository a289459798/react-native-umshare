/**
 * Created by zhangzy on 16/7/27.
 */

'use strict';
var ReactNative = require('react-native');
import { NativeModules } from 'react-native';
var UMAnalytics = NativeModules.UMAnalyticsModule;
var {
    Platform,
    } = ReactNative;

export default new class Analytics {


    init(appkey: string, debug: boolean) {
        UMAnalytics.init(appkey, debug)
    }

    event(name: string) {
        UMAnalytics.event(name)
    }

    pageBegin(name: string) {
        UMAnalytics.pageBegin(name)
    }

    pageEnd(name: string) {
        UMAnalytics.pageEnd(name)
    }

}()
