package exceptions;

public class EqualNumbersException extends Exception {

    //Java uses this id for this class in the runtime. This is done because the class extends from Exception
    public static final long serialVersionUID = 700L;

    public EqualNumbersException(String message) {
        super(message);
    }


}
