import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
//import java.awt.image.BufferedImage;


public class KeyInputService implements KeyListener{

	public static PlayMode pm;
	
	
	public KeyInputService(PlayMode p){
		pm = p;
		
	}
	
	

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(KeyEvent.VK_ESCAPE == e.getKeyCode()){
			//RunGame.mode = -1;
			pm.quit();
		}
		
		if(KeyEvent.VK_Q == e.getKeyCode()){
			if(pm instanceof Pgraph){
				try {
					//RunGame.sshot((BufferedImage) pm.dbImage);
					((Pgraph) pm).sshot();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(pm instanceof Pgraph){
			if(e.getKeyCode() == KeyEvent.VK_LEFT){
				pm.prevGraph();
			}
			
			if(e.getKeyCode() == KeyEvent.VK_RIGHT){
				pm.nextGraph();
			}
		}
		
	}





}
