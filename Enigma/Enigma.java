package Enigma;

import java.util.Arrays;
import java.util.Scanner;

public class Enigma {
    static boolean rotor = false;
    public static void main(String[] args) {
        Scanner e = new Scanner(System.in);

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
                    Substitute(code[1]);
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

    public static void Substitute(String code) {
        int rotorpos = 0;
        // EKMFLGDQVZNTOWYHXUSPAIBRCJ
        // AJDKSIRUXBLHWTMCQGZNPYFVOE
        // BDFHJLCPRTXVZNYEIWGAKMUSQO
        String[][] rotors = new String[3][26];
        rotors[0] = initializeRotor("EKMFLGDQVZNTOWYHXUSPAIBRCJ");
        rotors[1] = initializeRotor("AJDKSIRUXBLHWTMCQGZNPYFVOE");
        rotors[2] = initializeRotor("BDFHJLCPRTXVZNYEIWGAKMUSQO");
        String[] alphabet = initializeRotor("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        String[] split = code.split("");
        for (String s : split) { //example text "hello world"
            if (s.equals(" ")) {
                System.out.print(s); //space in hello world gets printed
            } else {
                int index = Arrays.binarySearch(alphabet, s); //starting at h
                //we search for the position of 'H' in the alphabet array
                // H appears in 7th position so index is 7
                if (rotor) {
                    //to make it through another rotor, 
                    
                    //rotor 0, pos 7 is Q, Q is 17
                    //rotor 1, pos 17 is Q, Q is 17
                    //rotor 2, pos 17 is I, I is 10
                    for (rotorpos = 0; rotorpos < 3; rotorpos++) {
                        index = Arrays.binarySearch(alphabet, rotors[rotorpos][index]);
                    }
                    
                }
                System.out.print(rotors[rotorpos][index]);
            }
        }
        System.out.println(" ");

    }

    public static String[] initializeRotor(String s) {
        String[] test = s.split("");
        return test;
    }
}
