package com.example.nesti_mes_recettes.ui.main;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.arch.core.util.Function;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import entity.Ingredient;
import entity.Recipe;

public class PageViewModel extends AndroidViewModel {

    private MutableLiveData<Integer> mIndex = new MutableLiveData<>();
    private LiveData<String> mText = Transformations.map(mIndex, new Function<Integer, String>() {
        @Override
        public String apply(Integer input) {
            if(input == 1){
                return "Liste des ingrédients";
            }else{
                return "Étapes de préparation";
            }
        }
    });


    public PageViewModel(@NonNull Application application) {
        super(application);
    }
    public void setMIndex(int index) {
        mIndex.setValue(index);
    }
    public MutableLiveData<Integer> getMIndex(int id) {
        if(this.ingredients == null || this.steps == null) {
            loadData(id);
        }
        return mIndex;
    }

    public LiveData<String> getText() {
        return mText;
    }

    // Liste des ingrédients
    private MutableLiveData<List<Ingredient>> ingredients;
    public LiveData<List<Ingredient>> getIngredients(int id) {
        if (ingredients == null) {
            loadData(id);
        }
        return ingredients;
    }

    // Liste des étapes
    private MutableLiveData<List<String>> steps;
    public LiveData<List<String>> getSteps(int id) {
        if (steps == null) {
            loadData(id);
        }
        return steps;
    }

    private void loadData(int id) {
        ingredients = new MutableLiveData<List<Ingredient>>();
        steps = new MutableLiveData<List<String>>();
        String url = "http://192.168.0.38/Nesti3CI4/public/index.php/api/recipe/" + id;

        requestApi(url);
    }

    private void requestApi(String url){

        final RequestQueue requestQueue = Volley.newRequestQueue(getApplication().getApplicationContext());

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.i("LogNesti", response.toString());
                readJSONRecipe(response);
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(
                        getApplication().getApplicationContext(),
                        "Une erreur est survenue sur l'iterogation de l'URI",
                        Toast.LENGTH_SHORT).show();
                error.printStackTrace();

            }
        });
        requestQueue.add(request);
    }

    private void readJSONRecipe(JSONObject response){

        ArrayList<Recipe> RecetteTemp = new ArrayList<>();

        try{
            //JSONArray tableau_JSON = new JSONArray(response);
            Log.i("LogNesti", "Nombre d enregistre :" +  response.length());
            //JSONObject object_JSON = response.getJSONObject(0);

            JSONObject  recette = response.getJSONObject("recipe");


            JSONArray ingredients = response.getJSONArray("ingredients");
            List<Ingredient> ingredientsTemp = new ArrayList<Ingredient>();
            for(int i = 0; i < ingredients.length(); i++) {
                Ingredient ingredient = new Ingredient();
                ingredient.setId(ingredients.getJSONObject(i).getInt("id"));
                ingredient.setNom(ingredients.getJSONObject(i).getString("nom"));
                ingredient.setQty(ingredients.getJSONObject(i).getInt("qty"));
                ingredient.setUnite(ingredients.getJSONObject(i).getString("unite"));
                ingredientsTemp.add(ingredient);
            }
            this.ingredients.setValue(ingredientsTemp);

            JSONArray preparations = recette.getJSONArray("preparations");
            Log.i("LogNesti", preparations.toString());

            List<String> paragraphsTemp = new ArrayList<>();
            for(int i = 0; i< preparations.length(); i++){
                paragraphsTemp.add(preparations.getString(i));
            }
            this.steps.setValue(paragraphsTemp);

        }catch (Exception e){
            Log.e("LogNesti", "Erreur de convertion du Json");
        }

    }
}