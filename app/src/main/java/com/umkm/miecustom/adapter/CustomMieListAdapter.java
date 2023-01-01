package com.umkm.miecustom.adapter;


import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.umkm.miecustom.R;
import com.umkm.miecustom.model.CustomMieData;

import java.util.List;

public class CustomMieListAdapter extends RecyclerView.Adapter<CustomMieListAdapter.MyViewHolder> {
    private Context context;
    private List<CustomMieData> dataList;
    private ItemClickListener clickListener;
    private MyViewHolder holder;
    private int position;
    private ImageView coverImage;
    private TextView title;
    private TextView price;
    private boolean rbc = false;
    private int lastcp = -1;

    public CustomMieListAdapter(Context context, List<CustomMieData> dataList, ItemClickListener clickListener) {
        this.context = context;
        this.dataList = dataList;
        this.clickListener = clickListener;
    }

    public void setCustomMieList(List<CustomMieData> dataList) {
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_custom_mie, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        CustomMieData result = dataList.get(position);
        String titlee = dataList.get(position).getTitle();
        String descp = dataList.get(position).getShortdesc();
        String hargaa = dataList.get(position).getPrice();
        String gambar = "http://miesuhh.000webhostapp.com/admin/Res_img/dishes/"+dataList.get(position).getImageProduk();
        int harggga = Integer.parseInt(hargaa.replaceAll("[\\D]",""));
        holder.price.setText("Rp "+harggga);
        holder.title.setText(titlee);
        holder.desc.setText(descp);
        Glide.with(context)
                .load(gambar)
                .apply(RequestOptions.centerCropTransform())
                .into(holder.coverImage);
        holder.ctv.setChecked(position == lastcp);
        if (holder.ctv.isChecked()){
            holder.ctv.setTextColor(ContextCompat.getColor(context,R.color.black));
            holder.ctv.setText("Dipilih");
            clickListener.onClick(result);
        } else{
            holder.ctv.setTextColor(ContextCompat.getColor(context,R.color.white));
            holder.ctv.setText("Pilih");
        }

    }



    @Override
    public int getItemCount() {
        if(this.dataList != null) {
            return this.dataList.size();
        }
        return 0;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public final View mView;

        private ImageView coverImage;
        private TextView title;
        private TextView desc;
        private TextView price;
        private RadioButton ctv;
        private RelativeLayout rl;

        MyViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            coverImage = mView.findViewById(R.id.coverImage);
            title = mView.findViewById(R.id.titleprdk);
            desc = mView.findViewById(R.id.desprod);
            price = mView.findViewById(R.id.hargaprod);
            ctv = mView.findViewById(R.id.btnPilih);
            rl = mView.findViewById(R.id.itemmie);

            ctv.setOnClickListener(view->{
                int cplcp = lastcp;
                lastcp = getAdapterPosition();
                notifyItemChanged(cplcp);
                notifyItemChanged(lastcp);
            });

        }
    }

    public interface ItemClickListener{

        void onClick(CustomMieData result);
    }
}

