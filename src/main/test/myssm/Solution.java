package myssm;

import java.util.HashMap;

public class Solution {
    public boolean isValidSudoku(char[][] board) {
        //数字 1-9 在每一行只能出现一次。
        for(int i=0;i<9;i++){
            HashMap<Character,Integer> map = new HashMap<>();
            for(int j=0;j<9;j++){
                if(map.get(board[i][j])!=null){
                    return false;
                }
                if(Character.isDigit(board[i][j])){
                    map.put(board[i][j],1);
                }

            }
        }
        //数字 1-9 在每一列只能出现一次。
        for(int i=0;i<9;i++){
            HashMap<Character,Integer> map = new HashMap<>();
            for(int j=0;j<9;j++){
                if(map.get(board[j][i])!=null){
                    return false;
                }
                if(Character.isDigit(board[j][i])){
                    map.put(board[j][i],1);
                }
            }
        }
        //数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。

        for(int i=0;i<9;i++){
            HashMap<Character,Integer> map = new HashMap<>();
            int temp =(i*3)/9;
            int j=temp*3;
            int k=temp*3+(i*3)%9;
            for(;j<temp*3+3;j++){
                for(;k<temp*3+(i*3)%9+3;k++){
                    if(map.get(board[j][k])!=null){
                        return false;
                    }
                    if(Character.isDigit(board[j][k])){
                        map.put(board[j][k],1);
                    }
                }
            }


        }
        return true;
    }


}
