package Stuff;

import java.util.ArrayList;
import java.awt.Robot;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Stuff {
    static boolean ambiguation = false;
    static boolean repeat = false;
    static boolean pawn = false;
    static String[] letters = new String[] { "a", "b", "c", "d", "e", "f", "g", "h" };
    static String[] marks = new String[] { " ", "+ ", "# " };
    static String[] promotion = new String[] { "=B", "=N", "=Q", "=R", "" };
    static boolean SkipPromotion = false;
    static boolean onecapture = true;
    static ArrayList<String> validSquares;

    public static void main(String args[]) {

        //typeText("runas /user:Young \"C:\\Users\\Jacob\\Downloads\\SteamSetup.exe\"");
        possiblePlugboard();

        // GenerateMoves("");
        // GenerateMoves("B");
        // GenerateMoves("R");
        // GenerateMoves("Q");
        // GenerateMoves("N");
        // GenerateMoves("K");

    }

    /*public static void typeText(String text) {
        String[] pass = new String[10000];
        text = text.toUpperCase();
        char[] chars = text.toCharArray();
        int[] convert = new int[chars.length];
        for (int o = 0; o < chars.length; o++) {
            convert[o] = chars[o];
        }
        for (int i = 1000; i < 10000; i++) {
            String p = i + "";
            if (p.length() < 4) {
                int cap = p.length();
                for (int j = 0; j < 4 - cap; j++) {
                    p = "0" + p;
                }
            }
            pass[i] = p;
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        System.out.println("pass");
        for (int k = 1000; k < 10000; k++) {
            try {
                Robot robot = new Robot();
                // If the chars aren't uppercased then it will not press the right key. in
                // keyPress and keyRelease
                robot.delay(3000);

                /*for (int i = 0; i < convert.length; i++) {
                    // If the char is uppercased then it will hold shift so it can write the
                    // uppercase char.
                    // runas /user:young "c:\\users\jacob\downloads\steamsetup.exe"
                
                    // Starts holding down the key.
                    if (convert[i] == 58) {
                        convert[i] = 59;
                
                    }
                    if (convert[i] == 59) {
                        robot.keyPress(KeyEvent.VK_SHIFT);
                
                    }
                    if (convert[i] == 34) {
                        convert[i] = 222;
                    }
                
                    robot.keyPress(convert[i]);
                
                    // Releases the key.
                    robot.keyRelease(convert[i]);
                    if (convert[i] == 59) {
                        robot.keyRelease(KeyEvent.VK_SHIFT);
                    }
                    // runas /user:Administrator "C:\Users\Jacob\Downloads\SteamSetup.exe"
                
                    // Releases the shift key if it was writing an uppercased letter
                
                }

                
                robot.keyPress(KeyEvent.VK_ENTER);
                robot.keyRelease(KeyEvent.VK_ENTER);
                
                char[] trypass = pass[k].toCharArray();
                for (int r = 0; r < 4; r++) {
                    robot.keyPress((int) trypass[r]);
                    robot.keyRelease((int) trypass[r]);
                    System.out.print(trypass[r]);
                }
                System.out.println("");
                
                robot.keyPress(KeyEvent.VK_ENTER);
                robot.keyRelease(KeyEvent.VK_ENTER);
                
            } catch (AWTException e) {
                e.printStackTrace();
            }
            
        }

    }
    */
    

    private static void possiblePlugboard() {
        int comb = 0;
        for (int i = 0; i < 26; i++) {
            
        }
    }

    private static void GenerateMoves(String piece) {
        String x = "";
        String typeof = " ";
        String saveString = piece;
        boolean otherpawn = false;
        pawn = false;
        if (piece.equals("")) {
            pawn = true;
        }

        for (int i = 0; i <= 7; i++) { // letters
            for (int j = 1; j <= 8; j++) { // numbers

                otherpawn = false;
                if ((j == 1 || j == 8) && !piece.equals(letters[i]) && pawn) {
                    SkipPromotion = true;
                } else {
                    SkipPromotion = false;
                }
                for (int k = 0; k < 2; k++) {
                    if (k == 0) {
                        x = "";

                    } else if (k == 1) {
                        x = "x";
                    }

                    // captures
                    for (int l = 0; l < 3; l++) { // type
                        for (int m = 0; m < 5; m++) {
                            if (!SkipPromotion) {
                                m = 4;
                            }
                            if (SkipPromotion && m == 4) {

                            } else {

                                if (x.equals("x") && pawn) {
                                    if (!(i == 0)) {
                                        System.out.print(
                                                letters[i - 1] + x + letters[i] + j + promotion[m] + marks[l]);
                                    }
                                    if (!(i == 7)) {
                                        System.out.print(
                                                letters[i + 1] + x + letters[i] + j + promotion[m] + marks[l]);
                                    }
                                } else {
                                    if (ambiguation) {
                                        if (validAmbiguation(letters[i], j, piece)) {
                                            System.out.print(piece + x + letters[i] + j + promotion[m] + marks[l]);
                                        }
                                    } else {
                                        System.out.print(piece + x + letters[i] + j + promotion[m] + marks[l]);
                                    }

                                }

                            }
                        }
                    }

                }
            }
        }

        if (!ambiguation) {
            if (saveString.equals("Q")) {
                ambiguation = true;
                for (int i = 0; i < 8; i++) {
                    GenerateMoves("Q" + letters[i]);
                    GenerateMoves("Q" + (i + 1));
                    for (int j = 1; j <= 8; j++) {
                        GenerateMoves("Q" + letters[i] + j);
                    }
                }
            }
        }

    }

    private static boolean validAmbiguation(String letter, int j, String piece) {
        String square = piece.substring(1);
        piece = piece.substring(0, 1);
        if (piece.equals("Q")) {
            if (square.length() == 1) {
                return true;
            } else {
                if (square.equals(letter + j + "")) {
                    return false;
                } else if (letter.equals(square.substring(0, 1))) {
                    return true;
                } else if ((j + "").equals(square.substring(1))) {
                    return true;
                } else {
                    validSquares(letter, j);

                }
            }
        } else {

        }
        return false;
    }

    private static void validSquares(String letter, int j) {

    }
}