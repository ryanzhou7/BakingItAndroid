package com.example.ryanzhouold.bakingandroid.model.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.ryanzhouold.bakingandroid.model.dao.IngredientDao;
import com.example.ryanzhouold.bakingandroid.model.dao.RecipeDao;
import com.example.ryanzhouold.bakingandroid.model.dao.StepDao;
import com.example.ryanzhouold.bakingandroid.model.pojo.Ingredient;
import com.example.ryanzhouold.bakingandroid.model.pojo.Recipe;
import com.example.ryanzhouold.bakingandroid.model.pojo.Step;

@Database(entities = {Recipe.class, Step.class, Ingredient.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract RecipeDao recipeDao();
    public abstract IngredientDao ingredientDao();
    public abstract StepDao stepDao();

    private static final String DB_NAME = "Recipes.db";
    private static volatile AppDatabase INSTANCE = null;
    synchronized static AppDatabase get(Context context){
        if(INSTANCE==null){
            INSTANCE = create(context, false);
        }
        return INSTANCE;
    }

    public static AppDatabase create(Context context, boolean isInMemory) {
        RoomDatabase.Builder<AppDatabase> b;
        if(isInMemory){
            b = Room.inMemoryDatabaseBuilder(context, AppDatabase.class);
        }
        else{
            b = Room.databaseBuilder(context, AppDatabase.class, DB_NAME);
        }
        return b.build();
    }

}