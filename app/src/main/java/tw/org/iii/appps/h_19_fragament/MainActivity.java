package tw.org.iii.appps.h_19_fragament;
//Fragemnt是切換的動作
//目的按下去四個按鈕,各自交易叫出Frgament
//beginTranssaction:當你要把Frgament丟出去,他是一種交易的動作,一定是有人近來有人出去
//Transztion.commit:你要交易最後要commit才會執行
//main是處理掌管Fragemnt切換,但各自的Frgament邏輯寫在各自的Fragemnt
//邏輯單獨處理維護性
//先把原生的搞懂,之後學新的方法更快
//1.Fragnement經理人叫出來
//2.F1要怎麼叫近來的,所以要物件實體化出來
//3.開始進行交易一開始就把F1頁面交易近來
//4.到F2頁面設定xml
//5.進行切換行為:repalce把原有的葉面轉換到別的Frgament
//6.將四個按鈕按下分別交易到各自的Frgament
//7.回到F1

//getSupportFragmentManager()://取得Fragemnt物件經理人(回傳到FragmentManager)
//FragmentManager.beginTransaction()://開始進行Fragment交易一進一出(回傳到FragmentTransaction)
//FragmentTransaction.add( int containerViewId,Fragment fragment)://新增要選擇加入的Fragment(1.要加入的容器id位置,2要加入的Fragment)
//FragmentTransaction.commit()://執行Fragment的交易
//FragmentTransaction.replace(int containerViewId,Fragment fragment)://把原本有的Frgament配置轉換成別的(1.容器int位置,2.要轉換到哪個Frgament)(回傳FragmentTransaction)
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private FragmentManager fragmentManager;
    private F1 f1;
    private F2 f2;
    private F3 f3;
    private F4 f4;
    private TextView maintitle;  //這個title是在mainActivity的原件,設定封裝title所以如果F1要玩要給他方法玩

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        f1 = new F1();
        f2 = new F2();
        f3 = new F3();
        f4 = new F4();

        maintitle = findViewById(R.id.txt_main_main);

        //1.一開始就把F1頁面交易近來
        fragmentManager = getSupportFragmentManager();//取得Fragemnt物件經理人(回傳到FragmentManager)
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction(); //開始進行Fragment交易一進一出(回傳到FragmentTransaction)
        fragmentTransaction.add(R.id.contanier,f1);////新增要選擇加入的Fragment(1.要加入的容器id位置,2要加入的Fragment)
        fragmentTransaction.commit();//執行Fragment的交易
    }

    //4.自訂title方法給,F1玩可以改我這邊的標題 (因為title被我封裝,所以只給她方法玩)
    public void setMainTitle (String title){
        maintitle.setText(title);
    }

    //5.讓f1可以取得F2的物件方法
    public F2 getF2(){
        return f2;
    }

    //1.按下按鈕交易Replace切換原本的f1到f1
    public void test1(View view) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.contanier,f1);
        fragmentTransaction.commit();
    }

    //2.按下按鈕Replaece交易F2
    public void test2(View view) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.contanier,f2);
        fragmentTransaction.commit();
    }
    //3.按下按鈕Replaece交易F3
    public void test3(View view) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.contanier,f3);
        fragmentTransaction.commit();
    }

    //4.按下按鈕Replaece交易F4
    public void test4(View view) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.contanier,f4);
        fragmentTransaction.commit();
    }
}
