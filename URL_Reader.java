/**
 *
 * @support  Peter Müller (der Originalcode wurde freundlicherweise von Karsten Samaschke zur Verfügung gestellt.)
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class URL_Reader
{
   /**
    * Liest den Inhalt einer externen Ressource als String ein
    */
   public static String read(String address)
   {
      // StringBuffer zum Halten der Daten
      StringBuffer buff = new StringBuffer();
      try
      {
         // URL-Instanz, die den Zugriff auf die externe
         // Ressource erlaubt
         URL url = new URL(address);

         // BufferedReader zum Einlesen der Textdaten
         BufferedReader rdr = new BufferedReader(
            new InputStreamReader(url.openStream()));

         // Einlesen der Daten
         String line = null;
         while((line = rdr.readLine()) != null)
	 {
            buff.append(line + "\n");
         }

         // Aufräumen
         rdr.close();
      }
      catch (MalformedURLException e)
      {
       e.printStackTrace();
      }
      catch (IOException e)
      {
       e.printStackTrace();
      }

      // Geladene Daten zurückgeben
      return buff.toString();
   }
}

