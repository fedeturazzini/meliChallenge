package com.challengemeli.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.challengemeli.R;
import com.challengemeli.model.pojo.Product;
import com.challengemeli.view.adapters.ProductListAdapter;
import com.challengemeli.viewmodel.ProductListViewModel;
import com.facebook.stetho.Stetho;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductSearchActivity extends AppCompatActivity implements ProductListAdapter.OnProductListener {

    // UI components
    @BindView(R.id.recyclerViewProduct)
    RecyclerView recyclerViewProduct;

    @BindView(R.id.searchViewProduct)
    SearchView searchView;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.errorSearchContainer)
    RelativeLayout searchError;

    @BindView(R.id.errorNetworkContainer)
    RelativeLayout errorNetworkContainer;

    @BindView(R.id.relativeSearchWelcomeContainer)
    RelativeLayout relativeSearchWelcomeContainer;

    // Variables
    private ProductListViewModel viewModelProduct;
    private ProductListAdapter adapterProduct = new ProductListAdapter(new ArrayList<>(), this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Stetho.initializeWithDefaults(this);

        viewModelProduct = ViewModelProviders.of(this).get(ProductListViewModel.class);
        setRecycler();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                viewModelProduct.refreshProducts(query);
                searchView.clearFocus();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        observer();
    }

    private void observer () {

        viewModelProduct.result.observe(this, results -> {
            if (results != null ) {
                relativeSearchWelcomeContainer.setVisibility(View.GONE);
                recyclerViewProduct.setVisibility(View.VISIBLE);
                adapterProduct.updateProduct(results.getProducts());
                searchError.setVisibility(View.GONE);
            }
        });

        viewModelProduct.productLoadError.observe(this, isError -> {
            if (isError != null) {
                errorNetworkContainer.setVisibility(isError ? View.VISIBLE : View.GONE);
            }
        });

        viewModelProduct.loading.observe(this, isLoading -> {
            if (isLoading != null) {
                progressBar.setVisibility(isLoading ? View.VISIBLE : View.GONE);
                if (isLoading) {
                    relativeSearchWelcomeContainer.setVisibility(View.GONE);
                    searchError.setVisibility(View.GONE);
                    errorNetworkContainer.setVisibility(View.GONE);
                    recyclerViewProduct.setVisibility(View.GONE);
                }
            }
        });

        viewModelProduct.searchError.observe(this, isSearchEror -> {
            if (isSearchEror != null) {
                searchError.setVisibility(isSearchEror? View.VISIBLE : View.GONE);
            }
        });
    }

    private void setRecycler () {
        recyclerViewProduct.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewProduct.setAdapter(adapterProduct);
    }

    @Override
    public void onProductClick(Product product) {
        Intent intentDetailActivity = new Intent(this, ProductDetailActivity.class);
        intentDetailActivity.putExtra(ProductDetailActivity.PRODUCT, product);
        startActivity(intentDetailActivity);
    }
}


