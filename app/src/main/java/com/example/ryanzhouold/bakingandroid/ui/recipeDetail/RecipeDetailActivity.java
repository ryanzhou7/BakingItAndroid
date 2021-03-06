package com.example.ryanzhouold.bakingandroid.ui.recipeDetail;

import android.content.Intent;
import android.os.Parcelable;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.view.Menu;
import android.view.MenuItem;

import com.example.ryanzhouold.bakingandroid.R;
import com.example.ryanzhouold.bakingandroid.data.dto.RecipeDto;
import com.example.ryanzhouold.bakingandroid.data.dto.StepDto;
import com.example.ryanzhouold.bakingandroid.ui.base.BaseActivity;
import com.example.ryanzhouold.bakingandroid.ui.step.StepActivity;
import com.example.ryanzhouold.bakingandroid.ui.step.StepFragment;
import com.example.ryanzhouold.bakingandroid.data.constants.Keys;

import java.util.ArrayList;
import java.util.Arrays;

public class RecipeDetailActivity extends BaseActivity
        implements RecipeFragment.OnListFragmentInteractionListener{
    private RecipeDto mRecipeDto;
    private boolean mIsTwoPane;
    private RecipeFragment mRecipeFragment;
    private StepFragment mStepFragment;
    @BindView(R.id.activity_detailed_toolbar) Toolbar mToolbar;
    private int mNUM_COL = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_recipe);
        setUnBinder(ButterKnife.bind(this));
        setSupportActionBar(mToolbar);
        Intent intent = getIntent();
        if(intent.hasExtra(Keys.RECIPE_KEY)){
            mRecipeDto = intent.getParcelableExtra(Keys.RECIPE_KEY);
        }
        mIsTwoPane = getResources().getBoolean(R.bool.isTabletOrLarger);
        mRecipeFragment = RecipeFragment.newInstance(mNUM_COL, mRecipeDto);
        if(mIsTwoPane){
            //left and right
            addFragmentTo(mRecipeFragment, R.id.left_container);
            mStepFragment = StepFragment.newInstance(mIsTwoPane, Arrays.asList(mRecipeDto.getStepDtos()));
            addFragmentTo(mStepFragment, R.id.right_container);
        }
        else{
            addFragmentTo(mRecipeFragment, R.id.detailed_main_container);
        }
    }

    private void addFragmentTo(Fragment fragment, int container){
        getSupportFragmentManager().beginTransaction()
                .add(container, fragment)
                .commit();
    }

    @Override
    public void onClick(StepDto item) {
        if(mIsTwoPane) {
            mStepFragment.showStep(item);
        }
        else{
            Intent stepIntent = new Intent(this, StepActivity.class);
            StepDto[] stepDtos = mRecipeDto.getStepDtos();
            ArrayList<Parcelable> list = new ArrayList<>();
            list.addAll(Arrays.asList(stepDtos));
            stepIntent.putParcelableArrayListExtra(Keys.STEPS_KEY, list);
            startActivity(stepIntent);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_favorite:
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.recipe_detail_activity_menu_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

}
