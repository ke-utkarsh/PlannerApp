package ymsli.com.adminemployee.ui.viewModel;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.disposables.CompositeDisposable;
import ymsli.com.adminemployee.database.room.entity.Team;
import ymsli.com.adminemployee.database.room.entity.User;
import ymsli.com.adminemployee.network.Repository;

@HiltViewModel
public class PlannerViewModel extends AndroidViewModel {
    Repository repository;
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    public Context context;

    private MutableLiveData<User> _isAlreadyRegistered = new MutableLiveData<>();
    public LiveData<User> isAlreadyRegistered(){
        return _isAlreadyRegistered;
    }

    private MutableLiveData<Team> _teamData = new MutableLiveData<>();
    public LiveData<Team> teamData(){
        return _teamData;
    }

    private MutableLiveData<Boolean> _dateUpdate = new MutableLiveData<>();
    public LiveData<Boolean> dateUpdate(){
        return _dateUpdate;
    }

    @Inject
    public PlannerViewModel(Application application, Repository repository) {
        super(application);
        this.repository = repository;
        context = application.getApplicationContext();
    }

    public void searchUserByName(String uName) {
        User userById = repository.searchUserByName(uName);
        if(userById!=null){
            _isAlreadyRegistered.postValue(userById);
        }
        else {
            _isAlreadyRegistered.postValue(null);
        }
    }

    public void getTeamData(String team){
        Team t=repository.getTeam(team);
        _teamData.postValue(t);
    }

    public void updateLeaveDates(String uname,String leave){
        int i=repository.updateDateData(uname,leave);
        if(i>0){
            _dateUpdate.postValue(true);
        }else{
            _dateUpdate.postValue(false);
        }
    }
}
