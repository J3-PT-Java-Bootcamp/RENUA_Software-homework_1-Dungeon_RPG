package utilities.Keyboard;
import java.util.Scanner;

public class ReadFromKeyboard {

    public static int readKeyboard() {
        Scanner teclado = new java.util.Scanner(System.in);
        return teclado.nextInt();
    }

    public static int readNumber(int min, int max) {
        return new java.util.Scanner(System.in).nextInt();
    }

}
