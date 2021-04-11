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

        //ajout des variable globales à partir de la class GloblasVariable qui est ajouté au fichier AndroidManifest.xml
        final GlobalsVariable globalsVariable = (GlobalsVariable) getApplicationContext();
        globalsVariable.setUrlAPI("http://192.168.0.38/Nesti3CI4/public/index.php/api/");
        globalsVariable.setUrlImage("http://192.168.0.38/Nesti3/public/images/upload/");

        setContentView(R.layout.activity_main);
        Bundle extras = new Bundle();

        // bouton facile
        final Button btnEasy = (Button)findViewById(R.id.main_btn_easy);
        btnEasy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RecettesActivity.class);
                extras.putString("cat", "facile");
                extras.putString("titre", "Les recettes faciles");
                intent.putExtras(extras);
                startActivity(intent);
            }
        });

        final Button btnTraditional= (Button)findViewById(R.id.main_btn_traditional);
        btnTraditional.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RecettesActivity.class);
                extras.putString("cat", "traditionnelles");
                extras.putString("titre", "Les recettes traditionnelles");
                intent.putExtras(extras);
                startActivity(intent);
            }
        });

        final Button btnSeason= (Button)findViewById(R.id.main_btn_season);
        btnSeason.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RecettesActivity.class);
                extras.putString("cat", "saison");
                extras.putString("titre", "Les recettes de saison");
                intent.putExtras(extras);
                startActivity(intent);
            }
        });

        final Button btnGluten= (Button)findViewById(R.id.main_btn_gluten);
        btnGluten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RecettesActivity.class);
                extras.putString("cat", "gluten");
                extras.putString("titre", "Les recettes sans gluten");
                intent.putExtras(extras);
                startActivity(intent);
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
                //intent = new Intent(MainActivity.this, TabRecipeActivity.class);
                //startActivity(intent);
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