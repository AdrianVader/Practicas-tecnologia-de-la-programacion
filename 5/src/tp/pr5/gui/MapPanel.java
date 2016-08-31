package tp.pr5.gui;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import tp.pr5.Constants;
import tp.pr5.Directions;
import tp.pr5.MapObserver;
import tp.pr5.RoomInfo;


public class MapPanel extends JPanel implements MapObserver{

	private static final long serialVersionUID = 1L;

	
	private MapCell[][] cells;
	private JTextArea text;
	int row;
	int col;
	private JPanel panel;
	
	
	public MapPanel(){
		super();
		this.row = Constants.NUM_CELLS / 2;
		this.col = Constants.NUM_CELLS / 2;
		this.cells = new MapCell[Constants.NUM_CELLS][Constants.NUM_CELLS];
		this.panel = new JPanel(); 
		this.setLayout(new BorderLayout());
		panel.setLayout(new GridLayout(Constants.NUM_CELLS, Constants.NUM_CELLS));
		this.add(panel);
		
		this.setBorder(BorderFactory.createTitledBorder("Map"));
		
		
		//------------Room description----------------------------------
		this.text = new JTextArea(4, 0);
		JScrollPane scroll = new JScrollPane(this.text);
		this.text.setEditable(false);
		scroll.setBorder(BorderFactory.createTitledBorder("Room"));
		this.add(scroll, BorderLayout.SOUTH);
		//--------------------------------------------------------------
		
		
		for(int i = 0; i < Constants.NUM_CELLS; i++){
			for(int j = 0; j < Constants.NUM_CELLS; j++){
				MapCell cell = new MapCell();
				this.cells[i][j] = cell;
				cell.setOwner(this);
				this.panel.add(this.cells[i][j]);
			}
		}
		
		
	}


	public MapCell[][] getCells() {
		return cells;
	}
	public void setCells(MapCell[][] cells) {
		this.cells = cells;
	}
	public JTextArea getText() {
		return text;
	}
	public void setText(JTextArea text) {
		this.text = text;
	}
	public void setText(String description) {
		this.text.setText(description);
	}
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public int getCol() {
		return col;
	}
	public void setCol(int col) {
		this.col = col;
	}
	


	@Override
	public void playerHasExaminedRoom(RoomInfo r) {
	
		this.text.setText(r.getDescription());
		
	}


	@Override
	public void roomEntered(Directions direction, RoomInfo targetRoom) {
		
		this.cells[this.row][this.col].setBackground(Color.GRAY);
		
		if(direction.name().equals("NORTH")){
			this.row += -1;										
		}else if(direction.name().equals("SOUTH")){
			this.row += 1;
		}else if(direction.name().equals("EAST")){
			this.col += 1;
		}else if(direction.name().equals("WEST")){
			this.col += -1;
		}
		
		this.cells[this.row][this.col].setBackground(Color.GREEN);
		this.cells[this.row][this.col].setText(targetRoom.getName());
		this.cells[this.row][this.col].setRoom(targetRoom);
		this.text.setText(targetRoom.getDescription());
	}
	

	@Override
	public void updateRoomInventory(RoomInfo room) {
		
		this.text.setText(room.getDescription());
	}
	
	
	public void forgetRoom(){
		this.cells[this.row][this.col].forget();
	}
	
	
	public void changeRoom(Directions direction, RoomInfo targetRoom){
		
		if(direction.name().equals("NORTH")){
			this.row += -1;										
		}else if(direction.name().equals("SOUTH")){
			this.row += 1;
		}else if(direction.name().equals("EAST")){
			this.col += 1;
		}else if(direction.name().equals("WEST")){
			this.col += -1;
		}
		
		this.cells[this.row][this.col].setBackground(Color.GREEN);
		this.cells[this.row][this.col].setText(targetRoom.getName());
		this.cells[this.row][this.col].setRoom(targetRoom);
		this.text.setText(targetRoom.getDescription());
	}

	
}
