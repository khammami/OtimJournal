package com.otimware.myjournal;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;


/**
 * Created by OtimDiBossman on 7/1/2018.
 */
@Dao
public interface DB_DAO {
    @Query("SELECT * FROM journalEntry ORDER BY id")
    List<DB_entity> loadAllTasks();
    @Insert
    void insertJournal(DB_entity journalTable);
    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateJournal(DB_entity journalTable);
    @Delete
    void deleteJournal(DB_entity journalTable);
    @Query("SELECT * FROM JOURNALENTRY WHERE id =:id")
    List<DB_entity> loadTaskById(int id);
}
