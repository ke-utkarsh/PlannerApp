package ymsli.com.adminemployee.di;



import android.content.Context;

import androidx.room.Room;

import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;
import ymsli.com.adminemployee.database.room.UserDatabase;
import ymsli.com.adminemployee.database.room.dao.UserDao;

@Module
@InstallIn(SingletonComponent.class)
public class AppModule {



    @Singleton
    @Provides
    public String getUserDatabase() {
        return "USER_DATABASE";
    }


    @Singleton
    @Provides
    public UserDao getDao(UserDatabase userDatabase) {
        return userDatabase.getUserDao();
    }


    @Singleton
    @Provides
    public UserDatabase getRoomDatabaseInstance(@ApplicationContext Context context, String databaseName) {
        return Room.databaseBuilder(context, UserDatabase.class, databaseName).allowMainThreadQueries().fallbackToDestructiveMigration().build();
    }
}
