package ymsli.com.adminemployee.ui.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import ymsli.com.adminemployee.database.room.entity.User;
import ymsli.com.adminemployee.network.Repository;

@HiltViewModel
public class AddMemberViewModel extends AndroidViewModel {
    Repository repository;
    private MutableLiveData<User> _isAlreadyRegistered = new MutableLiveData<>();
    public LiveData<User> isAlreadyRegistered(){
        return _isAlreadyRegistered;
    }
    private MutableLiveData<List<User>> _employeeList = new MutableLiveData<>();
    public LiveData<List<User>> employeeList(){
        return _employeeList;
    }
    private MutableLiveData<Boolean> _update = new MutableLiveData<>();
    public LiveData<Boolean> update(){
        return _update;
    }
    private MutableLiveData<List<User>> _employeeList1 = new MutableLiveData<>();
    public LiveData<List<User>> employeeList1(){
        return _employeeList1;
    }

    @Inject
    public AddMemberViewModel(@NonNull Application application, Repository repository) {
        super(application);
        this.repository=repository;
    }

    public void searchFreeMembers(){
        List<User> u=repository.getFreeEmployeeData("");
        _employeeList.postValue(u);
    }


    public void getEmployeeFromTeam(String team){
        List<User> u=repository.getEmployeeData(team);
        _employeeList1.postValue(u);
    }
    public void updateManagerByEid(String manager,String team, String eid){
        int i=repository.updateManagerDataByEid(manager,team,eid);
        if(i>0){
            _update.postValue(true);
        }else{
            _update.postValue(false);
        }
    }

    public void updateManager(String manager,String team, String userName){
        int i=repository.updateManagerData(manager,team,userName);
        if(i>0){
            _update.postValue(true);
        }else{
            _update.postValue(false);
        }
    }
}
