package dell.scarnesdice;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private int computer_overall_score;
    private int computer_turn_score;
    private int user_overall_score;
    private int user_turn_score;
    private TextView currentScore;
    private TextView finalScore;
    private ImageView imageView;
    private Button roll;
    private Button hold;
    private Button reset;
    private Context context;
    private int score;
    private static final String text_user_overall_score = "Your Score: ";
    private static final String text_computer_overall_score = " Computer's Score: ";
    private static final String text_hold = "It's other player's turn Now";
    private static final String text_turn = "Current Turn Score : ";
    private static final String text_fail = "Sorry You rolled a 1. It's now other's turn";
    private int[] drawableResources = {R.drawable.dice1,R.drawable.dice2,R.drawable.dice3,R.drawable.dice4,R.drawable.dice5,R.drawable.dice6};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        currentScore = findViewById(R.id.textView2);
        imageView = findViewById(R.id.imageView);
        finalScore = findViewById(R.id.textView);
        roll = findViewById(R.id.button);
        hold = findViewById(R.id.button2);
        reset = findViewById(R.id.button3);
        context = this;
    }

    public void roll(View view) {
        score = (int) (Math.random()*5)+1;
        if (score == 1) {
            currentScore.setText(text_fail);
            user_turn_score=0;
            //computerTurn();
            finalScore.setText(text_user_overall_score);
            finalScore.append(String.valueOf(user_overall_score));
            finalScore.append(text_computer_overall_score);
            finalScore.append(String.valueOf(computer_overall_score));
        } else {
            currentScore.setText(text_turn);
            currentScore.append(String.valueOf(score));
            user_turn_score = score;
        }
        user_overall_score+=user_turn_score;
        Drawable drawable = ContextCompat.getDrawable(context, drawableResources[score-1]);
        imageView.setImageDrawable(drawable);
    }

    public void hold (View view) {
        currentScore.setText(text_hold);
        finalScore.setText(text_user_overall_score);
        finalScore.append(String.valueOf(user_overall_score));
        finalScore.append(text_computer_overall_score);
        finalScore.append(String.valueOf(computer_overall_score));
        Drawable drawable = ContextCompat.getDrawable(context, drawableResources[0]);
        imageView.setImageDrawable(drawable);
    }

    public void computerTurn() {
        roll.setEnabled(false);
        hold.setEnabled(false);
        reset.setEnabled(false);
    }

    public void reset(View view) {
        computer_overall_score=0;
        computer_turn_score=0;
        user_overall_score=0;
        user_turn_score=0;
        Drawable drawable = ContextCompat.getDrawable(context, drawableResources[0]);
        imageView.setImageDrawable(drawable);
        currentScore.setText(text_turn);
        finalScore.setText(text_user_overall_score);
        finalScore.append("0");
        finalScore.append(text_computer_overall_score);
        finalScore.append("0");
    }
}
