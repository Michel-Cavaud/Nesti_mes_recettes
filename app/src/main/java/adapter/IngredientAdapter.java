package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.nesti_mes_recettes.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import data.sqlite.TableCart;
import entity.Ingredient;
import entity.Recipe;


public class IngredientAdapter extends ArrayAdapter<Ingredient> {

    public IngredientAdapter (@NonNull Context context, int textViewResourceId, ArrayList<Ingredient> ingredients){
        super(context, textViewResourceId, ingredients);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        View result = convertView;
        if(convertView == null){
            result = LayoutInflater.from(getContext()).inflate(R.layout.line_ingredient, parent, false);
        }
        Ingredient one_ingredient = getItem(position);

        TextView nom = result.findViewById(R.id.tx_ingredient);
        nom.setText(one_ingredient.getNom());

        TextView qty = result.findViewById(R.id.tx_qty_ingredient);
        qty.setText(one_ingredient.getQty() + " " + one_ingredient.getUnite());

        FloatingActionButton btn = result.findViewById(R.id.btn_ingredient);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    addItem(one_ingredient);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        return result;
    }
    /**
     * Add in database sqlite
     * @param item
     */
    public void addItem(Ingredient item) {
        try{
            TableCart model = new TableCart(getContext());
            //model.removeAllItem();
            if(!model.isFoundById(item.getId())){
                model.insertItem(item);
                Toast.makeText(getContext(), item.getNom() + " ajouté au panier",
                        Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(getContext(), item.getNom() + " est déjà dans votre liste",
                        Toast.LENGTH_SHORT).show();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
