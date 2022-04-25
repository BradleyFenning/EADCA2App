package com.example.eadca2app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button button1, button2;
    EditText dataInput;
    ListView bookList;

    final WeatherDataService weatherDataService = new WeatherDataService(MainActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        button1 = findViewById(R.id.button1);
        dataInput = findViewById(R.id.userInput);
        bookList = findViewById(R.id.bookList);

        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                String url = "https://ca2service20220424232144.azurewebsites.net/api/Books/";

                JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        String cityID = "";
                        JSONObject bookInfo = new JSONObject( );
                        ArrayList<String> listOfBooks = new ArrayList<>();

                        try {
                            for(int i = 0 ; i < 10 ; i++ ){
                                bookInfo = response.getJSONObject(i);
                                cityID = "";
                                cityID = bookInfo.getString("bookName") + " " + bookInfo.getString("author") + " " + bookInfo.getString("availableForLoan");
                                listOfBooks.add(cityID);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, listOfBooks);
                        bookList.setAdapter(arrayAdapter);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                });

                queue.add(request);

              /* StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Toast.makeText(MainActivity.this, response, Toast.LENGTH_SHORT).show();
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                });

                queue.add(stringRequest);*/

                /*weatherDataService.getCityID(dataInput.getText().toString(), new WeatherDataService.VolleyResponseListener() {
                    @Override
                    public void onError(String message) {
                        Toast.makeText(MainActivity.this, "Something Wrong" , Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String cityID) {
                        Toast.makeText(MainActivity.this, "Returned an ID of " + cityID, Toast.LENGTH_SHORT).show();
                    }
                });*/
            }
        });

        button2 = findViewById(R.id.button2);

        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                String url = "https://ca2service20220424232144.azurewebsites.net/api/Libraries/";

                JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        String cityID = "";
                        JSONObject bookInfo = new JSONObject( );
                        ArrayList<String> listOfBooks = new ArrayList<>();

                        try {
                            for(int i = 0 ; i < 2 ; i++ ){
                                bookInfo = response.getJSONObject(i);
                                cityID = "";
                                cityID = bookInfo.getString("libraryName") + "\t\t\t " + bookInfo.getString("libraryAddress");
                                listOfBooks.add(cityID);
                            }
                            //bookInfo = response.getJSONObject(2);
                            //cityID = bookInfo.getString("bookName");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, listOfBooks);
                        bookList.setAdapter(arrayAdapter);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                });

                queue.add(request);
            }
        });

    }
}