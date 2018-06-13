package cat.jordihernandez.carefeed.model;


import java.util.Date;

import io.realm.RealmObject;

/**
 * Created by jordi on 31/07/17.
 */

public class RegistryMain extends RealmObject {
    private Date data;
    private float value;

    public void setData(Date data) {
        this.data = data;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public Date getData() {
        return data;
    }

    public float getValue() {
        return value;
    }
}
