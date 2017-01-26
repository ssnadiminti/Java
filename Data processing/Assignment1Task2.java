package com.santosh;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Assignment1Task2 {
	public static void main(String args[]){
		PrintWriter out = null;
		BufferedWriter writeBufferWriter=null;
		try{
			String writeFileName=args[1];
			FileWriter writeFile=new FileWriter(writeFileName);
			writeBufferWriter=new BufferedWriter(writeFile);
			out = new PrintWriter(writeBufferWriter);
			String fileName=args[0];
			File inputfile=new File(fileName);
			FileReader fileReader=new FileReader(inputfile);
			BufferedReader bufferedFileReader=new BufferedReader(fileReader);
			String readLine="";
		    int count=0;
		    int totalnumberofwords=0;
		    HashSet hashSet=new HashSet();
		    HashMap hashMap=new HashMap();
		    while((readLine=bufferedFileReader.readLine())!= null && readLine.trim().length()!=0){
		    	if(readLine.trim().length()!=0){
		    		String tabTokens[]=readLine.trim().split("\t");
		    		String tokens[]=null;
		    		hashSet.add(tabTokens[0].trim());
		    		if(tabTokens!=null && tabTokens.length!=0 && tabTokens.length==5){
			    		tokens=tabTokens[4].split(" ");
			    		if(tokens!=null && tokens.length!=0){
			    			totalnumberofwords+=tokens.length;
			    		}
		    		}
		    		if(hashMap.containsKey(tabTokens[0].trim())){
		    			if(tokens!=null && tokens.length!=0){
		    				String exitTokens=(String)hashMap.get(tabTokens[0].trim());
		    				hashMap.put(tabTokens[0].trim(),""+(tokens.length+Integer.parseInt(exitTokens)));
		    			}
		    		}else{
		    			if(tokens!=null && tokens.length!=0){
		    				hashMap.put(tabTokens[0].trim(),""+tokens.length);
		    			}
		    		}		    		
		    		count++;
		    	}
		    }
		    String maxAvgCategory="";
		    String minAvgCategory="";
		    int number=0;
		    if(hashMap!=null && hashMap.size()!=0){
		    	for(Object key : hashMap.keySet()){
					int find=(totalnumberofwords/Integer.parseInt((String)hashMap.get(key)));
		    		if(number<find){
		    			maxAvgCategory=key.toString();
		    			number=find;
		    		}
				}
		    	for(Object key : hashMap.keySet()){
					int find=(totalnumberofwords/Integer.parseInt((String)hashMap.get(key)));
		    		if(number>find){
		    			minAvgCategory=key.toString();
		    			number=find;
		    		}
				}
		    }
		    out.println(count+"\t"+(totalnumberofwords/count)+"\t"+(count/hashSet.size())+"\t"+maxAvgCategory+"\t"+minAvgCategory);
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
