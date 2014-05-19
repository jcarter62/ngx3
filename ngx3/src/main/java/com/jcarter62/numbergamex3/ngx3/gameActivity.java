package com.jcarter62.numbergamex3.ngx3;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class gameActivity extends ActionBarActivity {
    private numbers n = new numbers();
    private int correct;
    private int incorrect;
    private int score;
    private long qStart, qEnd, qDiff;
    private feedback userFeedBack = new feedback();
    private String userMessage = "";
    private int RemainingQuestions;
    private final int NUMQ = 5;
    private boolean gameOver;

    private DatabaseConnector db;

    private void tally(boolean ans) {
        if (RemainingQuestions > 0 ) {
            int thisScore;

            qEnd = System.currentTimeMillis();
            qDiff = qEnd - qStart;
            thisScore = (int)( 100.0 - ( qDiff / 100.0 ) );
            if ( thisScore < 0 ) {
                thisScore = 0;
            }

            RemainingQuestions --;
            if ( RemainingQuestions < 0 )
                RemainingQuestions = 0;

            if (ans) {
                correct++;
                score += thisScore;
                userMessage = userFeedBack.GetWord();
            } else {
                incorrect++;
                score -= ( thisScore / 2 );
                if ( score < 0 )
                    score = 0;
                userMessage = "";
            }

            n.generate();
            loadButtons();
        }

        if (RemainingQuestions <= 0 ) {
            gameOver = true;
            endGame();
        }
    }

    public void btn1Click(View v) {
        tally(n.getN1() == n.getHighest());
    }

    public void bnt2Click(View v) {
        tally(n.getN2() == n.getHighest());
    }

    public void btn3Click(View v) {
        tally(n.getN3() == n.getHighest());
    }

    private String int2str(int x) {
        StringBuilder sb = new StringBuilder();
        sb.append("");
        sb.append(x);
        String r = sb.toString();
        return r;
    }

    private void loadButtons() {
        Button b;
        if ( RemainingQuestions > 0 ) {
            b = (Button) findViewById(R.id.btn1);
            b.setText(int2str(n.getN1()));
            b = (Button) findViewById(R.id.btn2);
            b.setText(int2str(n.getN2()));
            b = (Button) findViewById(R.id.btn3);
            b.setText(int2str(n.getN3()));
            b = null;
        } else {
            b = (Button) findViewById(R.id.btn1);
            b.setText("");
            b = (Button) findViewById(R.id.btn2);
            b.setText("");
            b = (Button) findViewById(R.id.btn3);
            b.setText("");
            b = null;
        }
        TextView t;
        t = (TextView) findViewById(R.id.textCorrect);
        t.setText("Correct:" + int2str(correct));
        t = (TextView) findViewById(R.id.textIncorrect);
        t.setText("Incorrect:" + int2str(incorrect));
        t = null;
        // feedback message
        t = (TextView) findViewById(R.id.userFeedback);
        t.setText(userMessage);
        // display current score
        t = (TextView) findViewById(R.id.textScore);
        t.setText("Score:" + int2str(score));
        //
        qStart = System.currentTimeMillis();
        qEnd = qStart;
        qDiff = qEnd - qStart;
    }

    private void startGame() {
        RemainingQuestions = NUMQ;
        score = 0;
        gameOver = false;
    }

    private void showMessage( String msg ) {
        Context context = getApplicationContext();
        CharSequence text = msg;
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    private void endGame() {
        // show toast message with score.
        showMessage("Game Over, Your Score: " + int2str(score));

        Context context = getApplicationContext();
        db = new DatabaseConnector(context);
        db.addScore(score);
        db.close();
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        RemainingQuestions = NUMQ;
        loadButtons();
        startGame();
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
