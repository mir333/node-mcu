package im.ligas.remotefornode;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by ligasm on 07/04/16.
 */
public class SendCommand extends AsyncTask<String, Void, String> {

    private static final String TAG = "SendCommand";

    protected String doInBackground(String... requestURL) {
        String result = null;
        int count = requestURL.length;
        if (count != 0) {
            String urlStr = requestURL[0];
            Log.i(TAG, "Calling " +urlStr);
            HttpURLConnection urlConnection = null;
            try {
                URL url = new URL(urlStr);
                urlConnection = (HttpURLConnection) url.openConnection();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                result = bufferedReader.readLine();
                bufferedReader.close();
                urlConnection.disconnect();
            } catch (Exception e) {
                Log.e(TAG, "Unable to make GET", e);
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
            }
        }


        return result;
    }
}