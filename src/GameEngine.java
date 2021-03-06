/* Author: Joseph Blaine Olin
 * Date Created: February 18, 2016
 *
*/

import java.util.*;

public class GameEngine {
    private int [][] array;
    private int direction;
    private boolean [][] merges;
    private int boardSize = 4;
    
    // Constructor for GameEngine
    public GameEngine(){
        array = new int[boardSize][boardSize];
        merges = new boolean[boardSize][boardSize];
        direction = -1;
    }
    
    // Method to check if the game board is full
    private boolean isFull(){
        // code to be cleaned up later
        for(int i = 0; i < array.length; i++){
            for(int j = 0; j < array[i].length; j++){
                if(array[i][j] == 0){
                    return false;
                }
            }
        }
        return true;
    }// isFull
    
    // returns true if a tile with the number 2 was successfully added to the 
    // board. Returns false otherwise.
    private boolean addTile(){
        boolean continu;
        if(continu=!isFull()){
            int rowRand = 0;
            int colRand = 0;
            Random rand = new Random();
            do {
                rowRand = rand.nextInt(boardSize);
                colRand = rand.nextInt(boardSize);
            }while(array[rowRand][colRand] != 0);
            array[rowRand][colRand] = 2;
        }
        else {
            endGame();
        }
        // returns true if tile was added, false if not
        return continu;
    }
    
    // performs the shifting and merging of the tiles
    public void shift(){
        emptyMerges();
        switch(direction) {
            case 1:
                for(int row = 0; row < 4; row++){
                    for(int col = 1; col < 4; col++){
                        int index = col;
                        int next = array[row][index - 1];
                        int el = array[row][index];
                        while(next == 0 && el != 0 && index > 0){
                            array[row][index] = 0;
                            array[row][index - 1] = el;
                            index--;
                            if(index>0)
                                next = array[row][index - 1];
                        }// while
                        if(index <= 0){
                            index++;
                        }
                        if(next == el && !merges[row][index-1] && el != 0){
                            array[row][index - 1] = el * 2;
                            array[row][index] = 0;
                            merges[row][index-1] = true;
                        }
                        while(next == 0 && el != 0 && index > 0){
                            if(merges[row][index-1]){
                                merges[row][index] = false;
                                merges[row][index-1] = true;
                            }
                            array[row][index] = 0;
                            array[row][index - 1] = el;
                            index--;
                            if(index>0)
                                next = array[row][index - 1];
                        }// while
                    }// for
                }//for
                break;
            case 2:
                for(int row = 1; row < 4; row++){
                    for(int col = 0; col < 4; col++){
                        int index = row;
                        int next = array[index-1][col];
                        int el = array[index][col];
                        while(next == 0 && el != 0 && index > 0){
                            array[index][col] = 0;
                            array[index-1][col] = el;
                            index--;
                            if(index>0)
                                next = array[index-1][col];
                        }// while
                        if(index <= 0){
                            index++;
                        }
                        if(next == el && !merges[index-1][col] && el != 0){
                            array[index-1][col] = el * 2;
                            array[index][col] = 0;
                            merges[index-1][col] = true;
                        }
                         while(next == 0 && el != 0 && index > 0){
                            if(merges[index-1][col]){
                                merges[index][col] = false;
                                merges[index-1][col] = true;
                            }
                            array[index][col] = 0;
                            array[index-1][col] = el;
                            index--;
                            if(index>0)
                                next = array[index-1][col];
                        }// while
                    }// for
                }//for
                break;
            case 3:
                for(int row = 0; row < 4; row++){
                    for(int col = array.length-2; col >= 0; col--){
                        int index = col;
                        int next = array[row][index + 1];
                        int el = array[row][index];
                        while(next == 0 && el != 0 && index < 3){
                            array[row][index] = 0;
                            array[row][index + 1] = el;
                            index++;
                            if(index < 3)
                                next = array[row][index + 1];
                        }// while
                        if(index >= 3){
                            index--;
                        }
                        if(next == el && !merges[row][index+1] && el != 0){
                            array[row][index + 1] = el * 2;
                            array[row][index] = 0;
                            merges[row][index+1] = true;
                        }
                        while(next == 0 && el != 0 && index < 3){
                            if(merges[row][index+1]){
                                merges[row][index] = false;
                                merges[row][index+1] = true;
                            }
                            array[row][index] = 0;
                            array[row][index + 1] = el;
                            index++;
                            if(index < 3)
                                next = array[row][index + 1];
                        }// while
                    }// for
                }//for
                break;
            case 4:
                for(int row = array.length - 2; row >= 0; row--){
                    for(int col = 0; col < 4; col++){
                        int index = row;
                        int next = array[index+1][col];
                        int el = array[index][col];
                        while(next == 0 && el != 0 && index < 3){
                            array[index][col] = 0;
                            array[index+1][col] = el;
                            index++;
                            if(index<3)
                                next = array[index+1][col];
                        }// while
                        if(index >= 3){
                            index++;
                        }
                        if(next == el && !merges[index+1][col] && el != 0){
                            array[index+1][col] = el * 2;
                            array[index][col] = 0;
                            merges[index+1][col] = true;
                        }
                         while(next == 0 && el != 0 && index < 3){
                             if(merges[index+1][col]){
                                merges[index][col] = false;
                                merges[index+1][col] = true;
                            }
                            array[index][col] = 0;
                            array[index+1][col] = el;
                            index++;
                            if(index<3)
                                next = array[index+1][col];
                        }// while
                    }// for
                }//for
                break;
            default:
                System.out.println("Invalid direction");    
        }// switch
    }// shift
    
    // prints out the current state of the game board
    public void printOut(){
        for(int row = 0; row < array.length; row++){
            for(int col = 0; col < array[row].length; col++){
                System.out.printf("%5s ", array[row][col]);
            }
            System.out.println();
        }
    }
    
    // empties the merges list for another shift
    public void emptyMerges(){
        for(int i = 0; i < merges.length; i++){
            for(int j = 0; j < merges.length; j++){
                merges[i][j] = false;
            }
        }
    }
    
    public boolean unchangedBoard(int[][] arrCopy){
        
        int length = 0;
        boolean unchanged = true;
        while(length < array.length && unchanged){
            unchanged = Arrays.equals(arrCopy[length], array[length]);
            length++;
        }
        return unchanged;
    }
    
    // This method dispatches commands to start the game and keep it going
    public void run(){
        addTile();
        boolean go = addTile();
         
        while(go){
            printOut();
            System.out.println("\n###################");
            System.out.println("Left = 1  Up = 2  Right = 3 Down = 4");
            Scanner input = new Scanner(System.in);           
            int [][] arrCopy = new int[boardSize][boardSize];
            for(int i = 0; i < array.length; i++){
                for(int j = 0; j < array[i].length; j++){
                    arrCopy[i][j] = array[i][j];
                }
            }
            direction = input.nextInt();
            while(direction < 1 || direction > 4){
                System.out.println("Invalid Direction\nEnter Again\n");
                direction = input.nextInt();
            }
            shift();
            
            while(unchangedBoard(arrCopy)){
                direction = input.nextInt();
                while(direction < 1 || direction > 4){
                    System.out.println("Invalid Direction\nEnter Again\n");
                    direction = input.nextInt();
                }
                shift();
            }// while
            
            
            go = addTile();
            System.out.println("\n###################");
        }
        
    }// run
    
//    public void askToRestart(){
//        System.out.println("Play again? (y/n");
//        Scanner input = new Scanner(System.in);
//        String response = input.next();
//        if(response.equalsIgnoreCase("y")){
//            run();
//        }
//    }
    
    // This method displays the message at the end of the game.
    public void endGame(){
        System.out.println("GAME OVER");
        System.out.println("Score: ");
    }
    
    
    
}// GameEngine class
