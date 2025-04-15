package com.example.hw04_gymlog_v300.Database;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.RoomDatabase.Callback;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.hw04_gymlog_v300.Database.entities.GymLog;
import com.example.hw04_gymlog_v300.Database.entities.GymLogDAO;
import com.example.hw04_gymlog_v300.MainActivity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Database(entities = {GymLog.class}, version =1, exportSchema = false)
public abstract class GymLogDatabase extends RoomDatabase {

private static final String DATABASE_NAME="GymLog_database";
    public static final String gym_Log_Table = "gymLogTable";

    private static volatile GymLogDatabase INSTANCE;

    private static final int NUMBER_OF_THREADS=4;

    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static GymLogDatabase getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (GymLogDatabase.class){
                if(INSTANCE==null){

                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    GymLogDatabase.class,
                                    DATABASE_NAME)
                            .fallbackToDestructiveMigrationFrom(false,1) // watch this might casue error
                            .addCallback(addDefaultValues)
                            .build();

                }
            }
        }
        return INSTANCE;
    }
    private static final RoomDatabase.Callback addDefaultValues= new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            Log.i(MainActivity.TAG,"DATABASE CREATED!");
            //TODO: add databaseWriteExecutor.execute(()->{...}
        }
    };


    public abstract GymLogDAO gymLogDAO();

}
