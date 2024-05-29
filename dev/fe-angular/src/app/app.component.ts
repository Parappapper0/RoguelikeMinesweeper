import { CommonModule, JsonPipe, NgIf } from '@angular/common';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';

import { HomeComponent } from './home/home.component';
import { GameService } from './services/game.service';
import { GridComponent } from './grid/grid.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, HomeComponent, GridComponent, JsonPipe, HttpClientModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'fe-angular';

  tutorials: any;

  constructor(private gameService: GameService) {}

  ngOnInit() {
    this.gameService.getGames().subscribe((data: any) => {
      this.tutorials = data;
    });

  }

}
