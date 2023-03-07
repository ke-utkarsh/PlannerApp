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
public class HomeViewModel extends AndroidViewModel {
    Repository repository;
    private MutableLiveData<User> _isAlreadyRegistered = new MutableLiveData<>();
    public LiveData<User> isAlreadyRegistered(){
        return _isAlreadyRegistered;
    }

    private MutableLiveData<Integer> _count = new MutableLiveData<>();
    public LiveData<Integer> count(){
        return _count;
    }
    private MutableLiveData<Integer> _teamcount = new MutableLiveData<>();
    public LiveData<Integer> teamcount(){
        return _teamcount;
    }

    private MutableLiveData<List<Team>> _teams = new MutableLiveData<>();
    public LiveData<List<Team>> team(){
        return _teams;
    }

    private MutableLiveData<Boolean> _update = new MutableLiveData<>();
    public LiveData<Boolean> update(){
        return _update;
    }

    private MutableLiveData<Boolean> _update2 = new MutableLiveData<>();
    public LiveData<Boolean> update2(){
        return _update2;
    }

    private MutableLiveData<Boolean> _delete = new MutableLiveData<>();
    public LiveData<Boolean> delete(){
        return _delete;
    }


    @Inject
    public HomeViewModel(@NonNull Application application,Repository repository) {
        super(application);
        this.repository=repository;
    }

    public void searchUserByEid(String uName) {
        User userById = repository.searchUserByEid(uName);
        if(userById!=null){
            _isAlreadyRegistered.postValue(userById);
        }
        else {
            _isAlreadyRegistered.postValue(null);
        }
    }


    public void countNumEmp(String uName){
        int i=repository.getMangerEmployeeCount(uName);
        _count.postValue(i);
    }

    public void countTeams(String uName){
        int i=repository.getTeamCount(uName);
    }

    public void getTeamData(String uname){
        List<Team> t=repository.getTeamData(uname);
        _teams.postValue(t);
    }

    public void getEmpCountData(String uname){
        List<User> u=repository.getEmpCount(uname);
        _count.postValue(u.size());
    }

    public void updateManagerByEid(String manager,String team, String userName){
        int i=repository.updateManagerDataByEid(manager,team,userName);
        if(i>0){
            _update.postValue(true);
        }else{
            _update.postValue(false);
        }
    }

    public void deleteTeam(Team team){
        int i=repository.deleteTeam(team);
        if(i>0){
            _delete.postValue(true);
        }else{
            _delete.postValue(false);
        }
    }

    public void updateUserAfterTeamDeletion(String teamName){
        int i=repository.updateUserAfterDeleteTeamData(teamName);
        if(i>0){
            _update2.postValue(true);
        }else{
            _update2.postValue(false);
        }
    }

}
