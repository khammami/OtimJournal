package com.otimware.myjournal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static com.otimware.myjournal.AddEntryActivity.constant.collectionName;

public class AddEntryActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText mEditTitle;
    private EditText mEditThoughts;
    private Button mButtonSave;
    private DatabaseReference DBref;
    private FirebaseAuth firebaseAuth;
    private database mDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_entry);

        firebaseAuth =FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()==null){
            Toast.makeText(this,"log in first",Toast.LENGTH_LONG).show();
            finish();
            startActivity(new Intent(AddEntryActivity.this,MainActivity.class));
        }
        DBref= FirebaseDatabase.getInstance().getReference();
        FirebaseUser User=firebaseAuth.getCurrentUser();

        mEditTitle=(EditText) findViewById(R.id.editTittle);
        mEditThoughts=(EditText) findViewById(R.id.editThoughts);
        mButtonSave=(Button) findViewById(R.id.buttonSave);
        mButtonSave.setOnClickListener(this);
       //initialise database
        mDB=database.getInstance(getApplicationContext());

    }
    public void saveEntryInfo(){
        String Title=mEditTitle.getText().toString().trim();
        String Thoughts=mEditThoughts.getText().toString().trim();
        Date date= Calendar.getInstance().getTime();
        String dateTime=date.toString();
        EntryInformation enterInfo=new EntryInformation(Title,Thoughts,dateTime);
        FirebaseUser user=firebaseAuth.getCurrentUser();
        DBref=FirebaseDatabase.getInstance().getReference(constant.collectionName);
        DatabaseReference perUserPerJournal= DBref.child(user.getUid()).child(constant.journalName).push();
        perUserPerJournal.setValue(enterInfo);
        //local database
        DB_entity journalEntry=new DB_entity(Title,Thoughts,dateTime);
        mDB.journalDao().insertJournal(journalEntry);


       //toast and return to list

        Toast.makeText(this,"journal entry added",Toast.LENGTH_LONG).show();
        finish();
    }
    public class constant{
        public static final String collectionName="journalnotes";
        public static final String journalName="journal";


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_entries_java, menu);
        return true;


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int ItemId = item.getItemId();
        switch (ItemId) {
            case (R.id.editItem):
                //code to edit
            case (R.id.deleteItem):
                //code to delete
        }
        return super.onOptionsItemSelected(item);
    }
    public void onClick(View v){
        if(v==mButtonSave){
            saveEntryInfo();
        }
    }
}
