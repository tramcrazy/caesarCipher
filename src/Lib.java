import java.util.Arrays;

public class Lib {
    public int charToInt(char character) {
        return character;
    }
    public char intToChar(int integer) {
        return (char) integer;
    }
    public int[] stringAsIntArray(String name) {
        String[] nameArray = name.split("");
        int[] intArray = new int[nameArray.length];
        for (int i = 0; i < nameArray.length; i++) {
            intArray[i] = charToInt(nameArray[i].charAt(0));
        }
        return intArray;
    }
    public void printIntArray(int[] intArray) {
        System.out.println(Arrays.toString(intArray));
    }
}
