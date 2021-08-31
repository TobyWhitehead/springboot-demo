package com.example.demo;

import java.util.Scanner;
import java.util.ArrayList;

public class Minesweeper {

    static Tile[][] grid = new Tile[10][10];
    static boolean gameOver = false;
    static int uncoverCount = 0;

    public static int generateGrid(Tile[][] grid) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                grid[i][j] = new Tile();
            }
        }
        int numMines = 0;
        do {
            int randNumY = (int) (Math.random() * 10);
            int randNumX = (int) (Math.random() * 10);
            if (!grid[randNumY][randNumX].getMine()) {
                grid[randNumY][randNumX].setMine(true);
                numMines++;
            }
        } while (numMines < 10);
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                int adjMines = 0;
                if (i > 0 && j > 0 && grid[i - 1][j - 1].getMine()) {
                    adjMines++;
                }
                if (j > 0 && grid[i][j - 1].getMine()) {
                    adjMines++;
                }
                if (i < 9 && j > 0 && grid[i + 1][j - 1].getMine()) {
                    adjMines++;
                }
                if (i > 0 && grid[i - 1][j].getMine()) {
                    adjMines++;
                }
                if (i < 9 && grid[i + 1][j].getMine()) {
                    adjMines++;
                }
                if (i > 0 && j < 9 && grid[i - 1][j + 1].getMine()) {
                    adjMines++;
                }
                if (j < 9 && grid[i][j + 1].getMine()) {
                    adjMines++;
                }
                if (i < 9 && j < 9 && grid[i + 1][j + 1].getMine()) {
                    adjMines++;
                }
                grid[i][j].setNumber(adjMines);
            }
        }
        return numMines;
    }

    public static String displayGrid(Tile[][] grid) {
        String sampleString = "";
        for(Tile[] a : grid) {

            String[] place = new String[10];

            for(int k = 0; k < place.length; k++){
                if(a[k].getCoverStatus()){
                    place[k] = " ";
                }
                else{
                    place[k] = Integer.toString(a[k].getNumber());
                }
            }

            ArrayList<String> outputString = new ArrayList<>();
            for (String s : place) {
                String s1 = "[" + s + "]";
                outputString.add(s1);
            }
            StringBuilder builder = new StringBuilder();
            for (String value : outputString) {
                builder.append(value);
            }
            String textOutput = builder.toString();
            System.out.println(textOutput);
            sampleString = textOutput;
        }
        return sampleString;
    }

    public static void clickTile() {
        System.out.println("Enter row number:");
        Scanner keyboard = new Scanner(System.in);
        int xInput = Integer.parseInt(keyboard.nextLine());
        System.out.println("Enter column number:");
        Scanner keyboard2 = new Scanner(System.in);
        int yInput = Integer.parseInt(keyboard2.nextLine());
        try {
            if (grid[xInput - 1][yInput - 1].getCoverStatus()) {
                uncoverCount++;
            }
            grid[xInput - 1][yInput - 1].setCoverStatus(false);
            if (grid[xInput - 1][yInput - 1].getMine()) {
                gameOver = true;
                System.out.println("you hit a mine, game over");
            } else if (uncoverCount >= 90) {
                gameOver = true;
                displayGrid(grid);
                System.out.println("all empty tiles cleared, game over");
            } else {
                displayGrid(grid);
            }
        }catch(IndexOutOfBoundsException e){
            System.out.println("index out of bounds");
            displayGrid(grid);
        }
    }

    public static void main(String[] args) {
        //put in do while loop for option to play multiple games
        generateGrid(grid);
        displayGrid(grid);
        do {
            clickTile();
        }while(!gameOver);
    }
}

