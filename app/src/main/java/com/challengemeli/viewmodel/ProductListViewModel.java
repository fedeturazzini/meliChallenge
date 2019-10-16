package com.challengemeli.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.challengemeli.model.services.ProductService;
import com.challengemeli.model.pojo.Product;
import com.challengemeli.model.pojo.Results;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class ProductListViewModel extends ViewModel {

    public MutableLiveData <Results> result = new MutableLiveData<>();
    public MutableLiveData <Boolean> productLoadError = new MutableLiveData<>();
    public MutableLiveData <Boolean> loading = new MutableLiveData<>();
    public MutableLiveData <Boolean> searchError = new MutableLiveData<>();

    private ProductService productService = ProductService.getInstanceProduct();

    private CompositeDisposable disposable = new CompositeDisposable();

    public void refreshProducts (String query) {
        fetchProducts(query);
    }

    private void fetchProducts (String query) {

        loading.setValue(true);
        disposable.add(
                productService.getResultProduct(query)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<Results>() {

                            @Override
                            public void onSuccess(Results results) {
                                result.setValue(results);
                                productLoadError.setValue(false);
                                loading.setValue(false);
                                if (results.getProducts().size() == 0) {
                                    searchError.setValue(true);
                                } else {
                                    searchError.setValue(false);
                                }
                            }

                            @Override
                            public void onError(Throwable e) {
                                productLoadError.setValue(true);
                                loading.setValue(false);
                                searchError.setValue(false);
                                e.printStackTrace();
                            }
                        })
        );
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }

}
