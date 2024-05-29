import { CommonModule } from '@angular/common';
import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-grid',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './grid.component.html',
  styleUrl: './grid.component.css'
})
export class GridComponent {

  source = [{Id : "1",gameId : "1", pos : 1, monsterId : 0, nearbyBombs : 1, revealed : false},{Id : "1",gameId : "1", pos : 1, monsterId : 0, nearbyBombs : 1, revealed : false},{Id : "1",gameId : "1", pos : 1, monsterId : 0, nearbyBombs : 1, revealed : false}];

  cells : Cell[][] = [[]];

  fillCells() {

    for (let i = 0; i < 169; i+=13) {
      for(let j = 0; j < 169; j+=13)
        this.cells[i / 13][i % 13] = this.source[i];
    }
  }
}

interface Cell {
  Id : string;
  gameId : string;
  pos: number;
  monsterId : number;
  nearbyBombs : number;
  revealed : boolean;
}