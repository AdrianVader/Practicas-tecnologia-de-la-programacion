package tp.pr5.commands.exceptions;

public class CommandExecutionException extends java.lang.Exception{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	//Constructor without parameters (no message is given)
	public CommandExecutionException(){

	}
	
	
	//The exception thrown is created with a problem message.
	public CommandExecutionException(java.lang.String arg0){
		
		System.out.println(arg0);
		
	}
	
	
	//Constructor to create the exception with a nested cause.
	public CommandExecutionException(java.lang.Throwable arg0){
		
	}
	
	
	//Constructor to create the exception with a nested cause and an error message.
	public CommandExecutionException(java.lang.String arg0, java.lang.Throwable arg1){
		
	}
	
	
}
