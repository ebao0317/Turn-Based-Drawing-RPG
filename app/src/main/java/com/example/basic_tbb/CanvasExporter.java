// StorageRationaleDialog.java
// This file helps with saving to the gallery, essentially takes the canvas and exports it
// CPSC 312-02, Fall 2021
// Project
// Sources: https://www.youtube.com/watch?v=LqJko2Ln86E followed this video series to create this class
// Specifically for this class, the way to save was outdated in the video so I used the creators updated version of paint app
// and utilized that way to save the bitmap to gallery.
// Link to his public github repo: https://github.com/danstoakes/2021-simple-paint-app where I derived his saving method
// Created by Ethan Bao on 12/09/21
package com.example.basic_tbb;

import android.graphics.Bitmap;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;


public class CanvasExporter
{
    //saves to a directory within the photo gallery
    private static final String DIRECTORY_PATH = "/Pictures/Paint";
    private static final String SAVE_FILE_NAME = "/drawing_";
    private static final String SHARE_FILE_NAME = "/shared_";
    private static final String FILE_EXTENSION = ".png";

    public static final int PERMISSION_WRITE_EXTERNAL_STORAGE = 1;
    public static final int FLAG_SAVE = 1;
    public static final int FLAG_SHARE = 2;

    private final File subDirectory;

    private int exportType;


    public CanvasExporter() {
        // get the output storage directory and find the sub-directory.
        File storageDirectory = Environment.getExternalStorageDirectory();
        subDirectory = new File(storageDirectory.toString() + DIRECTORY_PATH);
    }


    public void setExportType (int exportType)
    {
        this.exportType = exportType;
    }


    public int getExportType ()
    {
        return exportType;
    }

    /*
     * Creates the subdirectory and returns true if it already exists or the result of creating the subdirectory
     */
    private boolean createDirectory () {
        // creates the subdirectory if it does not exist
        if (!subDirectory.exists()) {
            return subDirectory.mkdir();
        }
        // returns true if the directory already exists
        return true;
    }

    /*
     * Loops through our directory and counts how many photos are already in the directory
     */
    public int getExistingFileCount(File directory) {
        int count = 0;
        // get the existing images as an array
        File[] existingImages = directory.listFiles();
        if (existingImages != null) {
            // loops through the list of images in the directory
            for (File file : existingImages) {
                // extract the file name and increment the counter if it is a valid file type
                String name = file.getName();
                if (name.endsWith(".jpg") || name.endsWith(".png")) {
                    count++;
                }
            }
        }
        return count;
    }

    /*
     * Essentially saves the bitmap, which is our painting, to the gallery
     */
    private void outputToFileStream (File image, Bitmap bitmap) {
        FileOutputStream fileOutputStream;
        try
        {
            // compress the bitmap and link it to the output stream
            fileOutputStream = new FileOutputStream(image);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
            // flush and close the output stream.
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (Exception e)
        {
            Log.w("ERROR", "" + e.getMessage());
        }
    }

    /*
     * Saves the image bitmap calling the previous function to help in achieving this
     */
    public String saveImage(Bitmap bitmap) {
        boolean created = createDirectory();
        // if the sub-directory exists or was created successfully
        if (subDirectory.exists() || created)
        {
            // create a new file for the bitmap
            int fileCount = getExistingFileCount(subDirectory);
            File image = new File(subDirectory, SAVE_FILE_NAME + ++fileCount + FILE_EXTENSION);
            outputToFileStream(image, bitmap);
            // return the path to the saved image.
            return image.getAbsolutePath();
        }
        return null;
    }
}

