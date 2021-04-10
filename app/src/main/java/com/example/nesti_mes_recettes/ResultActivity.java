package com.example.nesti_mes_recettes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import adapter.RecipeAdapter;
import adapter.SearchAdapter;
import entity.Recipe;
import model.GlutenViewModel;
import model.SearchViewModel;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        String term = this.getIntent().getStringExtra("term");

        TextView textView = findViewById(R.id.result_txtView_term);
        textView.setText(term);

        SearchViewModel viewModel = new ViewModelProvider
                .AndroidViewModelFactory(this.getApplication())
                .create(SearchViewModel.class);

        viewModel.getRecipes(textView.getText().toString()).observe(this, recipes -> {
            ListView list_view = (ListView) findViewById(R.id.result_listView);

            SearchAdapter adapter = new SearchAdapter(
                    this,
                    R.layout.line_search,
                    (ArrayList<Recipe>) recipes);
            list_view.setAdapter(adapter);
        });
    }
}