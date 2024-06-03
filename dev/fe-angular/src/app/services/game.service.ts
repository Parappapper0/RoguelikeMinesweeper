import { HttpClient, HttpHeaders } from '@angular/common/http';
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

  update(x : number, y : number, type : number){
    let http_headers = new HttpHeaders().set("Content-Type", "application/json")
    return this.httpClient.put('http://localhost:8080',
      '{"x" :"' +x+
      '" "y" : "'+y+
      '" "type" : "'+type+
      '"}', 
      {headers:http_headers})
  }
  
}
