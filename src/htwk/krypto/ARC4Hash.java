package htwk.krypto;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ARC4Hash{
 
        public static void main (String[] args){
            String k = DateiAuslessen();
            List textblocks = BlockZerlegung(k);
            byte sha256Register[] = initSHA256();

            System.out.println("\n4. Für jeden Klartextblock B wiederhole:");
            byte arc[] = new byte[sha256Register.length];
            byte temp[] = new byte[sha256Register.length];
            for( int i=0; i<sha256Register.length; i++){
                exorVerknuefen(sha256Register[i]);
                temp[i] = initARC4(sha256Register[i]);            
                arc = verARC4(arc);
                sha256Register = nextARC4(arc);            
            }
            System.out.println("\n9. Ergebnis: Der Inhalt des Textregisters.");        

        }

        public static String DateiAuslessen(){
            System.out.println("\n1. Der aus einer Datei eingelesene Klartext wird wie in SHA-256 aufgefüllt.");
            return "Hello World, ich bin der Test und ich will auch 2 mal gesehen werden, du noob";
        }

        public static List BlockZerlegung(String s) {
        	int len = s.length();
			List textBlocks = new LinkedList<>();
			
			ArrayList<Integer> textBlock = new ArrayList<Integer>();
			char[] part = new char[16];
            for (int i =0 ; i <=len/16 ; i++){
                if((1+i)*16>len){
                    char[] part1 = s.substring(i*16,len).toCharArray();
                    for (int j = 0; j < 16; j++) {
                        if (j<part1.length) {
                            part[j] = part1[j];
                        }else{
                            part[j] = '0';
                        }
                    }
                }else
                    part = s.substring(i*16,(1+i)*16).toCharArray();
                for (int j = 0; j < 16 ; j++) {
                    textBlock.add((int) part[j]);
                }
                textBlocks.add(textBlock);
                textBlock.clear();
            }

           //System.out.println(textBlocks.toString());
           //System.out.println(textBlocks.size());
			return textBlocks;
		}

        public static byte[] initSHA256(){
        	MessageDigest md;        	
        	byte byteData[] = null;
        	try {
				md = MessageDigest.getInstance("SHA-256");
				byteData = md.digest();
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	        	
        	return byteData;
        }

        public static void exorVerknuefen( int text){
        	//System.out.println("\n5. Die niederwertigen 128 Bits des Textregisters werden bitweise EXOR-verknüpft mit B.\n   Das liefert die neuen 128 Bits des Textregisters.");
            //return text;
        }

        public static byte initARC4( int text){
            //System.out.println("\n6. Mit dem Textregister wird ARC4 initialisiert.");
            return (byte) text;
        }

        public static byte[] verARC4( byte[] arc){
            //System.out.println("\n7. Man läßt die nächsten 256 Bytes des ARC4 verfallen.");
            return arc;
        }

        public static byte[] nextARC4(byte[] arc){
            //System.out.println("\n8. Die nächsten ausgegebenen 256 Bit des ARC4 liefern den neuen Inhalt des Textregisters.");
            return arc;
        }
        
        public static String SHAHashing (String password){
        	
        	MessageDigest md;
			try {
				md = MessageDigest.getInstance("SHA-256");
			
            md.update(password.getBytes());
     
            byte byteData[] = md.digest();
     
            //convert the byte to hex format method 1
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
             sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }
     
            System.out.println("Hex format : " + sb.toString());
     
            //convert the byte to hex format method 2
            StringBuffer hexString = new StringBuffer();
        	for (int i=0;i<byteData.length;i++) {
        		String hex=Integer.toHexString(0xff & byteData[i]);
       	     	if(hex.length()==1) hexString.append('0');
       	     	hexString.append(hex);
        	}
        	System.out.println("Hex format : " + hexString.toString());
        	return hexString.toString();
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return password;
			}
        }
}
