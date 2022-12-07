package file;
import java.util.*;
import java.nio.file.*;
import java.io.*;

public class metadata_twoCountries {
	public static void main(String args[]) throws IOException {
		Scanner sc=new Scanner(System.in);
		String p="C:/extdir/dir1/";  //directory to store information
        File file =new File(p);
        boolean bool = file.mkdirs();  
        if(bool){  
           System.out.println("New Folder is created successfully");  
        }
        else{  
           System.out.println("Error Found!");  
        }  
    	
        
        String[] inputs;
        File file_input = new File("C:\\Metadata");
        inputs = file_input.list();
        ArrayList<String> country_list=new ArrayList<String>();
        for(String item:inputs) {
        	country_list.add(item);
        }
        
        
        boolean i=true;
        while(i) {
        	
        	System.out.println("Country list");
            System.out.println(country_list);
            
        	System.out.println("Enter country :");
        	String country=sc.next();
        	if(country_list.contains(country)) {
        		String path="C:\\Metadata\\"+country+"\\metadata_"+country+".csv";   //where all datas exists
            	BufferedReader br = new BufferedReader(new FileReader(path));
            	//to store various features
            	ArrayList<String> list=new ArrayList<String>();
            	ArrayList<String> list1=new ArrayList<String>();
            	ArrayList<String> li=new ArrayList<String>();
            	ArrayList<String> li1=new ArrayList<String>();
            	ArrayList<String> li2=new ArrayList<String>();
            	ArrayList<String> li3=new ArrayList<String>();
            	ArrayList<String> li4=new ArrayList<String>();
            	System.out.println(list);
            	
            	String line;
            	while ((line = br.readLine()) != null) {
            	    String[] cols = line.split(",");
            	  
            	       list.add(cols[0]+cols[1]);
            	       li.add(cols[0]);
            	       li1.add(cols[1]);
            	       li2.add(cols[2]);
            	       li3.add(cols[3]);
            	       li4.add(cols[4]);
            	    
            	}
            	
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
                File f = new File("C:\\Metadata\\"+country);
                pathnames = f.list();
                
                
                
                int index=0;
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
                    	      
                    	      
                    	      if(index<list.size()) {
                    	      FileWriter myWriter = new FileWriter(command);
                    	      myWriter.write("XECM_UPLOAD_FILE "+li1.get(index));
                    	      myWriter.write("\nXECM_CONTENT_TYPE "+li2.get(index));
                    	      myWriter.write("\nXECM_USER "+li.get(index));
                    	      myWriter.write("\nXECM_DOC_TYPE "+li3.get(index));
                    	      myWriter.write("\nXECMSF_DOC_META  "+li4.get(index));
                    	      myWriter.close();
                    	      
                    	      }
                    	      for (String pathname : pathnames) {
                                  if(pathname.contains(li1.get(index))) {
                                   String initial="C:\\Metadata\\"+country+"\\"+pathname;
                               	   Path temp = Files.move
                               		        (Paths.get((initial)),
                               		        Paths.get(path+"/"+pathname));
                               	   if(temp != null)
                                      {
                                          System.out.println(li1.get(index)+"moved successfully");
                                          
                                      }
                                      else
                                      {
                                          System.out.println("Failed to move the file");
                                      }
                                  }
                                  
                                  
                                  }
                    	      
                    	      
                    	      index+=1;
                                        
                    	      
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
            	country_list.remove(country);
        	
        	
        	}
        	
        	
        	else {
        		System.out.println("Doesnt exist or program already done for that country");
        	}
        	
        	System.out.println("Do you want to continue for another country(Y/N):");
        	char answer=sc.next().charAt(0);
        	if(answer=='N') {
        		i=false;
        	}
        	
        }
        
        
		
		
		
	   }
	
	}
	