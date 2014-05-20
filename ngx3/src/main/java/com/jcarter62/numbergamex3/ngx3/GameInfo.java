package com.jcarter62.numbergamex3.ngx3;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

public class GameInfo extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_game_info);
        if (savedInstanceState == null) {
            OpenFrag1();
        }
    }

    private void OpenFrag1() {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, new PlaceholderFragment1())
                .addToBackStack("frag1")
                .commit();
    }

    private void OpenFrag2() {
        getSupportFragmentManager()
                .popBackStack();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, new PlaceholderFragment2())
                .commit();
    }

    public void btnNext(View view) {
        OpenFrag2();
    }

    public void btnFinish(View view) {
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.game_info, menu);
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

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment1 extends Fragment {
        public View thisView;

        public PlaceholderFragment1() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.frag_game_info, container, false);
            thisView = rootView;
            return rootView;
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment2 extends Fragment {
        public View thisView;

        public PlaceholderFragment2() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.frag_game_info2, container, false);
            thisView = rootView;
            return rootView;
        }
    }


}
