package tp.pr4.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import tp.pr4.Constants;
import tp.pr4.items.Item;

public class PlayerPanel extends JPanel{

	private Jtable modelo;
	private JLabel health;
	private JLabel score;
	private JTable tabla;
	
	public PlayerPanel(){
		
		super();
		this.setLayout(new BorderLayout());
		
		this.setBorder(BorderFactory.createTitledBorder("Player info"));
		
		
		//--------------Player info----------------------
		String[] columnas = {"Id", "Description"};
		
		this.modelo = new Jtable();
		this.modelo.addColumn(columnas[0]);
		this.modelo.addColumn(columnas[1]);
		
		this.tabla = new JTable(this.modelo);
		JScrollPane panel = new JScrollPane(this.tabla);
		panel.setPreferredSize(new Dimension(450, 110));
		this.add(panel, BorderLayout.SOUTH);
		
		this.health = new JLabel();
		this.score = new JLabel();
		
		JPanel status = new JPanel();
		this.add(status, BorderLayout.NORTH);
		
		status.add(this.health);
		status.add(this.score);
		
		this.updatePlayerHealth(Constants.INITIAL_LIVE);
		this.updatePlayerScore(Constants.INITIAL_SCORE);
		//-----------------------------------------------
		
		
	}
	
	
	//Sets the inventory in the player info.
	public void updateInventory(java.util.List<Item> inventory){
		int size = this.modelo.getRowCount();
		for(int i = 0; i < size;i++){
			this.modelo.removeRow(0);// Tiene que quitar siempre el primero !!!
		}
		for(int i = 0; i < inventory.size(); i++){
			String[] item = {inventory.get(i).getId(), inventory.get(i).getDescription()};
			this.modelo.addRow(item);
		}
	}
	
	//Sets the player's health in the player info.
	public void updatePlayerHealth(int playerHealth){
		
		this.health.setText("Health: "+ playerHealth);
	}
	
	//Sets the player's score in the player info.
	public void updatePlayerScore(int playerScore){
		this.score.setText("Score: " + playerScore);
	}

	public JTable getTabla() {
		return tabla;
	}
	
	public void setTabla(JTable tabla) {
		this.tabla = tabla;
	}
	
	
	
	/*
	//Returns a String with the name of the item in the JTextField. 
	public java.lang.String getSelectedItem(){
		return this.idItem.getText();
	}
	
	public JButton getPick() {
		return pick;
	}

	public void setPick(JButton pick) {
		this.pick = pick;
	}

	public JTextField getIdItem() {
		return idItem;
	}

	public void setIdItem(JTextField idItem) {
		this.idItem = idItem;
	}
	
	public JComboBox getDirections() {
		return directions;
	}

	public void setDirections(JComboBox directions) {
		this.directions = directions;
	}

	public JButton getGo() {
		return go;
	}

	public void setGo(JButton go) {
		this.go = go;
	}
	 */
}
