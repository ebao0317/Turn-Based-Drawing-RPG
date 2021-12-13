// StorageRationaleDialog.java
// This file helps with saving to the gallery
// CPSC 312-02, Fall 2021
// Programming Assignment #6
// Sources: https://www.youtube.com/watch?v=LqJko2Ln86E followed this video series to create this class
// Specifically for this class, the way to save was outdated in the video so I used the creators updated version of paint app
// and utilized that way to save the bitmap to gallery. I could not really figure out how else to save without having this
// class with its xml which does not even display
// Link to his public github repo: https://github.com/danstoakes/2021-simple-paint-app where I derived his saving method
// Created by Ethan Bao on 12/09/21
package com.example.basic_tbb;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import androidx.annotation.NonNull;

/**
 * Custom dialog which displays the storage rationale to the user.
 */
public class StorageRationaleDialog extends Dialog implements View.OnClickListener
{
    private StorageRationaleOptionSelectedListener listener;

    /**
     * Constructor for the class. Handles the creation of the dialog window and setting of listeners.
     * @param context - the context of the application.
     */
    public StorageRationaleDialog(@NonNull Context context)
    {
        // set the current activity using the context
        super(context);
        setOwnerActivity ((Activity) context);
        // define the window and set the background as transparent to allow for rounded corners
        Window window = super.getWindow ();
        if (window != null)
            window.setBackgroundDrawable (new ColorDrawable(Color.TRANSPARENT));
        // set the resource to be used for the dialog
        super.setContentView (R.layout.activity_storage_rationale_dialog);
        super.setCancelable (false);
        // Initialise the buttons
        Button buttonCancel = findViewById(R.id.buttonCancel);
        Button buttonAllow = findViewById(R.id.buttonAllow);
        // Set the click listeners for the buttons
        buttonCancel.setOnClickListener(this);
        buttonCancel.setBackgroundTintList(null);
        buttonAllow.setOnClickListener(this);
        buttonAllow.setBackgroundTintList(null);
    }

    /**
     * Sets the listener for the dialog box.
     * @param listener - the listener for options being selected.
     */
    public void setOnStorageRationaleOptionSelectedListener (StorageRationaleOptionSelectedListener listener)
    {
        this.listener = listener;
    }

    /**
     * The click handler for the various clickable elements in the dialog.
     * @param v - the dialog view object which was clicked.
     */
    @Override
    public void onClick(View v)
    {
        // get the ID of the view object being clicked
        int viewID = v.getId();
        // prompt the listener depending on the option selected
        if (viewID == R.id.buttonCancel)
        {
            listener.onStorageRationaleOptionSelected(false);
            dismiss();
        } else if (viewID == R.id.buttonAllow)
        {
            listener.onStorageRationaleOptionSelected(true);
            dismiss();
        }
    }

    /**
     * Interface which handles callbacks when dialog options are selected.
     */
    public interface StorageRationaleOptionSelectedListener
    {
        void onStorageRationaleOptionSelected (boolean allow);
    }
}
