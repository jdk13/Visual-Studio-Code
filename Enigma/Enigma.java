package Enigma;

import java.util.Scanner;

public class Enigma {
    static String[][] rotors = new String[5][26];
    static String[] alphabet;
    static boolean rotor = false;
    static int LeftShift;
    static int midshift;
    static int RightShift;
    static boolean quit;

    public static void main(String[] args) {
        // Crack("");
        Scanner e = new Scanner(System.in);
        rotors[0] = initializeRotor("EKMFLGDQVZNTOWYHXUSPAIBRCJ");
        rotors[1] = initializeRotor("AJDKSIRUXBLHWTMCQGZNPYFVOE");
        rotors[2] = initializeRotor("BDFHJLCPRTXVZNYEIWGAKMUSQO");
        rotors[3] = initializeRotor("ESOVPZJAYQUIRHXLNFTGKDCMWB");
        rotors[4] = initializeRotor("VZBRGITYUPSDNHLXAWMJQOFECK");
        alphabet = initializeRotor("ABCDEFGHIJKLMNOPQRSTUVWXYZ");

        while (!quit) {
            LeftShift = 0;
            midshift = 0;
            RightShift = 0;
            rotor = false;
            System.out.print("Enter: ");
            quit = false;
            String input = e.nextLine();
            String[] code = input.split(" ");
            if (code.length > 15) {
                whole(code, false, 0);
            } else if (code.length > 2) {
                for (int i = 2; i < code.length; i++) {
                    code[1] += " " + code[i];
                }
            }
            if (input.equalsIgnoreCase("Quit")) {
                quit = true;
            } // Caesar(code);
            else if (code[0].length() <= 4) {
                if (code[0].startsWith(">C") || code[0].startsWith("<C")) {
                    int shift = 1;
                    if (code[0].length() >= 3) {
                        try {
                            shift = Integer.parseInt(code[0].substring(2));
                        } catch (NumberFormatException ee) {

                        }
                    }
                    if (code[0].startsWith("<C")) {
                        shift = shift * -1;
                    }
                    Caesar(code[1], shift);

                } else if (code[0].startsWith(">A") || code[0].startsWith("<A")) {
                    if (code[0].equalsIgnoreCase("<A")) {

                        String[] split = initializeRotor(code[1]);
                        for (String letter : split) {
                            if (letter.equals(" ")) {
                                System.out.print(" ");
                            } else {
                                int index = Decrypt(letter, 0);
                                System.out.print(alphabet[index]);
                            }

                        }
                        System.out.println(" ");
                    } else {
                        String[] split = initializeRotor(code[1]);
                        for (String letter : split) {
                            if (letter.equals(" ")) {
                                System.out.print(" ");
                            } else {
                                int E = Encrypt(letter, 0);
                                System.out.print(rotors[0][E]);
                            }
                        }
                        System.out.println(" ");
                    }

                } else if (code[0].startsWith(">R") || code[0].startsWith("<R")) {
                    if (code[0].equalsIgnoreCase("<R")) {
                        String[] split = initializeRotor(code[1]);
                        for (String s : split) {
                            if (s.equals(" ")) {
                                System.out.print(" ");
                            } else {
                                String snip = s;
                                int index = 0;
                                for (int i = 2; i > -1; i--) {
                                    index = Decrypt(snip, i);
                                    snip = alphabet[index];
                                }

                                System.out.print(alphabet[index]);
                            }

                        }
                        System.out.println(" ");
                    } else {
                        String[] split = initializeRotor(code[1]);
                        for (String s : split) {
                            if (s.equals(" ")) {
                                System.out.print(" ");
                            } else {
                                String snip = s;
                                int index = 0;
                                for (int i = 0; i < 3; i++) {
                                    index = Encrypt(snip, i);
                                    snip = rotors[i][index];
                                }

                                System.out.print(rotors[2][index]);
                            }

                        }
                        System.out.println(" ");
                    }

                } else {
                    System.out.println("");
                }

            }

        }
        e.close();

    }

    public static String Caesar(String code, int shift) {
        String[] split = code.split("");
        for (String letter : split) {

            if (letter.equals(" ")) {
                System.out.print(" ");
            } else {
                char w = (char) (letter.charAt(0));
                int i = w;

                if (i > 64 && i < 91) {
                    w += shift;
                    i = w;
                    if (i >= 91) {
                        w -= 26;
                    }
                    if (i <= 64) {
                        w += 26;
                    }
                    return w + "";
                } else if (i > 96 && i < 123) {
                    w += shift;
                    if (i >= 123) {
                        w -= 26;
                    }
                    if (i <= 96) {
                        w += 26;
                    }
                    return w + "";
                }

            }

        }
        return code;

        // for (char alphabet = 'A'; alphabet <= 'Z'; alphabet++) {
        // System.out.println(alphabet);
        // }
    }

    public static String[] initializeRotor(String s) {
        String[] test = s.split("");
        return test;
    }

    public static int index(String arr[], String t, int start) {
        // this is just a method that finds indexes

        if (start == arr.length)
            return -1;

        if (arr[start].equalsIgnoreCase(t))
            return start;

        return index(arr, t, start + 1);
    }

    public static void whole(String[] code, boolean crack, int index2) {
        String[][] plugboard = new String[10][2];

        String[][] rotorsettings = rotorsettings(code);
        int[] notch = notches(code);

        // this sets up the order of rotors

        // this sets up the shifts

        for (int i = 3; i < 6; i++) {
            if (i == 3) {
                LeftShift = index(alphabet, code[i], 0);
            }
            if (i == 4) {
                midshift = index(alphabet, code[i], 0);
            }
            if (i == 5) {
                RightShift = index(alphabet, code[i], 0);
            }
        }

        for (int i = 6; i < 16; i++) {
            plugboard[i - 6][0] = code[i].substring(0, 1);
            plugboard[i - 6][1] = code[i].substring(1);
        }
        if (code.length > 16) {
            for (int i = 17; i < code.length; i++) {
                code[15] += code[i];
            }
        }
        boolean turn = false;
        boolean turnagain = false;

        String[] text = initializeRotor(code[16]);
        turn = false;

        for (int i = 0; i < text.length; i++) {
            turn = false;
            RightShift++;
            if (RightShift == notch[2]) {
                turn = true;
            }
            if (RightShift == 26) {
                RightShift = 0;
            }

            String fp = plugboard(plugboard, text[i]);
            translate(fp, RightShift, midshift, LeftShift, rotorsettings);
            fp = plugboard(plugboard, fp);
            if (crack) {
                if (i == index2) {
                    if (!fp.equals("C")) {
                        return;
                    }
                }
                if (i == index2) {
                    if (!fp.equals("O")) {
                        return;
                    }
                }
                if (i == index2) {
                    if (!fp.equals("M")) {
                        return;
                    }
                }
                if (i == index2) {
                    if (!fp.equals("P")) {
                        return;
                    }
                }
                if (i == index2) {
                    if (!fp.equals("U")) {
                        return;
                    }
                }
                if (i == index2) {
                    if (!fp.equals("T")) {
                        return;
                    }
                }
                if (i == index2) {
                    if (!fp.equals("E")) {
                        return;
                    }
                }
                if (i == index2) {
                    if (!fp.equals("R")) {
                        return;
                    }
                }

            }
            System.out.print(fp);
            if (crack) {
                for (String s : code)
                    System.out.print(" " + s);
            }
            // double steppings
            if (turnagain) {
                midshift++;
                LeftShift++;
            }

            turnagain = false;
            // this part deals with shiftings after every key press
            if (turn) {
                midshift++;
                if (midshift == notch[1]) {
                    turnagain = true;
                }
            }

        }

        /*
         * 1. Go Plugboard
         * 2. Shift up Normal Alphabet
         * 3. translate
         * 4. Shift back down Normal Alphabet
         * 5. Repeat 2-4 2 more times based off of rotor order
         * 6. Reflects to new letter
         * 7. Send it back in reverse (3,1,2 becomes 2,1,3)
         * 8. Plugboard again
         */
        // TODO create the entire enigma method

        // Rotations
        // Right rotor rotates with every letter
        // Middle rotates with every rotation the right rotator takes
        // Left rotates with eevry rotation the middle rotor takes
        // TODO construct rotations
    }

    public static int Encrypt(String letter, int Rotor) {

        int index = index(alphabet, letter, 0);

        return index;

    }

    public static int Decrypt(String letter, int Rotor) {

        // int index = index(alphabet, letter, 0);

        int index = index(rotors[Rotor], letter, 0);

        return index;

    }

    public static void Crack(String s) {
        // KJKRVCZFIQDUDHSBCGIKVJQEMBUXWWBGOBEKMQIXFODK
        // COMPUTER
        String encoded = "KJKRVCZFIQDUDHSBCGIKVJQEMBUXWWBGOBEKMQIXFODK";
        String[] encipher = initializeRotor(encoded);
        String[] key = initializeRotor("COMPUTER");
        int[] indexes = new int[] { 0, 1, 2, 3, 4, 6, 8, 9, 10, 11, 12, 13, 14, 15, 18, 19, 20, 21, 23, 24, 25, 26, 27,
                29, 30, 32, 33, 35 };
        String[] testcode = new String[17];
        testcode[16] = encoded;
        for (int ii : indexes) {
            for (int y = 0; y < 5; y++) {
                for (int z = 0; z < 5; z++) {
                    for (int a = 0; a < 5; a++) {
                        if (!(y == z) && !(z == a) && !(a == y)) {
                            testcode[0] = Roman(y);
                            testcode[1] = Roman(z);
                            testcode[2] = Roman(a);
                            for (int i = 0; i < 26; i++) {
                                String[][] rotorsettings = rotorsettings(testcode);
                                testcode[3] = alphabet[i];
                                for (int j = 0; j < 26; j++) {
                                    testcode[4] = alphabet[j];
                                    for (int k = 0; k < 26; k++) {
                                        testcode[5] = alphabet[k];
                                        // Assume First letter goes to something
                                        // plug that in and get other letter
                                        // KJKRVCZFIQDUDHSBCGIKVJQEMBUXWWBGOBEKMQIXFODK
                                        // COMPUTER
                                        boolean incorrect = true;

                                        // String[][] pairs = new String[10][2];
                                        // looking at encipher[indexes] and key[0]
                                        for (int v = 0; v < 26; v++) {
                                            for (int vv = 0; vv < 26; vv++) {
                                                String pair1 = alphabet[v] + alphabet[vv];
                                                if (v < vv || v == vv) {
                                                    continue;
                                                }
                                                for (int vvv = 0; vvv < 26; v++) {
                                                    for (int vvvv = 0; vvvv < 26; vvvv++) {
                                                        for (int vvvvv = 0; vvvvv < 26; vvvvv++) {
                                                            for (int vvvvvv = 0; vvvvvv < 26; vvvvvv++) {
                                                                for (int vvvvvvv = 0; vvvvvv < 26; vvvvvvv++) {
                                                                    for (int vvvvvvvv = 0; vvvvvv < 26; vvvvvvvv++) {

                                                                    }

                                                                }
                                                            }

                                                        }
                                                    }

                                                }
                                            }

                                        }

                                        /*
                                         * for (int h = 0; h < 25; h++) {
                                         * // TRY K goes to A, B, C, D... Y, Z;
                                         * // build off of that
                                         * pairs[0][0] = encipher[ii];
                                         * if (!(h == index(alphabet, encipher[ii], 0))) {
                                         * pairs[0][1] = alphabet[h];
                                         * String output = translate(pairs[0][1], k, j, i, rotorsettings);
                                         * pairs[1][0] = output;
                                         * pairs[1][1] = key[0];
                                         * for (int t = 0; t < 10; t++) {
                                         * for (int v = 0; v < 2; v++) {
                                         * 
                                         * }
                                         * }
                                         * }
                                         * 
                                         * }
                                         */

                                    }
                                }
                            }
                        }

                    }

                }
            }
        }

        // there are 10 pairs and 6 empty
        // KJKRVCZF
        // COMPUTER
        // C goes to K
        // assume C goes to A
        // A gets sent through 3 and
        // loop through possible COMPUTER locations
        // the loop through possible rotor combinations (there's about 60 of them)
        // loop through shifts
        // loop through plugboards

    }

    private static String[] configurePlug(String[] test) {

        return test;

    }

    public static String plugboard(String[][] plugboard, String a) {
        for (String[] first : plugboard) {
            if (a.equalsIgnoreCase(first[0])) {
                return first[1];
            } else if (a.equalsIgnoreCase(first[1])) {
                return first[0];
            }
        }
        return a;
    }

    public static String[][] rotorsettings(String[] code) {
        String[][] rotorsettings = new String[3][26];
        for (int i = 0; i < 3; i++) {
            if (code[i].equalsIgnoreCase("I")) {
                rotorsettings[i] = rotors[0];
            }
            if (code[i].equalsIgnoreCase("II")) {
                rotorsettings[i] = rotors[1];
            }
            if (code[i].equalsIgnoreCase("III")) {
                rotorsettings[i] = rotors[2];
            }
            if (code[i].equalsIgnoreCase("IV")) {
                rotorsettings[i] = rotors[3];
            }
            if (code[i].equalsIgnoreCase("V")) {
                rotorsettings[i] = rotors[4];
            }

        }
        return rotorsettings;
    }

    public static int[] notches(String[] code) {
        int[] notch = new int[3];
        for (int i = 0; i < 3; i++) {
            if (code[i].equalsIgnoreCase("I")) {
                notch[i] = 16;
            }
            if (code[i].equalsIgnoreCase("II")) {
                notch[i] = 4;
            }
            if (code[i].equalsIgnoreCase("III")) {
                notch[i] = 21;
            }
            if (code[i].equalsIgnoreCase("IV")) {
                notch[i] = 9;
            }
            if (code[i].equalsIgnoreCase("V")) {
                notch[i] = 25;
            }
        }
        return notch;
    }

    public static String Roman(int i) {
        if (i == 0) {
            return "I";
        }
        if (i == 1) {
            return "II";
        }
        if (i == 2) {
            return "III";
        }
        if (i == 3) {
            return "IV";
        }
        if (i == 4) {
            return "V";
        }
        return null;
    }

    public static String translate(String fp, int RightShift, int midshift, int LeftShift, String[][] rotorsettings) {
        String[] reflect = initializeRotor("YRUHQSLDPXNGOKMIEBFZCWVJAT");
        fp = Caesar(fp, RightShift);
        int index = index(alphabet, fp, 0);
        fp = rotorsettings[2][index];
        fp = Caesar(fp, RightShift * -1);
        fp = Caesar(fp, midshift);
        index = index(alphabet, fp, 0);
        fp = rotorsettings[1][index];
        fp = Caesar(fp, midshift * -1);
        fp = Caesar(fp, LeftShift);
        index = index(alphabet, fp, 0);
        fp = rotorsettings[0][index];
        fp = Caesar(fp, LeftShift * -1);
        index = index(alphabet, fp, 0);
        fp = reflect[index];
        fp = Caesar(fp, LeftShift);
        index = index(rotorsettings[0], fp, 0);
        fp = alphabet[index];
        fp = Caesar(fp, LeftShift * -1);
        fp = Caesar(fp, midshift);
        index = index(rotorsettings[1], fp, 0);
        fp = alphabet[index];
        fp = Caesar(fp, midshift * -1);
        fp = Caesar(fp, RightShift);
        index = index(rotorsettings[2], fp, 0);
        fp = alphabet[index];
        return fp = Caesar(fp, RightShift * -1);
    }

}
