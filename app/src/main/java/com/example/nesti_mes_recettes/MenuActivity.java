package com.example.nesti_mes_recettes;


import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity {
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
                intent = new Intent(getApplicationContext(), SearchActivity.class);
                startActivity(intent);
                break;
            case R.id.menu_list:
                Log.i( "LogNesti", "Menu : Liste de course");
                t = Toast.makeText(this, "Liste", Toast.LENGTH_SHORT);
                t.show();
                intent = new Intent(getApplicationContext(), listCourseActivity.class);
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
