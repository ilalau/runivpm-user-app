package com.ids.idsuserapp.db;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.ids.idsuserapp.db.dao.ArcoDao;
import com.ids.idsuserapp.db.dao.BeaconDao;
import com.ids.idsuserapp.db.dao.MappaDao;
import com.ids.idsuserapp.db.entity.Arco;
import com.ids.idsuserapp.db.entity.Beacon;
import com.ids.idsuserapp.db.entity.Mappa;


@Database(entities = {Mappa.class, Beacon.class, Arco.class}, version = 11)
public abstract class AppRoomDatabase extends RoomDatabase {

    public abstract MappaDao mappaDao();

    public abstract BeaconDao beaconDao();

    public abstract ArcoDao arcoDao();

    private static AppRoomDatabase INSTANCE;

    public static AppRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppRoomDatabase.class) {
                if (INSTANCE == null) {
                    // Create database here
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppRoomDatabase.class, "app_database").fallbackToDestructiveMigration()
                            .allowMainThreadQueries().build();
                }
            }
        }
        return INSTANCE;
    }
}
