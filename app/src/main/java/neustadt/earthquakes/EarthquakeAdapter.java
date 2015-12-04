package neustadt.earthquakes;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class EarthquakeAdapter extends RecyclerView.Adapter<EarthquakeViewHolder>{

    private Earthquake[] earthquakes;

    public EarthquakeAdapter(Earthquake[] earthquakes){
        this.earthquakes = earthquakes;
    }

    @Override
    public EarthquakeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.earthquake_list_item, parent, false);
        return new EarthquakeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final EarthquakeViewHolder holder, final int position) {
        holder.bind(earthquakes[position]);
    }

    @Override
    public int getItemCount() {
        return earthquakes.length;
    }
}


