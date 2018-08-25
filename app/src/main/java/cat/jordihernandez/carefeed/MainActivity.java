package cat.jordihernandez.carefeed;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextClock;
import android.widget.Toast;

import java.util.Date;

import cat.jordihernandez.carefeed.control.SettingsActivity;
import cat.jordihernandez.carefeed.model.RegistryMain;
import cat.jordihernandez.carefeed.view.FragmentMood;
import cat.jordihernandez.carefeed.view.FragmentPhysic;
import io.realm.Realm;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static int NUM_FRAGMENTS = 2;
    private TextClock txtClock;
    private Button btnSave;
    private LinearLayout layout_fragments;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private Fragment fragment;
    private short count;

    private RegistryMain register = new RegistryMain();

    public RegistryMain getRegister() {
        return register;
    }

    public void setRegister(RegistryMain register) {
        this.register = register;
    }

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

        //instanciar administrador de fragments i de transactions
        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragment = FragmentMood.newInstance(null,null);
        fragmentTransaction.add(R.id.layout_fragments,fragment,"mood").commit();

        //TODO al inciar el rating bar inciai amb 0.5 però si no es toca desa 0
        //TODO crear primaykey per controlar les repeticions
        //TODO avisar que ja s'han desat dades en el mateix dia
        //TODO Afegir un menu configuració per decicidir quants registres es fan per dia
    }

    //Save rate value and date date
    public void saveRating() {
        Date date = new Date();
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        RegistryMain data = realm.copyToRealm(register);
        data.setData(date);
        realm.commitTransaction();

        Log.i("DATA","saved:" + register.getMood() + ":" + date);
    }


    @Override
    public void onClick(View view) {

        if(count < NUM_FRAGMENTS) {
            count++;

            if(count == NUM_FRAGMENTS)
                btnSave.setText(getResources().getString(R.string.Save));
        }else{
            Toast.makeText(this, "Desat", Toast.LENGTH_LONG).show();
            btnSave.setText(getResources().getString(R.string.Next));
            count = 1;
            saveRating();
        }
        nextFragment();


    }

    /**
     * Canviar al següent fragment
     */
    public void nextFragment() {
        Class fragmentClass = null;
        switch (count) {
            case 1: fragmentClass = FragmentMood.class; break;
            case 2: fragmentClass = FragmentPhysic.class; break;
        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        fragmentManager.beginTransaction()
                .replace(R.id.layout_fragments,fragment)
                .disallowAddToBackStack() //button back not return to the last fragment
                .commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mainmenu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                startActivity(new Intent(this, SettingsActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }
}
