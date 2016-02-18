package ua.com.kistudio.asynctaskp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    public static final String LOG_TAG = "AsyncLogs";
    public static final String IMAGE_URL = "http://cityfinder.esy.es/img/5.jpg";

     void loadImage(){
        Bitmap bm = null;
        ImageView ivLoadedImage = (ImageView) findViewById(R.id.ivLoadImage);
        TextView tvAdress = (TextView) findViewById(R.id.tvAdress);
        try {

            URL imageURL = new URL(IMAGE_URL);
            URLConnection connection = imageURL.openConnection();
            connection.connect();
            InputStream inputStream = connection.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(inputStream);
            bm = BitmapFactory.decodeStream(bis);
            bis.close();
            inputStream.close();

            tvAdress.setText(IMAGE_URL);
            ivLoadedImage.setImageBitmap(bm);

        } catch (MalformedURLException e) {
            Log.d(LOG_TAG,"MalformedURLException");
        } catch (IOException e) {
            Log.d(LOG_TAG, "IOException");
        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //loadImage();
        AsyncLoadImage asyncLoadImage = new AsyncLoadImage();
        asyncLoadImage.execute(IMAGE_URL);
        ImageView ivLoadedImage = (ImageView) findViewById(R.id.ivLoadImage);

        try {

            ivLoadedImage.setImageBitmap(asyncLoadImage.get());

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
