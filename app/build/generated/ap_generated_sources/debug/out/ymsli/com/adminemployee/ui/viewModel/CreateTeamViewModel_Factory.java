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
public final class CreateTeamViewModel_Factory implements Factory<CreateTeamViewModel> {
  private final Provider<Application> applicationProvider;

  private final Provider<Repository> repositoryProvider;

  public CreateTeamViewModel_Factory(Provider<Application> applicationProvider,
      Provider<Repository> repositoryProvider) {
    this.applicationProvider = applicationProvider;
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public CreateTeamViewModel get() {
    return newInstance(applicationProvider.get(), repositoryProvider.get());
  }

  public static CreateTeamViewModel_Factory create(Provider<Application> applicationProvider,
      Provider<Repository> repositoryProvider) {
    return new CreateTeamViewModel_Factory(applicationProvider, repositoryProvider);
  }

  public static CreateTeamViewModel newInstance(Application application, Repository repository) {
    return new CreateTeamViewModel(application, repository);
  }
}
