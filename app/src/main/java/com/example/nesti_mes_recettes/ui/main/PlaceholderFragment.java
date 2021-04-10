package com.example.nesti_mes_recettes.ui.main;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.nesti_mes_recettes.R;

import java.util.ArrayList;

import adapter.IngredientAdapter;
import adapter.RecipeAdapter;
import entity.Ingredient;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;
    private int id;
    public static PlaceholderFragment newInstance(int index, int id) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        fragment.setId(id);
        return fragment;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        pageViewModel = new ViewModelProvider
                .AndroidViewModelFactory(this.getActivity().getApplication())
                .create(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setMIndex(index);
    }

    @Override
    public View onCreateView(
        @NonNull LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_tab_recipe, container, false);
        final TextView textView = root.findViewById(R.id.frament_section_label);
        pageViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        pageViewModel.getMIndex(id).observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                Log.i("LogNesti", "mes:" + integer);
                if( integer == 1) {
                  pageViewModel.getIngredients(id).observe(
                        PlaceholderFragment.this, ingredients -> {
                            ListView list_view = (ListView)root.findViewById(R.id.frament_listView_details);
                            IngredientAdapter adapter = new IngredientAdapter(getActivity(), R.layout.activity_tab_recipe,
                            (ArrayList<Ingredient>) ingredients);
                            list_view.setAdapter(adapter);
                        });
                }else{
                    // Afficher les préparations
                    pageViewModel.getSteps(id).observe(PlaceholderFragment.this, steps -> {
                        ListView list_view = (ListView)root.findViewById(R.id.frament_listView_details);
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                        android.R.layout.simple_list_item_1, (ArrayList<String>) steps);
                        list_view.setAdapter(adapter);
                    });
                }
            }
        });

        return root;
    }
}