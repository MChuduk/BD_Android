package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity
{
    private EditText _lastNameEditText;
    private EditText _surNameEditText;

    private final String FILE_NAME = "Base_Lab.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("Lab_04: ", String.valueOf(super.getFilesDir()));

        findView();
        startApp();
    }

    private void findView() {
        _lastNameEditText = findViewById(R.id.editTextLastname);
        _surNameEditText = findViewById(R.id.editTextSurname);
    }

    public void onInputButtonClick(View button){
        String surName = String.valueOf(_surNameEditText.getText());
        String lastName = String.valueOf(_lastNameEditText.getText());

        writeFile(surName + "; " + lastName + ";\r\n");
    }

    public void onShowFileInfoButtonClick(View button){
        String readeInfo = readFile();
        showDialogWindow("Содержимое файла", readeInfo);
    }

    private void startApp(){
        File file = new File(super.getFilesDir(), FILE_NAME);

        if(!file.exists()){
            createFile(file);
            showDialogWindow("Создание файла", "Файл " + FILE_NAME + " создан.");
        }
    }

    private void createFile(File file){
        try{
            file.createNewFile();
            Log.d("Lab_04: ", "Файл " + FILE_NAME + " создан.");
        }
        catch(IOException e){
            Log.d("Lab_04: ", "Файл " + FILE_NAME + " не создан.");
        }
    }

    private void writeFile(String message) {
        try{
            File file = new File(super.getFilesDir(), FILE_NAME);
            FileWriter writer = new FileWriter(file, true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.write(message);
            bufferedWriter.close();
            Log.d("Lab_04: ", "Инфа записана в файл.");
        }
        catch (IOException e){
            Log.d("Lab_04: ", "Ошибка записи в файл.");
        }
    }

    private String readFile() {
        try{
            String message = "";
            File file = new File(super.getFilesDir(), FILE_NAME);
            FileReader reader = new FileReader(file);
            Scanner scanner = new Scanner(reader);

            while(scanner.hasNext()){
                message += scanner.nextLine() + "\n";
            }
            reader.close();
            Log.d("Lab_04: ", "Инфа записана в файл.");
            return message;
        }
        catch (IOException e){
            Log.d("Lab_04: ", "Ошибка записи в файл.");
            return null;
        }
    }

    private void showDialogWindow(String title, String message){
        AlertDialog.Builder windowBuilder = new AlertDialog.Builder(this);
        windowBuilder
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("ОК", (dialog, which) ->
                {
                    Log.d("Lab_04: ", "Закрытие диалогового окна");
                });
        AlertDialog window = windowBuilder.create();
        window.show();
    }
}