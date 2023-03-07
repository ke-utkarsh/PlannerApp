package ymsli.com.adminemployee.ui.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import ymsli.com.adminemployee.database.room.entity.Team;
import ymsli.com.adminemployee.database.room.entity.User;
import ymsli.com.adminemployee.network.Repository;

@HiltViewModel
public class CreateTeamViewModel extends AndroidViewModel {
    Repository repository;
    private MutableLiveData<User> _isAlreadyRegistered = new MutableLiveData<>();
    public LiveData<User> isAlreadyRegistered(){
        return _isAlreadyRegistered;
    }

    private MutableLiveData<Boolean> _isRegister = new MutableLiveData<>();
    public LiveData<Boolean> isRegister(){
        return _isRegister;
    }

    private MutableLiveData<Boolean> _update = new MutableLiveData<>();
    public LiveData<Boolean> update(){
        return _update;
    }

    private MutableLiveData<Integer> _count = new MutableLiveData<>();
    public LiveData<Integer> count(){
        return _count;
    }

    private MutableLiveData<Team> _teamData = new MutableLiveData<>();
    public LiveData<Team> teamData(){
        return _teamData;
    }

    private MutableLiveData<List<User>> _employeeList = new MutableLiveData<>();
    public LiveData<List<User>> employeeList(){
        return _employeeList;
    }

    @Inject
    public CreateTeamViewModel(@NonNull Application application, Repository repository) {
        super(application);
        this.repository=repository;
    }

    public void searchFreeMembers(){
        List<User> u=repository.getFreeEmployeeData("");
        _employeeList.postValue(u);
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



    public void insertTeam(Team team){
        long i= repository.insertTeam(team);
        if(i>0){
            _isRegister.postValue(true);
        }else{
            _isRegister.postValue(false);
        }

    }
    public void getTeamData(String teamName){
        Team t=repository.getTeam(teamName);
        _teamData.postValue(t);
    }

}
