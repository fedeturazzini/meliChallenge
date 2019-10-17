package com.challengemeli.view.activities;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.challengemeli.R;
import com.challengemeli.model.pojo.Product;
import com.challengemeli.view.utils.ImageHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductDetailActivity extends AppCompatActivity {

    // UI components
    @BindView(R.id.imageViewProduct)
    ImageView imgProduct;

    @BindView(R.id.productCondition)
    TextView productCondition;

    @BindView(R.id.productTitle)
    TextView productTitle;

    @BindView(R.id.productPrice)
    TextView productPrice;

    @BindView(R.id.productMercadoPago)
    TextView productMercadoPago;

    @BindView(R.id.productAdress)
    TextView productAdress;

    @BindView(R.id.productShipping)
    TextView productShipping;

    @BindView(R.id.buttonComprar)
    TextView buttonComprar;

    @BindView(R.id.buttonAgregarAlCarrito)
    TextView buttonAgregarAlCarrito;

    // Variables
    public static final String PRODUCT = "product_object";

    private Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        ButterKnife.bind(this);
        getProductExtras();
        setUpToolbar();
        setUpProductView();
    }

    private void setUpProductView () {
        ImageHelper.loadImage(imgProduct, product.getThumbnail(), ImageHelper.getProgressDrawable(imgProduct.getContext()));
        if (product.getCondition() != null) {
            productCondition.setText(product.getCondition().equalsIgnoreCase("new") ? "Nuevo" : "Usado");
        }
        productTitle.setText(product.getTitle());
        productPrice.setText(product.getPrice());
        productMercadoPago.setText(product.isAcceptingMercadoPago() ? "Acepta Mercado Pago" : "");
        productAdress.setText(product.getAddress().getCityName() + " - " + product.getAddress().getStateName());
        productShipping.setText(product.getShipping().isFreeShipping() ? "Envio gratis" : "Coordina el envio con el vendedor");

        buttonComprar.setOnClickListener(view -> Toast.makeText(this, "Compraste correctamente", Toast.LENGTH_SHORT).show());

        buttonAgregarAlCarrito.setOnClickListener(view -> Toast.makeText(this, "Agregado al carrito correctamente", Toast.LENGTH_SHORT).show());

    }

    private void getProductExtras () {
        Bundle extras = getIntent().getExtras();
        product = (Product) extras.getSerializable(PRODUCT);
    }

    private void setUpToolbar ()  {
        // Back button
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(v -> finish());
    }

}
