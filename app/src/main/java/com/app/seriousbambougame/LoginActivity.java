package com.app.seriousbambougame;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import static com.app.seriousbambougame.datas.Preference.setUser;

public class LoginActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;
    Button button;

    String url;


    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);

        button = (Button) findViewById(R.id.submit_login);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getDataOfUser(username.getText().toString(), password.getText().toString());
            }
        });


    }

    String baseUrl = "http://35.204.21.81/api/auth/login";
    private void getDataOfUser(String usernameData, String passwordData){

        JSONObject params = new JSONObject();

        try {
            params.put("username", usernameData);
            params.put("password", passwordData);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        this.url = this.baseUrl;
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, params, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                setUser(getApplicationContext(), response.toString());
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("JsonPost", "Error: " + error);
                button.setText("Recommencer");
            }
        });

        queue.add(jsonObjectRequest);
    }



}
