package Enigma;

import java.util.Arrays;
import java.util.Scanner;

public class Enigma {
    static String[][] rotors = new String[5][26];
    static String[] alphabet;
    static boolean rotor = false;

    public static void main(String[] args) {
        Scanner e = new Scanner(System.in);
        rotors[0] = initializeRotor("EKMFLGDQVZNTOWYHXUSPAIBRCJ");
        rotors[1] = initializeRotor("AJDKSIRUXBLHWTMCQGZNPYFVOE");
        rotors[2] = initializeRotor("BDFHJLCPRTXVZNYEIWGAKMUSQO");
        rotors[3] = initializeRotor("ESOVPZJAYQUIRHXLNFTGKDCMWB");
        rotors[4] = initializeRotor("VZBRGITYUPSDNHLXAWMJQOFECK");
        alphabet = initializeRotor("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        int LeftShift = 0;
        int midshift = 0;
        int RightShift = 0;
        boolean quit = false;
        while (!quit) {
            rotor = false;
            System.out.print("Enter: ");
            quit = false;
            String input = e.nextLine();
            String[] code = input.split(" ");
            if(code.length > 15) {
            	whole(code);
            }
            else          
            if (code.length > 2) {
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
        for(int i = 0; i < 3; i++) {
        	
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
        
        //int index = index(alphabet, letter, 0);

        int index = index(rotors[Rotor], letter, 0);

        return index;

    }

}
