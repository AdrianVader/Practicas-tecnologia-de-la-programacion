package tp.pr4.gui;

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

import tp.pr4.Constants;
import tp.pr4.Directions;
import tp.pr4.Game;
import tp.pr4.commands.DropCommand;
import tp.pr4.commands.GoCommand;
import tp.pr4.commands.PickCommand;
import tp.pr4.commands.QuitCommand;
import tp.pr4.commands.UndoCommand;
import tp.pr4.commands.UseCommand;
import tp.pr4.commands.exceptions.CommandExecutionException;



public class MainWindow {
	
	private JFrame frame;
	private JPanel actions;
	private PlayerPanel playerPanel;
	private JButton go;
	private JComboBox directions;
	private JButton pick;
	private JTextField idItem;
	private JButton drop;
	private JButton use;
	private JButton quit;
	private JButton undo;
	private MapPanel mapPanel;
	private Game game;
	private UndoCommand undoCommand;
	
	
	public MainWindow(Game game){
		
		this.frame = new JFrame("Practica 4");
		this.frame.setLayout(new BorderLayout());
		this.game = game;
		this.playerPanel = new PlayerPanel();
		this.mapPanel = new MapPanel();
		this.undoCommand = new UndoCommand();
		this.undoCommand.setCurrentGame(MainWindow.this.game);
		
		
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
				//MainWindow.this.frame.setVisible(false);				
				//JOptionPane ask = new JOptionPane();
				int answer = JOptionPane.showConfirmDialog(MainWindow.this.frame, "Are you sure ?", "Quit", JOptionPane.YES_NO_OPTION);
				if(answer == 0){
					//JOptionPane gameOver = new JOptionPane();
					JOptionPane.showMessageDialog(new JButton("Aceptar"), Constants.MESSAGE_FIN + MainWindow.this.game.getPlayer().toString());
					System.exit(0);
				}
			}
		});
		//-----------------------------------------
		
		

		//-----------------Action buttons------------------
		String[] dir = {"NORTH", "SOUTH", "EAST", "WEST"};
		
		this.go = new JButton("GO");
		this.directions = new JComboBox(dir);
		this.pick = new JButton("PICK");
		this.idItem = new JTextField();
		this.drop = new JButton("DROP");
		this.use = new JButton("USE");
		this.quit = new JButton("QUIT");
		this.undo = new JButton("UNDO");
		//-----------------Action buttons------------------
		
		
		
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
		this.actions.add(this.go);
		this.actions.add(this.directions);
		this.actions.add(this.pick);
		this.actions.add(this.idItem);
		this.actions.add(this.drop);
		this.actions.add(this.use);
		this.actions.add(this.quit);
		this.actions.add(this.undo);
		//----------------------------
		
		
		
		//------------------Frame-----------------------------------
		this.frame.add(splitPane, BorderLayout.NORTH);
		this.frame.add(this.mapPanel, BorderLayout.CENTER);
		
			//------------Set the first room-------------------
			this.mapPanel.getCells()[this.mapPanel.getRow()][this.mapPanel.getCol()].setRoom(this.game.getCurrentMap().getCurrentRoom());
			this.mapPanel.getCells()[this.mapPanel.getRow()][this.mapPanel.getCol()].setText(this.game.getCurrentMap().getCurrentRoom().getName());
			this.mapPanel.getCells()[this.mapPanel.getRow()][this.mapPanel.getCol()].setBackground(Color.GREEN);
			this.mapPanel.getText().setText(this.game.getCurrentMap().getCurrentRoom().getDescription());
			//-------------------------------------------------
			
		this.frame.setVisible(true);
		this.frame.setSize(1000, 600);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//----------------------------------------------------------
		
		
		//-------------Action listeners--------------------------------
		
		
		this.go.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				GoCommand go = new GoCommand();
				
				if(MainWindow.this.directions.getSelectedItem().equals("NORTH"))
					go.setDirection(Directions.NORTH);
				else if(MainWindow.this.directions.getSelectedItem().equals("SOUTH"))
					go.setDirection(Directions.SOUTH);
				else if(MainWindow.this.directions.getSelectedItem().equals("EAST"))
					go.setDirection(Directions.EAST);
				else if(MainWindow.this.directions.getSelectedItem().equals("WEST"))
					go.setDirection(Directions.WEST);
					
				go.setGame(MainWindow.this.game);
				
				try {
					go.execute();
					MainWindow.this.undoCommand.saveGame(go);
				} catch (CommandExecutionException e) {}
				
			}
		});
		
		
		this.pick.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				PickCommand pick = new PickCommand();
				pick.setGame(MainWindow.this.game);
				pick.setIdItem(MainWindow.this.idItem.getText());
				try {
					pick.execute();
					MainWindow.this.undoCommand.saveGame(pick);
				}catch (CommandExecutionException e) {}
			}
		});
		
		
		this.use.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0){
				UseCommand use = new UseCommand();
				if(MainWindow.this.playerPanel.getTabla().getSelectedRow() >= 0){
					use.setIdItem(MainWindow.this.playerPanel.getTabla().getValueAt(MainWindow.this.playerPanel.getTabla().getSelectedRow(), 0).toString());
					use.setGame(MainWindow.this.game);
					try{
						use.execute();
						MainWindow.this.undoCommand.saveGame(use);
					}catch(Exception e){}
				}
			}
		});
		
		
		this.quit.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				QuitCommand quit = new QuitCommand();
				quit.setGame(MainWindow.this.game);
				try{
					quit.execute();
				}catch(Exception e){}
			}
		});
		
		
		this.drop.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				DropCommand drop = new DropCommand();
				if(MainWindow.this.playerPanel.getTabla().getSelectedRow() >= 0){
					drop.setIdItem(MainWindow.this.playerPanel.getTabla().getValueAt(MainWindow.this.playerPanel.getTabla().getSelectedRow(), 0).toString());
					drop.setGame(MainWindow.this.game);
					try{
						drop.execute();
						MainWindow.this.undoCommand.saveGame(drop);
					}catch(Exception e){}
				}
			}
			
		});
		
		
		this.undo.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try{
					MainWindow.this.undoCommand.execute();
				}catch(Exception e){}
			}
		});
		
		
		//-------------------------------------------------------------
		
	
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
	public JButton getGo() {
		return go;
	}
	public void setGo(JButton go) {
		this.go = go;
	}
	public JComboBox getDirections() {
		return directions;
	}
	public void setDirections(JComboBox directions) {
		this.directions = directions;
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
	public JButton getDrop() {
		return drop;
	}
	public void setDrop(JButton drop) {
		this.drop = drop;
	}
	public JButton getUse() {
		return use;
	}
	public void setUse(JButton use) {
		this.use = use;
	}
	public JButton getQuit() {
		return quit;
	}
	public void setQuit(JButton quit) {
		this.quit = quit;
	}
	public MapPanel getMapPanel() {
		return mapPanel;
	}
	public void setMapPanel(MapPanel mapPanel) {
		this.mapPanel = mapPanel;
	}
	public Game getGame() {
		return game;
	}
	public void setGame(Game game) {
		this.game = game;
	}
	public JButton getUndo() {
		return undo;
	}
	public void setUndo(JButton undo) {
		this.undo = undo;
	}
	public UndoCommand getUndoCommand() {
		return undoCommand;
	}
	public void setUndoCommand(UndoCommand undoCommand) {
		this.undoCommand = undoCommand;
	}
}
