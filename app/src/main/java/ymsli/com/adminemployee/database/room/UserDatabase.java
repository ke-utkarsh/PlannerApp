package ymsli.com.adminemployee.database.room;


import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import ymsli.com.adminemployee.database.room.dao.UserDao;
import ymsli.com.adminemployee.database.room.entity.Team;
import ymsli.com.adminemployee.database.room.entity.User;

@Database(entities = {User.class, Team.class},version = 5,exportSchema = false)
@TypeConverters(Convertor.class)
public abstract class UserDatabase extends RoomDatabase {
    public abstract UserDao getUserDao();
}


