package comm.example.jni.mvpseconddemo.service.module;

import android.content.Context;

import comm.example.jni.mvpseconddemo.service.RetrofitHelper;
import comm.example.jni.mvpseconddemo.service.RetrofitService;
import comm.example.jni.mvpseconddemo.service.enity.book;
import io.reactivex.Observable;

public class DataModule {
    private RetrofitService retrofitService;


    public DataModule (Context context,String baseUrl){
       this.retrofitService = RetrofitHelper.getInstance(context,baseUrl).getService();
    }

    public Observable<book> getSearchFood(String path,String stage_id,String limit,String page){
        return retrofitService.getSearchFood(path,stage_id,limit,page);
    }
}
