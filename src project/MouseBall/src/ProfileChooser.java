import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Ellipse2D;
//import java.util.ArrayList;

//import javax.swing.JFrame;


public class ProfileChooser implements Runnable{

	private int screenWidth;
	private int screenHeight;
	private GameFrame gameFrame;
	private Ellipse2D circle; // selector for difficulty setting
	private ProfileChooserInputService pcis;

	//for double buffering
    private Image dbImage;
    private Graphics2D dbGraphics;
    private int difficulty;
    //private int circleY;
    public state s;
    private int option=0;
    private ProfileManager pm;
    public StringBuilder newName;
    //public char[] nName;
    //public int pos;
    //public ArrayList<String> ProfileList;
	
	/**
	 * Constructor
	 */
	public ProfileChooser(GameFrame gf) {
		//circleY=155;
		//System.out.println("tip");
		//nName = new char[21]; pos =0;
		//newName = "";
		newName = new StringBuilder(50);
		screenHeight = RunGame.screenHeight;
		screenWidth = RunGame.screenWidth;
		gameFrame = gf;
		pcis = new ProfileChooserInputService(this);
		gameFrame.getArea().addKeyListener(pcis); // add listener
		circle = new Ellipse2D.Float(95, 155, 10, 10);
		s = state.CHOOSING;
		pm = ProfileManager.getInstance();
		if(pm.profiles ==null || pm.profiles.size()<1) s = state.MAKING;
	}

	/**
	 * Run method. Contains game loop.
	 */
	public void run() {
		//RunGame.mode = 0;
		
		while (RunGame.mode==5) { //stay in menu until difficulty is selected
			update();
			draw((Graphics2D) gameFrame.getArea().getGraphics());

			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				System.out.println(e.getMessage());
			}
			
		}
		gameFrame.getArea().removeKeyListener(pcis);
	}

	/**
	 * Update
	 */
	private void update() {
		
		//circle.setFrame(195, circleY, 10, 10); //move circle to corresponding difficulty choice
	}


	public void down(){
		option++;
		if(option>=pm.profiles.size()) option = pm.profiles.size()-1;
	}
	
	public void up(){
		option--;
		if(option <0) option =0;
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
		
		g2d.drawString("press ESC to quit at any point", 10, 20);
		
		
		if(s == state.CHOOSING){
			g2d.drawString("Press 'N' to create a new profile", 10, 35);
			g2d.drawString("Press Enter to Choose a Profile", 10, 50);
			g2d.drawString("Use 'UP' and 'DOWN' to navigate profiles", 10, 65);
			g2d.fill(circle);
			int y = 0;
			if(pm.profiles!=null){
				for(int x = option; x<=option+15;x++){
			
				if(x>=pm.profiles.size()) break;
				if(pm.profiles.get(x).uname!=null)g2d.drawString(pm.profiles.get(x).uname,110,150+(15*(y+1)));
				y++;
				}
			}
		}
		
		if(s == state.MAKING){
			g2d.drawString("Press Enter Confirm", 10, 35);
			g2d.drawString("Choose Profile Name (No Spaces or Special Characters Allowed):> "+newName, 10, 50);
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
		//circleY = y;
	}
	
	public void addChar(String c){
		if (newName.length()>50) return;
		
		newName.append(c);
	
		//System.out.println(newName+" bs");

	}
	public void backChar(){

		if(newName.length()>0) newName.setLength(newName.length()-1);

	}
	
	public enum state{
		CHOOSING,
		MAKING
	}

	public void chooseState() {
		s =state.CHOOSING;
		if (pm.profiles ==null || pm.profiles.size()<1) makeNewState();
	}
	public void makeNewState(){
		s = state.MAKING;
		newName = new StringBuilder(50);
	}

	public void confirm() {
		//RunGame.mode=0;
		//pm.makeNewProfile(newName.toString());
		//chooseState();
	
		
		String newname = Profile.sanitize(newName.toString());
		
		if(newname == null || newname.length()<1){
			newName = new StringBuilder(50);
			makeNewState();
		}
		if(!pm.find(newname)){
			pm.makeNewProfile(newname);
			chooseState();
			pm.saveProfiles();
			return;
		}
		else{ 
			newName = new StringBuilder(50);
			makeNewState();
		}
		// TODO Auto-generated method stub
		
	}

	public boolean emptyString(){
		if(newName.length()<1) return true;
		return false;
	}
	
	public void logIn(){
		pm.activateProfile(option);
		RunGame.mode =0;
	}
}
