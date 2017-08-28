/**
 *  You can modify and use this source freely
 *  only for the development of application related Live2D.
 *
 *  (c) Live2D Inc. All rights reserved.
 */

package tw.org.iii.rightnow.Live2D.sample;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import tw.org.iii.rightnow.R;
import tw.org.iii.rightnow.Live2D.android.FileManager;
import tw.org.iii.rightnow.Live2D.android.SoundManager;

//此頁為Live2D初始值載入頁面, 已經拉class到外部修改, 此頁僅留保存用

/*
 * MainActivityはActivityを継承し、サンプル・アプリケーションのエントリポイント（メインのActivityクラス）となります。
 */
public class MainActivity extends Activity
{
    //  Live2Dの管理
    private LAppLive2DManager live2DMgr ;
    static private Activity instance;

    public MainActivity( )
    {
        instance=this;
        if(LAppDefine.DEBUG_LOG)
        {
            Log.d( "", "==============================================\n" ) ;
            Log.d( "", "   Live2D Sample  \n" ) ;
            Log.d( "", "==============================================\n" ) ;
        }

        SoundManager.init(this);
        live2DMgr = new LAppLive2DManager() ;
    }


    static public void exit()
    {
        SoundManager.release();
        instance.finish();
    }


    /*
     * Activityが作成されたときのイベント
     */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        // GUIを初期化
        setupGUI();
        FileManager.init(this.getApplicationContext());
    }


    /*
     * GUIの初期化
     * activity_main.xmlからViewを作成し、そこにLive2Dを配置する
     */
    void setupGUI()
    {
        setContentView(R.layout.live2dtop);

        //  Viewの初期化
        LAppView view = live2DMgr.createView(this) ;

        // activity_main.xmlにLive2DのViewをレイアウトする
        FrameLayout layout=(FrameLayout) findViewById(R.id.live2DLayout);
        layout.addView(view, 0, new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));


        // モデル切り替えボタン
        ImageButton iBtn = (ImageButton)findViewById(R.id.imageButton1);
        ClickListener listener = new ClickListener();
        iBtn.setOnClickListener(listener);
    }


    // ボタンを押した時のイベント
    class ClickListener implements OnClickListener{

        @Override
        public void onClick(View v) {
            Toast.makeText(getApplicationContext(), "Change Model", Toast.LENGTH_SHORT).show();
            live2DMgr.changeModel();//Live2D Event
        }
    }


    /*
     * Activityを再開したときのイベント。
     */
    @Override
    protected void onResume()
    {
        //live2DMgr.onResume() ;
        super.onResume();
    }


    /*
     * Activityを停止したときのイベント。
     */
    @Override
    protected void onPause()
    {
        live2DMgr.onPause() ;
        super.onPause();
    }
}
