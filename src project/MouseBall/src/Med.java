import java.awt.geom.Ellipse2D;



public class Med extends PlayMode{

	public Med(GameFrame gf) {
		super(gf);
		mode = 2;
		size = 25;
		ball = new Ellipse2D.Double(rand.nextInt(screenWidth-size), rand.nextInt(screenHeight-size), size, size);
	}

	@Override
	public void update() {
		if (ball.contains(mouse)){
			score++;
			ball.setFrame(rand.nextInt(screenWidth-size), rand.nextInt(screenHeight-size), size, size);
		}
		
		timeLeft = (timeLimit - (System.currentTimeMillis() - start)/1000);
		if(timeLeft<=0){
			endRound();
		}
	}




}
