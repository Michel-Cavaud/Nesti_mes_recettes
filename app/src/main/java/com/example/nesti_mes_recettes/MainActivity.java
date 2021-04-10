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
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button btnEasy = (Button)findViewById(R.id.main_btn_easy);
        btnEasy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                alertDialog.setTitle("Facile à faire");
                alertDialog.setMessage("Voulez-vous choisir une recette facile à faire ?");
                alertDialog.setPositiveButton("oui", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(MainActivity.this, EasyActivity.class);
                        startActivity(intent);
                       Toast.makeText(getApplicationContext(), btnEasy.getText(), Toast.LENGTH_SHORT).show();
                    }
                });
                alertDialog.setNegativeButton("non", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                alertDialog.show();
            }
        });

        final Button btnTraditional= (Button)findViewById(R.id.main_btn_traditional);
        btnTraditional.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                alertDialog.setTitle("Traditionnalles");
                alertDialog.setMessage("Voulez-vous choisir une recette traditionnelle ?");
                alertDialog.setPositiveButton("oui", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(MainActivity.this, TraditionalActivity.class);
                        startActivity(intent);
                        Toast.makeText(getApplicationContext(), btnTraditional.getText(), Toast.LENGTH_SHORT).show();
                    }
                });
                alertDialog.setNegativeButton("non", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                alertDialog.show();
            }
        });
        final Button btnSeason= (Button)findViewById(R.id.main_btn_season);
        btnSeason.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                alertDialog.setTitle("C'est la saison");
                alertDialog.setMessage("Voulez-vous choisir une recette de saison ?");
                alertDialog.setPositiveButton("oui", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(MainActivity.this, SaesonActivity.class);
                        startActivity(intent);
                        Toast.makeText(getApplicationContext(), btnSeason.getText(), Toast.LENGTH_SHORT).show();
                    }
                });
                alertDialog.setNegativeButton("non", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                alertDialog.show();
            }
        });
        final Button btnGluten= (Button)findViewById(R.id.main_btn_gluten);
        btnGluten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                alertDialog.setTitle("Sans gluten");
                alertDialog.setMessage("Voulez-vous choisir une recette sans gluten ?");
                alertDialog.setPositiveButton("oui", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(MainActivity.this, GlutenActivity.class);
                        startActivity(intent);
                        Toast.makeText(getApplicationContext(), btnGluten.getText(), Toast.LENGTH_SHORT).show();
                    }
                });
                alertDialog.setNegativeButton("non", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                alertDialog.show();
            }
        });
    }

    /**
     * Gestion ouverture/fermeture du menu général
     * @param  Menu
     * @return boolean true
     */
    @Override
    public boolean onCreateOptionsMenu(Menu pMenu) {
        MenuInflater inflater = this.getMenuInflater();
        inflater.inflate(R.menu.menu_general, pMenu);
        return true;
    }

    /**
     * Lance une action en fonction de l'élément du menu sélectionné
     * @param pItem menuItem
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem pItem){
        Toast t;
        Intent intent;
        switch (pItem.getItemId()){
            case R.id.menu_search:
                Log.i( "LogNesti",  "Menu : Recherche");
                t = Toast.makeText(this, "Recherche", Toast.LENGTH_SHORT);
                t.show();
                intent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intent);
                break;
            case R.id.menu_list:
                Log.i( "LogNesti", "Menu : Liste de course");
                t = Toast.makeText(this, "Liste", Toast.LENGTH_SHORT);
                t.show();
                intent = new Intent(MainActivity.this, listCourseActivity.class);
                startActivity(intent);
                break;
            case R.id.menu_contact:
                Log.i( "LogNesti", "Menu : Contact");
                t = Toast.makeText(this, "Contact", Toast.LENGTH_SHORT);
                t.show();
                intent = new Intent(MainActivity.this, TabRecipeActivity.class);
                startActivity(intent);
                break;
            case R.id.menu_team:
                Log.i( "LogNesti", "Menu : Equipe");
                t = Toast.makeText(this, "L'équipe", Toast.LENGTH_SHORT);
                t.show();
                break;
            case R.id.menu_project:
                Log.i( "LogNesti", "Menu : Projet");
                t = Toast.makeText(this, "Le projet", Toast.LENGTH_SHORT);
                t.show();
                break;
        }
        return true;
    }



}