package tp.pr5.commands.exceptions;

public class WrongCommandFormatException extends java.lang.Exception{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//Constructor without parameters (no message is given)
	public WrongCommandFormatException(){
		
	}
	
	//The exception thrown is created with a problem message.
	public WrongCommandFormatException(java.lang.String arg0){
		
		System.out.println(arg0);
	}
	
	//Constructor to create the exception with a nested cause.
	public WrongCommandFormatException(java.lang.Throwable arg0){
		
	}
	
	//Constructor to create the exception with a nested cause and an error message.
	public WrongCommandFormatException(java.lang.String arg0, java.lang.Throwable arg1){
		
	}
}
