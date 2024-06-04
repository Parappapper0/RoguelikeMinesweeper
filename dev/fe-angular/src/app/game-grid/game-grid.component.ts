import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { GameService } from '../services/game.service';

@Component({
  selector: 'app-game-grid',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './game-grid.component.html',
  styleUrl: './game-grid.component.css'
})
export class GameGridComponent implements OnInit{
 

  constructor(private gameService: GameService) {}

  flags : number = 40
  time : string = "00:00"
  seconds : number = 0
  source !:  Cell[];
  placeholderSource : Cell[] = [
    {value : "", id : "1", pos : 1, nearbyBombs : 1, revealed : false}, {value : "", id : "1", pos : 1, nearbyBombs : 4, revealed : false},{value : "",id : "1", pos : 1, nearbyBombs : 1, revealed : false},{value : "", id : "1", pos : 1, nearbyBombs : 1, revealed : false}, {value : "",id : "1", pos : 1, nearbyBombs : 4, revealed : false},{value : "",id : "1", pos : 1, nearbyBombs : 1, revealed : false},{value : "", id : "1", pos : 1, nearbyBombs : 1, revealed : false}, {value : "",id : "1", pos : 1, nearbyBombs : 4, revealed : false},{value : "",id : "1", pos : 1, nearbyBombs : 1, revealed : false},{value : "", id : "1", pos : 1, nearbyBombs : 1, revealed : false}, {value : "",id : "1", pos : 1, nearbyBombs : 4, revealed : false},{value : "",id : "1", pos : 1, nearbyBombs : 1, revealed : false},{value : "", id : "1", pos : 1, nearbyBombs : 1, revealed : false}, {value : "",id : "1", pos : 1, nearbyBombs : 4, revealed : false},{value : "",id : "1", pos : 1, nearbyBombs : 1, revealed : false},{value : "", id : "1", pos : 1, nearbyBombs : 1, revealed : false}, {value : "",id : "1", pos : 1, nearbyBombs : 4, revealed : false},{value : "",id : "1", pos : 1, nearbyBombs : 1, revealed : false},{value : "", id : "1", pos : 1, nearbyBombs : 1, revealed : false}, {value : "",id : "1", pos : 1, nearbyBombs : 4, revealed : false},{value : "",id : "1", pos : 1, nearbyBombs : 1, revealed : false},{value : "", id : "1", pos : 1, nearbyBombs : 1, revealed : false}, {value : "",id : "1", pos : 1, nearbyBombs : 4, revealed : false},{value : "",id : "1", pos : 1, nearbyBombs : 1, revealed : false},{value : "", id : "1", pos : 1, nearbyBombs : 1, revealed : false}, {value : "",id : "1", pos : 1, nearbyBombs : 4, revealed : false},
    
  ];

  table : Cell[][] = [[]];

  ngOnInit(): void {
    
    this.gameService.getGame(-1).subscribe(res => {

      console.log(res + " prova ")
      
    });

    this.gameService.getCells
    
    this.table = this.convertArrayToMatrix(this.placeholderSource,13)

  }

  convertArrayToMatrix<T>(array: T[], rowSize: number = 13): T[][] {
    const matrix: T[][] = [];
    let row: T[] = [];

    for (let i = 0; i < array.length; i++) {
        row.push(array[i]);
        if (row.length === rowSize) {
            matrix.push(row);
            row = [];
        }
    }

    if (row.length > 0) {
        matrix.push(row);
    }

    return matrix;
  }


  onRightClick(event: MouseEvent, rowIndex: number, colIndex: number): void {
    event.preventDefault();

    this.toggleFlag(rowIndex, colIndex);
  }

  onLeftClick(rowIndex: number, colIndex: number): void {
    this.gameService.update(colIndex,rowIndex,"0").subscribe(res => {

      console.log(res)
      
    });
    
  }

  toggleFlag(rowIndex: number, colIndex: number): void {
    const cell = this.table[rowIndex][colIndex];
    
    cell.revealed = !cell.revealed;
    cell.value = cell.revealed ? '🚩' : '';

  }

}


interface Cell {
  id : string;
  pos: number;
  nearbyBombs : number;
  revealed : boolean;
  value : string;
}