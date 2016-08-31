package tp.pr5.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

import tp.pr5.Directions;
import tp.pr5.GameObserver;
import tp.pr5.RoomInfo;



public class MainWindow implements GameObserver{
	
	private JFrame frame;
	private JPanel actions;
	private PlayerPanel playerPanel;
	private JComboBox directions;
	private JTextField idItem;
	private MapPanel mapPanel;
	private InfoPanel infoPanel;
	private GameControllerGUI gameControllerGUI;
	
	
	public MainWindow(GameControllerGUI gcGUI){
		
		this.frame = new JFrame("Practica 4");
		this.frame.setLayout(new BorderLayout());
		this.mapPanel = new MapPanel();
		this.playerPanel = new PlayerPanel();
		this.infoPanel = new InfoPanel();
		this.gameControllerGUI = gcGUI;
		

		//-----------------Add observers-----------------------
		//---InfoPanel -> GameObserver, MapObserver and Player Observer---
		this.gameControllerGUI.registerGameObserver(this.infoPanel);
		this.gameControllerGUI.registerMapObserver(this.infoPanel);
		this.gameControllerGUI.registerPlayerObserver(this.infoPanel);
		//---Game -> GameObserver---
		this.gameControllerGUI.registerGameObserver(this);
		//---Map -> MapObserver---
		this.gameControllerGUI.registerMapObserver(this.mapPanel);
		//---Player -> PlayerObserver---
		this.gameControllerGUI.registerPlayerObserver(this.playerPanel);
		//-----------------------------------------------------
		
		
		//-------------Menu------------------------
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("File");
		JMenuItem menuQuit = new JMenuItem("Quit");
		menu.add(menuQuit);
		menuBar.add(menu);
		this.frame.setJMenuBar(menuBar);
		menuQuit.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				MainWindow.this.gameControllerGUI.executeQuitAction();
			}
		});
		//-----------------------------------------
		
		

		//-----------------Action buttons------------------
		String[] dir = {"NORTH", "SOUTH", "EAST", "WEST"};
		
		JButton go = new JButton("GO");
		this.directions = new JComboBox(dir);
		JButton pick = new JButton("PICK");
		this.idItem = new JTextField();
		JButton drop = new JButton("DROP");
		JButton use = new JButton("USE");
		JButton quit = new JButton("QUIT");
		JButton undo = new JButton("UNDO");
		//------------------------------------------------
		
		
		
		//-----------------Create and set---------------------------------------
		this.actions = new JPanel();
		this.actions.setBorder(BorderFactory.createTitledBorder("Actions"));
		this.actions.setLayout(new GridLayout(4, 2, 5, 5));// 4 filas, 2 columnas y 5 pixels de separación horizontal y vertical.
		
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		splitPane.setLeftComponent(this.actions);
		splitPane.setRightComponent(this.playerPanel);
		splitPane.setDividerLocation(300);
		this.frame.add(splitPane);
		//----------------------------------------------------------------------
		
		
		
		//-----Add action buttons-----
		this.actions.add(go);
		this.actions.add(this.directions);
		this.actions.add(pick);
		this.actions.add(this.idItem);
		this.actions.add(drop);
		this.actions.add(use);
		this.actions.add(quit);
		this.actions.add(undo);
		//----------------------------
		
		
		
		//------------------Frame-----------------------------------
		this.frame.add(splitPane, BorderLayout.NORTH);
		this.frame.add(this.mapPanel, BorderLayout.CENTER);
		this.frame.add(this.infoPanel, BorderLayout.SOUTH);
		this.frame.setVisible(true);
		this.frame.setSize(1000, 600);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//----------------------------------------------------------
		
		
		//-------------Action listeners--------------------------------
		
		
		go.addActionListener(new goListener());
		pick.addActionListener(new pickListener());
		use.addActionListener(new useListener());
		quit.addActionListener(new quitListener());
		drop.addActionListener(new dropListener());
		undo.addActionListener(new undoListener());
		
		
		//-------------------------------------------------------------
		
	
	}
	
	
	private class goListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(MainWindow.this.directions.getSelectedItem().equals("NORTH"))
				MainWindow.this.gameControllerGUI.executeGoAction(Directions.NORTH);
			else if(MainWindow.this.directions.getSelectedItem().equals("SOUTH"))
				MainWindow.this.gameControllerGUI.executeGoAction(Directions.SOUTH);
			else if(MainWindow.this.directions.getSelectedItem().equals("EAST"))
				MainWindow.this.gameControllerGUI.executeGoAction(Directions.EAST);
			else if(MainWindow.this.directions.getSelectedItem().equals("WEST"))
				MainWindow.this.gameControllerGUI.executeGoAction(Directions.WEST);
		}
	}
	
	private class pickListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			MainWindow.this.gameControllerGUI.executePickAction(MainWindow.this.idItem.getText());
		}
	}
	
	private class useListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (MainWindow.this.playerPanel.getTabla().getSelectedRow() >= 0) {
				MainWindow.this.gameControllerGUI.executeUseAction(MainWindow.this.playerPanel.getTabla().getValueAt(MainWindow.this.playerPanel.getTabla().getSelectedRow(), 0).toString());
			}
		}
	}
	
	private class quitListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			MainWindow.this.gameControllerGUI.executeQuitAction();
		}
	}
	
	private class dropListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if (MainWindow.this.playerPanel.getTabla().getSelectedRow() >= 0) {
				MainWindow.this.gameControllerGUI.executeDropAction(MainWindow.this.playerPanel.getTabla().getValueAt(MainWindow.this.playerPanel.getTabla().getSelectedRow(), 0).toString());
			}	
		}
	}
	
	private class undoListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			MainWindow.this.gameControllerGUI.executeUndoAction();
		}
	}
	
	@Override
	public void gameError(String msg) {
		JOptionPane.showMessageDialog(this.frame, msg);
	}
	
	@Override
	public void gameHelp() {
		//No hacemos nada.
	}
	
	@Override
	public void gameOver(boolean win) {
		if(win){
			JOptionPane.showMessageDialog(this.frame, "YOU WIN!  =)");
			System.exit(0);
		}
		else {
			JOptionPane.showMessageDialog(this.frame, "YOU LOSE  =(");
			System.exit(0);
		}
	}
	
	@Override
	public void gameQuit() {
		int answer = JOptionPane.showConfirmDialog(this.frame, "Are you sure ?", "Quit", JOptionPane.YES_NO_OPTION);
		if(answer == 0){
			this.gameOver(false);
			System.exit(0);
		}
	}
	
	@Override
	public void gameStart(RoomInfo initialRoom, int playerPoints, int playerHealth) {
		//------------Set the first room-------------------
		this.mapPanel.getCells()[this.mapPanel.getRow()][this.mapPanel.getCol()].setRoom(initialRoom);
		this.mapPanel.getCells()[this.mapPanel.getRow()][this.mapPanel.getCol()].setText(initialRoom.getName());
		this.mapPanel.getCells()[this.mapPanel.getRow()][this.mapPanel.getCol()].setBackground(Color.GREEN);
		this.mapPanel.getText().setText(initialRoom.getDescription());
		//-------------------------------------------------
		this.infoPanel.gameStart(initialRoom, playerPoints, playerHealth);
	}
	
	public JFrame getFrame() {
		return frame;
	}
	public void setFrame(JFrame frame) {
		this.frame = frame;
	}	
	public JPanel getActions() {
		return actions;
	}
	public void setActions(JPanel actions) {
		this.actions = actions;
	}
	public PlayerPanel getPlayerPanel() {
		return playerPanel;
	}
	public void setPlayerPanel(PlayerPanel playerPanel) {
		this.playerPanel = playerPanel;
	}
	public JComboBox getDirections() {
		return directions;
	}
	public void setDirections(JComboBox directions) {
		this.directions = directions;
	}	
	public JTextField getIdItem() {
		return idItem;
	}
	public void setIdItem(JTextField idItem) {
		this.idItem = idItem;
	}
	public MapPanel getMapPanel() {
		return mapPanel;
	}
	public void setMapPanel(MapPanel mapPanel) {
		this.mapPanel = mapPanel;
	}
	public InfoPanel getInfoPanel() {
		return infoPanel;
	}
	public void setInfoPanel(InfoPanel infoPanel) {
		this.infoPanel = infoPanel;
	}
	public GameControllerGUI getGameControllerGUI() {
		return gameControllerGUI;
	}
	public void setGameControllerGUI(GameControllerGUI gameControllerGUI) {
		this.gameControllerGUI = gameControllerGUI;
	}
	
}
