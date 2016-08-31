package tp.pr4;

import jargs.gnu.CmdLineParser;

import java.io.FileInputStream;

import tp.pr4.gui.MainWindow;
import tp.pr4.maploader.MapLoaderFromTxtFile;

public class Main {

	public static void main(String[] args){
		
		
		CmdLineParser parser = new CmdLineParser();
		CmdLineParser.Option console = parser.addStringOption('m',"map");
		CmdLineParser.Option swing = parser.addStringOption('i',"interface");
		
		try {
			parser.parse(args);
		}
		catch (CmdLineParser.OptionException e ) {
			System.err.println(e.getMessage());
			System.exit(-1);
		}
		String swingValue = (String)parser.getOptionValue(swing);
		String consoleValue = (String)parser.getOptionValue(console);
		
		FileInputStream file = null;
		MapLoaderFromTxtFile loader = null;
		
		try{
			
			file = new FileInputStream(consoleValue);
			loader = new MapLoaderFromTxtFile();
			
		}
		catch(Exception e){ // null map
			
			//System.err.println(":~$ java tp.pr3.Main");
			System.err.println("No map file specified." + '\n');
			System.err.println("Usage: tp.pr3.Main <mapFile>" + '\n');
			
			System.exit(-1);
			
		}	
		
		try{
			Game g = new Game(loader.loadMap(file)); // ESTA LINEA CREA UN BUCLE INFINITO
			if(swingValue.equalsIgnoreCase("swing")){
				g.setMainWindow(new MainWindow(g));
				//new MainWindow(g);
			}
			else if(swingValue.equalsIgnoreCase("console")){
				
				g.runGame();
				//System.exit(0);para que no de error el validador?
			}
			else
				throw new Exception("Error : no se ha especificado ni console ni swing");
		}
		catch(Exception e){ // Wrong map format
			
			//System.err.println(":~$ java tp.pr3.Main");
			System.err.println("No map file specified." + '\n');
			System.err.println("Usage: tp.pr3.Main <mapFile>" + '\n');
			System.err.println(e.getMessage());
			System.exit(-1);
			
		}
		finally{
			//if (file != null)
			try{
				file.close();
			}
			catch(Exception p){}
		}
		
		
		
		return;
	}	
	
}