import {BrowserModule} from '@angular/platform-browser';
import {ErrorHandler, NgModule} from '@angular/core';
import {IonicApp, IonicErrorHandler, IonicModule} from 'ionic-angular';

import {MyApp} from './app.component';

import {StatusBar} from '@ionic-native/status-bar';
import {SplashScreen} from '@ionic-native/splash-screen';
import {CoapEndpointPage} from "../pages/coap-endpoint/coap-endpoint";
import {CoapEndpointProvider} from '../providers/coap-endpoint/coap-endpoint';
import {IonicStorageModule} from "@ionic/storage";
import {CoapEndpointListPage} from "../pages/coap-endpoint-list/coap-endpoint-list";
import {CoapActionPage} from "../pages/coap-action/coap-action";
import {CoapProvider} from '../providers/coap/coap';
import {HttpClientModule} from "@angular/common/http";
import {CoapClient} from "@ionic-native/coap-client";
import {AdMobFree} from "@ionic-native/admob-free";
import {CoapActionPageModule} from "../pages/coap-action/coap-action.module";
import {CoapEndpointListPageModule} from "../pages/coap-endpoint-list/coap-endpoint-list.module";
import {CoapEndpointPageModule} from "../pages/coap-endpoint/coap-endpoint.module";
import {HomePage} from "../pages/home/home";
import {DNS} from "@ionic-native/dns";
import {CoapResponsePageModule} from "../pages/coap-response/coap-response.module";
import {Clipboard} from "@ionic-native/clipboard";
import {AdProvider} from '../providers/ad/ad';

@NgModule({
  declarations: [
    MyApp,
    HomePage
  ],
  imports: [
    CoapResponsePageModule,
    CoapActionPageModule,
    CoapEndpointListPageModule,
    CoapEndpointPageModule,
    BrowserModule,
    HttpClientModule,
    IonicModule.forRoot(MyApp, {
      showAd: true,
      ad: {
        bannerid: 'ca-app-pub-8632145624745659/6931795890',
        interstitialid: 'ca-app-pub-8632145624745659/6712050451',
        interstitialCallCount: 20
      }
    }),
    IonicStorageModule.forRoot()
  ],
  bootstrap: [IonicApp],
  entryComponents: [
    MyApp,
    HomePage,
    CoapEndpointPage,
    CoapEndpointListPage,
    CoapActionPage,
  ],
  providers: [
    AdProvider,
    Clipboard,
    DNS,
    AdMobFree,
    StatusBar,
    SplashScreen,
    {provide: ErrorHandler, useClass: IonicErrorHandler},
    CoapEndpointProvider,
    CoapProvider,
    CoapClient,
    AdProvider,
    AdProvider,
  ]
})
export class AppModule {}
