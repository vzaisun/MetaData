package file;
//importing packages
import java.util.*;
import java.nio.file.Files;
import java.nio.file.*;
import java.io.*;

public class metadata {
    public static void main(String args[]) throws IOException {
    	
    	String p="C:/extdir/dir1/";  //directory to store information
        File file =new File(p);
        boolean bool = file.mkdirs();  
        if(bool){  
           System.out.println("New Folder is created successfully");  
        }
        else{  
           System.out.println("Error Found!");  
        }  
    	
    	
    	
    	String path="C:\\Users\\sgvij\\Downloads\\uploads\\metadata.csv";   //where all datas exists
    	BufferedReader br = new BufferedReader(new FileReader(path));
    	//to store various features
    	ArrayList<String> list=new ArrayList<String>();
    	ArrayList<String> list1=new ArrayList<String>();
    	ArrayList<String> li=new ArrayList<String>();
    	ArrayList<String> li1=new ArrayList<String>();
    	ArrayList<String> li2=new ArrayList<String>();
    	ArrayList<String> li3=new ArrayList<String>();
    	ArrayList<String> li4=new ArrayList<String>();
    	
    	String line;
    	while ((line = br.readLine()) != null) {
    	    String[] cols = line.split(",");
    	    if(!list.contains(cols[0]+cols[1])){
    	       list.add(cols[0]+cols[1]);
    	       li.add(cols[0]);
    	       li1.add(cols[1]);
    	       li2.add(cols[2]);
    	       li3.add(cols[3]);
    	       li4.add(cols[4]);
    	    }
    	}
    	
    	//remove column headers
    	li.remove(0);
    	li1.remove(0);
    	li2.remove(0);
    	li3.remove(0);
    	li4.remove(0);
    	list.remove(0);
    	
    	
    	for (String pn : list) {
            pn=pn.replace("_cert","");
            pn=pn.replace(".pdf", "");
            list1.add(pn);
            
        }
    	
    	
  
    	String[] pathnames;
        File f = new File("C:\\Users\\sgvij\\Downloads\\uploads");
        pathnames = f.list();
        
    	int i=0;
    	for(String ele:list1) {
     	   path="C:/extdir/dir1/";
     	   path=path+"Subdir_"+ele;
     	   File fi =new File(path);
           boolean b = fi.mkdirs();  
           if(b){  
               
                 //write in command file
                 String command=path+"/COMMANDS";
                 File f1=new File(command);
                 boolean isFileCreated = f1.createNewFile(); 
               
               
                 try {  
            	      String[] l=new String[5];
            	      
            	      
            	      if(i<list.size()) {
            	      FileWriter myWriter = new FileWriter(command);
            	      myWriter.write("XECM_UPLOAD_FILE "+li1.get(i));
            	      myWriter.write("\nXECM_CONTENT_TYPE "+li2.get(i));
            	      myWriter.write("\nXECM_USER "+li.get(i));
            	      myWriter.write("\nXECM_DOC_TYPE "+li3.get(i));
            	      myWriter.write("\nXECMSF_DOC_META  "+li4.get(i));
            	      myWriter.close();
            	      
            	      }
            	      for (String pathname : pathnames) {
                          if(pathname.contains(li1.get(i))) {
                           String initial="C:\\Users\\sgvij\\Downloads\\uploads\\"+pathname;
                       	   Path temp = Files.move
                       		        (Paths.get((initial)),
                       		        Paths.get(path+"/"+pathname));
                       	   if(temp != null)
                              {
                                  System.out.println(li1.get(i)+"moved successfully");
                                  
                              }
                              else
                              {
                                  System.out.println("Failed to move the file");
                              }
                          }
                          
                          
                          }
            	      
            	      
            	      i+=1;
                                
            	      
                 }
            	     
                  catch (IOException e) {
            	      System.out.println("An error occurred.");
            	      e.printStackTrace();
            	    }
               
               
               
               //create log file
               String log=path+"/LOG";
               File f2=new File(log);
               boolean isFileCreated1 = f2.createNewFile(); 
               
               
               
           }
           
       }
    	
    
    }
}
