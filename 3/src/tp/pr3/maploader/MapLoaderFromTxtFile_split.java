package tp.pr3.maploader;

import tp.pr3.Map;
import tp.pr3.Door;
import tp.pr3.Room;
import tp.pr3.maploader.exceptions.WrongMapFormatException;

public class MapLoaderFromTxtFile_split {

	
	protected java.util.Vector<Room> rooms; //Temporary collection of Rooms read from the file used to build the Map
	protected java.util.ArrayList<Door> doors; //Temporary collection of Doors read from the file used to build the Map
	
	
	public MapLoaderFromTxtFile_split(){
		this.rooms = null;
		this.doors = null;
		
	}
	
	
	public Map loadMap(java.io.InputStream file) throws WrongMapFormatException{

		Map map = null;
		return map;
		
	}
	
}
