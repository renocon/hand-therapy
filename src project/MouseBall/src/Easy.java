import java.awt.geom.Ellipse2D;




public class Easy extends PlayMode{

	public Easy(GameFrame gf) {
		super(gf);
		mode = 1;
		size = 50;
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
