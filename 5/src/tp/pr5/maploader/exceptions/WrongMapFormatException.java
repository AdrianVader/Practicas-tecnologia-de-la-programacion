package tp.pr5.maploader.exceptions;

public class WrongMapFormatException extends java.io.IOException{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	// Send no message.
	public WrongMapFormatException(){
		
	}
	
	
	// Send a User-friendly string (arg0) that explains the error.
	public WrongMapFormatException(java.lang.String arg0){
		
		System.out.println(arg0);
		
	}
	
	
	//Constructor to create the exception with a nested cause and an error message
	public WrongMapFormatException(java.lang.String arg0, java.lang.Throwable arg1){
				
	}
	
	
	//Constructor to create the exception with a nested cause
	public WrongMapFormatException(java.lang.Throwable arg0){
					
	}
	

}
