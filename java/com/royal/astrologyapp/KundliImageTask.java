package com.royal.astrologyapp;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;
import java.io.InputStream;
import java.net.URL;

public class KundliImageTask extends AsyncTask<String, Void, Bitmap> {
    private ImageView mImageView;

    public KundliImageTask(ImageView imageView) {
        mImageView = imageView;
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        try {
            // Construct the Kundli generation API URL
            String apiUrl = "https://api.astrosage.com/v1/astrology/kundli_chart";
            apiUrl += "?birth_date=" + params;
            apiUrl += "&birth_time=" + params[1];
            apiUrl += "&chart_style=north_indian";

            // Send a GET request to the API URL and decode the response as a Bitmap
            URL url = new URL(apiUrl);
            InputStream in = url.openStream();
            Bitmap bitmap = BitmapFactory.decodeStream(in);
            in.close();
            return bitmap;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        // Display the Kundli image in the ImageView
        mImageView.setImageBitmap(bitmap);
    }
}