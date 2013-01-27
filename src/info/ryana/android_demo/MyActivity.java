package info.ryana.android_demo;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONObject;

import java.net.URL;

public class MyActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        WebView webView = (WebView)findViewById(R.id.webView);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        class JsObject {
            Context mContext;

            /** Instantiate the interface and set the context */
            JsObject(Context c) {
                mContext = c;
            }
            @JavascriptInterface
            public void showToast(final String toast) {
                Toast.makeText(mContext, toast, Toast.LENGTH_LONG).show();
            }

            @JavascriptInterface
            public void loadItem(final String item) {
                try {
                    final JSONObject jsonObject = new JSONObject(item);

                    final String name = jsonObject.getString("titleNoFormatting");

                    String height = jsonObject.getString("height");
                    String width = jsonObject.getString("width");
                    final String dimensions = width + "x" + height;

                    final String url = jsonObject.getString("url");
                    Handler h = new Handler(mContext.getMainLooper());
                    h.post(new Runnable() {
                        @Override
                        public void run() {
                            TextView nameTextView = (TextView)findViewById(R.id.name);
                            nameTextView.setText(name);

                            TextView dimensionsTextView = (TextView)findViewById(R.id.dimensions);
                            dimensionsTextView.setText(dimensions);

                            TextView urlTextView = (TextView)findViewById(R.id.url);
                            urlTextView.setText(url);
                        }
                    });
                } catch (Exception e) {
                    showToast(e.getMessage());
                }
            }
        }
        webView.addJavascriptInterface(new JsObject(this), "Android");
        webView.loadUrl("http://android-demo.ryana.info");
    }
}
