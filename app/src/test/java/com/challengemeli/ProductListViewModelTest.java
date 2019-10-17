package com.challengemeli;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.challengemeli.model.pojo.Product;
import com.challengemeli.model.pojo.Results;
import com.challengemeli.model.services.ProductService;
import com.challengemeli.viewmodel.ProductListViewModel;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.internal.schedulers.ExecutorScheduler;
import io.reactivex.plugins.RxJavaPlugins;

public class ProductListViewModelTest {

    @Rule
    public InstantTaskExecutorRule rule = new InstantTaskExecutorRule();

    @Mock
    ProductService productService;

    @InjectMocks
    ProductListViewModel productListViewModelTest = new ProductListViewModel();

    private Single <Results> testSingleProduct;

    @Before
    public void setUp () {
        MockitoAnnotations.initMocks(this);
        setUpRxSchedulers();
    }

    @Test
    public void getProductsSucces () {
        // Mocked product
        Product productTest = new Product("1", "Test titulo", 12, "imagen", true);
        ArrayList <Product> productsListTest = new ArrayList<>();
        productsListTest.add(productTest);

        Results resultsTest = new Results(productsListTest);

        testSingleProduct = Single.just(resultsTest);

        Mockito.when(productService.getResultProduct("")).thenReturn(testSingleProduct);

        productListViewModelTest.refreshProducts("");

        Assert.assertEquals(1, productListViewModelTest.result.getValue().getProducts().size());
        Assert.assertEquals(false, productListViewModelTest.productLoadError.getValue());
        Assert.assertEquals(false, productListViewModelTest.loading.getValue());
        Assert.assertEquals(false, productListViewModelTest.searchError.getValue());
    }

    @Test
    public void getProductsFailure() {
        testSingleProduct = Single.error(new Throwable());

        Mockito.when(productService.getResultProduct("")).thenReturn(testSingleProduct);

        productListViewModelTest.refreshProducts("");

        Assert.assertEquals(true, productListViewModelTest.productLoadError.getValue());
        Assert.assertEquals(false, productListViewModelTest.loading.getValue());
        Assert.assertEquals(false, productListViewModelTest.searchError.getValue());
    }


    // Se ejecuta antes de todos los single test
    public void setUpRxSchedulers () {
        Scheduler immediate = new Scheduler() {
            @Override
            public Worker createWorker() {
                return new ExecutorScheduler.ExecutorWorker(runnable -> {
                    runnable.run();
                }, true);
            }
        };

        RxJavaPlugins.setInitIoSchedulerHandler(schedulerCallable -> immediate);
        RxJavaPlugins.setInitComputationSchedulerHandler (scheduler -> immediate);
        RxJavaPlugins.setInitNewThreadSchedulerHandler (scheduler -> immediate );
        RxJavaPlugins.setInitSingleSchedulerHandler (scheduler -> immediate);
        RxAndroidPlugins.setInitMainThreadSchedulerHandler (scheduler -> immediate);
    }
}
