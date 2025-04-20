package com.example.cropdoc;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.net.Uri;

import com.example.cropdoc.ml.CornModel;
import com.example.cropdoc.ml.Model;
import com.example.cropdoc.ml.PotatoModel;
import com.example.cropdoc.ml.RiceModel;
import com.example.cropdoc.ml.WheatModel;

import org.tensorflow.lite.DataType;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;


public class model_selector {
Bitmap image;
Context context;

String clasifyCornImage(Bitmap image,Context context){
    try {
        Model model = Model.newInstance(context);

        // Creates inputs for reference.
        TensorBuffer inputFeature0 = TensorBuffer.createFixedSize(new int[]{1, 256, 256, 3}, DataType.FLOAT32);
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(4 * data.ImageSize * data.ImageSize * 3);
        byteBuffer.order(ByteOrder.nativeOrder());
        inputFeature0.loadBuffer(byteBuffer);

        int[] intValues = new int[data.ImageSize*data.ImageSize];
        image.getPixels(intValues, 0, image.getWidth(), 0, 0, image.getWidth(), image.getHeight());
        int pixel = 0;
        //iterate over each pixel and extract R, G, and B values. Add those values individually to the byte buffer.
        for(int i = 0; i < data.ImageSize; i ++){
            for(int j = 0; j < data.ImageSize; j++){
                int val = intValues[pixel++]; // RGB
                byteBuffer.putFloat(((val >> 16) & 0xFF) * (1.f / 1));
                byteBuffer.putFloat(((val >> 8) & 0xFF) * (1.f / 1));
                byteBuffer.putFloat((val & 0xFF) * (1.f / 1));

//                byteBuffer.putFloat(((val >> 16) & 0xFF) * (1.f / 255));
//                byteBuffer.putFloat(((val >> 8) & 0xFF) * (1.f / 255));
//                byteBuffer.putFloat((val & 0xFF) * (1.f / 255));
            }
        }

        // Runs model inference and gets result.
        Model.Outputs outputs = model.process(inputFeature0);
        TensorBuffer outputFeature0 = outputs.getOutputFeature0AsTensorBuffer();

        float[] confidences = outputFeature0.getFloatArray();

        // find the index of the class with the biggest confidence.
        int maxPos = 0;
        float maxConfidence = 0;
        for (int i = 0; i < confidences.length; i++) {
            if (confidences[i] > maxConfidence) {
                maxConfidence = confidences[i];
                maxPos = i;
            }
        }
        String[] classes = {"Common_Rust", "Gray_Leaf_Spot", "Healthy","Leaf_Blight","Invalid"};
       // result.setText(classes[maxPos]);


        // Releases model resources if no longer used.
        model.close();
        data.confidence=confidences[maxPos];
        return classes[maxPos];
    } catch (IOException e) {
        // TODO Handle the exception
    }
    return "can't recognize";
}
String clasifyCorn2Image(Bitmap image,Context context){


    try {
        CornModel model = CornModel.newInstance(context);

        // Creates inputs for reference.
        TensorBuffer inputFeature0 = TensorBuffer.createFixedSize(new int[]{1, 256, 256, 3}, DataType.FLOAT32);
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(4 * data.ImageSize * data.ImageSize * 3);
        byteBuffer.order(ByteOrder.nativeOrder());
        inputFeature0.loadBuffer(byteBuffer);

        int[] intValues = new int[data.ImageSize*data.ImageSize];
        image.getPixels(intValues, 0, image.getWidth(), 0, 0, image.getWidth(), image.getHeight());
        int pixel = 0;
        //iterate over each pixel and extract R, G, and B values. Add those values individually to the byte buffer.
        for(int i = 0; i < data.ImageSize; i ++){
            for(int j = 0; j < data.ImageSize; j++){
                int val = intValues[pixel++]; // RGB
                byteBuffer.putFloat(((val >> 16) & 0xFF) * (1.f / 1));
                byteBuffer.putFloat(((val >> 8) & 0xFF) * (1.f / 1));
                byteBuffer.putFloat((val & 0xFF) * (1.f / 1));

//                byteBuffer.putFloat(((val >> 16) & 0xFF) * (1.f / 255));
//                byteBuffer.putFloat(((val >> 8) & 0xFF) * (1.f / 255));
//                byteBuffer.putFloat((val & 0xFF) * (1.f / 255));
            }
        }

        // Runs model inference and gets result.
        CornModel.Outputs outputs = model.process(inputFeature0);
        TensorBuffer outputFeature0 = outputs.getOutputFeature0AsTensorBuffer();

        float[] confidences = outputFeature0.getFloatArray();

        // find the index of the class with the biggest confidence.
        int maxPos = 0;
        float maxConfidence = 0;
        for (int i = 0; i < confidences.length; i++) {
            if (confidences[i] > maxConfidence) {
                maxConfidence = confidences[i];
                maxPos = i;
            }
        }
        String[] classes = {"Common_Rust", "Gray_Leaf_Spot", "Healthy","Leaf_Blight","Invalid"};
       // result.setText(classes[maxPos]);


        // Releases model resources if no longer used.
        model.close();
        data.confidence=confidences[maxPos];
        return classes[maxPos];
    } catch (IOException e) {
        // TODO Handle the exception
    }
    return "can't recognize";
}



//potato
String clasifyPotatoImage(Bitmap image,Context context){

//    try {
//        PotatoModel model = PotatoModel.newInstance(context);
//
//        // Creates inputs for reference.
//        TensorBuffer inputFeature0 = TensorBuffer.createFixedSize(new int[]{1, 256, 256, 3}, DataType.FLOAT32);
//        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(4 * data.ImageSize * data.ImageSize * 3);
//        byteBuffer.order(ByteOrder.nativeOrder());
//        inputFeature0.loadBuffer(byteBuffer);
//
//        // Runs model inference and gets result.
//        PotatoModel.Outputs outputs = model.process(inputFeature0);
//        TensorBuffer outputFeature0 = outputs.getOutputFeature0AsTensorBuffer();
//
//
//
//        float[] confidences = outputFeature0.getFloatArray();
//        // find the index of the class with the biggest confidence.
//        int maxPos = 0;
//        float maxConfidence = 0;
//        for (int i = 0; i < confidences.length; i++) {
//            if (confidences[i] > maxConfidence) {
//                maxConfidence = confidences[i];
//                maxPos = i;
//            }
//        }
//        String[] classes = {"Early_Blight","Healthy","Invalid","Late_Blight"};
//        // result.setText(classes[maxPos]);
//
//        // Releases model resources if no longer used.
//        model.close();
//        data.confidence=confidences[maxPos];
//        return classes[maxPos];
//    } catch (IOException e) {
//        // TODO Handle the exception
//    }
//    return "can't recognize";
//}
  //  old
        try {
            PotatoModel model = PotatoModel.newInstance(context);

        // Creates inputs for reference.
        TensorBuffer inputFeature0 = TensorBuffer.createFixedSize(new int[]{1, 256, 256, 3}, DataType.FLOAT32);
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(4 * data.ImageSize * data.ImageSize * 3);
        byteBuffer.order(ByteOrder.nativeOrder());
        inputFeature0.loadBuffer(byteBuffer);

        int[] intValues = new int[data.ImageSize*data.ImageSize];
        image.getPixels(intValues, 0, image.getWidth(), 0, 0, image.getWidth(), image.getHeight());
        int pixel = 0;
        //iterate over each pixel and extract R, G, and B values. Add those values individually to the byte buffer.
        for(int i = 0; i < data.ImageSize; i ++){
            for(int j = 0; j < data.ImageSize; j++){
                int val = intValues[pixel++]; // RGB
                byteBuffer.putFloat(((val >> 16) & 0xFF) * (1.f / 1));
                byteBuffer.putFloat(((val >> 8) & 0xFF) * (1.f / 1));
                byteBuffer.putFloat((val & 0xFF) * (1.f / 1));

//                byteBuffer.putFloat(((val >> 16) & 0xFF) * (1.f / 255));
//                byteBuffer.putFloat(((val >> 8) & 0xFF) * (1.f / 255));
//                byteBuffer.putFloat((val & 0xFF) * (1.f / 255));
            }
        }
            // Runs model inference and gets result.
            PotatoModel.Outputs outputs = model.process(inputFeature0);
            TensorBuffer outputFeature0 = outputs.getOutputFeature0AsTensorBuffer();


        float[] confidences = outputFeature0.getFloatArray();
        // find the index of the class with the biggest confidence.
        int maxPos = 0;
        float maxConfidence = 0;
        for (int i = 0; i < confidences.length; i++) {
            if (confidences[i] > maxConfidence) {
                maxConfidence = confidences[i];
                maxPos = i;
            }
        }
        String[] classes = {"Early_Blight","Healthy","Invalid","Late_Blight"};
        // result.setText(classes[maxPos]);

        // Releases model resources if no longer used.
        model.close();
        data.confidence=confidences[maxPos];
        return classes[maxPos];
    } catch (IOException e) {
        // TODO Handle the exception
    }
    return "can't recognize";
}

    String clasifyRiceImage(Bitmap image,Context context){
        try {
            RiceModel model = RiceModel.newInstance(context);

            // Creates inputs for reference.
            TensorBuffer inputFeature0 = TensorBuffer.createFixedSize(new int[]{1, 256, 256, 3}, DataType.FLOAT32);
            ByteBuffer byteBuffer = ByteBuffer.allocateDirect(4 * data.ImageSize * data.ImageSize * 3);
            byteBuffer.order(ByteOrder.nativeOrder());
            inputFeature0.loadBuffer(byteBuffer);

            int[] intValues = new int[data.ImageSize*data.ImageSize];
            image.getPixels(intValues, 0, image.getWidth(), 0, 0, image.getWidth(), image.getHeight());
            int pixel = 0;
            //iterate over each pixel and extract R, G, and B values. Add those values individually to the byte buffer.
            for(int i = 0; i < data.ImageSize; i ++){
                for(int j = 0; j < data.ImageSize; j++){
                    int val = intValues[pixel++]; // RGB
                    byteBuffer.putFloat(((val >> 16) & 0xFF) * (1.f / 1));
                    byteBuffer.putFloat(((val >> 8) & 0xFF) * (1.f / 1));
                    byteBuffer.putFloat((val & 0xFF) * (1.f / 1));

//                byteBuffer.putFloat(((val >> 16) & 0xFF) * (1.f / 255));
//                byteBuffer.putFloat(((val >> 8) & 0xFF) * (1.f / 255));
//                byteBuffer.putFloat((val & 0xFF) * (1.f / 255));
                }
            }
            // Runs model inference and gets result.
            RiceModel.Outputs outputs = model.process(inputFeature0);
            TensorBuffer outputFeature0 = outputs.getOutputFeature0AsTensorBuffer();


            float[] confidences = outputFeature0.getFloatArray();
            // find the index of the class with the biggest confidence.
            int maxPos = 0;
            float maxConfidence = 0;
            for (int i = 0; i < confidences.length; i++) {
                if (confidences[i] > maxConfidence) {
                    maxConfidence = confidences[i];
                    maxPos = i;
                }
            }
            String[] classes = {"Brown_Spot","Healthy","Hispa","Invalid","Leaf_Blast"};
            // result.setText(classes[maxPos]);

            // Releases model resources if no longer used.
            model.close();

            data.confidence=confidences[maxPos];
            return classes[maxPos];
        } catch (IOException e) {
            // TODO Handle the exception
        }
        return "can't recognize";
    }


    String clasifyWheatImage(Bitmap image,Context context){
        try {
            WheatModel model = WheatModel.newInstance(context);

            // Creates inputs for reference.
            TensorBuffer inputFeature0 = TensorBuffer.createFixedSize(new int[]{1, 256, 256, 3}, DataType.FLOAT32);
            ByteBuffer byteBuffer = ByteBuffer.allocateDirect(4 * data.ImageSize * data.ImageSize * 3);
            byteBuffer.order(ByteOrder.nativeOrder());
            inputFeature0.loadBuffer(byteBuffer);

            int[] intValues = new int[data.ImageSize*data.ImageSize];
            image.getPixels(intValues, 0, image.getWidth(), 0, 0, image.getWidth(), image.getHeight());
            int pixel = 0;
            //iterate over each pixel and extract R, G, and B values. Add those values individually to the byte buffer.
            for(int i = 0; i < data.ImageSize; i ++){
                for(int j = 0; j < data.ImageSize; j++){
                    int val = intValues[pixel++]; // RGB
                    byteBuffer.putFloat(((val >> 16) & 0xFF) * (1.f / 1));
                    byteBuffer.putFloat(((val >> 8) & 0xFF) * (1.f / 1));
                    byteBuffer.putFloat((val & 0xFF) * (1.f / 1));

//                byteBuffer.putFloat(((val >> 16) & 0xFF) * (1.f / 255));
//                byteBuffer.putFloat(((val >> 8) & 0xFF) * (1.f / 255));
//                byteBuffer.putFloat((val & 0xFF) * (1.f / 255));
                }
            }
            // Runs model inference and gets result.
            WheatModel.Outputs outputs = model.process(inputFeature0);
            TensorBuffer outputFeature0 = outputs.getOutputFeature0AsTensorBuffer();


            float[] confidences = outputFeature0.getFloatArray();
            // find the index of the class with the biggest confidence.
            int maxPos = 0;
            float maxConfidence = 0;
            for (int i = 0; i < confidences.length; i++) {
                if (confidences[i] > maxConfidence) {
                    maxConfidence = confidences[i];
                    maxPos = i;
                }
            }
            String[] classes = {"Brown_Rust","Healthy","Invalid","Yellow_Rust"};
            // result.setText(classes[maxPos]);

            // Releases model resources if no longer used.
            model.close();
            data.confidence=confidences[maxPos];
            return classes[maxPos];
        } catch (IOException e) {
            // TODO Handle the exception
        }
        return "can't recognize";
    }

}



