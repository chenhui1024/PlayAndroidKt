package cn.learn.android.myapplication.javacommukt;

import android.content.Intent;
import android.view.Display;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import cn.learn.android.myapplication.A;
import cn.learn.android.myapplication.IA;
import cn.learn.android.myapplication.Parent;
import cn.learn.android.myapplication.Parent.*;
import cn.learn.android.myapplication.TestExKt;
import cn.learn.android.myapplication.javacommukt.callback.KotlinCallBack;

/**
 * @author ch
 * @description
 * @date 2021/9/1
 * @edit
 */
public class UtilsTest {

    class Animal {}
    class Person extends Animal {}
    class Student extends Person{}

    class Wrapper<T extends Animal> {}

    static class Base<T> {
        public T name;

        public <T> void print(List<T> list) {
        }
    }
    class BaseImpl extends Base<String> {

    }

    public void newObj() {
        List<Person> students = new ArrayList<>();
        students.add(new Person());
        students.add(new Student());
        List<? super Student> list = students;
    }

    public void testList(List<? super TextView> list) {
        list.add(new TextView(null));
        list.add(new Button(null));
    }

    public static void modifyList(List<String> list) {
        ArrayList<Button> buttons = new ArrayList<>();
        List<? extends TextView> buttonList = buttons;

        for (int i = 0; i < list.size(); i++) {
            list.set(i, list.get(i).toUpperCase());
        }

        Parent parent = new Parent();
//        TestExKt.getName(parent);
    }

    public static int test(BufferedReader reader) throws IOException {
        String r = reader.readLine();
        return Integer.parseInt(r);
    }



    /**
     *   ????????????kotlin??????
     * @param args
     */
    public static void main(String[] args) {
        Base base = new Base<String>();


//        Base base = new Base<String>();
//        try {
//            Field field = base.getClass().getField("name");
//            System.out.println("field name:" + field.getGenericType());
//        } catch (NoSuchFieldException e) {
//            e.printStackTrace();
//        }


//        System.out.println("base:" + base.getClass().getFields().length);

        //java??????kotlin,??????????????????????????????????????????????????????????????????????????????kt???????????????
//        UtilsKt.sout("111");
//        Utils.Companion.internalSout();
//        new Utils().show();

        //??????kotlin????????????
//        UtilsKt.123();//??????
    }

    /**
     *  ??????java?????????kotlin??????
     */
    public static void in() {
        System.out.println("in invoke === > ");
    }

    /**
     *  ??????java?????????kotlin??????
     */
    public static void out() {
        System.out.println("out invoke === > ");
    }

    class AA implements ITest {

        @Override
        public void print(@Nullable String str) {

        }
    }

    class IntRef {
        int value;
    }

    public void tt() {
        final IntRef intRef = new IntRef();
        intRef.value = 0;
        setInterface(new ITmp() {
            @Override
            public void onTest() {
                intRef.value ++;
            }
        });
    }

    public void setInterface(ITmp interf) {
    }

    interface ITmp {
        void onTest();
    }

}
