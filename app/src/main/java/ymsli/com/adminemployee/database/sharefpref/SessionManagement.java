package ymsli.com.adminemployee.database.sharefpref;

import android.content.Context;
import android.content.SharedPreferences;


public class SessionManagement {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String SHARED_PREF =  "session";

    public SessionManagement(Context context){
        sharedPreferences = context.getSharedPreferences(SHARED_PREF,Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void saveSession(String userId,String password){
        editor.putString("UserId",userId).commit();
        editor.putString("Password",password).commit();
    }

    public String getUserId(){
        return sharedPreferences.getString("UserId","");
    }
    public String getPassword(){
        return sharedPreferences.getString("Password","");
    }
}
