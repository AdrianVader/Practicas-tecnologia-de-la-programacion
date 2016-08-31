package tp.pr4.maploader;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import tp.pr4.Directions;
import tp.pr4.Map;
import tp.pr4.Door;
import tp.pr4.Room;
import tp.pr4.items.*;
import tp.pr4.maploader.exceptions.WrongMapFormatException;

public class MapLoaderFromTxtFile{
	
	
	protected java.util.ArrayList<Room> rooms; //Temporary collection of Rooms read from the file used to build the Map
	protected java.util.ArrayList<Door> doors; //Temporary collection of Doors read from the file used to build the Map
	
	
	
	//Constructor for a maploader from a text file in which the descriptions don't contain any spaces
	public MapLoaderFromTxtFile(){
		this.rooms = new ArrayList<Room>();
		this.doors = new ArrayList<Door>();
		
	}
	
	
	//Builds a Map from an input File
	public Map loadMap(java.io.InputStream file) throws WrongMapFormatException{

		
		Key key;
		Food food;
		Valuable valuable;
		
		Map map = null;
		String aux = new String();

		BufferedReader buffer = new BufferedReader(new InputStreamReader(file));
		
		try{

			if(!buffer.readLine().equals("BeginMap")){// Si lo primero que lee no es BeginMap lanza una excepción y el archivo es rechazado como mapa válido
				throw new WrongMapFormatException("El archivo no empieza por BeginMap");
				//throw new WrongMapFormatException();
			}
			///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			else{

				aux = buffer.readLine();
				while(!aux.equals("EndMap")){// || aux == null){ // Mientras no sea el final del mapa

				

					if(aux.equals("BeginRooms")){ // Insertamos las habitaciones

						aux = buffer.readLine();
						int cont = 0;

						while(!aux.equals("EndRooms")){// || aux == null){

							String[] words = aux.split(" ");

							if(words[0].equals("room") && Integer.parseInt(words[1]) == cont){ // Verificamos que el número de la habitación es 0 si es la primera, y las siguientes tienen el número que les corresponde

								if(words[4].equals("noexit")){
									this.rooms.add(new Room(false, words[3].replace("_", " "), words[2]));
								}else if(words[4].equals("exit")){
									this.rooms.add(new Room(true, words[3].replace("_", " "), words[2]));
								}

							}

							cont++;
							aux = buffer.readLine();	
						}



					}else if(aux.equals("BeginDoors")){ // Insertamos las puertas

						aux = buffer.readLine();
						int cont = 0;

						while(!aux.equals("EndDoors")){// || aux == null){

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

						while(!aux.equals("EndItems")){// || aux == null){

							String[] words = aux.split(" ");

							if(words[0].equals("food") && Integer.parseInt(words[1]) == cont){ // Verificamos que el número del objeto es 0 si es el primero, y los siguientes tienen el número que les corresponde

								//Item food = new Food(words[2], words[3].replace("_", " "), Integer.parseInt(words[4]), Integer.parseInt(words[5]));
								food = new Food(words[2], words[3].replace("_", " "), Integer.parseInt(words[4]), Integer.parseInt(words[5]));
								this.rooms.get(Integer.parseInt(words[7])).addItem(food);

							}else if(words[0].equals("key") && Integer.parseInt(words[1]) == cont){

								//Item key = new Key(words[2], words[3].replace("_", " "), this.doors.get(Integer.parseInt(words[5])));
								key = new Key(words[2], words[3].replace("_", " "), this.doors.get(Integer.parseInt(words[5])));
								this.rooms.get(Integer.parseInt(words[7])).addItem(key);

							}else if(words[0].equals("valuable") && Integer.parseInt(words[1]) == cont){

								//Item valuable = new Valuable(words[2], words[3].replace("_", " "), Integer.parseInt(words[4]));
								valuable = new Valuable(words[2], words[3].replace("_", " "), Integer.parseInt(words[4]));
								this.rooms.get(Integer.parseInt(words[6])).addItem(valuable);
							}
							else
								throw new WrongMapFormatException("No es ni food ni key ni valuable");
								//throw new WrongMapFormatException();

							aux = buffer.readLine();
							cont++;
						}

					}

					//if(aux == null)// La última palabra del archivo no es endMap.
						//throw new WrongMapFormatException();

					aux = buffer.readLine();
				}

			}/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////			
			buffer.close();
			
		}
		catch(Exception e){
			throw new WrongMapFormatException("El mapa no es correcto");
			//throw new WrongMapFormatException();
		}
		finally{

		}
		
		map = new Map(this.rooms.get(0));
		map.setDoors(this.doors);
		
		return map;
		
	}
	
	
}