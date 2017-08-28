package tw.org.iii.rightnow.ITRITTSSpeaker;

import android.os.AsyncTask;
import android.util.Log;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

/**
 * Created by iii on 2017/8/24.
 */

public class TTSTAW extends AsyncTask<String, Void, String> {
    //TTS(NEW)
    String convertID;
    String SpeakURL;
    String methodName = "";
    private static final String endPoint = "http://tts.itri.org.tw/TTSService/Soap_1_3.php";
    private static final String nameSpace = "http://tts.itri.org.tw/TTSService/";
    String result;

    public interface AsyncResponseTAW {
        void processFinishTAW(String output);
    }

    public AsyncResponseTAW delegate = null;

    public TTSTAW(AsyncResponseTAW delegate){
        this.delegate = delegate;
    }






    @Override
    protected String doInBackground(String... params) {

        while (!isCancelled()) {
            String BOTReturnWord = params[0];

            isCancelled();
            String ID = GetIDName(BOTReturnWord, 1);

            int statusCode;
            String[] retAry;
            SoapObject obj;

            methodName = "GetConvertStatus";

            String soapAction = nameSpace + methodName;
            SoapObject rpc = new SoapObject(nameSpace, methodName);
            rpc.addProperty("accountID", "alex800528");
            rpc.addProperty("password", "Jason789");
            rpc.addProperty("convertID", ID);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.bodyOut = rpc;
            envelope.dotNet = true;
            envelope.setOutputSoapObject(rpc);
            HttpTransportSE transport = new HttpTransportSE(endPoint);
            transport.debug = true;
            try {
                //do something
                do {
                    transport.call(soapAction, envelope);
                    obj = (SoapObject) envelope.bodyIn;
                    result = obj.toString();
                    retAry = result.split("&");
                    statusCode = Integer.valueOf(retAry[2]);
                    Log.i("shiaukai", Integer.toString(statusCode));
                } while (statusCode != 2);
                SpeakURL = retAry[4].replaceAll("[;}]", "");
                Log.i("shiaukai", SpeakURL);
                return SpeakURL;
            } catch (Exception e) {
                //handle error
                e.printStackTrace();
                Log.i("shiaukai", "取網址失敗");
                return null;
            }
        }
        Log.i("shiaukai", "TestTask ends");
        return null;
    }

    @Override
    protected void onPostExecute(String ID) {
        //執行後 完成背景任務
        super.onPostExecute(ID);
        delegate.processFinishTAW(ID);
    }

    public String GetIDName(String TalkString, int SpeakerID) {

        String speakstring = TalkString;
        String TTSSpeaker = null;

        if (SpeakerID == 0) {
            TTSSpeaker = "Angela";
        } else if (SpeakerID == 1) {
            TTSSpeaker = "TW_LIT_AKoan";
        }

        result = "";
        methodName = "ConvertAdvancedText";

        String soapAction = nameSpace + methodName;

        SoapObject rpc = new SoapObject(nameSpace, methodName);
        rpc.addProperty("accountID", "alex800528");
        rpc.addProperty("password", "Jason789");
        rpc.addProperty("TTStext", speakstring);
        rpc.addProperty("TTSSpeaker", TTSSpeaker);
        rpc.addProperty("volume", 100);
        rpc.addProperty("speed", 0);
        rpc.addProperty("outType", "wav");
        rpc.addProperty("PitchLevel", 0);
        rpc.addProperty("PitchSign", 0);
        rpc.addProperty("PitchScale", 5);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.bodyOut = rpc;
        envelope.dotNet = true;
        envelope.setOutputSoapObject(rpc);
        HttpTransportSE transport = new HttpTransportSE(endPoint);
        transport.debug = true;
        try {
            transport.call(soapAction, envelope);
            SoapObject obj = (SoapObject) envelope.bodyIn;
            result = obj.toString();
            String[] retAry = result.split("&");
            String status = retAry[0];
            if (status.substring(status.length() - 1).equals("0")) {
                convertID = retAry[2].replaceAll("[;}]", "");
            }
            Log.i("shiaukai", convertID);
            return convertID;
        } catch (Exception e) {
            e.printStackTrace();
            Log.i("shiaukai", "取ID失敗");
            return null;
        }
    }
}

