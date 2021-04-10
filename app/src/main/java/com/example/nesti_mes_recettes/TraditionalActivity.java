package com.example.nesti_mes_recettes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;

import adapter.RecipeAdapter;
import entity.Recipe;
import model.GlutenViewModel;

public class TraditionalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_traditional);

        final Button btnRetour = (Button)findViewById(R.id.btn_retour_traditional);
        btnRetour.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                TraditionalActivity.this.finish();
            }
        });

        GlutenViewModel viewModel = new ViewModelProvider
                .AndroidViewModelFactory(this.getApplication())
                .create(GlutenViewModel.class);

        viewModel.getRecipes("traditionnelles").observe(this, recipes -> {
            ListView list_view = (ListView) findViewById(R.id.tradition_listView);

            RecipeAdapter adapter = new RecipeAdapter(
                    this,
                    R.layout.line_recipe,
                    (ArrayList<Recipe>) recipes);
            list_view.setAdapter(adapter);
        });

    }


}