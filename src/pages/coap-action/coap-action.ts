import {Component, ViewChild} from '@angular/core';
import {
  AlertController,
  Content,
  IonicPage,
  Loading,
  LoadingController,
  ModalController,
  NavParams,
  PopoverController
} from 'ionic-angular';
import {CoapEndpoint} from "../../app/models/coap-endpoint";
import {CoapProvider} from "../../providers/coap/coap";
import {CoapEndpointProvider} from "../../providers/coap-endpoint/coap-endpoint";
import {CoapResponsePage} from "../coap-response/coap-response";
import {StringConverter} from "../../app/helper/string-converter";
import {AdProvider} from "../../providers/ad/ad";
import {CoapClient, CoapResponse, MediaType} from '@ionic-native/coap-client';

/**
 * Generated class for the CoapActionPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@IonicPage()
@Component({
  selector: 'page-coap-action',
  templateUrl: 'coap-action.html',
})
export class CoapActionPage {
  @ViewChild(Content) content: Content;
  loading: Loading;
  title: string = 'CoAP Action';
  actions: Array<string> = ['DISCOVER', 'GET', 'POST', 'PUT', 'DELETE', 'PING'];
  selectedAction: string = this.actions[0];
  accepts: MediaType[] = [];

  resources: Array<any> = [];

  endpoint: CoapEndpoint;

  JSON: JSON;

  stringConverter: StringConverter;


  constructor(private coapProvider: CoapProvider,
              private alertCtrl: AlertController,
              private loadingCtrl: LoadingController,
              private coapEnpointProvider: CoapEndpointProvider,
              private popoverCtrl: PopoverController,
              private navParams: NavParams,
              private modalCtrl: ModalController,
              private adProvider: AdProvider,
              private coapClient: CoapClient
  ) {
    this.stringConverter = StringConverter;
    this.JSON = JSON;
    this.endpoint = CoapEndpoint.Instance(JSON.parse(this.navParams.get('endpoint')));
    this.coapClient.mediaTypes()
      .then((mediaTypes: MediaType[]) => {
        this.accepts = mediaTypes.sort(this.mediaTypesComparator);
      })
      .catch((error) => {
        console.log('MEDIATYPES ERROR', error);
      });
  }

  ionViewWillLeave() {
    this.coapEnpointProvider.saveEndpoint(this.endpoint);
    this.hideLoading();
  }

  coapAction() {
    console.log(this.selectedAction);
    this.presentLoading();
    switch (this.selectedAction) {
      case 'DISCOVER':
        this.resources = [];
        this.coapProvider.discover(this.endpoint)
          .then((links) => {
            this.hideLoading();
            this.resources = JSON.parse(links);
          }).catch((err) => {
          this.hideLoading();
          this.showError(err);
        });
        break;
      case 'PING':
        console.log('ENDPOINT', JSON.stringify(this.endpoint));
        this.coapProvider.ping(this.endpoint)
          .then((ping) => {
            this.hideLoading();
            this.showPingMessage(ping);
          }).catch((err) => {
          this.hideLoading();
          this.showError(err);
        });
        break;
      case 'GET':
        this.coapProvider.get(this.endpoint)
          .then((response: CoapResponse) => {
            this.hideLoading();
            this.showResponseModal(response);
          }).catch((err) => {
          this.hideLoading();
          this.showError(err);
        });
        break;
      case 'POST':
        this.coapProvider.post(this.endpoint)
          .then((response: CoapResponse) => {
            this.hideLoading();
            this.showResponseModal(response);
          }).catch((err) => {
          this.hideLoading();
          this.showError(err);
        });
        break;
      case 'PUT':
        this.coapProvider.put(this.endpoint)
          .then((response: CoapResponse) => {
            this.hideLoading();
            this.showResponseModal(response);
          }).catch((err) => {
          this.hideLoading();
          this.showError(err);
        });
        break;
      case 'DELETE':
        this.coapProvider.delete(this.endpoint)
          .then((response: CoapResponse) => {
            this.hideLoading();
            this.showResponseModal(response);
          }).catch((err) => {
          this.hideLoading();
          this.showError(err);
        });
        break;
    }
    this.adProvider.showIntersital();
  }

  showResponseModal(response: CoapResponse): void {
    this.modalCtrl.create(CoapResponsePage, {response: JSON.stringify(response)}).present();
  }


  presentLoading() {
    this.loading = this.loadingCtrl.create({
      content: this.selectedAction,
    });
    this.loading.present();
  }

  hideLoading() {
    if(this.loading) {
      this.loading.dismiss()
        .catch((err) => console.log(err));
    }
  }

  showPingMessage(ping: string) {
    this.alertCtrl.create({
      title: 'CoAP Ping',
      subTitle: 'Result: ' + ping,
      buttons: ['Dissmiss']
    }).present();
  }

  setResource(path: string) {
    this.endpoint.options.path = this.decodePath(path);
    this.content.scrollToTop(1500);
  }


  clearResources() {
    this.resources = [];
  }


  showError(error: string): void {
    this.alertCtrl.create({
      title: "Uups..",
      subTitle: error
    }).present();
  }

  decodePath(path: string) {
    return decodeURIComponent(path);
  }

  mediaTypesComparator(a: MediaType, b: MediaType) {
    return a.value.localeCompare(b.value);
  }
}
