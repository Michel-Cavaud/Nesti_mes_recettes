package adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.nesti_mes_recettes.R;
import com.example.nesti_mes_recettes.TabRecipeActivity;

import java.util.ArrayList;

import entity.Recipe;

public class SearchAdapter extends ArrayAdapter<Recipe> {

    public SearchAdapter(@NonNull Context context, int textViewResourceId, ArrayList<Recipe> recipes){
        super(context, textViewResourceId, recipes);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        View result = convertView;
        if(convertView == null){
            result = LayoutInflater.from(getContext()).inflate(R.layout.line_search, parent, false);
        }
        Recipe one_recipe = getItem(position);

        TextView title = result.findViewById(R.id.lineSearche_tx_recette);
        title.setText(one_recipe.getTitle());

        final Button btnVoir= result.findViewById(R.id.search_line_btn_voir);
        btnVoir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast t = Toast.makeText(getContext().getApplicationContext(), "ID: " + one_recipe.getId() + " " + one_recipe.getTitle(), Toast.LENGTH_SHORT);
                t.show();

                Intent intent = new Intent(getContext(), TabRecipeActivity.class);
                // création du paquet
                Bundle extras = new Bundle();
                // Remplir le paquet
                extras.putInt("id", one_recipe.getId());
                extras.putString("name", one_recipe.getTitle());
                // Le paquet est attaché à l'intention
                intent.putExtras(extras);
                // L'activité est lancée
                getContext().startActivity(intent);

            }
        });
        return result;
    }

}
