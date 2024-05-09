// package org;
// 09-05-2024

import org.apache.xerces.impl.dv.util.Base64;
import java.security.MessageDigest;

public class Crypte_Passwd
{

 private boolean verbose = true;
 private static String str1 = "";
 private static String str2 = "";
 private static String str3 = "";

 private MessageDigest sha = null;

 public Crypte_Passwd()
 {
  verbose = true;

  try
  {
   sha = MessageDigest.getInstance("SHA-1");
  }
  catch (java.security.NoSuchAlgorithmException e)
  {
   System.out.println("Construction failed: " + e);
  }
 }

 public void createDigest(int ausg, byte[] salt, String entity)
 {
  String label = "{SSHA}";

  sha.reset();
  sha.update(entity.getBytes());
  sha.update(salt);

  // Complete hash computation, this results in binary data
  byte[] pwhash = sha.digest();

  if (ausg == 1 ) { verbose = true; } else { verbose = false;  }

  if( verbose )
  {
   System.out.println("pwhash, binary represented as hex: " + toHex(pwhash) + " \n");
   System.out.println("Putting it all together: ");
   System.out.println("binary digest of password plus binary salt: " + pwhash + salt);
   System.out.println("Now we base64 encode what is respresented above this line ...");
  }

  str1 = label + new String(Base64.encode(concatenate(pwhash, salt)));
  str2 = pwhash.toString() + salt.toString();
  str3 = toHex(salt);
 }


 public boolean checkDigest(boolean ausg, String digest, String entity)
 {
  boolean valid = true;

  // ignore the {SSHA} hash ID
  digest = digest.substring(6);

  // extract the SHA hashed data into hs[0]
  // extract salt into hs[1]
  byte[][] hs = split(Base64.decode(digest), 20);
  byte[] hash = hs[0];
  byte[] salt = hs[1];

  // Update digest object with byte array of clear text string and salt
  sha.reset();
  sha.update(entity.getBytes());
  sha.update(salt);

  // Complete hash computation, this is now binary data
  byte[] pwhash = sha.digest();

  if( ausg == true )
  {
   System.out.println("Salted Hash extracted (in hex): " + toHex(hash) + " " + "\nSalt extracted (in hex): " + toHex(salt));
   System.out.println("Hash length is: " + hash.length + " Salt length is: " + salt.length);
   System.out.println("Salted Hash presented in hex: " + toHex(pwhash));
  }

  if( !MessageDigest.isEqual(hash, pwhash) )
  {
   valid = false;
   // System.out.println("Hashes DON'T match: " + entity);
   System.out.print("<false>");
  }

  if( MessageDigest.isEqual(hash, pwhash) )
  {
   valid = true;
   //System.out.println("Hashes match: " + entity);
   System.out.print("<true>");
  }

  return valid;
 }

 public String checkDigest_2(String digest)
 {

  // ignore the {SSHA} hash ID
  digest = digest.substring(6);

  // extract the SHA hashed data into hs[0]
  // extract salt into hs[1]
  byte[][] hs = split(Base64.decode(digest), 20);
  byte[] salt = hs[1];

  // System.out.println( hs[1] );
  // System.out.println( salt );
  return toHex(salt);
 }


 public void setVerbose(boolean verbose)
 {
  this.verbose = verbose;
 }

 private static byte[] concatenate(byte[] l, byte[] r)
 {
  byte[] b = new byte[l.length + r.length];
  System.arraycopy(l, 0, b, 0, l.length);
  System.arraycopy(r, 0, b, l.length, r.length);
  return b;
 }

 private static byte[][] split(byte[] src, int n)
 {
  byte[] l, r;

  if( src == null || src.length <= n )
  {
   l = src;
   r = new byte[0];
  }
  else
  {
   l = new byte[n];
   r = new byte[src.length - n];
   System.arraycopy(src, 0, l, 0, n);
   System.arraycopy(src, n, r, 0, r.length);
  }

  byte[][] lr = { l, r };

  return lr;
 }

 private static String hexits = "0123456789abcdef";

 private static String toHex(byte[] block)
 {
  StringBuffer buf = new StringBuffer();

  for( int i = 0; i < block.length; ++i)
  {
   buf.append(hexits.charAt((block[i] >>> 4) & 0xf));
   buf.append(hexits.charAt(block[i] & 0xf));
  }

  return buf + "";
 }

 private static byte[] fromHex(String s)
 {
  s = s.toLowerCase();
  byte[] b = new byte[(s.length() + 1) / 2];
  int j = 0;
  int h;
  int nibble = -1;

  for( int i = 0; i < s.length(); ++i)
  {
   h = hexits.indexOf(s.charAt(i));

   if( h >= 0)
   {
    if (nibble < 0)
    {
     nibble = h;
    }
    else
    {
     b[j++] = (byte) ((nibble << 4) + h);
     nibble = -1;
    }
   }
  }

  if( nibble >= 0 )
  {
   b[j++] = (byte) (nibble << 4);
  }

  if( j < b.length )
  {
   byte[] b2 = new byte[j];
   System.arraycopy(b, 0, b2, 0, j);
   b = b2;
  }

  return b;
 }

 private static void usage(String className)
 {
  System.out.print("usage: "
			+ className
			+ " -s salt SourceString ...\n"
			+ "   or: "
			+ className
			+ " -c EncodedDigest SourceString ...\n");
 }


 private static void Test(String pa1, String pa2, String pa3)
 {
  byte[] salt = {};
  Crypte_Passwd sh2 = new Crypte_Passwd();

  System.out.println( "Parameter1 : " + pa1);
  System.out.println( "Parameter2 : " + pa2);
  System.out.println( "Parameter3 : " + pa3);

  salt = fromHex( pa1 );

  sh2.createDigest(0, salt, pa3 );

  System.out.println( "Str1 : " + str1 );
  System.out.println( "Str2 : " + str2 );
  System.out.println( "Str3 : " + str3 );

  System.out.println( "Salt : " + salt );
  System.out.println( "SSHA : " + str1 );

  System.out.println( sh2.checkDigest_2( str1 ) );
}

 // public static String start( String w1, String w2, String w3 )
 public static void main( String[] args )
 {
  Crypte_Passwd sh = new Crypte_Passwd();
  String className = "Crypte_Passwd";

  // -c validate data against digest
  // -s using a seed for randomness

  if( "-t".equals(args[0]) )
  {
   Test("smnnhess", "{SSHA}Wfu9mEarma5HFDUCEx8PZwRJX0Hg", "test00");
   //Test("smnnhess", "e0", "test00");
   //usage(className);
   return;
  }

  if( "-c".equals( args[0]) )
  {
   String digest = args[1];
   sh.checkDigest(false, digest, args[2] );
   return;
  }

  if( "-c2".equals(args[0]) )
  {
   String digest = args[1];
   System.out.println( sh.checkDigest_2( digest ) );
   return;
  }

  if( "-s".equals(args[0]) )
  {
   // generate digest from data passed in
   byte[] salt = {};
   salt = fromHex( args[1] );
   sh.createDigest( 0, salt, args[2] );

   System.out.println( str1 );
   return;
  }

  if( "-s1".equals(args[0]) )
  {
   // generate digest from data passed in
   byte[] salt = {};
   salt = fromHex( args[1] );

   str1 = "";
   str2 = "";
   str3 = "";
   sh.createDigest(0, salt, args[2] );

   sh.createDigest(1, fromHex(str2), args[2] );

   System.out.println( str1 );
   //System.out.println( str2 );
   //System.out.println( str3 );
   return;
  }

  if( "-fromhex".equals(args[0]) )
  {
   // generate digest from data passed in
   byte[] salt = {};
   salt = fromHex( args[1] );

   System.out.println( salt );
   return;
  }

  if( "-tohex".equals(args[0]) )
  {
   // generate digest from data passed in
   byte[] salt = {};
   salt = fromHex( args[1] );

   System.out.println( toHex(salt) );
   return;
  }

  //Test("smnnhess", "smnnhess");
  usage(className);
 }

}

