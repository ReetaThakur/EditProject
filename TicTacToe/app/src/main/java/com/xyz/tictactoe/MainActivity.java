package com.xyz.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnOne, btnTwo, btnThree, btnFour, btnFive, btnSix, btnSeven,
            btnEight, btnNine, btnReset;

    private int turn = 1;
    private int draw = 0;
    private boolean isEnded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeListeners();
        handleClicks();
    }

    private void initializeListeners() {
        btnOne = findViewById(R.id.btnOne);
        btnTwo = findViewById(R.id.btnTwo);
        btnThree = findViewById(R.id.btnThree);
        btnFour = findViewById(R.id.btnFour);
        btnFive = findViewById(R.id.btnFive);
        btnSix = findViewById(R.id.btnSix);
        btnSeven = findViewById(R.id.btnSeven);
        btnEight = findViewById(R.id.btnEight);
        btnNine = findViewById(R.id.btnNine);
        btnReset = findViewById(R.id.btnReset);
    }

    private void handleClicks() {
        btnOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playGame(btnOne);
            }
        });

        btnTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playGame(btnTwo);
            }
        });

        btnThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playGame(btnThree);
            }

        });

        btnFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playGame(btnFour);
            }
        });

        btnFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playGame(btnFive);
            }
        });

        btnSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playGame(btnSix);
            }
        });

        btnSeven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playGame(btnSeven);
            }
        });

        btnEight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playGame(btnEight);
            }
        });

        btnNine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playGame(btnNine);
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
            }
        });
    }

    public void playGame(Button button) {
        if (button.getText().toString().equals("")) {
            if (turn == 1) {
                button.setText("X");
                turn = 2;
            } else if (turn == 2) {
                button.setText("O");
                turn = 1;
            }
            draw = draw + 1;

            endGame();
        }
    }

    public void endGame() {
        String box1, box2, box3, box4, box5, box6, box7, box8, box9;
        box1 = btnOne.getText().toString();
        box2 = btnTwo.getText().toString();
        box3 = btnThree.getText().toString();
        box4 = btnFour.getText().toString();
        box5 = btnFive.getText().toString();
        box6 = btnSix.getText().toString();
        box7 = btnSeven.getText().toString();
        box8 = btnEight.getText().toString();
        box9 = btnNine.getText().toString();


        if (box1.equals("X") && box2.equals("X") && box3.equals("X")) {
            Toast.makeText(this, "Player X won", Toast.LENGTH_SHORT).show();
            isEnded = true;
        }

        if (box4.equals("X") && box5.equals("X") && box6.equals("X")) {
            Toast.makeText(this, "Player X won", Toast.LENGTH_SHORT).show();
            isEnded = true;

        }
        if (box7.equals("X") && box8.equals("X") && box9.equals("X")) {
            Toast.makeText(this, "Player X won", Toast.LENGTH_SHORT).show();
            isEnded = true;
        }

        if(box1.equals("X") && box4.equals("X") && box7.equals("X")){
            Toast.makeText(this, "Player X won", Toast.LENGTH_SHORT).show();
            isEnded = true;
        }

        if(box2.equals("X") && box5.equals("X") && box8.equals("X")){
            Toast.makeText(this, "Player X won", Toast.LENGTH_SHORT).show();
            isEnded = true;
        }
        if(box3.equals("X") && box6.equals("X") && box9.equals("X")){
            Toast.makeText(this, "Player X won", Toast.LENGTH_SHORT).show();
            isEnded = true;
        }

        if(box1.equals("X") && box5.equals("X") && box9.equals("X")){
            Toast.makeText(this, "Player X won", Toast.LENGTH_SHORT).show();
            isEnded = true;
        }

        if(box3.equals("X") && box5.equals("X") && box7.equals("X")){
            Toast.makeText(this, "Player X won", Toast.LENGTH_SHORT).show();
            isEnded = true;
        }


        if (box1.equals("O") && box2.equals("O") && box3.equals("O")) {
            Toast.makeText(this, "Player O won", Toast.LENGTH_SHORT).show();
            isEnded = true;
        }

        if (box4.equals("O") && box5.equals("O") && box6.equals("O")) {
            Toast.makeText(this, "Player O won", Toast.LENGTH_SHORT).show();
            isEnded = true;

        }
        if (box7.equals("O") && box8.equals("O") && box9.equals("O")) {
            Toast.makeText(this, "Player O won", Toast.LENGTH_SHORT).show();
            isEnded = true;
        }

        if(box1.equals("O") && box4.equals("O") && box7.equals("O")){
            Toast.makeText(this, "Player O won", Toast.LENGTH_SHORT).show();
            isEnded = true;
        }

        if(box2.equals("O") && box5.equals("O") && box8.equals("O")){
            Toast.makeText(this, "Player O won", Toast.LENGTH_SHORT).show();
            isEnded = true;
        }
        if(box3.equals("O") && box6.equals("O") && box9.equals("O")){
            Toast.makeText(this, "Player O won", Toast.LENGTH_SHORT).show();
            isEnded = true;
        }

        if(box1.equals("O") && box5.equals("O") && box9.equals("O")){
            Toast.makeText(this, "Player O won", Toast.LENGTH_SHORT).show();
            isEnded = true;
        }

        if(box3.equals("O") && box5.equals("O") && box7.equals("O")){
            Toast.makeText(this, "Player O won", Toast.LENGTH_SHORT).show();
            isEnded = true;
        }

        if (isEnded) {
            btnOne.setEnabled(false);
            btnTwo.setEnabled(false);
            btnThree.setEnabled(false);
            btnFour.setEnabled(false);
            btnFive.setEnabled(false);
            btnSix.setEnabled(false);
            btnSeven.setEnabled(false);
            btnEight.setEnabled(false);
            btnNine.setEnabled(false);
            btnReset.setText("Play Again");
        }

        if(draw == 9 && !isEnded){
            Toast.makeText(this,"Drawed", Toast.LENGTH_SHORT).show();
            btnReset.setText("Play Again");
        }
    }

    private void resetGame() {
        recreate();

    }

}