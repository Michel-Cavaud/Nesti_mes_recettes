package model;

import android.app.Application;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.nesti_mes_recettes.GlobalsVariable;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import entity.Recipe;

public class SearchViewModel extends AndroidViewModel {

    private MutableLiveData<List<Recipe>> recipes;


    /**
     * Constructor
     * @param application
     */
    public SearchViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<Recipe>> getRecipes(String recherche) {
        if (recipes == null) {
            recipes = new MutableLiveData<List<Recipe>>();
            loadRecipes(recherche);
        }
        return recipes;
    }
    private void loadRecipes(String recherche) {
        final GlobalsVariable globalsVariable = (GlobalsVariable)getApplication().getApplicationContext();
        String url = globalsVariable.getUrlAPI() + "search/" + recherche;
        requestApi(url);
    }

    private void requestApi(String url){

        final RequestQueue requestQueue = Volley.newRequestQueue(getApplication().getApplicationContext());

        JsonArrayRequest arrayRequest = new JsonArrayRequest(
                url,
                new Response.Listener<JSONArray>(){
                    @Override
                    public void onResponse(JSONArray response){
                        Log.i("LogNesti", response.toString());
                        readJSONRecipe(response);
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        Toast.makeText(
                                getApplication().getApplicationContext(),
                                "Une erreur est survenue sur l'iteroogation de l'URI",
                                Toast.LENGTH_SHORT).show();
                        error.printStackTrace();
                    }
                }
        );
        requestQueue.add(arrayRequest);
    }

    private void readJSONRecipe(JSONArray response){

        ArrayList<Recipe> recipesTemp = new ArrayList<>();

        try{
            //JSONArray tableau_JSON = new JSONArray(response);
            Log.i("LogNesti", "Nombre d enregistre :" +  response.length());

            for(int i = 0; i < response.length(); i++){
                JSONObject object_JSON = response.getJSONObject(i);

                Recipe r = new Recipe();
                r.setId(object_JSON.getInt("id_recettes"));
                r.setTitle(object_JSON.getString("nom_recettes"));
                r.setTemps(object_JSON.getString("temps_recettes"));
                r.setNb(object_JSON.getInt("nombre_personne_recettes"));

                recipesTemp.add(r);
            }
        }catch (Exception e){
            Log.e("LogNesti", "Erreur de convertion du Json");
        }

        this.recipes.setValue(recipesTemp);
    }

}
