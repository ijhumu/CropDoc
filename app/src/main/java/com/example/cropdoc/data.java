package com.example.cropdoc;

import android.graphics.Bitmap;
import android.net.Uri;

public class data {
static Bitmap image;
static String identified_disease;
static String selected_crop;
static float confidence;

static String cropModel;
static int ImageSize=256;
    static int[] crop_icon ={R.drawable.corn__2_,
            R.drawable.potato__2_,
            R.drawable.rice__2_,
            R.drawable.weate__2_};

   static String[] crop_name ={"Corn","Potato","Rice","Wheat"};

   static String[] disease_name={"Common_Rust","Gray_Leaf_Spot","Leaf_Blight"};
   static String[] disease_name_1={"Early_Blight","Late_Blight"};
   static String[] disease_name_2={"Brown_Spot","Hispa","Leaf_Blast"};
   static String[] disease_name_3={"Brown_Rust","Yellow_Rust"};
}
