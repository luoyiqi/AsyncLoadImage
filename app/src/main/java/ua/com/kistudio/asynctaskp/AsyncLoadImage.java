package ua.com.kistudio.asynctaskp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Вiталя on 18.02.2016.
 */
public class AsyncLoadImage extends AsyncTask<String,Void,Bitmap>{

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Log.d(MainActivity.LOG_TAG,"onPreExecute");
    }

    @Override
    protected Bitmap doInBackground(String... params) {

        Log.d(MainActivity.LOG_TAG,"doInBackground");
        Bitmap bm = null;
        try {

            URL imageURL = new URL(params[0]);
            URLConnection connection = imageURL.openConnection();
            connection.connect();
            InputStream inputStream = connection.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(inputStream);
            bm = BitmapFactory.decodeStream(bis);
            bis.close();
            inputStream.close();

        } catch (MalformedURLException e) {
            Log.d(MainActivity.LOG_TAG,"MalformedURLException");
        } catch (IOException e) {
            Log.d(MainActivity.LOG_TAG, "IOException");
        }
        return bm;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        Log.d(MainActivity.LOG_TAG,"onPostExecute");
    }
}
