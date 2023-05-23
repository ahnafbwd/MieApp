package com.umkm.miecustom.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.umkm.miecustom.R;
import com.umkm.miecustom.model.ProductData;

import java.util.List;

public class ProductListAdapter  extends RecyclerView.Adapter<ProductListAdapter.MyViewHolder> {
    private Context context;
    private List<ProductData> dataList;
    private ItemClickListener clickListener;

    public ProductListAdapter(Context context, List<ProductData> dataList, ItemClickListener clickListener) {
        this.context = context;
        this.dataList = dataList;
        this.clickListener = clickListener;
    }

    public void setProductList(List<ProductData> dataList) {
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_product, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ProductData result = dataList.get(position);
        holder.iid = dataList.get(position).getId_product();
        String titlee = dataList.get(position).getTitle();
        String hargaa = dataList.get(position).getPrice();
        String gambar = "http://miesuhh.000webhostapp.com/admin/Res_img/dishes/"+result.getImageProduk();
        holder.price.setText("Rp "+hargaa);
        holder.title.setText(titlee);
        Glide.with(context)
                .load(gambar)
                .apply(RequestOptions.centerCropTransform())
                .into(holder.coverImage);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onClick(result);
            }
        });
        holder.btntambahh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onClick(result);
            }
        });
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
        private TextView price;
        Button btntambahh;
        String iid;

        MyViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            coverImage = mView.findViewById(R.id.coverImage);
            title = mView.findViewById(R.id.titleprdk);
            price = mView.findViewById(R.id.harga);
            btntambahh = mView.findViewById(R.id.btntambahh);
        }
    }


    public interface ItemClickListener{
        void onClick(ProductData result);
    }
}
