package android.obee.com.geroquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.obee.com.geroquiz.R;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends Activity {

    public static final String EXTRA_ANSWER_IS_TRUE = "android.obee.com.geroquiz.answer_is_true";
    public static final String EXTRA_ANSWER_SHOWN = "android.obee.com.geroquiz.answer_shown";
    private static final String KEY_CHEATER = "cheater";

    private boolean mAnswerIsTrue;
    private TextView mAnswerTextView;
    private Button mShowAnswer;
    private boolean mIsCheater = false;

    public void setAnswerShownResult(boolean isAnswerShown) {
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown);
        setResult(RESULT_OK, data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        if(savedInstanceState != null) {
            mIsCheater = savedInstanceState.getBoolean(KEY_CHEATER, false);
            setAnswerShownResult(mIsCheater);
        } else {
            mIsCheater = false;
            setAnswerShownResult(false);
        }


        mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);
        mAnswerTextView = (TextView)findViewById(R.id.answerTextView);
        mShowAnswer = (Button)findViewById(R.id.showAnswerButton);

        if(mIsCheater) {
            mAnswerTextView.setText(mAnswerIsTrue ? R.string.true_button : R.string.false_button);
        }

        mShowAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mIsCheater = true;
                if (mAnswerIsTrue) {
                    mAnswerTextView.setText(R.string.true_button);
                } else {
                    mAnswerTextView.setText(R.string.false_button);
                }
                setAnswerShownResult(true);

            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putBoolean(KEY_CHEATER, mIsCheater);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.cheat, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
