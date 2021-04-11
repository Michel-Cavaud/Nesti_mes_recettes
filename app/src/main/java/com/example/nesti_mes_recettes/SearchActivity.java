package com.example.nesti_mes_recettes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SearchActivity extends MenuActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        EditText editText = findViewById(R.id.search_editTxt_recipe);
        final Button btnSearch= (Button)findViewById(R.id.search_btn_ok);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editText.getText().length() > 2){
                    String term = editText.getText().toString();
                    Intent intent = new Intent(SearchActivity.this, ResultActivity.class);
                    intent.putExtra("term", term);
                    startActivity(intent);
                }else{
                    Toast t = Toast.makeText(getApplicationContext(), "Au moins 3 caractères pour la recherche", Toast.LENGTH_SHORT);
                    t.show();
                }

            }
        });
    }

}