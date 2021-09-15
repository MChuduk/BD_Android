package com.example.myapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.TextClock;
import android.widget.TextView;

import java.io.File;

public class MainActivity extends AppCompatActivity
{
    private TextView absoluteTextView;
    private TextView nameTextView;
    private TextView pathTextView;
    private TextView readWriteTextView;
    private TextView externalTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FindView();
    }

    public void OnGetFilesDirButtonClick(View button) { DisplayInfo(super.getFilesDir()); }

    public void OnGetCacheDirButtonClick(View button) { DisplayInfo(super.getCacheDir()); }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void OnGetExternalFilesDirClick(View button) {
        DisplayInfo(super.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)); };

    public void OnGetExternalCacheDirClick(View button) { DisplayInfo(super.getExternalCacheDir()); };

    public void OnGetExternalStorageDirectoryClick(View button) {
        DisplayInfo(Environment.getExternalStorageDirectory()); };

    public void OnGetExternalStoragePublicDirectoryClick(View button) {
        DisplayInfo(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES)); };

    private void FindView()
    {
        absoluteTextView = findViewById(R.id.textViewAbsolute);
        nameTextView = findViewById(R.id.textViewName);
        pathTextView = findViewById(R.id.textViewPath);
        readWriteTextView = findViewById(R.id.textViewReadWrite);
        externalTextView = findViewById(R.id.textViewExternal);
    }

    private void DisplayInfo(File file)
    {
        absoluteTextView.setText("Absolute:" + file.getAbsolutePath());
        nameTextView.setText("Name: " + file.getName());
        pathTextView.setText("Path: " + file.getPath());
        readWriteTextView.setText("Read/Write: " + file.canRead() + "/" + file.canWrite());
        externalTextView.setText("External: " + Environment.getExternalStorageState());
    }
}