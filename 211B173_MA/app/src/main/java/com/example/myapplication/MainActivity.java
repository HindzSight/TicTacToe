package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    boolean isWinner = false;
    int imageClicked = -1;
    int player=1; // P1 is cross
    int [][]winningStates = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    int []gameState = {-1,-1,-1,-1,-1,-1,-1,-1,-1};
    boolean kuchbhi = false;
//    int line = -1;

    public void load(View view)
    {

            ImageView v = (ImageView) view;
            int tag = Integer.parseInt(v.getTag().toString());
            imageClicked = gameState[tag];
        if(!isWinner && imageClicked == -1) {
            if (player == 1) {
                v.setImageResource(R.drawable.cross);
                gameState[tag] = player;
                //Toast.makeText(this, tag + " : " + "X", Toast.LENGTH_SHORT).show();
                player = 0;
            } else {
                v.setImageResource(R.drawable.circle);
                gameState[tag] = player;
                //Toast.makeText(this, tag + " : " + "O", Toast.LENGTH_SHORT).show();
                player = 1;
            }
        }
            for (int i = 0; i < winningStates.length; i++) {
                if (gameState[winningStates[i][0]] == gameState[winningStates[i][1]] && gameState[winningStates[i][1]] == gameState[winningStates[i][2]] && gameState[winningStates[i][0]] > -1) {
                    Toast.makeText(this, "Winner is " + (player == 0 ? "X" : "O"), Toast.LENGTH_SHORT).show();
//                    line = i;
                    isWinner = true;
                }
            }

    }
    public void reset(View view)
    {
        GridLayout gridLayout=findViewById(R.id.background);
        int total_images=gridLayout.getChildCount();
        for(int i=0;i<total_images;i++)
        {
            ImageView v = (ImageView) gridLayout.getChildAt(i);
            v.setImageDrawable(null);
        }
        isWinner=false;
        imageClicked=-1;
        player=1;
        Arrays.fill(gameState, -1);
    }

    public void doNothing(View view)
    {
        if (!kuchbhi) kuchbhi = true;
        else kuchbhi = false;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Welcome!")
                .setMessage("Tic Tac Toe")
                .setNeutralButton("OK", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog,int which) {
                        Toast.makeText(getApplicationContext(), "PLAY!", Toast.LENGTH_SHORT).show();
                    }
        }).show();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}