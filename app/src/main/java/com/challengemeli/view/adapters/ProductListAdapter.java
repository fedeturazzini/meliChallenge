package com.challengemeli.view.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.challengemeli.R;
import com.challengemeli.model.pojo.Product;
import com.challengemeli.view.utils.ImageHelper;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ProductViewHolder>{

    private List<Product> productList;
    private OnProductListener onProductListener;

    public ProductListAdapter(List<Product> resultsProductList, OnProductListener onProductListener) {
         this.productList = resultsProductList;
         this.onProductListener = onProductListener;
    }

    public void updateProduct (List<Product> newProducts) {
        productList.clear();
        productList.addAll(newProducts);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_cell, parent, false);
        return new ProductViewHolder(view, onProductListener);
    }

    @Override
     public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.bind(product);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.img_product)
        ImageView imgProduct;

        @BindView(R.id.productName)
        TextView productName;

        @BindView(R.id.productPrice)
        TextView productPrice;

        @BindView(R.id.productCondition)
        TextView productCondition;

        @BindView(R.id.productMercadoPago)
        TextView productMercadoPago;

        OnProductListener onProductListener;

        public ProductViewHolder(@NonNull View itemView, OnProductListener onProductListener) {
            super(itemView);
            this.onProductListener = onProductListener;
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        void bind (Product product) {
            productName.setText(product.getTitle());
            productPrice.setText(product.getPrice());
            ImageHelper.loadImage(imgProduct, product.getThumbnail(), ImageHelper.getProgressDrawable(imgProduct.getContext()));
            if (product.getCondition() != null) {
                productCondition.setText(product.getCondition().equalsIgnoreCase("new") ? "Nuevo" : "Usado");
            }
            productMercadoPago.setText(product.isAcceptingMercadoPago() ? "Acepta Mercado Pago" : "");
        }

        @Override
        public void onClick(View view) {
            onProductListener.onProductClick(productList.get(getAdapterPosition()));
        }
    }

    public interface OnProductListener {
        void onProductClick (Product product);
    }

}

