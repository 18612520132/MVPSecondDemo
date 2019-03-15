package comm.example.jni.mvpseconddemo.service;

import android.content.Context;

import okhttp3.OkHttpClient;
import okhttp3.internal.Internal;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static okhttp3.internal.Internal.instance;

public class RetrofitHelper {
    private OkHttpClient okHttpClient = new OkHttpClient();
    private GsonConverterFactory gsonConverterFactory = GsonConverterFactory.create();
    private RxJava2CallAdapterFactory rxJava2CallAdapterFactory = RxJava2CallAdapterFactory.create();


    private Context context;
    private String baseUrl;
    private Retrofit retrofit;
    private static RetrofitHelper instance;

    public RetrofitHelper(Context context, String baseUrl) {
        this.context = context;
        this.baseUrl = baseUrl;
        init();
    }

    private void init() {
        retrofit = new Retrofit.Builder()
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(rxJava2CallAdapterFactory)
                .client(okHttpClient)
                .baseUrl(baseUrl)
                .build();
    }

    public RetrofitService getService(){
        return retrofit.create(RetrofitService.class);
    }

    public static synchronized RetrofitHelper getInstance(Context context,String baseUrl){
        if (instance == null){
            instance = new RetrofitHelper(context, baseUrl);
        }
        return instance;
    }

}
