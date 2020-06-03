package com.example.tictactoe;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    final int[][] arr = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {1, 4, 7}, {2, 5, 8}, {3, 6, 9}, {1, 5, 9}, {3, 5, 7}};
    Player pl1 = new Player(1);
    Player pl2 = new Player(2);
    TextView view;
    int[] id = new int[9];
    int k = 0;
    public void dropin(View view) {
        ImageView ctr = (ImageView) view;
        if (this.view.getVisibility() == View.VISIBLE)
            Toast.makeText(this, "press restart buton", Toast.LENGTH_SHORT).show();
        else {
            if (!in(id, ctr.getId())) {
                add(ctr.getId());
                if (getActivePlayer() == pl1) {
                    Toast.makeText(this, getActivePlayer().toString(), Toast.LENGTH_SHORT).show();
                    pl1.clicks[0] = Integer.parseInt(ctr.getTag().toString());
                    setActivePlayer(pl1, pl2);
                    ctr.setTranslationY(-1500);
                    ctr.setImageResource(R.drawable.x);
                    ctr.animate().translationYBy(1500).rotation(360).setDuration(500);
                } else if (getActivePlayer() == pl2) {
                    Toast.makeText(this, getActivePlayer().toString(), Toast.LENGTH_SHORT).show();
                    pl2.clicks[0] = Integer.parseInt(ctr.getTag().toString());
                    setActivePlayer(pl2, pl1);
                    ctr.setTranslationY(-1500);
                    ctr.setImageResource(R.drawable.o);
                    ctr.animate().translationYBy(1500).rotation(360).setDuration(500);
                }
            } else {
                Toast.makeText(this, "filled cell", Toast.LENGTH_SHORT).show();
            }
        }

        getWinner(pl1.clicks, pl2.clicks);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //initilly pl2 is active
        ///when activity is created pl1 becomes active
        setActivePlayer(pl2, pl1);
        Log.d("Active Player", "pl1");
        view = findViewById(R.id.textView);
        view.setVisibility(View.INVISIBLE);


    }

    public Player getActivePlayer() {
        if (pl1.getFlag()) {
            return pl1;
        } else
            return pl2;
    }

    public void setActivePlayer(Player pl_active, Player pl_inactive) {
        pl_active.setFlag(false);
        pl_inactive.setFlag(true);
    }

    public boolean in(int[] arr, int id) {
        for (int value : arr) {
            if (value == id) {
                return true;
            }
        }
        return false;
    }

    public void add(int id) {
        this.id[k++] = id;
    }

    public void getWinner(int[] pl1_arr, int[] pl2_arr) {
        Arrays.sort(pl1_arr);
        Arrays.sort(pl2_arr);
        String pl1 = getArrayString(pl1_arr);
        String pl2 = getArrayString(pl2_arr);
        for (int[] arrs : arr) {
            System.out.println(getArrayString(arrs) + "===================" + pl1);
            if (pl1.contains(getArrayString(arrs))) {
                view.setText(R.string.pl1);
                view.setVisibility(View.VISIBLE);
            }
        }
        for (int[] arrs : arr) {
            System.out.println(getArrayString(arrs) + "===================" + pl2);

            if (pl2.contains(getArrayString(arrs))) {
                view.setText(R.string.pl2);
                view.setVisibility(View.VISIBLE);

            }
        }

    }

    public void restart(View view) {
        GridLayout gl = findViewById(R.id.gridlayout);
        for (int i = 0; i < gl.getChildCount(); i++) {
            ImageView ctr = (ImageView) gl.getChildAt(i);
            ctr.setImageDrawable(null);
        }
        k = 0;
        id = new int[9];
        pl1 = new Player(1);
        pl2 = new Player(2);
    }

    public String getArrayString(int[] arr) {
        StringBuffer str = new StringBuffer();
        for (int a : arr) {
            str = str.append(a);
        }
        return str.toString();
    }
}