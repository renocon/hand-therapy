import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;


public class ProfileManager{

	public ArrayList<Profile> profiles;
	private static ProfileManager pm;
	public Profile activeProf;
	public ArrayList<Integer> ae,am,ah; // active arrayLists
	
	@SuppressWarnings("unchecked")
	private ProfileManager(){
		profiles = (ArrayList<Profile>) loadList("META",0,0);
		if(profiles == null /*|| profiles.size() <=0*/) profiles = new ArrayList<Profile>();
		ae = null;//new ArrayList<Integer>();
		am = null;
		ah = null;
		activeProf = null;
	}
	
	public void saveProfiles(){
		saveList("META",0,0,profiles);
	}
	
	
	
	public synchronized static ProfileManager getInstance(){
		if(pm == null) pm = new ProfileManager();
		return pm;	
	}
	
	@SuppressWarnings("unchecked")
	public void activateProfile(int place){
		if(place>=profiles.size() || place < 0){
			return;
		}
		activeProf = profiles.get(place);
		ae = (ArrayList<Integer>) loadList(activeProf.uname,1,activeProf.easyNum);
		if(ae == null) ae = new ArrayList<Integer>();
		am = (ArrayList<Integer>) loadList(activeProf.uname,2,activeProf.medNum);
		if(am == null) am = new ArrayList<Integer>();
		ah = (ArrayList<Integer>) loadList(activeProf.uname,3,activeProf.hardNum);
		if(ae == null) ae = new ArrayList<Integer>();
		//saveProfiles();
	}
	
	public void deactivateProfile(){
		
		activeProf = null;
		ae = null;
		am = null;
		ah = null;
		RunGame.mode = 5;
	}
	
	public void addScore(int score,int mode){
		if(mode == 1){
			activeProf.elast = score;
			if(score> activeProf.easyhi) activeProf.easyhi=score;
			if(ae == null || ae.size()>=30){
				ae=new ArrayList<Integer>();
				activeProf.easyNum++;
				//saveList(activeProf.uname,mode,activeProf.easyNum,ae);
			}
			ae.add(score);
			saveList(activeProf.uname,mode,activeProf.easyNum,ae);
			
			
		}
		
		if(mode == 2){
			activeProf.mlast = score;
			if(score> activeProf.medhi) activeProf.medhi=score;
			if(am==null || am.size()>=30){
				am=new ArrayList<Integer>();
				activeProf.medNum++;
				//saveList(activeProf.uname,mode,activeProf.medNum,am);
			}//else{
			am.add(score);
			saveList(activeProf.uname,mode,activeProf.medNum,am);
			//}
		}
		
		if(mode == 3){
			activeProf.hlast = score;
			if(score> activeProf.hardhi) activeProf.hardhi=score;
			if(ah==null || ah.size()>=30){
				ah=new ArrayList<Integer>();
				activeProf.hardNum++;
				//saveList(activeProf.uname,mode,activeProf.hardNum,ah);
			}//else//{
			ah.add(score);
			saveList(activeProf.uname,mode,activeProf.hardNum,ah);
			//}
		}
		
		saveProfiles();
		
	}
	
	public Object loadList(String name,int mode, int fileNum){
		if(!test(name,mode, fileNum)){
			return null;}

		
			
	      try
	      {
	         FileInputStream fileIn = new FileInputStream("savedata/"+name+"_"+mode+"_"+fileNum+".ser");
	         ObjectInputStream in = null;
	         try{
	        	 in = new ObjectInputStream(fileIn);
	         }catch(Exception e){
	        	 if(in!=null)in.close();
	        	 if(mode == 0){
						saveList(name,mode,fileNum,new ArrayList<Profile>());
					}else{
						saveList(name,mode,fileNum,new ArrayList<Integer>());
					}
	        	 return loadList(name,mode,fileNum);
	         }
	         Object obj = in.readObject();
	         in.close();
	         fileIn.close();
	         System.out.println("Serialized data is loaded from savedata/"+name+"_"+mode+"_"+fileNum+".ser");
	         return obj;
	      }catch(IOException i)
	      {
	         i.printStackTrace();
	    	  
	         return null;
	      }catch(ClassNotFoundException c)
	      {
	         //System.out.println("Employee class not found");
	         c.printStackTrace();
	         return null;
	      }
	}
	
	public void saveList(String name,int mode, int fileNum, Object data){
		 if(!test(name,mode,fileNum)) return;
	      try
	      {
	         FileOutputStream fileOut =
	         new FileOutputStream("savedata/"+name+"_"+mode+"_"+fileNum+".ser");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(data);
	         out.close();
	         fileOut.close();
	         System.out.println("Serialized data is saved in savedata/"+name+"_"+mode+"_"+fileNum+".ser");
	      }catch(IOException i)
	      {
	          i.printStackTrace();
	      }
	}
	
	

	private boolean test(String name,int mode, int fileNum){
		new File("savedata/").mkdirs();
		File data = new File("savedata/"+name+"_"+mode+"_"+fileNum+".ser");
		if(!data.exists()) {
		    try {
				data.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		} 
		return true;
	}

	public void makeNewProfile(String string) {
		if(string==null)return;
		if(string.length()<1) return;
		if(find(string)) return;
		Profile p = new Profile(string);
		if(p!=null) profiles.add(p);
		saveProfiles();
		saveList(p.uname,1,0,new ArrayList<Integer>());
		saveList(p.uname,2,0,new ArrayList<Integer>());
		saveList(p.uname,3,0,new ArrayList<Integer>());
	}
	
	public boolean find(String s){
		 Iterator<Profile> pi = profiles.iterator();
		 
		 while(pi.hasNext()){
			 Profile p = pi.next();
			 if( p.uname.equals(s)){
				 return true;
			 }
		 }
		return false;
	}
}

