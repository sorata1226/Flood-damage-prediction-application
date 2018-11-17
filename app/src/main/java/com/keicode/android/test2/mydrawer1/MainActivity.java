package com.keicode.android.test2.mydrawer1;

import android.content.Intent;
import android.os.Bundle;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    static String TAG = "MainActivity";
    private SoundPool soundPool;
    private int soundOne, soundTwo;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // DrawerToggle
        DrawerLayout drawer =
                (DrawerLayout) findViewById(R.id.drawerLayout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar,
                R.string.drawer_open,
                R.string.drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        // NavigationView Listener
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(this);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//
//
//
//                button = findViewById(R.id.button);
                soundPool.play(soundOne, 3.0f, 3.0f, 0, 0, 1);

                Intent intent = new Intent(
                        getApplicationContext(), KenActivity.class);

                // SubActivityへ遷移
                startActivity(intent);
//
//
//                button.setOnClickListener(new View.OnClickListener(){
//                    @Override
//                    public void onClick(View v){
//
//
//                    }
//                });
//
            }

        });


    }

    @Override
    protected void onResume() {
        super.onResume();

        AudioAttributes audioAttributes = new AudioAttributes.Builder()
                // USAGE_MEDIA
                // USAGE_GAME
                .setUsage(AudioAttributes.USAGE_GAME)
                // CONTENT_TYPE_MUSIC
                // CONTENT_TYPE_SPEECH, etc.
                .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
                .build();

        soundPool = new SoundPool.Builder()
                .setAudioAttributes(audioAttributes)
                // ストリーム数に応じて
                .setMaxStreams(2)
                .build();

        // one.wav をロードしておく
        soundOne = soundPool.load(this, R.raw.sound1, 1);

        // load が終わったか確認する場合
        soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                Log.d("debug", "sampleId=" + sampleId);
                Log.d("debug", "status=" + status);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                Log.d(TAG, "Settings Selected!");
                break;
        }
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (item.getItemId()) {
            case R.id.menu_item1:
                Log.d(TAG, "Item 1 Selected!");
                break;
            case R.id.menu_item2:
                Log.d(TAG, "Item 2 Selected!");
                break;
            case R.id.menu_item3:
                Log.d(TAG, "Item 3 Selected!");
                break;
            case R.id.menu_item4:
                Log.d(TAG, "Item 4 Selected!");
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawerLayout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
