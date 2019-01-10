package Threads;
import java.io.ObjectInputFilter.Status;
import java.util.ArrayList;
import javax.swing.SwingUtilities;
import GUI.MyFrame;
import Game.Game;
import Robot.Play;


public class play extends MyFrame implements Runnable {
	@Override
	public void run() {
		// TODO Auto-generated method stub
		ArrayList<String> now = new ArrayList<String>();
		// 1) Create a "play" from a file (attached to Ex4)
		//String file_name = "data/Ex4_OOP_example2.csv";
		Play play1 = new Play("data/Ex4_OOP_example2.csv");
		
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
		play1.setInitLocation(32.1040,35.2061);
		
		// 6) Start the "server"
		play1.start(); // default max time is 100 seconds (1000*100 ms).
		
		// 7) "Play" as long as there are "fruits" and time
	//	for(int i=0;i<10;i++) {
		int i=0;
			while(play1.isRuning()) {
				i++;
		// 7.1) this is the main command to the player (on the server side)
			play1.rotate(36*i); 
			System.out.println("***** "+i+"******");
			
		// 7.2) get the current score of the game
			String info = play1.getStatistics();
			System.out.println(info);
		// 7.3) get the game-board current state
			board_data = play1.getBoard();
			for(int a=0;a<board_data.size();a++) {
				System.out.println(board_data.get(a));
				now.add(board_data.get(a));
			}
			Game cur = new Game(now);
			game = cur;
			now.clear();
			System.out.println();
		}
		// 8) stop the server - not needed in the real implementation.
		//play1.stop();
		System.out.println("**** Done Game (user stop) ****");
		
		// 9) print the data & save to the course DB
		String info = play1.getStatistics();
		System.out.println(info);
	}


}
