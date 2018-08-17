package cat.jordihernandez.carefeed.model;


import java.util.Date;

import io.realm.RealmObject;

/**
 * Created by jordi on 31/07/17.
 */

public class RegistryMain extends RealmObject {
    private Date data;
    private float mood, physical;

    public float getPhysical() {
        return physical;
    }

    public void setPhysical(float physical) {
        this.physical = physical;
    }

    public void setData(Date data) {
        this.data = data;
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
