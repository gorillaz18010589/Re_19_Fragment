package tw.org.iii.appps.h_19_fragament;
//設定一個時間任務,每一秒Counter +1,在建構時初始化就去執行
//1.目標完執行緒
//2.讓f1可以玩我f2的title
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;


/**
 * A simple {@link Fragment} subclass.
 */
public class F2 extends Fragment {
    private Timer timer;
    private View view;
    private TextView f2_tv,txt_f2_title;
    int iCounter = 0;
    private UIhadler uIhadler;


    //1.建構式就宣告實體化
    public F2 (){
        uIhadler = new UIhadler();
        timer = new Timer();
        timer.schedule(new MyTask(),0,1000);//控制執行序的間隔跟時間
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(view == null){
            view = inflater.inflate(R.layout.fragment_f2, container, false);
            f2_tv = view.findViewById(R.id.f2_tv);
            txt_f2_title = view.findViewById(R.id.txt_f2_title);
        }

        return view;
    }

    //4.讓f1可以完我的title方法
    public void setF2Title(String titile){
        if(txt_f2_title != null){ //如果有初始化不為空,才設定此方法,防止空指向
            txt_f2_title.setText(titile);
        }
    }



    //2.這是背景任務工人,一個計時器每一秒+1,呈現在畫面上
    private class MyTask extends TimerTask{
        @Override
        public void run() {
            if(f2_tv != null){
                iCounter ++;
                Log.v("hank","TimerTask");
                uIhadler.sendEmptyMessage(0);//這邊發出一個0的訊息
            }

        }
    }
    //3.hadler接受0的訊息,來呈現在畫面
    private class  UIhadler extends Handler{
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            f2_tv.setText("" + iCounter);

        }
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
