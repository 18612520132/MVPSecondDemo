package comm.example.jni.mvpseconddemo.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import comm.example.jni.mvpseconddemo.R;
import comm.example.jni.mvpseconddemo.service.enity.book;
import comm.example.jni.mvpseconddemo.service.presenter.IBookPresenter;
import comm.example.jni.mvpseconddemo.service.view.FoodView;
import comm.example.jni.mvpseconddemo.ui.adaper.MyAdpaer;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRv;
    private IBookPresenter presenter;
    private List<book.DataBean> list = new ArrayList<>();
    private MyAdpaer adpaer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }


    /**
     * 网络接口
     * http://www.qubaobei.com/ios/cf/dish_list.php?stage_id=1&limit=20&page=1
     */
    private void init() {
        mRv = findViewById(R.id.mRv);

        presenter = new IBookPresenter(this,"http://www.qubaobei.com/ios/cf/");

        presenter.create();
        presenter.attachView(foodView);

        presenter.getSearchFood("dish_list.php",
                "1",
                "20",
                "1");

        LinearLayoutManager manager = new LinearLayoutManager(MainActivity.this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRv.setLayoutManager(manager);
        mRv.addItemDecoration(new DividerItemDecoration(MainActivity.this,DividerItemDecoration.VERTICAL));
        adpaer = new MyAdpaer(MainActivity.this);
        mRv.setAdapter(adpaer);

    }

    private FoodView foodView = new FoodView() {
        @Override
        public void success(book book) {
            list = book.getData();
            Log.e("fff",list.get(0).getTitle());
            adpaer.refresh(list);
        }

        @Override
        public void error(String error) {

        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.stop();
    }
}
