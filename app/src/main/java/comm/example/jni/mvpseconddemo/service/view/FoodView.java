package comm.example.jni.mvpseconddemo.service.view;

import comm.example.jni.mvpseconddemo.service.enity.book;

public interface FoodView extends view{
    void success(book book);

    void error(String error);
}
