package tp.pr5.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import tp.pr5.RoomInfo;

public class MapCell extends JButton{

	private static final long serialVersionUID = 1L;
	
	
	private RoomInfo room;
	private MapPanel owner;


	public MapCell(){
		
		super();
		this.room = null; // se inicializa el nombre del botón cuando se crea en go.execute
						  //y la habitacion se añade con un setter
		this.owner = null;
		this.setLayout(new BorderLayout());
		
		
		//-------------Action listeners--------------------------------
		
		this.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(MapCell.this.room != null){
					MapCell.this.owner.setText(MapCell.this.room.getDescription());
				}
			}
		});
		
		//-------------------------------------------------------------		
	}
	
	
	
	//Notifies that the player enters a room.
	public void enter(RoomInfo targetRoom){
		
		this.setBackground(Color.GREEN);
		this.setName(targetRoom.getName());
		
	}
	
	 
	
	//Notifies that the player leaves the room 
	public void left(){
		
		this.setBackground(Color.GRAY);
	}
	
	//Notifies that the player leaves the room when undo has been executed and the room has only been visited once
	public void forget(){
		
		this.setBackground(new javax.swing.plaf.ColorUIResource(238, 238, 238));
		this.setText("");
		this.setRoom(null);
	}
	
	public RoomInfo getRoom() {
		return room;
	}
	public void setRoom(RoomInfo room) {
		this.room = room;
	}	
	public MapPanel getOwner() {
		return owner;
	}
	public void setOwner(MapPanel owner) {
		this.owner = owner;
	}
	
	
}
