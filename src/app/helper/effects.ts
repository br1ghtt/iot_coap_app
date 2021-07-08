import {Content} from "ionic-angular";

export class Effects {
  public static scrollTo(content: Content, element:string, duration: number) {
    let elem = document.getElementById(element);
    var box = elem.getBoundingClientRect();

    var body = document.body;
    var docEl = document.documentElement;

    var scrollTop = window.pageYOffset || docEl.scrollTop || body.scrollTop;
    var clientTop = docEl.clientTop || body.clientTop || 0;
    var top  = box.top +  scrollTop - clientTop;
    var cDim = content.getContentDimensions();

    var scrollOffset = Math.round(top) + cDim.scrollTop - cDim.contentTop;

    content.scrollTo(0, scrollOffset, duration);
  }
}
