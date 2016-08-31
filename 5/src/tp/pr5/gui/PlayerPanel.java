package tp.pr5.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import tp.pr5.Constants;
import tp.pr5.PlayerObserver;
import tp.pr5.items.Item;

public class PlayerPanel extends JPanel implements PlayerObserver{


	private static final long serialVersionUID = 1L;
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
	
	
	

		
	//Sets the player's health in the player info.
	public void updatePlayerHealth(int playerHealth){
		
		this.health.setText("Health: "+ playerHealth);
	}
	
	//Sets the player's score in the player info.
	public void updatePlayerScore(int playerScore){
		this.score.setText("Score: " + playerScore);
	}
	
	
	@Override
	public void inventoryUpdate(List<Item> inventory) {
		int size = this.modelo.getRowCount();
		for(int i = 0; i < size;i++){
			this.modelo.removeRow(0);// Tiene que quitar siempre el primero !!!
		}
		for(int i = 0; i < inventory.size(); i++){
			String[] item = {inventory.get(i).getId(), inventory.get(i).getDescription()};
			this.modelo.addRow(item);
		}
	}


	@Override
	public void itemEmpty(String itemName) {
		//no hace falta en gui
	}


	@Override
	public void itemLooked(String description) {
		//no hace nada en gui
	}


	@Override
	public void itemUsed(String itemName) {		
		//en gui no hace falta
	}


	@Override
	public void playerDead() {
		JOptionPane.showMessageDialog(this, Constants.MESSAGE_DIE);
	}


	@Override
	public void playerLookedInventory(List<Item> inventory) {
		//no hace falta en gui
	}


	@Override
	public void playerUpdate(int newHealth, int newScore) {
		this.health.setText("Health: "+ newHealth);
		this.score.setText("Score: " + newScore);
	}
	
	
	
	
	
	
	
	
	
	public JTable getTabla() {
		return tabla;
	}
	
	public void setTabla(JTable tabla) {
		this.tabla = tabla;
	}


	
	
}
