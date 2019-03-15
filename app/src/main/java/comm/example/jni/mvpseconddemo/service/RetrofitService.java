package comm.example.jni.mvpseconddemo.service;

import comm.example.jni.mvpseconddemo.service.enity.book;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface  RetrofitService {
    /**
     * http://www.qubaobei.com/ios/cf/dish_list.php?stage_id=1&limit=20&page=1
     */
    @GET("{path}")
    Observable<book> getSearchFood(@Path("path")String path,
                                   @Query("stage_id")String stage_id,
                                   @Query("limit")String limit,
                                   @Query("page")String page);
}
