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
        Crack("");
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
                whole(code);
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

    public static void Caesar(String code, int shift) {
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
                    System.out.print(w);
                } else if (i > 96 && i < 123) {
                    w += shift;
                    if (i >= 123) {
                        w -= 26;
                    }
                    if (i <= 96) {
                        w += 26;
                    }
                    System.out.print(w);
                }

            }

        }
        System.out.print("\n");

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

    public static void whole(String[] code) {
        String[] reflect = initializeRotor("YRUHQSLDPXNGOKMIEBFZCWVJAT");
        String[][] rotorsettings = new String[3][26];
        // this sets up the order of rotors
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
                rotorsettings[i] = rotors[0];
            }

        }
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
        int[] indexes = new int[] { 0, 1, 2, 3, 4, 6, 8, 9, 10, 11, 12, 13, 14, 15, 18, 19, 20, 21, 23, 24, 25, 26, 27,
                29, 30, 32, 33, 35 };
        String[] key = initializeRotor("COMPUTER");
        int test = 0;
        int[][][] rotorpos = new int[5][12][3];
        String[] code = initializeRotor("KJKRVCZFIQDUDHSBCGIKVJQEMBUXWWBGOBEKMQIXFODK");
        int mid = 0;
        for (int y = 0; y < 5; y++) {
            mid = 0;
            for (int z = 0; z < 5; z++) {
                for (int a = 0; a < 5; a++) {
                    if (!(y == z) && !(z == a) && !(a == y)) {
                        rotorpos[y][mid][0] = y;
                        rotorpos[y][mid][1] = z;
                        rotorpos[y][mid][2] = a;

                        mid++;
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
        //loop through possible COMPUTER locations
        // the loop through possible rotor combinations (there's about 60 of them)
        //loop through shifts
        // loop through plugboards

        for (int i : indexes) {
            for (int[][] rot : rotorpos) {
                for (int[] rot2 : rot) {
                    String[][] rotorsettings = new String[3][26];

                    // here we initialize rotor settings
                    rotorsettings[0] = rotors[rot2[0]];
                    rotorsettings[1] = rotors[rot2[1]];
                    rotorsettings[2] = rotors[rot2[2]];
                    for (int k = 0; k < 25; k++) {
                        for (int l = 0; k < 25; k++) {
                            for (int m = 0; k < 25; k++) {
                                for (int n = 0; n < 26; n++) {
                                    
                                }
                            }

                        }
                    }
                    
                }
            }
        }

    }

}
