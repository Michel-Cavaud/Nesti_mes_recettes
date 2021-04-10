package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.nesti_mes_recettes.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import data.sqlite.TableCart;
import entity.Ingredient;

public class CourseAdapter extends ArrayAdapter<Ingredient> {
    public CourseAdapter (@NonNull Context context, int textViewResourceId, ArrayList<Ingredient> ingredients){
        super(context, textViewResourceId, ingredients);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        View result = convertView;
        if(convertView == null){
            result = LayoutInflater.from(getContext()).inflate(R.layout.line_course, parent, false);
        }
        Ingredient one_ingredient = getItem(position);

        CheckedTextView nom = (CheckedTextView)result.findViewById(R.id.checkedTextView);
        nom.setText(one_ingredient.getNom());
        if(one_ingredient.getCheck() == 0){
            nom.setChecked(false);
        }else{
            nom.setChecked(true);
        }
        nom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TableCart model = new TableCart(getContext());

                try {
                    if(nom.isChecked()){
                        one_ingredient.setCheck(0);
                        model.updateById(one_ingredient);
                        nom.setChecked(false);
                    }else{
                        one_ingredient.setCheck(1);
                        model.updateById(one_ingredient);
                        nom.setChecked(true);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        return result;
    }
}
