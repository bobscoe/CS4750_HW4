package threeInARow;

public class IllegalOperationException extends Exception{
	String message = "";
	public IllegalOperationException(String message){
		this.message = message;
	}
	public String getMessage(){
		return message;
	}
}
