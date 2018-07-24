package com.otimware.myjournal;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.database.FirebaseDatabase;
import com.otimware.myjournal.R;

public class EntriesActivity extends AppCompatActivity implements GreenAdapter.ListItemClickListener{
     private GreenAdapter mAdapter;
     private RecyclerView mRecyclerView;
     private static final int num_of_items=100;
     private database mDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entries);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addEntry=new Intent(EntriesActivity.this,AddEntryActivity.class);
                startActivity(addEntry);
            }
        });
        mRecyclerView= findViewById(R.id.recycle_layout);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        mAdapter=new GreenAdapter(num_of_items,this);
        mRecyclerView.setAdapter(mAdapter);


    }

    @Override
    public void OnListItemClick(int clickedItem){

        Intent CheckEntryIntent=new Intent(EntriesActivity.this,ViewEntry.class);
        CheckEntryIntent.putExtra("clickID",clickedItem);
      startActivity(CheckEntryIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_entries_java,menu);
        return true;


    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int ItemId=item.getItemId();
        switch (ItemId){
            case(R.id.add_entry):
                startActivity(new Intent(this,AddEntryActivity.class));
        }
        return super.onOptionsItemSelected(item);

    }
}
