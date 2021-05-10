package com.example.appseriespelis;

import android.os.AsyncTask;
import android.util.Log;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appseriespelis.RecyclerViews.ListAdapter;
import com.example.appseriespelis.Objetos.PeliculaSerie;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public class fetchData extends AsyncTask<Void, Void, Void> {
    boolean algo = true;
    ArrayList<PeliculaSerie> elements;
    protected String data = "";
    protected String results = "";
    protected ArrayList<String> strTypes; // Create an ArrayList object
    protected String movie;


    protected FragmentActivity fr;

    public fetchData(String movie, FragmentActivity fr) {

        this.movie = movie;
        this.fr = fr;

    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {

            //Make API connection
            URL url = new URL("https://api.themoviedb.org/3/search/movie?api_key=dc5c546bfdaef9a6da9e082c1e2ef636&query=" + movie.replaceAll(" ", "+"));
            Log.i("logTest", "https://api.themoviedb.org/3/search/movie?api_key=dc5c546bfdaef9a6da9e082c1e2ef636&query=" + movie.replaceAll(" ", "+"));

            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            // Read API results
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder sBuilder = new StringBuilder();

            // Build JSON String
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                sBuilder.append(line + "\n");
            }

            inputStream.close();
            data = sBuilder.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid){
        JSONObject jObject = null;
        try {
            jObject = new JSONObject(data);

            elements = new ArrayList<>();


            JSONArray results = new JSONArray(jObject.getString("results"));
            for (int i = 0; i < results.length(); i++){
                JSONObject movie = new JSONObject(results.getString(i));
                String title = movie.getString("title");
                String popularity = movie.getString("popularity");
                String overview = movie.getString("overview");
                Integer id_peli = movie.getInt("id");
               // Log.i("logTest", title);

                String poster_path = movie.getString("poster_path");

                JSONArray genre_id = new JSONArray(movie.getString("genre_ids"));

                ArrayList<Integer> listGenres = new ArrayList<Integer>();
                for (int j = 0; j < genre_id.length(); j++){

                    Log.i("logTest", String.valueOf(genre_id.getInt(j)));
                    listGenres.add(genre_id.getInt(j));
                }



                elements.add(new PeliculaSerie("#000000", title, "No Visto",popularity, listGenres, overview, poster_path,id_peli));

            }

            //Pasamos los que hemos introducido  al recyclerView

            ListAdapter listAdapter = new ListAdapter(elements, fr, 0);
            RecyclerView recyclerView = fr.findViewById(R.id.lista_recyclerview);

            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(fr));
            recyclerView.setAdapter(listAdapter);



        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

}
