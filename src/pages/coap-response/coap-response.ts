import {Component} from '@angular/core';
import {AlertController, IonicPage, NavController, NavParams} from 'ionic-angular';
import {Clipboard} from "@ionic-native/clipboard";
import {StringConverter} from "../../app/helper/string-converter";
import {CoapResponse} from "@ionic-native/coap-client";

/**
 * Generated class for the CoapResponsePage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@IonicPage()
@Component({
  selector: 'page-coap-response',
  templateUrl: 'coap-response.html',
})
export class CoapResponsePage {

  response: CoapResponse;
  JSON: JSON;
  stringConverter: StringConverter;
  showDetails: boolean = true;

  constructor(private clipboard: Clipboard, private alertCtrl: AlertController, public navCtrl: NavController, public navParams: NavParams) {
    this.JSON = JSON;
    this.stringConverter = StringConverter;
    this.response = JSON.parse(this.navParams.get('response'));
  }

  ionViewDidLoad() {
    console.log('ionViewDidLoad CoapResponsePage');
  }

  toggleDetails() {
    console.log('toggleDetails');
    this.showDetails = !this.showDetails;
    console.log('showDetails', this.showDetails);
  }
}
