package com.harsh.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    boolean gamealive = true;
    String activeplayer = "X";
    String[] gamestate = {"b","b","b","b","b","b","b","b","b"};
    int[][] winPosion = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    public void playertap(View view){
        try {
            ImageView ima = (ImageView) view;
            String tapppedImage = ima.getTag().toString();
            Log.d("MyApp", tapppedImage);
            String numberOnly = tapppedImage.replaceAll("[^0-9]", "");
            int numbersOnly = Integer.parseInt(numberOnly);
            if(!gamealive){
                resetGame(view);
            }
            if (gamestate[numbersOnly] == "b" && gamealive) {
                gamestate[numbersOnly] = activeplayer;
                ima.setTranslationY(-1000f);
                if (activeplayer == "X") {
                    ima.setImageResource(R.drawable.x);
                    activeplayer = "O";
                    TextView statusbar = findViewById(R.id.statusbar);
                    statusbar.setPaintFlags(statusbar.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
                    statusbar.setText("O's turn Tap to Play");

                } else {
                    ima.setImageResource(R.drawable.o);
                    activeplayer = "X";
                    TextView statusbar = findViewById(R.id.statusbar);
                    statusbar.setText("X's turn Tap to Play");
                }

                ima.animate().translationYBy(1000f).setDuration(300);
            }

        }
        catch (Exception e){
            Log.d("MyApp", e.toString());
        }
        for(int[] winningposion: winPosion){
            if(gamestate[winningposion[0]] == gamestate[winningposion[1]] &&
                    gamestate[winningposion[1]] == gamestate[winningposion[2]] &&
                    gamestate[winningposion[0]] != "b"){
                gamealive = false;
                String winnerStr;
                if(gamestate[winningposion [1]] == "X"){
                    winnerStr = "X Has Won";
                }
                else{
                    winnerStr = "O has Won";
                }
                TextView statusbar = findViewById(R.id.statusbar);
                statusbar.setText(winnerStr);
            }


        }
        boolean Squarenotempty = false;
        for (String squareState : gamestate) {
            if (squareState ==  "b") {
                Squarenotempty = true;
                break;
            }
        }
        if (!Squarenotempty && gamealive) {
            // Game is a draw
            gamealive = false;
            String winnerStr;
            winnerStr = "Tie Game";
            TextView status = findViewById(R.id.statusbar);
            status.setText(winnerStr);
        }

    }

    public void resetGame(View view){
        gamealive = true;
        activeplayer = "X";
        for(int i=0;i<gamestate.length;i++){
            gamestate[i] = "b";
        }
        ((ImageView)findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}