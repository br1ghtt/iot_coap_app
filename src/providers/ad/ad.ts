import {Injectable} from '@angular/core';
import {AdMobFree, AdMobFreeBannerConfig, AdMobFreeInterstitialConfig} from "@ionic-native/admob-free";
import {Config} from "ionic-angular";

/*
  Generated class for the AdProvider provider.

  See https://angular.io/guide/dependency-injection for more info on providers
  and Angular DI.
*/
@Injectable()
export class AdProvider {
  private callCountMod: number;
  private actualInterstitialCallCount: number;
  private intertstitialCfg: AdMobFreeInterstitialConfig;
  private bannerCfg: AdMobFreeBannerConfig;
  constructor(private adMob: AdMobFree, private config: Config) {
    console.log('created adProvider');
    this.callCountMod = this.config.get('ad').interstitialCallCount;
    this.actualInterstitialCallCount = this.callCountMod - 1;

    this.bannerCfg = {
      id: this.config.get('ad').bannerid,
      isTesting: false,
      autoShow: true
    };
    this.adMob.banner.config(this.bannerCfg);

    this.intertstitialCfg = {
      id: this.config.get('ad').interstitialid,
      isTesting: false
    };
    this.adMob.interstitial.config(this.intertstitialCfg);

  }

  showIntersital():void {
    console.log(this.actualInterstitialCallCount);
    if(this.config.get('showAd')) {
      if (!(this.actualInterstitialCallCount % this.callCountMod)) {
        console.log(this.actualInterstitialCallCount % this.config.get('ad').interstitialCallCount);
        this.adMob.interstitial.prepare()
          .then(() => {
            this.adMob.interstitial.isReady()
              .then(() => {
                this.adMob.interstitial.show()
                  .then(() => console.log('Interstitial show'))
                  .catch(err => console.log('Interstitial', err))
              })
              .catch(err => console.log(err));
          });
      }
      this.actualInterstitialCallCount += 1;
      this.actualInterstitialCallCount = this.actualInterstitialCallCount % this.callCountMod;
    }
  }

  showBanner():void {
    if(this.config.get('showAd')) {
      this.adMob.banner.prepare()
        .then(() => {
          this.adMob.banner.show();
        })
        .catch(err => console.log(err));
    }
  }

}
