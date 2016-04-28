package example.kagura.com.gson;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Type;


public class MainActivity extends AppCompatActivity {

    private ImageView image;
    private TextView tv;
    private EditText et;
    private static final String url = "https://api.douban.com/v2/movie/search?q=";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.tv);
        et = (EditText) findViewById(R.id.et);
        public void click(View view)
        {
            String name = tv.getText().toString().trim();
            String nUrl = url+name;
            AsyncHttpClient asy = new AsyncHttpClient();
            asy.get(nUrl, new AsyncHttpResponseHandler() {

                @Override
                public void onFailure(int i, org.apache.http.Header[] headers, byte[] bytes,
                                      Throwable throwable) {
                    Toast.makeText(getApplicationContext(), "失败", Toast.LENGTH_SHORT).show();
                }
                @Override
                public void onSuccess(int i, org.apache.http.Header[] headers, byte[] bytes) {
                    Toast.makeText(getApplicationContext(), "成功", Toast.LENGTH_SHORT).show();
                    String json = new String(bytes);
                    Type type = new TypeToken<Movie>() {
                    }.getType();
                    Gson gson = new Gson();
                    Movie b = gson.fromJson(json, type);
                    image = (ImageView) findViewById(R.id.image);
                    Picasso.with(getApplicationContext()).load(b.getImages().getLarge()).into(image);
                }
            });
        }

    }
}
