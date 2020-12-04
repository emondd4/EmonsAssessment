package com.emon.emonsassessment.RoomData;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.emon.emonsassessment.RoomModel.User;

@Dao
public interface UserDAO {

    @Query("SELECT * FROM User WHERE UserName = :UserName and  Password = :Password")
    User getUser (String UserName, String Password);

    @Insert
    void insert(User user);

    @Update
    void update(User user);

    @Delete
    void delete(User user);
}
