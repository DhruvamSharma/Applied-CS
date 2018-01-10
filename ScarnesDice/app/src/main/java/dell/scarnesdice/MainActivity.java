package dell.scarnesdice;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private int computer_overall_score;
    private int computer_turn_score;
    private int user_overall_score;
    private int user_turn_score;
    private TextView currentScore;
    private TextView finalScore;
    private ImageView imageView;
    private ImageView imageView2;
    private Button roll;
    private Button hold;
    private Button reset;
    private Context context;
    private int score1;
    private int score2;
    private int difficulty=20;
    private int MAXIMUM_POINTS=100;
    private Handler timeHandler;
    private static final String text_user_overall_score = "Your Score: ";
    private static final String text_computer_overall_score = " Computer's Score: ";
    private static final String text_hold = "It's other player's turn Now";
    private static final String text_turn = "Current Turn Score : ";
    private static final String text_fail = "Sorry You rolled a 1. It's now other's turn";
    private static int flag=0;
    private int[] checked;
    private int[] drawableResources = {R.drawable.dice1,R.drawable.dice2,R.drawable.dice3,R.drawable.dice4,R.drawable.dice5,R.drawable.dice6};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        currentScore = findViewById(R.id.textView2);
        imageView = findViewById(R.id.imageView);
        imageView2= findViewById(R.id.imageView2);
        finalScore = findViewById(R.id.textView);
        roll = findViewById(R.id.button);
        hold = findViewById(R.id.button2);
        reset = findViewById(R.id.button3);
        context = this;
        timeHandler = new Handler();
        FloatingActionButton fab =  findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Click action
                final String[] multiChoiceItems = {"DIfficult Mode","Increase Max Winning Score", "Default Level"};
                checked = new int[3];
                final boolean[] checkedItems = {false, false, false, false};
                new AlertDialog.Builder(context)
                        .setTitle("Select your preference")
                        .setMultiChoiceItems(multiChoiceItems, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int index, boolean isChecked) {

                                Log.d("MainActivity", "clicked item index is " + index);
                                if (!isChecked) {
                                    checked[index] = index;
                                }else {

                                    checked[index] = -1;
                                }
                            }
                        })
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Log.d("MainActivity", "clicked item index is " + checked[0] + "  "+ checked[1]);
                                setPreferences(checked);
                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .show();
            }
        });
    }

    public void roll(View view) {
        score1 = (int) (Math.random()*5)+1;
        score2 = (int) (Math.random()*5)+1;
        if(flag == 0)
        Log.v("user score1",String.valueOf(score1)+"  " + String.valueOf(score2));
        else {
            Log.v("computer score1",String.valueOf(score1)+"  " + String.valueOf(score2));
        }

         if(score1 == 1 && score2 == 1) {
            if (flag == 1) {
                computer_turn_score = 0;
                computer_overall_score = 0;
                finalStatements();
                Toast.makeText(this,"Sorry, Computer's score has ben reduced to 0",Toast.LENGTH_SHORT).show();
            } else {
                user_turn_score = 0;
                user_overall_score = 0;
                finalStatements();
                Toast.makeText(this,"Sorry, your score has ben reduced to 0",Toast.LENGTH_SHORT).show();
                computerTurn();
                finalStatements();
            }

        }
        else if(score1 == 1 || score2 == 1){
            computer_turn_score=0;
            user_turn_score=0;
            currentScore.setText(text_turn);
            currentScore.append(String.valueOf(user_turn_score));

            if (flag == 0) {

                computerTurn();
                //finalStatements();
            }
        }
        else {
            if (flag == 1){
                computer_turn_score += score1 + score2;
            }
            else {
                user_turn_score += score1 + score2;
            }
        }
        if (score1 ==0 || score2 == 0) {
            score1 = 1;
            score2 = 1;
        }

        Drawable drawable = ContextCompat.getDrawable(context, drawableResources[score1-1]);
        imageView.setImageDrawable(drawable);
        Drawable drawable2 = ContextCompat.getDrawable(context,drawableResources[score2-1]);
        imageView2.setImageDrawable(drawable2);
        currentScore.setText(text_turn);
        currentScore.append(String.valueOf(user_turn_score));

        if(user_overall_score>=MAXIMUM_POINTS) {
            Toast.makeText(this,"You Won",Toast.LENGTH_SHORT).show();
            reset(reset);
        }
        if(computer_overall_score>=MAXIMUM_POINTS) {
            Toast.makeText(this,"You Lost",Toast.LENGTH_SHORT).show();
            reset(reset);
        }


    }

    public void hold (View view) {

            if (flag == 0)
                user_overall_score += user_turn_score;
            else {
                computer_overall_score += computer_turn_score;
                Toast.makeText(this,"Computer Decided to hold to these precious scores",Toast.LENGTH_SHORT).show();
                Log.v("computer_overall_score", String.valueOf(computer_overall_score));
            }
            user_turn_score = 0;
            computer_turn_score = 0;
            currentScore.setText(text_hold);
            finalScore.setText(text_user_overall_score);
            finalScore.append(String.valueOf(user_overall_score));
            finalScore.append(text_computer_overall_score);
            finalScore.append(String.valueOf(computer_overall_score));

            Drawable drawable = ContextCompat.getDrawable(context, drawableResources[0]);
            imageView.setImageDrawable(drawable);

            imageView2.setImageDrawable(drawable);
            if (flag == 0)
                computerTurn();


    }

    private void finalStatements() {
        if (score1 ==0 || score2 == 0) {
            score1 = 1;
            score2 = 1;
        }
        finalScore.setText(text_user_overall_score);
        finalScore.append(String.valueOf(user_overall_score));
        finalScore.append(text_computer_overall_score);
        finalScore.append(String.valueOf(computer_overall_score));
        Drawable drawable = ContextCompat.getDrawable(context, drawableResources[score1-1]);
        imageView.setImageDrawable(drawable);
        Drawable drawable2 = ContextCompat.getDrawable(context,drawableResources[score2-1]);
        imageView2.setImageDrawable(drawable2);
        currentScore.setText(text_turn);
        currentScore.append(String.valueOf(user_turn_score));
    }


    public void reset(View view) {
        computer_overall_score=0;
        computer_turn_score=0;
        user_overall_score=0;
        user_turn_score=0;
        flag=0;
        Drawable drawable = ContextCompat.getDrawable(context, drawableResources[0]);
        imageView.setImageDrawable(drawable);
        imageView2.setImageDrawable(drawable);
        currentScore.setText(text_turn);
        finalScore.setText(text_user_overall_score);
        finalScore.append("0");
        finalScore.append(text_computer_overall_score);
        finalScore.append("0");
        roll.setEnabled(true);
        hold.setEnabled(true);
        roll.setBackgroundColor(Color.parseColor("#b76a1c"));
        hold.setBackgroundColor(Color.parseColor("#b76a1c"));
    }

    public void computerTurn() {

        roll.setEnabled(false);
        hold.setEnabled(false);
        roll.setBackgroundColor(Color.parseColor("#d3d3d3"));
        hold.setBackgroundColor(Color.parseColor("#d3d3d3"));
        flag = 1;
        score1 = 0;
        score2 = 0;
        //random = (int) (Math.random()*difficulty);
        roll(roll);

        if (score1 != 1 && score2 != 1) {
            timeHandler.postDelayed(runnable , 1000);
        }else {
            setEnabled();
        }



    }

    private void setEnabled() {
        flag=0;
        roll.setEnabled(true);
        hold.setEnabled(true);
        roll.setBackgroundColor(Color.parseColor("#b76a1c"));
        hold.setBackgroundColor(Color.parseColor("#b76a1c"));
        Log.v("tag..","computer_turn_over");
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            int random = (int) (Math.random()*difficulty+1);
            if (random >= score1 + score2 ) {
                if (score1 != 1 && score2 != 1) {
                    roll(roll);
                    timeHandler.postDelayed(this, 1000);
                }
                else {
                    setEnabled();
                    timeHandler.removeCallbacks(this);
                }
            } else {
                hold(hold);
                setEnabled();
                timeHandler.removeCallbacks(this);
            }
        }
    };


    public void setPreferences(int[] checked) {
        if(checked[0] == -1) {
            difficulty = 15;
            MAXIMUM_POINTS = 50;
            Toast.makeText(this,"Difficulty Increased",Toast.LENGTH_SHORT).show();
        }

        if(checked[1] == -1) {
            MAXIMUM_POINTS = 150;
            Toast.makeText(this, "MAXIMUMPOINTS INCREASED TO 150",Toast.LENGTH_SHORT).show();
        }

        if (checked[2] == -1) {
            difficulty = 20;
            MAXIMUM_POINTS = 100;
        }
    }


}
