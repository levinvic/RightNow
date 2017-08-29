package tw.org.iii.rightnow;


class SearchWord {

    static String SEARCH(String StringInput) {


        //這裡寫的是回應給Live2dTop.java判斷swtich關鍵字KeyWord用的, 跟BOT回應對話框無關
        //共同參考於 KeyDictionary.java


        //提示說明
        for (int i = 0; i < KeyDictionary.tips.length; i++) {
            if (StringInput.indexOf(KeyDictionary.tips[i]) != -1) {
                StringInput = KeyWordDictionary.tip;
            }
        }
        //GoogleMap
        for (int i=0;i<KeyDictionary.GoogleMapGuide.length;i++) {
            if (StringInput.indexOf(KeyDictionary.GoogleMapGuide[i]) != -1) {
                StringInput = KeyWordDictionary.GoogleMapGuide;
            }
        }
        //相簿
        for (int i=0;i<KeyDictionary.Album.length;i++) {
            if (StringInput.indexOf(KeyDictionary.Album[i]) != -1) {
                StringInput = KeyWordDictionary.相簿;
            }
        }
        //Line
        for (int i=0;i<KeyDictionary.Line.length;i++) {
            if (StringInput.indexOf(KeyDictionary.Line[i]) != -1) {
                StringInput = KeyWordDictionary.Line;
            }
        }
        //臉書
        for (int i=0;i<KeyDictionary.Facebook.length;i++) {
            if (StringInput.indexOf(KeyDictionary.Facebook[i]) != -1) {
                StringInput = KeyWordDictionary.Facebook;
            }
        }
        //開Chrome
        for (int i=0;i<KeyDictionary.Chrome.length;i++) {
            if (StringInput.indexOf(KeyDictionary.Chrome[i]) != -1) {
                StringInput = KeyWordDictionary.Chrome;
            }
        }
        //畫畫
        for (int i=0;i<KeyDictionary.Draw.length;i++) {
            if (StringInput.indexOf(KeyDictionary.Draw[i]) != -1) {
                StringInput = KeyWordDictionary.繪圖;
            }
        }
        //天氣
        for (int i=0;i<KeyDictionary.Weather.length;i++) {
            if (StringInput.indexOf(KeyDictionary.Weather[i]) != -1) {
                StringInput = KeyWordDictionary.天氣;
            }
        }
        //Gmail
        for (int i=0;i<KeyDictionary.Gmail.length;i++) {
            if (StringInput.indexOf(KeyDictionary.Gmail[i]) != -1) {
                StringInput = KeyWordDictionary.Gmail;
            }
        }
        //Youtube
        for (int i=0;i<KeyDictionary.Youtube.length;i++) {
            if (StringInput.indexOf(KeyDictionary.Youtube[i]) != -1) {
                StringInput = KeyWordDictionary.Youtube;
            }
        }
        //GoogleMap
        for (int i=0;i<KeyDictionary.GoogleMap.length;i++) {
            if (StringInput.indexOf(KeyDictionary.GoogleMap[i]) != -1) {
                StringInput = KeyWordDictionary.GoogleMap;
            }
        }
        //Skype
        for (int i=0;i<KeyDictionary.Skype.length;i++) {
            if (StringInput.indexOf(KeyDictionary.Skype[i]) != -1) {
                StringInput = KeyWordDictionary.Skype;
            }
        }
        //Twitch
        for (int i=0;i<KeyDictionary.Twitch.length;i++) {
            if (StringInput.indexOf(KeyDictionary.Twitch[i]) != -1) {
                StringInput = KeyWordDictionary.Twitch;
            }
        }
        //行事曆
        for (int i=0;i<KeyDictionary.Calendar.length;i++) {
            if (StringInput.indexOf(KeyDictionary.Calendar[i]) != -1) {
                StringInput = KeyWordDictionary.行事曆;
            }
        }
        //Ptt
        for (int i=0;i<KeyDictionary.Ptt.length;i++) {
            if (StringInput.indexOf(KeyDictionary.Ptt[i]) != -1) {
                StringInput = KeyWordDictionary.Ptt;
            }
        }
        //Twitter
        for (int i=0;i<KeyDictionary.Twitter.length;i++) {
            if (StringInput.indexOf(KeyDictionary.Twitter[i]) != -1) {
                StringInput = KeyWordDictionary.推持;
            }
        }
        //Messenger
        for (int i=0;i<KeyDictionary.Messenger.length;i++) {
            if (StringInput.indexOf(KeyDictionary.Messenger[i]) != -1) {
                StringInput = KeyWordDictionary.Messenger;
            }
        }
        //民視
        for (int i=0;i<KeyDictionary.民視.length;i++) {
            if (StringInput.indexOf(KeyDictionary.民視[i]) != -1) {
                StringInput = KeyWordDictionary.民視;
            }
        }
        //華視
        for (int i=0;i<KeyDictionary.華視.length;i++) {
            if (StringInput.indexOf(KeyDictionary.華視[i]) != -1) {
                StringInput = KeyWordDictionary.華視;
            }
        }
        //中視
        for (int i=0;i<KeyDictionary.中視.length;i++) {
            if (StringInput.indexOf(KeyDictionary.中視[i]) != -1) {
                StringInput = KeyWordDictionary.中視;
            }
        }
        //打電話給
        for (int i=0;i<KeyDictionary.打電話給.length;i++) {
            if (StringInput.indexOf(KeyDictionary.打電話給[i]) != -1) {
                StringInput = KeyWordDictionary.打電話;
            }
        }
        //滾石老歌
        for (int i=0;i<KeyDictionary.滾石老歌.length;i++) {
            if (StringInput.indexOf(KeyDictionary.滾石老歌[i]) != -1) {
                StringInput = KeyWordDictionary.滾石老歌;
            }
        }
        //中天
        for (int i=0;i<KeyDictionary.中天.length;i++) {
            if (StringInput.indexOf(KeyDictionary.中天[i]) != -1) {
                StringInput = KeyWordDictionary.中天;
            }
        }
        //三立
        for (int i=0;i<KeyDictionary.三立.length;i++) {
            if (StringInput.indexOf(KeyDictionary.三立[i]) != -1) {
                StringInput = KeyWordDictionary.三立;
            }
        }
        //東森
        for (int i=0;i<KeyDictionary.東森.length;i++) {
            if (StringInput.indexOf(KeyDictionary.東森[i]) != -1) {
                StringInput = KeyWordDictionary.東森;
            }
        }
        //公視
        for (int i=0;i<KeyDictionary.公視.length;i++) {
            if (StringInput.indexOf(KeyDictionary.公視[i]) != -1) {
                StringInput = KeyWordDictionary.公視;
            }
        }
        //台視
        for (int i=0;i<KeyDictionary.台視.length;i++) {
            if (StringInput.indexOf(KeyDictionary.台視[i]) != -1) {
                StringInput = KeyWordDictionary.台視;
            }
        }
        //故事
        for (int i=0;i<KeyDictionary.故事.length;i++) {
            if (StringInput.indexOf(KeyDictionary.故事[i]) != -1) {
                StringInput = KeyWordDictionary.故事;
            }
        }
        //大樂透
        for (int i=0;i<KeyDictionary.大樂透.length;i++) {
            if (StringInput.indexOf(KeyDictionary.大樂透[i]) != -1) {
                StringInput = KeyWordDictionary.大樂透;
            }
        }
        //發票
        for (int i=0;i<KeyDictionary.發票.length;i++) {
            if (StringInput.indexOf(KeyDictionary.發票[i]) != -1) {
                StringInput = KeyWordDictionary.本期發票;
            }
        }
        //前往對獎
        for (int i=0;i<KeyDictionary.前往對獎.length;i++) {
            if (StringInput.indexOf(KeyDictionary.前往對獎[i]) != -1) {
                StringInput = KeyWordDictionary.至對獎發票網頁;
            }
        }
        //大福彩
        for (int i=0;i<KeyDictionary.大福彩.length;i++) {
            if (StringInput.indexOf(KeyDictionary.大福彩[i]) != -1) {
                StringInput = KeyWordDictionary.大福彩;
            }
        }
        //威力彩
        for (int i=0;i<KeyDictionary.威力彩.length;i++) {
            if (StringInput.indexOf(KeyDictionary.威力彩[i]) != -1) {
                StringInput = KeyWordDictionary.威力彩;
            }
        }
        //卡提諾
        for (int i=0;i<KeyDictionary.卡提諾.length;i++) {
            if (StringInput.indexOf(KeyDictionary.卡提諾[i]) != -1) {
                StringInput = KeyWordDictionary.卡提諾;
            }
        }
        //開Chrome搜尋
        for (int i=0;i<KeyDictionary.開Chrome搜尋.length;i++) {
            if (StringInput.indexOf(KeyDictionary.開Chrome搜尋[i]) != -1) {
                StringInput = KeyWordDictionary.開Chrome搜尋;
            }
        }
        //維基
        for (int i=0;i<KeyDictionary.維基.length;i++) {
            if (StringInput.indexOf(KeyDictionary.維基[i]) != -1) {
                StringInput = KeyWordDictionary.在維基百科找資料;
            }
        }
        for (int i=0;i<KeyDictionary.我要看.length;i++) {
            if (StringInput.indexOf(KeyDictionary.我要看[i]) != -1) {
                StringInput = KeyWordDictionary.搜尋Youtube影片;
            }
        }
        for (int i=0;i<KeyDictionary.台語.length;i++) {
            if (StringInput.indexOf(KeyDictionary.台語[i]) != -1) {
                StringInput = KeyWordDictionary.台語;
            }
        }
        for (int i=0;i<KeyDictionary.國語.length;i++) {
            if (StringInput.indexOf(KeyDictionary.國語[i]) != -1) {
                StringInput = KeyWordDictionary.國語;
            }
        }
        for (int i=0;i<KeyDictionary.小女孩.length;i++) {
            if (StringInput.indexOf(KeyDictionary.小女孩[i]) != -1) {
                StringInput = KeyWordDictionary.小女孩;
            }
        }
        for (int i=0;i<KeyDictionary.附近的.length;i++) {
            if (StringInput.indexOf(KeyDictionary.附近的[i]) != -1) {
                StringInput = KeyWordDictionary.附近的;
            }
        }
        for (int i=0;i<KeyDictionary.Operation.length;i++) {
            if (StringInput.indexOf(KeyDictionary.Operation[i]) != -1) {
                StringInput = KeyWordDictionary.Operation;
            }
        }
        for (int i=0;i<KeyDictionary.Operation2.length;i++) {
            if (StringInput.indexOf(KeyDictionary.Operation2[i]) != -1) {
                StringInput = KeyWordDictionary.Operation2;
            }
        }







        return StringInput;
    }
}
