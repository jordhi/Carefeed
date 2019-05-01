package cat.jordihernandez.carefeed.view;


import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import cat.jordihernandez.carefeed.R;
import cat.jordihernandez.carefeed.model.RegistryMain;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentChart#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentChart extends Fragment {
    Realm realm;
    private PieChart pieChart;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public FragmentChart() {
        // Required empty public constructor
        realm = Realm.getDefaultInstance();
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentChart.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentChart newInstance(String param1, String param2) {
        FragmentChart fragment = new FragmentChart();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chart, container, false);
        pieChart = view.findViewById(R.id.frgPieChart);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        createChart();
    }

    private void createChart() {
        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(5,10,5,5);

        pieChart.setDragDecelerationFrictionCoef(0.95f);
        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(Color.WHITE);
        pieChart.setTransparentCircleRadius(61f);

        final ArrayList<PieEntry> yValues = new ArrayList<>();
        /*yValues.add(new PieEntry(34f,"Vietnam"));
        yValues.add(new PieEntry(24f,"USA"));
        yValues.add(new PieEntry(74f,"UK"));
        yValues.add(new PieEntry(20f,"India"));
        yValues.add(new PieEntry(55f,"Russia"));
        yValues.add(new PieEntry(34f,"Japan"));*/
        ArrayList<RegistryMain> registryMains = getWorstMood();
        registryMains.forEach(r -> yValues.add(new PieEntry(r.getMood(),"Dilluns")));

        PieDataSet dataSet = new PieDataSet(yValues, getResources().getString(R.string.daysweek));
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);
        dataSet.setColors(ColorTemplate.JOYFUL_COLORS);

        PieData data = new PieData(dataSet);
        data.setValueTextSize(10f);
        data.setValueTextColor(Color.YELLOW);

        pieChart.setData(data);
    }

    private ArrayList<RegistryMain> getWorstMood() {
        final ArrayList<RegistryMain> dataset = new ArrayList<>();
        RealmResults results = realm.where(RegistryMain.class)
                .sort("mood", Sort.ASCENDING)
                .lessThan("mood",2.5f)
                .findAll();
        dataset.addAll(realm.copyFromRealm(results));
        return dataset;
    }

}
