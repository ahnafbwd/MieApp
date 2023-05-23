package com.umkm.miecustom;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

public class DetailProduct extends AppCompatActivity {
    private ImageView imageView;
    private TextView qtyprod;
    private TextView titleprod;
    private TextView descprod;
    private TextView hargaprod;
    private TextView noteuser;

    LinearLayout backk;

    String idprod;
    String imageUri;
    String titlep;
    String desp;
    String hargap;
    String iduser;
    String qty;

    int idp;
    int toidp;

    int totalpr;

    String id_user,id_product,title_product,image,description,note,price,quantity;
    String fulldata;
    ImageView kurang,tambah;
    TextView jumlah,simpanjumlah;

    int count=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product);

        kurang = findViewById(R.id.btnkurang);
        tambah = findViewById(R.id.btntambah);
        jumlah= findViewById(R.id.txtjumlahproduk);
        simpanjumlah = findViewById(R.id.btntambahproduk);
        SharedPreferences sharedPreferences = getSharedPreferences("miecustom", Context.MODE_PRIVATE);
        iduser = sharedPreferences.getString("id_user","");

        // Inisialisasi variabel
        imageView = findViewById(R.id.imageView);
        titleprod = findViewById(R.id.titleview);
        descprod = findViewById(R.id.descview);
        hargaprod = findViewById(R.id.hargaview);
        noteuser = findViewById(R.id.noteuser);
        qtyprod = findViewById(R.id.txtjumlahproduk);


// Ambil data gambar yang dipilih



// Tampilkan gambar yang dipilih di ImageView

        qty = String.valueOf(qtyprod.getText());

        backk = findViewById(R.id.btnback);
        backk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        String tmbhkecart= "Tambah ke Keranjang";
        String kemenu="Kembali ke Menu";

        getSupportActionBar().hide();

        //data to database
        id_user = String.valueOf(sharedPreferences.getString("id_user",""));
        id_product = String.valueOf(getIntent().getStringExtra("idprod"));
        title_product = String.valueOf(getIntent().getStringExtra("titlep"));
        image = String.valueOf("http://miesuhh.000webhostapp.com/admin/Res_img/dishes/"+getIntent().getStringExtra("imageurl"));
        description = getIntent().getStringExtra("desp");
        note = String.valueOf(noteuser.getText());
        hargap = String.valueOf(getIntent().getStringExtra("hargap"));
        try {
            price = String.valueOf(NumberFormat.getInstance().parse(hargap).intValue());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Glide.with(DetailProduct.this).load(image).into(imageView);
        titleprod.setText(title_product);
        descprod.setText(description);
        hargaprod.setText(hargap);
        simpanjumlah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count ++;
                jumlah.setText(""+count);
                simpanjumlah.setText(tmbhkecart);
                simpanjumlah.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        addtocart();
                    }
                }) ;
            }
        });

        kurang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count<0){
                    count = 0;
                    jumlah.setText(count);
                    simpanjumlah.setText(kemenu);

                }else if (count==0){
                    simpanjumlah.setText(kemenu);
                    simpanjumlah.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            onBackPressed();
                        }
                    });
                }else if (count==1){
                    count--;
                    jumlah.setText(""+count);
                    simpanjumlah.setText(kemenu);
                    simpanjumlah.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            onBackPressed();
                        }
                    });
                } else {
                    count--;
                    jumlah.setText(""+count);
                    simpanjumlah.setText(tmbhkecart);
                    simpanjumlah.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            addtocart();
                        }
                    });
                }
            }
        });


    }
    //Override onSupportNavigateUp agar tombol back kembali ke fragment sebelumnya
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }



    public void addtocart(){
        SharedPreferences sharedPreferences = getSharedPreferences("miecustom", Context.MODE_PRIVATE);
        String id_user2 = String.valueOf(sharedPreferences.getString("id_user",""));
        String id_product2 = String.valueOf(getIntent().getStringExtra("idprod"));
        String title_product2 = String.valueOf(getIntent().getStringExtra("titlep"));
        String image2 = String.valueOf(getIntent().getStringExtra("imageurl"));
        String description2 = getIntent().getStringExtra("desp");
        String note2 = String.valueOf(noteuser.getText());
        String hargap2 = String.valueOf(getIntent().getStringExtra("hargap"));
        try {
            String price2 = String.valueOf(NumberFormat.getInstance().parse(hargap2).intValue());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String quantity2 = jumlah.getText().toString();
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url ="https://miesuhh.000webhostapp.com/mobileapps/cart/addcart.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String status = jsonObject.getString("status");
                            String message = jsonObject.getString("message");
                            if (status.equals("success")){
                                Toast.makeText(getApplicationContext(),message, Toast.LENGTH_LONG).show();
                                onBackPressed();
                            } else {
                                Toast.makeText(getApplicationContext(),message, Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        }){
            protected Map<String, String> getParams(){
                Map<String, String> paramV = new HashMap<>();
                paramV.put("id_user", id_user2);
                paramV.put("id_product", id_product2);
                paramV.put("title_product", title_product2);
                paramV.put("image", image2);
                paramV.put("description", description2);
                paramV.put("note", note2);
                paramV.put("price", hargap2);
                paramV.put("quantity", quantity2);
                return paramV;
            }
        };
        queue.add(stringRequest);
    }
}