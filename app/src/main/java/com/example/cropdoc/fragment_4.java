package com.example.cropdoc;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragment_4#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragment_4 extends Fragment {

    RecyclerView rv_f4;
    TextView textView_4;

    int[] crop_icon =data.crop_icon;
    String[] crop_name =data.crop_name;
    CardView back;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public fragment_4() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fragment_4.
     */
    // TODO: Rename and change types and number of parameters
    public static fragment_4 newInstance(String param1, String param2) {
        fragment_4 fragment = new fragment_4();
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

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_4, container, false);
        rv_f4=(RecyclerView) view.findViewById(R.id.rv_f4);
        textView_4=(TextView) view.findViewById(R.id.textView_f4);

        textView_4.setTextColor(getContext().getColor(R.color.treatment));

        rv_f4.setLayoutManager(new GridLayoutManager(view.getContext(),2));
        rv_f4.setAdapter(new rv_adapter4(crop_icon,crop_name,view.getContext()));

        back=view.findViewById(R.id.cardView4);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFragment(fragment_1.newInstance("", ""));
            }
        });
        return view;
    }
    public void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}