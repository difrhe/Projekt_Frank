import java.net.*;
import java.io.*;
import java.util.*;
import java.util.Properties;
import java.text.DateFormat;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

class Main_Logger
{
 private static String DateS = "";
 private static String TimeS = "";
 private static Logger logger = Logger.getLogger(Main_Logger.class.getName());

 //-----------------------------------------------
 // Get_Time                                     |
 //-----------------------------------------------
 public static void Get_Time()
 {
  DateFormat df1 = DateFormat.getTimeInstance();
  DateFormat df2 = DateFormat.getDateInstance();

  TimeS = df1.format(new Date() );
  DateS = df2.format(new Date() );
 }

 //-----------------------------------------------
 // main                                         |
 //-----------------------------------------------
 public static void main( String args[] ) throws Exception
 {
  String FileName = "./Main_Logger.properties";
  String Sek = "";

  PropertyConfigurator.configure(FileName);
  logger.debug("Main_Logger startet.");

  if( "-start".equals(args[0]) )
  {
   // System.out.println( "Logger Start" );
   try
   {
    //System.out.println( "Logger Write" );

    Get_Time();

    logger.debug( "Time : " + TimeS + "\r\n" );
    logger.debug( "Date : " + DateS + "\r\n" );

   }
   catch(java.lang.Exception e)
   {
    logger.error( "Java Exception Error\r\n" );
    return;
   }
  }

  if( "-loop".equals(args[0]) )
  {
   // System.out.println( "Logger Start" );
   try
   {
    //System.out.println( "Logger Write" );
    Sek = "";

    for(;;)
    {
     Get_Time();

     if( ! Sek.equals(TimeS) )
     {
      Sek = TimeS;
      System.out.println( "Time : " + TimeS + ", " + DateS + "\r\n" );
      logger.info( "Time : " + TimeS + ", " + DateS + "\r\n" );
     }
    }

   }
   catch(java.lang.Exception e)
   {
    logger.error( "Java Exception Error\r\n" );
    return;
   }
  }

 }

}
