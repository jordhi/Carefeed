package cat.jordihernandez.carefeed.view;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextClock;

import cat.jordihernandez.carefeed.R;

public class ActivityChart extends AppCompatActivity {
    private static int NUM_FRAGMENTS = 2;
    private LinearLayout layout_fragmentsChart;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        layout_fragmentsChart = (LinearLayout) findViewById(R.id.layout_fragmentChart);

        //instanciar administrador de fragments i de transactions
        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragment = FragmentChart.newInstance(null,null);
        fragmentTransaction.add(R.id.layout_fragmentChart,fragment,"chart").commit();

    }


}
