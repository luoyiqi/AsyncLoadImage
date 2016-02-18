package ua.com.kistudio.asynctaskp;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by Вiталя on 18.02.2016.
 */
public class MyAdapter extends BaseAdapter {

    Context context;
    ArrayList<Bitmap> bitmapArrayList;
    LayoutInflater inflater;

    public MyAdapter(Context context, ArrayList<Bitmap> bitmapArrayList) {
        this.context = context;
        this.bitmapArrayList = bitmapArrayList;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return bitmapArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return bitmapArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view==null){
            view = inflater.inflate(R.layout.item,parent,false);
        }
        ((ImageView) view.findViewById(R.id.ivItem))
                .setImageBitmap(bitmapArrayList.get(position));
        return view;
    }
}
