package com.umkm.miecustom.adapter;


import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.umkm.miecustom.R;
import com.umkm.miecustom.model.CustomToppingData;

import java.util.ArrayList;
import java.util.List;

public class CustomToppingListAdapter extends RecyclerView.Adapter<CustomToppingListAdapter.MyViewHolder> {
    private Context context;
    private List<CustomToppingData> dataList;
    private List<String>toplist;
    private ItemClickListener clickListener;
    private MyViewHolder holder;
    public List<String>list1 = new ArrayList<>();
    public List<String>list2 = new ArrayList<>();
    private int position;
    ArrayList<CustomToppingData> selectedCheckbox = new ArrayList<CustomToppingData>();

    private boolean rbc = false;
    private int lastcp = -1;

    public CustomToppingListAdapter(Context context, List<CustomToppingData> dataList, ItemClickListener clickListener) {
        this.context = context;
        this.dataList = dataList;
        this.toplist=toplist;
        this.clickListener = clickListener;
    }

    public void setCustomToppingList(List<CustomToppingData> dataList) {
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_topping, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        CustomToppingData result = dataList.get(position);
        Integer id = dataList.get(position).getId_topping();
        String titlee = dataList.get(position).getTitle();
        String hargaa = dataList.get(position).getPrice();
        int hrggg = Integer.parseInt(hargaa.replaceAll("[\\D]",""));
        String topdata = titlee+hargaa;
        holder.price.setText("Rp "+hrggg);
        holder.title.setText(titlee);
        holder.ctv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dataList != null && dataList.size() > 0){
                    if (holder.ctv.isChecked()){
                        dataList.add(result);
                        clickListener.onClick(result,samanamalis(list1,result),samahargalis(list2,result));

                    }else{
                        deletenamalis(list1,result);
                        deletehargalis(list2,result);
                        dataList.remove(result);
                    }
                }
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

        private TextView title;
        private TextView price;
        ImageView kurang,tambah;
        TextView jumlah;
        private int count=0;

        List<String>list1 = new ArrayList<>();
        List<String>list2 = new ArrayList<>();

         CheckBox ctv;
        private RelativeLayout rl;

        MyViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            title = mView.findViewById(R.id.namatop);
            price = mView.findViewById(R.id.hargatop);

            ctv = mView.findViewById(R.id.cektop);

            ctv.setOnClickListener(view->{
                int cplcp = lastcp;
                lastcp = getAdapterPosition();
                notifyItemChanged(cplcp);
                notifyItemChanged(lastcp);
            });

        }
    }
    public List<String> samanamalis(List<String>list1, CustomToppingData result){
        list1.add(result.getTitle());
       // Set<String> uniqe =new HashSet<String>(list1);
       // list1.clear();
       // list1.addAll(uniqe);
        return list1;
    }
    public List<String> samahargalis(List<String> list2, CustomToppingData result){
        list2.add(result.getPrice());
      //  Set<String> uniqe =new HashSet<String>(list2);
       // list2.clear();
      //  list2.addAll(uniqe);
        return list2;
    }
    public List<String> deletenamalis(List<String>list1, CustomToppingData result){

        list1.remove(result.getTitle());
        return list1;
    }
    public List<String> deletehargalis(List<String> list2, CustomToppingData result){

        list2.remove(result.getPrice());
        return list2;
    }

    public interface ItemClickListener{

        void onClick(CustomToppingData result, List<String> list1, List<String> samahargalis);
    }

}

