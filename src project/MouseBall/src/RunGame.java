import java.awt.image.BufferedImage;
import java.io.File;
//import java.util.ArrayList;
//import java.util.Iterator;


import javax.imageio.ImageIO;
//import javax.swing.JFrame;

//Warren O'Connell
//COMP 3900
//ASSIGNMENT 1



public class RunGame {

	public static int difficulty;//game difficulty affects the number of lanes in the game (global)
	public static int mode;
	private static GameFrame gameFrame; //(global game frame)
	public static final int screenWidth = 700, screenHeight = 700; //globally used variables
	//public static int highScore = 0;
	//public static Profile profile;
	
	//public static ArrayList<Integer> scores;
	//public static String[] sysList;

	public static void main(String[] args) {
		//difficulty = 1;
		//profile = new Profile();
		//scores = new ArrayList<Integer>();
		//ScoreBoard.load();
		//sysList = args;
		mode = 5;
		gameFrame = new GameFrame("Catch The Ball", screenWidth, screenHeight); //initialize game frame
		//gameFrame.maximize();


		//gameFrame.setMaximizedBounds(gameFrame.getMaximizedBounds());
		//gameFrame.setExtendedState(gameFrame.MAXIMIZED_BOTH);
		//Drawing drawing = new Drawing(gameFrame,(Graphics2D) gameFrame.getArea().getGraphics());
		//drawing.run();// display man and rocket drawings
		
		while(true){
			//System.out.println(mode);
			// create menu to get difficulty
			if(mode < 1 || mode >5 || mode == 4){ // default mode or 4 for graph
				Menu menu = new Menu(gameFrame);
				//System.out.println(mode);
				menu.run();// display screen with controls and o choose difficulty
				menu = null;
			}
			
			
			
			if(mode == 1){ // easy
				Easy easy = new Easy(gameFrame);
				easy.run();
				easy = null;
			}
			
			if(mode == 2){ // med
				Med med = new Med(gameFrame);
				med.run();
				med = null;
			}
			
			if(mode == 3){ // hard
				Hard hard = new Hard(gameFrame);
				hard.run();
				hard = null;
			}
			
//			if(mode == 4){ // graph
//				Pgraph graph = new Pgraph(gameFrame);
//				graph.run();
//				graph = null;
//			}
			
			if(mode == 5){
				//System.out.println("gt");
				ProfileChooser profchooser = new ProfileChooser(gameFrame);
				profchooser.run();
				profchooser = null;
			}
			
		}
	}

//	public static int getHighScore() {
//		
//		Iterator<Integer> i = scores.iterator();
//		if(!i.hasNext()) return 0;
//		
//		while(i.hasNext()){
//			int n = i.next();
//			if(n>highScore) highScore = n;
//		}
//		
//		return highScore;
//	}
//	
//	public static int getLastRoundScore(){
//		if(scores.size()>0) return scores.get(scores.size()-1);
//		return 0;
//	}
	
	public static boolean sshot(BufferedImage bi) throws Exception{
		
		try {

			ImageIO.write(bi, "bmp", new File("sshot.bmp"));
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
		
	
}
