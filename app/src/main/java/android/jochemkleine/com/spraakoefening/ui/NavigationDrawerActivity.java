package android.jochemkleine.com.spraakoefening.ui;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.jochemkleine.com.spraakoefening.R;
import android.jochemkleine.com.spraakoefening.data.DutchDictionary;
import android.jochemkleine.com.spraakoefening.data.Word;
import android.jochemkleine.com.spraakoefening.tasks.InitWordListTask;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class NavigationDrawerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private ArrayList<Word> dutchDictionary;
    private Toolbar toolbar;
    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        // TODO Replace with SQLite db system
        if (DutchDictionary.wordList.size() == 0) {
            try {
                DutchDictionary.initialize(new InitWordListTask().execute(context).get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        System.out.println("DUTCH DICT. SIZE : " + DutchDictionary.wordList.size());

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Fragment fragment;
        FragmentManager fragmentManager = getFragmentManager();
        fragment = new PracticeSelectionFragment();
        fragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commit();

    }

    public Toolbar getToolbar (){
        return toolbar;
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Fragment fragment;
        FragmentManager fragmentManager = getFragmentManager();

        if (id == R.id.nav_practice) {
            fragment = new PracticeSelectionFragment();
            fragmentManager.beginTransaction()
                                        .replace(R.id.container, fragment)
                                        .commit();
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public ArrayList<Word> getDictionary () {
        return dutchDictionary;
    }

    public boolean dictionaryInitialised (){
        if (dutchDictionary != null) {
            return true;
        }
        return false;
    }

    public void initDictionary (){
        Context context = this;
        try {
            dutchDictionary = new InitWordListTask().execute(context).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
