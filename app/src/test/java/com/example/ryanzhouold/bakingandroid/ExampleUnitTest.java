package com.example.ryanzhouold.bakingandroid;

import com.example.ryanzhouold.bakingandroid.data.dao.RecipeDao;
import com.example.ryanzhouold.bakingandroid.data.database.AppDatabase;
import com.example.ryanzhouold.bakingandroid.data.pojo.Recipe;

import org.junit.Before;
import org.junit.Test;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    private RecipeDao recipeDao;
    private AppDatabase appDatabase;
    @Before
    public void setUp(){

    }

    @Test
    public void basics() {
        final Recipe recipe = new Recipe(0, "cake", 1, null);
        recipeDao.insert(recipe);

    }

}