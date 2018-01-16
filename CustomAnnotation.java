package com.example.pcpv.customanotation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        awesomeMethod();
    }

    @MethodInfo(author = "John Snow", revision = 2, comments = "Hey!")
    public void awesomeMethod() {
        Method method = null;
        try {
            method = getClass().getMethod("awesomeMethod");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        MethodInfo methodInfo = method.getAnnotation(MethodInfo.class);

        Log.d("MethodInfo", methodInfo.author());
        Log.d("MethodInfo", String.valueOf(methodInfo.revision()));
        Log.d("MethodInfo", methodInfo.comments());
    }

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @Inherited
    public @interface MethodInfo {
        String author() default "Igor Brishkoski";
        int revision() default 1;
        String comments();
    }




}
