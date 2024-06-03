import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-game-grid',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './game-grid.component.html',
  styleUrl: './game-grid.component.css'
})
export class GameGridComponent implements OnInit{
 
  flags : number = 40
  time : string = "00:00"
  source : Cell[] = [
    {value : "1", gameId : "1", pos : 1, monsterId : 0, nearbyBombs : 1, revealed : false}, {value : "1", gameId : "1", pos : 1, monsterId : 0, nearbyBombs : 4, revealed : false},{value : "1",gameId : "1", pos : 1, monsterId : 0, nearbyBombs : 1, revealed : false},{value : "1", gameId : "1", pos : 1, monsterId : 0, nearbyBombs : 1, revealed : false}, {value : "1",gameId : "1", pos : 1, monsterId : 0, nearbyBombs : 4, revealed : false},{value : "1",gameId : "1", pos : 1, monsterId : 0, nearbyBombs : 1, revealed : false},{value : "1", gameId : "1", pos : 1, monsterId : 0, nearbyBombs : 1, revealed : false}, {value : "1",gameId : "1", pos : 1, monsterId : 0, nearbyBombs : 4, revealed : false},{value : "1",gameId : "1", pos : 1, monsterId : 0, nearbyBombs : 1, revealed : false},{value : "1", gameId : "1", pos : 1, monsterId : 0, nearbyBombs : 1, revealed : false}, {value : "1",gameId : "1", pos : 1, monsterId : 0, nearbyBombs : 4, revealed : false},{value : "1",gameId : "1", pos : 1, monsterId : 0, nearbyBombs : 1, revealed : false},{value : "1", gameId : "1", pos : 1, monsterId : 0, nearbyBombs : 1, revealed : false}, {value : "1",gameId : "1", pos : 1, monsterId : 0, nearbyBombs : 4, revealed : false},{value : "1",gameId : "1", pos : 1, monsterId : 0, nearbyBombs : 1, revealed : false},{value : "1", gameId : "1", pos : 1, monsterId : 0, nearbyBombs : 1, revealed : false}, {value : "1",gameId : "1", pos : 1, monsterId : 0, nearbyBombs : 4, revealed : false},{value : "1",gameId : "1", pos : 1, monsterId : 0, nearbyBombs : 1, revealed : false},{value : "1", gameId : "1", pos : 1, monsterId : 0, nearbyBombs : 1, revealed : false}, {value : "1",gameId : "1", pos : 1, monsterId : 0, nearbyBombs : 4, revealed : false},{value : "1",gameId : "1", pos : 1, monsterId : 0, nearbyBombs : 1, revealed : false},{value : "1", gameId : "1", pos : 1, monsterId : 0, nearbyBombs : 1, revealed : false}, {value : "1",gameId : "1", pos : 1, monsterId : 0, nearbyBombs : 4, revealed : false},{value : "1",gameId : "1", pos : 1, monsterId : 0, nearbyBombs : 1, revealed : false},{value : "1", gameId : "1", pos : 1, monsterId : 0, nearbyBombs : 1, revealed : false}, {value : "1",gameId : "1", pos : 1, monsterId : 0, nearbyBombs : 4, revealed : false},
    
  ];

  table : Cell[][] = [[]];

  ngOnInit(): void {
    this.table = this.convertArrayToMatrix(this.source,13)

    console.log(this.table)
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

  clickedTile(cell : Cell){

  }


  onRightClick(event: MouseEvent, rowIndex: number, colIndex: number): void {
    event.preventDefault();

    // Your flagging logic here
    this.toggleFlag(rowIndex, colIndex);
  }

  onLeftClick(rowIndex: number, colIndex: number): void {
    // Your left-click logic here
  }

  toggleFlag(rowIndex: number, colIndex: number): void {
    const cell = this.table[rowIndex][colIndex];
    
    cell.revealed = !cell.revealed;
    cell.value = cell.revealed ? '🚩' : ''; // Update the display property based on flag status
  }

}

interface Cell {
  gameId : string;
  pos: number;
  monsterId : number;
  nearbyBombs : number;
  revealed : boolean;
  value : string;
}