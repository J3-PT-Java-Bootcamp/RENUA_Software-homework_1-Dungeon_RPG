package exceptions;

public class MyHelper {

    MyHelper() {}

    public void equalNumbers(int num1, int num2) throws EqualNumbersException {
        if (num1 == num2) {
            throw new EqualNumbersException("Los numeros deben ser diferentes");
        }
    }

}
