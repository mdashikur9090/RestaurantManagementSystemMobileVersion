package com.example.tastybites;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {
    private TextView tableIdTextView,  passwordTextView;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);

        int tableId = sharedPref.getInt("TABLEID", 0);

        if (tableId > 0){
            Log.d("message", "Table id found in share");
            Intent intent = new Intent(this, Webview.class);
            intent.putExtra("TABLEID",  tableId);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }else{
            Log.d("message", "Table id not found in share");
        }

        tableIdTextView = (TextView)findViewById(R.id.tableId);
        passwordTextView = (TextView)findViewById(R.id.input_password);
        btnLogin = (Button)findViewById(R.id.btn_login);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (tableIdTextView.getText().toString().trim().isEmpty()){
                    Log.d("message", "please give table id");


                }else{
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putInt("TABLEID", Integer.parseInt(tableIdTextView .getText().toString()));
                    editor.commit();

                }
            }
        });

    }
}
