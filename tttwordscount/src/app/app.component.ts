import { Component } from '@angular/core';

import { AppService } from './app.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Top frequently occurred words count';
  private words: any[] = null;
  private number: string;
  private error: string;

  constructor(private _appService: AppService) { }

  getWordsCount() {
    if (this.number != null) {
      this._appService.getWordsCount(this.number).subscribe(
        words => {
          this.words = words;
          console.log(this.words);
        },
        error => {
          this.error = error;
          console.log(this.error);
        }
      );
    }
  }

  clearWords($event) {
    this.words = null;
    this.error = null;
  }

}
