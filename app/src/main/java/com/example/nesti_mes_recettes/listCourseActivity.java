package com.example.nesti_mes_recettes;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import adapter.CourseAdapter;
import adapter.IngredientAdapter;
import data.sqlite.TableCart;
import entity.Ingredient;

public class listCourseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_course);

        TableCart model = new TableCart(this);
        ArrayList<Ingredient> items = model.getAllItems();

        ListView list_view = (ListView)this.findViewById(R.id.list_list_course);
        CourseAdapter adapter = new CourseAdapter(this, R.layout.line_course,
                (ArrayList<Ingredient>) items);
        list_view.setAdapter(adapter);

        final Button btnEasy = (Button)findViewById(R.id.btn_listCourse_vider);
        btnEasy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(listCourseActivity.this);
                alertDialog.setTitle("Vider la liste");
                alertDialog.setMessage("Vous allez vder votre liste de course !");
                alertDialog.setPositiveButton("oui", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        model.removeAllItem();
                        list_view.removeAllViewsInLayout();
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
}