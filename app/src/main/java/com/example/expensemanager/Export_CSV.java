package com.example.expensemanager;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;

public class Export_CSV {

    DataBaseHelper myDB;

    public void export_Data (Context context){

    myDB = new DataBaseHelper(context);
        File folder = new File(Environment.getExternalStorageDirectory() + "/ExportCSVExpenses/");
        String file = folder.toString() + "/Expenses.csv";

        boolean isCreated = false;
        if (!folder.exists()){
            isCreated = folder.mkdir();
        }

        try{
            FileWriter fileWriter = new FileWriter(file);
            Cursor data = myDB.getDataTotal();

            if (data.getCount() == 0){

                Toast.makeText(context, "There  is no entries", Toast.LENGTH_SHORT).show();

            }else {

                fileWriter.append("Year");
                fileWriter.append(",");
                fileWriter.append("Month");
                fileWriter.append(",");
                fileWriter.append("Day");
                fileWriter.append(",");
                fileWriter.append("Type");
                fileWriter.append(",");
                fileWriter.append("Amount");
                fileWriter.append("\n");


                while(data.moveToNext()){

                    fileWriter.append(data.getString(1));
                    fileWriter.append(",");
                    fileWriter.append(data.getString(2));
                    fileWriter.append(",");
                    fileWriter.append(data.getString(3));
                    fileWriter.append(",");
                    fileWriter.append(data.getString(4));
                    fileWriter.append(",");
                    fileWriter.append(data.getString(5));
                    fileWriter.append("\n");

                }

                Toast.makeText(context, "Data exported to internal memory", Toast.LENGTH_SHORT).show();

            }

            myDB.close();
            fileWriter.close();



        }catch (Exception e){
            Toast.makeText(context, "Not Exported", Toast.LENGTH_SHORT).show();
        }

    }

    public Intent shareFile(){
        File exporDir = new File(Environment.getExternalStorageDirectory(), "/ExportCSVExpenses/");
        String fileName = "Expenses.csv";
        File sharingFile = new File(exporDir, fileName);
        Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
        shareIntent.setType("application/csv");
        Uri uri = Uri.fromFile(sharingFile);
        shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
        return shareIntent;
        //startActivity(Intent.createChooser(shareIntent, "Share CSV"));

    }

}
