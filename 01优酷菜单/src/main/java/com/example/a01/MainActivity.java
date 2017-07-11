package com.example.a01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView icon_home;
    private ImageView icon_menu;
    private RelativeLayout level1;
    private RelativeLayout level2;
    private RelativeLayout level3;
    private boolean isShowlevel3 = true;//是否显示第三圆环
    private boolean isShowlevel2 = true;//是否显示第二圆环
    private boolean isShowlevel1= true;//是否显示第1圆环
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        icon_home = (ImageView) findViewById(R.id.icon_home);
        icon_menu = (ImageView) findViewById(R.id.icon_menu);
        level1 = (RelativeLayout) findViewById(R.id.level1);
        level2 = (RelativeLayout) findViewById(R.id.level2);
        level3 = (RelativeLayout) findViewById(R.id.level3);
        icon_home.setOnClickListener(this);
        icon_menu.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.icon_home://home
                //如果2,3显示，都隐藏
                if(isShowlevel2){
                    isShowlevel2 = false;
                    Tools.hideView(level2);
                    if (isShowlevel3){
                        isShowlevel3 =false;
                        Tools.hideView(level3,200);
                    }
                }else{
                    //如果都隐藏，显示2级菜单
                    isShowlevel2 = true;
                    Tools.showView(level2);
                }
                break;
            case R.id.icon_menu://菜单
                if(isShowlevel3){
                    //隐藏
                   isShowlevel3 = false;
                    Tools.hideView(level3);
                }else {
                    isShowlevel3 = true;
                    Tools.showView(level3);
                }
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode ==KeyEvent.KEYCODE_MENU){
            //如果一级，二级，三级菜单是显示的就全部隐藏
            if(isShowlevel1){
                isShowlevel1 = false;
                Tools.hideView(level1);
                if(isShowlevel2){
                    //隐藏二级菜单
                    isShowlevel2 = false;
                    Tools.hideView(level2,200);
                    if(isShowlevel3){
                        //隐藏三级菜单
                        isShowlevel3 = false;
                        Tools.hideView(level3,400);
                    }
                }
            }else{
                //如果一级，二级菜单隐藏，就显示
                //显示一级菜单
                isShowlevel1 = true;
                Tools.showView(level1);

                //显示二级菜单
                isShowlevel2 = true;
                Tools.showView(level2,200);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
