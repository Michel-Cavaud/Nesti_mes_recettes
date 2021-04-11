package adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.text.LineBreaker;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.example.nesti_mes_recettes.GlobalsVariable;
import com.example.nesti_mes_recettes.R;
import com.example.nesti_mes_recettes.TabRecipeActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import entity.Recipe;

public class RecipeAdapter extends ArrayAdapter<Recipe> {

    public RecipeAdapter(@NonNull Context context, int textViewResourceId, ArrayList<Recipe> recipes){
        super(context, textViewResourceId, recipes);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        View result = convertView;
        if(convertView == null){
            result = LayoutInflater.from(getContext()).inflate(R.layout.line_recipe, parent, false);
        }
        Recipe one_recipe = getItem(position);

        TextView title = result.findViewById(R.id.line_recipe_txtView_title);

        title.setText(one_recipe.getTitle());

        TextView author = result.findViewById(R.id.line_recipe_txtView_author);
        author.setText(one_recipe.getAuthor());

        ImageView imageView = result.findViewById(R.id.line_recipe_imgView_recipe);
       // imageView.setImageResource(one_recipe.getImgId());

        final GlobalsVariable globalsVariable = (GlobalsVariable) getContext().getApplicationContext();
        Picasso.get()
                .load( globalsVariable.getUrlImage() + one_recipe.getImg())
                .into(imageView);


        ImageView imageStar= result.findViewById(R.id.line_recipe_imgStar);
        imageStar.setImageResource(one_recipe.getImgStarId());

        result.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

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
