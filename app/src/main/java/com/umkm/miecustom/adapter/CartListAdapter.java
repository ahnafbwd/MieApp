package com.umkm.miecustom.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.umkm.miecustom.EditProduct;
import com.umkm.miecustom.R;
import com.umkm.miecustom.model.CartData;
import com.umkm.miecustom.ui.cart.CartFragment;
import com.umkm.miecustom.ui.cart.CartViewModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartListAdapter extends RecyclerView.Adapter<CartListAdapter.MyViewHolder> {
    private Context context;
    private List<CartData> dataList;
   // private ItemClickListener clickListener;
    private MyViewHolder holder;
    private int position;
    private ImageView coverImage;
    private TextView title;
    private TextView price;
    CartViewModel cartViewModel;
    CartFragment cartFragment;
    private List<CartData> CartList;
    private CartViewModel viewModel;
    private List<CartData> CartList2;
    private CartViewModel viewModel2;

    public CartListAdapter(Context context, List<CartData> dataList) {
        this.context = context;
        this.dataList = dataList;
        //this.clickListener = clickListener;
    }

    public void setCartList(List<CartData> dataList) {
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cart, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        CartData result = dataList.get(position);
        Integer id_cart = dataList.get(position).getId_cart();
        String idcrt = id_cart.toString();
        String qty = dataList.get(position).getQuantity();
        String note = dataList.get(position).getNote();
        String titlee = dataList.get(position).getTitle();
        String dess = dataList.get(position).getDescription();
        String hargaa = dataList.get(position).getPrice();
        String gambar = "http://miesuhh.000webhostapp.com/admin/Res_img/dishes/"+dataList.get(position).getImageProduk();
        holder.count = Integer.parseInt(qty);
        holder.price.setText("Rp "+hargaa);
        holder.title.setText(titlee);
        holder.desc.setText(dess);
        holder.note.setText(note);
        holder.jumlah.setText(qty);
        Glide.with(context)
                .load(gambar)
                .apply(RequestOptions.centerCropTransform())
                .into(holder.coverImage);

        holder.btnedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (titlee.equals("Custom Mie")){
                    Toast.makeText(context,"ini dari custom",Toast.LENGTH_LONG).show();

                }else {
                    Intent intent = new Intent(context, EditProduct.class);
                    //intent.putExtra("darimana","daricart");
                    intent.putExtra("idcart",idcrt);
                    intent.putExtra("title_product",result.getTitle());
                    intent.putExtra("image",result.getImageProduk());
                    intent.putExtra("description",result.getDescription());
                    intent.putExtra("note",result.getNote());
                    intent.putExtra("price",result.getPrice());
                    intent.putExtra("quantity",result.getQuantity());
                    context.startActivity(intent);
                }
            }
        });

        holder.tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.count ++;
                holder.jumlah.setText(""+holder.count);
                String qtyy= String.valueOf(holder.count);
                updatecart(idcrt,dess,note,hargaa, qtyy);
            }
        });

        holder.kurang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.count<0){
                    holder.count = 0;
                    holder.jumlah.setText(holder.count);
                    String qtyy= String.valueOf(holder.count);
                    deletecart(idcrt);
                }else if (holder.count==0){
                    deletecart(idcrt);
                }else if (holder.count==1){
                    holder.count--;
                    holder.jumlah.setText(""+holder.count);
                    deletecart(idcrt);
                } else {
                    holder.count--;
                    holder.jumlah.setText(""+holder.count);
                    String qtyy= String.valueOf(holder.count);
                    updatecart(idcrt,dess,note,hargaa, qtyy);
                }
            }
        });
        /*holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                clickListener.onClick(result);
            }
        });*/
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
        private TextView note;
        LinearLayout btnedit;

        ImageView kurang,tambah;
        TextView jumlah,simpanjumlah;

        private Integer count;

        MyViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            coverImage = mView.findViewById(R.id.imageproduk);
            title = mView.findViewById(R.id.namaproduk);
            desc = mView.findViewById(R.id.deskripsiproduk);
            price = mView.findViewById(R.id.hargaproduk);
            note = mView.findViewById(R.id.noteproduk);

            kurang = mView.findViewById(R.id.btnkurang);
            tambah = mView.findViewById(R.id.btntambah);
            jumlah= mView.findViewById(R.id.txtjumlahproduk);

            btnedit = mView.findViewById(R.id.btneditcart);

        }
    }



    /*public interface ItemClickListener{
        void onClick(CartData result);
    }*/

    public void deletecart(String idcrt){
        RequestQueue queue = Volley.newRequestQueue(context.getApplicationContext());
        String url ="https://miesuhh.000webhostapp.com/mobileapps/cart/deletecart.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String status = jsonObject.getString("status");
                            String message = jsonObject.getString("message");
                            if (status.equals("success")){
                                Toast.makeText(context.getApplicationContext(),message, Toast.LENGTH_LONG).show();

                            } else {
                                Toast.makeText(context.getApplicationContext(), message, Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context.getApplicationContext(), error.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        }){
            protected Map<String, String> getParams(){
                Map<String, String> paramV = new HashMap<>();
                paramV.put("id_cart", idcrt);
                return paramV;
            }
        };
        queue.add(stringRequest);
    }
    public void updatecart(String idcrt,String dess,String note,String hargaa, String qtyy){
        RequestQueue queue = Volley.newRequestQueue(context.getApplicationContext());
        String url ="https://miesuhh.000webhostapp.com/mobileapps/cart/updateqtycart.php";
        String quantity = qtyy;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String status = jsonObject.getString("status");
                            String message = jsonObject.getString("message");
                            if (status.equals("success")){
                                Toast.makeText(context.getApplicationContext(),"Berhasil menambahkan ke keranjang", Toast.LENGTH_LONG).show();

                            } else {
                                Toast.makeText(context.getApplicationContext(), message, Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context.getApplicationContext(), error.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        }){
            protected Map<String, String> getParams(){
                Map<String, String> paramV = new HashMap<>();
                paramV.put("id_cart", idcrt);
                paramV.put("description", dess);
                paramV.put("note", note);
                paramV.put("price", hargaa);
                paramV.put("quantity", quantity);
                return paramV;
            }
        };
        queue.add(stringRequest);
    }
}
