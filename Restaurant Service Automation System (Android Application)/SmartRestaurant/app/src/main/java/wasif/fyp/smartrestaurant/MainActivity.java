package wasif.fyp.smartrestaurant;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.util.List;

import wasif.fyp.smartrestaurant.Fragments.HelpFragment;
import wasif.fyp.smartrestaurant.Fragments.NotificationsFragment;
import wasif.fyp.smartrestaurant.Fragments.YourOrderFragment;
import wasif.fyp.smartrestaurant.Tabs.ProfileFragment;
import wasif.fyp.smartrestaurant.Tabs.ProjectsFragment;
import wasif.fyp.smartrestaurant.Tabs.ServicesFragment;

import static wasif.fyp.smartrestaurant.SignInActivity.PREFERENCE;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG_HOME = "home";
    private static final String TAG_HISTORY = "history";
    private static final String TAG_PROFILE = "profile";
    private static final String TAG_LOGOUT = "logout";
    private static final String TAG_SUPPORT = "support";
    private static final String TAG_NO = "no";
    public static int navItemIndex = 0;
    public static String CURRENT_TAG = TAG_HOME;
    public static String CURRENT_TAG_NEW = TAG_NO;
    SharedPreferences mSharedPreferences;
    private TextView mToolbarTextView, mTextMessage;
    private Handler mHandler;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {

                case R.id.navigation_services:
                    loadHomeFragment(1);

                    return true;
                case R.id.navigation_profile:
                    loadHomeFragment(2);

                    return true;
                case R.id.navigation_search:
                    loadHomeFragment(3);

                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mHandler = new Handler();
        mSharedPreferences = getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE);
        Dexter.withActivity(this)
                .withPermissions(
                        android.Manifest.permission.ACCESS_FINE_LOCATION,
                        android.Manifest.permission.ACCESS_COARSE_LOCATION
                ).withListener(new MultiplePermissionsListener() {
            @Override
            public void onPermissionsChecked(MultiplePermissionsReport report) {
            }

            @Override
            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
            }

        }).check();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbarTextView = toolbar.findViewById(R.id.toolbar_title);
        setSupportActionBar(toolbar);
        loadHomeFragment(0);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
//                Intent i = new Intent(MainActivity.this,  ChatActivity.class);
//                startActivity(i);
//                finish();


            }
        });
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
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
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_order) {
            mToolbarTextView.setText("Special Deals");
            Fragment fragment = new YourOrderFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
            fragmentTransaction.replace(R.id.frame, fragment);
            fragmentTransaction.commitAllowingStateLoss();
        } else if (id == R.id.nav_notifications) {
            mToolbarTextView.setText("Get Discount");
            Fragment fragment = new NotificationsFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
            fragmentTransaction.replace(R.id.frame, fragment);
            fragmentTransaction.commitAllowingStateLoss();
        } else if (id == R.id.nav_help) {
            mToolbarTextView.setText("Help");
            Fragment fragment = new HelpFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
            fragmentTransaction.replace(R.id.frame, fragment);
            fragmentTransaction.commitAllowingStateLoss();
        } else if (id == R.id.nav_signout) {
            Intent i = new Intent(MainActivity.this, IntroLocationActivity.class);
            SharedPreferences.Editor mEditor = mSharedPreferences.edit();
            mEditor.clear();
            mEditor.commit();
            startActivity(i);
            finish();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void loadHomeFragment(final int i) {
        Runnable mPendingRunnable = new Runnable() {
            @Override
            public void run() {
                // update the main content by replacing fragments
                Fragment fragment = getHomeFragment(i);
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
                fragmentTransaction.replace(R.id.frame, fragment, CURRENT_TAG);
                fragmentTransaction.commitAllowingStateLoss();
            }
        };
        // If mPendingRunnable is not null, then add to the message queue
        if (mPendingRunnable != null) {
            mHandler.post(mPendingRunnable);
        }
    }

    private Fragment getHomeFragment(int i) {
        switch (i) {

            case 1:
                // service fragment
                mToolbarTextView.setText("Select Menu");
                mToolbarTextView.setVisibility(View.VISIBLE);
                ServicesFragment servicesFragment = new ServicesFragment();
                return servicesFragment;
            case 2:
                // profile fragment
                mToolbarTextView.setText("Profile");
                mToolbarTextView.setVisibility(View.VISIBLE);
                ProfileFragment profileFragment = new ProfileFragment();
                return profileFragment;
            case 3:
                // project fragment
                mToolbarTextView.setText("Orders");
                mToolbarTextView.setVisibility(View.VISIBLE);
                ProjectsFragment searchFragment = new ProjectsFragment();
                return searchFragment;
            default:
                return new ServicesFragment();
        }
    }
}
