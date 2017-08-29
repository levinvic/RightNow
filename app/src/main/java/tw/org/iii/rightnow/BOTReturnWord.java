package tw.org.iii.rightnow;

import java.text.SimpleDateFormat;
import java.util.Calendar;



//此區專寫BOT回應在對話框上面的文字,以及回答時的動作

class BOTReturnWord {

    static String ResStr(String StringInput) {
        //取得系統時間物件
        Calendar cl = Calendar.getInstance();
        //BOT回應在View上面的文字
        String BotResponseWordOnView = "";

        /*
         *APP開啓
         */
        if (new BOTActivityEvent().Line(StringInput)) {
            BotResponseWordOnView = "賴已開啓";
            Live2dTop.live2DMgr.TouchEvent(PackDictionary.就這樣);

        } else if (new BOTActivityEvent().Facebook(StringInput)) {
            BotResponseWordOnView = "臉書開了";
            Live2dTop.live2DMgr.TouchEvent(PackDictionary.就這樣);

        } else if (new BOTActivityEvent().Album(StringInput)) {
            BotResponseWordOnView = "幫您開啓相簿";
            Live2dTop.live2DMgr.TouchEvent(PackDictionary.就這樣);

        } else if (new BOTActivityEvent().Gmail(StringInput)) {
            BotResponseWordOnView = "居妹兒已開啓";
            Live2dTop.live2DMgr.TouchEvent(PackDictionary.就這樣);

        } else if (new BOTActivityEvent().民視(StringInput)) {
            BotResponseWordOnView = "已經幫您開啓民視直播";
            Live2dTop.live2DMgr.TouchEvent(PackDictionary.就這樣);

        } else if (new BOTActivityEvent().台視(StringInput)) {
            BotResponseWordOnView = "已經幫您開啓台視直播";
            Live2dTop.live2DMgr.TouchEvent(PackDictionary.就這樣);

        } else if (new BOTActivityEvent().華視(StringInput)) {
            BotResponseWordOnView = "已經幫您開啓華視直播";
            Live2dTop.live2DMgr.TouchEvent(PackDictionary.就這樣);

        } else if (new BOTActivityEvent().中視(StringInput)) {
            BotResponseWordOnView = "已經幫您開啓中視直播";
            Live2dTop.live2DMgr.TouchEvent(PackDictionary.就這樣);

        } else if (new BOTActivityEvent().東森(StringInput)) {
            BotResponseWordOnView = "已經幫您開啓東森直播";
            Live2dTop.live2DMgr.TouchEvent(PackDictionary.就這樣);

        } else if (new BOTActivityEvent().滾石老歌(StringInput)) {
            BotResponseWordOnView = "每個人心中都有一首滾石老歌";
            Live2dTop.live2DMgr.TouchEvent(PackDictionary.就這樣);

        } else if (new BOTActivityEvent().中天(StringInput)) {
            BotResponseWordOnView = "幫你開中天直播";
            Live2dTop.live2DMgr.TouchEvent(PackDictionary.就這樣);

        } else if (new BOTActivityEvent().公視(StringInput)) {
            BotResponseWordOnView = "已經幫你開啓公視";
            Live2dTop.live2DMgr.TouchEvent(PackDictionary.就這樣);

        } else if (new BOTActivityEvent().三立(StringInput)) {
            BotResponseWordOnView = "已經幫你開啓三立直播";
            Live2dTop.live2DMgr.TouchEvent(PackDictionary.就這樣);

        } else if (new BOTActivityEvent().Ptt(StringInput)) {
            BotResponseWordOnView = "開啓Ptt中, 請稍等";
            Live2dTop.live2DMgr.TouchEvent(PackDictionary.就這樣);

        } else if (new BOTActivityEvent().Messenger(StringInput)) {
            BotResponseWordOnView = "幫您開啓臉書聊天室";
            Live2dTop.live2DMgr.TouchEvent(PackDictionary.就這樣);

        } else if (new BOTActivityEvent().Chrome(StringInput)) {
            BotResponseWordOnView = "幫您開啓Chrome瀏覽器";
            Live2dTop.live2DMgr.TouchEvent(PackDictionary.就這樣);

        } else if (new BOTActivityEvent().Weather(StringInput)) {
            BotResponseWordOnView = "幫您開啓內建氣象APP";
            Live2dTop.live2DMgr.TouchEvent(PackDictionary.就這樣);

        } else if (new BOTActivityEvent().GoogleMap(StringInput)) {
            BotResponseWordOnView = "幫您開啓內建谷歌地圖";
            Live2dTop.live2DMgr.TouchEvent(PackDictionary.就這樣);

        } else if (new BOTActivityEvent().Skype(StringInput)) {
            BotResponseWordOnView = "正在開啓Skype, 請稍候";
            Live2dTop.live2DMgr.TouchEvent(PackDictionary.就這樣);

        } else if (new BOTActivityEvent().Calendar(StringInput)) {
            BotResponseWordOnView = "幫您開啓內建行事曆";
            Live2dTop.live2DMgr.TouchEvent(PackDictionary.就這樣);

        } else if (new BOTActivityEvent().Youtube(StringInput)) {
            BotResponseWordOnView = "Youtube開啓中, 請稍等";
            Live2dTop.live2DMgr.TouchEvent(PackDictionary.就這樣);

        } else if (new BOTActivityEvent().Twitch(StringInput)) {
            BotResponseWordOnView = "幫你開啓Twitch遊戲直播";
            Live2dTop.live2DMgr.TouchEvent(PackDictionary.就這樣);

        } else if (new BOTActivityEvent().Twitter(StringInput)) {
            BotResponseWordOnView = "推特開啓中, 請稍等";
            Live2dTop.live2DMgr.TouchEvent(PackDictionary.就這樣);

        } else if (new BOTActivityEvent().Draw(StringInput)) {
            BotResponseWordOnView = "幫你開啓內建繪圖";
            Live2dTop.live2DMgr.TouchEvent(PackDictionary.就這樣);

        } else if (new BOTActivityEvent().GoogleMapGuideCheck(StringInput)) {
            BotResponseWordOnView = "幫你導引至" +
                    new BOTActivityEvent().GoogleMapGuide(StringInput) + "目的地";
            Live2dTop.live2DMgr.TouchEvent(PackDictionary.就這樣);

        } else if (new BOTActivityEvent().附近的Check(StringInput)) {
            BotResponseWordOnView = "已幫你找到附近" + new BOTActivityEvent().附近的(StringInput) + "的位置";
            Live2dTop.live2DMgr.TouchEvent(PackDictionary.就這樣);
        }



        /*
         *詢問
         */

        else if (StringInput.indexOf("早餐") != -1
                || StringInput.indexOf("早點") != -1
                ) {
            if (new BOTActivityEvent().NowTime(4, 10))
                BotResponseWordOnView = "早餐想吃什麼, 火腿三明治或許是不錯的選擇";
            else
                BotResponseWordOnView = "現在還不是吃早餐的時間";
        } else if (StringInput.indexOf("午餐") != -1
                || StringInput.indexOf("中餐") != -1
                ) {
            if (new BOTActivityEvent().NowTime(10, 14))
                BotResponseWordOnView = "午餐想吃什麼呢";
            else
                BotResponseWordOnView = "現在不是吃午餐的時間";
        } else if (StringInput.indexOf("晚餐") != -1) {
            if (new BOTActivityEvent().NowTime(17, 21))
                BotResponseWordOnView = "是該吃晚餐了, 想吃點什麼呢";
            else
                BotResponseWordOnView = "還沒到晚餐的時間, 你肚子已經餓了嗎";
        } else if (StringInput.indexOf("宵夜") != -1) {
            if (new BOTActivityEvent().NowTime(21, 24))
                BotResponseWordOnView = "吃宵夜對身體不好喔, 不過肚子餓的話, 我還是可以幫你找找";
            else if (new BOTActivityEvent().NowTime(0, 4))
                BotResponseWordOnView = "吃宵夜對身體不好喔, 不過肚子餓的話我還是可以幫你找找";
            else
                BotResponseWordOnView = "現在不是吃宵夜的時間";
        } else if (StringInput.indexOf("餓") != -1 || StringInput.indexOf("餐") != -1) {

            if (new BOTActivityEvent().NowTime(0, 5)) {
                BotResponseWordOnView = "吃宵夜對身體不好喔, 不過肚子餓的話我還是可以幫你找找";

            } else if (new BOTActivityEvent().NowTime(5, 11)) {
                BotResponseWordOnView = "是時候吃中餐了";
            } else if (new BOTActivityEvent().NowTime(11, 15)) {
                BotResponseWordOnView = "請問您要吃早餐嗎? 你想要中式還是西式的?";
            } else if (new BOTActivityEvent().NowTime(15, 24)) {
                BotResponseWordOnView = "好吧, 我幫你在附近找點吃的";
            }
        }

        /*
         *查詢
         */
        else if (StringInput.indexOf("現在時間") != -1) {
            SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日 HH:mm");
            String str = sdf.format(cl.getTime());
            BotResponseWordOnView = "現在時間" + str;

        }
        else if (new BOTActivityEvent().發票(StringInput)) {
            BotResponseWordOnView = new BOTActivityEvent().Recipt();

        }
        else if (new BOTActivityEvent().卡提諾(StringInput)) {
            BotResponseWordOnView = "卡提諾九四狂";

        }
        else if (new BOTActivityEvent().前往對獎(StringInput)) {
            BotResponseWordOnView = "已經幫您開啓自動對號網頁";

        }
        else if (StringInput.indexOf("跟我說") != -1) {
            //回應一樣的文字
            BotResponseWordOnView = StringInput.replace("跟我說", "").replace("跟我講", "");

        }

        //二次判斷

          else if (new BOTActivityEvent().我要看(StringInput)) {
            //youtube
            BotResponseWordOnView = "在Youtube上幫你搜尋" +
                    new BOTActivityEvent().YoutubeSearch(StringInput) + "相關影片";

        } else if (new BOTActivityEvent().開Chrome搜尋(StringInput)) {
            //Chrome
            BotResponseWordOnView = "幫你在谷歌收尋" + new BOTActivityEvent().ChromeSearch(StringInput) + "相關資料";

        } else if (new BOTActivityEvent().維基(StringInput)) {
            //維基
            BotResponseWordOnView = "幫你在維基百科找尋相關資料";

        } else if (new BOTActivityEvent().打電話給(StringInput)) {
            //打電話
            BotResponseWordOnView = "幫你在電話簿搜尋中";

        } else if (new BOTActivityEvent().台語(StringInput)) {
            //台語
            BotResponseWordOnView = "已切換為台語模式";

        } else if (new BOTActivityEvent().國語(StringInput)) {
            //google語音
            BotResponseWordOnView = "已切換為國語模式";

        } else if (new BOTActivityEvent().小女孩(StringInput)) {
            //小女孩
            BotResponseWordOnView = "已切換為小女孩模式";

        }


        /*
         *日常招呼
         */
        else if (StringInput.indexOf("你好") != -1) {
            BotResponseWordOnView = "你好阿";
        } else if (StringInput.indexOf("真可愛") != -1) {
            BotResponseWordOnView = "謝謝";
        } else if (StringInput.indexOf("晚上好") != -1) {
            BotResponseWordOnView = "月色很美";
        } else if (StringInput.indexOf("早安") != -1) {
            BotResponseWordOnView = "早安, 今天也是元氣的一天!";
        } else if (StringInput.indexOf("晚安") != -1) {
            BotResponseWordOnView = "晚安, 祝你有個美好的夜晚!";
        } else if (StringInput.indexOf("嫁給我") != -1) {
            BotResponseWordOnView = "好的, 雖然我想這麼說但現在不是時候";
        } else if (StringInput.indexOf("無聊") != -1) {
            BotResponseWordOnView = "要我唱歌給你聽嗎";
        } else if (StringInput.indexOf("唱歌") != -1) {
            BotResponseWordOnView = "不好吧, 我會不好意思";
        } else if (StringInput.indexOf("三圍") != -1) {
            BotResponseWordOnView = "不告訴你...";
        } else if (new BOTActivityEvent().Operation(StringInput)) {
            //計算機
            BotResponseWordOnView = new BOTActivityEvent().calculationYet(StringInput)
                    + " ,我算出來的結果是\"" + new BOTActivityEvent().calculation(StringInput) + "\" ,我厲害吧";
        }else if (new BOTActivityEvent().Operation2(StringInput)) {
            //計算機2
            BotResponseWordOnView = StringInput
                    + " ,我算出來的結果是\"" + new BOTActivityEvent().calculation2(StringInput) + "\" 元,我厲害吧";
        }

        /*
         *其他
         */
        else {
            BotResponseWordOnView = "不好意思, 我沒聽懂[" + StringInput + "], 可以再說一次嗎?";
        }
        return BotResponseWordOnView;
    }
}
