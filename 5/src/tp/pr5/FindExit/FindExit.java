package tp.pr5.FindExit;

import java.util.ArrayList;

import tp.pr5.Directions;
import tp.pr5.Door;
import tp.pr5.Map;
import tp.pr5.Room;
import tp.pr5.commands.Command;
import tp.pr5.commands.GoCommand;
import tp.pr5.commands.PickCommand;
import tp.pr5.commands.UseCommand;
import tp.pr5.items.Item;

public class FindExit {

	private Map map;
	private int moves;
	private ArrayList<Command> actions;
	private ArrayList<Room> visitado;
	private ArrayList<Item> items;
	private boolean encontrado;
	
	
	
	public FindExit(Map var_map, int var_moves){
		this.map = var_map;
		this.moves = var_moves;
		this.actions = new ArrayList<Command>();
		this.visitado = new ArrayList<Room>();
		this.items = new ArrayList<Item>();
		this.visitado.add(this.map.getCurrentRoom()); // Pongo que he visitado la habitación en la que empiezas.
		this.encontrado = false;
	}
	
	
	
	public void solve(Room InitRoom){ // Supongo que al principio es prometedora. Se da por suesto que la habitación inicial no es salida ?
		
		if(!this.encontrado){
			if(this.esPrometedora()){ // Falta comprobar aquí abajo que es la solución final.
			
				if(!this.esSolucion()){
				
					this.cogeObjetos();// Coje las llaves. 
					
					for(Directions dir: Directions.values()){
	
						this.map.setCurrentRoom(InitRoom, dir);
						
						Door door =this.map.getDoor(this.map.getCurrentRoom(), dir);
						
						if (door != null){ // Hay una habitación al norte donde nos podemos mover.
							if(door.isOpen() || this.usar()){ // Comprueba si puedes pasar, sino usa llaves, sino vuelta atrás y deja esa habitación sin visitar.
							
								if(this.esPrometedora()){ // Compruebo que al gastar los movimientos de usar la llave sigue siendo posible salir.
								
									Room room = this.map.getDoor(this.map.getCurrentRoom(), dir).nextRoom(this.map.getCurrentRoom());
									
									if(!this.estaVisitada(room)){ // Aún  no está visitada.								
										this.map.setCurrentRoom(room, dir);
										this.visitado.add(room);
										GoCommand go = new GoCommand();
										go.setDirection(dir);
										this.actions.add(go);
										this.moves--;
									
										// Llamada recursiva.
										this.solve(room);
									}
								}
							}
						}
					}
				}
				else{
					this.escribeSolución();
				}
			}
			this.vueltaAtras(); // Vuelta atrás.
			
		}
	}



	private boolean usar() {
		for(int i = 0; i < this.items.size();i++){
			if(this.items.get(i).use(null, this.map.getCurrentRoom())){
				this.items.get(i).use(null, this.map.getCurrentRoom()); // Lo uso otra vez para que el mapa no esté cambiado a la hora de jugar más tarde.
				UseCommand use = new UseCommand();
				use.setIdItem(this.items.get(i).getId());
				this.actions.add(use);
				this.moves -= 2;
				return true;
			}
		}
		return false;
	}



	private void cogeObjetos() {
		for(int i = 0; i < this.map.getCurrentRoom().getItems().size();i++){
			if(this.map.getCurrentRoom().getItems().get(i).getClass() == tp.pr5.items.Key.class){
				Item item = this.map.getCurrentRoom().getItems().get(i);
				PickCommand pick = new PickCommand();
				pick.setIdItem(item.getId());
				this.actions.add(pick);
				this.items.add(item);
			}
		}
	}



	private void escribeSolución() { // Imprime por pantalla la solución ¿ o la ejecuta en el juego ? e indica que se ha encontrado.
		
		for(int i = 0; i < this.actions.size();i++){
			if(this.actions.get(i).getClass() == tp.pr5.commands.PickCommand.class){
				boolean encontrado = false;
				for(int j = 0 ; j < this.actions.size();j++){
					if(this.actions.get(j).getClass() == tp.pr5.commands.UseCommand.class){
						if(((PickCommand) this.actions.get(i)).getIdItem().equalsIgnoreCase(((UseCommand) this.actions.get(j)).getIdItem())){
							encontrado = true;
						}
					}
				}
				if(!encontrado){
					this.actions.remove(i);
				}
			}
		}
		
		for(int i = 0; i < this.actions.size();i++){
			if(this.actions.get(i).getClass() == tp.pr5.commands.GoCommand.class){
				System.out.println("Go " + ((tp.pr5.commands.GoCommand) this.actions.get(i)).getDirection().name());
			}else if(this.actions.get(i).getClass() == tp.pr5.commands.PickCommand.class){
				System.out.println("Pick " + ((tp.pr5.commands.PickCommand) this.actions.get(i)).getIdItem());
			}else if(this.actions.get(i).getClass() == tp.pr5.commands.DropCommand.class){
				System.out.println("Drop " + ((tp.pr5.commands.DropCommand) this.actions.get(i)).getIdItem());
			}else if(this.actions.get(i).getClass() == tp.pr5.commands.UseCommand.class){
				System.out.println("Use " + ((tp.pr5.commands.UseCommand) this.actions.get(i)).getIdItem());
			}
		}
		
		this.encontrado = true;
	}



	private boolean vueltaAtras() { // Vuelve a la acción anterior para probar otras combinaciones.
		if(!this.actions.isEmpty()){
			for(int i = 0; i < this.actions.size();i++){
				if(this.actions.get(this.actions.size() - 1).getClass() == tp.pr5.commands.UseCommand.class || this.actions.get(this.actions.size() - 1).getClass() == tp.pr5.commands.PickCommand.class){ // Puede que falte comprobar si es pick.
					this.actions.remove(this.actions.size() - 1);
				}
			}
			this.actions.remove(this.actions.size() - 1);
			
			this.moves++;
			return true;
		}
		else{
			return false;
		}
	}
	
	
	
	private boolean esPrometedora(){ // Si nos quedan movimientos tenemos posibilidades, si no olvidamos la habitación para cuando lleguemos con más movimientos.
		if(this.map.getCurrentRoom().isExit()){
			return true;
		}else{
			if(this.moves > 0){
				return true;
			}else{
				this.visitado.remove(this.map.getCurrentRoom());
				return false;
			}
		}
	}
	
	
	
	private boolean esSolucion(){ // Devuelve si hemos conseguido salir en ese numero de movimientos.
		return this.map.getCurrentRoom().isExit() || this.moves == 0;
	}
	
	
	
	private boolean estaVisitada(Room room){ // Devuelve si ya has pasado por esa habitación.
		return this.visitado.contains(room);
	}
	
	
	
}
