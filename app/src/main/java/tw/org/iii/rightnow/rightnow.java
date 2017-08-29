package tw.org.iii.rightnow;


import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.Button;

import tw.org.iii.rightnow.FloatView.FloatWindowManager;

import static tw.org.iii.rightnow.R.layout.rightnow;
//////////


public class rightnow extends Activity {

    //    private int swtichCount = 0;
//
//    //登入按鈕
//    private View.OnClickListener login = new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            Intent intent = new Intent(rightnow.this,Live2dTop.class);
//            startActivity(intent);
//        }
//    };
//    //圓形功能例開關
//    private View.OnClickListener btnCircle_click = new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            switchCircle();
//        }
//    };
//    //圓形功能例開關
//    private void switchCircle() {
//        if(swtichCount==0){
//            FloatWindowManager.getInstance().applyOrShowFloatWindow(this);
//            swtichCount=1;
//        }else{
//            FloatWindowManager.getInstance().dismissWindow();
//            swtichCount=0;
//        }
//    }
//
//
//    //LocationListener四個方法
//    @Override
//    public void onLocationChanged(Location location) {
//
//    }
//
//    @Override
//    public void onStatusChanged(String provider, int status, Bundle extras) {
//
//    }
//
//    @Override
//    public void onProviderEnabled(String provider) {
//
//    }
//
//    @Override
//    public void onProviderDisabled(String provider) {
//
//    }
//
//
    @RequiresApi(api = Build.VERSION_CODES.M)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(rightnow);
//        InitialComponent();
        //開啓後過2000毫秒後執行Runnable()
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent().setClass(rightnow.this, Live2dTop.class));
                finish();
            }
        }, 2000);
    }
//
//    private void InitialComponent() {
//        btn_login = (Button) findViewById(R.id.btn_login);
//        btn_login.setOnClickListener(login);
//        btnCircle = (Button)findViewById(R.id.btnCircle);
//        btnCircle.setOnClickListener(btnCircle_click);
//    }
//
//    Button btn_login;
//    Button btnCircle;
}
