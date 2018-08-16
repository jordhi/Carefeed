package cat.jordihernandez.carefeed;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextClock;
import java.util.Date;

import cat.jordihernandez.carefeed.model.RegistryMain;
import io.realm.Realm;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextClock txtClock;

    private Button btnSave;
    private LinearLayout layout_fragments;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout_fragments = (LinearLayout) findViewById(R.id.layout_fragments);
        txtClock = (TextClock) findViewById(R.id.txtClock);

        btnSave = (Button) findViewById(R.id.btnSave);
        btnSave.setOnClickListener(this);

        //instaciar administrador de fragments i de transactions
        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragment = FragmentMood.newInstance(null,null);
        fragmentTransaction.add(R.id.layout_fragments,fragment).commit();

    }

    //Save rate value and date date
    public void saveRating(float r) {
        Date date = new Date();
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        RegistryMain data = realm.createObject(RegistryMain.class);
        data.setData(date);
        data.setMood(r);
        realm.commitTransaction();

        Log.i("DATA","saved:" + r + ":" + date);
    }


    @Override
    public void onClick(View view) {
      /*  btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveRating(ratingMood.getRating());
            }
        });*/

    }


}
