import { CommonModule, JsonPipe, NgIf } from '@angular/common';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';

import { GamesService } from './services/games.service';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, JsonPipe, HttpClientModule, FormsModule, CommonModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'fe-angular';

  gameData: any;
  gameID : number = 1;

  constructor(private gamesService: GamesService) {}

  newGame = () => {

    this.gamesService.getGame(-1).subscribe((data: any) => {
      
      this.gameData = data;
    });
  }

  loadGame = (id : number) => {

    this.gamesService.getGame(id).subscribe((data: any) => {

      this.gameData = data;
    })
  }

}
