import java.util.Scanner;

public class GameEngine {
    private int [][] array;
    private int direction;
    private boolean [][] merges;
    private int boardSize = 4;
    
    public GameEngine(){
        array = new int[boardSize][boardSize];
        merges = new boolean[boardSize][boardSize];
        direction = -1;
    }
    
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
    
    
    private boolean addTile(){
        boolean continu;
        if(continu=!isFull()){
            int rowRand = 0;
            int colRand = 0;
            do {
                rowRand = (int)(Math.random()*3);
                colRand = (int)(Math.random()*3);
            }while(array[rowRand][colRand] != 0);
            array[rowRand][colRand] = 2;
        }
        else {
            endGame();
        }
        // returns true if tile was added, false if not
        return continu;
    }
    
    public void shift(){
        emptyMerges();
        switch(direction) {
            case 0:
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
            case 1:
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
            case 2:
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
            case 3:
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
    
    public void printOut(){
        for(int[] row: array){
            for(int el: row){
                System.out.printf("%5s ", el);
            }
            System.out.println();
        }
    }
    
    public void emptyMerges(){
        for(int i = 0; i < merges.length; i++){
            for(int j = 0; j < merges.length; j++){
                merges[i][j] = false;
            }
        }
    }
    
    public void run(){
        addTile();
        boolean go = addTile();
         
        while(go){
            printOut();
            System.out.println("\n###################");
            System.out.println("Left = 0  Up = 1  Right = 2 Down = 3");
            Scanner input = new Scanner(System.in);
            direction = input.nextInt();
            shift();
            go = addTile();
            System.out.println("\n###################");
        }
        
        
    }// run
    
    public void endGame(){
        System.out.println("GAME OVER");
        System.out.println("Score: ");
//        System.out.println("Play again? (y/n");
//        Scanner input = new Scanner(System.in);
//        String response = input.next();
//        if(response.equalsIgnoreCase("y")){
//            run();
//        }
    }
    
    
    
}// GameEngine class
