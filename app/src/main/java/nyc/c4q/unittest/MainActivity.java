package nyc.c4q.unittest;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import nyc.c4q.unittest.backend.MultiSquareParser;
import nyc.c4q.unittest.backend.MultiSquareResponse;
import nyc.c4q.unittest.backend.UrlConstants;
import nyc.c4q.unittest.views.MultiSquareView;

public class MainActivity extends AppCompatActivity {

    private Button mUpdateColor;
    private RequestQueue mRequestQueue;
    private MultiSquareView mSquareView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSquareView = (MultiSquareView) findViewById(R.id.multiSquare);
        mRequestQueue = Volley.newRequestQueue(this);
        mUpdateColor = (Button) findViewById(R.id.button);
        mUpdateColor.setOnClickListener(buildClickListener());
    }

    private View.OnClickListener buildClickListener() {
        return ignored -> {
            if (isOnline(MainActivity.this)) {
                StringRequest request = new StringRequest(
                        Request.Method.GET,
                        getUrlAddress(),
                        buildSuccessListener(),
                        buildErrorListener()
                );
                request.setShouldCache(false);
                mRequestQueue.add(request);
            } else {
                showOfflineToast();
            }
        };
    }

    private void showOfflineToast() {
        Toast.makeText(MainActivity.this, "Must be online", Toast.LENGTH_LONG).show();
    }

    public static boolean isOnline(Context context) {
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        }
        return false;
    }


    private Response.ErrorListener buildErrorListener() {
        return error -> {
            Toast.makeText(MainActivity.this, "Download Failed", Toast.LENGTH_LONG).show();
        };
    }

    private Response.Listener<String> buildSuccessListener() {
        return response -> {
            MultiSquareResponse result = MultiSquareParser.parse(response);
            mSquareView.setBoxColors(result);
            Toast.makeText(MainActivity.this, "Download Succeeded", Toast.LENGTH_LONG).show();
        };
    }

    private String getUrlAddress() {
        return UrlConstants.DYNAMIC_CONTENT;
    }
}
