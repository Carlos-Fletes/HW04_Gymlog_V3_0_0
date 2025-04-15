package com.example.hw04_gymlog_v300.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.hw04_gymlog_v300.database.entities.GymLog;

import java.util.List;


@Dao
public interface GymLogDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(GymLog gymlog);

    @Query("SELECT * From " + GymLogDatabase.gym_Log_Table + " ORDER BY date DESC")
    List<GymLog> getAllRecords();


}
