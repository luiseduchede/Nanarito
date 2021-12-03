package com.example.nanarito;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private boolean nanarito = false;
    private int[] gamestate = {2, 2, 2 , 2, 2, 2, 2, 2, 2};
    private int[][] winningpos = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
    private boolean gameactive = true;

    public void dropIn(View view) {
        ImageView counter = (ImageView) view;
        int pos;

        if (gamestate[Integer.parseInt(view.getTag().toString())] == 2 && gameactive) {
            pos = Integer.parseInt(view.getTag().toString());
            counter.setTranslationY(-1500);
            if (nanarito) {
                counter.setImageResource(R.drawable.nanamo);
                gamestate[pos] = 0;
            } else {
                counter.setImageResource(R.drawable.lolorito);
                gamestate[pos] = 1;
            }
            checkWinning();
            nanarito = !nanarito;
            counter.animate().translationYBy(1500).rotation(3600).setDuration(500);
        }

    }

    public void playAgain(View view) {
        Log.i("Debugging", "Debugging");
        Button playagain = findViewById(R.id.playAgainButton);
        TextView winnertext = findViewById(R.id.winnerTextView);
        androidx.gridlayout.widget.GridLayout gridLayout = (androidx.gridlayout.widget.GridLayout) findViewById(R.id.gridLayout);

        winnertext.setVisibility(View.INVISIBLE);
        playagain.setVisibility(View.INVISIBLE);

        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            ImageView counter = (ImageView) gridLayout.getChildAt(i);
            counter.setImageDrawable(null);
        }

        for (int i = 0; i < gamestate.length; i++) {
            gamestate[i] = 2;
        }

        gameactive = true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void checkWinning(){
        Button playagain = findViewById(R.id.playAgainButton);
        TextView winnertext = findViewById(R.id.winnerTextView);

        for (int[] position : winningpos){
            if(gamestate[position[0]] == gamestate[position[1]] && gamestate[position[1]] == gamestate[position[2]] && gamestate[position[0]] != 2){
                String winner;

                if (nanarito)
                    winner = "Nanamo";
                else winner = "Lolorito";

                Toast.makeText(this, winner + " has won!", Toast.LENGTH_SHORT).show();
                winnertext.setText(winner + " has won!");
                gameactive = false;

                winnertext.setVisibility(View.VISIBLE);
                playagain.setVisibility(View.VISIBLE);
            }
        }
    }
}