package com.eurekakids.euraka1;

/**
 * Created by Kirubanand on 05/09/2015.
 */
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ImageButton;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.eurekakids.com.eurekakids.db.DatabaseHandler;
import com.eurekakids.db.datamodel.Student;

import java.util.ArrayList;


public class Listscreen extends AppCompatActivity {

    ImageButton FAB;
    private Toolbar toolbar;

	private int centre_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.name_list);
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

		Bundle bundle = getIntent().getExtras();
		centre_id = bundle.getInt("CENTRE_ID");

        FAB = (ImageButton) findViewById(R.id.imageButton);
        FAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addtoList(v);


            }
        });

        //String [] names = {"ram","ravi","anand","susan","basha","christie","divya","edwin","kannan","adam","henry","tamil"};
		DatabaseHandler db = new DatabaseHandler(getApplicationContext());
		ArrayList<Student> students = db.getAllStudentsByCentreId(centre_id);

		Student[] names = new Student[students.size()];
		for(int i =0; i< students.size(); i++){
			names[i] = students.get(i); //.getStudentName();
		}
        ListAdapter nadapter = new ArrayAdapter<Student>(this,android.R.layout.simple_expandable_list_item_1,names);
        ListView nview = (ListView) findViewById(R.id.name_list);
        nview.setAdapter(nadapter);
        nview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Student studentSelected = (Student)parent.getItemAtPosition(position);
                opennav(view, studentSelected);

            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent getSpinnerIntent = new Intent(this,Spinnerscreen.class);
        startActivity(getSpinnerIntent);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }

    public void addtoList(View view) {
        Intent getaddKidIntent = new Intent(this, AddKid.class);
		getaddKidIntent.putExtra("CENTRE_ID", centre_id);
        startActivity(getaddKidIntent);
    }
    public void opennav(View view,Student student){
        Intent navIntent = new Intent(this, tam_page.class);
        navIntent.putExtra("list_item", student);
		navIntent.putExtra("CENTRE_ID", centre_id);
        startActivity(navIntent);
    }

}
