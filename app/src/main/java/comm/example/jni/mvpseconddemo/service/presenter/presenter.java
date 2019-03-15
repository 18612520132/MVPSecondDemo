package comm.example.jni.mvpseconddemo.service.presenter;

import comm.example.jni.mvpseconddemo.service.view.view;

public interface presenter {
    void create();

    void stop();

    void attachView(view view);
}
