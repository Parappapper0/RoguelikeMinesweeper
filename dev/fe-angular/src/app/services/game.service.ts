import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class GameService {

  constructor(private httpClient: HttpClient) { }

  getGames() : Observable<any> {
      return this.httpClient.get('http://localhost:4200/api/games/list');
  }

  getGame(id : number) : Observable<any> {
      if (id == -1) {

        return this.httpClient.get('http://localhost:4200/api/games');
      }
      return this.httpClient.get('http://localhost:4200/api/games?id=' + id);
  } 
}
