/**
 *
 * @support  Peter Müller (der Originalcode wurde freundlicherweise von Karsten Samaschke zur Verfügung gestellt.)
 */
import java.net.*;
import java.util.Enumeration;

public class DisplayInterfaces {

   /**
    * Gibt eine Liste aller Netzwerk-Interfaces samt
    * zugeordneter IP-Adressen aus
    */
   public static void display() {
      try {
         // Netzwerk-Interfaces abrufen
         Enumeration<NetworkInterface> interfaces =
            NetworkInterface.getNetworkInterfaces();

         // Alle Interfaces durchlaufen
         while(interfaces.hasMoreElements()) {
            // Aktuelles Element abrufen und Namen ausgeben
            NetworkInterface ni = interfaces.nextElement();
            System.out.println(
               String.format("Netzwerk-Interface: %s (%s)",
               ni.getName(), ni.getDisplayName()));

            // Adressen abrufen
            Enumeration<InetAddress> addresses =
               ni.getInetAddresses();

            // Adressen durchlaufen
            while(addresses.hasMoreElements()) {
               InetAddress address = addresses.nextElement();

               // Adresse ausgeben
               System.out.println(
                  String.format("- %s",
                  address.getHostAddress()));
            }

            System.out.println();
         }
      } catch (SocketException e) {
         e.printStackTrace();
      }
   }
}
