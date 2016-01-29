package android.jochemkleine.com.spraakoefening.data;

import java.io.Serializable;

/**
 * Created by Jochemkleine on 22-1-2016.
 */
public class Word implements Serializable {

    private String word;
    private boolean a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z;

    public Word (String word) {
        this.word = word;
        //initLetterBools();
    }

    public String getWord (){
        return word;
    }

    private void initLetterBools() {
        for (int i=0; i < word.length(); i++){
            char letter = word.charAt(i);
            switch (letter){
                case 'a':
                    a = true;
                    break;
                case 'b':
                    b = true;
                    break;
                case 'c':
                    c = true;
                    break;
                case 'd':
                    d = true;
                    break;
                case 'e':
                    e = true;
                    break;
                case 'f':
                    f = true;
                    break;
                case 'g':
                    g = true;
                    break;
                case 'h':
                    h = true;
                    break;
                case 'i':
                    this.i = true;
                    break;
                case 'j':
                    j = true;
                    break;
                case 'k':
                    k = true;
                    break;
                case 'l':
                    l = true;
                    break;
                case 'm':
                    m = true;
                    break;
                case 'n':
                    n = true;
                    break;
                case 'o':
                    o = true;
                    break;
                case 'p':
                    p = true;
                    break;
                case 'q':
                    q = true;
                    break;
                case 'r':
                    r = true;
                    break;
                case 's':
                    s = true;
                    break;
                case 't':
                    t = true;
                    break;
                case 'u':
                    u = true;
                    break;
                case 'v':
                    v = true;
                    break;
                case 'w':
                    w = true;
                    break;
                case 'x':
                    x = true;
                    break;
                case 'y':
                    y = true;
                    break;
                case 'z':
                    z = true;
                    break;



            }
        }
    }


    public boolean isA() {
        return a;
    }

    public boolean isB() {
        return b;
    }

    public boolean isC() {
        return c;
    }

    public boolean isD() {
        return d;
    }

    public boolean isE() {
        return e;
    }

    public boolean isF() {
        return f;
    }

    public boolean isG() {
        return g;
    }

    public boolean isH() {
        return h;
    }

    public boolean isI() {
        return i;
    }

    public boolean isJ() {
        return j;
    }

    public boolean isK() {
        return k;
    }

    public boolean isL() {
        return l;
    }

    public boolean isM() {
        return m;
    }

    public boolean isN() {
        return n;
    }

    public boolean isO() {
        return o;
    }

    public boolean isP() {
        return p;
    }

    public boolean isQ() {
        return q;
    }

    public boolean isR() {
        return r;
    }

    public boolean isS() {
        return s;
    }

    public boolean isT() {
        return t;
    }

    public boolean isU() {
        return u;
    }

    public boolean isV() {
        return v;
    }

    public boolean isW() {
        return w;
    }

    public boolean isX() {
        return x;
    }

    public boolean isY() {
        return y;
    }

    public boolean isZ() {
        return z;
    }
}
