package com.addressbook;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

public class AddressBookMain {

	static HashMap<String,ArrayList<Person>> loadedlist=new  HashMap<String,ArrayList<Person>>();
	static ArrayList<Person> newlist=new ArrayList<Person>();
	static HashMap<String,ArrayList<Person>> updatedlist=new HashMap<String,ArrayList<Person>>();
	static Boolean isFileLoaded=false;
	static AddressBook book=new AddressBook();
	static HashMap<String,ArrayList<Person>> dairy=new HashMap<String,ArrayList<Person>>();
	static Scanner s=new Scanner(System.in);
	static ArrayList<String> filenames=new ArrayList<String>();
	
	public static void viewEntries(ArrayList<Person> book)
	{
		if(book.isEmpty())
			System.out.println("No Entries to display");
		else
		{
			
			for(Person p : book)
			{
				
				System.out.printf("\n %10s %10s %10s %15s %15s %20s",p.getFname(),p.getLname(),p.getAge(),p.getEmail(),p.getNumber(),p.getDob());
			}
			System.out.println();
		}
	}
	
	
	public static void printHeaders()
	{
		System.out.printf("\n %10s %10s %10s %15s %20s %15s","FirstName", "LastName","Age","Email", "Phone Number", "BirthDate");
		System.out.printf("\n %10s %10s %10s %20s %15s %15s","---------", "--------","---------","--------------", "-------------", "------------");
	}
	public static ArrayList<String> loadFilenames(String filename)
	{
		ArrayList<String>booknames=new ArrayList<String>();
		try
		{
			File file=new File(filename);
			if(file.exists())
			{
				BufferedReader br=new BufferedReader(new FileReader(filename));
				String name;
				while((name=br.readLine()) !=null)
				{
					booknames.add(name);
				}
				br.close();
			}
			else
				file.createNewFile();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return booknames;
	}
	
	public static void addFileName(String filename)
	{
		try
		{
			FileWriter fw=new FileWriter("filenames.txt",true);
			fw.write(filename +"\n");
			fw.flush();
			fw.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	public static void loadFiles()
	{
		filenames=loadFilenames("filenames.txt");
		if(filenames.size()==0)
			
		{
			String bookname=null;
			System.out.println("There is no AddressBook Available. Do you want to create a AddressBook(y/n)");
			String ch=s.next();
			if(ch.equalsIgnoreCase("y"))
			{
				System.out.println("enter the name of the AddressBook");
				bookname=s.next() +".txt";
				addFileName(bookname);
			}
		filenames=loadFilenames("filenames.txt");
		}
		else
		{
			Iterator names=filenames.iterator();
			while(names.hasNext())
			{
				String filename=(String)names.next();
				File file=new File(filename);
				if(!(file.exists()))
				{
					
					try {
						file.createNewFile();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				loadedlist.put(filename, book.loadEntries(filename));
				updatedlist.put(filename, loadedlist.get(filename));
			}
		}
		
		
		
		isFileLoaded=true;
	}
	public static void checkFileLoadStatus()
	{

		if(!isFileLoaded)
		{
			loadFiles();
		}
	}
	public static void main(String [] args) throws IOException
	{
		int limit=1;
	
		
		int opt;
		do {
			System.out.println("1) Load from File \n 2)Save to File\n 3) Add an Entry \n 4) Remove an Entry \n 5) Edit an Existing Entry"
					+ "\n 6) Sort the Address Book \n 7) search for a specific entry \n 8) Move entry from one file to another \n 9) Quit"
					+ "\n \n  please choose what you would like to do with the database: ");
			opt=s.nextInt();
			
			switch(opt)
			{
			case 1:
				loadFiles();
				break;
			case 2:
				checkFileLoadStatus();
				Iterator ir=loadFilenames("filenames.txt").iterator();
				while(ir.hasNext())
				{
					String filename=ir.next().toString();
					File file=new File(filename);
					if(file.exists())
					{
						file.delete();
					}
						ArrayList<Person> addbook=updatedlist.get(filename);
						for(Person p:addbook)
						{
							
							book.addEntry(p,filename);
						}
				}

				break;
				
			case 3:
				Person p=new Person();
				checkFileLoadStatus();
				Iterator keys=loadFilenames("filenames.txt").iterator();
				boolean isadded=false;
				while(keys.hasNext())
				{
					String filename=keys.next().toString();
					
					if(!updatedlist.containsKey(filename))
					{
						ArrayList<Person> newBook=new ArrayList<Person>();
						updatedlist.put(filename,newBook);
					}
					ArrayList<Person> adbook=updatedlist.get(filename);
					System.out.println(adbook.size());
					if(!(adbook.size()==limit))
					{
						System.out.println("enter first name");
						p.setFname(s.next());
						System.out.println("enter last name");
						p.setLname(s.next());
						System.out.println("enter email address");
						p.setEmail(s.next());
						System.out.println("enter date of birth(MM/dd/yyyy)");
						p.setDob(s.next());
						System.out.println("enter age");
						p.setAge(s.nextInt());
						System.out.println("enter phone number");
						p.setNumber(s.nextLong());
						adbook.add(p);
						updatedlist.put(filename, adbook);
						isadded=true;
						break;
					}
				}
				if(!keys.hasNext() && !isadded)
				{
					System.out.println("All Addressbooks in Dairy is full. DO you want to add new Addressbook into dairy(Y/N)");
					String ch=s.next();
					if(ch.equalsIgnoreCase("y"))
					{
						System.out.println("Enter new Addressbook name");
						String bookname=s.next() +".txt";
						addFileName(bookname);
						System.out.println("enter first name");
						p.setFname(s.next());
						System.out.println("enter last name");
						p.setLname(s.next());
						System.out.println("enter email address");
						p.setEmail(s.next());
						System.out.println("enter date of birth(MM/dd/yyyy)");
						p.setDob(s.next());
						System.out.println("enter age");
						p.setAge(s.nextInt());
						System.out.println("enter phone number");
						p.setNumber(s.nextLong());
						//adbook.add(p);
						//updatedlist.put(filename, adbook);
						ArrayList<Person> newBook=new ArrayList<Person>();
						newBook.add(p);
						updatedlist.put(bookname,newBook);
					}
				}
				
				break;
			
			case 4:
				checkFileLoadStatus();
				Iterator addbook=filenames.iterator();
				System.out.println("enter the last name to delete");
				String name=s.next();
				while(addbook.hasNext())
				{
					
					String filename=addbook.next().toString();
					updatedlist.put(filename,book.deleteEntry(updatedlist.get(filename), name));
					
				}	
				System.out.println("entry deleted");
				break;
			case 5:
					checkFileLoadStatus();
					 addbook=filenames.iterator();
					 System.out.println("enter the last name to edit the entry");
					 name=s.next();
					while(addbook.hasNext())
					{
						String filename=addbook.next().toString();
						
						updatedlist.put(filename,book.updateValue(updatedlist.get(filename), name));
						
				}
					System.out.println("Entry updated");
				break;
			case 6:
				checkFileLoadStatus();
				System.out.println("1. Sort by First name \n "
						+ "2.sort by last name \n"
						+ "3.Sort by Age \n"
						+ "4.Sort by Date of Birth" );
				System.out.println("Enter your choice of sorting :");
				int choice=s.nextInt();
				 addbook=filenames.iterator();
				printHeaders();
				while(addbook.hasNext())
				{
					
					viewEntries(book.SortEntries(updatedlist.get((String)addbook.next()), choice));
				}
				
				break;
			case 7:
				checkFileLoadStatus();
				System.out.println("enter the characters of last name to search");
					name=s.next();
				 addbook=filenames.iterator();
				 printHeaders();
				 while(addbook.hasNext())
				 {
					 viewEntries(book.retrieveByLname(updatedlist.get((String)addbook.next()), name));
				 }
				 break;
			case 8:
				checkFileLoadStatus();
				System.out.println("enter the source address book name");
				String sourcebookname=s.next() +".txt";
				String destinationbookname;
				if(!updatedlist.containsKey(sourcebookname))
				{
					System.out.println("no addressbook found");
					break;
				}
				System.out.println("enter destination address book name");
				destinationbookname=s.next() +".txt";
				if(!updatedlist.containsKey(destinationbookname))
				{
						System.out.println("no destination addressbook found");
						break;
				}
				System.out.println("Enter last name of the contact to move");
				name=s.next();
				ArrayList<Person> sourcebook=updatedlist.get(sourcebookname);
				ArrayList<Person> destinationbook=updatedlist.get(destinationbookname);
				ArrayList<Person> tempbook=book.retrieveByLname(sourcebook, name);
				
				 
				 if(!(tempbook.size()==0))
				 {
					 p=book.retrieveByLname(sourcebook, name).get(0);
					 destinationbook.add(p);
					 updatedlist.put(destinationbookname,destinationbook);
					 updatedlist.put(sourcebookname,book.deleteEntry(sourcebook, name));
				 }
				 else
					 System.out.println("No entry found with the given last name");
				
			}
			
				
		} while (opt!=9);
	}
}
