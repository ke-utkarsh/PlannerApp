// Generated by Dagger (https://dagger.dev).
package ymsli.com.adminemployee.di;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.inject.Provider;
import ymsli.com.adminemployee.database.room.UserDatabase;
import ymsli.com.adminemployee.database.room.dao.UserDao;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata
@DaggerGenerated
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class AppModule_GetDaoFactory implements Factory<UserDao> {
  private final AppModule module;

  private final Provider<UserDatabase> userDatabaseProvider;

  public AppModule_GetDaoFactory(AppModule module, Provider<UserDatabase> userDatabaseProvider) {
    this.module = module;
    this.userDatabaseProvider = userDatabaseProvider;
  }

  @Override
  public UserDao get() {
    return getDao(module, userDatabaseProvider.get());
  }

  public static AppModule_GetDaoFactory create(AppModule module,
      Provider<UserDatabase> userDatabaseProvider) {
    return new AppModule_GetDaoFactory(module, userDatabaseProvider);
  }

  public static UserDao getDao(AppModule instance, UserDatabase userDatabase) {
    return Preconditions.checkNotNullFromProvides(instance.getDao(userDatabase));
  }
}
