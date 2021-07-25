package sample;

public class NotEnoughMoneyException extends Exception{
    public NotEnoughMoneyException(String exceptionMessage){
        super(exceptionMessage);
    }
}
