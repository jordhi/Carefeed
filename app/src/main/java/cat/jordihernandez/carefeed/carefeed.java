package cat.jordihernandez.carefeed;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by jordi on 31/07/17.
 */

public class carefeed extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("carefeed.realm")
                .schemaVersion(0)
                //.migration(new Migration())
                .build();
        Realm.setDefaultConfiguration(config);
    }
}
