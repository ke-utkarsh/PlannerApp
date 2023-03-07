package ymsli.com.adminemployee.ui.view.fragment;

import static android.content.Context.MODE_PRIVATE;
import static androidx.navigation.fragment.FragmentKt.findNavController;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dagger.hilt.android.AndroidEntryPoint;
import es.dmoral.toasty.Toasty;
import ymsli.com.adminemployee.R;
import ymsli.com.adminemployee.database.room.entity.User;
import ymsli.com.adminemployee.ui.viewModel.RegisterViewModel;


@AndroidEntryPoint
public class RegisterFragment extends Fragment {

    private RegisterViewModel mViewModel;
    Activity activity;
    TextInputEditText uname;
    TextInputEditText pass;
    TextInputEditText cPass;
    AutoCompleteTextView actv;
    TextInputEditText enumber;
    int eidi;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_register, container, false);
        initfunc();

        ArrayAdapter arrayAdapter= new ArrayAdapter(getActivity(),R.layout.dropdown_item,getResources().getStringArray(R.array.rights));
        actv=view.findViewById(R.id.autoCompleteTextView);
        actv.setAdapter(arrayAdapter);
        uname=view.findViewById(R.id.userid);
        pass=view.findViewById(R.id.password);
        cPass=view.findViewById(R.id.password2);
        enumber=view.findViewById(R.id.empid);


        //getting new ein from sharedpref and setting edittext non editable
        SharedPreferences sh= requireActivity().getSharedPreferences("MySharedPref2", MODE_PRIVATE);
        eidi=sh.getInt("Ein",7);
        String eidS="YM"+eidi;
        enumber.setText(eidS);
        enumber.setKeyListener(null);
        enumber.setEnabled(false);
        enumber.setInputType(InputType.TYPE_NULL);
        enumber.setTextIsSelectable(false);

        view.findViewById(R.id.tvLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findNavController(getParentFragment()).navigate(R.id.action_registerFragment_to_loginFragment);
            }
        });
        view.findViewById(R.id.btnRegister).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkValidations()){
                    mViewModel.searchUserByEid(enumber.getText().toString());
                }
            }
        });

        Observer();
        return view;
    }

    private boolean checkValidations(){
        if(enumber.getText().toString().trim().length()==0){
            Toasty.error(activity,"Ein can't be empty",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(!enumber.getText().toString().trim().startsWith("YM")||enumber.getText().toString().length()<3){
            Toasty.error(activity,"Wrong format of Ein",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(uname.getText().toString().trim().length()==0){
            Toasty.error(activity,"Username can't be empty",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(uname.getText().toString().trim().length()<4){
            Toasty.error(activity, "Username must be more than 4 characters", Toast.LENGTH_SHORT).show();

            return false;
        }
        if(pass.getText().toString().trim().length()==0){
            Toasty.error(activity,"Password can't be empty",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(pass.getText().toString().trim().length()<8||pass.getText().toString().trim().length()>20){
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
        if(cPass.getText().toString().trim().length()==0){
            Toasty.error(activity,"Confirm password can't be empty",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(!pass.getText().toString().trim().equals(cPass.getText().toString())){
            Toasty.error(activity, "Confirm password is different from password", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(!actv.getText().toString().trim().equals("Admin")){
            if(!actv.getText().toString().trim().equals("Employee")){
                Toasty.error(activity, "Please select admin/employee", Toast.LENGTH_SHORT).show();

                return false;
            }
        }
        return true;
    }

    //to initalize the fragment
    private void initfunc() {
        activity = getActivity();
        mViewModel = new ViewModelProvider(this).get(RegisterViewModel.class);
    }

    private void Observer() {
        //
        mViewModel.isAlreadyRegistered().observe(getActivity(), new Observer<User>() {
            @Override
            public void onChanged(User user) {
                if(user==null){
                   User u=new User(enumber.getText().toString(),uname.getText().toString(),pass.getText().toString(),actv.getText().toString(),
                           "","","");
                    mViewModel.registerUser(u);
                }else{
                    Toasty.error(activity, "Employee with same eid already exists", Toast.LENGTH_SHORT).show();

                }
            }
        });
        mViewModel.isRegister().observe(getActivity(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean){
                    //setting new ein value on sharedpreferences
                    SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("MySharedPref2", MODE_PRIVATE);
                    SharedPreferences.Editor myEdit = sharedPreferences.edit();
                    myEdit.putInt("Ein",eidi+1);
                    myEdit.apply();

                    Toasty.success(getActivity(),"Registered, Please LogIn to continue",Toast.LENGTH_SHORT).show();
                    findNavController(getParentFragment()).navigate(R.id.action_registerFragment_to_loginFragment);
                }else{
                    Toasty.error(activity, "Something went wrong please try again!!", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }



}