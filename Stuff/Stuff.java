package Stuff;

import java.util.ArrayList;

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
        // GenerateMoves("");
        // GenerateMoves("B");
        // GenerateMoves("R");
        GenerateMoves("Q");
        // GenerateMoves("N");
        // GenerateMoves("K");

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