package ymsli.com.adminemployee.database.room.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

//making an entity for team table
@Entity(tableName = "TeamTable")
public class Team {
    @PrimaryKey(autoGenerate = false)
    @NonNull
    private String name;
    private String tech;
    private String manager;

    public Team(String name, String tech, String manager) {
        this.name = name;
        this.tech = tech;
        this.manager=manager;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTech() {
        return tech;
    }

    public void setTech(String tech) {
        this.tech = tech;
    }
    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }
}
