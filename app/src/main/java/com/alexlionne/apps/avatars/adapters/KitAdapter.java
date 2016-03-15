package com.alexlionne.apps.avatars.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.alexlionne.apps.avatars.R;
import com.alexlionne.apps.avatars.objects.Kit;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;

import java.util.List;

/**
 * This Class was created by Patrick J
 * on 19.01.16. For more Details and Licensing
 * have a look at the README.md
 */
public class KitAdapter extends RecyclerView.Adapter<KitAdapter.ViewHolder> {

    private final List<Kit> itemData;
    private final Context context;
    private final OnItemClickListener onItemClickListener;
    private int lastPosition = -1;


    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public KitAdapter(Context context, List<Kit> items, OnItemClickListener onItemClickListener) {
        this.itemData = items;
        this.context = context;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.kit_item, parent, false);
        return new ViewHolder(view, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Kit kit = itemData.get(position);
        holder.name.setText(kit.getName());
        holder.smalldesc.setText(kit.getSmallDesc());
        holder.wall.setImageDrawable(context.getResources().getDrawable(kit.getShowcase()));
        holder.icon.setImageDrawable(kit.getIcon());
        setAnimation(holder.content, position);



    }
    private void setAnimation(View viewToAnimate, int position)
    {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition)
        {
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }


    public Kit getItemAtPosition(int position) {
        return itemData.get(position);
    }

    @Override
    public int getItemCount() {
        return itemData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        final ImageView wall,icon;
        final TextView name;
        final TextView smalldesc;
        final OnItemClickListener onItemClickListener;
        final CardView content;

        public ViewHolder(View v, OnItemClickListener onItemClickListener) {
            super(v);
            v.setClickable(true);
            v.setOnClickListener(this);
            wall = (ImageView) v.findViewById(R.id.kit_showcase);
            icon = (ImageView) v.findViewById(R.id.kit_icon);
            content = (CardView)v.findViewById(R.id.card);
            name = (TextView) v.findViewById(R.id.kit_name);
            smalldesc = (TextView) v.findViewById(R.id.kit_small_desc);
            this.onItemClickListener = onItemClickListener;
        }

        @Override
        public void onClick(View v) {
            onItemClickListener.onItemClick(getAdapterPosition());
        }

    }

}