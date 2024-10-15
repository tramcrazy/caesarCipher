public class Main {
    public static void main(String[] args) {
        Lib myLib = new Lib();
        //System.out.println("ENCRYPT: Jonathan, key 16");
        //System.out.println(myLib.encryptDecryptString("Jonathan", 16, false));
        //System.out.println("ENCRYPT: zzYY, key 3");
        //System.out.println(myLib.encryptDecryptString("zzYY", 20, false));
        //System.out.println("DECRYPT: UFTU, key 1");
        //System.out.println(myLib.encryptDecryptString("UFTU", 1, true));
        myLib.bruteForceString("UFTU", "TEST");
    }
}
