package cat.jordihernandez.carefeed;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextClock;
import android.widget.Toast;

import java.util.Date;

import cat.jordihernandez.carefeed.model.RegistryMain;
import cat.jordihernandez.carefeed.view.FragmentMood;
import io.realm.Realm;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static int NUM_FRAGMENTS = 5;
    private TextClock txtClock;
    private Button btnSave;
    private LinearLayout layout_fragments;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private Fragment fragment;
    private short count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout_fragments = (LinearLayout) findViewById(R.id.layout_fragments);
        txtClock = (TextClock) findViewById(R.id.txtClock);

        btnSave = (Button) findViewById(R.id.btnSave);
        btnSave.setOnClickListener(this);
        btnSave.setText(getResources().getString(R.string.Next));
        count = 1;

        //instaciar administrador de fragments i de transactions
        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragment = FragmentMood.newInstance(null,null);
        fragmentTransaction.add(R.id.layout_fragments,fragment,"mood").commit();

        //TODO aïllar la gestió dels fragmnents i carregar i descarregar-los
        //TODO Gestió del button per carregar els fragments (next --> save)

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

        if(count < NUM_FRAGMENTS) {
            count++;
            if(count == NUM_FRAGMENTS)
                btnSave.setText(getResources().getString(R.string.Save));
        }else{
            Toast.makeText(this, "Desat", Toast.LENGTH_LONG).show();
            //saveRating(ratingMood.getRating());
        }


    }

    /**
     * Canviar al següent fragment
     */
    public void nextFragment() {

    }


}
