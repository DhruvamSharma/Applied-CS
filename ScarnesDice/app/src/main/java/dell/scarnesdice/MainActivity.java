package dell.scarnesdice;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
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
    private int score;
    private int score2;
    private int difficulty=5;
    private int MAXIMUM_POINTS=50;
    private static final String text_user_overall_score = "Your Score: ";
    private static final String text_computer_overall_score = " Computer's Score: ";
    private static final String text_hold = "It's other player's turn Now";
    private static final String text_turn = "Current Turn Score : ";
    private static final String text_fail = "Sorry You rolled a 1. It's now other's turn";
    private static int flag=0;
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

    }

    public void roll(View view) {
        score = (int) (Math.random()*5)+1;
        score2 = (int) (Math.random()*5)+1;
        if(flag==1){
            Log.v("computer_turn_score",String.valueOf(score));
        }

        if (score == 1 || score2 == 1) {
            currentScore.setText(text_fail);
            user_turn_score=0;
            computer_turn_score=0;
            if(flag==0) {
                user_overall_score += user_turn_score;
                Log.v("user", "user scored 1");
            }
            else{
                computer_overall_score+=computer_turn_score;
                Log.v("computer", "computer scored 1");
            }

            finalScore.setText(text_user_overall_score);
            finalScore.append(String.valueOf(user_overall_score));
            finalScore.append(text_computer_overall_score);
            finalScore.append(String.valueOf(computer_overall_score));

            if(flag==0)
            computerTurn();

        } else {
            currentScore.setText(text_turn);
            if(flag==0) {
                user_turn_score += score+score2;
                currentScore.append(String.valueOf(user_turn_score));
                Log.v("usesr_turn_score",String.valueOf(score+score2));
            }
            else {
                computer_turn_score += score+score2;
                currentScore.append(String.valueOf(computer_turn_score));
                //computer_overall_score+=computer_turn_score;
            }

        }

        if(score==0||score2==0) {
            score=1;
            score2=1;
        }
        Drawable drawable = ContextCompat.getDrawable(context, drawableResources[score-1]);
        imageView.setImageDrawable(drawable);
        Drawable drawable2 = ContextCompat.getDrawable(context,drawableResources[score2-1]);
        imageView2.setImageDrawable(drawable2);
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
        int random;
        roll.setEnabled(false);
        hold.setEnabled(false);
        roll.setBackgroundColor(Color.parseColor("#ff00ff"));
        hold.setBackgroundColor(Color.parseColor("#ff00ff"));
        flag = 1;
        score=0;
        while(score != 1||score2 !=1) {
            random = (int) (Math.random() * difficulty + 1);
            if (computer_turn_score < random) {
                    roll(roll);
            } else {
                hold(hold);
                Log.v("computer_action", "hold!!");

            }
        }


        flag=0;
        roll.setEnabled(true);
        hold.setEnabled(true);
        roll.setBackgroundColor(Color.parseColor("#b76a1c"));
        hold.setBackgroundColor(Color.parseColor("#b76a1c"));
        Log.v("tag..","computer_turn_over");
    }





}
