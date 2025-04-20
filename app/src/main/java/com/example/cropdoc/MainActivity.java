package com.example.cropdoc;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.transition.Slide;
import android.transition.TransitionManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.FirebaseApp;


public class MainActivity extends AppCompatActivity {
    bottom_sheet bottom_sheet;
    FloatingActionButton FAB_camera;
    BottomNavigationView bottomNavigationView;
    private boolean doubleBackToExitPressedOnce = false;
    boolean homeFrag=true;

    static FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseApp.initializeApp(this);

fm=getSupportFragmentManager();


//object
        bottom_sheet = new bottom_sheet();

        //view
        FAB_camera = findViewById(R.id.fab);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);



        FAB_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottom_sheet.show(getSupportFragmentManager(), bottom_sheet.getTag());
            }
        });


        //bottom navigation
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
        openFragment(fragment_1.newInstance("", ""));

    }
    public void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.frame_layout, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @RequiresApi(api = Build.VERSION_CODES.M)
                @Override public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Slide slide=new Slide();
                    switch (item.getItemId()) {
                        case R.id.icon1:

                            openFragment(fragment_1.newInstance("", ""));
                            homeFrag=true;
                            return true;
                        case R.id.icon2:
                            openFragment(fragment_2.newInstance("", ""));
                            homeFrag=false;
                            return true;
                        case R.id.icon4:
                            homeFrag=false;
                            openFragment(fragment_4.newInstance("", ""));
                            return true;
                        case R.id.icon5:
                            homeFrag=false;
                            openFragment(fragment_5.newInstance("", ""));
                            return true;

                    }
                    return false;
                }
            };





    @Override
    public void onBackPressed() {
        if (homeFrag) {
            new AlertDialog.Builder(this)
                    .setMessage("Are you sure you want to exit?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            finish();
                        }
                    })
                    .setNegativeButton("No", null)
                    .show();
        } else {
            if (doubleBackToExitPressedOnce) {
                new AlertDialog.Builder(this)
                        .setMessage("Are you sure you want to exit?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                finish();
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();
                return;
            }
            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, "Press back again to exit", Toast.LENGTH_SHORT).show();
            new Handler(Looper.getMainLooper()).postDelayed(() -> doubleBackToExitPressedOnce = false, 2000);
            super.onBackPressed();
        }

    }

    }



