package com.example.nesti_mes_recettes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import adapter.RecipeAdapter;
import entity.Recipe;
import model.GlutenViewModel;

public class EasyActivity extends AppCompatActivity {
    public String[] easyRecipes = {
            "Gateau au yaourt",
            "Crèpes",
            "Muffins",
            "Cookies"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy);

        final Button btnRetour = (Button)findViewById(R.id.btn_retour_easy);
        btnRetour.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                EasyActivity.this.finish();
            }
        });

        GlutenViewModel viewModel = new ViewModelProvider
                .AndroidViewModelFactory(this.getApplication())
                .create(GlutenViewModel.class);

        viewModel.getRecipes("facile").observe(this, recipes -> {
            ListView list_view = (ListView) findViewById(R.id.easy_listview);

            RecipeAdapter adapter = new RecipeAdapter(
                    this,
                    R.layout.line_recipe,
                    (ArrayList<Recipe>) recipes);
            list_view.setAdapter(adapter);
        });

    }
}