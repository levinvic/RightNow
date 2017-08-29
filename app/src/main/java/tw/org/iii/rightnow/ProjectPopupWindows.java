package tw.org.iii.rightnow;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.method.ScrollingMovementMethod;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.CharacterStyle;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.PopupWindow;
import android.widget.TextView;


public class ProjectPopupWindows extends PopupWindow {

    private View mView;
    public TextView dictionary;


    public SpannableStringBuilder highlight(String text, int color, int fontSize) {

        SpannableStringBuilder spannable = new SpannableStringBuilder(text);

        CharacterStyle span = null, span1;

        span = new ForegroundColorSpan(color);//字體顏色
        spannable.setSpan(span, 0, text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        span1 = new AbsoluteSizeSpan(fontSize);//字體大小
        spannable.setSpan(span1, 0, text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannable;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ProjectPopupWindows(Activity context) {
        super(context);


        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mView = inflater.inflate(R.layout.popupwindow, null);

        dictionary = (TextView) mView.findViewById(R.id.Dictionary);
        dictionary.setMovementMethod(ScrollingMovementMethod.getInstance());

        SpannableStringBuilder DS = new SpannableStringBuilder("歡迎使用RightNow手機行動助理\n");
        DS.append("此為行動助理之使用說明書\n\n");
        DS.append("1.請先點選右下方發話鍵\n");
        DS.append("2.對著手機話筒說出關鍵字\n\n");
        DS.append("以下為現有關鍵字庫及其對應之功能\n\n");

        //內部功能類
        DS.append(highlight("內部功能:\n",Color.RED,70));
        DS.append(highlight("\"國語\"\n", Color.YELLOW, 70));
        DS.append("語音將切換為Google小姐語音\n");
        DS.append(highlight("\"台語\"\n", Color.YELLOW, 70));
        DS.append("語音將切換為台語模式\n");
        DS.append(highlight("\"小女孩\"\n", Color.YELLOW, 70));
        DS.append("語音將切換為小女孩模式\n");
        DS.append("※台語及小女孩必須開啟網路才可正常運作\n");
        DS.append("※連線逾時將會切換為國語\n");
        DS.append(highlight("\"提示\"\n", Color.YELLOW, 70));
        DS.append("助理將隨機告知您一項目前現有功能\n");
        DS.append(highlight("\"故事\"\n", Color.YELLOW, 70));
        DS.append("助理將隨機唸出一段諺語\n");

        //外部功能類(內建)
        DS.append(highlight("外部功能(內建):\n",Color.RED,70));
        DS.append(highlight("目的地+\"怎麼走\"\n", Color.YELLOW, 70));
        DS.append("助理將開啟Google導航並設定目的地\n");
        DS.append(highlight("\"附近的\"+搜尋內容\n", Color.YELLOW, 70));
        DS.append("助理將開啟Google導航並設定目的地\n");
        DS.append(highlight("\"打給\"+通訊錄中全名\n", Color.YELLOW, 70));
        DS.append("助理將幫您撥號給該用戶\n");
        DS.append(highlight("\"我要看\"+影片名稱\n", Color.YELLOW, 70));
        DS.append("助理將在Youtube幫您搜尋相關影片\n");
        DS.append(highlight("\"甚麼是\"+搜尋內容\n", Color.YELLOW, 70));
        DS.append("助理將在維基百科幫您搜尋相關內容\n");
        DS.append(highlight("\"相簿\"\n", Color.YELLOW, 70));
        DS.append("助理將幫您開啟手機內建相簿\n");
        DS.append(highlight("\"Chrome\"\n", Color.YELLOW, 70));
        DS.append("助理將幫您開啟Google Chrome瀏覽器\n");
        DS.append(highlight("\"Gmail\"\n", Color.YELLOW, 70));
        DS.append("助理將幫您開啟Gmail信箱功能\n");
        DS.append(highlight("\"Youtube\"\n", Color.YELLOW, 70));
        DS.append("助理將幫您開啟Youtube影音平台\n");
        DS.append("另有電視台直播可收看，目前有\n");
        DS.append(highlight("\"民視、中視、華視、台視、\"\n", Color.YELLOW, 70));
        DS.append(highlight("\"公視、東森、三立、中天、滾石老歌\"\n", Color.YELLOW, 70));
        DS.append("※直接說出電視台關鍵字即可\n");
        DS.append(highlight("\"GoogleMap\"\n", Color.YELLOW, 70));
        DS.append("助理將幫您開啟Google地圖\n");
        DS.append(highlight("\"發票\"\n", Color.YELLOW, 70));
        DS.append("助理將列出最新一期的發票號碼\n");
        DS.append("※除發票外還有\n");
        DS.append(highlight("\"大樂透、大福彩、威力彩\"\n", Color.YELLOW, 70));

        //外部功能類(需安裝)
        DS.append(highlight("外部功能(需安裝):\n",Color.RED,70));
        DS.append("※如您沒有安裝，將被導向Play商店進行安裝\n");
        DS.append(highlight("\"Line\"\n", Color.YELLOW, 70));
        DS.append("助理將幫您開啟Line通訊軟體\n");
        DS.append(highlight("\"Skype\"\n", Color.YELLOW, 70));
        DS.append("助理將幫您開啟Skype通訊軟體\n");
        DS.append(highlight("\"Messenger\"\n", Color.YELLOW, 70));
        DS.append("助理將幫您開啟FB通訊軟體\n");
        DS.append(highlight("\"Facebook\"\n", Color.YELLOW, 70));
        DS.append("助理將幫您開啟FB社群軟體\n");
        DS.append(highlight("\"PTT\"\n", Color.YELLOW, 70));
        DS.append("助理將幫您開啟PTT社群軟體\n");
        DS.append(highlight("\"Twitter\"\n", Color.YELLOW, 70));
        DS.append("助理將幫您開啟Twitter社群軟體\n");
        DS.append(highlight("\"Twitch\"\n", Color.YELLOW, 70));
        DS.append("助理將幫您開啟Twitch直播軟體\n");
        DS.append(highlight("\"繪圖\"\n", Color.YELLOW, 70));
        DS.append("助理將幫您開啟Sony繪圖軟體\n");
        DS.append("※Sony手機限定\n");
        DS.append(highlight("\"天氣\"\n", Color.YELLOW, 70));
        DS.append("助理將幫您開啟Sony繪圖軟體\n");
        DS.append("※Sony手機限定\n");
        DS.append(highlight("\"行事曆\"\n", Color.YELLOW, 70));
        DS.append("助理將幫您開啟手機行事曆\n");
        DS.append("※Sony手機限定\n");



        dictionary.setText(DS);

        //設置PopupWindow的View
        this.setContentView(mView);
        //設置PopupWindow彈出視窗的寬
        this.setWidth(LayoutParams.MATCH_PARENT);
        //設置PopupWindow彈出視窗的高
        this.setHeight(LayoutParams.WRAP_CONTENT);
        //設置PopupWindow彈出視窗可點擊
        this.setFocusable(true);
        //設置SelectPicPopupWindow彈出視窗的動畫效果
        this.setAnimationStyle(R.style.Animation);
        //宣告一個ColorDrawable顏色為半透明
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        //設置SelectPicPopupWindow彈出視窗的背景
        this.setBackgroundDrawable(dw);
    }
}
