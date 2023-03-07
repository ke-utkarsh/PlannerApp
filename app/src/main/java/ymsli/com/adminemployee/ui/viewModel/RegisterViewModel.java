package ymsli.com.adminemployee.ui.viewModel;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.io.Closeable;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.disposables.CompositeDisposable;
import ymsli.com.adminemployee.database.room.entity.User;
import ymsli.com.adminemployee.network.Repository;

@HiltViewModel
public class RegisterViewModel extends AndroidViewModel {

    private MutableLiveData<Boolean> _isRegister = new MutableLiveData<>();
    public LiveData<Boolean> isRegister(){
        return _isRegister;
    }

    private MutableLiveData<User> _isAlreadyRegistered = new MutableLiveData<>();
    public LiveData<User> isAlreadyRegistered(){
        return _isAlreadyRegistered;
    }
    Repository repository;
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    public Context context;

    @Inject
    public RegisterViewModel(Application application, Repository repository) {
        super(application);
        this.repository = repository;
        context = application.getApplicationContext();
    }

    public void registerUser(User user){

        Long check =  repository.insertUser(user);
        if(check>0){
            _isRegister.postValue(true);
        }
        else {
            _isRegister.postValue(false);
        }
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