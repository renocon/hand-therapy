import java.io.Serializable;

public class Profile implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3280650536908918812L;
	public static final int fileVersion = 1;
	public String uname;
	
	public int easyNum,medNum,hardNum,easyhi,medhi,hardhi,elast,mlast,hlast;// sets of 30, hi scores, last scores
	private int shotID;
	
	public Profile(String name){
		name = sanitize(name);
		uname = name;
		easyNum = medNum = hardNum = easyhi = medhi = hardhi = elast = mlast = hlast = shotID = 0;
	}


	public static String sanitize(String name) {
		if(name == null) return null;
		name = name.toUpperCase();
		name = name.trim();
		if(name.length()<1) return null;
		if(name.length()>50)name = name.substring(0,50);
		
		return name;
	}


	public int getShotID() {
		return shotID++;
	}
	
	
	
}
