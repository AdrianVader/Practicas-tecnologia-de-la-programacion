package tp.pr5.maploader;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import tp.pr5.Directions;
import tp.pr5.Map;
import tp.pr5.Door;
import tp.pr5.Room;
import tp.pr5.items.Food;
import tp.pr5.items.Item;
import tp.pr5.items.Key;
import tp.pr5.items.Valuable;
import tp.pr5.maploader.exceptions.WrongMapFormatException;

public class MapLoaderFromTxtFile_split {

	
	protected java.util.ArrayList<Room> rooms; //Temporary collection of Rooms read from the file used to build the Map
	protected java.util.ArrayList<Door> doors; //Temporary collection of Doors read from the file used to build the Map
	
	
	public MapLoaderFromTxtFile_split(){
		this.rooms = new java.util.ArrayList<Room>();
		this.doors = new java.util.ArrayList<Door>();
		
	}
	
	
	public Map loadMap(java.io.InputStream file) throws WrongMapFormatException{

		Map map = null;
		String aux = new String();

		BufferedReader buffer = new BufferedReader(new InputStreamReader(file));
		
		try{
			
			if(!buffer.readLine().equals("BeginMap")){// Si lo primero que lee no es BeginMap lanza una excepción y el archivo es rechazado como mapa válido
				throw new WrongMapFormatException("El archivo no empieza por BeginMap");
			}
			else{
				
				aux = buffer.readLine();
				while(!aux.equals("EndMap") || aux == null){ // Mientras no sea el final del mapa
					
					
					
					if(aux.equals("BeginRooms")){ // Insertamos las habitaciones
						
						aux = buffer.readLine();
						int cont = 0;
						
						while(!aux.equals("EndRooms") || aux == null){
							
							String[] words = aux.split(" ");
							
							if(words[0].equals("room") && Integer.parseInt(words[1]) == cont){ // Verificamos que el número de la habitación es 0 si es la primera, y las siguientes tienen el número que les corresponde
								
								if(words[words.length-1].equals("noexit")){
									String descripcion = new String();
									for(int i = 3; i < (words.length-1);i++){
										descripcion += words[i];
									}
									this.rooms.add(new Room(false, descripcion.replace('"'+"", ""), words[2]));
								
								}else if(words[words.length-1].equals("exit")){
									String descripcion = new String();
									for(int i = 3; i < (words.length-1);i++){
										descripcion += words[i];
									}
									this.rooms.add(new Room(true, descripcion.replace('"'+"", ""), words[2]));
								}
								
							}
							
							cont++;
							aux = buffer.readLine();	
						}
					
						
						
					}else if(aux.equals("BeginDoors")){ // Insertamos las puertas
						
						aux = buffer.readLine();
						int cont = 0;
						
						while(!aux.equals("EndDoors") || aux == null){
							
							String[] words = aux.split(" ");
							
							
							if(words[0].equals("door") && Integer.parseInt(words[1]) == cont){ // Verificamos que el número de la puerta es 0 si es la primera, y las siguientes tienen el número que les corresponde
								
								if(words[5].equals("north")){
									
									if(words[2].equals("bidirectional") && words[8].equals("open")){
										
										this.doors.add(new Door(this.rooms.get(Integer.parseInt(words[4])), Directions.NORTH, this.rooms.get(Integer.parseInt(words[7])), true, true));
										
									}else if(words[2].equals("bidirectional") && words[8].equals("closed")){
										
										this.doors.add(new Door(this.rooms.get(Integer.parseInt(words[4])), Directions.NORTH, this.rooms.get(Integer.parseInt(words[7])), true, false));
										
									}else if(words[2].equals("directional") && words[8].equals("open")){
										
										this.doors.add(new Door(this.rooms.get(Integer.parseInt(words[4])), Directions.NORTH, this.rooms.get(Integer.parseInt(words[7])), false, true));
										
									}else if(words[2].equals("directional") && words[8].equals("closed")){
										
										this.doors.add(new Door(this.rooms.get(Integer.parseInt(words[4])), Directions.NORTH, this.rooms.get(Integer.parseInt(words[7])), false, false));
										
									}
									
									
								}else if(words[5].equals("south")){
									
									if(words[2].equals("bidirectional") && words[8].equals("open")){
										
										this.doors.add(new Door(this.rooms.get(Integer.parseInt(words[4])), Directions.SOUTH, this.rooms.get(Integer.parseInt(words[7])), true, true));
										
									}else if(words[2].equals("bidirectional") && words[8].equals("closed")){
										
										this.doors.add(new Door(this.rooms.get(Integer.parseInt(words[4])), Directions.SOUTH, this.rooms.get(Integer.parseInt(words[7])), true, false));
										
									}else if(words[2].equals("directional") && words[8].equals("open")){
										
										this.doors.add(new Door(this.rooms.get(Integer.parseInt(words[4])), Directions.SOUTH, this.rooms.get(Integer.parseInt(words[7])), false, true));
										
									}else if(words[2].equals("directional") && words[8].equals("closed")){
										
										this.doors.add(new Door(this.rooms.get(Integer.parseInt(words[4])), Directions.SOUTH, this.rooms.get(Integer.parseInt(words[7])), false, false));
										
									}
									
									
								}else if(words[5].equals("east")){
																		
									if(words[2].equals("bidirectional") && words[8].equals("open")){
										
										this.doors.add(new Door(this.rooms.get(Integer.parseInt(words[4])), Directions.EAST, this.rooms.get(Integer.parseInt(words[7])), true, true));
										
									}else if(words[2].equals("bidirectional") && words[8].equals("closed")){
										
										this.doors.add(new Door(this.rooms.get(Integer.parseInt(words[4])), Directions.EAST, this.rooms.get(Integer.parseInt(words[7])), true, false));
										
									}else if(words[2].equals("directional") && words[8].equals("open")){
										
										this.doors.add(new Door(this.rooms.get(Integer.parseInt(words[4])), Directions.EAST, this.rooms.get(Integer.parseInt(words[7])), false, true));
										
									}else if(words[2].equals("directional") && words[8].equals("closed")){
										
										this.doors.add(new Door(this.rooms.get(Integer.parseInt(words[4])), Directions.EAST, this.rooms.get(Integer.parseInt(words[7])), false, false));
										
									}
									
									
								}else if(words[5].equals("west")){
									
									if(words[2].equals("bidirectional") && words[8].equals("open")){
										
										this.doors.add(new Door(this.rooms.get(Integer.parseInt(words[4])), Directions.WEST, this.rooms.get(Integer.parseInt(words[7])), true, true));
										
									}else if(words[2].equals("bidirectional") && words[8].equals("closed")){
										
										this.doors.add(new Door(this.rooms.get(Integer.parseInt(words[4])), Directions.WEST, this.rooms.get(Integer.parseInt(words[7])), true, false));
										
									}else if(words[2].equals("directional") && words[8].equals("open")){
										
										this.doors.add(new Door(this.rooms.get(Integer.parseInt(words[4])), Directions.WEST, this.rooms.get(Integer.parseInt(words[7])), false, true));
										
									}else if(words[2].equals("directional") && words[8].equals("closed")){
										
										this.doors.add(new Door(this.rooms.get(Integer.parseInt(words[4])), Directions.WEST, this.rooms.get(Integer.parseInt(words[7])), false, false));
										
									}// ELSE SI NO ES UNA DIRECCIÓN O NO ES RECONOCIDO DIRECTIONAL/BIDIRECTONAL O OPEN/CLOSED.
									
									
								}
								
							}
							
							cont++;
							aux = buffer.readLine();
						}
						
						
						
					}else if(aux.equals("BeginItems")){ // Insertamos los objetos
						
						aux = buffer.readLine();
						int cont = 0;
						
						while(!aux.equals("EndItems") || aux == null){
							
							String[] words = aux.split(" ");
															
								if(words[0].equals("food") && Integer.parseInt(words[1]) == cont){ // Verificamos que el número del objeto es 0 si es el primero, y los siguientes tienen el número que les corresponde
									
									Item food = new Food(words[2], words[3].replace("_", " "), Integer.parseInt(words[4]), Integer.parseInt(words[5]));
									this.rooms.get(Integer.parseInt(words[7])).addItem(food);
									
								}else if(words[0].equals("key") && Integer.parseInt(words[1]) == cont){
									
									Item key = new Key(words[2], words[3].replace("_", " "), this.doors.get(Integer.parseInt(words[5])));
									this.rooms.get(Integer.parseInt(words[7])).addItem(key);
									
								}else if(words[0].equals("valuable") && Integer.parseInt(words[1]) == cont){
									
									Item valuable = new Valuable(words[2], words[3].replace("_", " "), Integer.parseInt(words[4]));
									this.rooms.get(Integer.parseInt(words[6])).addItem(valuable);
								}
								else
									throw new WrongMapFormatException("No es ni food ni key ni valuable");
							
							aux = buffer.readLine();
							cont++;
						}
						
					}
					
					else
						throw new WrongMapFormatException("El archivo no termina con endMap.");
					
					aux = buffer.readLine();
				}
				
			}			
			buffer.close();
		}
		catch(Exception e){
			throw new WrongMapFormatException("El mapa no es correcto");
		}
		finally{
			
		}
		
		map = new Map(this.rooms.get(0));
		map.setDoors(this.doors);
		
		return map;
		
	}
	
}
