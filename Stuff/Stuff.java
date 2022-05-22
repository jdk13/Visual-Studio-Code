package Stuff;

public class Stuff {
    static boolean ambiguation = false;
    static boolean repeat = false;
    static boolean pawn = false;
    static String[] letters = new String[] { "a", "b", "c", "d", "e", "f", "g", "h" };
    static boolean SkipPromotion = false;
    static boolean onecapture = true;

    public static void main(String args[]) {
        // GenerateMoves("");
        GenerateMoves("B");
        // GenerateMoves("R");
        // GenerateMoves("Q");
        // GenerateMoves("N");
        // GenerateMoves("K");

    }

    private static void GenerateMoves(String string) {
        String saveString = string;
        pawn = false;
        if(string.equals("") || string.equals("x")){
            pawn = true;
        }
        
        for (int i = 0; i <= 7; i++) {
            for (int j = 1; j <= 8; j++) {
                SkipPromotion = false;
                if (pawn) {
                    if (j == 1 || j == 8) {
                        Promotion(i, j, string);
                        SkipPromotion = true;
                    }
                }
                if (!SkipPromotion) {
                    System.out.print(string + letters[i] + j + " ");
                    System.out.print(string + letters[i] + j + "+ ");
                    System.out.print(string + letters[i] + j + "# ");
                }
                if (string.equals("x")) {
                    if (!(i == 0)) {
                        System.out.print(letters[i - 1] + string + letters[i] + j + " ");
                        System.out.print(letters[i - 1] + string + letters[i] + j + "+ ");
                        System.out.print(letters[i - 1] + string + letters[i] + j + "# ");
                    }
                    if (!(i == 7)) {
                        System.out.print(letters[i - 1] + string + letters[i] + j + " ");
                        System.out.print(letters[i - 1] + string + letters[i] + j + "+ ");
                        System.out.print(letters[i - 1] + string + letters[i] + j + "# ");
                    }
                }
            }
        }

    if(!repeat)

    {
        repeat = true;
        GenerateMoves(string + "x");
        repeat = false;
    }if(!ambiguation)
    {
        // run it back
    }

    }

    private static void Promotion(int i, int j, String string) {
        String[] promoting = new String[]{"B", "N", "Q", "R"};
        for(int l = 0; l < 4; l++){
            System.out.print("");
        }
    }
}