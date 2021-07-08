export class StringConverter {
  static payloadToString(payload: string) {
    if (payload) {
      var hex = this.toHexString(payload);
      var ascii = this.hexToASCII(hex);
      var decodedPayload = decodeURIComponent(ascii);
      return decodedPayload;
    } else {
      return payload;
    }
  }


  static hexToASCII(str1)
  {
    var hex  = str1.toString();
    var str = '';
    for (var n = 0; n < hex.length; n += 2) {
      str += String.fromCharCode(parseInt(hex.substr(n, 2), 16));
    }
    console.log(str);
    return str;
  }

  static toHexString(byteArray: string): string {
    return Array.from(byteArray, (byte: any)  => {
      return ('0' + (byte & 0xFF).toString(16)).slice(-2);
    }).join('')
  }

}
