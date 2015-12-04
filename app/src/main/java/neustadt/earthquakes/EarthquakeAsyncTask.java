package neustadt.earthquakes;


import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class EarthquakeAsyncTask extends AsyncTask {
    private Earthquake[] earthquakes;
    private RecyclerView recyclerView;

    public EarthquakeAsyncTask(RecyclerView recyclerView) {
    this.recyclerView = recyclerView;
    }

    @Override
    protected Object doInBackground(Object[] params) {
        InputStream in = null;
        try {
            URL url = new URL("http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_hour.geojson");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            in = connection.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Gson gson = new GsonBuilder().create();
        earthquakes = gson.fromJson(new InputStreamReader(in), Earthquake[].class);
        return earthquakes;
    }

    protected void onPostExecute(Earthquake[] earthquakes) {
        super.onPostExecute(earthquakes);
        EarthquakeAdapter adapter = new EarthquakeAdapter(earthquakes);
        recyclerView.setAdapter(adapter);

    }
}
