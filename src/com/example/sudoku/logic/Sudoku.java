package com.example.sudoku.logic;

import com.example.sudoku.logic.hideCells.CellHider;

import java.util.Random;

public class Sudoku {
    final int SIZE;
    private int level;
    private Cell cell;

    public int getLevel() {
        return level;
    }


    public Sudoku(int size) {
        SIZE = size;
    }

    /**
     * generate sudoku board as an object that is an array of cells
     * @return sudoku board ready to game, and containing information about the correct value of cells, about the hidden cells and about the level
     */
    public Cell[][] generateSudoku() {
        CellHider cellHider = new CellHider(new Random());
        Cell[][] sudoku = cellHider.makeBoardWithHiddenCells(SIZE);
        level = sortBoardsByLevels(sudoku);
        return sudoku;
    }

    /**
     *
     * @param sudoku for printing
     * @param clearingHidden to print the correct sudoku pass false, to print the sudoku with hidden cells pass true
     */
    public void printTheSudoku(Cell[][] sudoku, boolean clearingHidden) {
        for (Cell[] cells : sudoku) {
            for (Cell value : cells) {
               if (clearingHidden && value.HIDING) System.out.print("0" + " ");
              else System.out.print(value.CORRECT_VALUE + " ");
            }
            System.out.println();
        }
    }

    private int sortBoardsByLevels(Cell[][] sudoku) {
            int count = countAmountOfHiddenCells(sudoku);
            if (count >= 28 && count < 35) level =  1;
            else if (count >= 35 && count < 42) level = 2;
            else if (count >= 42) level = 3;
            else generateSudoku();
            return level;
    }

    private int countAmountOfHiddenCells(Cell[][] sudoku) {
        int count = 0;
        for (Cell[] cells : sudoku) {
            for (Cell value : cells) {
                if (value.HIDING) count++;
            }
        }
        return count;
    }

}