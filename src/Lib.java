import java.util.Arrays;

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
            }
            if (originalInt > 96 && originalInt < 123) { // check if lowercase
                if (newInt > 122) { // if out of lowercase range
                    newInt -= 26;
                }
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
}
