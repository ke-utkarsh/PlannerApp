package ymsli.com.adminemployee.database.room;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import ymsli.com.adminemployee.database.room.entity.Team;

public class Convertor {
    @TypeConverter
    public String fromValuesToList(ArrayList<Team> value) {
        if (value== null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Team>>() {}.getType();
        return gson.toJson(value, type);
    }

    @TypeConverter
    public ArrayList<Team> toOptionValuesList(String value) {
        if (value== null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Team>>() {
        }.getType();
        return gson.fromJson(value, type);
    }

    @TypeConverter
    public String fromValuesToStringList(ArrayList<String> value) {
        if (value== null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<String>>() {}.getType();
        return gson.toJson(value, type);
    }

    @TypeConverter
    public ArrayList<String> toOptionValuesStringList(String value) {
        if (value== null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<String>>() {
        }.getType();
        return gson.fromJson(value, type);
    }
}
