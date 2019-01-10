package GUI;
import Robot.Play;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import Coords.MyCoords;

//import com.sun.java.swing.plaf.windows.resources.windows;

import Game.Fruit;
import Game.Game;
import Game.Packman;
import Game.Path2KML;
import Game.Player;
import Geom.Point3D;
import Game.Map;
import Game.ShortestPathAlgo;
//import sun.swing.SwingUtilities2.RepaintListener;
/**
 * 
 * @author Gil&Amit
 * this is the gui, it loads the ariel map given to us and use's all the methods we created ending in the game
 *
 */

public class MyFrame extends JFrame implements MouseListener
{
	public BufferedImage myImage;
	public Game game;
	public int test = 0; //flag
	public String selected;
	public int pacCounter = 0;
	public int fruitCounter = 0;
	public int pacSelect = 0; //flag
	public int fruitSelect = 0; //flag
	public int fileCounter = 1;
	public int thereisplayer = 0;
	public Point3D rotate;
	public int move = 0;
	public Graphics g;
	/**
	 * change game function
	 * @param cur
	 */
	public void changeGame (Game cur) {
		game = cur;
		repaint();
	}

	/**
	 * human play
	 */
	public void playNow2 () {
		// 1) Create a "play" from a file (attached to Ex4)
		//String file_name = "data/Ex4_OOP_example2.csv";
		Play play1 = new Play(selected);
		move = 1;
		new Thread (new Runnable() {

			@Override
			public void run() {
				thereisplayer = 0;
				// TODO Auto-generated method stub
				// 2) Set your ID's - of all the group members
				play1.setIDs(312480791,311235881);
				
				// 3)Get the GPS coordinates of the "arena"
				String map_data = play1.getBoundingBox();
				System.out.println("Bounding Box info: "+map_data);
				
				// 4) get the game-board data
				ArrayList<String> board_data = play1.getBoard();
				for(int i=0;i<board_data.size();i++) {
					System.out.println(board_data.get(i));
				}
				System.out.println();
				System.out.println("Init Player Location should be set using the bounding box info");
				
				// 5) Set the "player" init location - should be a valid location
				
				//play1.setInitLocation(32.1040,35.2061);
				play1.setInitLocation(game.player.getPlayer().x(),game.player.getPlayer().y());
				
				// 6) Start the "server"
				play1.start(); // default max time is 100 seconds (1000*100 ms).
				
				// 7) "Play" as long as there are "fruits" and time
			//	for(int i=0;i<10;i++) {
				int i=0;
					while(play1.isRuning()) {
						double azi = Map.azimuth(game.getPlayer().getPlayer(), rotate);
						System.out.println(rotate);
					// 7.1) this is the main command to the player (on the server side)
						play1.rotate(azi); 
						System.out.println("******"+i+"******");
						
					// 7.2) get the current score of the game
						String info = play1.getStatistics();
						System.out.println(info);
					// 7.3) get the game-board current state
						board_data = play1.getBoard();
						for(int a=0;a<board_data.size();a++) {
							System.out.println(board_data.get(a));
						}
						Game cur = new Game(board_data);
						game = cur;
						repaint();
						try {
							Thread.sleep(25);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						i++;
						System.out.println();
				}
				// 8) stop the server - not needed in the real implementation.
				//play1.stop();
				System.out.println("**** Done Game (user stop) ****");
				
				// 9) print the data & save to the course DB
				String info = play1.getStatistics();
				System.out.println(info);
			}
			
		}).start();
	}
	/**
	 * auto play
	 */
	public void playNow3 () {
		// 1) Create a "play" from a file (attached to Ex4)
		Play play1 = new Play(selected);
		move = 1;
		new Thread (new Runnable() {

			@Override
			public void run() {
				thereisplayer = 0;
				// TODO Auto-generated method stub
				// 2) Set your ID's - of all the group members
				play1.setIDs(312480791,311235881);
				
				// 3)Get the GPS coordinates of the "arena"
				String map_data = play1.getBoundingBox();
				System.out.println("Bounding Box info: "+map_data);
				
				// 4) get the game-board data
				ArrayList<String> board_data = play1.getBoard();
				for(int i=0;i<board_data.size();i++) {
					System.out.println(board_data.get(i));
				}
				System.out.println();
				System.out.println("Init Player Location should be set using the bounding box info");
				
				// 5) Set the "player" init location - should be a valid location
				
				//play1.setInitLocation(32.1040,35.2061);
				play1.setInitLocation(game.player.getPlayer().x(),game.player.getPlayer().y());
				
				// 6) Start the "server"
				play1.start(); // default max time is 100 seconds (1000*100 ms).
				
				// 7) "Play" as long as there are "fruits" and time
			//	for(int i=0;i<10;i++) {
				int i=0;
					while(play1.isRuning()) {
						double azi = ShortestPathAlgo.shortestGame(game);
						System.out.println(rotate);
					// 7.1) this is the main command to the player (on the server side)
						play1.rotate(azi); 
						System.out.println("******"+i+"******");
						
					// 7.2) get the current score of the game
						String info = play1.getStatistics();
						System.out.println(info);
					// 7.3) get the game-board current state
						board_data = play1.getBoard();
						for(int a=0;a<board_data.size();a++) {
							System.out.println(board_data.get(a));
						}
						Game cur = new Game(board_data);
						game = cur;
						repaint();
						try {
							Thread.sleep(25);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						i++;
						System.out.println();
				}
				// 8) stop the server - not needed in the real implementation.
				//play1.stop();
				System.out.println("**** Done Game (user stop) ****");
				
				// 9) print the data & save to the course DB
				String info = play1.getStatistics();
				System.out.println(info);
			}
			
		}).start();
	}
	
	public MyFrame() 
	{
		initGUI();		
		this.addMouseListener(this); 
	}
	
	private void initGUI() 
	{
		MenuBar menuBar = new MenuBar();
		Menu menu = new Menu("Menu"); 
		Menu menu2 = new Menu("Play"); 
		MenuItem item2 = new MenuItem("Load Game");
		MenuItem item3 = new MenuItem("Path to kml");
		MenuItem item4 = new MenuItem("Save Game");
		MenuItem item5 = new MenuItem("Start Game");
		MenuItem item6 = new MenuItem("Restart");
		MenuItem item7 = new MenuItem("Packman");
		MenuItem item8 = new MenuItem("Packman Stop");
		MenuItem item9 = new MenuItem("Fruit");
		MenuItem item10 = new MenuItem("Fruit Stop");
		MenuItem play = new MenuItem("Play");
		MenuItem addPlayer = new MenuItem("add the player");
		MenuItem playAuto = new MenuItem("Auto Play");
		
		
		menuBar.add(menu);
		menuBar.add(menu2);
		menu.add(item2);
		menu.add(item3);
		menu.add(item4);
		menu.add(item5);
		menu.add(item6);
		menu.add(item7);
		menu.add(item8);
		menu.add(item9);
		menu.add(item10);
		menu2.add(play);
		menu2.add(playAuto);
		menu2.add(addPlayer);
		
		//buttons actions
		
		addPlayer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				thereisplayer = 1;
				pacSelect = 0; //flags
				fruitSelect = 0;
			}
		});
		
		playAuto.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				playNow3();
			}
		});
		
		play.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				playNow2();
			}
		});
		
		item4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				pacSelect = 0; //flags
				fruitSelect = 0;
				thereisplayer = 0;
				System.out.println("Save pressed");
				if(game != null) { //saving file
					try {
						game.game2CSV(fileCounter++);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					System.out.println("Saved to desktop");
				}
				else {
					System.out.println("nothing to save");
				}
			}
			
		});
		
			item7.addActionListener(new ActionListener() {
			//adding packmans to a new game
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				pacSelect = 1; //flags
				fruitSelect = 0;
				thereisplayer = 0;
				if(pacCounter == 0 && fruitCounter == 0) {
					game = new Game();
					
				}
				
				
				repaint();
			}
			
		});
			item8.addActionListener(new ActionListener() {
				//stopping for packmans
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				pacSelect = 0; //flags
				fruitSelect = 0;
				thereisplayer = 0;
				repaint();
			}
			
		});
			item9.addActionListener(new ActionListener() {
				//adding fruits to a new game
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(pacCounter == 0 && fruitCounter == 0) {
					game = new Game();
					
				}
				fruitSelect = 1; //flags
				pacSelect = 0;
				thereisplayer = 0;
				
				
				repaint();
			}
			
		});
			
			item10.addActionListener(new ActionListener() {
				//stopping for fruits
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				pacSelect = 0;//flags
				fruitSelect = 0;
				thereisplayer = 0;
				repaint();
			}
			
		});
		
			item5.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("start pressed");
				ShortestPathAlgo.shortest(game);
				pacSelect = 0;//flags
				fruitSelect = 0;
				thereisplayer = 0;
				repaint();
			}
			
		});
			
			item3.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					for(int i = 0; i<game.getPackmans().size(); i++) {
						try {
							Path2KML.path2kml(game.getPackmans().get(i));
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					pacSelect = 0;
					fruitSelect = 0;
					thereisplayer = 0;
				}
				
			});
			
			item2.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					System.out.println("Load pressed");
					  JFileChooser chooser = new JFileChooser(); //java file chooser
				        //filtering the right files
					  FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV files (*csv)", "csv");
				        chooser.setFileFilter(filter);
				        int returnVal = chooser.showOpenDialog(null);
				        if(returnVal == JFileChooser.APPROVE_OPTION) {
				            System.out.println("You chose to open this file: " +
				                    chooser.getSelectedFile().getName());
				        }
				        try {
							game = new Game(chooser.getSelectedFile().toString());
							selected = chooser.getSelectedFile().toString();
						} catch (IOException e1) {
							
							e1.printStackTrace();
						}
				        pacSelect = 0; //flags
						fruitSelect = 0;
						thereisplayer = 0;
				        test = 1;
					repaint();
				}
				
			});
			item6.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					//clearing the game
					System.out.println("Restart pressed");
						game.packmans.clear();
						game.fruits.clear();
						game.ghosts.clear();
						game.blocks.clear();
						pacSelect = 0; //flags
						fruitSelect = 0;
						thereisplayer = 0;
						test = 1;
						repaint();
					
				}
				
			});
		
		this.setMenuBar(menuBar);
		// images for the game
		try {
			 myImage = ImageIO.read(new File("Ariel1.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}

	int x = -1;
	int y = -1;
	
	public void paint(Graphics g)
	{
		g.drawImage(myImage, 0, 0, this);
		if (test == 1) {
			for (int i = 0; i < game.getBlocks().size(); i++) { //getting each block and placing it
				g.fillRect(game.getBlocks().get(i).getXA(), game.getBlocks().get(i).getYB(), game.getBlocks().get(i).getXB()-game.getBlocks().get(i).getXA(), game.getBlocks().get(i).getYA()-game.getBlocks().get(i).getYB());
			}
			if(game.player != null) {
				g.drawImage(game.getPlayer().getPlayerIMG(), game.getPlayer().getX(), game.getPlayer().getY(), this);
			}
			for (int i = 0; i < game.getPackmans().size(); i++) { //getting each packman and placing it
				for(int j = 1; j<game.getPackmans().get(i).getPath().getPath().size(); j++) { //getting each packman path and drawing it
					g.drawLine(game.getPackmans().get(i).getPath().getPath().get(j-1).getX(), game.getPackmans().get(i).getPath().getPath().get(j-1).getY(), game.getPackmans().get(i).getPath().getPath().get(j).getX(), game.getPackmans().get(i).getPath().getPath().get(j).getY());
				}
				g.drawImage(game.getPackmans().get(i).getPackmanIMG(), game.getPackmans().get(i).getX(), game.getPackmans().get(i).getY(), this);
			}
			for (int i = 0; i < game.getFruits().size(); i++) { //getting each fruit and placing it
			    g.drawImage(game.getFruits().get(i).getFruitIMG(), game.getFruits().get(i).getX(), game.getFruits().get(i).getY(), this);
			}
			for (int i = 0; i < game.getGhosts().size(); i++) { //getting each fruit and placing it
			    g.drawImage(game.getGhosts().get(i).ghostIMG, game.getGhosts().get(i).getX(), game.getGhosts().get(i).getY(), this);
			}

		}
		
	
		if(x!=-1 && y!=-1)
		{
			int r = 10;
			x = x - (r / 2);
			y = y - (r / 2);
			//g.fillOval(x, y, r, r);
		} 
	}
	
	//events
	@Override
	public void mouseClicked(MouseEvent arg) {
		System.out.println("mouse Clicked");
		System.out.println("("+ arg.getX() + "," + arg.getY() +")");
		x = arg.getX();
		y = arg.getY();
		if (pacSelect != 0) { //flag check
			Point3D shula = Map.Pixsels2LatLon(x, -y); //placing the packmans
			Packman temp = new Packman (pacCounter++, shula);
			game.packmans.add(temp); //adding the packman to the game array
			test = 1; //flag
		}
		if (fruitSelect != 0) { //flag check
			Point3D shula = Map.Pixsels2LatLon(x, -y); //placing the fruits
			Fruit temp = new Fruit (fruitCounter++, shula);
			game.fruits.add(temp); //adding the fruit to the game array
			test = 1; //flag
		}
		if (fruitSelect != 0) { //flag check
			Point3D shula = Map.Pixsels2LatLon(x, -y); //placing the fruits
			Fruit temp = new Fruit (fruitCounter++, shula);
			game.fruits.add(temp); //adding the fruit to the game array
			test = 1; //flag
		}
		if (thereisplayer != 0) { //flag check
			Point3D shula = Map.Pixsels2LatLon(x, -y); //placing player
			Player temp = new Player(0,shula);
			game.setPlayer(temp); //setting player
		}
		if (move != 0) {
			
		}
		rotate = Map.Pixsels2LatLon(x, -y);
		repaint();
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		/////////System.out.println("mouse entered");
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	

}
