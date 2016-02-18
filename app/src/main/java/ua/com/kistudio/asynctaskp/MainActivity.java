package ua.com.kistudio.asynctaskp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    public static final String LOG_TAG = "AsyncLogs";
    public static final String IMAGE_URL = "http://cityfinder.esy.es/img/5.jpg";
/*

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
*/
/*

    void asyncLoadImage(){
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
*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //loadImage();
        //asyncLoadImage();

        ListView lvImages = (ListView) findViewById(R.id.lvImages);
        AsyncLoadImageArray asyncLoadImageArray = new AsyncLoadImageArray(this);
        asyncLoadImageArray.execute("http://cityfinder.esy.es/img/1.jpg",
                "http://cityfinder.esy.es/img/2.jpg",
                "http://cityfinder.esy.es/img/3.jpg",
                "http://cityfinder.esy.es/img/4.jpg",
                "http://cityfinder.esy.es/img/5.jpg",
                "http://cityfinder.esy.es/img/6.jpg",
                "http://cityfinder.esy.es/img/7.jpg"
                );
        try {
            MyAdapter myAdapter = new MyAdapter(this,asyncLoadImageArray.get());
            lvImages.setAdapter(myAdapter);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        /*SimpleCursorAdapter simpleCursorAdapter = null;
        try {
            simpleCursorAdapter = new SimpleCursorAdapter(
                    this,R.layout.item,asyncLoadImageArray.get(),
                    new String[]{"image"},new int[]{R.id.ivItem},
                    Adapter.NO_SELECTION);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        lvImages.setAdapter(simpleCursorAdapter);*/


    }
}
