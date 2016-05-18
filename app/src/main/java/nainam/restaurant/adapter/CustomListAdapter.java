package nainam.restaurant.adapter;

/**
 * Created by istiak on 5/13/2016.
 */
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;

import jp.wasabeef.glide.transformations.CropCircleTransformation;
import nainam.restaurant.Model.Restaurant;
import nainam.restaurant.R;

public class CustomListAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<Restaurant> movieItems;
    private Context mContext;
    private int layoutResourceId;
    //ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    public CustomListAdapter(Context context,int layoutResourceId, List<Restaurant> movieItems) {
        this.layoutResourceId = layoutResourceId;
        this.mContext = context;
        this.movieItems = movieItems;
    }

    @Override
    public int getCount() {
        return movieItems.size();
    }

    @Override
    public Object getItem(int location) {
        return movieItems.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setGridData(List<Restaurant> movieItems) {
        this.movieItems = movieItems;
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder holder;

        if (row == null) {
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new ViewHolder();
             holder.titleTextView = (TextView) row.findViewById(R.id.genre);
            holder.anotherTextView = (TextView) row.findViewById(R.id.rating);
            holder.imageView = (ImageView) row.findViewById(R.id.imageView);

            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }

        Restaurant item = movieItems.get(position);
        holder.titleTextView.setText(Html.fromHtml(item.getName()));
        holder.anotherTextView.setText(Html.fromHtml(item.getAddress()));


        Glide.with(mContext).
                load(item.getRestaurantImage()).
                into(holder.imageView);


        return row;
    }

    static class ViewHolder {
        TextView titleTextView;
        TextView anotherTextView;
        ImageView imageView;


    }

}
