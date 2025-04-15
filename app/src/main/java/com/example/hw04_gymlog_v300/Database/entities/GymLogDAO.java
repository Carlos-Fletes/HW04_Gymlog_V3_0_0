package com.example.hw04_gymlog_v300.Database.entities;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.hw04_gymlog_v300.Database.GymLogDatabase;

import java.util.ArrayList;


@Dao
public interface GymLogDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(GymLog gymlog);

    @Query("SELECT * from " + GymLogDatabase.gym_Log_Table)
    ArrayList<GymLog> getAllRecords();


}
