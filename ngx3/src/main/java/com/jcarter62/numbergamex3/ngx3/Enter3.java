package com.jcarter62.numbergamex3.ngx3;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class Enter3 extends ActionBarActivity {

    int n1;
    int n2;
    int n3;
    int nLarge;

    public Enter3() {
        ClearNumbers();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter3);
    }

    private void ClearNumbers() {
        n1 = 0;
        n2 = 0;
        n3 = 0;
        nLarge = 0;
    }

    public void onClear(View v) {
        ClearNumbers();
        CalcLarge();
        updateNumbers();
    }

    private void updateNumbers() {
        EditText e;
        e = (EditText) findViewById(R.id.n1Text);
        e.setText(int2str(n1));
        e = (EditText) findViewById(R.id.n2Text);
        e.setText(int2str(n2));
        e = (EditText) findViewById(R.id.n3Text);
        e.setText(int2str(n3));
        TextView r;
        r = (TextView) findViewById(R.id.n4Text);
        r.setText(int2str(nLarge));
    }

    private String int2str(int x) {
        StringBuilder sb = new StringBuilder();
        sb.append("");
        sb.append(x);
        String r = sb.toString();
        return r;
    }

    public void onCompute(View v) {
        LoadNumbers();
        CalcLarge();

        TextView e;
        e = (TextView) findViewById(R.id.n4Text);
        e.setText(int2str(nLarge));
    }

    private void LoadNumbers() {
        EditText e;
        e = (EditText) findViewById(R.id.n1Text);
        n1 = Integer.parseInt(e.getText().toString());
        e = (EditText) findViewById(R.id.n2Text);
        n2 = Integer.parseInt(e.getText().toString());
        e = (EditText) findViewById(R.id.n3Text);
        n3 = Integer.parseInt(e.getText().toString());
    }

    public void CalcLarge() {
        int x;
        x = n1;
        if (n1 < n2)
            x = n2;
        if (x < n3)
            x = n3;
        nLarge = x;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.enter3, menu);
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
