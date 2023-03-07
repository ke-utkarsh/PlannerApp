package ymsli.com.adminemployee.ui.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import ymsli.com.adminemployee.database.room.entity.Team;
import ymsli.com.adminemployee.database.room.entity.User;
import ymsli.com.adminemployee.network.Repository;

@HiltViewModel
public class EmployeeListViewModel extends AndroidViewModel {
    Repository repository;
    private MutableLiveData<List<User>> _employeeList = new MutableLiveData<>();
    public LiveData<List<User>> employeeList(){
        return _employeeList;
    }
    private MutableLiveData<Team> _teamData = new MutableLiveData<>();
    public LiveData<Team> teamData(){
        return _teamData;
    }
    private MutableLiveData<Boolean> _update2 = new MutableLiveData<>();
    public LiveData<Boolean> update2(){
        return _update2;
    }

    @Inject
    public EmployeeListViewModel(@NonNull Application application, Repository repository) {
        super(application);
        this.repository=repository;
    }

    public void getEmployeeFromTeam(String team){
        List<User> u=repository.getEmployeeData(team);
        _employeeList.postValue(u);
    }

    public void getTeamData(String team){
        Team t=repository.getTeam(team);
        _teamData.postValue(t);
    }

    public void removeTeamForEmployee(String eid){
        int i=repository.updateTeamByEin(eid);
        if(i>0){
            _update2.postValue(true);
        }else{
            _update2.postValue(false);
        }
    }
}
