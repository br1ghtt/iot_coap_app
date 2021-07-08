import {Component, ViewChild} from '@angular/core';
import {Nav, Platform} from 'ionic-angular';
import {StatusBar} from '@ionic-native/status-bar';
import {SplashScreen} from '@ionic-native/splash-screen';

import {CoapEndpointListPage} from "../pages/coap-endpoint-list/coap-endpoint-list";
import {AdProvider} from "../providers/ad/ad";

@Component({
  templateUrl: 'app.html'
})
export class MyApp {
  @ViewChild(Nav) nav: Nav;

  rootPage: any = CoapEndpointListPage;

  pages: Array<{title: string, component: any}>;

  constructor(public platform: Platform, public statusBar: StatusBar, public splashScreen: SplashScreen, private adProvider: AdProvider) {
    this.initializeApp();

    this.pages = [
      { title: 'Coap Enpoints', component: CoapEndpointListPage},
    ];

  }

  initializeApp() {
    this.platform.ready().then(() => {
      this.statusBar.styleDefault();
      this.splashScreen.hide();
      this.adProvider.showBanner();
    });
  }

  openPage(page) {
     this.nav.setRoot(page.component);
  }
}
