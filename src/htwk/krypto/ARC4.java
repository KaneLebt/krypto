package htwk.krypto;

/**
 * Created by Blacksheep on 17.01.2015.
 */
public class ARC4 {

    private char[] s;

    public ARC4() {

    }

    public char[] initARC$(char[] textregister){
        int length = textregister.length;
        s = new char[length];

        for(int i = 0; i < s.length; i++){
            s[i] = (char) i;
        }

        char j = 0;
        for(int i = 0; i < s.length-1; i++){
            j = (char) ((j + s[i] + textregister[i % (s.length-1)]) % (length-1));
            swap(i,j);
        }

        return s;
    }

    public char[] generate(char[] klarText){
        int i = 0;
        int j = 0;
        char[] schl = new char[klarText.length];
        for (int n = 0; n < klarText.length; n++) {
            i = (i+1) % 256;
            j = (j + s[i]) % 256;
            swap(i,j);
            int z = s[(s[i] + s[j] ) % 256];
            schl[n] = (char) (z^klarText[n]);
        }
        return schl;
    }


    private void swap (int i, int j){
        char temp = s[i];
        s[i] = s[j];
        s[j] = temp;
    }

}
