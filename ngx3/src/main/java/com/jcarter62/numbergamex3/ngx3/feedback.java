package com.jcarter62.numbergamex3.ngx3;

import java.util.Random;

/**
 * Created by jim on 4/9/14.
 */
public class feedback {

    private Random r;
    private String lastword;
    private int numberOfWords;

    // word from resource:
    // http://www.k-3teacherresources.com/positive-words.html#.U0T-v-ZdVj4
    private final String[] goodwords = new String[]
            {
                    "bravo","wow","super","terrific","cool","amazing","superb","brilliant",
                    "fantastic","fabulous","champion","tops","congratulations","lovely",
                    "spot on","well done","you rock","great job","tip top","good thinking",
                    "you can do it","keep it up","great choice","way to go","right on",
                    "top stuff","take a bow","unreal","impressed","great stuff","awesome",
                    "nice going"
            };

    // Constructor
    public feedback() {
        // Allocate & init random number generator.
        r = new Random(System.currentTimeMillis());
        lastword = "";
        numberOfWords = goodwords.length;
    }

    public String GetWord(){
        String rtrn;
        try {
            rtrn = goodwords[r.nextInt(numberOfWords)];
            // try to not send the same word twice.
            if ( rtrn == lastword )
                rtrn = goodwords[r.nextInt(numberOfWords)];
        } catch (Exception e ) {
            rtrn = goodwords[0];
        }
        lastword = rtrn;
        return rtrn;
    }
}
