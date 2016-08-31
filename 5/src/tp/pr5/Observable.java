package tp.pr5;

import java.util.ArrayList;

public class Observable <T>{

	protected ArrayList<T> observadores; // protected para que las clases que heredan de aquí puedan acceder al atributo
	
	
	public Observable() {
		observadores = new ArrayList <T>();
	}	
	
	
	public boolean addObserver(T object){		
		if(!this.observadores.contains(object))
			return this.observadores.add(object);
		return false;		
	}
	
	public boolean removeObserver(T object){
		return this.observadores.remove(object);
	}
	
	
}
