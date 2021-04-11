package com.example.nesti_mes_recettes;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nesti_mes_recettes.ui.main.SectionsPagerAdapter;

public class TabRecipeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_recipe);

        // Réception des données
       Bundle extras = getIntent().getExtras();
        // on extrait chaque données de extras
        int id = extras.getInt("id");
        String name = extras.getString("name");
        int nb = extras.getInt("nb");
        String temps = extras.getString("temps");

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager(), id);
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        FloatingActionButton fab = findViewById(R.id.btn_tab_recipe_caddie);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //nackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        //.setAction("Action", null).show();
                Intent intent = new Intent(TabRecipeActivity.this, listCourseActivity.class);
                startActivity(intent);
            }
        });

        TextView title = this.findViewById(R.id.abRecipe_txtViewTitle);
        title.setText(name);
        TextView nbTx = this.findViewById(R.id.frament_tx_nb);
        nbTx.setText("Recette pour : " + nb + " personnes");
        TextView tempsTx = this.findViewById(R.id.frament_tx_temps);
        tempsTx.setText("Préparation en : " + temps);
    }
}