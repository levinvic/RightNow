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
            Live2dTop.live2DMgr.TouchEvent(PackDictionary.就這樣);
            BotResponseWordOnView = "賴已開啓";

        } else if (new BOTActivityEvent().Facebook(StringInput)) {
            Live2dTop.live2DMgr.TouchEvent(PackDictionary.就這樣);
            BotResponseWordOnView = "臉書開了";

        } else if (new BOTActivityEvent().Album(StringInput)) {
            Live2dTop.live2DMgr.TouchEvent(PackDictionary.就這樣);
            BotResponseWordOnView = "幫您開啓相簿";

        } else if (new BOTActivityEvent().Gmail(StringInput)) {
            Live2dTop.live2DMgr.TouchEvent(PackDictionary.就這樣);
            BotResponseWordOnView = "居妹兒已開啓";

        } else if (new BOTActivityEvent().民視(StringInput)) {
            Live2dTop.live2DMgr.TouchEvent(PackDictionary.就這樣);
            BotResponseWordOnView = "已經幫您開啓民視直播";

        } else if (new BOTActivityEvent().台視(StringInput)) {
            Live2dTop.live2DMgr.TouchEvent(PackDictionary.就這樣);
            BotResponseWordOnView = "已經幫您開啓台視直播";

        } else if (new BOTActivityEvent().華視(StringInput)) {
            Live2dTop.live2DMgr.TouchEvent(PackDictionary.就這樣);
            BotResponseWordOnView = "已經幫您開啓華視直播";

        } else if (new BOTActivityEvent().中視(StringInput)) {
            Live2dTop.live2DMgr.TouchEvent(PackDictionary.就這樣);
            BotResponseWordOnView = "已經幫您開啓中視直播";

        } else if (new BOTActivityEvent().東森(StringInput)) {
            Live2dTop.live2DMgr.TouchEvent(PackDictionary.就這樣);
            BotResponseWordOnView = "已經幫您開啓東森直播";

        } else if (new BOTActivityEvent().滾石老歌(StringInput)) {
            Live2dTop.live2DMgr.TouchEvent(PackDictionary.就這樣);
            BotResponseWordOnView = "每個人心中都有一首滾石老歌";

        } else if (new BOTActivityEvent().中天(StringInput)) {
            Live2dTop.live2DMgr.TouchEvent(PackDictionary.就這樣);
            BotResponseWordOnView = "幫你開中天直播";

        } else if (new BOTActivityEvent().公視(StringInput)) {
            Live2dTop.live2DMgr.TouchEvent(PackDictionary.就這樣);
            BotResponseWordOnView = "已經幫你開啓公視";

        } else if (new BOTActivityEvent().三立(StringInput)) {
            Live2dTop.live2DMgr.TouchEvent(PackDictionary.就這樣);
            BotResponseWordOnView = "已經幫你開啓三立直播";

        } else if (new BOTActivityEvent().Ptt(StringInput)) {
            Live2dTop.live2DMgr.TouchEvent(PackDictionary.就這樣);
            BotResponseWordOnView = "開啓Ptt中, 請稍等";

        } else if (new BOTActivityEvent().Messenger(StringInput)) {
            Live2dTop.live2DMgr.TouchEvent(PackDictionary.就這樣);
            BotResponseWordOnView = "幫您開啓臉書聊天室";

        } else if (new BOTActivityEvent().Chrome(StringInput)) {
            Live2dTop.live2DMgr.TouchEvent(PackDictionary.就這樣);
            BotResponseWordOnView = "幫您開啓Chrome瀏覽器";

        } else if (new BOTActivityEvent().Weather(StringInput)) {
            Live2dTop.live2DMgr.TouchEvent(PackDictionary.就這樣);
            BotResponseWordOnView = "幫您開啓內建氣象APP";

        } else if (new BOTActivityEvent().GoogleMap(StringInput)) {
            Live2dTop.live2DMgr.TouchEvent(PackDictionary.就這樣);
            BotResponseWordOnView = "幫您開啓內建谷歌地圖";

        } else if (new BOTActivityEvent().Skype(StringInput)) {

            Live2dTop.live2DMgr.TouchEvent(PackDictionary.就這樣);
            BotResponseWordOnView = "正在開啓Skype, 請稍候";


        } else if (new BOTActivityEvent().Calendar(StringInput)) {
            Live2dTop.live2DMgr.TouchEvent(PackDictionary.就這樣);
            BotResponseWordOnView = "幫您開啓內建行事曆";


        } else if (new BOTActivityEvent().Youtube(StringInput)) {
            Live2dTop.live2DMgr.TouchEvent(PackDictionary.就這樣);
            BotResponseWordOnView = "Youtube開啓中, 請稍等";


        } else if (new BOTActivityEvent().Twitch(StringInput)) {
            Live2dTop.live2DMgr.TouchEvent(PackDictionary.就這樣);
            BotResponseWordOnView = "幫你開啓Twitch遊戲直播";

        } else if (new BOTActivityEvent().Twitter(StringInput)) {
            Live2dTop.live2DMgr.TouchEvent(PackDictionary.就這樣);
            BotResponseWordOnView = "推特開啓中, 請稍等";

        } else if (new BOTActivityEvent().Draw(StringInput)) {
            Live2dTop.live2DMgr.TouchEvent(PackDictionary.就這樣);
            BotResponseWordOnView = "幫你開啓內建繪圖";

        } else if (new BOTActivityEvent().GoogleMapGuideCheck(StringInput)) {
            Live2dTop.live2DMgr.TouchEvent(PackDictionary.就這樣);
            BotResponseWordOnView = "幫你導引至" +
                    new BOTActivityEvent().GoogleMapGuide(StringInput) + "目的地";


        } else if (new BOTActivityEvent().附近的Check(StringInput)) {
            Live2dTop.live2DMgr.TouchEvent(PackDictionary.就這樣);
            BotResponseWordOnView = "已幫你找到附近" + new BOTActivityEvent().附近的(StringInput) + "的位置";

        }


        /*
         *詢問
         */

        else if (StringInput.indexOf("早餐") != -1
                || StringInput.indexOf("早點") != -1
                ) {
            if (new BOTActivityEvent().NowTime(4, 10)) {
                Live2dTop.live2DMgr.TouchEvent(PackDictionary.就這樣);
                BotResponseWordOnView = "早餐想吃什麼, 火腿三明治或許是不錯的選擇";

            } else {
                Live2dTop.live2DMgr.TouchEvent(PackDictionary.搖頭);
                BotResponseWordOnView = "現在還不是吃早餐的時間";

            }

        } else if (StringInput.indexOf("午餐") != -1
                || StringInput.indexOf("中餐") != -1
                ) {
            if (new BOTActivityEvent().NowTime(10, 14)) {
                Live2dTop.live2DMgr.TouchEvent(PackDictionary.思考);
                BotResponseWordOnView = "午餐想吃什麼呢";

            } else {
                Live2dTop.live2DMgr.TouchEvent(PackDictionary.搖頭);
                BotResponseWordOnView = "現在不是吃午餐的時間";

            }
        } else if (StringInput.indexOf("晚餐") != -1) {
            if (new BOTActivityEvent().NowTime(17, 21)) {
                Live2dTop.live2DMgr.TouchEvent(PackDictionary.就這樣);
                BotResponseWordOnView = "是該吃晚餐了, 想吃點什麼呢";

            } else {
                Live2dTop.live2DMgr.TouchEvent(PackDictionary.嚇到);
                BotResponseWordOnView = "還沒到晚餐的時間, 你肚子已經餓了嗎";

            }
        } else if (StringInput.indexOf("宵夜") != -1) {
            if (new BOTActivityEvent().NowTime(21, 24)) {
                BotResponseWordOnView = "吃宵夜對身體不好喔, 不過肚子餓的話, 我還是可以幫你找找";
                Live2dTop.live2DMgr.TouchEvent(PackDictionary.思考);
            } else if (new BOTActivityEvent().NowTime(0, 4)) {
                BotResponseWordOnView = "吃宵夜對身體不好喔, 不過肚子餓的話我還是可以幫你找找";
                Live2dTop.live2DMgr.TouchEvent(PackDictionary.思考);
            } else {
                BotResponseWordOnView = "現在不是吃宵夜的時間";
                Live2dTop.live2DMgr.TouchEvent(PackDictionary.搖頭);
            }
        } else if (StringInput.indexOf("餓") != -1 || StringInput.indexOf("餐") != -1) {

            if (new BOTActivityEvent().NowTime(0, 5)) {
                BotResponseWordOnView = "吃宵夜對身體不好喔, 不過肚子餓的話我還是可以幫你找找";
                Live2dTop.live2DMgr.TouchEvent(PackDictionary.就這樣);
            } else if (new BOTActivityEvent().NowTime(5, 11)) {
                BotResponseWordOnView = "是時候吃中餐了";
                Live2dTop.live2DMgr.TouchEvent(PackDictionary.點頭);
            } else if (new BOTActivityEvent().NowTime(11, 15)) {
                BotResponseWordOnView = "請問您要吃早餐嗎? 要我幫你找附近早餐店嗎?";
                Live2dTop.live2DMgr.TouchEvent(PackDictionary.需要幫忙嗎);
            } else if (new BOTActivityEvent().NowTime(15, 24)) {
                BotResponseWordOnView = "好吧, 我幫你在附近找點吃的";
                Live2dTop.live2DMgr.TouchEvent(PackDictionary.嚇到);
            }
        }

        /*
         *查詢
         */
        else if (StringInput.indexOf("現在時間") != -1) {
            SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日 HH:mm");
            String str = sdf.format(cl.getTime());
            BotResponseWordOnView = "現在時間" + str;
            Live2dTop.live2DMgr.TouchEvent(PackDictionary.拍腿);
        } else if (new BOTActivityEvent().發票(StringInput)) {
            BotResponseWordOnView = new BOTActivityEvent().Recipt();
            Live2dTop.live2DMgr.TouchEvent(PackDictionary.就這樣);
        } else if (new BOTActivityEvent().卡提諾(StringInput)) {
            BotResponseWordOnView = "卡提諾九四狂";
            Live2dTop.live2DMgr.TouchEvent(PackDictionary.就這樣);
        } else if (new BOTActivityEvent().前往對獎(StringInput)) {
            BotResponseWordOnView = "已經幫您開啓自動對號網頁";
            Live2dTop.live2DMgr.TouchEvent(PackDictionary.就這樣);
        } else if (StringInput.indexOf("跟我說") != -1) {
            //回應一樣的文字
            BotResponseWordOnView = StringInput.replace("跟我說", "").replace("跟我講", "");
            Live2dTop.live2DMgr.TouchEvent(PackDictionary.好高興);

        } else if (new BOTActivityEvent().我要看(StringInput)) {
            Live2dTop.live2DMgr.TouchEvent(PackDictionary.點頭);
            //youtube
            BotResponseWordOnView = "在Youtube上幫你搜尋" +
                    new BOTActivityEvent().YoutubeSearch(StringInput) + "相關影片";


        } else if (new BOTActivityEvent().開Chrome搜尋(StringInput)) {
            Live2dTop.live2DMgr.TouchEvent(PackDictionary.就這樣);
            //Chrome
            BotResponseWordOnView = "幫你在谷歌收尋" + new BOTActivityEvent().ChromeSearch(StringInput) + "相關資料";


        } else if (new BOTActivityEvent().維基(StringInput)) {
            Live2dTop.live2DMgr.TouchEvent(PackDictionary.思考);
            //維基
            BotResponseWordOnView = "幫你在維基百科找尋相關資料";


        } else if (new BOTActivityEvent().打電話給(StringInput)) {
            Live2dTop.live2DMgr.TouchEvent(PackDictionary.思考);
            //打電話
            BotResponseWordOnView = "幫你在電話簿搜尋中";


        } else if (new BOTActivityEvent().台語(StringInput)) {
            Live2dTop.live2DMgr.TouchEvent(PackDictionary.嚇到);
            //台語
            BotResponseWordOnView = "已切換為台語模式";

        } else if (new BOTActivityEvent().國語(StringInput)) {
            Live2dTop.live2DMgr.TouchEvent(PackDictionary.嚇到);
            //google語音
            BotResponseWordOnView = "已切換為國語模式";


        } else if (new BOTActivityEvent().小女孩(StringInput)) {
            Live2dTop.live2DMgr.TouchEvent(PackDictionary.嚇到);
            //小女孩
            BotResponseWordOnView = "已切換為小女孩模式";
        }


        /*
         *日常招呼
         */
        else if (StringInput.indexOf("可愛") != -1) {
            BotResponseWordOnView = "好高興喔";
            Live2dTop.live2DMgr.TouchEvent(PackDictionary.好高興);

        } else if (StringInput.indexOf("厲害") != -1
                || StringInput.indexOf("好棒") != -1
                ) {
            Live2dTop.live2DMgr.TouchEvent(PackDictionary.生氣);
            BotResponseWordOnView = "哼,哼,哼";
        } else if (StringInput.indexOf("瘋了") != -1) {
            Live2dTop.live2DMgr.TouchEvent(PackDictionary.嚇到);
            BotResponseWordOnView = "哼哼哼哼 顆顆顆顆 哈哈哈哈 嘿嘿嘿嘿 嘻嘻嘻嘻 嘎嘎嘎嘎 哇哇哇哇 磯磯磯磯 哦哦哦哦 呵呵呵呵 呼呼呼呼 齁齁齁齁 喔喔喔喔 咕咕咕咕";
        } else if (StringInput.indexOf("你好笨") != -1) {
            Live2dTop.live2DMgr.TouchEvent(PackDictionary.生氣);
            BotResponseWordOnView = "哼, 你才是笨蛋";

        } else if (StringInput.indexOf("你好") != -1) {
            Live2dTop.live2DMgr.TouchEvent(PackDictionary.點頭);
            BotResponseWordOnView = "你好阿";

        } else if (StringInput.indexOf("晚上好") != -1) {
            Live2dTop.live2DMgr.TouchEvent(PackDictionary.點頭);
            BotResponseWordOnView = "晚安啊";


        } else if (StringInput.indexOf("早安") != -1) {
            Live2dTop.live2DMgr.TouchEvent(PackDictionary.點頭);
            BotResponseWordOnView = "早安, 今天也是元氣的一天!";

        } else if (StringInput.indexOf("晚安") != -1) {
            Live2dTop.live2DMgr.TouchEvent(PackDictionary.點頭);
            BotResponseWordOnView = "晚安, 祝你有個美好的夜晚!";


        } else if (StringInput.indexOf("嫁給我") != -1) {
            Live2dTop.live2DMgr.TouchEvent(PackDictionary.搖頭);
            BotResponseWordOnView = "好的, 雖然我想這麼說但現在不是時候";

        } else if (StringInput.indexOf("無聊") != -1) {
            Live2dTop.live2DMgr.TouchEvent(PackDictionary.好高興);
            BotResponseWordOnView = "要我唱歌給你聽嗎";

        } else if (StringInput.indexOf("唱歌") != -1) {
            Live2dTop.live2DMgr.TouchEvent(PackDictionary.摸摸頭);
            BotResponseWordOnView = "不好吧, 我會不好意思";

        } else if (StringInput.indexOf("三圍") != -1) {
            Live2dTop.live2DMgr.TouchEvent(PackDictionary.生氣);
            BotResponseWordOnView = "不告訴你...";

        } else if (new BOTActivityEvent().Operation(StringInput)) {
            Live2dTop.live2DMgr.TouchEvent(PackDictionary.思考);
            //計算機
            BotResponseWordOnView = new BOTActivityEvent().calculationYet(StringInput)
                    + " ,我算出來的結果是\"" + new BOTActivityEvent().calculation(StringInput) + "\" ,我厲害吧";

        } else if (new BOTActivityEvent().Operation2(StringInput)) {
            Live2dTop.live2DMgr.TouchEvent(PackDictionary.思考);
            //計算機2
            BotResponseWordOnView = StringInput
                    + " ,我算出來的結果是\"" + new BOTActivityEvent().calculation2(StringInput) + "\" 元,我厲害吧";

        }
        //彩卷動作
        else if (new BOTActivityEvent().大樂透Check(StringInput)) {
            Live2dTop.live2DMgr.TouchEvent(PackDictionary.就這樣);
        } else if (new BOTActivityEvent().大福彩Check(StringInput)) {
            Live2dTop.live2DMgr.TouchEvent(PackDictionary.就這樣);
        } else if (new BOTActivityEvent().威力彩Check(StringInput)) {
            Live2dTop.live2DMgr.TouchEvent(PackDictionary.就這樣);
        }
        //說明
        else if(new BOTActivityEvent().說明Check(PackDictionary.tip)){
            Live2dTop.live2DMgr.TouchEvent(PackDictionary.就這樣);
        }
        /*
         *其他
         */
        else {
            Live2dTop.live2DMgr.TouchEvent(PackDictionary.搖頭);
            BotResponseWordOnView = "不好意思, 我沒聽懂[" + StringInput + "], 可以再說一次嗎?";
        }
        return BotResponseWordOnView;
    }
}
