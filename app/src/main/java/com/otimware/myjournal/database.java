package com.otimware.myjournal;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import android.util.Log;

/**
 * Created by OtimDiBossman on 7/1/2018.
 */
@Database(entities = {DB_entity.class},version = 1,exportSchema = false)
@TypeConverters(DateConverter.class)
public abstract class database extends RoomDatabase{
    private static final String LOG_TAG = database.class.getSimpleName();
    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME = "journalDB";
    private static database sInstance;

    public static database getInstance(Context context) {
        if (sInstance == null) {
            synchronized (LOCK) {
                Log.d(LOG_TAG, "Creating new database instance");
                sInstance = Room.databaseBuilder(context.getApplicationContext(),
                        database.class, database.DATABASE_NAME)
                        // COMPLETED (2) call allowMainThreadQueries before building the instance
                        // Queries should be done in a separate thread to avoid locking the UI
                        // We will allow this ONLY TEMPORALLY to see that our DB is working
                        .allowMainThreadQueries()
                        .build();
            }
        }
        Log.d(LOG_TAG, "Getting the database instance");
        return sInstance;
    }

    public abstract DB_DAO journalDao();

}
