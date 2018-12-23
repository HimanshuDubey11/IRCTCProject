package project.himanshu.com.irctcproject;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    ArrayList<RouteData> routeDataArrayList = new ArrayList<RouteData>();
    String url;
    RequestQueue requestQueue;
    ProgressDialog dialog;
    TextView tName, tNum, monRes, tueRes, wedRes, thuRes, friRes, satRes, sunRes;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        dialog = new ProgressDialog(SearchActivity.this);
        recyclerView = findViewById(R.id.myrecyclerview);
        dialog.setMessage("Loading...");
        dialog.show();
        tName = findViewById(R.id.trainname);
        tNum = findViewById(R.id.trainnumber);
        monRes = findViewById(R.id.monres);
        tueRes = findViewById(R.id.tueres);
        wedRes = findViewById(R.id.wedres);
        thuRes = findViewById(R.id.thures);
        friRes = findViewById(R.id.frires);
        satRes = findViewById(R.id.satres);
        sunRes = findViewById(R.id.sunres);
        url = "https://api.railwayapi.com/v2/route/train/" + MainActivity.trainnumberuser.toString() +
                "/apikey/" + "2g6h4pzx2f" +
                "/";

//        url = "https://api.railwayapi.com/v2/route/train/12155/apikey/2g6h4pzx2f/";
//        Toast.makeText(this, "" + MainActivity.trainnumberuser, Toast.LENGTH_SHORT).show();

        requestQueue = Volley.newRequestQueue(SearchActivity.this);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    dialog.dismiss();
                    JSONObject trainObject = response.getJSONObject("train");

                    String name = trainObject.getString("name");
                    tName.setText(name);

                    String num = trainObject.getString("number");
                    tNum.setText(num);

                    JSONArray dayArrayRequest = trainObject.getJSONArray("days");

                    String daysresults[] = new String[7];

                    for (int i = 0; i < dayArrayRequest.length(); i++) {

                        JSONObject daysObject = dayArrayRequest.getJSONObject(i);

                        daysresults[i] = daysObject.getString("runs");

                    }

                    monRes.setText(daysresults[0]);
                    tueRes.setText(daysresults[1]);
                    wedRes.setText(daysresults[2]);
                    thuRes.setText(daysresults[3]);
                    friRes.setText(daysresults[4]);
                    satRes.setText(daysresults[5]);
                    sunRes.setText(daysresults[6]);

                    JSONArray routeJsonArray = response.getJSONArray("route");

                    for (int i = 0; i < routeJsonArray.length(); i++) {

                        JSONObject routeObject = routeJsonArray.getJSONObject(i);

                        String number = routeObject.getString("no");
                        String scharr = routeObject.getString("scharr");
                        String schdep = routeObject.getString("schdep");
                        String distance = routeObject.getString("distance");
                        String day = routeObject.getString("day");

                        JSONObject stationObject = routeObject.getJSONObject("station");

                        String stationname = stationObject.getString("name");
                        String stationcode = stationObject.getString("code");

                        routeDataArrayList.add(new RouteData(number,scharr,schdep,day,distance,stationname,stationcode));

                    }

                    recyclerView.setLayoutManager(new LinearLayoutManager(SearchActivity.this,LinearLayoutManager.VERTICAL,false));
                    recyclerView.setAdapter(new RouteDataAdapter(routeDataArrayList));

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(SearchActivity.this, "" + error.toString(), Toast.LENGTH_SHORT).show();

            }
        });
        requestQueue.add(request);

    }
}
