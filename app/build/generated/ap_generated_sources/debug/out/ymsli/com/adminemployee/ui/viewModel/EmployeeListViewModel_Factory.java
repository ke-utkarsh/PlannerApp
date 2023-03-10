// Generated by Dagger (https://dagger.dev).
package ymsli.com.adminemployee.ui.viewModel;

import android.app.Application;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.inject.Provider;
import ymsli.com.adminemployee.network.Repository;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class EmployeeListViewModel_Factory implements Factory<EmployeeListViewModel> {
  private final Provider<Application> applicationProvider;

  private final Provider<Repository> repositoryProvider;

  public EmployeeListViewModel_Factory(Provider<Application> applicationProvider,
      Provider<Repository> repositoryProvider) {
    this.applicationProvider = applicationProvider;
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public EmployeeListViewModel get() {
    return newInstance(applicationProvider.get(), repositoryProvider.get());
  }

  public static EmployeeListViewModel_Factory create(Provider<Application> applicationProvider,
      Provider<Repository> repositoryProvider) {
    return new EmployeeListViewModel_Factory(applicationProvider, repositoryProvider);
  }

  public static EmployeeListViewModel newInstance(Application application, Repository repository) {
    return new EmployeeListViewModel(application, repository);
  }
}
