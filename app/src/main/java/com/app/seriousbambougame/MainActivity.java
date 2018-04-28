package com.app.seriousbambougame;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_activity);


        /** Initialisation du menu*/
        setupNavigationView();

        // Set up the user interaction to manually show or hide the system UI.

        // Upon interacting with UI controls, delay any scheduled hide()
        // operations to prevent the jarring behavior of controls going away
        // while interacting with the UI
    }

    private void setupNavigationView() {
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById( R.id.bottom_navigation );
        if (bottomNavigationView != null) {

            // Select first menu item by default and show Fragment accordingly.
            Menu menu = bottomNavigationView.getMenu();
            menuCreate( menu.getItem( 0 ) );

            // Set action to perform when any menu-item is selected.
            bottomNavigationView.setOnNavigationItemSelectedListener(
                    new BottomNavigationView.OnNavigationItemSelectedListener() {
                        @Override
                        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                            menuCreate( item );
                            return false;
                        }
                    } );
        }
    }

    public void menuCreate(MenuItem item) {


        switch (item.getItemId()) {

            case R.id.maps_fragment:
                item.setChecked( true );

                pushFragment(  getResources().getString( R.string.title_maps ), new FragmentAccount());
                break;

        }
    }


    public void pushFragment(String titleToolbar, Fragment fragment) {
        if (fragment == null)
            return;

        android.app.FragmentManager fragmentManager = getFragmentManager();
        if (fragmentManager != null) {
            FragmentTransaction ft = fragmentManager.beginTransaction();
            if (ft != null) {

                getSupportActionBar().setTitle(titleToolbar);
                ft.replace( R.id.root_fragment, fragment );
                ft.commit();
            }
        }
    }
}
