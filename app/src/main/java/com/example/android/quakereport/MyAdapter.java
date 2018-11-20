package com.example.android.quakereport;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import android.graphics.drawable.GradientDrawable;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private static final String LOG_TAG = MyAdapter.class.getSimpleName();
    private ArrayList<EarthquakeModel> mEarthquakeModels;
    private int magnitudeColor;
    private Context mContext;


    public MyAdapter(ArrayList<EarthquakeModel> earthquakeModels) {
        mEarthquakeModels = earthquakeModels;
    }

    @NonNull
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.ViewHolder holder, int position) {
        EarthquakeModel earthquakeModel = mEarthquakeModels.get(position);
        holder.mag.setText(String.valueOf(earthquakeModel.getMagnitude()));
        int magnitudeColor = holder.getMagnitudeColor(earthquakeModel.getMagnitude());
        holder.magnitudeCircle.setColor(magnitudeColor);
        holder.place.setText(String.valueOf(earthquakeModel.getPlace()));
        holder.date.setText(String.valueOf(earthquakeModel.getDate()));
        Log.d(LOG_TAG, String.valueOf(magnitudeColor));
    }

    @Override
    public int getItemCount() {
        return mEarthquakeModels.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView mag,place,date;
        GradientDrawable magnitudeCircle;

        public ViewHolder(View itemView) {
            super(itemView);
            mag = itemView.findViewById(R.id.id_mag);
            place = itemView.findViewById(R.id.id_place);
            date = itemView.findViewById(R.id.id_date);
            magnitudeCircle = (GradientDrawable) mag.getBackground();
        }

        private int getMagnitudeColor(double magnitude) {
            int magnitudeColorResourceId;
            int magnitudeFloor = (int) Math.floor(magnitude);
            switch (magnitudeFloor) {
                case 0:
                case 1:
                    magnitudeColorResourceId = R.color.magnitude1;
                    break;
                case 2:
                    magnitudeColorResourceId = R.color.magnitude2;
                    break;
                case 3:
                    magnitudeColorResourceId = R.color.magnitude3;
                    break;
                case 4:
                    magnitudeColorResourceId = R.color.magnitude4;
                    break;
                case 5:
                    magnitudeColorResourceId = R.color.magnitude5;
                    break;
                case 6:
                    magnitudeColorResourceId = R.color.magnitude6;
                    break;
                case 7:
                    magnitudeColorResourceId = R.color.magnitude7;
                    break;
                case 8:
                    magnitudeColorResourceId = R.color.magnitude8;
                    break;
                case 9:
                    magnitudeColorResourceId = R.color.magnitude9;
                    break;
                default:
                    magnitudeColorResourceId = R.color.magnitude10plus;
                    break;
            }
            return ContextCompat.getColor(itemView.getContext(),magnitudeColorResourceId);
        }
    }


}
