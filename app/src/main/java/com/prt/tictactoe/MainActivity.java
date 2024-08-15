package com.prt.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import android.app.Dialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    MaterialCardView btn_x, btn_o;
    boolean firstTurn_x = true;
    int count = 0;
    int flag = 0;
    TextView text1,text2, text3, text4, text5, text6, text7, text8,text9;
    ImageView image1, image2, image3, image4, image5, image6, image7, image8, image9;
    MaterialCardView restart;
    View winningLine1,winningLine2,winningLine3,winningLine4,winningLine5,winningLine6,winningLine7,winningLine8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //findViewById
        btn_x = findViewById(R.id.btn_x);
        btn_o = findViewById(R.id.btn_o);
        image1 = findViewById(R.id.image1);
        image2 = findViewById(R.id.image2);
        image3 = findViewById(R.id.image3);
        image4 = findViewById(R.id.image4);
        image5 = findViewById(R.id.image5);
        image6 = findViewById(R.id.image6);
        image7 = findViewById(R.id.image7);
        image8 = findViewById(R.id.image8);
        image9 = findViewById(R.id.image9);
        restart = findViewById(R.id.restart);
        winningLine1 = findViewById(R.id.winning_line);
        winningLine2 = findViewById(R.id.winning_line_2);
        winningLine3 = findViewById(R.id.winning_line_3);
        winningLine4 = findViewById(R.id.winning_line_4);
        winningLine5 = findViewById(R.id.winning_line_5);
        winningLine6 = findViewById(R.id.winning_line_6);
        winningLine7 = findViewById(R.id.winning_line_7);
        winningLine8 = findViewById(R.id.winning_line_8);
        text1 = findViewById(R.id.text1);
        text2 = findViewById(R.id.text2);
        text3 = findViewById(R.id.text3);
        text4 = findViewById(R.id.text4);
        text5 = findViewById(R.id.text5);
        text6 = findViewById(R.id.text6);
        text7 = findViewById(R.id.text7);
        text8 = findViewById(R.id.text8);
        text9 = findViewById(R.id.text9);

        //FirstTurn onClick listener
        btn_x.setOnClickListener(v -> {
            firstTurn_x = true;
            btn_x.setStrokeColor(ContextCompat.getColor(this,R.color.dark_blue));
            btn_o.setStrokeColor(ContextCompat.getColor(this,R.color.light_cyan));
            Toast.makeText(this,"Player X has the First turn !!",Toast.LENGTH_LONG).show();
            btn_x.setEnabled(false);
            btn_o.setEnabled(false);
        });
        btn_o.setOnClickListener(v -> {
            firstTurn_x = false;
            btn_o.setStrokeColor(ContextCompat.getColor(this,R.color.dark_blue));
            btn_x.setStrokeColor(ContextCompat.getColor(this,R.color.light_cyan));
            Toast.makeText(this,"Player O has the First turn !!",Toast.LENGTH_LONG).show();
            btn_x.setEnabled(false);
            btn_o.setEnabled(false);
        });

        //gridlayout onClick listener
        image1.setOnClickListener(v -> {
            onClick(image1,text1);
        });
        image2.setOnClickListener(v -> {
            onClick(image2,text2);
        });
        image3.setOnClickListener(v -> {
            onClick(image3,text3);
        });
        image4.setOnClickListener(v -> {
            onClick(image4,text4);
        });
        image5.setOnClickListener(v -> {
            onClick(image5,text5);
        });
        image6.setOnClickListener(v -> {
            onClick(image6,text6);
        });
        image7.setOnClickListener(v -> {
            onClick(image7,text7);
        });
        image8.setOnClickListener(v -> {
            onClick(image8,text8);
        });
        image9.setOnClickListener(v -> {
            onClick(image9,text9);
        });
        restart.setOnClickListener(v -> {Restart();});

    }

    private void Restart(){
        flag = 0;
        count = 0;
        btn_o.setEnabled(true);
        btn_x.setEnabled(true);
        image1.setImageResource(0);
        image2.setImageResource(0);
        image3.setImageResource(0);
        image4.setImageResource(0);
        image5.setImageResource(0);
        image6.setImageResource(0);
        image7.setImageResource(0);
        image8.setImageResource(0);
        image9.setImageResource(0);
        text1.setText("");
        text2.setText("");
        text3.setText("");
        text4.setText("");
        text5.setText("");
        text6.setText("");
        text7.setText("");
        text8.setText("");
        text9.setText("");
        winningLine1.setVisibility(View.INVISIBLE);
        winningLine2.setVisibility(View.INVISIBLE);
        winningLine3.setVisibility(View.INVISIBLE);
        winningLine4.setVisibility(View.INVISIBLE);
        winningLine5.setVisibility(View.INVISIBLE);
        winningLine6.setVisibility(View.INVISIBLE);
        winningLine7.setVisibility(View.INVISIBLE);
        winningLine8.setVisibility(View.INVISIBLE);
    }
    private void onClick(View view, View text) {
        ImageView imagecur = (ImageView) view;
        TextView textcur = (TextView) text;
        if (imagecur.getDrawable() == null) {
            count++;
            if (firstTurn_x) {
                if (flag == 0) {
                    textcur.setText("x");
                    imagecur.setImageResource(R.drawable.cross);
                    flag = 1;
                } else {
                    textcur.setText("o");
                    imagecur.setImageResource(R.drawable.o);
                    flag = 0;
                }
            } else {
                if (flag == 0) {
                    textcur.setText("o");
                    imagecur.setImageResource(R.drawable.o);
                    flag = 1;
                } else {
                    textcur.setText("x");
                    imagecur.setImageResource(R.drawable.cross);
                    flag = 0;
                }
            }
        }
        if (count > 4){
            checkWinner();
        }
    }

    private void checkWinner() {
        if (checkLine(text1,text2,text3) ) {

            showDialog(text1.getText().toString());
            winningLine1.setVisibility(View.VISIBLE);

        } else if (checkLine(text4,text5,text6)) {

            showDialog(text4.getText().toString());
            winningLine2.setVisibility(View.VISIBLE);

        } else if (checkLine(text7,text8,text9)) {

            showDialog(text7.getText().toString());
            winningLine3.setVisibility(View.VISIBLE);

        } else if (checkLine(text1,text4,text7)) {

            showDialog(text1.getText().toString());
            winningLine4.setVisibility(View.VISIBLE);

        } else if (checkLine(text2,text5,text8)) {

            showDialog(text2.getText().toString());
            winningLine5.setVisibility(View.VISIBLE);

        } else if (checkLine(text3,text6,text9)) {

            showDialog(text3.getText().toString());
            winningLine6.setVisibility(View.VISIBLE);

        } else if (checkLine(text1,text5,text9)) {

            showDialog(text1.getText().toString());
            winningLine7.setVisibility(View.VISIBLE);

        } else if (checkLine(text3,text5,text7)) {

            showDialog(text3.getText().toString());
            winningLine8.setVisibility(View.VISIBLE);

        } else if (count == 9) {
            showDialog("draw");
        }
    }

    private boolean checkLine(TextView text1, TextView text2, TextView text3) {
        String st1 = text1.getText().toString();
        String st2 = text2.getText().toString();
        String st3 = text3.getText().toString();
        if (st1.isEmpty() || st2.isEmpty() || st3.isEmpty()) {
            return false;
        }
        return ((st1.equals(st2)) && (st2.equals(st3)));
    }

    private void showDialog(String winner)   {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialogbox);
        TextView Winner = dialog.findViewById(R.id.winner_text);
        if(winner.equals("draw")){
            Winner.setText("DRAW !!");
        }else{
            Winner.setText("Player " + winner + " has Won !!");
        }
        MaterialButton restart2 = dialog.findViewById(R.id.restart2);
        restart2.setOnClickListener(v -> {
            Restart();
            dialog.dismiss();
        });
        dialog.show();
    }

}

