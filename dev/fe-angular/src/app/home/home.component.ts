import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterOutlet } from '@angular/router';
import { GamesService } from '../services/games.service';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [FormsModule, ],
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss'
})
export class HomeComponent {

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
