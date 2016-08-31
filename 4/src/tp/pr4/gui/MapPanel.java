package tp.pr4.gui;


import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import tp.pr4.Constants;


public class MapPanel extends JPanel{

	//private Map map;
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
		this.add(panel);//Si pongo BorderLayout.NORTH se separa todo y queda mal.
		
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
	
	
	
}
