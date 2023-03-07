package ymsli.com.adminemployee.ui.view.fragment;


import static androidx.navigation.fragment.FragmentKt.findNavController;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dagger.hilt.android.AndroidEntryPoint;
import es.dmoral.toasty.Toasty;
import ymsli.com.adminemployee.R;
import ymsli.com.adminemployee.database.room.entity.User;
import ymsli.com.adminemployee.database.sharefpref.SessionManagement;
import ymsli.com.adminemployee.ui.view.activity.HomeActivity;
import ymsli.com.adminemployee.ui.view.activity.PlannerActivity;
import ymsli.com.adminemployee.ui.viewModel.LoginViewModel;


//Login fragment
@AndroidEntryPoint
public class LoginFragment extends Fragment {

    private static final int REQUEST_LOCATION = 100;
    private LoginViewModel mViewModel;
    View view;
    Activity activity;
    TextInputEditText uname;
    TextInputEditText pass;
    Boolean isButtonPressed=false;



    //called when Fragment is created.
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_login, container, false);
        initfunc();

        uname=view.findViewById(R.id.userid);
        pass=view.findViewById(R.id.password);


        view.findViewById(R.id.tvRegister).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findNavController(getParentFragment()).navigate(R.id.action_loginFragment_to_registerFragment);
            }
        });
        view.findViewById(R.id.btnLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isButtonPressed=true;
                if(checkValidations()){
                    mViewModel.searchUserByEid(uname.getText().toString());
                }
            }
        });
        Observer();
        return view;
    }

    //checking validations of input fields.
    private boolean checkValidations(){
        if(uname.getText().toString().trim().length()==0){
            Toasty.error(activity,"Ein can't be empty",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(!uname.getText().toString().trim().startsWith("YM")||uname.getText().toString().length()<3){
            Toasty.error(activity,"Wrong format of Ein",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(pass.getText().toString().trim().length()==0){
            Toasty.error(activity,"Password can't be empty",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(pass.getText().toString().trim().length()<8||pass.getText().toString().length()>20){
            Toasty.error(activity,"Password length must be greater than 8 and less than 20",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(pass.getText().toString().trim().length()>0){
            String regex = "^(?=.*[0-9])" + "(?=.*[a-z])(?=.*[A-Z])" + "(?=.*[@#$%^&+=])" + "(?=\\S+$).{8,20}$";
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(pass.getText().toString().trim());
            if(!m.matches()){
                Toasty.error(activity,"Password must have atleast one upper case, one lower case and one special character",Toast.LENGTH_SHORT).show();
                return false;
            }
        }

        return true;
    }

    private void initfunc() {
        activity = getActivity();
        mViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
    }

    private void Observer(){

        mViewModel.isAlreadyRegistered().observe(getActivity(), new Observer<User>() {
            @Override
            public void onChanged(User user) {
                if(user==null&&isButtonPressed){
                    Toasty.error(activity, "User not found", Toast.LENGTH_SHORT).show();

                }else{
                    mViewModel.loginUser(uname.getText().toString(),pass.getText().toString());
                }
            }
        });

        mViewModel.login().observe(getActivity(), new Observer<User>() {
            @Override
            public void onChanged(User user) {
                if(user==null){
                    if(isButtonPressed){
                        Toasty.error(activity, "Wrong password, please try again", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    SessionManagement sessionManagement = new SessionManagement(getContext());
                    sessionManagement.saveSession(user.getEmpNumber(),user.getPassword());
                    if(user.getRights().contains("Admin")){
                        movetoHomeActivity(user.getUserName(),user.getEmpNumber());
                    }else{
                        movetoPlannerActivity(user.getUserName());
                    }
                }
            }
        });
    }




    @Override
    public void onStart() {
        super.onStart();
        SessionManagement sessionManagement = new SessionManagement(getContext());
        String isLoggedIn = sessionManagement.getUserId();
        if(!isLoggedIn.equals("")){
            mViewModel.loginUser(sessionManagement.getUserId(),sessionManagement.getPassword());
        }
    }

    private void movetoHomeActivity(String s,String e) {
        //Toast.makeText(activity, , Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getContext(), HomeActivity.class);
        intent.putExtra("UserName",s);
        intent.putExtra("Ein",e);
        startActivity(intent);
        getActivity().finish();
    }

    private void movetoPlannerActivity(String s){
        Intent intent = new Intent(getContext(), PlannerActivity.class);
        intent.putExtra("UserName",s);
        intent.putExtra("ViewOnly",false);
        startActivity(intent);
        getActivity().finish();
    }




}