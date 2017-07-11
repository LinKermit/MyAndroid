package com.example.a02;

import android.media.Image;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewpager;
    private TextView tv_title;
    private LinearLayout ll_point_group;
    private ArrayList<ImageView> imageViews;
    //图片背景
    private final int[] imageIds= {R.drawable.a,R.drawable.b,
            R.drawable.c,R.drawable.d,R.drawable.e};
    //标题背景
    private final String[] imageDdecriptions ={"1发条魔灵","2伊泽瑞尔",
            "3喝喝酒","4凤凰花","5苦咖啡"};

    private int prePoint = 0;//上一个点的位置
    /**
     * 是否已经滚动
     */
    private boolean isDragging = false;

    //自动切换
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int item = viewpager.getCurrentItem()+1;
            viewpager.setCurrentItem(item);
            handler.sendEmptyMessageDelayed(0,4000);//每4秒发送一次空消息
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewpager = (ViewPager) findViewById(R.id.viewpager);
        tv_title = (TextView) findViewById(R.id.tv_title);
        ll_point_group = (LinearLayout) findViewById(R.id.ll_point);
        imageViews = new ArrayList<>();
        for (int i =0; i<imageIds.length;i++){
            ImageView imageView = new ImageView(this);
            imageView.setBackgroundResource(imageIds[i]);
            imageViews.add(imageView);

            //设置广告点
            ImageView point = new ImageView(this);
            point.setBackgroundResource(R.drawable.point_selector);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(8,8);
            if(i==0){
                point.setEnabled(true);
            }else {
                point.setEnabled(false);
                params.leftMargin = 8;
            }

            point.setLayoutParams(params);//设置间隔
            ll_point_group.addView(point);
        }
        viewpager.setAdapter(new MyPagerAdapter());
        viewpager.addOnPageChangeListener(new MyPagerChangeListener());
        //设置中间位置
        int item = Integer.MAX_VALUE/2 - Integer.MAX_VALUE/2%imageViews.size();//要保证imageViews的整数倍
        viewpager.setCurrentItem(item);

        tv_title.setText(imageDdecriptions[prePoint]);
        handler.sendEmptyMessageDelayed(0,3000);
    }

    /**
     * 添加viewpager的监听事件
     */
    class MyPagerChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            int realPosition = position%imageViews.size();
            tv_title.setText(imageDdecriptions[realPosition]);
            ll_point_group.getChildAt(prePoint).setEnabled(false);
            ll_point_group.getChildAt(realPosition).setEnabled(true);
            prePoint = realPosition;
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            if(state == ViewPager.SCROLL_STATE_DRAGGING){
                isDragging = true;
                handler.removeCallbacksAndMessages(null);

            }else if(state == ViewPager.SCROLL_STATE_SETTLING){

            }else if(state == ViewPager.SCROLL_STATE_IDLE&&isDragging){
                isDragging = false;
                handler.removeCallbacksAndMessages(null);
                handler.sendEmptyMessageDelayed(0,4000);
            }
        }
    }

    class MyPagerAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;//无线个数量，左右滑动
 //           return imageViews.size();
        }

        /**
         * 相当于getView方法
         * @param container ViewPager本身
         * @param position
         * @return
         */
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            int realPosition = position%imageViews.size();//求余获得5的倍数图片
            final ImageView imageView = imageViews.get(realPosition);
            imageView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()){
                        case MotionEvent.ACTION_DOWN:
                            handler.removeCallbacksAndMessages(null);
                            break;
                        case MotionEvent.ACTION_UP:
                            handler.removeCallbacksAndMessages(null);
                            handler.sendEmptyMessageDelayed(0,4000);
                            break;
                        case MotionEvent.ACTION_MOVE:
                            break;
                        case MotionEvent.ACTION_CANCEL:
//                            handler.removeCallbacksAndMessages(null);
//                            handler.sendEmptyMessageDelayed(0,4000);
                            break;
                    }
                    return true;
                }
            });

            //点击事件
            imageView.setTag(position);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = (int) v.getTag()%imageViews.size();
                    Toast.makeText(MainActivity.this,"当前页"+imageDdecriptions[position],Toast.LENGTH_LONG).show();
                }
            });
            container.addView(imageView);//添加到ViewPager中
            return imageView;
        }

        //比较我们VIew和Objiect是否同一个实例
        @Override
        public boolean isViewFromObject(View view, Object object) {//instantiateItem方法返回object
                return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
