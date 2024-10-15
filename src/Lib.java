public class Lib {
    public int charToInt(char character) {
        return character;
    }
    public char intToChar(int integer) {
        return (char) integer;
    }
    public void printNameAsAscii(String name) {
        String[] nameArray = name.split("");
        int i = 0;
        for (String letter : nameArray) {
            if (i == nameArray.length - 1) {
                System.out.println(charToInt(letter.charAt(0)));
            } else {
                System.out.print(charToInt(letter.charAt(0)) + " ");
            }
            i++;
        }
    }
}
