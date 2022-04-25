package com.example.eadca2app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
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

public class Activity2 extends AppCompatActivity {

    public static final String QUERY_FOR_Book_ID = "https://ca2service20220424232144.azurewebsites.net/api";
    TextView librayList;
    EditText userInput;
    Button button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        librayList = (TextView) findViewById(R.id.librayList);
        userInput = (EditText) findViewById(R.id.userInput);
        button1 = (Button) findViewById(R.id.button1);

        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                RequestQueue queue = Volley.newRequestQueue(Activity2.this);
                String url = QUERY_FOR_Book_ID + "/Books/" + userInput.getText().toString();

                JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        String libraryid = "";

                        try {
                            libraryid = response.getString("libraryID");
                            String url2 = QUERY_FOR_Book_ID + "/Libraries/" + libraryid;

                            JsonObjectRequest request2 = new JsonObjectRequest(Request.Method.GET, url2, null, new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    String library = "";
                                    try {
                                        library = response.getString("libraryName") + "\t\t\t\t\t\t " + response.getString("libraryAddress");
                                        librayList.setText(library);

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                }
                            }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(Activity2.this, "Error", Toast.LENGTH_SHORT).show();
                                }
                            }
                            );
                            queue.add(request2);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Activity2.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                }
                );

                queue.add(request);

            }
        });
    }




}