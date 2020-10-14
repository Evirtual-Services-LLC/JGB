package com.evs.jgb.ui.activity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import com.evs.jgb.R;
import com.evs.jgb.adapter.NevigationListAdapter;
import com.evs.jgb.ui.fragment.AgingFragment;
import com.evs.jgb.ui.fragment.BalancingFragment;
import com.evs.jgb.ui.fragment.DashBoard;
import com.evs.jgb.ui.fragment.HelpScreen;
import com.evs.jgb.ui.fragment.International;
import com.evs.jgb.ui.fragment.LivingFragment;
import com.evs.jgb.ui.fragment.Parenting;
import com.evs.jgb.ui.fragment.PrivacyPolicy;
import com.evs.jgb.ui.fragment.TermsOfUse;
import com.evs.jgb.ui.fragment.ThrivingFragment;
import com.evs.jgb.ui.fragment.WorkingFragment;
import com.evs.jgb.utils.Global;
import com.evs.jgb.utils.SessionManager;
import com.evs.jgb.utils.Utills;
import com.evs.jgb.viewModels.UserViewModel;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.LinkedHashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    FragmentManager manager;
    ListView list_nav;
    NevigationListAdapter list_adapter;
    ActionBarDrawerToggle toggle;
    SharedPreferences prefs;
    //   ProgressDialog progress;
    TextView edit_profile;
    TextView txt_username, txt_userphone;
    UserViewModel userViewModel;
    private ProgressDialog progressDialog;
    RoundedImageView userImg;
    String username_Str, userphone_Str;
    private String currentFragName;
    @BindView(R.id.toolbar)
    Toolbar mainToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        prefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        progressDialog = Global.getProgressDialog(MainActivity.this, "Please wait...");
        Utills.StatusBarColour(MainActivity.this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        manager = getSupportFragmentManager();
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        userViewModel = ViewModelProviders.of(MainActivity.this).get(UserViewModel.class);
        txt_username = (TextView) findViewById(R.id.txt_username);
        txt_userphone = (TextView) findViewById(R.id.txt_userphone);
        userImg = findViewById(R.id.userImg);
        loadFragment(new DashBoard(), false);
//        queue = Volley.newRequestQueue(MainActivity.this);

        /*progress = new ProgressDialog(MainActivity.this);
        progress.setMessage("Loading");
        progress.setCancelable(true);*/

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        //ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        System.out.println("User Name == " + SessionManager.get_name(prefs) + " Contact Number == " + SessionManager.get_mobile(prefs) + ", And ID == " + SessionManager.get_user_id(prefs));

        list_nav = (ListView) findViewById(R.id.list_nav);
        nevigationlist();

        list_nav.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                updatedisplay(position);
            }
        });

        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new DashBoard()).commit();

    }

    @Override
    protected void onResume() {
        super.onResume();
        prefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
      /*  username_Str = SessionManager.get_name(prefs);
        userphone_Str = SessionManager.get_mobile(prefs);*/
        System.out.println("User Name == " + SessionManager.get_name(prefs) + " Contact Number == " + SessionManager.get_mobile(prefs));


        //     Glide.with(MainActivity.this).load(SessionManager.get_image(prefs)).placeholder(R.drawable.placeholder_user).into(userImg);


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (manager.getBackStackEntryCount() > 0) {
                if (manager.getBackStackEntryCount() == 2) {
                    mainToolbar.setVisibility(View.VISIBLE);

                }
                super.onBackPressed();
            } else {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
                alertDialog.setTitle("");
                alertDialog.setMessage("Do you really want to Exit?");
                alertDialog.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        });
                alertDialog.setNegativeButton("No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });
                alertDialog.show();

            }
        }

    }

    public void updatedisplay(int position) {
        Fragment fragment = null;
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        switch (position) {
            case 0:
                fragment = new DashBoard();
                loadFragment(fragment, true);
                break;
            case 1:
                fragment = new Parenting();
                loadMainFragment(fragment, true);
                break;
            case 2:
                fragment = new AgingFragment();
                loadMainFragment(fragment, true);

                break;
            case 3:
                fragment = new BalancingFragment();
                loadMainFragment(fragment, true);
                break;
            case 4:
                fragment = new ThrivingFragment();
                loadMainFragment(fragment, true);
                break;
            case 5:
                fragment = new WorkingFragment();
                loadMainFragment(fragment, true);
                break;
            case 6:
                fragment = new LivingFragment();
                loadMainFragment(fragment, true);
                break;
            case 7:
                fragment = new International();
                loadFragment(fragment, true);

                break;
            case 8:
                fragment = new HelpScreen();
                loadFragment(fragment, true);
                break;
            case 9:
                fragment = new PrivacyPolicy();
                loadFragment(fragment, true);
                break;
            case 10:
                fragment = new TermsOfUse();
                loadFragment(fragment, true);
                break;
            case 11:
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
                alertDialog.setTitle("");
                alertDialog.setMessage("Do you really want to logout?");
                alertDialog.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
//                                logout();

                                boolean b = SessionManager.get_remember(prefs);
                                String s = SessionManager.get_emailid(prefs);
                                SessionManager.dataclear(prefs);
                                SessionManager.save_check_login(prefs, false);
                                SessionManager.save_check_agreement(prefs, true);
                                SessionManager.save_remember(prefs, b);
                                SessionManager.save_emailid(prefs, s);
                                Intent intent = new Intent(MainActivity.this, WelcomeStarted.class);
                                startActivity(intent);
                                finish();

                                //       userViewModel.logoutUser(SessionManager.get_user_id(prefs), MainActivity.this);

                            }
                        });
                alertDialog.setNegativeButton("No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });
                alertDialog.show();
                break;
        }
        if (fragment != null) {
            manager.beginTransaction().replace(R.id.frame_container, fragment)
                    .addToBackStack(null).commit();
        }
    }

    private void nevigationlist() {
        LinkedHashMap<String, Integer> data = new LinkedHashMap<>();
        data.put(getResources().getString(R.string.home), R.drawable.home);
        data.put(getResources().getString(R.string.parenting), R.drawable.note2);
        data.put(getResources().getString(R.string.aging), R.drawable.note2);
        data.put(getResources().getString(R.string.balancing), R.drawable.note2);
        data.put(getResources().getString(R.string.thriving), R.drawable.note2);
        data.put(getResources().getString(R.string.working), R.drawable.note2);
        data.put(getResources().getString(R.string.living), R.drawable.note2);
        data.put(getResources().getString(R.string.international), R.drawable.note2);
        data.put(getResources().getString(R.string.help), R.drawable.help);
        data.put(getResources().getString(R.string.privacy_policy), R.drawable.note2);
        data.put(getResources().getString(R.string.terms_of_use), R.drawable.help);
        data.put(getResources().getString(R.string.logout), R.drawable.logout);
        list_adapter = new NevigationListAdapter(MainActivity.this, data);
        list_nav.setAdapter(list_adapter);
    }

    @Override
    public void setTitle(CharSequence title) {
        super.setTitle(title);
        TextView customToolbarTitleTxt = (TextView) findViewById(R.id.text_toolbar);
        customToolbarTitleTxt.setText(title);
    }

    public void loadFragment(@NonNull Fragment fragment, boolean clearTillHome) {
        String fragmentName = fragment.getClass().getSimpleName();
        currentFragName = fragmentName;
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (isFragmentInBackStack(fragmentManager, fragment.getClass().getSimpleName())) {
            // Fragment exists, go back to that fragment
            fragmentManager.popBackStack(fragment.getClass().getSimpleName(), 0);
        } else {
            if (clearTillHome)
                fragmentManager.popBackStack(MainActivity.class.getSimpleName(), 0);
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_container, fragment, fragmentName)
                    .addToBackStack(fragmentName)
                    .commit();
        }
        mainToolbar.setVisibility(View.VISIBLE);
    }
    public void loadMainFragment(@NonNull Fragment fragment, boolean clearTillHome) {
        String fragmentName = fragment.getClass().getSimpleName();
        currentFragName = fragmentName;
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (isFragmentInBackStack(fragmentManager, fragment.getClass().getSimpleName())) {
            // Fragment exists, go back to that fragment
            fragmentManager.popBackStack(fragment.getClass().getSimpleName(), 0);
        } else {
            if (clearTillHome)
                fragmentManager.popBackStack(MainActivity.class.getSimpleName(), 0);
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_container, fragment, fragmentName)
                    .addToBackStack(fragmentName)
                    .commit();
        }
        mainToolbar.setVisibility(View.GONE);
    }
    public static boolean isFragmentInBackStack(final FragmentManager fragmentManager, final String fragmentTagName) {
        for (int entry = 0; entry < fragmentManager.getBackStackEntryCount(); entry++) {
            if (fragmentTagName.equals(fragmentManager.getBackStackEntryAt(entry).getName())) {
                return true;
            }
        }
        return false;
    }
    @SuppressLint("WrongConstant")
    public void replaceFragment(Fragment fragment, String backstack_name) {
        FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.frame_container, fragment, backstack_name);
        fragmentTransaction.addToBackStack(backstack_name);
        fragmentTransaction.commit();
    }
}