package tw.org.iii.appps.h_19_fragament;
//先創一個Activity 一個text,四個按鈕,一個Framelayout是容器
//1.先看一下繼承androidx.fragment.app.Fragment
//2.一開始只需三個onCreateView, onAttach, onDetach()
//3.布局F1的xml
//4.回到Main去處理
//5.回來後生命週期了解一下 //當切到別的頁面時onDetach會被叫,當回到F1頁面時,OnAattch先被叫,再來是OnCreateView
//6.f1btn,產生樂透號碼後,到別的頁面回來又一次的onCreateView重新物件實體化所以號碼沒被保存
//7.這時因為View已經有Return東西回來 if(view == null)//如果Veiw == 空時近來,因為View已經有了所以她不會再進來重新創造樂透按鈕,因此質有留下來
//8.f1的三個按鈕都設定好後,想要玩MainActivity的東西,但不認識所以先宣告 MainActivity,而MainActivity從何而來
//8.1是從public void onAttach(Context context)的context而來,所以用 //取得MainActivity的Context,轉型成MainActivity這時候就認識了
//8.2目的是要認識main去改他的title


import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class F1 extends Fragment {
    private View view;
    private Button f1btn,f1btn2,f1btn3;
    private TextView f1tv;
    private MainActivity mainActivity;

    //1.Frgament一被創出來叫我進去,會有三個參數傳遞近來,每次從別的頁面回來又被叫一次
    @Override
    public View onCreateView(LayoutInflater inflater, //1.浮現inflater參數
                             ViewGroup container,
                             Bundle savedInstanceState) {
        if(view == null){//如果View為空近來,再回來f1發現view已經有了,所以不會再跑此段,適合只做一次的init放在這個判斷是裡,如果這段不寫每次回來都會重新初始化,質不會保留
            Log.v("hank","onCreateView");
            view = inflater.inflate(R.layout.fragment_f1, container, false);//從我肚子裡面去挖View

            f1btn = view.findViewById(R.id.f1btn);
            f1btn2 = view.findViewById(R.id.f1btn2);
            f1btn3 = view.findViewById(R.id.f1btn3);
            f1tv = view.findViewById(R.id.f1_tv);

            //1.f1_btn產生樂透
            f1btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    f1btnClick();
                }
            });

            //2.f1_btn2可以玩MainActivity的title
            f1btn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    f1btn2Click();
                }
            });

            //f1_btn2
            f1btn3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    f1btn3Click();
                }
            });
        }
        Log.v("hank","onCreateView");
        return view;
    }



    //1.f1_btn 產生樂透
    private void f1btnClick() {
        f1tv.setText("樂透號碼:" + (int)(Math.random() *38 +1));
    }

    //2.按鈕二 可以玩MainActivity的title
    private void f1btn2Click() {
        mainActivity.setMainTitle("這是我在F1裡面玩MainActivity的Title");
    }
    //按鈕三  玩f2的title
    private void f1btn3Click() {
        mainActivity.getF2().setF2Title("這是F1玩F2的title");
    }

    //2.掛上去(context是從MainActiovity而來,可以讓我認識MainActivity)(從別的頁面回來會再叫到onAttach再來是onCreateView)
    @Override
    public void onAttach(Context context) { //這個content傳來的參數就是MainActivity而來
        super.onAttach(context);

        //判斷現在到底是哪個Context,就是看看現在是哪個Activity
        if(context instanceof MainActivity){
            Log.v("hank","ok");
        }
        Log.v("hank","onAttach");
        mainActivity = (MainActivity) context; //取得MainActivity的Context,轉型成MainActivity這時候就認識了


    }
    //解除拆下來(當你跳到別的Fragment會被叫出來解除)
    @Override
    public void onDetach() {
        super.onDetach();
        Log.v("hank","onDetach");

    }


}
