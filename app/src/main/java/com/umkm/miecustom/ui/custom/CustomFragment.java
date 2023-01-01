package com.umkm.miecustom.ui.custom;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.umkm.miecustom.R;
import com.umkm.miecustom.adapter.CustomMieListAdapter;
import com.umkm.miecustom.adapter.CustomToppingListAdapter;
import com.umkm.miecustom.databinding.FragmentCustomMieBinding;
import com.umkm.miecustom.model.CustomMieData;
import com.umkm.miecustom.model.CustomToppingData;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomFragment extends Fragment {

    private FragmentCustomMieBinding binding;
    private List<CustomMieData> CustomMieList;
    private List<CustomToppingData> customToppingDataList;
    private CustomMieListAdapter adapter;
    private CustomToppingListAdapter adaptertop;
    private CustomToppingListAdapter ap;
    private CustomViewModel viewModel;
    private TextView cobaan;
    String mie;
    Integer hargamiee;
    ProgressDialog progressDoalog;
    String[] toppidata;
    List<String> list3 = new ArrayList<>();
    List<String> list4 = new ArrayList<>();
    SharedPreferences sharedPreferences;


    ImageView kuranglevel,kurang,tambahlevel,tambah;
    TextView jumlahlevel,jumlah,simpanjumlah,errortxt;

    int count=0;
    int countlevel= 0;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        progressDoalog = new ProgressDialog(getContext());
        progressDoalog.setMessage("Loading....");
        progressDoalog.show();

        View view= inflater.inflate(R.layout.fragment_custom_mie,container,false);

        errortxt = view.findViewById(R.id.errortxt);
        RecyclerView recyclerView = view.findViewById(R.id.customRecyclerVieww);
        RecyclerView recyclerViewtop = view.findViewById(R.id.customRecyclerViewtoping);
        final TextView noresult = view.findViewById(R.id.noResultTv);
        kurang = view.findViewById(R.id.btnkurang);
        tambah = view.findViewById(R.id.btntambah);
        jumlah= view.findViewById(R.id.txtjumlahproduk);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,true);
        recyclerView.setLayoutManager(layoutManager);
        LinearLayoutManager layoutManagertop = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,true);
        layoutManagertop.setAutoMeasureEnabled(true);
        recyclerViewtop.setLayoutManager(layoutManagertop);
        adapter = new CustomMieListAdapter(getContext(), CustomMieList, new CustomMieListAdapter.ItemClickListener() {
           @Override
           public void onClick(CustomMieData result) {
               String namamie = result.getTitle();
               String harga = "Harga : ";
               mie = result.getTitle();
               String hrgg = result.getPrice();
               try {
                   hargamiee = NumberFormat.getInstance().parse(hrgg).intValue();
               } catch (ParseException e) {
                   e.printStackTrace();
               }


           }
       });
        recyclerView.setAdapter(adapter);

        adaptertop = new CustomToppingListAdapter(getContext(), customToppingDataList, new CustomToppingListAdapter.ItemClickListener() {
            @Override
            public void onClick(CustomToppingData result, List<String> list1, List<String> samahargalis) {
                String toppingg = "Topping : ";
                String namaTopping = result.getTitle();
                String hargga = "Harga : ";

                toppidata = new String[] {result.getTitle()+result.getPrice()};
                list3 = list1;
                list4 = samahargalis;


            }
        });
        recyclerViewtop.setAdapter(adaptertop);

        viewModel = new ViewModelProvider(this).get(CustomViewModel.class);

        viewModel.getDataListObserver().observe(getViewLifecycleOwner(), new Observer<List<CustomMieData>>() {
            @Override
            public void onChanged(List<CustomMieData> dataList) {
                if(dataList != null) {
                    CustomMieList = dataList;
                    adapter.setCustomMieList(dataList);
                    noresult.setVisibility(View.GONE);
                    progressDoalog.dismiss();
                } else {
                    noresult.setVisibility(View.VISIBLE);
                }
            }
        });
        viewModel.getTopListObserver().observe(getViewLifecycleOwner(), new Observer<List<CustomToppingData>>() {
            @Override
            public void onChanged(List<CustomToppingData> customToppingData) {
                customToppingDataList = customToppingData;
                adaptertop.setCustomToppingList(customToppingData);
                noresult.setVisibility(View.GONE);
                progressDoalog.dismiss();
            }
        });
        viewModel.mieCall();
        viewModel.toppingCall();

        kuranglevel = view.findViewById(R.id.btnkuranglevel);
        tambahlevel = view.findViewById(R.id.btntambahlevel);
        jumlahlevel= view.findViewById(R.id.levelpedas);
        simpanjumlah = view.findViewById(R.id.btntambahproduk);
        cobaan = view.findViewById(R.id.notetxt);

        String tmbhkecart= "Tambah ke Keranjang";
        String kemenu="Pilih Terlebih Dahulu";

        simpanjumlah.setClickable(false);
        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count ++;
                jumlah.setText(""+count);
                simpanjumlah.setText(tmbhkecart);
                simpanjumlah.setOnClickListener(new View.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onClick(View v) {
                        StringBuilder sb = new StringBuilder();
                        StringBuilder sbb = new StringBuilder();
                        int sum = 0;
                        for (String har : list4){
                            sbb.append(har);
                            sbb.append(" ");
                        }
                        String hara = sbb.toString();
                        String[] harahara = hara.split(" ");
                        ArrayList<Integer> hahaha = new ArrayList<Integer>();
                        for (String s : harahara) {
                            try {
                                hahaha.add(NumberFormat.getInstance().parse(s).intValue());
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                        }
                        for (int num : hahaha){
                            sum+=num;
                        }
                        for (String datad :list3){
                            sb.append(" ");
                            sb.append(datad);
                            sb.append(" ");

                        }
                        int totaltoping = sum;
                        int totalkeseluruhan = totaltoping+hargamiee;
                        String price = String.valueOf(totalkeseluruhan);
                        String lvlp = (String) jumlahlevel.getText();
                        String data2 = sb.toString()+"="+String.valueOf(totaltoping);
                        String levelpedas = "Level : "+lvlp;
                        String data1 = mie+"="+hargamiee;
                        String description = data1+","+data2+","+levelpedas;
                        String note =  String.valueOf(cobaan.getText());
                        String quantity = (String) jumlah.getText();
                        String id_user;
                        sharedPreferences = getActivity().getSharedPreferences("miecustom", Context.MODE_PRIVATE);
                        id_user = sharedPreferences.getString("id_user","");

                        RequestQueue queue = Volley.newRequestQueue(getContext().getApplicationContext());
                        String url ="https://miesuhh.000webhostapp.com/mobileapps/Custom/addCustom.php";

                        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        try {
                                            JSONObject jsonObject = new JSONObject(response);
                                            String status = jsonObject.getString("status");
                                            String message = jsonObject.getString("message");
                                        if (status.equals("success")){
                                            Toast.makeText(getContext().getApplicationContext(),"Berhasil menambahkan ke keranjang", Toast.LENGTH_LONG).show();

                                        } else {
                                            errortxt.setText(response);
                                            Toast.makeText(getContext().getApplicationContext(),response, Toast.LENGTH_LONG).show();
                                        }
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(getContext().getApplicationContext(),error.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                            }
                        }){
                            protected Map<String, String> getParams(){
                                Map<String, String> paramV = new HashMap<>();
                                paramV.put("id_user", id_user);
                                paramV.put("description", description);
                                paramV.put("price", price);
                                paramV.put("note", note);
                                paramV.put("quantity", quantity);
                                return paramV;
                            }
                        };
                        queue.add(stringRequest);
                    }
                });
            }
        });

        kurang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count<0){
                    count = 0;
                    jumlah.setText(count);
                    simpanjumlah.setText(kemenu);
                    simpanjumlah.setClickable(false);

                }else if (count==0){
                    simpanjumlah.setText(kemenu);
                    simpanjumlah.setClickable(false);

                }else if (count==1){
                    count--;
                    jumlah.setText(""+count);
                    simpanjumlah.setText(kemenu);
                    simpanjumlah.setClickable(false);

                }else {
                    count--;
                    jumlah.setText(""+count);
                        simpanjumlah.setText(tmbhkecart);
                        simpanjumlah.setOnClickListener(new View.OnClickListener() {
                            @RequiresApi(api = Build.VERSION_CODES.N)
                            @Override
                            public void onClick(View v) {
                                StringBuilder sb = new StringBuilder();
                                StringBuilder sbb = new StringBuilder();
                                int sum = 0;
                                for (String har : list4){
                                    sbb.append(har);
                                    sbb.append(" ");
                                }
                                String hara = sbb.toString();
                                String[] harahara = hara.split(" ");
                                ArrayList<Integer> hahaha = new ArrayList<Integer>();
                                for (String s : harahara) {
                                    try {
                                        hahaha.add(NumberFormat.getInstance().parse(s).intValue());
                                    } catch (ParseException e) {
                                        e.printStackTrace();
                                    }
                                }
                                for (int num : hahaha){
                                    sum+=num;
                                }
                                for (String datad :list3){
                                    sb.append(" ");
                                    sb.append(datad);
                                    sb.append(" ");

                                }
                                int totaltoping = sum;
                                int totalkeseluruhan = totaltoping+hargamiee;
                                String price = String.valueOf(totalkeseluruhan);
                                String lvlp = (String) jumlahlevel.getText();
                                String data2 = sb.toString()+"="+String.valueOf(totaltoping);
                                String levelpedas = "Level : "+lvlp;
                                String data1 = mie+"="+hargamiee;
                                String description = data1+","+data2+","+levelpedas;
                                String note =  String.valueOf(cobaan.getText());
                                String quantity = (String) jumlah.getText();
                                String id_user;
                                sharedPreferences = getActivity().getSharedPreferences("miecustom", Context.MODE_PRIVATE);
                                id_user = sharedPreferences.getString("id_user","");

                                RequestQueue queue = Volley.newRequestQueue(getContext().getApplicationContext());
                                String url ="https://miesuhh.000webhostapp.com/mobileapps/Custom/addCustom.php";

                                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                                        new Response.Listener<String>() {
                                            @Override
                                            public void onResponse(String response) {
                                                try {
                                                    JSONObject jsonObject = new JSONObject(response);
                                                    String status = jsonObject.getString("status");
                                                    String message = jsonObject.getString("message");
                                                if (status.equals("success")){
                                                    Toast.makeText(getContext().getApplicationContext(),"Berhasil menambahkan ke keranjang", Toast.LENGTH_LONG).show();

                                                } else {
                                                    Toast.makeText(getContext().getApplicationContext(),response, Toast.LENGTH_LONG).show();

                                                }
                                                } catch (JSONException e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        Toast.makeText(getContext().getApplicationContext(),error.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                                    }
                                }){
                                    protected Map<String, String> getParams(){
                                        Map<String, String> paramV = new HashMap<>();
                                        paramV.put("id_user", id_user);
                                        paramV.put("description", description);
                                        paramV.put("price", price);
                                        paramV.put("note", note);
                                        paramV.put("quantity", quantity);
                                        return paramV;
                                    }
                                };
                                queue.add(stringRequest);
                            }
                        });
                    }
                }

        });

        tambahlevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countlevel ++;
                jumlahlevel.setText(""+countlevel);
            }
        });

        kuranglevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (countlevel<0){
                    countlevel = 0;
                    jumlahlevel.setText(countlevel);
                }else if (countlevel==0){
                    countlevel = 0;
                    jumlahlevel.setText(""+countlevel);
                }else if (countlevel==1){
                    countlevel--;
                    jumlahlevel.setText(""+countlevel);
                } else {
                    countlevel--;
                    jumlahlevel.setText(""+countlevel);
                }
            }
        });

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}