package utilities.Keyboard;
import java.util.Scanner;

public class ReadFromKeyboard {

    public static int readIntKeyboard() {
        Scanner keyboard = new java.util.Scanner(System.in);
        return keyboard.nextInt();
    }

    public static String readStringKeyboard() {
        Scanner keyboard = new java.util.Scanner(System.in);
        return keyboard.nextLine();
    }

    public static int readNumber(int min, int max) {
        return new java.util.Scanner(System.in).nextInt();
    }

}
