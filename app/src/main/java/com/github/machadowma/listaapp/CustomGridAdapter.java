package com.github.machadowma.listaapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomGridAdapter extends BaseAdapter {

    private ArrayList<Animal> listData;
    private LayoutInflater layoutInflater;

    static class ViewHolder {
        TextView textViewNome, textViewRaca;
        ImageView imageView;
    }

    public CustomGridAdapter(Context aContext, ArrayList<Animal> listData) {
        this.listData = listData;
        layoutInflater = LayoutInflater.from(aContext);
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        final int pos = position;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_grid, null);
            holder = new ViewHolder();
            holder.textViewNome = (TextView) convertView.findViewById(R.id.textViewNome);
            holder.textViewRaca = (TextView) convertView.findViewById(R.id.textViewRaca);
            holder.imageView = (ImageView) convertView.findViewById(R.id.imageView);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.textViewNome.setText(listData.get(position).getNome());
        holder.textViewRaca.setText(listData.get(position).getRaca());
        holder.imageView.setImageResource(listData.get(position).getDrawableId());

        return convertView;
    }
}
