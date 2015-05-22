import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import javax.imageio.ImageIO;


public class Pgraph extends PlayMode{

	public ProfileManager pm;
	public int difficulty;
	public int gnum;
	public int max;
	public ArrayList<Integer> temp;
	public Color prev;
	
	@SuppressWarnings("unchecked")
	public Pgraph(GameFrame gf, int diff) {
		//diffString="";
		super(gf);
		mode = 4;
		pm = ProfileManager.getInstance();
		difficulty =diff;
		gnum = -1;
		
		//if(pm.activeProf==null) return;
		if(difficulty == 1){
			gnum = pm.activeProf.easyNum;
			diffString = "Beginner";
			hi = pm.activeProf.easyhi;
			last = pm.activeProf.elast;
		}
		if(difficulty == 2){
			gnum = pm.activeProf.medNum;
			diffString = "Intermediate";
			hi = pm.activeProf.medhi;
			last = pm.activeProf.mlast;
		}
		if(difficulty == 3){
			gnum = pm.activeProf.hardNum;
			diffString = "Advanced";
			hi = pm.activeProf.hardhi;
			last = pm.activeProf.hlast;
		}
		max = gnum;
		temp = (ArrayList<Integer>) pm.loadList(pm.activeProf.uname, difficulty, gnum);
		if(temp == null){
			System.exit(0);
		}
		
	}



	@Override
	public void update() {
		//no updates...
	}
	

	
	protected void drawComponent(Graphics2D g2d) {
		//drawStage(g2d);
		Color c = g2d.getColor();
		g2d.setColor(Color.white);
		g2d.fillRect(0, 0, screenWidth, screenHeight);
		
		
		//g2d.setColor(Color.yellow);
		//g2d.fill(ball);
		
		
		g2d.setColor(Color.black);
		g2d.drawString("Progress report",5,15);
		g2d.drawString("USER: "+pm.activeProf.uname, 5, 30);
		
		g2d.drawString("Press 'Q' to take a screenshot", 400, 15);
		g2d.drawString("Press 'LEFT' & 'RIGHT' to browse game sets", 400, 30);
		
		g2d.drawLine(100, 50, 100, 650);
		g2d.drawLine(100, 650, 850, 650);
		g2d.drawString("score", 30, 80);
		g2d.drawString("game set: "+gnum,680,690);
		
		//g2d.drawString("Time Left: "+timeLeft,5,30);
		g2d.drawString("Last Score: "+last,250,30);
		g2d.drawString("Best Score: "+hi,250,15);
		if(diffString!=null)g2d.drawString("Difficulty: "+diffString,105,15);
		//g2d.drawString("0",85,670);
		
		g2d.setColor(Color.gray);
		for(int x=0;x<=30;x++){
			int val = 100+ x*25;
			g2d.drawLine(val, 50, val, 650);
			g2d.drawString(""+x,val,670);
		}
		
		for(int x=0;x<=20;x++){
			int val = 650 - x*30;
			g2d.drawLine(100, val, 850, val);
			g2d.drawString(""+x*5,70,val);
		}
		
		int ax,ay,bx,by;
		
		
		
		Stroke gtd = g2d.getStroke();
		g2d.setColor(new Color(0,100,0));//green
		g2d.setStroke(new BasicStroke(2));
		Iterator<Integer> scores = temp.iterator() ;
		ax=0;
		ay = 0;
		while(scores.hasNext()){
			bx=ax+1;
			by=scores.next();
			
			g2d.drawLine((int)100+ax*25,(int)(650-(ay*6)), (int)100+bx*25, (int)(650-(by*6)));
			
			ax=bx;
			ay=by;
		}
		g2d.setStroke(gtd);
		g2d.setColor(c);
	}
	
	@SuppressWarnings("unchecked")
	public void prevGraph() {
		gnum--;
		if(gnum<0) gnum = max;
		temp = (ArrayList<Integer>) pm.loadList(pm.activeProf.uname, difficulty, gnum);
	}



	@SuppressWarnings("unchecked")
	public void nextGraph() {
		gnum++;
		gnum=gnum%(max+1);
		temp = (ArrayList<Integer>) pm.loadList(pm.activeProf.uname, difficulty, gnum);
	}
	
	public boolean sshot() throws Exception{
		//pm.sshot((BufferedImage) dbImage)
		try {
			new File("screenshots/").mkdirs();
			String fileName = "screenshots/"+pm.activeProf.uname+"_";
			
			if(difficulty == 1) fileName+= "easy_";
			if(difficulty == 2) fileName+= "intermediate_";
			if(difficulty == 3) fileName+= "advanced_";
			
			fileName=fileName+gnum+"_"+pm.activeProf.getShotID()+"";
			
			fileName+=".bmp";
			ImageIO.write((BufferedImage) dbImage, "bmp", new File(fileName));
			System.out.println("Screen shot saved to "+fileName);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

}
