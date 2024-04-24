/**
 * Aktuelles Datum abfragen
 *
 * @author Dirk Louis
 */
import java.util.Date;
import java.util.Calendar;
import java.text.DateFormat;

public class Demo_1
{

 public static void main(String args[])
 {
  System.out.println();

  Date today = new Date();
  System.out.println(today);

  Calendar calendar = Calendar.getInstance();
  System.console().printf("%s\n", DateFormat.getDateTimeInstance().format(calendar.getTime()) );

 }
}

