package com.codingblocks.applock;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class chamberAdapter extends BaseAdapter {

    ArrayList<chamber_value >chamber_values;
    Context context;

    public chamberAdapter(ArrayList<chamber_value> chamber_values, Context context) {
        this.chamber_values = chamber_values;
        this.context = context;
    }

    @Override

    public int getCount() {
      return chamber_values.size();
    }

    @Override
    public chamber_value getItem(int position) {
       return chamber_values.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       SuperHeroHolder holder;
        View inflatedView;
        LayoutInflater li = LayoutInflater.from(context);
        if(convertView==null) {
            inflatedView = li.inflate(R.layout.list_chamber, parent, false);
            holder = new SuperHeroHolder();

            holder.imageView = inflatedView.findViewById(R.id.chamner_image);
            holder.Heading = inflatedView.findViewById(R.id.chamber_text);
            holder.subheading = inflatedView.findViewById(R.id.chamber_subheading);
            inflatedView.setTag(holder);

        }
        else
        {
            inflatedView=convertView;
            holder=(SuperHeroHolder)inflatedView.getTag();
        }
     chamber_value chamberValue =getItem(position) ;


        holder.Heading.setText(chamberValue.getHeading());
        holder.imageView.setImageDrawable(chamberValue.getImageView());
        holder. subheading.setText(chamberValue.getSubheading());

        return inflatedView;
    }

    class SuperHeroHolder{
       ImageView imageView;
        TextView Heading,subheading;
    }


}

