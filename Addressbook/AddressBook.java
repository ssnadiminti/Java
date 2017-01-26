package com.addressbook;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Scanner;

public class AddressBook {

	private int limt;
	Scanner s=new Scanner(System.in);

	
	public ArrayList<Person> deleteEntry(ArrayList<Person> book, String lname)
	{
		int index=0;
		for(Person p:book)
		{
			if(p.getLname().equalsIgnoreCase(lname))
				break;
			else
				index++;	
		}
		book.remove(index);
		return book;
	}
	
	public void addEntry(Person details,String filename)
	{
		try
		{
		FileWriter fw=new FileWriter(filename,true);
		fw.write(details.getFname() +"\t" +details.getLname()+"\t"+details.getAge() +"\t"+details.getEmail()+"\t"+details.getNumber() +"\t"+details.getDob()+"\n");
		fw.flush();
		fw.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public ArrayList<Person> loadEntries(String filename)
	{
		BufferedReader br=null;
		FileReader fr=null;
		ArrayList<Person> book=new ArrayList<Person>();	
		try {
			 fr=new FileReader(filename);
			 br=new BufferedReader(fr);
			String line;
			
			while((line=br.readLine())!=null)
			{
				String address[]=line.split("\t");
				Person p=new Person();
				p.setFname(address[0]);
				p.setLname(address[1]);
				p.setAge(Integer.parseInt(address[2]));
				p.setEmail(address[3]);
				p.setNumber(Integer.parseInt(address[4]));
				p.setDob(address[5]);
				if(PrintBirthDays(p.getDob()))
				{
					System.out.println("your friend "+p.getFname() +" has birtday on: "+ p.getDob());
				}
				book.add(p);
			}
			fr.close();
			br.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		finally
		{
			try {
				fr.close();
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return book;
	}
	
	private boolean PrintBirthDays(String dob) {
		String pattern = "MM/dd/yyyy";
		boolean isbday=true;
	    SimpleDateFormat format = new SimpleDateFormat(pattern);
		try {
			Date date=format.parse(dob);
			Date currentdate= new Date();
			if((date.getMonth()==currentdate.getMonth()) && (currentdate.getDay()-date.getDay()<3))
			{
				isbday=true;
			}
			else
				isbday= false;
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isbday;
	}

	public ArrayList<Person> SortEntries(ArrayList<Person> book, int choice)
	{
		
		 Collections.sort(book, new Comparator<Person>() 
				{

					@Override
					public int compare(Person p1, Person p2) {
						if(choice==2)
						{
							if(p1.getLname().compareTo(p2.getLname()) < 1)
								return 1;
							else
								return -1;
						}
						else if(choice==1)
						{
							if(p1.getFname().compareTo(p2.getFname()) < 1)
								return 1;
							else
								return -1;
						}
						else if(choice==3)
						{
							if(p1.getAge() < p2.getAge())
								return 1;
							else
								return -1;
						}
						else 
						{
							if(p1.getDob().compareTo(p2.getDob()) < 1)
								return 1;
							else
								return -1;
						}
						
					}
			
				});
		
		return book;
	}
	
	public ArrayList<Person> retrieveByLname(ArrayList<Person> book, String charseq)
	{
		ArrayList<Person> lnames=new ArrayList<Person>();
		Iterator ir=book.iterator();
		while(ir.hasNext())
		{
			Person p=(Person)ir.next();
			String lname=p.getLname();
			if(lname.startsWith(charseq))
				lnames.add(p);
		}
		return lnames;
	}
	
	public ArrayList<Person> updateValue(ArrayList<Person> book, String name)
	{
		ArrayList<Person> modifiedbook=new ArrayList<Person>();
		Iterator ir=book.iterator();
		while(ir.hasNext())
		{
			Person p=(Person)ir.next();
			String lname=p.getLname();
			if(lname.equalsIgnoreCase(name))
			{
				System.out.println("enter modified fname");
				p.setFname(s.next());
				System.out.println("enter modified last name");
				p.setLname(s.next());
				System.out.println("enter modifed email address");
				p.setEmail(s.next());
				System.out.println("enter modified date of birth(MM/dd/yyyy)");
				p.setDob(s.next());
				System.out.println("enter modifed age");
				p.setAge(s.nextInt());
				System.out.println("enter modified phone number");
				p.setNumber(s.nextLong());
				
			}
			modifiedbook.add(p);
				
		}
		return modifiedbook;
	}
	
	
}
