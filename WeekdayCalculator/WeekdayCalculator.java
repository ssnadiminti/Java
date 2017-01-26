import java.util.Scanner;


class WeekdayCalculator
{
public static void main( String[] args )
{
Scanner keyboard = new Scanner(System.in);

System.out.println("Welcome to Mr. Santhosh's fantastic birth-o-meter!");
System.out.println();
System.out.println("All you have to do is enter your birth date, and it will");
System.out.println("tell you the day of the week on which you were born.");
System.out.println();
System.out.println("Some automatic tests....");
System.out.println("12 10 2003 => " + weekday(12,10,2003));
System.out.println(" 2 13 1976 => " + weekday(2,13,1976));
System.out.println(" 2 13 1977 => " + weekday(2,13,1977));
System.out.println(" 7  2 1974 => " + weekday(7,2,1974));
System.out.println(" 1 15 2003 => " + weekday(1,15,2003));
System.out.println("10 13 2000 => " + weekday(10,13,2000));
System.out.println();

System.out.println("Now it's your turn!  What's your birthday?");
System.out.print("Birth date (mm dd yyyy): ");
 
int mm = keyboard.nextInt();
 
int dd = keyboard.nextInt();
 
int yyyy = keyboard.nextInt();

// put a function call for weekday() here
System.out.println("You were born on " +weekday(mm,dd,yyyy));
}

public static int month_offset(int number)
{
if(number==1)
return 1;
else if(number==2)
return 4;
else if(number==3)
return 4;
else if(number==4)
return 0;
else if(number==5)
return 2;
else if(number==6)
return 5;
else if(number==7)
return 0;
else if(number==8)
return 3;
else if(number==9)
return 6;
else if(number==10)
return 1;
else if(number==11)
return 4;
else if(number==12)
return 6;
else
return -1;
}
 
public static String month_name(int number)
{
if(number==1)
return "January";
else if(number==2)
return "February";
else if(number==3)
return "March";
else if(number==4)
return "April";
else if(number==5)
return "May";
else if(number==6)
return "June";
else if(number==7)
return "July";
else if(number==8)
return "August";
else if(number==9)
return "September";
else if(number==10)
return "October";
else if(number==11)
return "November";
else if(number==12)
return "December";
else
return "Error";
}
public static String weekday( int mm, int dd, int yyyy )
{
int yy, total;
String date = "";
yy=yyyy-1900;
System.out.println(yy);
total=yy/4;
System.out.println(total);
total=total+month_offset(mm)+yy+dd;
System.out.println(month_offset(yyyy,mm));
total=total- month_offset(yyyy,mm)-1;
System.out.println(total);
int day=total%7;
System.out.println(day);
String dayname=weekday_name(day);
// bunch of calculations go here

date = dayname+", "+month_name(mm) +" "+dd+ ", " + yyyy;

return date;
}

public static String weekday_name(int day)
{
System.out.println(day);
if(day==1)
return "Monday";
else if(day==2)
return "Tuesday";
else if(day==3)
return "Wednesday";
else if(day==4)
return "Thursday";
else if(day==5)
return "Friday";
else if(day==6)
return "Saturday";
else if(day==0)
return "sunday";
else
return "error";
}
 
public static int month_offset(int yr,int mm)
{
if( is_leap(yr) && ((month_name(mm).equalsIgnoreCase("January")) || (month_name(mm).equalsIgnoreCase("February"))))
{
return 1;
}
else
return 0;
 
 
}

// paste your functions from MonthName, WeekdayName, and MonthOffset here
 
public static boolean is_leap( int year )
{
// years which are evenly divisible by 4 are leap years,
// but years divisible by 100 are not leap years,
// though years divisible by 400 are leap years
boolean result;

if ( year%400 == 0 )
result = true;
else if ( year%100 == 0 )
result = false;
else if ( year%4 == 0 )
result = true;
else
result = false;
 
return result;
}
}