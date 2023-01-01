package com.umkm.miecustom.ui.cart;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
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
import com.umkm.miecustom.OrderConfirm;
import com.umkm.miecustom.R;
import com.umkm.miecustom.adapter.CartCustomListAdapter;
import com.umkm.miecustom.adapter.CartListAdapter;
import com.umkm.miecustom.databinding.FragmentCartBinding;
import com.umkm.miecustom.model.CartData;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartFragment extends Fragment {

    private FragmentCartBinding binding;
    private List<CartData> CartList;
    private CartListAdapter adapter;
    private CartCustomListAdapter adapter2;
    private CartViewModel viewModel;
    private List<CartData> CartList2;
    private CartViewModel viewModel2;
    ProgressDialog progressDoalog;
    String id_user;
    LinearLayout btnketransaksi;

    String total;
    SharedPreferences sharedPreferences;

    TextView textViewName,textViewEmail,textViewError;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        progressDoalog = new ProgressDialog(getContext());
        progressDoalog.setMessage("Loading....");
        progressDoalog.show();

        View view= inflater.inflate(R.layout.fragment_cart,container,false);

        btnketransaksi = view.findViewById(R.id.btnpesansekarang);

        getviewcart(view);
        getviewcartcustom(view);
        sharedPreferences = getActivity().getSharedPreferences("miecustom", Context.MODE_PRIVATE);
        id_user = sharedPreferences.getString("id_user","").toString();
        btnketransaksi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getTotalcart();
                // Fragment fragmentt = new OrderConfirmationFragment();
               // FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
               // transaction.replace(R.id.container, fragmentt).commit();
            }
        });
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void getviewcart(View view){

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("miecustom", Context.MODE_PRIVATE);
        id_user = sharedPreferences.getString("id_user","").toString();

        RecyclerView recyclerView = view.findViewById(R.id.customRecyclerVieww);
        final TextView noresult = view.findViewById(R.id.noResultTv);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,true);
        recyclerView.setLayoutManager(layoutManager);
        adapter =  new CartListAdapter(getContext(),CartList);
        recyclerView.setAdapter(adapter);

        viewModel = new ViewModelProvider(this).get(CartViewModel.class);
        viewModel.getDataListObserver().observe(getViewLifecycleOwner(), new Observer<List<CartData>>() {
            @Override
            public void onChanged(List<CartData> dataList) {
                if(dataList != null) {
                    CartList = dataList;
                    adapter.setCartList(dataList);
                    noresult.setVisibility(View.GONE);
                    progressDoalog.dismiss();
                } else {
                    noresult.setVisibility(View.VISIBLE);
                    progressDoalog.dismiss();
                }
            }
        });
        viewModel.getnotcustom(id_user,getContext());
    }
    public void getviewcartcustom(View view){

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("miecustom", Context.MODE_PRIVATE);
        id_user = sharedPreferences.getString("id_user","").toString();

        RecyclerView recyclerView2 = view.findViewById(R.id.customRecyclerView2);
        final TextView noresult2 = view.findViewById(R.id.noResultTv2);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,true);
        recyclerView2.setLayoutManager(layoutManager2);
        adapter2 =  new CartCustomListAdapter(getContext(),CartList2);
        recyclerView2.setAdapter(adapter2);

        viewModel2 = new ViewModelProvider(this).get(CartViewModel.class);
        viewModel2.getDataList2Observer().observe(getViewLifecycleOwner(), new Observer<List<CartData>>() {
            @Override
            public void onChanged(List<CartData> dataList2) {
                if(dataList2 != null) {
                    CartList2 = dataList2;
                    adapter2.setCartList(dataList2);
                    noresult2.setVisibility(View.GONE);
                    progressDoalog.dismiss();
                } else {
                    noresult2.setVisibility(View.VISIBLE);
                }
            }
        });
        viewModel2.getcustom(id_user,getContext());
    }
    public void getTotalcart(){
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("miecustom", Context.MODE_PRIVATE);
        id_user = sharedPreferences.getString("id_user","").toString();
        RequestQueue queue = Volley.newRequestQueue(getContext().getApplicationContext());
        String url ="https://miesuhh.000webhostapp.com/mobileapps/cart/total.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String status = jsonObject.getString("status");
                            String message = jsonObject.getString("message");
                            String ttl = jsonObject.getString("total");
                            if (status.equals("success")){
                                Intent intent = new Intent(getContext().getApplicationContext(), OrderConfirm.class);
                                intent.putExtra("totall",ttl);
                                startActivity(intent);
                            } else {
                                textViewError.setText(response);
                                textViewError.setVisibility(View.VISIBLE);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                textViewError.setText(error.getLocalizedMessage());
                textViewError.setVisibility(View.VISIBLE);
            }
        }){
            protected Map<String, String> getParams(){
                Map<String, String> paramV = new HashMap<>();
                paramV.put("id_user", id_user);
                return paramV;
            }
        };
        queue.add(stringRequest);
    }


}