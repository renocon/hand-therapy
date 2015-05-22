//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.ObjectInputStream;
//import java.io.ObjectOutputStream;
//import java.io.Serializable;
//import java.util.ArrayList;
//
//
//@SuppressWarnings("serial")
public class ScoreBoard{
//	public static File game = null; 
//
//	private static boolean test(String name,int mode, int fileNum){
//		File data = new File(name+".ser");
//		if(!data.exists()) {
//		    try {
//				data.createNewFile();
//			} catch (IOException e) {
//				e.printStackTrace();
//				return false;
//			}
//		} 
//		return true;
//	}
//	
//	public static void save (String name,int mode,int fileNum)
//	   {
//	      if(!test(name,mode,fileNum)) return;
//	      try
//	      {
//	         FileOutputStream fileOut =
//	         new FileOutputStream(name+".ser");
//	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
//	         out.writeObject(RunGame.scores);
//	         out.close();
//	         fileOut.close();
//	         System.out.println("Serialized data is saved in scores.ser");
//	      }catch(IOException i)
//	      {
//	          i.printStackTrace();
//	      }
//	   }
//	
//	@SuppressWarnings("unchecked")
//	public static void load(String name,int mode, int fileNum)
//	   {
//		if(!test(name,mode, fileNum)) return;
//	      try
//	      {
//	         FileInputStream fileIn = new FileInputStream(name+".ser");
//	         ObjectInputStream in = null;
//	         try{
//	        	 in = new ObjectInputStream(fileIn);
//	         }catch(Exception e){
//	        	 if(in!=null)in.close();
//	        	 return;
//	         }
//	         RunGame.scores = (ArrayList<Integer>) in.readObject();
//	         in.close();
//	         fileIn.close();
//	         System.out.println("Serialized data is loaded from "+name+".ser\n"+RunGame.scores.size()+" elements");
//	      }catch(IOException i)
//	      {
//	         i.printStackTrace();
//	    	  
//	         return;
//	      }catch(ClassNotFoundException c)
//	      {
//	         //System.out.println("Employee class not found");
//	         c.printStackTrace();
//	         return;
//	      }
//	      
//	    }
//
}
