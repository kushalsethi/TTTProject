import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'keyvalue'
})
export class AppPipe implements PipeTransform {

  transform(value: any, args?: any): any {
    let keyValuePair= [];
    for(let key in value){
      keyValuePair.push({name:key, count:value[key]});
    }
    return keyValuePair;
  }

}
