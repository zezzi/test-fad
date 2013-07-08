package com.zezzi.fadintroduccion;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Modified by zezzi on 7/8/13.
 * based on GTUG Code by Ykro
 */

public class Fad_Activity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //El Layout que se quiere mostrar
        setContentView(R.layout.activity_main);

        /*
         * Paso 2*/
        Button btnAdd = (Button)findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
		/*
		 *  Paso 3*/
        switch (v.getId()) {
            case R.id.btnAdd:
                Intent listDataIntent = new Intent(this, Task.class);
                EditText et = (EditText)findViewById(R.id.txtTask);
                String inputText = et.getText().toString();
                listDataIntent.putExtra(Intent.EXTRA_TEXT, inputText);
                et.setText("");
                startActivity(listDataIntent);
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.fad_, menu);
        return true;
       // getMenuInflater().inflate(R.menu.fad_, menu);
       // return true;
    }
    
}
