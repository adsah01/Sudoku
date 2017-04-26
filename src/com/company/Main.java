package com.company;

public class Main {

    static int[][] box = new int[][]{
            { 0, 4, 3, 7, 0, 0, 9, 0, 8},
            { 0, 0, 5, 0, 3, 0, 0, 0, 0},
            { 0, 1, 0, 0, 0, 0, 3, 0, 0},
            { 6, 0, 0, 0, 2, 7, 0, 0, 0},
            { 4, 0, 7, 0, 0, 0, 1, 0, 3},
            { 0, 0, 0, 5, 4, 0, 0, 0, 9},
            { 0, 0, 2, 0, 0, 0, 0, 3, 0},
            { 0, 0, 0, 0, 5, 0, 4, 0, 0},
            { 5, 0, 4, 0, 0, 1, 2, 6, 0}
    };

    int[][] checkBox = new int[][]{
            { 0, 0, 0, 0, 0, 0, 0, 0, 0},
            { 0, 0, 0, 0, 0, 0, 0, 0, 0},
            { 0, 0, 0, 0, 0, 0, 0, 0, 0},
            { 0, 0, 0, 0, 0, 0, 0, 0, 0},
            { 0, 0, 0, 0, 0, 0, 0, 0, 0},
            { 0, 0, 0, 0, 0, 0, 0, 0, 0},
            { 0, 0, 0, 0, 0, 0, 0, 0, 0},
            { 0, 0, 0, 0, 0, 0, 0, 0, 0},
            { 0, 0, 0, 0, 0, 0, 0, 0, 0}
    };

    public static void main(String[] args) {
	// write your code here
        Main main = new Main();
        main.checkForDigits();
        main.sudokuSolver(0,0, box[0][0]);
        System.out.println("Grattis! Här har du din sudoku!!");
        for (int i = 0; i < box.length; i++) {
            for (int j = 0; j < box[i].length; j++) {
                System.out.print(box[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void checkForDigits(){
        for(int i = 0; i < box.length; i++){
            for(int j = 0; j < box[i].length; j++){
                if(box[i][j]!= 0){
                    checkBox[i][j] = 10;
                }
            }
        }
    }


    public void sudokuSolver(int x, int y, int num){
        int count = 0;
        for (int i = 0; i < box.length; i++) {
            for (int j = 0; j < box[i].length; j++) {
                if(box[i][j] != 0){
                    count += 1;
                }
            }
        }
        if(count < 81){
            if (point(y, x, num)) {
                if (y == 8) {
                    y = 0;
                    x += 1;
                } else {
                    y += 1;
                }
                for (int i = 0; i < box.length; i++) {
                    for (int j = 0; j < box[i].length; j++) {
                        System.out.print(box[i][j] + " ");
                    }
                    System.out.println();
                }System.out.println();

                num = 0;
                sudokuSolver(x, y, num);
            }else{
                if(y >= 1){
                    y = y-1;
                    checkForConstant(x, y);
                }
                else if (y == 0){
                    if(x > 0) {
                        y = 8;
                        x = x - 1;
                    }checkForConstant(x, y);
                }
            }
        }
    }

    public boolean point(int x, int y, int num){
        if(checkBox[y][x] != 10){
            box[y][x] = testForDigit(x, y, num);
            if(box[y][x] == 0){
                return false;
            }
        }return true;
    }

    public int testForDigit(int x, int y, int num) {
        switch (num){
            case 0:
                if(checkNumber(x, y, num+1)){
                    return 1;
                }num = 1;
            case 1:
                if(checkNumber(x, y, num+1)){
                    return 2;
                }num = 2;
            case 2:
                if(checkNumber(x, y, num+1)){
                    return 3;
                }num = 3;
            case 3:
                if(checkNumber(x, y, num+1)){
                    return 4;
                }num = 4;
            case 4:
                if(checkNumber(x, y, num+1)){
                    return 5;
                }num = 5;
            case 5:
                if(checkNumber(x, y, num+1)){
                    return 6;
                }num = 6;
            case 6:
                if(checkNumber(x, y, num+1)){
                    return 7;
                }num = 7;
            case 7:
                if(checkNumber(x, y, num+1)){
                    return 8;
                }num = 8;
            case 8:
                if(checkNumber(x, y, num+1)){
                    return 9;
                }
            case 9:
                if(checkNumber(x, y, num+1)){
                    return 0;
                }
            default:
                return 0;
        }
    }

    public boolean checkNumber(int x, int y, int num){
        if(boxCondition(x,y,num) && rowCondition(x,y,num)){
            return true;
        }return false;
    }

    public boolean boxCondition(int x, int y, int num){

        if(y <= 2 && x <= 2){
            return checkBoxCondition(0,2,0,2,num);
        }
        else if(y >= 3 && y <= 5 && x <= 2){
            return checkBoxCondition(3,5,0,2,num);
        }
        else if(y >= 3 && y <= 5 && x >= 3 && x <= 5){
            return checkBoxCondition(3,5,3,5,num);
        }
        else if((y >= 3 && y <= 5) && (x >= 6 && x <= 8)){
            return checkBoxCondition(3,5,6,8,num);
        }
        else if((y >= 6 && y <= 8) && (x >= 3 && x <= 5)){
            return checkBoxCondition(6,8,3,5,num);
        }
        else if(y >= 6 && y <= 8 && x <= 2){
            return checkBoxCondition(6,8,0,2,num);
        }
        else if(y <= 2 && x >= 3 && x <= 5){
            return checkBoxCondition(0,2,3,5,num);
        }
        else if(y <= 2 && x >= 6 && x <= 8){
            return checkBoxCondition(0,2,6,8,num);
        }
        else if(y >= 6 && y <= 8 && x >= 6 && x <= 8){
            return checkBoxCondition(6,8,6,8,num);
        }return true;
    }

    public boolean rowCondition(int x, int y, int num){
        int count = 0;
        for (int i = 0; i < box.length; i++) {
            if (box[i][x] != num) {
                count = count + 1;
            }
            if (box[y][i] != num) {
                count = count + 1;
            }
        }
        if (count < 18) {
            return false;
        }return true;
    }

    public boolean checkBoxCondition(int x, int y, int z, int w, int num){
        for(int i = x; i <= y; i++){
            for(int j = z; j <= w; j++){
                if(num == box[i][j]){
                    return false;
                }
            }
        }return true;
    }

    public void checkForConstant(int x, int y){
        while(checkBox[x][y] == 10) {
            if(y >= 1){
                y -= 1;
            }if (y == 0 && checkBox[x][y] == 10) {
                if (x > 0) {
                    y = 8;
                    x -= 1;
                }
            }
        }sudokuSolver(x,y,box[x][y]);
    }
}