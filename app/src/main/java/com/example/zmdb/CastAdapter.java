package com.example.zmdb;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.zmdb.models.Cast;

import java.util.ArrayList;
import java.util.List;

public class CastAdapter extends RecyclerView.Adapter<CastAdapter.CastViewHolder> {
    private static final String IMG_BASE_URL = "https://image.tmdb.org/t/p/w500";

    private List<Cast> castList;

    public CastAdapter(List<Cast> castList) {
        this.castList = castList;
    }

    @NonNull
    @Override
    public CastViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_row_cast, viewGroup, false);
        return new CastViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CastViewHolder holder, int position) {
        Cast cast = castList.get(position);
        Glide.with(holder.itemView.getContext())
                .load(IMG_BASE_URL + cast.getProfile_path())
                .placeholder(R.drawable.img_placeholder)
                .error(R.drawable.img_placeholder)
                .into(holder.imgCastProfilePhoto);
        holder.tvCharacter.setText(cast.getCharacter());
        holder.tvName.setText(cast.getName());
        if (cast.getGender() == 1) {
            holder.tvGender.setText(R.string.female);
        } else {
            holder.tvGender.setText(R.string.male);
        }
    }

    @Override
    public int getItemCount() {
        return castList.size();
    }

    public class CastViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgCastProfilePhoto;
        private TextView tvCharacter, tvName, tvGender;

        public CastViewHolder(@NonNull View itemView) {
            super(itemView);
            imgCastProfilePhoto = itemView.findViewById(R.id.img_cast_profile_photo);
            tvCharacter = itemView.findViewById(R.id.tv_cast_character);
            tvName = itemView.findViewById(R.id.tv_cast_name);
            tvGender = itemView.findViewById(R.id.tv_cast_gender);
        }
    }
}
