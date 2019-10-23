package com.example.aks.MyLabs;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TabHost;

public class User_area extends TabActivity {
    //private DrawerLayout mDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);
       /* Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
       // setSupportActionBar(toolbar);
        //ActionBar actionBar=getSupportActionBar();
        //actionBar.setDisplayHomeAsUpEnabled(true);
        //actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_icon);
        mDrawer= (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView mNavigationView= (NavigationView) findViewById(R.id.nav_view);
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                mDrawer.closeDrawers();

                return true;
            }
        });*/
       TabHost tabhost = (TabHost)findViewById(android.R.id.tabhost);
        TabHost.TabSpec tabspec;
        Intent intent;
        MenuInflater inflator = null;
        //Home tab
        tabspec= tabhost.newTabSpec("home");
        tabspec.setIndicator("",getResources().getDrawable(R.drawable.ic_home));
        intent =new Intent(this,Test.class);
        tabspec.setContent(intent);
        tabhost.addTab(tabspec);

        //Profile tab
        tabspec=tabhost.newTabSpec("Profile");
        tabspec.setIndicator("",getResources().getDrawable(R.drawable.ic_profile));
        intent=new Intent(this,Profile.class);
        tabspec.setContent(intent);
        tabhost.addTab(tabspec);

        //History tab
        tabspec= tabhost.newTabSpec("History");
        tabspec.setIndicator("",getResources().getDrawable(R.drawable.ic_history));
        intent =new Intent(this,Test.class);
        tabspec.setContent(intent);
        tabhost.addTab(tabspec);

        //copyright
        tabspec= tabhost.newTabSpec("Copyright");
        tabspec.setIndicator("",getResources().getDrawable(R.drawable.ic_copyright));
        intent =new Intent(this,Test.class);
        tabspec.setContent(intent);
        tabhost.addTab(tabspec);

        //About
        tabspec= tabhost.newTabSpec("About");
        tabspec.setIndicator("",getResources().getDrawable(R.drawable.ic_info));
        intent =new Intent(this,Test.class);
        tabspec.setContent(intent);
        tabhost.addTab(tabspec);

    }

    /*@Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case android.R.id.home:
                mDrawer.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }*/
}
