package com.zezzi.fadintroduccion;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by zezzi on 7/8/13.
 */
public class DetailTask extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_task);
        Intent it = getIntent();
        CharSequence strTask2 = it.getCharSequenceExtra(Intent.EXTRA_TEXT);
        try{
        String strTask = strTask2.toString();
        Log.d("recibiendo",strTask);
        TextView texto=(TextView)findViewById(R.id.title_detail);
        texto.setText(strTask);
            it.removeExtra(Intent.EXTRA_TEXT);
            Toast.makeText(
                    this,
                    getString(R.string.taskAddedToast, strTask.toString()),
                    Toast.LENGTH_SHORT).show();
        }catch(Exception strTaskNull){}

    }

}
