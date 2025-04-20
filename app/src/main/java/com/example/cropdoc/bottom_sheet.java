package com.example.cropdoc;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.io.IOException;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link bottom_sheet#newInstance} factory method to
 * create an instance of this fragment.
 */
public class bottom_sheet extends BottomSheetDialogFragment {

    Button camera;
    Button gallary;
    Intent intent;
    model_selector model_selector;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public bottom_sheet() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment bottom_sheet.
     */
    // TODO: Rename and change types and number of parameters
    public static bottom_sheet newInstance(String param1, String param2) {
        bottom_sheet fragment = new bottom_sheet();
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
        View view= inflater.inflate(R.layout.fragment_bottom_sheet, container, false);
        camera=view.findViewById(R.id.bottom_sheet_btn1);
        gallary=view.findViewById(R.id.bottom_sheet_btn2);



        intent=new Intent(view.getContext(),result.class);

        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImagePicker.with(bottom_sheet.this)
                        .cameraOnly()
                        .crop(1f,1f)
                     .compress(1024)
                        .maxResultSize(1080, 1080)
                        .start();
            }
        });


        gallary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImagePicker.with(bottom_sheet.this)
                        .crop(1f,1f)
                        .galleryOnly()
                        .compress(1024)
                        .maxResultSize(1080, 1080)
                        .start();
            }
        });
        return view;

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


       // Bitmap image = (Bitmap) data.getExtras().get("data");

        try {
            Uri imageUri = data.getData();
            Bitmap image = null;
            try {
                image = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), imageUri);
                int dimension = Math.min(image.getWidth(), image.getHeight());
                image = ThumbnailUtils.extractThumbnail(image, dimension, dimension);
                image = Bitmap.createScaledBitmap(image, com.example.cropdoc.data.ImageSize, com.example.cropdoc.data.ImageSize, false);
            } catch (IOException e) {
                e.printStackTrace();
            }
            com.example.cropdoc.data.image = image;
            String selectedModel = com.example.cropdoc.data.cropModel;
            switch (selectedModel) {
                case "Corn":
//                com.example.cropdoc.data.identified_disease=new model_selector().clasifyCornImage(image,getContext());
                    com.example.cropdoc.data.selected_crop = "Corn";
                    com.example.cropdoc.data.identified_disease = new model_selector().clasifyCorn2Image(image, getContext());
                    break;

                case "Potato":
                    com.example.cropdoc.data.selected_crop = "Potato";
                    com.example.cropdoc.data.identified_disease = new model_selector().clasifyPotatoImage(image, getContext());
                    break;

                case "Rice":
                    com.example.cropdoc.data.selected_crop = "Rice";
                    com.example.cropdoc.data.identified_disease = new model_selector().clasifyRiceImage(image, getContext());
                    break;

                case "Wheat":
                    com.example.cropdoc.data.selected_crop = "Wheat";
                    com.example.cropdoc.data.identified_disease = new model_selector().clasifyWheatImage(image, getContext());
                    break;
            }

            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(getContext(), "Please select an image", Toast.LENGTH_SHORT).show();
            dismiss();
        }


    }

}