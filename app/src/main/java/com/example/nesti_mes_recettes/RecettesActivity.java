package com.example.nesti_mes_recettes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import adapter.RecipeAdapter;
import entity.Recipe;
import model.GlutenViewModel;

public class RecettesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recettes);
        Bundle extras = getIntent().getExtras();
        String cat = extras.getString("cat");
        String titre = extras.getString("titre");


        TextView textView = this.findViewById(R.id.rectteActivity_Title);
        textView.setText(titre);

        GlutenViewModel viewModel = new ViewModelProvider
                .AndroidViewModelFactory(this.getApplication())
                .create(GlutenViewModel.class);

        viewModel.getRecipes(cat).observe(this, recipes -> {
            ListView list_view = (ListView) findViewById(R.id.recettesActivity_listView);

            RecipeAdapter adapter = new RecipeAdapter(
                    this,
                    R.layout.line_recipe,
                    (ArrayList<Recipe>) recipes);
            list_view.setAdapter(adapter);
        });


    }
}