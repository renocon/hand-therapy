import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class MenuInputService implements KeyListener {

	private Menu menu;
	private int choice; // difficulty selected
	private int gdiff; // for choosing which set of graph results to display
	// easy = 1;
	// medium = 2;
	// hard = 3;
	// progress = 4;
	// change user
	
	public MenuInputService(Menu menu) {
		this.menu =  menu;
		choice = 1;
		gdiff = 0;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		//not used
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		if(RunGame.mode == 0){
			if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
				menu.logOut();
			}
			
			if(RunGame.mode==0 && choice!=4){
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
					RunGame.mode= choice; //confirm pick difficulty
					//gdiff = 1;
				}

			if(e.getKeyCode() == KeyEvent.VK_UP){
				choice--;
				choice = (choice%5);
				if (choice == 0)choice = 5; //change associated integer for difficulty
			}
			
			if(e.getKeyCode() == KeyEvent.VK_DOWN){
				choice++;
				choice = (choice%5);
				if (choice == 0)choice = 5; //change associated integer for difficulty
			}
	
			menu.setCircleY(310 + (15*choice));//move circle next to option
			menu.setCircleX(195);
		}
		
		if(RunGame.mode == 4){
			
			if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
				RunGame.mode = 0;
				menu.initMenu();
				choice = 1; 
				gdiff =0;
			}
			
			

			if(e.getKeyCode() == KeyEvent.VK_UP){
				gdiff--;
				gdiff = (gdiff%4);
				if (gdiff == 0)gdiff = 3; //change associated integer for difficulty
			}
			
			if(e.getKeyCode() == KeyEvent.VK_DOWN){
				gdiff++;
				gdiff = (gdiff%4);
				if (gdiff == 0)gdiff = 1; //change associated integer for difficulty
			}
	
			menu.setCircleY(355 + (15*gdiff));//move circle next to option
			menu.setCircleX(325);
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(gdiff>0){
			if(e.getKeyCode() == KeyEvent.VK_ENTER){
				//RunGame.mode = choice; //confirm pick difficulty
				menu.setgdiff(gdiff);
			}
			
			if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
				//RunGame.mode = choice; //confirm pick difficulty
				menu.initMenu();
			}	
		}
		
		
		if(RunGame.mode==0 && choice==4){
			if(e.getKeyCode() == KeyEvent.VK_ENTER){
				RunGame.mode= choice; //confirm pick difficulty
				gdiff = 1;
				menu.setCircleX(325);
			}
			if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
				//RunGame.mode = choice; //confirm pick difficulty
				menu.initMenu();
			}	
		}
		
		
		
	}


}
