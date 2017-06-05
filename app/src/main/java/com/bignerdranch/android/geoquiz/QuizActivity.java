package com.bignerdranch.android.geoquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class QuizActivity extends AppCompatActivity {

    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private TextView mQuestionTextView;
    private Question mCurrentQuestion;
    private Question[] mQuestions;
    private int mIndexQuestion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        //create array of questions. TODO(check correctness)
        mQuestions = new Question[]{
                new Question(R.string.question_africa, false),
                new Question(R.string.question_americas, true),
                new Question(R.string.question_asia, true),
                new Question(R.string.question_australia, true),
                new Question(R.string.question_mideast, false),
                new Question(R.string.question_oceans, true)
        };

        initializeQuestion();

        mTrueButton = (Button) findViewById(R.id.true_button);
        // This says, set a listener on mTrueButton that executes onClick when button is clicked
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QuizActivity.this.makeToast(true);
            }
        });
        mFalseButton = (Button) findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeToast(false);
            }
        });
        mNextButton = (Button) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateQuestion();
            }
        });
    }
    private void makeToast(boolean true_clicked) {
        int messageResId = 0;
        if (!(true_clicked^mCurrentQuestion.isAnswerTrue())) {
            messageResId = R.string.correct_toast;
        } else {
            messageResId = R.string.incorrect_toast;
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
    }
    private void updateQuestion() {
        mIndexQuestion += 1;
        mIndexQuestion %= 6;
        mCurrentQuestion = mQuestions[mIndexQuestion];
        mQuestionTextView.setText(mCurrentQuestion.getTextResId());

    }
    private void initializeQuestion() {
        mIndexQuestion = 0;
        mCurrentQuestion = mQuestions[0];
        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
        mQuestionTextView.setText(mCurrentQuestion.getTextResId());

    }



}
