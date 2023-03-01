package sg.edu.nus.iss.workshop24prac.exception;

public class OrderException extends Exception {
    public OrderException(){
        super();
    }

    public OrderException(String msg){
        super(msg);
    }
}
