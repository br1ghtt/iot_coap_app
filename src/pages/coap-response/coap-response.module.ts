import {NgModule} from '@angular/core';
import {IonicPageModule} from 'ionic-angular';
import {CoapResponsePage} from './coap-response';

@NgModule({
  declarations: [
    CoapResponsePage,
  ],
  imports: [
    IonicPageModule.forChild(CoapResponsePage),
  ],
})
export class CoapResponsePageModule {}
