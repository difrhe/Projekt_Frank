/**
 * URI - Textinhalt abrufen
 *
 * @support  Peter Müller (der Originalcode wurde freundlicherweise von Karsten Samaschke zur Verfügung gestellt.)
 */
public class URL_Aufrufen
{
 public static void main( String[] args )
 {
  String[] Liste = new String[20];

  Liste[0] = "http://192.168.2.111/angular/webservice_111/";
  Liste[1] = "http://192.168.2.112/angular/webservice_112/";
  Liste[2] = "http://192.168.2.113/angular/webservice_113/";
  Liste[3] = "http://192.168.2.144/angular/webservice_144/";
  Liste[4] = "http://192.168.2.145/angular/webservice_145/";
  // Adresse einlesen
  String address = "";
   
  if( (null != args) && (args.length > 0) )
  {
   if( "-all".equals(args[0]) )
   {
    System.out.println();

    for(int xx=0; xx < 5; xx++)
    {
     System.out.println( URL_Reader.read(Liste[xx]) );
     System.out.println();
    }
    System.out.println();
    return;
   }
   else
   {
    address = args[0];
    
    if( address.length() > 5 )
    {
     // Inhalt ausgeben
     System.out.println( URL_Reader.read(address) );
    }
   }
   return;
  }

  System.out.println("Error : Keine URK");
  return;
 }

}

