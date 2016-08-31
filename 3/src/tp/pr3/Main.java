package tp.pr3;

import java.io.FileInputStream;

import tp.pr3.maploader.MapLoaderFromTxtFile;

public class Main {

	public static void main(String[] args){
		
		FileInputStream file = null;
		MapLoaderFromTxtFile loader = null;
		
		try{
			
			file = new FileInputStream(args[0]);
			loader = new MapLoaderFromTxtFile();
			
		}
		catch(Exception e){ // null map
			
			//System.err.println(":~$ java tp.pr3.Main");
			System.err.println("No map file specified." + '\n');
			System.err.println("Usage: tp.pr3.Main <mapFile>" + '\n');
			
			System.exit(-1);
			
		}		
		
		try{
			
			Game g = new Game(loader.loadMap(file));
			
			g.runGame();
			
		}
		catch(Exception e){ // Wrong map format
			
			//System.err.println(":~$ java tp.pr3.Main");
			System.err.println("No map file specified." + '\n');
			System.err.println("Usage: tp.pr3.Main <mapFile>" + '\n');
			
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
