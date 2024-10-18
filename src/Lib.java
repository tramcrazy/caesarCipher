import java.util.Scanner;

public class Lib {
    public int charToInt(char character) {
        return character;
    }

    public char intToChar(int integer) {
        return (char) integer;
    }

    public int[] stringAsIntArray(String name) {
        String[] stringArray = name.split("");
        int[] intArray = new int[stringArray.length];
        for (int i = 0; i < stringArray.length; i++) {
            intArray[i] = charToInt(stringArray[i].charAt(0));
        }
        return intArray;
    }

    public String intArrayAsString(int[] intArray) {
        String[] stringArray = new String[intArray.length];
        for (int i = 0; i < intArray.length; i++) {
            stringArray[i] = String.valueOf(intToChar(intArray[i]));
        }
        return String.join("", stringArray);
    }

    public String encryptDecryptString(String plaintext, int key, boolean decrypt) {
        int[] intArray = stringAsIntArray(plaintext);
        int originalInt;
        int newInt;
        if (decrypt) {
            key = 26 - key;
        }
        for (int i = 0; i < intArray.length; i++) {
            originalInt = intArray[i];
            newInt = intArray[i] + key;
            if (originalInt > 64 && originalInt < 91) { // check if uppercase
                if (newInt > 90) { // if out of uppercase range
                    newInt -= 26;
                }
            } else if (originalInt > 96 && originalInt < 123) { // check if lowercase
                if (newInt > 122) { // if out of lowercase range
                    newInt -= 26;
                }
            } else {
                newInt = originalInt;
            }
            intArray[i] = newInt;
        }
        return intArrayAsString(intArray);
    }

    public void bruteForceString(String ciphertext, String crib) {
        String testString;
        for (int i = 1; i < 26; i++) {
            testString = encryptDecryptString(ciphertext, i, true);
            if (testString.contains(crib)) {
                System.out.println("POSSIBLE PLAINTEXT!");
                System.out.println("Plaintext: " + testString);
                System.out.println("Key: " + i);
            }
        }
    }

    public int[][] analyseFrequencies(String ciphertext) {
        ciphertext = ciphertext.toUpperCase();
        int[][] frequencies = new int[26][2];
        int currentCode;
        for (int i = 0; i < frequencies.length; i++) {
            frequencies[i][0] = i + 65;
            frequencies[i][1] = 0;
        }
        for (int i = 0; i < ciphertext.length(); i++) {
            if (Character.isLetter(ciphertext.charAt(i))) {
                currentCode = ciphertext.codePointAt(i);
                frequencies[currentCode - 65][1] += 1;
            }
        }
        return frequencies;
    }

    public void menu() {
        Scanner menuScan = new Scanner(System.in);
        Scanner intScan = new Scanner(System.in);
        String userPlaintext;
        String userCiphertext;
        String userCrib;
        int userKey;
        String selectedOption;
        char selectedOptionChar = 0;
        boolean running = true;
        int[][] frequencyAnalysis;
        boolean selectionNotMade;
        while (running) {
            System.out.print("""
                    ---------------------------------
                    Menu:
                    (A)nalyse frequencies
                    (E)ncrypt plaintext
                    (D)ecrypt ciphertext
                    (B)ruteforce ciphertext with crib
                    (Q)uit
                    ---------------------------------
                    """);
            selectionNotMade = true;
            while (selectionNotMade) {
                System.out.print("Enter A/E/D/B/Q >>> ");
                selectedOption = menuScan.nextLine();
                if (!selectedOption.isEmpty()) {
                    selectedOptionChar = selectedOption.charAt(0);
                    selectionNotMade = false;
                }
            }
            switch (selectedOptionChar) {
                case 'A':
                    System.out.print("Enter ciphertext >>> ");
                    userCiphertext = menuScan.nextLine();
                    frequencyAnalysis = analyseFrequencies(userCiphertext);
                    System.out.println("Letter: Frequency");
                    for (int[] row : frequencyAnalysis) {
                        System.out.println(intToChar(row[0]) + ": " + row[1]);
                    }
                    break;
                case 'E':
                    System.out.print("Enter plaintext >>> ");
                    userPlaintext = menuScan.nextLine();
                    System.out.print("Enter encryption key >>> ");
                    userKey = intScan.nextInt();
                    userCiphertext = encryptDecryptString(userPlaintext, userKey, false);
                    System.out.println("Ciphertext:");
                    System.out.println(userCiphertext);
                    break;
                case 'D':
                    System.out.print("Enter ciphertext >>> ");
                    userCiphertext = menuScan.nextLine();
                    System.out.print("Enter encryption key >>> ");
                    userKey = intScan.nextInt();
                    userPlaintext = encryptDecryptString(userCiphertext, userKey, true);
                    System.out.println("Plaintext:");
                    System.out.println(userPlaintext);
                    break;
                case 'B':
                    System.out.print("Enter ciphertext >>> ");
                    userCiphertext = menuScan.nextLine();
                    System.out.print("Enter crib >>> ");
                    userCrib = menuScan.nextLine();
                    bruteForceString(userCiphertext, userCrib);
                    break;
                case 'Q':
                    running = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("Goodbye!");
    }
}