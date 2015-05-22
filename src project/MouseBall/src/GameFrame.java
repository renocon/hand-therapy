import java.awt.Canvas;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class GameFrame extends JFrame
{
	private Component area;  
	private Canvas canvas; 
	
	/**
	 * Constructor
	 * @param windowTitle
	 */
	public GameFrame(String windowTitle, int SCREEN_WIDTH, int SCREEN_HEIGHT)
	{
		super(windowTitle); 
		
		JPanel panel = (JPanel) getContentPane(); 
		//panel.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		panel.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		panel.setLayout(null);
		
		setBounds(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
		
		
		canvas = new Canvas(); 
		area = canvas; 
		area.setSize(panel.getPreferredSize()); 
		panel.add(area); 
        
		setIgnoreRepaint(true);
        pack(); 
		setVisible(true);

		this.setResizable(true);
		setExtendedState(getExtendedState()|JFrame.MAXIMIZED_BOTH);
		//panel.setPreferredSize();
		//panel.setMinimumSize(this.getBounds().getSize());
		//this.setResizable(false); 
		//canvas.createBufferStrategy(2);  //need to explain why 2 
		
		//set up Listeners
        addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
        
        //area.addKeyListener(KeyboardInputService.getInstance()); 
         
                
		area.requestFocus();
	}
	
//	public void maximize(){
//		if(getMaximizedBounds()!=null)setBounds(getMaximizedBounds());
//	}
	
	public Component getArea()
	{
		return area; 
	}
	 
	 
}
