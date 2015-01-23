package htwk.krypto;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Calendar;

public class ARC4Hash{
	
		private static String testString = "This is the test";
		private static byte[] plainText;
        private static final int blockSize = 16;

		public static void main (String[] args){
        	
			//file argument handle
        	if(args.length == 1){
        		System.out.println("Datei geladen: " + args[0]);
        		plainText = readBytesFromFile(args[0]);
        	}else{
        		System.err.println("WARNUNG: Teststring wird mangels Argument verwendet.\n");
        		plainText = testString.getBytes();
        	}

            //TODO auffüllen


        	ArrayList<ArrayList<Character>> textblocks = blockFragmentation(plainText);
            
        	System.out.println("Streufunktion mit ARC4 läuft.");
        	
            //Run Algorithm
            char textregister[] = initSHA256();
            
            char temp[] = new char[textregister.length];
            ARC4 arc4 = new ARC4();
            for( int i=0; i<textblocks.size(); i++){
                ArrayList<Character> b = textblocks.get(i);
                textregister = EXOR(textregister,b);
                textregister = arc4.initARC(textregister);
                //flush one round
                temp = arc4.generate(textregister);
                textregister = arc4.generate(textregister);
            }
            
            System.out.println("\nFertig. Inhalt des Textregisters:");        
            System.out.println(new String(textregister));
            
            System.out.println("\nLänge des Resultathashes:\n" + textregister.length);
            
            System.out.println("\nResultathash:\n" + displayHexFromCharArray(textregister));
            
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
        
        /**
         * Separates String into fragments the size of global variable blockSize
         * @param s
         * String to be fragmented
         * @return
         * ArrayList containing the blocks as ArrayLists of Integers
         */
        public static ArrayList<ArrayList<Character>> blockFragmentation(byte[] s) {
        	int len = s.length;


        	ArrayList<ArrayList<Character>> textBlocks = new ArrayList<>();
			
			ArrayList<Character> textBlock = new ArrayList<>();
            
            byte[] part = new byte[blockSize];
            for (int i =0 ; i <=len/blockSize ; i++){
                if((1+i)* blockSize >len){
                    byte[] part1 = Arrays.copyOf(s, i * blockSize);
                    for (int j = 0; j < blockSize; j++) {
                        if (j<part1.length) {
                            part[j] = part1[j];
                        }else{
                            part[j] = '0';
                        }
                    }
                }else
                    part = Arrays.copyOf(s,(1+i)* blockSize  );
                for (int j = 0; j < 16 ; j++) {
                    textBlock.add((char)part[j]);

                }
                final ArrayList<Character> t = textBlock;
                textBlocks.add(new ArrayList<>(t));
                textBlock.clear();
            }

			return textBlocks;
		}
        
        /**
         * Initialize SHA256
         * @return
         * Init char array
         */
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

        public static char[] EXOR(char[] register, ArrayList<Character> b){
        	char[] dummy = new char[register.length];
            for (int i = 0; i < b.size() ; i++) {
                char a  = b.get(i);
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
