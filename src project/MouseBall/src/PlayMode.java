import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.geom.Ellipse2D;
import java.util.Random;


public abstract class PlayMode implements Runnable{

	public abstract void update();
	//public abstract void draw(Graphics2D g2d);
	//public abstract void run();
	
	public int score;
	public Ellipse2D ball;
	public int size;
	protected GameFrame frame;
	protected int screenWidth;
	protected int screenHeight;
	protected KeyInputService kis;
	protected MouseInputService mis;
	protected Image dbImage;
	protected Graphics2D dbGraphics;
	protected int mode;
	public Point mouse;
	protected Random rand;
	protected long start,timeLeft;
	protected int timeLimit;
	protected ProfileManager pm;
	protected int hi,last;
	public String diffString;
	
	
	
	public PlayMode(GameFrame gf){
		timeLimit = 60;
		screenWidth = RunGame.screenWidth;
		screenHeight = RunGame.screenHeight;
		score = 0;
		rand = new Random();
		mouse = new Point();
		mouse.x=-1;
		mouse.y=-1;
		frame  = gf;
		kis = new KeyInputService(this);
		mis = new MouseInputService(this);
		frame.getArea().addKeyListener(kis);
		frame.getArea().addMouseMotionListener(mis);
		start = System.currentTimeMillis();
		timeLeft=60;
		pm = ProfileManager.getInstance();
		
		if(RunGame.mode == 1){
			hi = pm.activeProf.easyhi;
			last = pm.activeProf.elast;
			diffString = "Beginner";
		}
		if(RunGame.mode == 2){
			hi = pm.activeProf.medhi;
			last = pm.activeProf.mlast;
			diffString = "Intermediate";
		}
		if(RunGame.mode == 3){
			hi = pm.activeProf.hardhi;
			last = pm.activeProf.hlast;
			diffString = "Advanced";
		}
	}
	
	

	public void quit() {
		score = 0;
		
		frame.getArea().removeKeyListener(kis);
		frame.getArea().removeMouseMotionListener(mis);
		mis = null;
		kis = null;
		RunGame.mode = 0;
	}
	
	public void endRound(){
		pm.addScore(score, mode);
		quit();
	}
	
	public void run() {

		while (RunGame.mode==mode) {
			update();
			draw((Graphics2D) frame.getArea().getGraphics());
			
			
			
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				System.out.println(e.getMessage());
			}
		}
		quit();
	}
	
	public void draw(Graphics2D g2d) {
		dbImage = frame.createImage(screenWidth,screenHeight);
		dbGraphics = (Graphics2D) dbImage.getGraphics();
		drawComponent(dbGraphics);
		g2d.drawImage(dbImage, 0, 0, frame);
	}

	public Image getDbImage() {
		return dbImage;
	}



	//draw background
	public void drawStage(Graphics2D g2d) {
		g2d.setColor(Color.blue);
		g2d.fillRect(0, 0, screenWidth, screenHeight);
		
		

	}

	protected void drawComponent(Graphics2D g2d) {
		drawStage(g2d);
		
		g2d.setColor(Color.yellow);
		g2d.fill(ball);
		
		Color c = g2d.getColor();
		g2d.setColor(Color.white);
		g2d.drawString("Score: "+score,5,15);
		g2d.drawString("Time Left: "+timeLeft,5,30);
		g2d.drawString("Last Score: "+last,5,45);
		g2d.drawString("Best Score: "+hi,5,60);
		g2d.drawString(diffString,5,75);
		g2d.setColor(c);
	}



	public void prevGraph() {
		// TODO Auto-generated method stub
		
	}



	public void nextGraph() {
		// TODO Auto-generated method stub
		
	}
	
}
