package com.emon.emonsassessment.RoomData;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.emon.emonsassessment.RoomModel.User;

@Database(entities = {User.class}, version = 1, exportSchema = false)
public abstract class UserDataBase extends RoomDatabase {

    public abstract UserDAO getUserDao();

}
