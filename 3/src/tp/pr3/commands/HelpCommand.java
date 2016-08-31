package tp.pr3.commands;


import tp.pr3.Game;
import tp.pr3.commands.exceptions.CommandExecutionException;
import tp.pr3.commands.exceptions.WrongCommandFormatException;

public class HelpCommand implements Command{
	
	
	
	//Constructor for a help command
	public HelpCommand(){
	}
	
	@Override
	public void execute() throws CommandExecutionException{
		
		System.out.println("You are lost. You are alone. You wander around" + '\n' + "Your command words are:");
		System.out.println("   " + new ExamineCommand().getHelp());
		System.out.println("   " + new GoCommand().getHelp());
		System.out.println("   " + new HelpCommand().getHelp());
		System.out.println("   " + new LookCommand().getHelp());
		System.out.println("   " + new PickCommand().getHelp());
		System.out.println("   " + new DropCommand().getHelp());
		System.out.println("   " + new QuitCommand().getHelp());
		System.out.println("   " + new UseCommand().getHelp() + '\n');
		
	}

	
	
	//Returns a description of the command syntax.
	@Override
	public String getHelp() {

		return "HELP|AYUDA";
	}

	
	//Parses the String returning a HelpCommand instance or throwing a WrongCommandFormatException()
	@Override
	public Command parse(String cad, Game execContext) throws WrongCommandFormatException{

		HelpCommand helpCommand = new HelpCommand();
		
		String words[] = cad.split(" ");

			if((words[0].toUpperCase().equals("HELP") || words[0].toUpperCase().equals("AYUDA"))){
				if(words.length != 1)
					throw new WrongCommandFormatException();
			}
			else
				throw new WrongCommandFormatException();	
				
		return helpCommand;
	}
	
	
	/*public static void main(String[] args){
		
		HelpCommand ayuda = new HelpCommand();
		ayuda.execute();
		
	}*/

}
