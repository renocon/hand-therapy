import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class ProfileChooserInputService implements KeyListener {

	ProfileChooser pc;
	
	public ProfileChooserInputService(ProfileChooser profileChoose) {
		pc = profileChoose;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
			if(pc.s == ProfileChooser.state.CHOOSING){
				System.exit(0);
			}else{
				pc.chooseState();
			}
		}
		
		
		if(pc.s == ProfileChooser.state.CHOOSING){
			if(e.getKeyCode() == KeyEvent.VK_UP){
				pc.up();
			}
			if(e.getKeyCode() == KeyEvent.VK_DOWN){
				pc.down();
			}
			
			if(e.getKeyCode()==KeyEvent.VK_ENTER){
				pc.logIn();
			}
		}
		
		else{
			if(e.getKeyCode()>=KeyEvent.VK_A && e.getKeyCode()<=KeyEvent.VK_Z)pc.addChar(e.getKeyChar()+"");
			if(e.getKeyCode() == KeyEvent.VK_SPACE)pc.addChar("_");
			if(e.getKeyCode()==KeyEvent.VK_BACK_SPACE) pc.backChar();
			if(e.getKeyCode()==KeyEvent.VK_ENTER) pc.confirm();
//			if(e.getKeyCode()==KeyEvent.VK_ESCAPE){
//				if(pc.emptyString())System.exit(0);
//				else{
//					
//				}
//			}
			
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_N){
			if(pc.s == ProfileChooser.state.CHOOSING) pc.makeNewState();
		}
		
	}

}
