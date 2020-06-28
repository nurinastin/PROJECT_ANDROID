package com.project.sinisa.dashboard;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.navigation.NavigationView;
import com.project.sinisa.R;
import com.project.sinisa.akun.Sign_In;
import com.project.sinisa.config.AuthData;

import de.hdodenhof.circleimageview.CircleImageView;

public class Dashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private FrameLayout frameLayout;
    private NavigationView navigationView;
    private SwitchCompat darkModeSwitch;
    TextView nama, nik, namaUser, nikUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        initializeViews();
        toggleDrawer();
        initializeDefaultFragment(savedInstanceState,0);
//        setDarkModeSwitchListener();

    }
    /**
     * Initialize all widgets
     */
    private void initializeViews() {
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Sinisa");

        setSupportActionBar(toolbar);
        nama = findViewById(R.id.nama);
        nik = findViewById(R.id.nik);
        nama.setText(AuthData.getInstance(getBaseContext()).getNama_depan());
        nik.setText(AuthData.getInstance(getBaseContext()).getNik());
        drawerLayout = findViewById(R.id.drawer_layout_id);
        frameLayout = findViewById(R.id.framelayout_id);
        navigationView = findViewById(R.id.navigationview_id);
        navigationView.setNavigationItemSelectedListener(this);
        View headerView = navigationView.getHeaderView(0);
        namaUser = headerView.findViewById(R.id.namaUser);
        nikUser = headerView.findViewById(R.id.nikUser);
        nikUser.setText(AuthData.getInstance(getBaseContext()).getNik());
        namaUser.setText(AuthData.getInstance(getBaseContext()).getNama_depan());
    }

    /**
     * Checks if the savedInstanceState is null - onCreate() is ran
     * If so, display fragment of navigation drawer menu at position itemIndex and
     * set checked status as true
     * @param savedInstanceState
     * @param itemIndex
     */
    private void initializeDefaultFragment(Bundle savedInstanceState, int itemIndex){
        if (savedInstanceState == null){
            MenuItem menuItem = navigationView.getMenu().getItem(itemIndex).setChecked(true);
            onNavigationItemSelected(menuItem);
        }
    }

    /**
     * Creates an instance of the ActionBarDrawerToggle class:
     * 1) Handles opening and closing the navigation drawer
     * 2) Creates a hamburger icon in the toolbar
     * 3) Attaches listener to open/close drawer on icon clicked and rotates the icon
     */
    private void toggleDrawer() {
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
    }

    @Override
    public void onBackPressed() {
        //Checks if the navigation drawer is open -- If so, close it
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        // If drawer is already close -- Do not override original functionality
        else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.home:
                getSupportFragmentManager().beginTransaction().replace(R.id.framelayout_id, new Fragment_Dashboard())
                        .commit();
                closeDrawer();
                break;
            case R.id.hubungi:
                getSupportFragmentManager().beginTransaction().replace(R.id.framelayout_id, new Hubungi())
                        .commit();
                closeDrawer();
                break;
            case R.id.tentang:
                getSupportFragmentManager().beginTransaction().replace(R.id.framelayout_id, new Tentang())
                        .commit();
                closeDrawer();
                break;
            case R.id.keluar:
                new AlertDialog.Builder(Dashboard.this,R.style.AppCompatAlertDialogStyle)
                        .setIcon(R.drawable.logo)
                        .setTitle("Keluar Akun")
                        .setMessage("Apakah Anda Yakin Ingin Keluar Dari Akun?")
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                AuthData.getInstance(Dashboard.this).logout();
                                finish();
                                startActivity(new Intent(Dashboard.this, Sign_In.class));
                            }

                        })
                        .setNegativeButton("Tidak", null)
                        .show();
                deSelectCheckedState();
                closeDrawer();
                break;
        }
        return true;
    }

    /**
     * Attach setOnCheckedChangeListener to the dark mode switch
     */
    private void setDarkModeSwitchListener(){
        darkModeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked){
                    Toast.makeText(Dashboard.this, "Dark Mode Turn Off", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(Dashboard.this, "Dark Mode Turn On", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * Checks if the navigation drawer is open - if so, close it
     */
    private void closeDrawer(){
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    /**
     * Iterates through all the items in the navigation menu and deselects them:
     * removes the selection color
     */
    private void deSelectCheckedState(){
        int noOfItems = navigationView.getMenu().size();
        for (int i=0; i<noOfItems;i++){
            navigationView.getMenu().getItem(i).setChecked(false);
        }
    }
}
