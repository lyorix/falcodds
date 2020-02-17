import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { take, map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class OddsService {

  constructor(private http: HttpClient) {
  }

  public computeOdds(empirePlan: string): Observable<string> {
    return this.http.post('http://localhost:8080/odds', JSON.parse(empirePlan)).pipe(
      take(1),
      map(result => result as string)
    );
  }
}
