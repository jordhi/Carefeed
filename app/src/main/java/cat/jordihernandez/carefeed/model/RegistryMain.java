package cat.jordihernandez.carefeed.model;


import java.util.Calendar;
import java.util.Date;

import io.realm.RealmObject;

/**
 * Created by jordi on 31/07/17.
 */

public class RegistryMain extends RealmObject {
    private Date data;
    private int year, month, day;
    private float mood, physical;

    public float getPhysical() {
        return physical;
    }

    public void setPhysical(float physical) {
        this.physical = physical;
    }

    public void setData(Date data) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(data);
        this.data = data;
        this.year = cal.get(Calendar.YEAR);
        this.month = cal.get(Calendar.MONTH);
        this.day = cal.get(Calendar.DAY_OF_MONTH);
    }

    public void setMood(float value) {
        this.mood = value;
    }

    public Date getData() {
        return data;
    }

    public float getMood() {
        return mood;
    }


}
