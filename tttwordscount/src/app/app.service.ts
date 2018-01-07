import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable'
import { Http, Response, Headers, URLSearchParams , RequestOptions } from '@angular/http'
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/do';
import 'rxjs/add/operator/catch';
import 'rxjs/add/observable/throw';

@Injectable()
export class AppService {
  private baseURL:string= "http://localhost:8080/wordsplay/";
  constructor(private _http: Http) { }

  getWordsCount(number:string):Observable<any[]>{
    var httpUrl = this.baseURL+"counter/getwords?number="+  number;
    let headers = new Headers({
      'Content-Type':'application/json'
    });
    let searchParams = new URLSearchParams();
    searchParams.append('number', number);
    let options =new RequestOptions({ headers: headers }); 
      return this._http.get(httpUrl)
            .map((response: Response) => <any[]> response.json())
            .catch(this.handleError);
  }

  private handleError(error: Response) {
      return Observable.throw(error.json() || "Server Error");
  }
}
