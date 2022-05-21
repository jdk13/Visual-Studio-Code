package Stuff;

public class chessmoves{
    public static void Main(String args[]){
        GenerateMoves("B");
        

    }

    private static void GenerateMoves(String string) {
        String[] letters = new String[]{"a", "b", "c", "d", "e", "f", "g", "h"};
        for(int i = 0; i <= 7; i++){
            for(int j = 1; j <= 8; j++){
                System.out.print(string + letters[i] + j + " ");
            }
        }
    }
}