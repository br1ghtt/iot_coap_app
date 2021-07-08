import {NgModule} from '@angular/core';
import {IonicPageModule} from 'ionic-angular';
import {CoapActionPage} from './coap-action';

@NgModule({
  declarations: [
    CoapActionPage,
  ],
  imports: [
    IonicPageModule.forChild(CoapActionPage),
  ],
})
export class CoapActionPageModule {}
