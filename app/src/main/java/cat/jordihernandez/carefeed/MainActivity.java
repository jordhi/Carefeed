package cat.jordihernandez.carefeed;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextClock;
import java.util.Date;

import cat.jordihernandez.carefeed.model.RegistryMain;
import io.realm.Realm;

public class MainActivity extends AppCompatActivity {
    private TextClock txtClock;
    private RatingBar ratingMood;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtClock = (TextClock) findViewById(R.id.txtClock);
        ratingMood = (RatingBar) findViewById(R.id.rbarMood);
        btnSave = (Button) findViewById(R.id.btnSave);

        ratingMood.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Log.i("DATA","ranting:" + rating );

            }

        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveRating(ratingMood.getRating());
            }
        });

    }

    //Save rate value and date date
    public void saveRating(float r) {
        Date date = new Date();
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        RegistryMain data = realm.createObject(RegistryMain.class);
        data.setData(date);
        data.setValue(r);
        realm.commitTransaction();

        Log.i("DATA","saved:" + r + ":" + date);
    }


}
