package ymsli.com.adminemployee.ui.viewModel;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.disposables.CompositeDisposable;
import ymsli.com.adminemployee.database.room.entity.User;
import ymsli.com.adminemployee.network.Repository;

@HiltViewModel
public class LoginViewModel extends AndroidViewModel {

    Repository repository;
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    public Context context;

    private MutableLiveData<Boolean> isLoggedIn = new MutableLiveData<>();
    public LiveData<Boolean> loggedIn(){
        return isLoggedIn;
    }

    private MutableLiveData<User> _isAlreadyRegistered = new MutableLiveData<>();
    public LiveData<User> isAlreadyRegistered(){
        return _isAlreadyRegistered;
    }

    private MutableLiveData<User> _login = new MutableLiveData<>();
    public LiveData<User> login(){
        return _login;
    }





    @Inject
    public LoginViewModel(Application application,Repository repository) {
        super(application);
        this.repository = repository;
        context = getApplication().getApplicationContext();
    }

    public void loginUser(String uName,String password){
        User user = repository.userLogin(uName,password);
        _login.postValue(user);
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

}