package Enigma;

import java.util.ArrayList;
// Jacob Kim
// AP Computer Science
// Period 6
// Enigma
import java.util.Scanner;

public class Enigma {
    static String[][] rotors = new String[5][26];
    static String[] alphabet;
    static boolean rotor = false;
    static boolean turn = false;
    static boolean turnagain = false;
    static int[] shift = new int[3];
    static boolean quit;
    static int restart = 0;
    static boolean print = true;

    public static void main(String[] args) {

        Scanner e = new Scanner(System.in);
        rotors[0] = initializeRotor("EKMFLGDQVZNTOWYHXUSPAIBRCJ");
        rotors[1] = initializeRotor("AJDKSIRUXBLHWTMCQGZNPYFVOE");
        rotors[2] = initializeRotor("BDFHJLCPRTXVZNYEIWGAKMUSQO");
        rotors[3] = initializeRotor("ESOVPZJAYQUIRHXLNFTGKDCMWB");
        rotors[4] = initializeRotor("VZBRGITYUPSDNHLXAWMJQOFECK");
        alphabet = initializeRotor("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        Crack("");

        while (!quit) {
            shift[0] = 0;
            shift[1] = 0;
            shift[2] = 0;
            rotor = false;
            System.out.print("Enter: ");
            quit = false;
            String inpu = e.nextLine();
            String[] code = inpu.split(" ");
            if (code.length > 15) {
                whole(code, false, 0);
            } else if (code.length > 2) {
                for (int i = 2; i < code.length; i++) {
                    code[1] += " " + code[i];
                }
            }
            if (inpu.equalsIgnoreCase("Quit")) {
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
                shift[0] = index(alphabet, code[i], 0);
            }
            if (i == 4) {
                shift[1] = index(alphabet, code[i], 0);
            }
            if (i == 5) {
                shift[2] = index(alphabet, code[i], 0);
            }
        }

        for (int i = 6; i < 16; i++) {
            try {
                plugboard[i - 6][0] = code[i].substring(0, 1);
                plugboard[i - 6][1] = code[i].substring(1);
            } catch (Exception e) {
                break;
            }
            
        }
        if (code.length > 16) {
            for (int i = 17; i < code.length; i++) {
                code[15] += code[i];
            }
        }

        String[] text = initializeRotor(code[16]);
        turn = false;
        String message = "";

        for (int i = 0; i < text.length; i++) {
            turn = false;
            shift[2]++;
            if (shift[2] == notch[2]) {
                turn = true;
            }
            if (shift[2] == 26) {
                shift[2] = 0;
            }

            String fp = plugboard(plugboard, text[i]);
            fp = translate(fp, shift, rotorsettings);
            fp = plugboard(plugboard, fp);
            if (i == index2) {
                if (!(fp.equals("C"))) {
                    print = true;
                    break;
                } else {

                    print = false;
                }
            }
            message += fp;

            if (crack) {
                for (String s : code)
                    System.out.print(" " + s);
            }
            // double steppings
            if (turnagain) {
                shift[1]++;
                shift[0]++;
                if (shift[1] == 26) {
                    shift[1] = 0;
                } else if (shift[0] == 26) {
                    shift[0] = 0;
                }
            }

            turnagain = false;
            // this part deals with shiftings after every key press
            if (turn) {
                shift[1]++;
                if (shift[1] == notch[1]) {
                    turnagain = true;
                }
                if (shift[1] == 26) {
                    shift[1] = 0;
                }
            }

        }
        if (message.contains("COMPUTER")) {
            System.out.print(message);
        }
        else {
            print = true;
            
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

    public static String translate(String fp, int[] shift, String[][] rotorsettings) {
        String[] reflect = initializeRotor("YRUHQSLDPXNGOKMIEBFZCWVJAT");
        fp = Caesar(fp, shift[2]);
        int index = index(alphabet, fp, 0);
        fp = rotorsettings[2][index];
        fp = Caesar(fp, shift[2] * -1);
        fp = Caesar(fp, shift[1]);
        index = index(alphabet, fp, 0);
        fp = rotorsettings[1][index];
        fp = Caesar(fp, shift[1] * -1);
        fp = Caesar(fp, shift[0]);
        index = index(alphabet, fp, 0);
        fp = rotorsettings[0][index];
        fp = Caesar(fp, shift[0] * -1);
        index = index(alphabet, fp, 0);
        fp = reflect[index];
        fp = Caesar(fp, shift[0]);
        index = index(rotorsettings[0], fp, 0);
        fp = alphabet[index];
        fp = Caesar(fp, shift[0] * -1);
        fp = Caesar(fp, shift[1]);
        index = index(rotorsettings[1], fp, 0);
        fp = alphabet[index];
        fp = Caesar(fp, shift[1] * -1);
        fp = Caesar(fp, shift[2]);
        index = index(rotorsettings[2], fp, 0);
        fp = alphabet[index];
        return fp = Caesar(fp, shift[2] * -1);
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
                            String[][] rotorsettings = rotorsettings(testcode);
                            int[] notches = notches(testcode);
                            for (int i = 0; i < 26; i++) {

                                testcode[3] = alphabet[i];
                                shift[0] = i;
                                for (int j = 0; j < 26; j++) {
                                    testcode[4] = alphabet[j];
                                    shift[1] = j;
                                    for (int k = 0; k < 26; k++) {
                                        testcode[5] = alphabet[k];
                                        shift[2] = k;
                                        // Assume First letter goes to something
                                        // plug that in and get other letter
                                        // KJKRVCZFIQDUDHSBCGIKVJQEMBUXWWBGOBEKMQIXFODK
                                        // COMPUTER
                                        ArrayList<String> remains = new ArrayList<>();
                                        for (int b = 0; b < 26; b++) {
                                            remains.add(alphabet[b]);
                                        }
                                        for (int yy = 0; yy < 26; yy++) {
                                            print = true;
                                            
                                            String[][] pairs = new String[10][2];
                                            boolean incorrect = true;
                                            pairs = configurePlug(pairs, initializeRotor(encoded.substring(ii, ii + 8)),
                                                    remains, 0, rotorsettings, notches, false, "", 0, yy);
                                            
                                            // <ArrayList>
                                            if (!(pairs[9][1] == null)) {
                                                whole(testcode, false, ii);
                                                if (!print) {
                                                    
                                                    System.out.print(" ");
                                                    System.out.print(ii + " " + testcode[0] + " " + testcode[1] + " "
                                                            + testcode[2] + " " + testcode[3] + " " + testcode[4] + " "
                                                            + testcode[5] + " ");
                                                    for (int pp = 0; pp < pairs.length; pp++) {
                                                        String temp = "";
                                                        for (int qq = 0; qq < 2; qq++) {
                                                            if (pairs[pp][qq] != null) {
                                                                System.out.print(pairs[pp][qq]);
                                                                temp += pairs[pp][qq];
                                                            }

                                                        }
                                                        testcode[6 + pp] = temp;
                                                        System.out.print(" ");
                                                    }
                                                    System.out.println(" ");
                                                }
                                                
                                                
                                                
                                            }
                                        }
                                        
                                        

                                        
                                        // configureplug(pairs, remain);

                                        // String[][] pairs = new String[10][2];
                                        // looking at encipher[indexes] and key[0]

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
    // FIRST ITERATION
    // K ---> ?
    // loop A - Z and null
    // translate letter
    // output = C ----> ?
    // if any are

    private static String[][] configurePlug(String[][] plug, String[] code, ArrayList<String> remains, int index,
            String[][] rotorsettings, int[] notch, boolean output, String outputString, int arrindex, int bump) {
        restart = -2;
        // this index covers for arrays, and another index will call this method to
        // determine the parts of the code it will use
        if (index == 8) {
            return plug;
        }
        String[] key = initializeRotor("COMPUTER");
        int codeindex = index(alphabet, code[index], 0); // 10
        int keyindex = index(alphabet, key[index], 0); // 2
        if (output) {

            try {
                plug[arrindex][0] = remains.remove(remains.indexOf(outputString));
                plug[arrindex][1] = remains.remove(remains.indexOf(key[index]));
            } catch (Exception e) {
                restart = index;
                return plug;
            }
            if (turnagain) {
                shift[1]++;
                shift[0]++;
                if (shift[1] == 26) {
                    shift[1] = 0;
                } else if (shift[0] == 26) {
                    shift[0] = 0;
                }
            }
            turnagain = false;
            if (turn) {
                shift[1]++;
                if (shift[1] == notch[1]) {
                    turnagain = true;
                }
                if (shift[1] == 26) {
                    shift[1] = 0;
                }
            }
            arrindex++;
            index++;
            String[][] testplug =  configurePlug(plug, code, remains, index, rotorsettings, notch, false, outputString, arrindex, 0);
            if (checkPlug(plug)) {
                return testplug;
            }

        } else {
            // Shift
            // for loop
            //
            turn = false;
            shift[2]++;
            if (shift[2] == notch[2]) {
                turn = true;
            }
            if (shift[2] == 26) {
                shift[2] = 0;
            }
            int realsize = remains.size(); // K goes into plug[][] size = 26
            for (int i = 0; i < realsize + 1; i++) {
                if (i == 0 && bump != 0) {
                    i += bump;
                }
                if (!(i == realsize)) {
                    // K
                    // C
                    if (!(remains.get(i).equals(code[index]) || remains.get(i).equals(key[index]))) {
                        String input = plugboard(plug, code[index]);
                        if (input.equals(code[index])) { // if not plugboard existing
                            input = remains.get(i);
                            String translation = translate(input, shift, rotorsettings); // assumed letter
                            if (checkPlug(plug, translation) == -1) {
                                ArrayList<String> remains2 = new ArrayList<>(remains);
                                try {
                                    plug[arrindex][0] = remains2.remove(remains2.indexOf(code[index]));
                                    plug[arrindex][1] = remains2.remove(i);
                                } catch (Exception e) {
                                    restart = index;
                                    return plug;
                                }
                                arrindex++;
                                String[][] testplug = configurePlug(plug, code, remains2, index, rotorsettings, notch,
                                        true, translation, arrindex, 0);
                                if (checkPlug(plug)) {
                                    continue;
                                } else {
                                    return testplug;
                                }

                                // plug = configurePlug(plug, code, remains2, index, rotorsettings, notch);
                            } else {
                                continue;

                            }

                        } else {
                            input = translate(input, shift, rotorsettings);
                            if (checkPlug(plug, input) == -1) {
                                return configurePlug(plug, code, remains, index, rotorsettings, notch, true,
                                        input, arrindex, bump);

                            }
                        }
                    }
                }
                else {
                    String[][] testplug = configurePlug(plug, code, remains, index, rotorsettings, notch, true,
                            "", arrindex, bump);
                    return testplug;
                }

            }
            
        }

        // code - KJKRVCZF
        // key - COMPUTER
        // K ---> ?
        // ? ---> C
        //
        // J ---> ?
        // ? ---> O
        //
        // K ---> ?
        // ? ---> M
        //
        // R ---> ? ---- ? ---> P
        // V ---> ? ---- ? ---> U
        //
        // K and C
        // (K, ?)

        return plug;

    }

    public static String plugboard(String[][] plugboard, String a) {
        
        for (String[] first : plugboard) {
            if (a.equalsIgnoreCase(first[0])) {
                if (first[1] == "") {
                    return a;
                }
                return first[1];
            } else if (a.equalsIgnoreCase(first[1])) {
                if (first[0] == "") {
                    return a;
                }
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

    public static int checkPlug(String[][] plug, String input) {
        for (int i = 0; i < plug.length - 1; i++) {
            for (int j2 = 0; j2 < 2; j2++) {
                if (!(plug[i][j2] == null)) {
                    if (plug[i][j2].equals(input)) {
                        return i;
                    }
                }

            }
        }
        return -1;
    }
    
    public static boolean checkPlug(String[][] plug) {
        for (int i = 0; i < plug.length - 1; i++) {
            for (int j = i+1; j < plug.length; j++) {
                for (int j2 = 0; j2 < 2; j2++) {
                    if (!(plug[i][j2] == null)) {
                        if (plug[i][j2].equals(plug[j][j2])) {
                            return true;
                        }
                    }

                }
            }
        }
        return false;
    }

}
