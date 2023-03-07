package ymsli.com.adminemployee.database.room.entity;

public class CalendarModel {
    private String date;
    private boolean selected;

    public CalendarModel(String date, boolean selected) {
        this.date = date;
        this.selected = selected;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
