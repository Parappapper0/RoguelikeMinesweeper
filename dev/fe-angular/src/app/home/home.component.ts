import { Component } from '@angular/core';
import { CommonModule, NgIf } from '@angular/common';
import { GameGridComponent } from '../game-grid/game-grid.component';


@Component({
  selector: 'app-home',
  standalone: true,
  imports: [GameGridComponent, NgIf, CommonModule],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {

  gameStart : boolean = false;


  
}
