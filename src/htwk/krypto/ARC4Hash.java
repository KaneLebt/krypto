package htwk.krypto;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ARC4Hash{
 
        public static void main (String[] args){
            String k = DateiAuslessen();
            Textblockaufteiler(k);
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
            return "Hello World, ich bin der Test und ich will auch 2 mal gesehen werden, du noob";
        }

        public static void Textblockaufteiler(String s) {
        	int len = s.length();
System.out.println(len);
			
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

            System.out.println(textBlocks.toString());
            System.out.println(textBlocks.size());
			//return null;
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
}
