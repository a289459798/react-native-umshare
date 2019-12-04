/**
 * Created by zhangzy on 16/7/27.
 */

'use strict';
import React, {
    Component,
} from 'react';

var ReactNative = require('react-native');
import { NativeModules } from 'react-native';
var UMAnalytics = NativeModules.UMAnalyticsModule;
var {
    Platform,
    } = ReactNative;

class Analytics {


    init(appkey, debug) {

        UMAnalytics.init(appkey, debug)
    }

    event(name) {

        UMAnalytics.event(name)
    }

    pageBegin(name) {

        UMAnalytics.pageBegin(name)
    }

    pageEnd(name) {

        UMAnalytics.pageEnd(name)
    }

}

Analytics = new Analytics();

module.exports = Analytics;
