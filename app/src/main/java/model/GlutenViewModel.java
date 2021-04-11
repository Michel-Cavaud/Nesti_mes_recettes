package model;

import android.app.Application;
import android.util.Log;
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

public class GlutenViewModel extends AndroidViewModel {
    private MutableLiveData<List<Recipe>> recipes;


    /**
     * Constructor
     * @param application
     */
    public GlutenViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<Recipe>> getRecipes(String type) {
        if (recipes == null) {
            recipes = new MutableLiveData<List<Recipe>>();
            loadRecipes(type);
        }
        return recipes;
    }

    private void loadRecipes(String type) {
        final GlobalsVariable globalsVariable = (GlobalsVariable)getApplication().getApplicationContext();
        String url = globalsVariable.getUrlAPI() + "category/" + type;
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
                r.setId(object_JSON.getInt("id"));
                r.setTitle(object_JSON.getString("title"));
                r.setAuthor(object_JSON.getString("author"));
                r.setNb(object_JSON.getInt("nb"));
                r.setTemps(object_JSON.getString("temps"));


                String  img = object_JSON.getString("img");
                r.setImg(img);

                int s = this.getResourceImage("star_" + object_JSON.getString("diff"));
                r.setImgStarId(s);

                recipesTemp.add(r);
            }
        }catch (Exception e){
            Log.e("LogNesti", "Erreur de convertion du Json");
        }

        this.recipes.setValue(recipesTemp);
    }

    private int getResourceImage(String nameImage){
        String path = getApplication().getPackageName() + ":drawable/" + nameImage;
        return getApplication().getResources().getIdentifier(path, null, null);
    }
}
