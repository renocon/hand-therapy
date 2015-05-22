import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;


public class MouseInputService implements MouseMotionListener{

	public static PlayMode pm;
	
	
	public MouseInputService(PlayMode p){
		pm = p;
	}
	
	

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		pm.mouse  =e.getPoint();
	}



	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		pm.mouse  =e.getPoint();
	}

}
