import { Component, ChangeDetectionStrategy, ChangeDetectorRef, OnInit } from '@angular/core';
import { OddsService } from './services/odds.service';

const enum AppState {
  Init,
  Loading,
  Loaded,
  Error
}

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class AppComponent implements OnInit {

  odds: string;
  state: AppState;

  constructor(
    private oddsService: OddsService,
    private cdr: ChangeDetectorRef) {
  }

  ngOnInit(): void {
    this.state = AppState.Init;
  }

  public computeOdds(empirePlan): void {
    this.state = AppState.Loading;
    this.oddsService.computeOdds(empirePlan).subscribe(res => {
      this.odds = res;
      this.state = AppState.Loaded;
      this.cdr.markForCheck();
    }, err => {
      this.odds = undefined;
      this.state = AppState.Error;
      this.cdr.markForCheck();
    });
  }

  public isLoading(): boolean {
    return this.state === AppState.Loading;
  }

  public hasError(): boolean {
    return this.state === AppState.Error;
  }
}
