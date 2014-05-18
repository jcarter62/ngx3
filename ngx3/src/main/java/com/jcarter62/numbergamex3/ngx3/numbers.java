package com.jcarter62.numbergamex3.ngx3;

import java.util.Random;

/**
 * Created by jcarter on 4/8/2014.
 */
public class numbers {
    /* Properties */
    private int n1, n2, n3;
    private int low;
    private int high;
    private int lowest;
    private int highest;
    private int middle;
    /* Private */
    private Random r;

    // Constructor
    public numbers() {
        low = 1000;
        high = 1999;

        // Allocate & init random number generator.
        r = new Random(System.currentTimeMillis());
        // Reference:
        // http://stackoverflow.com/questions/3535574/getting-current-date-time-for-a-random-number-generators-seed
        generate();
    }

    private int MyRandom() {
        int x;
        x = r.nextInt(high - low) + low;
        return x;
    }

    // returns true if b is in the middle.
    private boolean isInMiddle(int a, int b, int c) {
        if ( ( ( a <= b ) && ( b <= c ) ) || ( ( a >= b) && ( b >= c )) )
            return true;
        else
            return false;
    }

    private void CalcLowHighEtc() {
        int x;
        // first highest.
        x = n2;
        if ( n1 > n2 ) x = n1;
        if ( n3 > x ) x = n3;
        highest = x;

        // lowest
        x = n1;
        if ( n1 > n2 ) x = n2;
        if ( n3 < x ) x = n3;
        lowest = x;

        // middle
        if ( isInMiddle(n1,n2,n3) )
            middle = n2;
        else if ( isInMiddle(n2,n1,n3))
            middle = n1;
        else
            middle = n3;
    }

    public void generate() {
        n1 = MyRandom();
        n2 = MyRandom();
        n3 = MyRandom();

        CalcLowHighEtc();
    }

    public void setLow(int low) {
        this.low = low;
    }

    public void setHigh(int high) {
        this.high = high;
    }

    public int getN1() {
        return n1;
    }

    public int getN2() {
        return n2;
    }

    public int getN3() {
        return n3;
    }

    public int getLow() {
        return low;
    }

    public int getHigh() {
        return high;
    }

    public int getLowest() {
        return lowest;
    }

    public int getHighest() {
        return highest;
    }

    public int getMiddle() {
        return middle;
    }
}
