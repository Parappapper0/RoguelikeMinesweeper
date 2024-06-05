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
export class GameGridComponent implements OnInit {
  constructor(private gameService: GameService) {}

  flags: number = 40;
  time: string = '00:00';
  seconds: number = 0;
  gameResult: string = '';
  gameState: boolean = false; //true with game over
  id = '';
  timerInterval: any;
  source: Cell[] = [];

  blankCell: Cell = { value: '', id: '-1', pos: -1, nearbyMines: '-1', revealed: false };

  field: Cell[][] = [[]];

  ngOnInit(): void {
    this.newGame();
  }

  newGame() {
    this.flags = 25;
    this.time = '00:00';
    this.seconds = 0;
    this.gameResult = '';
    this.gameState = false; //true with game over
    this.id = '-1';
    this.source = [];
    this.field= [[]];
    clearInterval(this.timerInterval);
    this.timerInterval = null;

    this.gameService.getGame(this.id).subscribe((res) => {
      this.id = res.id;
    });

    this.initializeField();
  }

  initializeField() {
    for (let i = 0; i < 169; i++) {
      this.source.push({ ...this.blankCell });
    }

    this.field = this.convertArrayToMatrix(this.source, 13);
  }

  placeRevealedCells(revealedCells: Cell[]) {
    for (let i = 0; i < revealedCells.length; i++) {
      this.source[revealedCells[i].pos] = revealedCells[i];

      this.source[revealedCells[i].pos].value = revealedCells[i].nearbyMines;
    }

    this.field = this.convertArrayToMatrix(this.source, 13);
 
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

  onLeftClick(rowIndex: number, colIndex: number): void {
    if (!this.timerInterval) {
      this.startTimer();
    }

    this.gameService.update(rowIndex, colIndex, '0', this.id).subscribe((res) => {
      if (res == null) {
        
        const cell = this.field[rowIndex][colIndex];
        cell.value = '💣';
        cell.revealed = true; // to don't let users flag it
        this.gameOver(false);
        return;
      }

      if((res as Cell []).length == 144){
        this.placeRevealedCells(res as Cell[]);
        this.gameOver(true);
        return;
      }

      this.placeRevealedCells(res as Cell[]);
    });
  }

  onRightClick(event: MouseEvent, rowIndex: number, colIndex: number): void {
    event.preventDefault();
    if (this.gameState) return;
    if (!this.timerInterval) {
      this.startTimer();
    }

    const cell = this.field[rowIndex][colIndex];
    if (cell.nearbyMines != '-1') return;
    this.toggleFlag(cell);
  }

  gameOver(wl: boolean) {
    if (wl) this.gameResult = '<b>GAME OVER</b> <br> Bravo, hai vinto!';
    else this.gameResult = '<b>GAME OVER</b> <br> Peccato, hai perso!';
    this.gameState = true;
    clearInterval(this.timerInterval);

    for (let i = 0; i < this.field.length; i++) {
      for (let j = 0; j < this.field[i].length; j++) {
        this.field[i][j].revealed = true;
      }
    }

    clearInterval(this.timerInterval);
  }


  toggleFlag(cell: Cell): void {
    cell.revealed = !cell.revealed;
    cell.value = cell.revealed ? '🚩' : '';
    this.flags += cell.revealed ? -1 : 1; 
  }

  startTimer() {
    this.timerInterval = setInterval(() => {
      this.seconds++;
      const minutes = Math.floor(this.seconds / 60);
      const seconds = this.seconds % 60;
      this.time = `${minutes < 10 ? '0' : ''}${minutes}:${seconds < 10 ? '0' : ''}${seconds}`;
    }, 1000);
  }
}

interface Cell {
  id: string;
  pos: number;
  nearbyMines: string;
  revealed: boolean;
  value: string;
}