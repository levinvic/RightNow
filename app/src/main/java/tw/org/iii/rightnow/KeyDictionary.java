package tw.org.iii.rightnow;

/**
 * Created by jr760 on 2017/8/7.
 */

//此區為SearchWord要判定KeyWord有什麼相關字詞

class KeyDictionary {

    //自訂程式
    static final String[] tips = {"你會什麼", "你會做什麼", "提示", "tip", "功能", "你可以幫我什麼", "說明", "哈囉", "哈嚕",
            "跟我講話", "Sony", "羅敏", "你還會什麼", "你還會做什麼"};
    static final String[] Operation = {"幫我算", "泡菜", "Watson", "花生", "哇省", "防衛省", "蘭花繩", "幫我省"};
    static final String[] Operation2 = {"多少錢", "多少"};
    static final String[] 故事 = {"故事", "郭書", "讀書", "gosu", "二叔", "溝鼠", "購書", "過數", "複數", "布書", "讀書", "告書"};
    static final String[] 打電話給 = {"打電話給", "打電話", "打給"};
    //內建APP
    static final String[] Album = {"相簿", "相片", "照片", "畫像皮", "橡皮", "用品", "臭屁", "送筆",
            "威秀", "線筆", "Photoshop", "雄鼻", "跨下皮", "跨向品"};
    static final String[] Line = {"LINE", "Line", "line", "賴", "goodnight", "無奈", "Green light", "vinai", "拍賣"};
    static final String[] Facebook = {"臉書", "Facebook", "非死不可", "FB"};
    static final String[] Chrome = {"Chrome", "瀏覽器", "福隆", "CtiTV", "儘量Q"};
    static final String[] Draw = {"畫畫", "繪圖", "會抖", "威德", "尾抖password4pa", "鮪頭", "阿斗", "味道", "回頭"};
    static final String[] Weather = {"天氣", "下雨", "Weather"};
    static final String[] Gmail = {"寄信", "Gmail", "email", "Email", "依妹兒", "亞培", "家輝", "價位",
            "調推", "校徽", "架推", "家規", "耀煇"};
    static final String[] Youtube = {"Youtube", "看影片", "八點檔", "看電視", "點戲", "華亞科",
            "free sexy", "vnc", "龜丹溪", "creadence", "揮電機", "點心", "freedom seed"};
    static final String[] GoogleMap = {"地圖", "北投", "貝多", "北斗", "帶刀", "weedle", "griddle"};
    static final String[] Skype = {"Skype", "蘇該逼"};
    static final String[] Twitch = {"Twitch", "遊戲直播", "Twins"};
    static final String[] Calendar = {"行事曆", "日曆", "月份", "UT", "Calendar"};
    static final String[] Ptt = {"PTT", "ptt", "Ptt", "批踢踢", "鄉民"};
    static final String[] Twitter = {"Twitter", "推特", "printer", "音特"};
    static final String[] Messenger = {"fb訊息", "Messenger", "fb聊天", "fb聊天室"};
    //開Youtube的
    static final String[] 民視 = {"民視", "冰系", "菲律賓西", "win7", "灰林溪"};
    static final String[] 中視 = {"中視", "東西", "不一定西", "中西", "call c"};
    static final String[] 華視 = {"華視", "花溪", "花絮"};
    static final String[] 台視 = {"台視", "大安溪", "黛西", "袋戲", "代戲"};
    static final String[] 公視 = {"公視"};
    static final String[] 東森 = {"東森"};
    static final String[] 三立 = {"三立"};
    static final String[] 中天 = {"中天"};
    static final String[] 滾石老歌 = {"老歌", "滾石老歌"};
    static final String[] 我要看 = {"我要看", "我想看"};
    //爬蟲
    static final String[] 大福彩 = {"大福彩"};
    static final String[] 威力彩 = {"威力彩"};
    static final String[] 大樂透 = {"大樂透"};
    static final String[] 發票 = {"發票"};
    //開瀏覽器
    static final String[] 開Chrome搜尋 = {"幫我找", "幫我查", "我想找", "我想查", "我要查", "我要找", "澎湖找"};
    static final String[] 卡提諾 = {"卡提諾"};
    static final String[] 維基 = {"是什麼", "什麼是"};
    static final String[] 前往對獎 = {"前往對獎"};
    //語音切換
    static final String[] 台語 = {"台語"};
    static final String[] 國語 = {"國語"};
    static final String[] 小女孩 = {"小女孩"};

    //開Google導覽
    static final String[] GoogleMapGuide = {"怎麼走", "怎麼去", "帶我去", "帶我到", "我想去", "我想到", "我要去", "我要到"};
    static final String[] 附近的 = {"附近的", "附近", "周遭的", "鄰近的", "找附近", "幫我找找"};

    //文字轉數字用
    static final String[] nums_1 = {"一", "壹"};
    static final String[] nums_2 = {"二", "兩", "貳"};
    static final String[] nums_3 = {"三", "參"};
    static final String[] nums_4 = {"四", "是", "肆"};
    static final String[] nums_5 = {"五", "虎", "伍"};
    static final String[] nums_6 = {"六", "陸"};
    static final String[] nums_7 = {"七", "柒"};
    static final String[] nums_8 = {"八", "吧", "捌"};
    static final String[] nums_9 = {"九", "玖"};
    static final String[] nums_10 = {"十", "拾", "什", "石"};
    static final String[] nums_100 = {"一百"};
    static final String[] nums_1000 = {"千", "仟"};
    static final String[] nums_10000 = {"一萬"};
}
