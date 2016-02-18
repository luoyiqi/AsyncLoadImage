package ua.com.kistudio.asynctaskp;

import android.app.ProgressDialog;
import android.content.Context;
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
import java.util.ArrayList;

/**
 * Created by Вiталя on 18.02.2016.
 */
public class AsyncLoadImageArray extends AsyncTask<String,Integer,ArrayList<Bitmap>> {


    ArrayList<Bitmap> bmArray = new ArrayList<>();
    Context context;
    ProgressDialog progressDialog;
    public AsyncLoadImageArray(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
    }

    @Override
    protected void onPostExecute(ArrayList<Bitmap> bitmapArrayList) {
        super.onPostExecute(bitmapArrayList);
        progressDialog.dismiss();
    }

    @Override
    protected ArrayList<Bitmap> doInBackground(String... params) {
        int i=1;
        for (String imageUrl:params){
            bmArray.add(load(imageUrl));
            }
        return bmArray;
    }

    private Bitmap load(String urlAdress){
        Bitmap bm = null;
        try {

            URL imageURL = new URL(urlAdress);
            URLConnection connection = imageURL.openConnection();
            connection.connect();
            InputStream inputStream = connection.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(inputStream);
            bm = BitmapFactory.decodeStream(bis);
            bis.close();
            inputStream.close();

        } catch (MalformedURLException e) {
            Log.d(MainActivity.LOG_TAG, "MalformedURLException");
        } catch (IOException e) {
            Log.d(MainActivity.LOG_TAG, "IOException");
        }
        return bm;

    }
}
