package com.example.basic_tbb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.media.MediaScannerConnection;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import yuku.ambilwarna.AmbilWarnaDialog;

public class PaintActivity extends AppCompatActivity {

    private CanvasExporter canvasExporter;
    private PaintView paintView;
    private int defaultColor;
    private int STORAGE_PERMISSION_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Button button;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paint);

        canvasExporter = new CanvasExporter();
        paintView = findViewById(R.id.paintView);
        button = findViewById(R.id.change_color_button);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        SeekBar seekBar = findViewById(R.id.seekBar);
        final TextView textView = findViewById(R.id.current_pen_size);

        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        paintView.initialize(displayMetrics);

        textView.setText("Pen size: " + seekBar.getProgress());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openColorPicker();
            }
        });
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                paintView.setStrokeWidth(seekBar.getProgress());
                textView.setText("Pen size: " + seekBar.getProgress());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void requestStoragePermission () {
        String permission = Manifest.permission.WRITE_EXTERNAL_STORAGE;
        // request the permission to write to storage
        ActivityCompat.requestPermissions(this, new String[]{permission},
                CanvasExporter.PERMISSION_WRITE_EXTERNAL_STORAGE);
    }

    private void checkForPermissions()
    {
        int permission = ContextCompat.checkSelfPermission(
                this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission == PackageManager.PERMISSION_DENIED)
        {
            // if the user has not granted permission
            boolean shouldShowRationale = ActivityCompat.shouldShowRequestPermissionRationale(
                    this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

            if (shouldShowRationale)
            {
                // display a rationale for saving (why the permission is needed)
                StorageRationaleDialog dialog = new StorageRationaleDialog(PaintActivity.this);
                dialog.setOnStorageRationaleOptionSelectedListener(new StorageRationaleDialog.StorageRationaleOptionSelectedListener()
                {
                    /**
                     * Callback method which handles whether a user accepted the permission or not.
                     * @param allow - whether the permission is accepted or not.
                     */
                    @Override
                    public void onStorageRationaleOptionSelected(boolean allow)
                    {
                        // if the user accepts the storage permission in the dialog, request it officially
                        if (allow)
                            requestStoragePermission();
                    }
                });
                dialog.show();
            } else
            {
                // request the permission to write to storage
                requestStoragePermission();
            }
        } else
        {
            // save/share the image as permission already granted
            exportImage();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == STORAGE_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Access granted", Toast.LENGTH_SHORT).show();
                exportImage();
            } else {
                Toast.makeText(this, "Access denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.clear_button:
                paintView.clear();
                return true;
            case R.id.undo_button:
                paintView.undo();
                return true;
            case R.id.redo_button:
                paintView.redo();
                return true;
            case R.id.save_button:
                canvasExporter.setExportType(CanvasExporter.FLAG_SAVE);
                checkForPermissions();
                return true;
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void openColorPicker() {
        AmbilWarnaDialog ambilWarnaDialog = new AmbilWarnaDialog(this, defaultColor, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onCancel(AmbilWarnaDialog dialog) {
                Toast.makeText(PaintActivity.this, "Unavailable", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onOk(AmbilWarnaDialog dialog, int color) {
                defaultColor = color;
                paintView.setColor(color);
            }
        });
        ambilWarnaDialog.show();
    }

    private void exportImage ()
    {
        if (canvasExporter.getExportType() == CanvasExporter.FLAG_SAVE)
        {
            // if the user is wanting to save, attempt and return its filename
            String fileName = canvasExporter.saveImage(paintView.getmBitmap());

            if (fileName != null)
            {
                // refresh the gallery to show the new image if it exists
                MediaScannerConnection.scanFile(
                        PaintActivity.this, new String[]{fileName}, null, null);
                Toast.makeText(PaintActivity.this, "The image was saved successfully.", Toast.LENGTH_SHORT).show();
            } else
            {
                Toast.makeText(PaintActivity.this, "There was an error saving the image.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}