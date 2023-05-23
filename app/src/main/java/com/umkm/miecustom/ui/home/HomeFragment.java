package com.umkm.miecustom.ui.home;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.IndicatorView.draw.controller.DrawController;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;
import com.umkm.miecustom.DetailProduct;
import com.umkm.miecustom.R;
import com.umkm.miecustom.adapter.CategorytAdapter;
import com.umkm.miecustom.adapter.ProductListAdapter;
import com.umkm.miecustom.adapter.SliderAdapter;
import com.umkm.miecustom.databinding.FragmentHomeBinding;
import com.umkm.miecustom.model.ProductData;
import com.umkm.miecustom.model.SliderItem;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    private List<ProductData> ProductList;
    private ProductListAdapter adapter;
    private CategorytAdapter catedapter;
    ProgressDialog progressDoalog;
    SliderView sliderView;
    private SliderAdapter adapterslide;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        progressDoalog = new ProgressDialog(getContext());
        progressDoalog.setMessage("Loading....");
        progressDoalog.show();

        View view= inflater.inflate(R.layout.fragment_home,container,false);




        RecyclerView catee = view.findViewById(R.id.btncategory);
        LinearLayoutManager layoutManagercate = new LinearLayoutManager(getContext());
        layoutManagercate.setOrientation(RecyclerView.HORIZONTAL);
        catee.setLayoutManager(layoutManagercate);
        ArrayList<String> catest = new ArrayList<>();
        for (int i=0;i<1;i++){
            catest.add("All");
            catest.add("Modar");
            catest.add("Cupu");
            catest.add("Bocil");
            catest.add("Snack");
            catest.add("Minuman");
        }
        HomeViewModel viewModel;
        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        catedapter = new CategorytAdapter(getContext(), catest, new CategorytAdapter.ItemClickListener() {
            @Override
            public void onClick(String list) {
                if (list.equals("All")){
                    tampildata(view,viewModel);
                    viewModel.getAll();
                }else if(list.equals("Modar")){
                    tampildata(view,viewModel);
                    viewModel.getModar();
                }else if(list.equals("Cupu")){
                    tampildata(view,viewModel);
                    viewModel.getCupu();
                }else if(list.equals("Bocil")){
                    tampildata(view,viewModel);
                    viewModel.getBocil();
                }else if(list.equals("Snack")){
                    tampildata(view,viewModel);
                    viewModel.getSnack();
                }else if(list.equals("Minuman")){
                    tampildata(view,viewModel);
                    viewModel.getMinuman();
                }else {
                    Toast.makeText(getContext(),"Error list",Toast.LENGTH_LONG).show();
                }
            }
        });
        catee.setAdapter(catedapter);

        slide(view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void tampildata(View view,HomeViewModel viewModel){
        RecyclerView recyclerView = view.findViewById(R.id.customRecyclerView);
        final TextView noresult = view.findViewById(R.id.noResultTv);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter =  new ProductListAdapter(getContext(), ProductList, new ProductListAdapter.ItemClickListener() {
            @Override
            public void onClick(ProductData result) {
                //Toast.makeText(getContext(),result.getTitle(),Toast.LENGTH_SHORT).show();
                String idd = String.valueOf(result.getId_product());
                Intent intent = new Intent(getContext(), DetailProduct.class);
                intent.putExtra("idprod",idd);
                intent.putExtra("titlep",result.getTitle());
                intent.putExtra("imageurl",result.getImageProduk());
                intent.putExtra("hargap",result.getPrice());
                intent.putExtra("desp",result.getShortdesc());
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapter);
        viewModel.getDataListObserver().observe(getViewLifecycleOwner(), new Observer<List<ProductData>>() {
            @Override
            public void onChanged(List<ProductData> dataList) {
                if(dataList != null) {
                    ProductList = dataList;
                    adapter.setProductList(dataList);
                    noresult.setVisibility(View.GONE);
                    progressDoalog.dismiss();
                } else {
                    noresult.setVisibility(View.VISIBLE);
                }
            }
        });
    }
    public void slide(View view){
        sliderView = view.findViewById(R.id.image_slider);


        adapterslide = new SliderAdapter(getContext());
        sliderView.setSliderAdapter(adapterslide);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.setScrollTimeInSec(3);
        sliderView.setAutoCycle(true);
        sliderView.startAutoCycle();


        sliderView.setOnIndicatorClickListener(new DrawController.ClickListener() {
            @Override
            public void onIndicatorClicked(int position) {
                Log.i("GGG", "onIndicatorClicked: " + sliderView.getCurrentPagePosition());
            }
        });
        List<SliderItem> sliderItemList = new ArrayList<>();
        //dummy data
        for (int i = 0; i < 4; i++) {
            SliderItem sliderItem = new SliderItem();
            if (i == 0) {
                sliderItem.setImageUrl("http://miesuhh.000webhostapp.com/admin/Res_img/dishes/639c2477777b1.png");
            }else if (i == 1) {
                sliderItem.setImageUrl("http://miesuhh.000webhostapp.com/admin/Res_img/dishes/6379a6fedacb3.png");
            }else if (i == 2) {
                sliderItem.setImageUrl("http://miesuhh.000webhostapp.com/admin/Res_img/dishes/606d752a209c3.jpg");
            }else if (i == 3) {
                sliderItem.setImageUrl("http://miesuhh.000webhostapp.com/admin/Res_img/dishes/6387197c2cb56.png");
            } else {
                sliderItem.setImageUrl("https://images.pexels.com/photos/747964/pexels-photo-747964.jpeg?auto=compress&cs=tinysrgb&h=750&w=1260");
            }
            sliderItemList.add(sliderItem);
        }
        adapterslide.slide(sliderItemList);
    }

}