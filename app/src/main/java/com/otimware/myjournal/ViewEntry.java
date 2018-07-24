package com.otimware.myjournal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ViewEntry extends AppCompatActivity {

    private TextView mtextTitle;
    private TextView mtextThoughts;
    private TextView mtextDate;
    private FirebaseAuth mFirebaseAuth;
    private DatabaseReference DBref;
    private EntryInformation retrievedInfo;
    private database mDBRetrieve;
    private DB_entity mEntity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_entry);
        mtextTitle=(TextView) findViewById(R.id.textTitle);
        mtextDate=(TextView) findViewById(R.id.textDate);
        mtextThoughts=(TextView) findViewById(R.id.text_Thoughts);
        DBref= FirebaseDatabase.getInstance().getReference();
        mDBRetrieve.getInstance(getApplicationContext());

        retrieveData();

    }
    public void retrieveData(){
        FirebaseUser user=mFirebaseAuth.getCurrentUser();
        if(mFirebaseAuth.getCurrentUser()==null){
            Toast.makeText(this,"log in first",Toast.LENGTH_LONG).show();
            finish();
            startActivity(new Intent(ViewEntry.this,MainActivity.class));
        }


        Intent intentToViewEntry=getIntent();
        if (intentToViewEntry.hasExtra("clickID") && intentToViewEntry!=null){
            int idValue=intentToViewEntry.getIntExtra("clickID", Integer.parseInt(String.valueOf("clickID")));


        mDBRetrieve.journalDao().loadTaskById(idValue);
        String mtitle=mEntity.getTitle();
        mtextTitle.setText(mtitle);
        mtextDate.setText(mEntity.getDate());


    }
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
}

