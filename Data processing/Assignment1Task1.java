package com.santosh;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

public class Assignment1Task1 {
	public static void main(String args[]){
		PrintWriter out = null;
		BufferedWriter writeBufferWriter=null;
		try{
			String category="";
			String fileName=args[1];
			FileWriter writeFile=new FileWriter(fileName);
			writeBufferWriter=new BufferedWriter(writeFile);
			out = new PrintWriter(writeBufferWriter);
			String dir=args[0];
			File folder = new File(dir);
			File[] listOfFiles = folder.listFiles();
			
			if(listOfFiles!=null && listOfFiles.length!=0){
				for(int i = 0; i < listOfFiles.length; i++){
					if(listOfFiles[i].isDirectory()) {
						String newdir=dir+"/"+listOfFiles[i].getName();
						category=listOfFiles[i].getName();
						File folder1 = new File(newdir);
						File[] listOfFiles1 = folder1.listFiles();
						if(listOfFiles1!=null && listOfFiles1.length!=0){
							for(int i1 = 0; i1 < listOfFiles1.length; i1++){
								if(listOfFiles1[i1].isFile()) {
									
									String textfileName=newdir+"/"+listOfFiles1[i1].getName();
									File inputfile=new File(textfileName);
									FileReader fileReader=new FileReader(inputfile);
									BufferedReader bufferedFileReader=new BufferedReader(fileReader);
									boolean checkbodylinestart=false;
									String sender="",organization="",subject="",body="";
								    String readLine="";								    
								    while((readLine=bufferedFileReader.readLine())!= null){
								    	if(readLine.trim().length()!=0){
								    		if(readLine.trim().indexOf("From:")!=-1){
								    			sender=readLine.replace("From:","");
								    		}
								    		if(readLine.trim().indexOf("Subject:")!=-1){  								    			
								    			subject=readLine.replace("Subject:","");
								    		}
								    		if(readLine.trim().indexOf("Organization:")!=-1){
								    			organization=readLine.replace("Organization:","");
								    		}
								    		if(checkbodylinestart==true){
								    			if(readLine.trim().indexOf("archive-name")==-1 && readLine.trim().indexOf("Archive-name")==-1 
								    					&& readLine.trim().indexOf("Last-modified")==-1 && readLine.trim().indexOf("Version")==-1
								    					&& readLine.trim().indexOf("article")==-1 && readLine.trim().indexOf("Article-ID")==-1
								    					&& readLine.trim().startsWith("Keywords")==false && readLine.trim().startsWith("keywords")==false
								    					&& readLine.trim().indexOf("Lines")==-1){
								    				body+=" "+readLine.replaceAll("\\s+"," ");
								    			}
								    		}
								    		if(readLine.trim().indexOf("Lines")!=-1){
								    			checkbodylinestart=true;
								    		}								    		
								    	}
								    }
								    
								    out.println(category.trim()+"\t"+sender.trim()+"\t"+subject.trim()+"\t"+organization+"\t"+body);
								}								
							}
						}
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				out.close();
				writeBufferWriter.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
}
