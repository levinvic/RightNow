package tw.org.iii.rightnow;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.transition.Visibility;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;
//TODO pickerView
//import com.bigkoo.pickerview.OptionsPickerView;
//


import com.wang.avi.AVLoadingIndicatorView;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import tw.org.iii.rightnow.FloatView.FloatWindowManager;
import tw.org.iii.rightnow.ITRITTSSpeaker.TTSCHI;
import tw.org.iii.rightnow.ITRITTSSpeaker.TTSTAW;
import tw.org.iii.rightnow.Live2D.android.FileManager;
import tw.org.iii.rightnow.Live2D.android.SoundManager;
import tw.org.iii.rightnow.Live2D.sample.LAppDefine;
import tw.org.iii.rightnow.Live2D.sample.LAppLive2DManager;
import tw.org.iii.rightnow.Live2D.sample.LAppView;

import static tw.org.iii.rightnow.R.layout.live2dtop;

/*
 * MainActivityはActivityを継承し、サンプル・アプリケーションのエントリポイント（メインのActivityクラス）となります。
 */
public class Live2dTop extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener,
        TTSCHI.AsyncResponseCHI, TTSTAW.AsyncResponseTAW {
    //TODO 全域變數位置
    public static LAppLive2DManager live2DMgr;//  Live2Dの管理
    static private Activity instance; //????啥鬼
    String KeyWord; //關鍵字
    String BotSpeakWord; //BOT語音
    ArrayList<String> ResList; //輸入進來的文字
    //GPS位置
    LocationManager mgr;
    double latitudestart;
    double longitudestart;
    //
    private DrawerLayout mDrawerLayout; //側邊選單欄
    private ActionBarDrawerToggle mToggle; //元件觸發
    //private Toolbar mToolboar;
    private ArrayList<String> options1Items = new ArrayList<>(); //PickerView
    //TODO pickerView
    //private OptionsPickerView pvOptions;
    static int ttsNumberset = 0; //語音選擇
    private int ViewStatus = 0; //顯示對話畫面狀態
    //重復對話用的
    String uri_chi; //小女孩音檔uri(網址)
    String uri_taw; //台語音檔uri(網址)


    //功能字庫
    private OnClickListener btnDictionary_click = new OnClickListener() {
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public void onClick(View v) {
            //對話框隱藏
            tvdialog.setVisibility(View.GONE);
            nameId.setVisibility(View.GONE);
            //啓動上側浮動選單
            projectpopupwindow = new ProjectPopupWindows(Live2dTop.this);
            projectpopupwindow.showAtLocation(Live2dTop.this.findViewById(R.id.drawerLayout), Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 0);
        }
    };

    //閉嘴
    private OnClickListener btnStopSpeak_click = new OnClickListener() {
        @Override
        public void onClick(View v) {
            //TTS(google)
            tts.stop();
            videoView.stopPlayback();
        }
    };


    //畫面畫面
    private OnClickListener btnSwitchSpeakView_click = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if (ViewStatus == 0) {
                nameId.setVisibility(View.GONE);
                tvdialog.setVisibility(View.GONE);
                ViewStatus = 1;
            } else if (ViewStatus == 1) {
                nameId.setVisibility(View.VISIBLE);
                tvdialog.setVisibility(View.VISIBLE);
                ViewStatus = 0;
            }
        }
    };


    //重複對話, 抓上一次回傳的音檔網址
    private OnClickListener btnRepeatSpeak_click = new OnClickListener() {
        @Override
        public void onClick(View v) {
//            startSpeakVoice(ttsNumberset, tvdialog.getText().toString());
            switch (ttsNumberset){
                case 0:
                    tts.speak(tvdialog.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);
                    break;
                case 1:
                    videoView.setVideoURI(Uri.parse(uri_chi));
                    videoView.requestFocus();
                    videoView.start();
                    break;
                case 2:
                    videoView.setVideoURI(Uri.parse(uri_taw));
                    videoView.requestFocus();
                    videoView.start();
                    break;
            }
        }
    };


    //畫面右上角時鐘按下去時
    private OnClickListener btnClock_click = new OnClickListener() {
        @Override
        public void onClick(View v) {
            Calendar cl = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日 HH:mm");
            String str = sdf.format(cl.getTime());

            //TTS(google)
            Log.i("shiaukai", "執行緒進入前");
            startSpeakVoice(ttsNumberset, "現在時間" + str);
            Log.i("shiaukai", "執行緒進入後");
        }
    };

    public Live2dTop() {
        instance = this;
        if (LAppDefine.DEBUG_LOG) {
            Log.d("", "==============================================\n");
            Log.d("", "   Live2D Sample  \n");
            Log.d("", "==============================================\n");
        }
        SoundManager.init(this);
        live2DMgr = new LAppLive2DManager();
    }

    // ボタンを押した時のイベント /Button按下去時的事件
    class ClickListener implements OnClickListener {
        @Override
        public void onClick(View v) {
            Toast.makeText(getApplicationContext(), "Change Model", Toast.LENGTH_SHORT).show();
            live2DMgr.changeModel();//Live2D Event
        }
    }

    //SpeechtoText接收server端回傳的字串
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                //顯示對話方塊
                nameId.setVisibility(View.VISIBLE);
                tvdialog.setVisibility(View.VISIBLE);

                //語音輸入存入ArrayList<String>
                ResList = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

                //BOT回應在對話框上的文字
                BotSpeakWord = BOTReturnWord.ResStr(ResList.get(0));
                tvdialog.setText(BotSpeakWord);


                //分析輸入文字, 輸出成關鍵字存在一個字串變數中, 一次性搜尋
                KeyWord = SearchWord.SEARCH(ResList.get(0));

                //TODO 判斷String關鍵字
                switch (KeyWord) {
                    //TODO 整合性功能
                    case KeyWordDictionary.相簿:
                        startSpeakVoice(ttsNumberset, tvdialog.getText().toString());
                        startCheckAPP(PackDictionary.Album);
                        break;
                    case KeyWordDictionary.Facebook:
                        startSpeakVoice(ttsNumberset, tvdialog.getText().toString());
                        startCheckAPP(PackDictionary.Fackbook);
                        break;
                    case KeyWordDictionary.Line:
                        startSpeakVoice(ttsNumberset, tvdialog.getText().toString());
                        startCheckAPP(PackDictionary.Line);
                        break;
                    case KeyWordDictionary.Chrome:
                        startSpeakVoice(ttsNumberset, tvdialog.getText().toString());
                        startCheckAPP(PackDictionary.Chrome);
                        break;
                    case KeyWordDictionary.天氣:
                        startSpeakVoice(ttsNumberset, tvdialog.getText().toString());
                        startCheckAPP(PackDictionary.Weather);
                        break;
                    case KeyWordDictionary.GoogleMap:
                        startSpeakVoice(ttsNumberset, tvdialog.getText().toString());
                        startCheckAPP(PackDictionary.GoogleMap);
                        break;
                    case KeyWordDictionary.繪圖:
                        startSpeakVoice(ttsNumberset, tvdialog.getText().toString());
                        startCheckAPP(PackDictionary.Draw);
                        break;
                    case KeyWordDictionary.Gmail:
                        startSpeakVoice(ttsNumberset, tvdialog.getText().toString());
                        startCheckAPP(PackDictionary.Gmail);
                        break;
                    case KeyWordDictionary.Skype:
                        startSpeakVoice(ttsNumberset, tvdialog.getText().toString());
                        startCheckAPP(PackDictionary.Skype);
                        break;
                    case KeyWordDictionary.行事曆:
                        startSpeakVoice(ttsNumberset, tvdialog.getText().toString());
                        startCheckAPP(PackDictionary.Calendar);
                        break;
                    case KeyWordDictionary.Youtube:
                        startSpeakVoice(ttsNumberset, tvdialog.getText().toString());
                        startCheckAPP(PackDictionary.Youtube);
                        break;
                    case KeyWordDictionary.Ptt:
                        startSpeakVoice(ttsNumberset, tvdialog.getText().toString());
                        startCheckAPP(PackDictionary.Ptt);
                        break;
                    case KeyWordDictionary.推持:
                        startSpeakVoice(ttsNumberset, tvdialog.getText().toString());
                        startCheckAPP(PackDictionary.Twitter);
                        break;
                    case KeyWordDictionary.Twitch:
                        startSpeakVoice(ttsNumberset, tvdialog.getText().toString());
                        startCheckAPP(PackDictionary.Twitch);
                        break;
                    case KeyWordDictionary.Messenger:
                        startSpeakVoice(ttsNumberset, tvdialog.getText().toString());
                        startCheckAPP(PackDictionary.Messenger);
                        break;
                    case KeyWordDictionary.卡提諾:
                        //開啓圓形功能列
                        FloatWindowManager.getInstance().applyOrShowFloatWindow(this);
                        startSpeakVoice(ttsNumberset, tvdialog.getText().toString());
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(PackDictionary.crazyck101)));
                        break;
                    case KeyWordDictionary.威力彩:
                        tvdialog.setText(new BOTActivityEvent().PowerLottery());
                        startSpeakVoice(ttsNumberset, tvdialog.getText().toString());
                        break;
                    case KeyWordDictionary.大樂透:
                        tvdialog.setText(new BOTActivityEvent().BigLottery());
                        startSpeakVoice(ttsNumberset, tvdialog.getText().toString());
                        break;
                    case KeyWordDictionary.大福彩:
                        tvdialog.setText(new BOTActivityEvent().BigLoveColor());
                        startSpeakVoice(ttsNumberset, tvdialog.getText().toString());
                        break;
                    case KeyWordDictionary.搜尋Youtube影片:
                        startSpeakVoice(ttsNumberset, tvdialog.getText().toString());
                        //開啓圓形功能列
                        FloatWindowManager.getInstance().applyOrShowFloatWindow(this);
                        startActivity(new Intent(Intent.ACTION_SEARCH).setPackage(PackDictionary.Youtube)
                                .putExtra(SearchManager.QUERY, new BOTActivityEvent().YoutubeSearch(ResList.get(0).toString())));
                        finish();
                        break;
                    case KeyWordDictionary.台視:
                        startSpeakVoice(ttsNumberset, tvdialog.getText().toString());
                        new BOTActivityEvent().startTV(Uri.parse("R.raw.ttv"));
                        //開啓圓形功能列
                        FloatWindowManager.getInstance().applyOrShowFloatWindow(this);
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(PackDictionary.TTV)));
                        finish();
                        break;
                    case KeyWordDictionary.華視:
                        startSpeakVoice(ttsNumberset, tvdialog.getText().toString());
                        new BOTActivityEvent().startTV(Uri.parse("R.raw.cts"));
                        //開啓圓形功能列
                        FloatWindowManager.getInstance().applyOrShowFloatWindow(this);
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(PackDictionary.CTS)));
                        finish();
                        break;
                    case KeyWordDictionary.中視:
                        startSpeakVoice(ttsNumberset, tvdialog.getText().toString());
                        new BOTActivityEvent().startTV(Uri.parse("R.raw.ctv"));
                        //開啓圓形功能列
                        FloatWindowManager.getInstance().applyOrShowFloatWindow(this);
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(PackDictionary.CTV)));
                        finish();
                        break;
                    case KeyWordDictionary.民視:
                        startSpeakVoice(ttsNumberset, tvdialog.getText().toString());
                        new BOTActivityEvent().startTV(Uri.parse("R.raw.ftv"));
                        //開啓圓形功能列
                        FloatWindowManager.getInstance().applyOrShowFloatWindow(this);
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(PackDictionary.FTV)));
                        finish();
                        break;
                    case KeyWordDictionary.東森:
                        startSpeakVoice(ttsNumberset, tvdialog.getText().toString());
                        new BOTActivityEvent().startTV(Uri.parse("R.raw.ebc"));
                        //開啓圓形功能列
                        FloatWindowManager.getInstance().applyOrShowFloatWindow(this);
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(PackDictionary.EBC)));
                        finish();
                        break;
                    case KeyWordDictionary.中天:
                        startSpeakVoice(ttsNumberset, tvdialog.getText().toString());
                        new BOTActivityEvent().startTV(Uri.parse("R.raw.cti"));
                        //開啓圓形功能列
                        FloatWindowManager.getInstance().applyOrShowFloatWindow(this);
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(PackDictionary.CTI)));
                        finish();
                        break;
                    case KeyWordDictionary.三立:
                        startSpeakVoice(ttsNumberset, tvdialog.getText().toString());
                        new BOTActivityEvent().startTV(Uri.parse("R.raw.set"));
                        //開啓圓形功能列
                        FloatWindowManager.getInstance().applyOrShowFloatWindow(this);
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(PackDictionary.SET)));
                        finish();
                        break;
                    case KeyWordDictionary.公視:
                        startSpeakVoice(ttsNumberset, tvdialog.getText().toString());
                        new BOTActivityEvent().startTV(Uri.parse("R.raw.pts"));
                        //開啓圓形功能列
                        FloatWindowManager.getInstance().applyOrShowFloatWindow(this);
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(PackDictionary.PTS)));
                        finish();
                        break;
                    case KeyWordDictionary.滾石老歌:
                        startSpeakVoice(ttsNumberset, tvdialog.getText().toString());
                        new BOTActivityEvent().startTV(Uri.parse("R.raw.old"));
                        //開啓圓形功能列
                        FloatWindowManager.getInstance().applyOrShowFloatWindow(this);
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(PackDictionary.OLD)));
                        finish();
                        break;
                    case KeyWordDictionary.打電話:
                        startSpeakVoice(ttsNumberset, tvdialog.getText().toString());
                        startCall();
                        break;
                    case KeyWordDictionary.開Chrome搜尋:
                        Intent it = new Intent(Intent.ACTION_SEARCH);
                        it.setAction(Intent.ACTION_WEB_SEARCH);
                        it.putExtra(SearchManager.QUERY, new BOTActivityEvent().ChromeSearch(ResList.get(0)));
                        startSpeakVoice(ttsNumberset, tvdialog.getText().toString());
                        //開啓圓形功能列
                        FloatWindowManager.getInstance().applyOrShowFloatWindow(this);
                        startActivity(it);
                        finish();
                        break;
                    case KeyWordDictionary.在維基百科找資料:
                        String wikiSearchWord = ResList.get(0).replace("什麼是", "").replace("是什麼", "");
                        tvdialog.setText("在維基百科上幫您搜尋" + wikiSearchWord + "相關資料\n\n\n");
                        startSpeakVoice(ttsNumberset, tvdialog.getText().toString());
                        Uri uri = Uri.parse(PackDictionary.Wiki + wikiSearchWord);
                        //開啓圓形功能列
                        FloatWindowManager.getInstance().applyOrShowFloatWindow(this);
                        startActivity(new Intent(Intent.ACTION_VIEW, uri));
                        finish();
                        break;
                    case KeyWordDictionary.故事:
                        new BOTActivityEvent().store();
                        startSpeakVoice(ttsNumberset, tvdialog.getText().toString());
                        break;
                    case KeyWordDictionary.本期發票:
                        tvdialog.setText(new BOTActivityEvent().Recipt());
                        startSpeakVoice(ttsNumberset, tvdialog.getText().toString());
                        break;
                    case KeyWordDictionary.至對獎發票網頁:
                        //開啓圓形功能列
                        FloatWindowManager.getInstance().applyOrShowFloatWindow(this);
                        startSpeakVoice(ttsNumberset, tvdialog.getText().toString());
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(PackDictionary.recipt)));
                        break;
                    case KeyWordDictionary.GoogleMapGuide: //地圖導航功能
                        startSpeakVoice(ttsNumberset, tvdialog.getText().toString());
                        //開啓圓形功能列
                        FloatWindowManager.getInstance().applyOrShowFloatWindow(this);
                        mapGo(new BOTActivityEvent().GoogleMapGuide(ResList.get(0)));
                        break;
                    case KeyWordDictionary.tip:
                        new BOTActivityEvent().tip();
                        startSpeakVoice(ttsNumberset, tvdialog.getText().toString());
                        break;
                    case KeyWordDictionary.國語:
                        ttsNumberset = 0;
                        startSpeakVoice(ttsNumberset, tvdialog.getText().toString());
                        nameId.setText("  壯年哈嚕  ");
                        break;
                    case KeyWordDictionary.小女孩:
                        ttsNumberset = 1;
                        startSpeakVoice(ttsNumberset, tvdialog.getText().toString());
                        nameId.setText("  幼年哈嚕  ");
                        break;
                    case KeyWordDictionary.台語:
                        ttsNumberset = 2;
                        startSpeakVoice(ttsNumberset, tvdialog.getText().toString());
                        nameId.setText("  老年哈嚕  ");
                        break;
                    case KeyWordDictionary.附近的:
                        startSpeakVoice(ttsNumberset, tvdialog.getText().toString());
                        placeSearch(new BOTActivityEvent().附近的(ResList.get(0)));
                        break;
                    case KeyWordDictionary.Operation:
                        startSpeakVoice(ttsNumberset, tvdialog.getText().toString());
                        break;
                    case KeyWordDictionary.Operation2:
                        startSpeakVoice(ttsNumberset, tvdialog.getText().toString());
                        break;
                    default:
                        //講畫面上的字
                        startSpeakVoice(ttsNumberset, tvdialog.getText().toString());
                        break;
                }

                /*雖然用if也可以, 不過程式由上跑到下面每次都會SEARCH一次迴圈,資料量大可能會變慢, 改用switch一次性判斷, 留著當參考而已
                if("Album".equals(SearchWord.SEARCH(BotSpeakWord))){
                    startActivity(new Intent(getPackageManager().getLaunchIntentForPackage(PackDictionary.Album)));
                }
                */
            }
        }
    }


    //TODO BOT講話要啓動的種類寫在這, google語音為0, 工研院小女孩語音為1,台語發音為2
    void startSpeakVoice(int ttsNumber, String speakWord) {
        TTSCHI ttschi = new TTSCHI(this);
        TTSTAW ttstaw = new TTSTAW(this);
        if (ttsNumber == 0) {
            //google語音TTS方法寫在這
            tts.speak(speakWord, TextToSpeech.QUEUE_FLUSH, null);
        }
        if (ttsNumber == 1) {
            //工研院的語音TTS方法寫在這(小女孩)
            try {
                ttschi.execute(speakWord).get(5000, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                Log.i("shiaukai", "終止例外");
            } catch (ExecutionException e) {
                Log.i("shiaukai", "執行例外");
            } catch (TimeoutException e) {
                Log.i("shiaukai", "逾時例外");
                ttschi.cancel(true);
                Toast.makeText(getApplicationContext(), "連線逾時，改由Google小姐回答", Toast.LENGTH_SHORT).show();
                tts.speak(speakWord, TextToSpeech.QUEUE_FLUSH, null);
            }
        }
        if (ttsNumber == 2) {
            //工研院的語音TTS方法寫在這(台語)
            try {
                ttstaw.execute(speakWord).get(10000, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                Log.i("shiaukai", "終止例外");
            } catch (ExecutionException e) {
                Log.i("shiaukai", "執行例外");
            } catch (TimeoutException e) {
                Log.i("shiaukai", "逾時例外");
                ttstaw.cancel(true);
                Toast.makeText(getApplicationContext(), "連線逾時，改由Google小姐回答", Toast.LENGTH_SHORT).show();
                tts.speak(speakWord, TextToSpeech.QUEUE_FLUSH, null);
            }
        }
    }


    //實作小女孩語音方法
    @Override
    public void processFinishCHI(String output) {
        uri_chi = output;

        videoView.setVideoURI(Uri.parse(output));
        videoView.requestFocus();
        videoView.start();
    }

    //實作台語語音方法
    @Override
    public void processFinishTAW(String output) {
        uri_taw = output;
        videoView.setVideoURI(Uri.parse(output));
        videoView.requestFocus();
        videoView.start();
    }

    //GPS定位導向內建GoogleMap導航
    @RequiresApi(api = Build.VERSION_CODES.M)
    void mapGo(String address) {
        Log.d("vic", address);
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                    checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 0);
                requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 0);
            } else {
                //取自己經緯度位置
                mgr.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 50, 0, mgrstart);
                Location location = mgr.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                double latitude;
                double longitude;
                if (location != null) {
                    latitude = location.getLatitude();
                    longitude = location.getLongitude();
                } else {
                    latitude = latitudestart;
                    longitude = longitudestart;
                }
                Log.d("vic", "自身經度:" + latitude + "自身緯度:" + longitude);
                //地址轉換成經緯度工具
                Geocoder gc = new Geocoder(Live2dTop.this, new Locale("zh", "TW"));
                try {
                    //取得目的地的經緯度
                    List<Address> addresslist = gc.getFromLocationName(address, 1);
                    double latitudeend = addresslist.get(0).getLatitude();
                    double longitudeend = addresslist.get(0).getLongitude();
                    Log.d("vic", "目標經度:" + latitude + "目標緯度:" + longitude);
                    //導航至目標
                    Intent it = new Intent(Intent.ACTION_VIEW, Uri.parse(
                            String.format("http://maps.google.com/maps?saddr=%f,%f&daddr=%f,%f",
                                    latitude, longitude, latitudeend, longitudeend)));
                    it.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
                    mgr.removeUpdates(mgrstart);

                    //開啓圓形功能列
                    FloatWindowManager.getInstance().applyOrShowFloatWindow(this);

                    startActivity(it);
                    finish();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            //取自己經緯度位置
            mgr.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 50, 0, mgrstart);
            Location location = mgr.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            double latitude;
            double longitude;
            if (location != null) {
                latitude = location.getLatitude();
                longitude = location.getLongitude();
            } else {
                latitude = latitudestart;
                longitude = longitudestart;
            }
//            double latitude = location.getLatitude();
//            double longitude = location.getLongitude();
            Log.d("vic", "自身經度:" + latitude + "自身緯度:" + longitude);
            //地址轉換成經緯度工具
            Geocoder gc = new Geocoder(Live2dTop.this, new Locale("zh", "TW"));
            try {
                //取得目的地的經緯度
                List<Address> addresslist = gc.getFromLocationName(address, 1);
                double latitudeend = addresslist.get(0).getLatitude();
                double longitudeend = addresslist.get(0).getLongitude();
                Log.d("vic", "目標經度:" + latitude + "目標緯度:" + longitude);
                //導航至目標
                Intent it = new Intent(Intent.ACTION_VIEW, Uri.parse(
                        String.format("http://maps.google.com/maps?saddr=%f,%f&daddr=%f,%f",
                                latitude, longitude, latitudeend, longitudeend)));
                it.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
                mgr.removeUpdates(mgrstart);
                //開啓圓形功能列
                FloatWindowManager.getInstance().applyOrShowFloatWindow(this);
                startActivity(it);
                finish();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    void placeSearch(String address) {
        Log.d("vic", address);
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                    checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 0);
                requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 0);
            } else {
                //取自己經緯度位置
                mgr.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 50, 0, mgrstart);
                Location location = mgr.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                double latitude = 0;
                double longitude = 0;
                if (location != null) {
                    latitude = location.getLatitude();
                    longitude = location.getLongitude();
                } else {
                    latitude = latitudestart;
                    longitude = longitudestart;
                }
                Log.d("vic", "自身經度:" + latitude + "自身緯度:" + longitude);
                //附近目標搜尋

                Intent it = new Intent(Intent.ACTION_VIEW, Uri.parse(
                        String.format("https://www.google.com.tw/maps/search/%s/@%f,%f,16z?hl=zh-TW",
                                address, latitude, longitude)));
                it.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
                mgr.removeUpdates(mgrstart);

                //開啓圓形功能列
                FloatWindowManager.getInstance().applyOrShowFloatWindow(this);

                startActivity(it);
                finish();

            }
        } else {
            //取自己經緯度位置
            mgr.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 50, 0, mgrstart);
            Location location = mgr.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            double latitude = 0;
            double longitude = 0;
            if (location != null) {
                latitude = location.getLatitude();
                longitude = location.getLongitude();
            } else {
                latitude = latitudestart;
                longitude = longitudestart;
            }
            Log.d("vic", "自身經度:" + latitude + "自身緯度:" + longitude);
            //附近目標搜尋

            Intent it = new Intent(Intent.ACTION_VIEW, Uri.parse(
                    String.format("https://www.google.com.tw/maps/search/%s/@%f,%f,16z?hl=zh-TW",
                            address, latitude, longitude)));
            it.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
            mgr.removeUpdates(mgrstart);

            //開啓圓形功能列
            FloatWindowManager.getInstance().applyOrShowFloatWindow(this);

            startActivity(it);
            finish();
        }
    }

    private LocationListener mgrstart = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            latitudestart = location.getLatitude();
            longitudestart = location.getLongitude();
            String strres = String.format("目前位置-->經度%f   緯度%f", latitudestart, longitudestart);
            Log.d("vic", strres);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };

    //TODO 開啓APP封包, 如果手機內不存在則在GoogleStare商店中尋找
    void startCheckAPP(String PackageName) {
        try {
            //開啓圓形功能列
            FloatWindowManager.getInstance().applyOrShowFloatWindow(this);

            startActivity(new Intent(getPackageManager().getLaunchIntentForPackage(PackageName)));
            finish();
        } catch (Exception e) {
            //開啓圓形功能列
            FloatWindowManager.getInstance().applyOrShowFloatWindow(this);

            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(PackDictionary.GoogleStore + PackageName)));
            finish();
        }
    }


    //TODO 撥號給聯絡人
    void startCall() {
        //API 23開始開放權限除了一般權限外還有危險權限
        avi.show();
        if (Build.VERSION.SDK_INT >= 23) {

            if (checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, 0);
            } else {
                int a = ResList.get(0).indexOf(KeyWord) + KeyWord.length();
                int b = ResList.get(0).length();
                String name = ResList.get(0).substring(a, b);

                String phone = getphonebook(name);
                if ("".equals(phone)) {
                    String strMsg = "找不到聯絡人[" + name + "]資料" + "\n\n\n";
                    tvdialog.setText(strMsg);
                    //TTS(google)
                    startSpeakVoice(ttsNumberset, strMsg);
                } else {
                    tvdialog.setText("請點選播號鍵即可撥電話給" + name + "\n\n\n");
                    //TTS(google)
                    startSpeakVoice(ttsNumberset, tvdialog.getText().toString());
                    Uri uri = Uri.parse("tel:" + phone);
                    Intent intent = new Intent(Intent.ACTION_DIAL, uri);

                    //開啓圓形功能列
                    FloatWindowManager.getInstance().applyOrShowFloatWindow(this);

                    startActivity(intent);
                    finish();
                }
            }
        } else {
            int a = ResList.get(0).indexOf(KeyWord) + KeyWord.length();
            int b = ResList.get(0).length();
            String name = ResList.get(0).substring(a, b);

            String phone = getphonebook(name);
            if ("".equals(phone)) {
                String strMsg = "找不到聯絡人[" + name + "]資料" + "\n\n\n";
                tvdialog.setText(strMsg);
                //TTS(google)
                startSpeakVoice(ttsNumberset, strMsg);
            } else {
                tvdialog.setText("請點選播號鍵即可撥電話給" + name + "\n\n\n");
                //TTS(google)
                startSpeakVoice(ttsNumberset, tvdialog.getText().toString());
                Uri uri = Uri.parse("tel:" + phone);
                Intent intent = new Intent(Intent.ACTION_DIAL, uri);

                //開啓圓形功能列
                FloatWindowManager.getInstance().applyOrShowFloatWindow(this);

                startActivity(intent);
                finish();
            }
        }
    }

    //讀取電話簿
    public String getphonebook(String name) {
        String phone = "";
        Cursor namedata = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        while (namedata.moveToNext()) {
            //HashMap<String, String> data = new HashMap();
            String phonenumber = "";
            long id = namedata.getLong(
                    namedata.getColumnIndex(ContactsContract.Contacts._ID)
            );
            Cursor numberdata = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    null,
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + Long.toString(id), null, null);
            while (numberdata.moveToNext()) {
                phonenumber = numberdata.getString(numberdata.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            }
            numberdata.close();
            String nameres = namedata.getString(namedata.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            if (name.equals(nameres)) {
                phone = phonenumber;
            }
        }
        return phone;
    }

    //TextToSpeech物件初始化(google)
    private void CreateLanguageTTS() {
        if (tts == null) {
            tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
                @Override
                public void onInit(int status) {
                    if (status == TextToSpeech.SUCCESS) {
                        Locale l = Locale.TAIWAN;
                        if (tts.isLanguageAvailable(l) == TextToSpeech.LANG_COUNTRY_AVAILABLE) {
                            tts.setLanguage(l);
                        }
                    }
                }
            });
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            dialog();
            return false;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }


    private void dialog() {
        AlertDialog.Builder ad = new AlertDialog.Builder(Live2dTop.this);
        ad.setTitle("關閉訊息");
        ad.setMessage("您要離開了嗎");
        startSpeakVoice(ttsNumberset, "您要離開了嗎");
        ad.setPositiveButton("殘忍離開", btnOut_click);
        Dialog message = ad.create();
        message.show();
    }

    private DialogInterface.OnClickListener btnOut_click = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            finish();
        }
    };

    @Override
    protected void onResume() {
        //live2DMgr.onResume() ;
        FloatWindowManager.getInstance().dismissWindow();
        super.onResume();//Activity重開時Event
    }

    @Override
    protected void onStop() {
        //開啓圓形功能列
        if (FloatWindowManager.getInstance().checkfloatwindowPermission(this) == true) {
            FloatWindowManager.getInstance().openFloatWindow(this);
        }

        super.onStop();
    }

    @Override
    protected void onPause() {
        FloatWindowManager.getInstance().dismissWindow();
        live2DMgr.onPause();
        super.onPause();//Activity暫停時Event
    }

    public void exit() {
        SoundManager.release();
        instance.finish();
    }

    //此區為側邊選單點擊事件
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        //實作清單觸發
        mDrawerLayout.closeDrawer(GravityCompat.START);
        int id = item.getItemId();

        if (id == R.id.nav_favorite) {
            //TODO pickerView
//            pvOptions.show();
        } else if (id == R.id.nav_settings) {
            Toast.makeText(this, "設定", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_logout) {
            Toast.makeText(this, "登出", Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    //上側ToolBar
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//
//        if (mToggle.onOptionsItemSelected(item)) {
//            //當按下左上三條線或顯示工具列
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }

//    PickerView
//    private void initOptionData() {
//        options1Items.add("Google小姐");
//        options1Items.add("小女孩");
//        options1Items.add("台語大嬸");
//    }
//// TODO pickerView
////    private void initOptionPicker() {
////
////        pvOptions = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
////            @Override
////            public void onOptionsSelect(int options1, int options2, int options3, View v) {
////                String tx = options1Items.get(options1);
////                int index = options1Items.indexOf(options1Items.get(options1)) /*+ options2Items.get(options1).get(options2)*/;
////                Toast.makeText(getApplicationContext(), "切換為" + tx + "語音", Toast.LENGTH_SHORT).show();
////                ttsNumberset = index;
////            }
////        }).setTitleText("請選擇語音模式") // 選擇器標題
////                .setContentTextSize(20)//設定滾輪文字大小
////                .setDividerColor(Color.LTGRAY)//設定分割線顏色
////                .setSelectOptions(0)//默認選中值
////                .setBgColor(Color.WHITE)//選擇器背景顏色
////                .setTitleBgColor(Color.LTGRAY)//標題背景顏色
////                .setTitleColor(Color.WHITE)//標題顏色
////                .setCancelColor(Color.RED)//取消顏色
////                .setSubmitColor(Color.BLUE)//確定顏色
////                .setCancelText("取消")//取消文字
////                .setSubmitText("確定")//選定文字
////                .setTextColorCenter(Color.BLACK)//選項顏色
////                .setBackgroundId(0x66000000) //設定外部遮罩顏色
////                .build();
////
////        pvOptions.setPicker(options1Items/*, options2Items*/);
////    }




    /*
     * Activityが作成されたときのイベント
     */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //TTS(google)物件初始化
        CreateLanguageTTS();
        setupGUI();
        if (FloatWindowManager.getInstance().checkfloatwindowPermission(this) == false) {
            FloatWindowManager.getInstance().floatwindowPermission(this);
        }
        //一開始先把對話框隱藏起來
        nameId.setVisibility(View.GONE);
        tvdialog.setVisibility(View.GONE);

        FileManager.init(this.getApplicationContext());

    }

    /* TODO InitialComponent
     * GUIの初期化
     * activity_main.xmlからViewを作成し、そこにLive2Dを配置する
     * (從live2dtop.xml生成View, 並配覆Live2d)
     */
    void setupGUI() {
        setContentView(live2dtop);

        //  Viewの初期化
        view = live2DMgr.createView(this);

        // activity_main.xmlにLive2DのViewをレイアウトする
        layout = (FrameLayout) findViewById(R.id.live2DLayout);
        layout.addView(view, 0, new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));

        // モデル切り替えボタン /Model切換用Button
        iBtn = (ImageButton) findViewById(R.id.imageButton1);
        iBtn.setOnClickListener(new ClickListener());

        //設定說話按鈕點擊後STT設定物件
        btn_speak = (ImageButton) findViewById(R.id.btn_speak);
        btn_speak.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //停止前面的話, 重新講話
                tts.stop();
                videoView.stopPlayback();

                Intent it = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                it.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
//                it.putExtra(RecognizerIntent.EXTRA_PROMPT, "請說話......");
                startActivityForResult(it, 1);
            }
        });

        tvdialog = (TextView) findViewById(R.id.txtDialog);
        tvdialog.setMovementMethod(ScrollingMovementMethod.getInstance());
        btnStopSpeak = (ImageButton) findViewById(R.id.btnStopSpeak);
        btnStopSpeak.setOnClickListener(btnStopSpeak_click);
        btnClearView = (ImageButton) findViewById(R.id.btnClearView);
        btnClearView.setOnClickListener(btnSwitchSpeakView_click);
        btnRepeatSpeak = (ImageButton) findViewById(R.id.btnRepeatSpeak);
        btnRepeatSpeak.setOnClickListener(btnRepeatSpeak_click);
        btnDictionary = (ImageButton) findViewById(R.id.btn_dictionary);
        btnDictionary.setOnClickListener(btnDictionary_click);
        btnClock = (TextClock) findViewById(R.id.btnClock);
        btnClock.setOnClickListener(btnClock_click);
        mgr = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        //上側ToolBar
        //mToolboar = (Toolbar) findViewById(R.id.nav_action);
        //setSupportActionBar(mToolboar);//Toolbar取代原本的ActionBar

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout); //在Fragment下面的Activity
        //上側ToolBar
        //mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);//必須用字串資源檔
        //mDrawerLayout.addDrawerListener(mToggle);//工具欄監聽事件
        //上側ToolBar
        //mToggle.syncState();
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);//隱藏顯示箭頭返回

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);//清單觸發監聽事件

        /*PickerView
        initOptionData();

        TODO pickerView
        initOptionPicker();
        */

        //progressbar
        avi = (AVLoadingIndicatorView) findViewById(R.id.avi);
        //

        //videoView
        videoView = (VideoView) this.findViewById(R.id.videoView01);
        MediaController mc = new MediaController(this);
        videoView.setMediaController(mc);
        nameId = (TextView) findViewById(R.id.nameId);
        //
    }

    //TODO 自建View元件
    LAppView view;
    ImageButton iBtn;
    FrameLayout layout;
    ImageButton btn_speak;
    static TextView tvdialog;
    TextToSpeech tts;
    ImageButton btnStopSpeak;
    ImageButton btnClearView;
    ImageButton btnRepeatSpeak;
    ImageButton btnDictionary;
    TextClock btnClock;
    VideoView videoView;
    //progressbar
    AVLoadingIndicatorView avi;
    //動態浮動視窗
    ProjectPopupWindows projectpopupwindow;
    NavigationView navigationView; //側邊選單
    TextView nameId;

}
