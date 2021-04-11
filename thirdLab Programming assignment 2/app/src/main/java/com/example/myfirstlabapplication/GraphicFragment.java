package com.example.myfirstlabapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GraphicFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GraphicFragment extends Fragment {
    PieChart pieChart;
    private LineGraphSeries<DataPoint> series;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public GraphicFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GraphicFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GraphicFragment newInstance(String param1, String param2) {
        GraphicFragment fragment = new GraphicFragment();
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
    private class DrawView extends View {
        Paint paint = new Paint();

        public DrawView(Context context) {
            super(context);
            paint.setColor(Color.BLACK);
        }
        public DrawView(Context context, AttributeSet attrs) {
            super(context, attrs);
            paint.setColor(Color.BLACK);
        }
        public DrawView(Context context, AttributeSet attrs, int defStyle) {
            super(context, attrs, defStyle);
            paint.setColor(Color.BLACK);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            canvas.drawLine(0, 0, 50, 50, paint);
            canvas.drawLine(50, 0, 0, 50, paint);
        }

        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);

            int parentWidth = MeasureSpec.getSize(widthMeasureSpec);
            int parentHeight = MeasureSpec.getSize(heightMeasureSpec);
            this.setMeasuredDimension(parentWidth, parentHeight);
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        double x = 0.01;
        double y = 4;
        final int[] MY_COLORS = {Color.rgb(255, 255, 0),
                Color.rgb(0, 128, 0),
                Color.rgb(127, 255, 212),
                Color.rgb(255, 0, 0),
                Color.rgb(0, 0, 255)};

        GraphView graphView = getActivity().findViewById(R.id.graph);
        Switch switcher = getActivity().findViewById(R.id.switcher);
        switcher.setChecked(false);
        series = new LineGraphSeries<>();
        switcher.setOnCheckedChangeListener((CompoundButton buttonView, boolean check) -> {
            if (check == true) {
                graphView.setVisibility(View.INVISIBLE);
                pieChart.setVisibility(View.VISIBLE);
            } else {
                pieChart.setVisibility(View.INVISIBLE);
                graphView.setVisibility(View.VISIBLE);
            }
        });

        for (double i = x; i <= y; i += 0.1) {
            series.appendData(new DataPoint(i, Math.log(i)), true, 100);
        }

        graphView.addSeries(series);
        pieChart = Objects.requireNonNull(getActivity()).findViewById(R.id.pieChart);
        pieChart.setUsePercentValues(true);
        pieChart.setHoleRadius(25f);
        pieChart.setTransparentCircleRadius(25f);

        List<PieEntry> value = new ArrayList<>();
        value.add(new PieEntry(10f, "Yellow"));
        value.add(new PieEntry(20f, "Green"));
        value.add(new PieEntry(25f, "Light Blue"));
        value.add(new PieEntry(5f, "Red"));
        value.add(new PieEntry(40f, "Blue"));


        PieDataSet pieDataSet = new PieDataSet(value, "Colours");
        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);

        ArrayList<Integer> colors = new ArrayList<>();
        for (int c : MY_COLORS) colors.add(c);
        pieDataSet.setColors(MY_COLORS[0], MY_COLORS[1], MY_COLORS[2], MY_COLORS[3], MY_COLORS[4]);
        pieChart.animateXY(1400, 1400);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_graphic, container, false);
    }
}