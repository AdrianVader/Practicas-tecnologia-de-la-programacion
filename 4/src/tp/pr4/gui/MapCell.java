package tp.pr4.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

import tp.pr4.Room;

public class MapCell extends JButton{
	
	
	
	//private boolean visited;
	private Room room;
	private MapPanel owner;


	public MapCell(){
		
		super();
		this.room = null;// se inicializa el nombre del botón cuando se crea en go.execute
		//y la habitacion se añade con un setter
		this.owner = null;
		this.setLayout(new BorderLayout());
		
		
		//-------------Action listeners--------------------------------
		
		this.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(MapCell.this.room != null){
					owner.getText().setText(MapCell.this.room.getDescription());
				}
			}
		});
		
		//-------------------------------------------------------------
		
		
		
	}
	
	
	public Room getRoom() {
		return room;
	}
	public void setRoom(Room room) {
		this.room = room;
	}
	public MapPanel getOwner() {
		return owner;
	}
	public void setOwner(MapPanel owner) {
		this.owner = owner;
	}
	
	
}
