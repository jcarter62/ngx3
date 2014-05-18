package com.jcarter62.numbergamex3.ngx3;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;


public class gameActivity extends ActionBarActivity {
    private numbers n = new numbers();
    private int correct;
    private int incorrect;
    private feedback userFeedBack = new feedback();
    private String userMessage = "";

    private void tally(boolean ans) {
        if ( ans ) {
            correct++;
            userMessage = userFeedBack.GetWord();
        }
        else {
            incorrect++;
            userMessage = "";
        }

        n.generate();
        loadButtons();
    }

    public void btn1Click(View v ) {
        tally(n.getN1() == n.getHighest());
    }

    public void bnt2Click(View v) {
        tally( n.getN2() == n.getHighest() );
    }

    public void btn3Click(View v) {
        tally( n.getN3() == n.getHighest() );
    }

    private String int2str( int x ) {
        StringBuilder sb = new StringBuilder();
        sb.append("");
        sb.append(x);
        String r  = sb.toString();
        return r;
    }

    private void loadButtons() {
        Button b;
        b = (Button)findViewById( R.id.btn1);
        b.setText( int2str(n.getN1()));
        b = (Button)findViewById(R.id.btn2);
        b.setText( int2str(n.getN2()));
        b = (Button)findViewById(R.id.btn3);
        b.setText( int2str(n.getN3()));
        b = null;
        TextView t;
        t = (TextView)findViewById(R.id.textCorrect);
        t.setText("Correct:" + int2str(correct) );
        t = (TextView)findViewById(R.id.textIncorrect);
        t.setText("Incorrect:" + int2str(incorrect) );
        t = null;
        // feedback message
        t = (TextView)findViewById(R.id.userFeedback);
        t.setText(userMessage);

    }

    @Override
    protected void onResume() {
        super.onResume();
        loadButtons();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.game, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
