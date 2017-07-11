package com.example.lin.myandroid.interfaces;

import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.lin.myandroid.R;

/**
 * Created by Lin on 2017/6/21.
 */

public class MeClientActivity extends AppCompatActivity {

    private Button bt_interface;
    YouServer youServer = new YouServer();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me_client);
        bt_interface = (Button) findViewById(R.id.bt_interface);

        bt_interface.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //现在有问题想不出来答案，想去问你
                youServer.setAnInterface("蜗牛", "某道题答案是什么？", new ContactInterface() {
                    @Override
                    public void callBackByTel(String answer) {
                        System.out.println("我说：嗯，好的，我收到答案了:"+answer+"，谢谢");
                    }
                });
                //你接到电话，起床开始思考问题
                new Thread(youServer).start();
            }
        });


    }

}
