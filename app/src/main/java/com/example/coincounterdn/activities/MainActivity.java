package com.example.coincounterdn.activities;

import android.os.Bundle;

import com.example.coincounterdn.R;
import com.example.coincounterdn.lib.Utils;
import com.example.coincounterdn.models.CoinCounter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private CoinCounter mCoinCounter;
    private EditText mEt_Penny, mEt_Nickel, mEt_Dime, mEt_Quarter;
    private TextView mTv_statusMssg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupToolbar();
        setupModelAndViews();
        setupFab();
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void setupModelAndViews() {
        mCoinCounter = new CoinCounter();
        mEt_Penny = findViewById(R.id.et_penny);
        mEt_Nickel = findViewById(R.id.et_nickel);
        mEt_Dime = findViewById(R.id.et_dime);
        mEt_Quarter = findViewById(R.id.et_quarter);
        mTv_statusMssg = findViewById(R.id.tv_status);
    }

    private void setupFab() {
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleFABClick();
            }
        });
    }

    private void handleFABClick() {
        mCoinCounter.setCountOfPennies(mEt_Penny.getText().toString());
        mCoinCounter.setCountOfNickels(mEt_Nickel.getText().toString());
        mCoinCounter.setCountOfDimes(mEt_Dime.getText().toString());
        mCoinCounter.setCountOfQuarters(mEt_Quarter.getText().toString());
        
        mTv_statusMssg.setText(
                String.format(Locale.getDefault(), "%s: %d",
                        getString(R.string.total),
                        mCoinCounter.getCentsValueTotal()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if(id == R.id.clear_all) {
            clearAll();
            return true;
        }
        if (id == R.id.about) {
            showAbout();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void clearAll() {
        mEt_Penny.setText(R.string.numberHint);
        mEt_Nickel.setText(R.string.numberHint);
        mEt_Dime.setText(R.string.numberHint);
        mEt_Quarter.setText(R.string.numberHint);
    }

    private void showAbout() {
        Utils.showInfoDialog (MainActivity.this,
                R.string.about, R.string.about_text);
    }
}