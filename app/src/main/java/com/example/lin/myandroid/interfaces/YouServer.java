package com.example.lin.myandroid.interfaces;

/**
 * Created by Lin on 2017/6/21.
 */

public class YouServer implements Runnable {

    public void setAnInterface(String who,String question,ContactInterface anInterface) {
        this.anInterface = anInterface;
        this.who = who;
    }

    private ContactInterface anInterface;
    private String who;

    public void handleThings(){
        //假如你现在正在想问题的答案，需要一点时间
        for(int i=0;i<5000;i++){
            if(i == 0){
                System.out.println("你正在思考问题.....");
            }
        }
        String answer = "答案是A";
        //想到问题的办法了
        System.out.println("你说：想到答案了，准备打回去给"+who+"告诉他答案");
        anInterface.callBackByTel(answer);
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
            handleThings();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
