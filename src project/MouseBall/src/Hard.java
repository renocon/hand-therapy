import java.awt.Point;
import java.awt.geom.Ellipse2D;


public class Hard extends PlayMode{
	
	private int motion;
	private Point loc;
	private int prevMot;
	
	//for linear motion
	private int gradX,gradY;
	
	public Hard(GameFrame gf) {
		super(gf);
		mode = 3;
		size = 30;
		ball = new Ellipse2D.Double(rand.nextInt(screenWidth-size), rand.nextInt(screenHeight-size), size, size);
		loc = new Point();
		loc.x = (int) ball.getX();
		loc.y = (int) ball.getY();
		gradX = rand.nextInt(5);
		gradY = rand.nextInt(5);
		motion = rand.nextInt(2);
		prevMot=0;
	}

	@Override
	public void update() {
		if (ball.contains(mouse)){
			score++;
			ball.setFrame(rand.nextInt(screenWidth-size), rand.nextInt(screenHeight-size), size, size);
			motion = rand.nextInt(5);
			loc.x = (int) ball.getX();
			loc.y = (int) ball.getY();
			prevMot=0;
		}
		
		timeLeft = (timeLimit - (System.currentTimeMillis() - start)/1000);
		if(timeLeft<=0){
			endRound();
		}
		
		switch(motion){
			
			 case 1://linear
				 if(prevMot!=1){
					gradX = rand.nextInt(5);
					gradY = rand.nextInt(5);
					if(rand.nextBoolean())gradX*=-1;
					if(rand.nextBoolean())gradY*=-1;
					prevMot = 1;
				 }
				 if(loc.x<=0 || loc.x>=screenWidth-size) gradX*=-1;
				 if(loc.y<=0 || loc.y>=screenHeight-size) gradY*=-1;
				 loc.x+=gradX;
				 loc.y+=gradY;
				 break;
				 
//			 case 2://sine
//				 if(prevMot!=2){
//					 if(rand.nextBoolean()){
//						 loc.x=rand.nextInt(screenWidth-200)+200;
//						 loc.y=0;
//					 }
//					 else{
//						 loc.y=rand.nextInt(screenHeight-200)+200;
//						 loc.x=0;
//					 }
//					 prevMot = 2;
//				 }
//				 
//				 break;
//				 
//			 case 3://cosine
//				 if(prevMot!=3){
//					 if(rand.nextBoolean()){
//						 loc.x=rand.nextInt(screenWidth-200)+200;
//						 loc.y=0;
//					 }
//					 else{
//						 loc.y=rand.nextInt(screenHeight-200)+200;
//						 loc.x=0;
//					 }
//					 prevMot = 3;
//				 }
//				 break;	 
		
			 default:break;
		}
		
		ball.setFrame(loc.x, loc.y, size, size);
	}


}
