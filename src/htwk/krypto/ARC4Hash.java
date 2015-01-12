package htwk.krypto;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ARC4Hash{
 
        public static void main (String[] args){
            String k = DateiAuslessen();
            
            int temp = Blockzerlegung(k);
            int text = initSHA256();

            System.out.println("\n4. Für jeden Klartextblock B wiederhole:");
            int arc;            
            for( int i=0; i<1; i++){
                text = exorVerknuefen(text);
                arc = initARC4(text);            
                arc = verARC4(arc);
                text = nextARC4(arc);            
            }
            System.out.println("\n9. Ergebnis: Der Inhalt des Textregisters.");        

        }

        public static String DateiAuslessen(){
            System.out.println("\n1. Der aus einer Datei eingelesene Klartext wird wie in SHA-256 aufgefüllt.");
            return "Hello World! usw";        
        }

        public static int Blockzerlegung( String k){
            System.out.println("\n2. Der aufgefüllte Klartext wird in Blöcke zu je 128 Bit zerlegt.");
            return k.length();
        }

        public static int initSHA256(){
            System.out.println("\n3. Das 256-Bit Textregister wird initialisiert wie in SHA-256.");
            return 42;
        }

        public static int exorVerknuefen( int text){
            System.out.println("\n5. Die niederwertigen 128 Bits des Textregisters werden bitweise EXOR-verknüpft mit B.\n   Das liefert die neuen 128 Bits des Textregisters.");
            return text;
        }

        public static int initARC4( int text){
            System.out.println("\n6. Mit dem Textregister wird ARC4 initialisiert.");
            return text;
        }

        public static int verARC4( int arc){
            System.out.println("\n7. Man läßt die nächsten 256 Bytes des ARC4 verfallen.");
            return arc;
        }

        public static int nextARC4(int arc){
            System.out.println("\n8. Die nächsten ausgegebenen 256 Bit des ARC4 liefern den neuen Inhalt des Textregisters.");
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
