package co.martinbaciga.drawingtest.ui.activity;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Path;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import co.martinbaciga.drawingtest.R;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.LinearInterpolator;
import android.widget.EditText;

import com.google.gson.Gson;

import static android.R.attr.path;

public class Main2Activity extends AppCompatActivity {
    private Path pathObj;
    private EditText etAnswer;
    private String ANSWER;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        etAnswer = (EditText)findViewById(R.id.etAnswer);

        ANSWER = getIntent().getStringExtra("answer");

        ComplexPreferences complexPreferences = ComplexPreferences.getComplexPreferences(this, "user_pref", 0);
        pathObj = complexPreferences.getObject("current-user",Path.class);

        final AnimatedPathView view = (AnimatedPathView)findViewById(R.id.animated_path);

        view.setPath(pathObj);
        ObjectAnimator anim = ObjectAnimator.ofFloat(view, "percentage", 0.0f, 1.0f);
        anim.setDuration(2000);
        anim.setInterpolator(new LinearInterpolator());
        anim.start();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main2, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.action_share:
                String msg="";
                if(ANSWER.equalsIgnoreCase(etAnswer.getText().toString().trim())){
                    msg = "You WON this contest.";
                }else{
                    msg = "You FAIL this contest.";
                }

                new AlertDialog.Builder(this)
                        .setTitle("Information")
                        .setMessage(msg)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        })

                        .show();

                break;


        }

        return super.onOptionsItemSelected(item);
    }

}
