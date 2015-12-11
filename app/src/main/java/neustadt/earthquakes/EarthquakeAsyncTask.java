package neustadt.earthquakes;

import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class EarthquakeAsyncTask extends AsyncTask<Void, Void, Earthquake> {
    private Earthquake earthquakes;
    private RecyclerView recyclerView;

    public EarthquakeAsyncTask(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }

    @Override
    protected Earthquake doInBackground(Void... params) {
        try {
            URL url = new URL("http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/significant_month.geojson");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            InputStream in = connection.getInputStream();
            Gson gson = new GsonBuilder().create();
            this.earthquakes = gson.fromJson(new InputStreamReader(in), Earthquake.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return earthquakes;
    }

    protected void onPostExecute(Earthquake earthquakes) {
        super.onPostExecute(earthquakes);
        EarthquakeAdapter adapter = new EarthquakeAdapter(earthquakes.getFeatures());
        recyclerView.setAdapter(adapter);
    }
}
