package com.example.ryanzhouold.bakingandroid.Step;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.ryanzhouold.bakingandroid.R;
import com.example.ryanzhouold.bakingandroid.constants.Keys;
import com.example.ryanzhouold.bakingandroid.modelLayer.pojo.Step;

import java.util.Arrays;
import java.util.List;

public class StepActivity extends AppCompatActivity{

    StepFragment mStepFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step);
        findViewById(R.id.step_container);
        Intent intent = getIntent();
        if(intent.hasExtra(Keys.STEPS_KEY)){
            List<Step> list = intent.getParcelableArrayListExtra(Keys.STEPS_KEY);
            //Step[] steps = (Step[]) intent.getParcelableArrayExtra(Keys.STEPS_KEY);
            //Step[] steps = (Step[]) intent.getParcelableArrayExtra(Keys.STEPS_KEY);
            mStepFragment = StepFragment.newInstance(false, list);
            getSupportFragmentManager().beginTransaction().add(R.id.step_container, mStepFragment)
                    .commit();
        }
    }
}
