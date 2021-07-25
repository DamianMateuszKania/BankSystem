package sample;

public class ClientNotFoundException extends Exception{
    public ClientNotFoundException(String exceptionMessage){
        super(exceptionMessage);
    }
}
