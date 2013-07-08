package com.zezzi.fadintroduccion;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;


/**
 * Modified by zezzi on 7/8/13.
 * based on GTUG Code by Ykro
 */
public class Task extends Activity implements AdapterView.OnItemClickListener {

    static LinkedList<HashMap<String, String>> taskList = new LinkedList<HashMap<String, String>>();
    static LinkedList<Integer> priorityList = new LinkedList<Integer>();
    final static String TASK_TEXT = "T";
    final static String TASK_DATA = "D";
    final static int NORMAL_PRIORITY = 0;
    final static int IMPORTANT_PRIORITY = 1;
    final static int IMPORTANT_PRIORITY_COLOR = 0xFFFF0000;
    final static int NORMAL_PRIORITY_COLOR = 0x00000000;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        setContentView(R.layout.list);

		/*
		 *  Paso 4*/
        Intent it = getIntent();
        CharSequence strTask = it.getCharSequenceExtra(Intent.EXTRA_TEXT);
        try {
            HashMap<String, String> data = new HashMap<String, String>();
            data.put(TASK_TEXT,strTask.toString());
            data.put(TASK_DATA, (new Date()).toLocaleString());
            taskList.addFirst(data);
            priorityList.addFirst(new Integer(NORMAL_PRIORITY));

            it.removeExtra(Intent.EXTRA_TEXT);
            Toast.makeText(
                    this,
                    getString(R.string.taskAddedToast, strTask.toString()),
                    Toast.LENGTH_SHORT).show();
        } catch (NullPointerException strTaskNull){}

        /* Fin Paso 4 */
		/*
		 *  Paso 5*/
        MyAdapter sAdapter = new MyAdapter(this, taskList,
                android.R.layout.two_line_list_item,
                new String[] { TASK_TEXT, TASK_DATA},
                new int[] { android.R.id.text1, android.R.id.text2},
                priorityList,
                new int[] {NORMAL_PRIORITY_COLOR, IMPORTANT_PRIORITY_COLOR});
        ListView lv = (ListView) findViewById(android.R.id.list);
        lv.setAdapter(sAdapter);
        /* Fin Paso 5 */

		/*
		 * Parte del paso 6*/
        lv.setOnItemClickListener(this);


         /* Fin Paso 6 */

		/*
		 * Parte del paso 7 */
        lv.setOnCreateContextMenuListener(this);
        /* Fin Paso 7 */

        super.onCreate(savedInstanceState);
    }

    /*
	 * Paso 7*/
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.fad_, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item
                .getMenuInfo();
        checkMenuAction(menuInfo.targetView, item.getItemId(), menuInfo.position);
        return super.onContextItemSelected(item);
    }

    private void checkMenuAction(View listItem, int action, int position) {
        switch (action) {
            case R.id.delete:
                ListView lv = (ListView) findViewById(android.R.id.list);
                SimpleAdapter sA = (SimpleAdapter)lv.getAdapter();
                HashMap<String, String> map = taskList.remove(position);
                sA.notifyDataSetChanged();
                Toast.makeText(
                        this,
                        getString(R.string.taskRemovedToast, map.get(TASK_TEXT)),
                        Toast.LENGTH_SHORT).show();
        }
    }

	/*fin */

    public void onItemClick(AdapterView<?> listView, View listItem, int position, long id) {
		/*
		 * Paso 6*/
        if (priorityList.get(position) == NORMAL_PRIORITY) {
            listItem.setBackgroundColor(IMPORTANT_PRIORITY_COLOR);
            priorityList.set(position, new Integer(IMPORTANT_PRIORITY));
        } else if (priorityList.get(position) == IMPORTANT_PRIORITY){
            listItem.setBackgroundColor(NORMAL_PRIORITY_COLOR);
            priorityList.set(position, new Integer(NORMAL_PRIORITY));
        }
        HashMap<String, String> map = taskList.get(position);
        Toast.makeText(
                this,
                getString(R.string.taskModifiedToast, map.get(TASK_TEXT)),
                Toast.LENGTH_SHORT).show();

    }

}
