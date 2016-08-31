package tp.pr5;

import jargs.gnu.CmdLineParser;

import java.io.FileInputStream;

import tp.pr5.FindExit.FindExit;
import tp.pr5.console.Console;
import tp.pr5.console.GameControllerConsole;
import tp.pr5.gui.GameControllerGUI;
import tp.pr5.gui.MainWindow;
import tp.pr5.maploader.MapLoaderFromTxtFile;

public class Main {

	
	//tp.pr.Main (-i |--interface) (console | swing | both) (-m |--map) <mapFilename> (-d | --max-depth) <depth>
	
	
	public static void main(String[] args){
		
		
		CmdLineParser parser = new CmdLineParser();
		CmdLineParser.Option console = parser.addStringOption('m',"map");
		CmdLineParser.Option swing = parser.addStringOption('i',"interface");
		CmdLineParser.Option solve = parser.addStringOption('d', "max-depth"); //--interface both --map map.txt --max-depth 3
		
		try {
			parser.parse(args);
		}
		catch (CmdLineParser.OptionException e ) {
			System.err.println(e.getMessage());
			System.exit(-1);
		}
		String swingValue = (String)parser.getOptionValue(swing);
		String consoleValue = (String)parser.getOptionValue(console);
		String solveValue = (String)parser.getOptionValue(solve);
		
		FileInputStream file = null;
		MapLoaderFromTxtFile loader = null;
		
		try{
			
			file = new FileInputStream(consoleValue);
			loader = new MapLoaderFromTxtFile();
			
		}
		catch(Exception e){ // null map
			
			System.err.println("No map file specified." + '\n');
			System.err.println("Usage: tp.pr3.Main <mapFile>" + '\n');
			
			System.exit(-1);
			
		}	
		
		try{
			Map map = loader.loadMap(file); // No sé por qué si lo intento cargar 2 veces me salta excepción. Por eso lo guardo en un mapa y con ese trabajo.
			Room InitRoom = map.getCurrentRoom();
			Game g = new Game(map);
			
			if(Integer.parseInt(solveValue) > 0){
				new FindExit(map, Integer.parseInt(solveValue)).solve(map.getCurrentRoom());
			}
			
			map.setCurrentRoom(InitRoom, Directions.UNKNOWN);
			
			if(swingValue.equalsIgnoreCase("swing")){
				
				GameControllerGUI gui = new GameControllerGUI(g);
				new MainWindow(gui);
				gui.runGame();
				
			}
			else if(swingValue.equalsIgnoreCase("console")){
				
				GameControllerConsole cons = new GameControllerConsole(g); 
				new Console();
				cons.runGame();
				
			}
			else if(swingValue.equalsIgnoreCase("both")){
				
				GameControllerGUI gui = new GameControllerGUI(g);
				new MainWindow(gui);
				gui.runGame();
				
				
				
				new GameControllerConsole(g); 
				new Console();
				//cons.runGame(); // No se permiten comandos desde la consola en este modo.
				
			}
			else
				throw new Exception("Error : no se ha especificado ni console ni swing");
		}
		catch(Exception e){ // Wrong map format
			System.err.println("No map file specified." + '\n');
			System.err.println("Usage: tp.pr3.Main <mapFile>" + '\n');
			System.err.println(e.getMessage());
			System.exit(-1);
			
		}
		
		finally{
			try{
				file.close();
			}
			catch(Exception p){}
		}
		
		return;
	}	
	
}