package cn.learn.android.myapplication.coroutine;

import androidx.annotation.NonNull;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;

/**
 * @author ch
 * @description
 * @date 2021/10/20
 * @edit
 */
public class Test {

    public static void main(String[] args) {
        Object r = GuaqiKt.fun2("1", "2", new Continuation<Integer>() {
            @NonNull
            @Override
            public CoroutineContext getContext() {
                return EmptyCoroutineContext.INSTANCE;
            }

            @Override
            public void resumeWith(@NonNull Object o) {
                System.out.println("resume result:" + o);
            }
        });
        System.out.println("object:" + r);
    }




}
