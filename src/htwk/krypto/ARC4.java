package htwk.krypto;

/**
 * Created by Blacksheep on 17.01.2015.
 */
public class ARC4 {

    private char[] s;

    public ARC4() {

    }

    public void initARC(char[] textregister){
    	int ConsL = 256;
        int length = textregister.length;
        s = new char[ConsL];

        for(int i = 0; i < ConsL; i++){
            s[i] = (char) i;
        }

        char j = 0;
        for(int i = 0; i < ConsL; i++){
            j = (char) ((j + s[i] + textregister[i % (length)]) % (ConsL));
            swap(i,j);
        }
    }

    public char[] generate(char[] plainText){
        int i = 0;
        int j = 0;
        char[] key = new char[plainText.length];
        for (int n = 0; n < plainText.length; n++) {
            i = (i+1) % 256;
            j = (j + s[i]) % 256;
            swap(i,j);
            int z = s[(s[i] + s[j] ) % 256];
            key[n] = (char) (z^plainText[n]);
        }
        return key;
    }


    private void swap (int i, int j){
        char temp = s[i];
        s[i] = s[j];
        s[j] = temp;
    }

}