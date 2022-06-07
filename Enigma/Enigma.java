package Enigma;

import java.util.Arrays;
import java.util.Scanner;

public class Enigma {
    static boolean rotor = false;

    public static void main(String[] args) {
        Scanner e = new Scanner(System.in);
        String[][] rotors = new String[5][26];
        rotors[0] = initializeRotor("EKMFLGDQVZNTOWYHXUSPAIBRCJ");
        rotors[1] = initializeRotor("AJDKSIRUXBLHWTMCQGZNPYFVOE");
        rotors[2] = initializeRotor("BDFHJLCPRTXVZNYEIWGAKMUSQO");
        rotors[3] = initializeRotor("ESOVPZJAYQUIRHXLNFTGKDCMWB");
        rotors[4] = initializeRotor("VZBRGITYUPSDNHLXAWMJQOFECK");
        String[] alphabet = initializeRotor("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
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
                                int index = 0;
                                for (int i = 2; i > -1; i--) {
                                    index = Decrypt(s, i);
                                }
                                System.out.print(alphabet[index]);
                            }

                        }
                        System.out.println(" ");
                    } else {
                        rotor = true;
                        Substitute(code[1], false);
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

    public static void Substitute(String code, boolean b) {
        int rotorpos = 0;
        String[][] rotors = new String[5][26];
        rotors[0] = initializeRotor("EKMFLGDQVZNTOWYHXUSPAIBRCJ");
        rotors[1] = initializeRotor("AJDKSIRUXBLHWTMCQGZNPYFVOE");
        rotors[2] = initializeRotor("BDFHJLCPRTXVZNYEIWGAKMUSQO");
        rotors[3] = initializeRotor("ESOVPZJAYQUIRHXLNFTGKDCMWB");
        rotors[4] = initializeRotor("VZBRGITYUPSDNHLXAWMJQOFECK");
        String[] alphabet = initializeRotor("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        // TODO Add Reflector
        // TODO Add Plugboard

        String[] split = code.split("");
        for (String s : split) { // example text "hello world"
            if (s.equals(" ")) {
                System.out.print(s); // space in hello world gets printed
            } else {
                int index = Arrays.binarySearch(alphabet, s.toUpperCase()); // starting at h
                // we search for the position of 'H' in the alphabet array
                // H appears in 7th position so index is 7
                if (rotor && !b) {
                    // to make it through another rotor,

                    // rotor 0, pos 7 is Q, Q is 17
                    // rotor 1, pos 17 is Q, Q is 17
                    // rotor 2, pos 17 is I, I is 10
                    for (rotorpos = 0; rotorpos < 3; rotorpos++) {
                        index = index(alphabet, rotors[rotorpos][index], 0);
                    }
                    rotorpos--;
                    System.out.print(rotors[rotorpos][index]);
                } else if (rotor && b) {
                    index = index(rotors[2], s, 0);
                    for (rotorpos = 1; rotorpos > 0; rotorpos--) {
                        // G to S is 18
                        // S to E is 4
                        // E to A is 0
                        index = index(rotors[rotorpos], alphabet[index], 0);
                    }
                    index = index(rotors[0], alphabet[index], 0);
                    rotorpos++;
                    System.out.print(alphabet[index]);
                }

            }
        }
        for (String s : split) {
            if (s.equals(" ")) {
                System.out.print(s); // space in hello world gets printed
            } else {
                if (rotor && !b) {

                }
            }
        }
        System.out.println(" ");

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

    public static void whole(String e) {
        String[] reflect = initializeRotor("YRUHQSLDPXNGOKMIEBFZCWVJAT");
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
        String[][] rotors = new String[5][26];
        rotors[0] = initializeRotor("EKMFLGDQVZNTOWYHXUSPAIBRCJ");
        rotors[1] = initializeRotor("AJDKSIRUXBLHWTMCQGZNPYFVOE");
        rotors[2] = initializeRotor("BDFHJLCPRTXVZNYEIWGAKMUSQO");
        rotors[3] = initializeRotor("ESOVPZJAYQUIRHXLNFTGKDCMWB");
        rotors[4] = initializeRotor("VZBRGITYUPSDNHLXAWMJQOFECK");
        String[] alphabet = initializeRotor("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        int index = Arrays.binarySearch(alphabet, letter.toUpperCase());

        return index;

    }

    public static int Decrypt(String letter, int Rotor) {
        String[][] rotors = new String[5][26];
        rotors[0] = initializeRotor("EKMFLGDQVZNTOWYHXUSPAIBRCJ");
        rotors[1] = initializeRotor("AJDKSIRUXBLHWTMCQGZNPYFVOE");
        rotors[2] = initializeRotor("BDFHJLCPRTXVZNYEIWGAKMUSQO");
        rotors[3] = initializeRotor("ESOVPZJAYQUIRHXLNFTGKDCMWB");
        rotors[4] = initializeRotor("VZBRGITYUPSDNHLXAWMJQOFECK");

        int index = index(rotors[Rotor], letter, 0);

        return index;

    }

}
