package com.hackapp.hacksignal;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


public class ProfileEditActivity extends ActionBarActivity {

    public EditText languageEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit);

        languageEdit = (EditText) findViewById(R.id.editTextLanguage);
        //I
        //LISTVIEW
        String[] languages = {"Java","C#","C++","C","Ruby","Python","SQL","PHP","JavaScrip","Object-c","Visual Basic","Scala","HasKell","Pascal"};
        ArrayList<String> arrayList = new ArrayList<String>(Arrays.asList(languages));
        Collections.sort(arrayList);
        ListView listView = (ListView) findViewById(R.id.listView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.list_item,R.id.item,arrayList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new ItemList());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_profile_edit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public Boolean checkLanguage(String languageName){

        return languageEdit.getText().toString().contains(languageName);

    }

    public void showDialog(String msg){
        AlertDialog alertDialog = new AlertDialog.Builder(ProfileEditActivity.this).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage(msg);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }


    class ItemList implements AdapterView.OnItemClickListener{

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            ViewGroup vg = (ViewGroup) view;
            TextView tv = (TextView) vg.findViewById(R.id.item);
            Toast.makeText(ProfileEditActivity.this,tv.getText().toString(),Toast.LENGTH_SHORT).show();
            if(!checkLanguage(tv.getText().toString())){
                languageEdit.setText(languageEdit.getText() + tv.getText().toString()+ ", ");
            }else{
                showDialog("This Language is Already on the List");
            }

        }
    }
}
