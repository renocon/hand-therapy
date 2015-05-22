import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;


public class Menu implements Runnable{
	//set up screen for choosing difficulty
	private int screenWidth;
	private int screenHeight;
	private GameFrame gameFrame;
	private Ellipse2D circle; // selector for difficulty setting
	private MenuInputService mis;

	//for double buffering
    private Image dbImage;
    private Graphics2D dbGraphics;
    private int difficulty;
    private int gdiff;
    private int circleY,circleX;
    public ArrayList<String> ProfileList;
    public ProfileManager pm;
	
	/**
	 * Constructor
	 */
	public Menu(GameFrame gf) {
		pm = ProfileManager.getInstance();
		gdiff=0;
		circleY = 325;
		circleX = 195;
		screenHeight = RunGame.screenHeight;
		screenWidth = RunGame.screenWidth;
		gameFrame = gf;
		mis = new MenuInputService(this);
		gameFrame.getArea().addKeyListener(mis); // add listener
		circle = new Ellipse2D.Float(circleX, circleY, 10, 10);
	}

	/**
	 * Run method. Contains game loop.
	 */
	public void run() {
		RunGame.mode = 0;
		
		while (RunGame.mode==0 || RunGame.mode>5 || RunGame.mode == 4) { //stay in menu until difficulty is selected or 4 for graph
			update();
			draw((Graphics2D) gameFrame.getArea().getGraphics());
			if(gdiff>0){
				gameFrame.getArea().removeKeyListener(mis);

				Pgraph graph = new Pgraph(gameFrame,gdiff);
				graph.run();
				graph = null;
				
				gdiff = 0;
				difficulty = 1;
				gameFrame.getArea().addKeyListener(mis);
				}
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				System.out.println(e.getMessage());
			}
			
		}
		gameFrame.getArea().removeKeyListener(mis);
	}

	/**
	 * Update
	 */
	private void update() {
		
		circle.setFrame(circleX, circleY, 10, 10); //move circle to corresponding difficulty choice
	}


	
	public void draw(Graphics2D g2d){
		dbImage = gameFrame.createImage(this.getScreenWidth(),this.getScreenHeight());
	    dbGraphics = (Graphics2D)dbImage.getGraphics();
		drawComponent(dbGraphics);
		g2d.drawImage(dbImage, 0, 0, gameFrame);
	}
	
	//draw background
	public void drawStage(Graphics2D g2d){
		g2d.setColor(Color.black);
		g2d.fillRect(0, 0, screenWidth, screenHeight);
		g2d.setColor(Color.white);
		
	}
	
	private void drawComponent(Graphics2D g2d) {
		drawStage(g2d);
		g2d.setColor(Color.white);
		g2d.drawString("Choose Difficulty: ", 210, 305);
		g2d.drawString("Beginner", 210, 335);
		g2d.drawString("Intermediate", 210, 350);
		g2d.drawString("Advanced", 210, 365);
		g2d.drawString("Progress Report", 210, 380);
		g2d.drawString("Change Profile",210,395);
		g2d.drawString("press ESC to quit at any point", 10, 100);
		//g2d.drawString("High Score: "+RunGame.getHighScore(), 210, 425);
		//g2d.drawString("Last Round: "+RunGame.getLastRoundScore(), 210, 440);
		g2d.drawString("Take the mouse to the ball using appropriate hand", 10, 20);
		if(ProfileManager.getInstance().activeProf!=null)
			g2d.drawString("Welcome "+ProfileManager.getInstance().activeProf.uname+"!", 10, 35);
		g2d.drawString("Enjoy!!! :)", 10, 50);
		g2d.fill(circle);
		
		if(RunGame.mode == 4){
			g2d.drawString("Beginner", 340, 380);
			g2d.drawString("Intermmediate", 340, 395);
			g2d.drawString("Advanced", 340, 410);
			
		}
	}

	public int getScreenWidth() {
		return screenWidth;
	}

	public int getScreenHeight() {
		return screenHeight;
	}

	public int getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(int d) {
		this.difficulty = d;
	}
	
	public void setCircleY(int y){
		circleY = y;
	}
	
	public void setCircleX(int x){
		circleX = x;
	}
	
	public void logOut(){
		pm.deactivateProfile();
		RunGame.mode=5;
	}

	public void setgdiff(int diff) {
		gdiff = diff;
		
	}

	public void initMenu() {
		// TODO Auto-generated method stub
		RunGame.mode = -1;
	}
}
