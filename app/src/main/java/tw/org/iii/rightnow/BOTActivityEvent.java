package tw.org.iii.rightnow;


import android.app.Activity;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.StrictMode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.security.Key;
import java.util.ArrayList;
import java.util.Calendar;

import static tw.org.iii.rightnow.Live2dTop.tvdialog;

//非static有含有Activity的方法寫在這

public class BOTActivityEvent extends Activity {

    //發票對獎
    public String Recipt() {

        String recipt = "";

        StrictMode.ThreadPolicy l_policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(l_policy);

        try {
            URL url = new URL("http://invoice.etax.nat.gov.tw/");
            URLConnection con = url.openConnection();
            InputStream streamIn = con.getInputStream();

            BufferedReader r = new BufferedReader(new InputStreamReader(streamIn));
            StringBuilder html = new StringBuilder();

            String line;
            while ((line = r.readLine()) != null) {
                html.append(line);
            }

            String page = html.toString();

            recipt +="本期發票中獎號碼\n";

            String key = "<td class=\"title\">特別獎</td>                                <td><span class=\"t18Red\">";
            int start = page.indexOf(key) + key.length();
            recipt += "特別獎:" + page.substring(start, start + 8) + "\n";

            String key2 = "<td class=\"title\">特獎</td>                                <td><span class=\"t18Red\">";
            start = page.indexOf(key2) + key2.length();
            recipt += "特獎:" + page.substring(start, start + 8) + "\n";

            String key3 = "<td class=\"title\">頭獎</td>                                <td><span class=\"t18Red\">";
            start = page.indexOf(key3) + key3.length();
            recipt += "頭獎:" + page.substring(start, start + 26) + "\n";

            String key4 = "<td class=\"title\">增開六獎</td>                                <td><span class=\"t18Red\">";
            start = page.indexOf(key4) + key4.length();
            recipt += "增開六獎:" + page.substring(start, start + 3) + "\n\n";

            recipt += "是否要對獎?\n\n\n";

        } catch (IOException e) {
            e.printStackTrace();
        }
        return recipt;
    }

    //開youtube直播
    MediaPlayer mPlayer;

    public void startTV(Uri uri) {
        try {
            mPlayer = MediaPlayer.create(this, uri);
            mPlayer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //威力彩
    public String PowerLottery() {

        String powerLottery = "";

        StrictMode.ThreadPolicy l_policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(l_policy);

        try {
            URL url = new URL("http://www.taiwanlottery.com.tw/Result_all.aspx");
            URLConnection con = url.openConnection();
            InputStream streamIn = con.getInputStream();

            BufferedReader r = new BufferedReader(new InputStreamReader(streamIn));
            StringBuilder html = new StringBuilder();

            String line;
            while ((line = r.readLine()) != null) {
                html.append(line);
            }

            String page = html.toString();


            String key = "第 <span id=\"SL638DrawTerm_new\">";
            int start = page.indexOf(key) + key.length();
            powerLottery += "威力彩\n第: " + page.substring(start, start + 10) + " 期\n";

            String key2 = "第1區 依大小順序排列：<span>";
            start = page.indexOf(key2) + key2.length();
            powerLottery += "第1區:\n" + page.substring(start, start + 77).replaceAll("<span>", " ").replaceAll("</span>", "") + "\n";

            String key3 = "第2區：<span class=\"font_red14\">";
            start = page.indexOf(key3) + key3.length();
            powerLottery += "第2區: " + page.substring(start, start + 3) + "\n";

            String key4 = "<span id=\"SL638ExDate_new\">";
            start = page.indexOf(key4) + key4.length();
            powerLottery += "兌獎日期至:\n" + page.substring(start, start + 15) + "\n祝您中獎!!\n\n\n";


        } catch (IOException e) {
            e.printStackTrace();
        }
        return powerLottery;
    }

    public Boolean 大樂透Check(String StringInput){
        Boolean biglottery = false;
        for (int i = 0; i < KeyDictionary.大樂透.length; i++) {
            if (StringInput.indexOf(KeyDictionary.大樂透[i]) != -1)
                biglottery = true;
        }
        return biglottery;
    }
    public Boolean 大福彩Check(String StringInput){
        Boolean bigColor = false;
        for (int i = 0; i < KeyDictionary.大福彩.length; i++) {
            if (StringInput.indexOf(KeyDictionary.大福彩[i]) != -1)
                bigColor = true;
        }
        return bigColor;
    }
    public Boolean 威力彩Check(String StringInput){
        Boolean PowerCheck = false;
        for (int i = 0; i < KeyDictionary.威力彩.length; i++) {
            if (StringInput.indexOf(KeyDictionary.威力彩[i]) != -1)
                PowerCheck = true;
        }
        return PowerCheck;
    }

    public Boolean 說明Check(String StringInput){
        Boolean tipCheck = false;
        for (int i = 0; i < KeyDictionary.tips.length; i++) {
            if (StringInput.indexOf(KeyDictionary.tips[i]) != -1)
                tipCheck = true;
        }
        return tipCheck;
    }


    //大樂透Check
    public String BigLottery() {

        String powerLottery = "";

        StrictMode.ThreadPolicy l_policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(l_policy);

        try {
            URL url = new URL("http://www.taiwanlottery.com.tw/Result_all.aspx");
            URLConnection con = url.openConnection();
            InputStream streamIn = con.getInputStream();

            BufferedReader r = new BufferedReader(new InputStreamReader(streamIn));
            StringBuilder html = new StringBuilder();

            String line;
            while ((line = r.readLine()) != null) {
                html.append(line);
            }

            String page = html.toString();


            String key = "<span id=\"L649DrawTerm\">";
            int start = page.indexOf(key) + key.length();
            powerLottery += "大樂透Check\n第: " + page.substring(start, start + 9) + " 期\n";

            String key2 = "<td class=\"even\">" +
                    "                                    依大小順序排列：";
            start = page.indexOf(key2) + key2.length();
            powerLottery += "本期中獎號碼:\n" + page.substring(start, start + 83).replaceAll("<span>", " ").replaceAll("</span>", "") + "\n";

            String key3 = "特別號：";
            start = page.indexOf(key3) + key3.length();
            String str = page.substring(start, start + 102);
            str = str.replaceAll("<span class=\"font_red14\">", "");
            str = str.replaceAll("<span class=font_red14>", "");
            str = str.replaceAll(" ", "");
            str = str.replaceAll("</span>", "");
            powerLottery += "特別獎: " + str + "\n";
            String key4 = "<span id=\"L649ExDate\">";
            start = page.indexOf(key4) + key4.length();
            powerLottery += "兌獎日期至:\n" + page.substring(start, start + 15) + "\n祝您中獎!!\n\n\n";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return powerLottery;
    }

    //大福彩
    public String BigLoveColor() {

        String powerLottery = "";

        StrictMode.ThreadPolicy l_policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(l_policy);

        try {
            URL url = new URL("http://www.taiwanlottery.com.tw/Result_all.aspx");
            URLConnection con = url.openConnection();
            InputStream streamIn = con.getInputStream();

            BufferedReader r = new BufferedReader(new InputStreamReader(streamIn));
            StringBuilder html = new StringBuilder();

            String line;
            while ((line = r.readLine()) != null) {
                html.append(line);
            }

            String page = html.toString();
            String key = "<span id=\"L740DrawTerm\">";
            int start = page.indexOf(key) + key.length();
            powerLottery += "大福彩\n第: " + page.substring(start, start + 9) + " 期\n";

            String key2 = "至<span id=\"L740ExDate\">";
            start = page.indexOf(key2) + key2.length();
            String str = page.substring(start + 15, start + 505);
            str = str.replaceAll("<tr>", "");
            str = str.replaceAll("</tr>", "");
            str = str.replaceAll("</td>", "");
            str = str.replace("&nbsp;(註2)", "");
            str = str.replace("<td class=\"first\">", "");
            str = str.replace("本期中獎號碼", "");
            str = str.replace("<td class=\"even\">", "");
            str = str.replace("依大小順序排列：", "");
            str = str.replaceAll("<span>", "");
            str = str.replaceAll("</span>", "\t");
            str = str.replaceAll(" ", "");
            powerLottery += "本期中獎號碼:\n" + str + "\n";

            String key3 = "&nbsp;(註2)                                </td>";
            start = page.indexOf(key3) + key3.length();
            String str1 = page.substring(start + 486, start + 551);

            str1 = str1.replaceAll("<tr>", "");
            str1 = str1.replaceAll("</tr>", "");
            str1 = str1.replace("<td class=\"first\">", "");
            str1 = str1.replaceAll("</td>", "");
            str1 = str1.replace("<td class=\"even\">", "");
            str1 = str1.replace("本期中獎號碼", "");
            str1 = str1.replace("依大小順序排列：", "");
            str1 = str1.replace("&nbsp;特別號：", "");
            str1 = str1.replaceAll("<span>", "");
            str1 = str1.replaceAll("</span>", "");
            str1 = str1.replaceAll("<span class=font_red14>", "");
            str1 = str1.replace("<br />", "");
            str1 = str1.replaceAll(" ", "");

            powerLottery += "特別獎: " + str1 + "\n";

            String key4 = "<span id=\"L740ExDate\">";
            start = page.indexOf(key4) + key4.length();
            powerLottery += "兌獎日期至:\n" + page.substring(start, start + 15) + "\n祝您中獎!!\n\n\n";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return powerLottery;
    }

    //隨機講故事
    public void store() {
        ArrayList<String> store1 = new ArrayList<>();
        store1.add(PackDictionary.Store);
        store1.add(PackDictionary.Store1);
        store1.add(PackDictionary.Store2);
        store1.add(PackDictionary.Store3);
        store1.add(PackDictionary.Store4);
        store1.add(PackDictionary.Store5);
        store1.add(PackDictionary.Store6);
        int random1 = (int) (Math.random() * store1.size());
        tvdialog.setText(store1.get(random1) + "\n\n\n");
    }

    //隨機說明檔
    public void tip() {
        ArrayList<String> tip1 = new ArrayList<>();
        //說明檔持續往下加
        tip1.add(PackDictionary.tip);
        tip1.add(PackDictionary.tip1);
        tip1.add(PackDictionary.tip2);
        tip1.add(PackDictionary.tip3);
        tip1.add(PackDictionary.tip4);
        tip1.add(PackDictionary.tip5);
        tip1.add(PackDictionary.tip6);
        tip1.add(PackDictionary.tip7);
        tip1.add(PackDictionary.tip8);
        tip1.add(PackDictionary.tip9);
        tip1.add(PackDictionary.tip10);
        tip1.add(PackDictionary.tip11);
        tip1.add(PackDictionary.tip12);
        tip1.add(PackDictionary.tip13);
        tip1.add(PackDictionary.tip14);
        tip1.add(PackDictionary.tip15);
        tip1.add(PackDictionary.tip16);
        tip1.add(PackDictionary.tip17);
        tip1.add(PackDictionary.tip18);
        tip1.add(PackDictionary.tip19);
        tip1.add(PackDictionary.tip20);
        tip1.add(PackDictionary.tip21);
        tip1.add(PackDictionary.tip22);
        tip1.add(PackDictionary.tip23);
        tip1.add(PackDictionary.tip24);
        tip1.add(PackDictionary.tip25);
        tip1.add(PackDictionary.tip26);
        tip1.add(PackDictionary.tip27);
        tip1.add(PackDictionary.tip28);
        tip1.add(PackDictionary.tip29);
        tip1.add(PackDictionary.tip30);
        tip1.add(PackDictionary.tip31);
        tip1.add(PackDictionary.tip32);
        tip1.add(PackDictionary.tip33);
        tip1.add(PackDictionary.tip34);
        tip1.add(PackDictionary.tip35);
        tip1.add(PackDictionary.tip36);
        tip1.add(PackDictionary.tip37);
        tip1.add(PackDictionary.tip38);
        tip1.add(PackDictionary.tip39);
        int random2 = (int) (Math.random() * tip1.size());
        tvdialog.setText(tip1.get(random2) + "\n\n\n");
    }


    //判定時間
    public Boolean NowTime(int TimeFrom, int TimeTo) {
        Calendar cl = Calendar.getInstance();
        int hour = cl.get(Calendar.HOUR_OF_DAY);
        Boolean time = false;
        if (hour > TimeFrom && hour <= TimeTo) {
            time = true;
        }
        return time;
    }

    //GoogleMapGuide
    public String GoogleMapGuide(String StringInput) {
        for (int i = 0; i < KeyDictionary.GoogleMapGuide.length; i++) {
            StringInput = StringInput.replace(KeyDictionary.GoogleMapGuide[i], "");
        }
        return StringInput;
    }

    public Boolean GoogleMapGuideCheck(String StringInput) {
        Boolean googlemapguidecheck = false;
        for (int i = 0; i < KeyDictionary.GoogleMapGuide.length; i++) {
            if (StringInput.indexOf(KeyDictionary.GoogleMapGuide[i]) != -1)
                googlemapguidecheck = true;
        }
        return googlemapguidecheck;
    }

    //Album
    public Boolean Album(String StringInput) {
        Boolean album = false;
        for (int i = 0; i < KeyDictionary.Album.length; i++) {
            if (StringInput.indexOf(KeyDictionary.Album[i]) != -1)
                album = true;
        }
        return album;
    }

    //Line
    public Boolean Line(String StringInput) {
        Boolean line = false;
        for (int i = 0; i < KeyDictionary.Line.length; i++) {
            if (StringInput.indexOf(KeyDictionary.Line[i]) != -1)
                line = true;
        }
        return line;
    }

    //Facebook
    public Boolean Facebook(String StringInput) {
        Boolean facebook = false;
        for (int i = 0; i < KeyDictionary.Facebook.length; i++) {
            if (StringInput.indexOf(KeyDictionary.Facebook[i]) != -1)
                facebook = true;
        }
        return facebook;
    }

    public Boolean Chrome(String StringInput) {
        Boolean chrome = false;
        for (int i = 0; i < KeyDictionary.Chrome.length; i++) {
            if (StringInput.indexOf(KeyDictionary.Chrome[i]) != -1)
                chrome = true;
        }
        return chrome;
    }

    public Boolean Draw(String StringInput) {
        Boolean draw = false;
        for (int i = 0; i < KeyDictionary.Draw.length; i++) {
            if (StringInput.indexOf(KeyDictionary.Draw[i]) != -1)
                draw = true;
        }
        return draw;
    }

    public Boolean Weather(String StringInput) {
        Boolean weather = false;
        for (int i = 0; i < KeyDictionary.Weather.length; i++) {
            if (StringInput.indexOf(KeyDictionary.Weather[i]) != -1)
                weather = true;
        }
        return weather;
    }

    public Boolean Gmail(String StringInput) {
        Boolean gmail = false;
        for (int i = 0; i < KeyDictionary.Gmail.length; i++) {
            if (StringInput.indexOf(KeyDictionary.Gmail[i]) != -1)
                gmail = true;
        }
        return gmail;
    }

    public Boolean Youtube(String StringInput) {
        Boolean youtube = false;
        for (int i = 0; i < KeyDictionary.Youtube.length; i++) {
            if (StringInput.indexOf(KeyDictionary.Youtube[i]) != -1)
                youtube = true;
        }
        return youtube;
    }

    public Boolean GoogleMap(String StringInput) {
        Boolean googlemap = false;
        for (int i = 0; i < KeyDictionary.GoogleMap.length; i++) {
            if (StringInput.indexOf(KeyDictionary.GoogleMap[i]) != -1)
                googlemap = true;
        }
        return googlemap;
    }

    public Boolean Skype(String StringInput) {
        Boolean skype = false;
        for (int i = 0; i < KeyDictionary.Skype.length; i++) {
            if (StringInput.indexOf(KeyDictionary.Skype[i]) != -1)
                skype = true;
        }
        return skype;
    }

    public Boolean Twitch(String StringInput) {
        Boolean twitch = false;
        for (int i = 0; i < KeyDictionary.Twitch.length; i++) {
            if (StringInput.indexOf(KeyDictionary.Twitch[i]) != -1)
                twitch = true;
        }
        return twitch;
    }

    public Boolean Calendar(String StringInput) {
        Boolean calendar = false;
        for (int i = 0; i < KeyDictionary.Calendar.length; i++) {
            if (StringInput.indexOf(KeyDictionary.Calendar[i]) != -1)
                calendar = true;
        }
        return calendar;
    }

    public Boolean Ptt(String StringInput) {
        Boolean ptt = false;
        for (int i = 0; i < KeyDictionary.Ptt.length; i++) {
            if (StringInput.indexOf(KeyDictionary.Ptt[i]) != -1)
                ptt = true;
        }
        return ptt;
    }

    public Boolean Twitter(String StringInput) {
        Boolean twitter = false;
        for (int i = 0; i < KeyDictionary.Twitter.length; i++) {
            if (StringInput.indexOf(KeyDictionary.Twitter[i]) != -1)
                twitter = true;
        }
        return twitter;
    }

    public Boolean Messenger(String StringInput) {
        Boolean messenger = false;
        for (int i = 0; i < KeyDictionary.Messenger.length; i++) {
            if (StringInput.indexOf(KeyDictionary.Messenger[i]) != -1)
                messenger = true;
        }
        return messenger;
    }

    public Boolean 民視(String StringInput) {
        Boolean 民視 = false;
        for (int i = 0; i < KeyDictionary.民視.length; i++) {
            if (StringInput.indexOf(KeyDictionary.民視[i]) != -1)
                民視 = true;
        }
        return 民視;
    }

    public Boolean 中視(String StringInput) {
        Boolean 中視 = false;
        for (int i = 0; i < KeyDictionary.中視.length; i++) {
            if (StringInput.indexOf(KeyDictionary.中視[i]) != -1)
                中視 = true;
        }
        return 中視;
    }

    public Boolean 華視(String StringInput) {
        Boolean 華視 = false;
        for (int i = 0; i < KeyDictionary.華視.length; i++) {
            if (StringInput.indexOf(KeyDictionary.華視[i]) != -1)
                華視 = true;
        }
        return 華視;
    }

    public Boolean 台視(String StringInput) {
        Boolean 台視 = false;
        for (int i = 0; i < KeyDictionary.台視.length; i++) {
            if (StringInput.indexOf(KeyDictionary.台視[i]) != -1)
                台視 = true;
        }
        return 台視;
    }

    public Boolean 公視(String StringInput) {
        Boolean 公視 = false;
        for (int i = 0; i < KeyDictionary.公視.length; i++) {
            if (StringInput.indexOf(KeyDictionary.公視[i]) != -1)
                公視 = true;
        }
        return 公視;
    }

    public Boolean 東森(String StringInput) {
        Boolean 東森 = false;
        for (int i = 0; i < KeyDictionary.東森.length; i++) {
            if (StringInput.indexOf(KeyDictionary.東森[i]) != -1)
                東森 = true;
        }
        return 東森;
    }

    public Boolean 三立(String StringInput) {
        Boolean 三立 = false;
        for (int i = 0; i < KeyDictionary.三立.length; i++) {
            if (StringInput.indexOf(KeyDictionary.三立[i]) != -1)
                三立 = true;
        }
        return 三立;
    }

    public Boolean 中天(String StringInput) {
        Boolean 中天 = false;
        for (int i = 0; i < KeyDictionary.中天.length; i++) {
            if (StringInput.indexOf(KeyDictionary.中天[i]) != -1)
                中天 = true;
        }
        return 中天;
    }

    public Boolean 滾石老歌(String StringInput) {
        Boolean 滾石老歌 = false;
        for (int i = 0; i < KeyDictionary.滾石老歌.length; i++) {
            if (StringInput.indexOf(KeyDictionary.滾石老歌[i]) != -1)
                滾石老歌 = true;
        }
        return 滾石老歌;
    }

    public Boolean 打電話給(String StringInput) {
        Boolean 打電話給 = false;
        for (int i = 0; i < KeyDictionary.打電話給.length; i++) {
            if (StringInput.indexOf(KeyDictionary.打電話給[i]) != -1)
                打電話給 = true;
        }
        return 打電話給;
    }

    public Boolean 發票(String StringInput) {
        Boolean 發票 = false;
        for (int i = 0; i < KeyDictionary.發票.length; i++) {
            new KeyDictionary();
            if (StringInput.indexOf(KeyDictionary.發票[i]) != -1)
                發票 = true;
        }
        return 發票;
    }

    public Boolean 卡提諾(String StringInput) {
        Boolean 卡提諾 = false;
        for (int i = 0; i < KeyDictionary.卡提諾.length; i++) {
            if (StringInput.indexOf(KeyDictionary.卡提諾[i]) != -1)
                卡提諾 = true;
        }
        return 卡提諾;
    }

    public Boolean 開Chrome搜尋(String StringInput) {
        Boolean 開Chrome搜尋 = false;
        for (int i = 0; i < KeyDictionary.開Chrome搜尋.length; i++) {
            if (StringInput.indexOf(KeyDictionary.開Chrome搜尋[i]) != -1)
                開Chrome搜尋 = true;
        }
        return 開Chrome搜尋;
    }

    public Boolean 我要看(String StringInput) {
        Boolean 我要看 = false;
        for (int i = 0; i < KeyDictionary.我要看.length; i++) {
            if (StringInput.indexOf(KeyDictionary.我要看[i]) != -1)
                我要看 = true;
        }
        return 我要看;
    }

    public Boolean 維基(String StringInput) {
        Boolean 維基 = false;
        for (int i = 0; i < KeyDictionary.維基.length; i++) {
            if (StringInput.indexOf(KeyDictionary.維基[i]) != -1)
                維基 = true;
        }
        return 維基;
    }

    //
    public Boolean 前往對獎(String StringInput) {
        Boolean 前往對獎 = false;
        for (int i = 0; i < KeyDictionary.前往對獎.length; i++) {
            if (StringInput.indexOf(KeyDictionary.前往對獎[i]) != -1)
                前往對獎 = true;
        }
        return 前往對獎;
    }


    public String YoutubeSearch(String StringInput) {
        for (int i = 0; i < KeyDictionary.我要看.length; i++) {
            StringInput = StringInput.replace(KeyDictionary.我要看[i], "");
        }
        return StringInput;
    }

    public String ChromeSearch(String StringInput) {
        for (int i = 0; i < KeyDictionary.開Chrome搜尋.length; i++) {
            StringInput = StringInput.replace(KeyDictionary.開Chrome搜尋[i], "");
        }
        return StringInput;
    }

    public Boolean 台語(String StringInput) {
        Boolean 台語 = false;
        for (int i = 0; i < KeyDictionary.台語.length; i++) {
            if (StringInput.indexOf(KeyDictionary.台語[i]) != -1)
                台語 = true;
        }
        return 台語;
    }

    public Boolean 國語(String StringInput) {
        Boolean 國語 = false;
        for (int i = 0; i < KeyDictionary.國語.length; i++) {
            if (StringInput.indexOf(KeyDictionary.國語[i]) != -1)
                國語 = true;
        }
        return 國語;
    }

    public Boolean 小女孩(String StringInput) {
        Boolean 小女孩 = false;
        for (int i = 0; i < KeyDictionary.小女孩.length; i++) {
            if (StringInput.indexOf(KeyDictionary.小女孩[i]) != -1)
                小女孩 = true;
        }
        return 小女孩;
    }

    public String 附近的(String StringInput) {
        for (int i = 0; i < KeyDictionary.附近的.length; i++) {
            if (StringInput.indexOf(KeyDictionary.附近的[i]) != -1) {
                int a = StringInput.indexOf(KeyDictionary.附近的[i]) + KeyDictionary.附近的[i].length();
                int b = StringInput.length();
                StringInput = StringInput.substring(a, b);
                break;
            }
//            StringInput = StringInput.replace(new KeyDictionary().附近的[i],"");
        }
        return StringInput;
    }

    public Boolean 附近的Check(String StringInput) {
        Boolean 附近的Check = false;
        for (int i = 0; i < KeyDictionary.附近的.length; i++) {
            if (StringInput.indexOf(KeyDictionary.附近的[i]) != -1)
                附近的Check = true;
        }
        return 附近的Check;
    }




    public Boolean Operation(String StringInput) {
        Boolean operation = false;
        for (int i = 0; i < KeyDictionary.Operation.length; i++) {
            if (StringInput.indexOf(KeyDictionary.Operation[i]) != -1)
                operation = true;
        }
        return operation;
    }

    public Boolean Operation2(String StringInput){
        Boolean operation = false;
        for (int i = 0; i < KeyDictionary.Operation2.length; i++) {
            if (StringInput.indexOf(KeyDictionary.Operation2[i]) != -1)
                operation = true;
        }
        return operation;
    }


    public String calculationYet(String StringInput) {
        String operation = "";
        for (int i = 0; i < KeyDictionary.Operation.length; i++) {
            if (StringInput.indexOf(KeyDictionary.Operation[i]) != -1) {
                int a = StringInput.indexOf(KeyDictionary.Operation[i]) + KeyDictionary.Operation[i].length();
                int b = StringInput.length();
                operation = StringInput.substring(a, b)
                        .replace("加", "+").replace("家", "+").replace("教", "+")
                        .replace("減", "-").replace("簡", "-").replace("件", "-").replace("錢", "-")
                        .replace("乘", "*").replace("成語", "*").replace("陳", "*").replace("成", "*")
                        .replace("除", "/").replace("著", "/").replace("出", "/").replace("錯", "/");
                break;
            }
        }
        return operation;
    }

    //計算機, 回傳結果
    public String calculation(String StringInput) {
        String Operation = "";


        for (int i = 0; i < KeyDictionary.Operation.length; i++) {
            if (StringInput.indexOf(KeyDictionary.Operation[i]) != -1) {
                Operation = StringInput.replace("等於", "").replace("多少", "");

            }
        }

        for (int i = 0; i < KeyDictionary.Operation.length; i++) {
            if (StringInput.indexOf(KeyDictionary.Operation[i]) != -1) {
                int a = StringInput.indexOf(KeyDictionary.Operation[i]) + KeyDictionary.Operation[i].length();
                int b = StringInput.length();
                Operation = StringInput.substring(a, b)
                        .replace("加", "+").replace("家", "+").replace("教", "+")
                        .replace("減", "-").replace("簡", "-").replace("件", "-").replace("錢", "-")
                        .replace("乘", "*").replace("成語", "*").replace("陳", "*").replace("成", "*")
                        .replace("除", "/").replace("著", "/").replace("出", "/").replace("錯", "/");
                Operation=replace(Operation,KeyDictionary.nums_1,KeyWordDictionary.num_1);
                Operation=replace(Operation,KeyDictionary.nums_2,KeyWordDictionary.num_2);
                Operation=replace(Operation,KeyDictionary.nums_3,KeyWordDictionary.num_3);
                Operation=replace(Operation,KeyDictionary.nums_4,KeyWordDictionary.num_4);
                Operation=replace(Operation,KeyDictionary.nums_5,KeyWordDictionary.num_5);
                Operation=replace(Operation,KeyDictionary.nums_6,KeyWordDictionary.num_6);
                Operation=replace(Operation,KeyDictionary.nums_7,KeyWordDictionary.num_7);
                Operation=replace(Operation,KeyDictionary.nums_8,KeyWordDictionary.num_8);
                Operation=replace(Operation,KeyDictionary.nums_9,KeyWordDictionary.num_9);
                Operation=replace(Operation,KeyDictionary.nums_10,KeyWordDictionary.num_10);
                Operation=replace(Operation,KeyDictionary.nums_100,KeyWordDictionary.num_100);
                Operation=replace(Operation,KeyDictionary.nums_1000,KeyWordDictionary.num_1000);
                Operation=replace(Operation,KeyDictionary.nums_10000,KeyWordDictionary.num_10000);
                break;
            }
        }

        ArrayList<String> strlist = new ArrayList<>();
        for (int i = 0; i < Operation.length(); i++) {
            strlist.add(String.valueOf(Operation.charAt(i)));
        }
        boolean ifop = false;
        while (!ifop) {
            for (int i = 0; i < strlist.size(); i++) {
                if ("*".equals(strlist.get(i))) {
                    strlist.set(i, String.format("%f", (Double.parseDouble(strlist.get(i - 1)) * Double.parseDouble(strlist.get(i + 1)))));
                    strlist.remove(i + 1);
                    strlist.remove(i - 1);
                    break;
                }
                if ("/".equals(strlist.get(i))) {
                    strlist.set(i, String.format("%f", (Double.parseDouble(strlist.get(i - 1)) / Double.parseDouble(strlist.get(i + 1)))));
                    strlist.remove(i + 1);
                    strlist.remove(i - 1);
                    break;
                }
                if (i == strlist.size() - 1) {
                    ifop = true;
                }
            }
        }
        ifop = false;
        while (!ifop) {
            for (int i = 0; i < strlist.size(); i++) {
                if ("+".equals(strlist.get(i))) {
                    strlist.set(i, String.format("%f", (Double.parseDouble(strlist.get(i - 1)) + Double.parseDouble(strlist.get(i + 1)))));
                    strlist.remove(i + 1);
                    strlist.remove(i - 1);
                    break;
                }
                if ("-".equals(strlist.get(i))) {
                    strlist.set(i, String.format("%f", (Double.parseDouble(strlist.get(i - 1)) - Double.parseDouble(strlist.get(i + 1)))));
                    strlist.remove(i + 1);
                    strlist.remove(i - 1);
                    break;
                }
                if (i == strlist.size() - 1) {
                    ifop = true;
                }
            }
        }
        Double ansres = Double.parseDouble(strlist.get(0));
        if (ansres % 1 == 0) {
            return String.format("%.0f", ansres);
        } else {
            return String.format("%.2f", ansres);
        }

    }

    public String calculation2(String StringInput){
        String input=StringInput;
        input=replace(input,KeyDictionary.nums_1,KeyWordDictionary.num_1);
        input=replace(input,KeyDictionary.nums_2,KeyWordDictionary.num_2);
        input=replace(input,KeyDictionary.nums_3,KeyWordDictionary.num_3);
        input=replace(input,KeyDictionary.nums_4,KeyWordDictionary.num_4);
        input=replace(input,KeyDictionary.nums_5,KeyWordDictionary.num_5);
        input=replace(input,KeyDictionary.nums_6,KeyWordDictionary.num_6);
        input=replace(input,KeyDictionary.nums_7,KeyWordDictionary.num_7);
        input=replace(input,KeyDictionary.nums_8,KeyWordDictionary.num_8);
        input=replace(input,KeyDictionary.nums_9,KeyWordDictionary.num_9);
        input=replace(input,KeyDictionary.nums_10,KeyWordDictionary.num_10);
        input=replace(input,KeyDictionary.nums_100,KeyWordDictionary.num_100);
        input=replace(input,KeyDictionary.nums_1000,KeyWordDictionary.num_1000);
        input=replace(input,KeyDictionary.nums_10000,KeyWordDictionary.num_10000);
        //將字串內非數字用空質取代
        String[] strlistres=input.trim().split("\\D");
        ArrayList<String> strlist=new ArrayList<String>();
        for(int i=0;i<strlistres.length;i++){
            if(!"".equals(strlistres[i]))
                strlist.add(strlistres[i]);
        }
        double ansres=0;
        if(strlist.size()==3){
            ansres=(Integer.parseInt(strlist.get(1)))/(Integer.parseInt(strlist.get(0)))*(Integer.parseInt(strlist.get(2)));
        }else if(strlist.size()==2){
            ansres=(Integer.parseInt(strlist.get(0)))*(Integer.parseInt(strlist.get(1)));
        }

        if(ansres%1==0){
            return String.format("%.0f",ansres);
        }else{
            return String.format("%.2f",ansres);
        }
    }

    private String replace(String input,String[] check,String replace){
        String response=input;
        for (String i:check){
            response=response.replace(i,replace);
        }
        return response;
    }

    //等待用的方法
    public void Wait(int waitTime) {
        try {
            Thread.sleep(waitTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //產生totalRandom個數的亂數整數
    public int randomNum(int totalRandom) {
        totalRandom = (int) (Math.random() * totalRandom) + 1;
        return totalRandom;
    }


}
