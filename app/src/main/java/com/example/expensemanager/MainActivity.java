package com.example.expensemanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.DialogFragment;

import android.Manifest;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.zip.DataFormatException;

public class MainActivity extends AppCompatActivity {

    ImageView foodI, transportation, clothes, snacks, bills, travel, education, tickets, health;
    Button buttomShowData, buttonExportData;
    Export_CSV export_csv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        // Buttons for the different expenses

        foodI = findViewById(R.id.imageViewFood);
        transportation = findViewById(R.id.imageViewTransp);
        clothes = findViewById(R.id.imageViewClothes);
        snacks = findViewById(R.id.imageViewSnacks);
        bills = findViewById(R.id.imageViewBills);
        travel = findViewById(R.id.imageViewTravel);
        education = findViewById(R.id.imageViewEducat);
        tickets = findViewById(R.id.imageViewTicket);
        health = findViewById(R.id.imageViewHealth);

        setOnClick(foodI, "Food");
        setOnClick(transportation, "Transportation");
        setOnClick(clothes, "Clothes");
        setOnClick(snacks, "Snacks");
        setOnClick(bills, "Bills");
        setOnClick(travel, "Travel");
        setOnClick(education, "Education");
        setOnClick(tickets, "Tickets");
        setOnClick(health, "Health");

        // Button show the data

        buttomShowData = findViewById(R.id.buttonShowData);
        buttomShowData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(MainActivity.this, "Clicked", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getBaseContext(), ExpensesRegister.class);
                startActivity(intent);
            }
        });




        // Button export data

        permisions();

        buttonExportData = findViewById(R.id.buttonExportCSV);
        export_csv = new Export_CSV();
        buttonExportData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Export CSV file");
                builder.setMessage("Do you want to save and share CSV file");

                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        export_csv.export_Data(MainActivity.this);
                        Intent shareIntent = export_csv.shareFile();
                        startActivity(Intent.createChooser(shareIntent, "Share CSV"));


                        dialogInterface.dismiss();
                    }
                });

                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        dialogInterface.dismiss();

                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            }
        });





    }

    public void permisions(){
        //ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},0);
        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},0);
    }









    private void setOnClick (ImageView imageView, final String text){
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), RegisterData.class);
                intent.putExtra("KeyValue", text);
                startActivity(intent);
            }
        });
    }


}
