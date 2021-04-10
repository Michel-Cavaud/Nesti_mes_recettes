package com.example.nesti_mes_recettes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import adapter.RecipeAdapter;
import entity.Recipe;
import model.GlutenViewModel;

public class SaesonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saeson);

        final Button btnRetour = (Button)findViewById(R.id.btn_retour_season);
        btnRetour.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                SaesonActivity.this.finish();
            }
        });

        GlutenViewModel viewModel = new ViewModelProvider
                .AndroidViewModelFactory(this.getApplication())
                .create(GlutenViewModel.class);

        viewModel.getRecipes("saison").observe(this, recipes -> {
            ListView list_view = (ListView) findViewById(R.id.seazon_listView);

            RecipeAdapter adapter = new RecipeAdapter(
                    this,
                    R.layout.line_recipe,
                    (ArrayList<Recipe>) recipes);
            list_view.setAdapter(adapter);
        });
    }




}