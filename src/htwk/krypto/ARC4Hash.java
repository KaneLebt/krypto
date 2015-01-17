package htwk.krypto;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ARC4Hash{
	
		private static String testString = "1asdfasdfasdfsdf1dasdfasdf1";
		private static byte[] cleartext;
        private static final int bitBlocksign = 16;


        public static void main (String[] args){
        	
        	if(args.length == 1){
        		cleartext = readBytesFromFile(args[0]);
        	}else{
        		System.err.println("WARNING: A test string is used as cleartext");
        		cleartext = testString.getBytes();
        	}
            
        	//convert byte array to String via new String() constructor
        	// create textblocks
            List textblocks = BlockZerlegung(new String(cleartext));
            
            //char sha256Register[] = initSHA256();
            System.out.println("\n4. Für jeden Klartextblock B wiederhole:");
            //TODO bitweise XOR textblocks mit sha256Register
            //end while

            char textregister[] = initSHA256();
            char temp[] = new char[textregister.length];

            ARC4 arc4 = new ARC4();
            for( int i=0; i<textblocks.size(); i++){
                ArrayList<Integer> b = (ArrayList<Integer>) textblocks.get(i);
                textregister = exorVerknuefen(textregister,b);
                textregister = arc4.initARC(textregister);
                
                //lassen wir "verfallen"
                temp = arc4.generate(textregister);
                textregister = arc4.generate(textregister);
            }
            
            System.out.println("\n9. Ergebnis: Der Inhalt des Textregisters.");        
            System.out.println(new String(textregister));
            System.out.println(textregister.length);
            
            System.out.println(displayHexFromCharArray(textregister));
            
        }
        
        /**
         * takes a character array and returns its hex representation as a string
         * @param chararray
         * character array
         * @return
         * hex representation of char array as a string
         */
        public static String displayHexFromCharArray(char[] chararray){
        	
        	StringBuilder sb = new StringBuilder();
        	
        	for(int i = 0; i < chararray.length; i++){
        		sb.append(String.format("%02x", (byte) chararray[i]));
            }
        	
			return sb.toString();
        	
        }

        public static List BlockZerlegung(String s) {
        	int len = s.length();
			List textBlocks = new LinkedList<>();
			
			ArrayList<Integer> textBlock = new ArrayList<Integer>();
            
            char[] part = new char[16];
            for (int i =0 ; i <=len/16 ; i++){
                if((1+i)* bitBlocksign >len){
                    char[] part1 = s.substring(i* bitBlocksign,len).toCharArray();
                    for (int j = 0; j < bitBlocksign; j++) {
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
                final ArrayList<Integer> t = textBlock;
                textBlocks.add(new ArrayList<>(t));
                textBlock.clear();
            }

           //System.out.println(textBlocks.toString());
           //System.out.println(textBlocks.size());
			return textBlocks;
		}

        public static char[] initSHA256(){
        	MessageDigest md;        	
        	byte byteData[] = null;
        	try {
				md = MessageDigest.getInstance("SHA-256");
				byteData = md.digest();
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
        	char charArray[] = new char[byteData.length];
        	for(int i = 0; i < byteData.length; i++){
        		charArray[i] = (char) byteData[i];
        	}
        	        	
        	return charArray;
        }

        public static char[] exorVerknuefen(char[] register, ArrayList<Integer> b){
        	char[] dummy = new char[register.length];
            for (int i = 0; i < b.size() ; i++) {
                int a  = b.get(i);
                dummy[i] = (char) (register[i]^a);
            }
            return dummy;
        }
        
                
        /**
         * Reads byte array from file and returns it
         * @param filePath
         * path to file
         * @return
         * file as byte array
         */
        public static byte[] readBytesFromFile(String filePath){
            
    		Path path = Paths.get(filePath);
    		try {
    			//If file exists
				if(path.toFile().exists()){
					//load file into byte array and return it
	    			byte[] data = Files.readAllBytes(path);
					return data;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
    		System.err.println("File not found. Aborting.");
			System.exit(-1);
			return null;
        }
}
