package comm.example.jni.mvpseconddemo.service.presenter;

import android.content.Context;

import comm.example.jni.mvpseconddemo.service.enity.book;
import comm.example.jni.mvpseconddemo.service.module.DataModule;
import comm.example.jni.mvpseconddemo.service.view.FoodView;
import comm.example.jni.mvpseconddemo.service.view.view;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class IBookPresenter implements BookPresenter {
    private Context context;
    private String baseUrl;
    private DataModule dataModule;
    private FoodView foodView;
    private book book;

    public IBookPresenter(Context context, String baseUrl) {
        this.context = context;
        this.baseUrl = baseUrl;
    }

    @Override
    public void getSearchFood(String path, String stage_id, String limit, String page) {
        dataModule.getSearchFood(path,stage_id,limit,page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<book>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(book book) {
                        IBookPresenter.this.book =book;
                    }

                    @Override
                    public void onError(Throwable e) {
                        foodView.error("请求失败");
                    }

                    @Override
                    public void onComplete() {
                        if (book != null) {
                            foodView.success(book);
                        }
                    }
                });
    }

    @Override
    public void create() {
        dataModule = new DataModule(context,baseUrl);
    }

    @Override
    public void stop() {

    }

    @Override
    public void attachView(view view) {
        foodView = (FoodView) view;
    }
}
