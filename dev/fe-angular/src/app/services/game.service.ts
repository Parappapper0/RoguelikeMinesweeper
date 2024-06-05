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

  getGame(id : any) : Observable<any> {
      
      return this.httpClient.get('http://localhost:4200/api/games?id=' + id);
  } 

  getCells(id : any) : Observable<any> {
    return this.httpClient.get('http://localhost:4200/api/games/cells?id=' + id);
}

  update(x : number, y : number, actionType : string, id : string){
    let http_headers = new HttpHeaders().set("Content-Type", "application/json")
    return this.httpClient.put('http://localhost:4200/api/games/' + id,
      '{"x" :"' +x+
      '", "y" : "'+y+
      '", "actionType" : "'+actionType+
      '"}', 
      {headers:http_headers})
  }
  
}
