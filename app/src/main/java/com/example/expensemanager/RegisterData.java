package com.example.expensemanager;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;

import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.TimeZone;

public class RegisterData extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    TextView title, currentA;
    ImageView datePicker;
    EditText textViewDate, editTextAmount;
    String titleS, dateS, amountS;
    Button buttonSaveR;
    DataBaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_data);

        myDB = new DataBaseHelper(this);

        // Assign the title

        title = findViewById(R.id.textViewTitle);
        String KeyW = getIntent().getStringExtra("KeyValue");
        title.setText(KeyW);


        // To add current amount

        currentA = findViewById(R.id.textViewCurrentA);

        titleS = KeyW;
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;

        int value = addCurrentA(titleS, ""+month, ""+year);
        currentA.setText(""+value);





        // To pick a date

        textViewDate = findViewById(R.id.editTextDate);
        datePicker = findViewById(R.id.imageViewDataP);

        datePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                hideKeyboards(RegisterData.this);

                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");

            }
        });



        // button to save data


        editTextAmount = findViewById(R.id.editTextValue);

        buttonSaveR = findViewById(R.id.buttonSaveR);
        buttonSaveR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i){
                            case DialogInterface.BUTTON_POSITIVE:

                                titleS = title.getText().toString();
                                dateS = textViewDate.getText().toString();
                                amountS = editTextAmount.getText().toString();


                                if (dateS.length() > 0 && amountS.length() > 0) {


                                    StringTokenizer tokens = new StringTokenizer(dateS, "/");
                                    String month = tokens.nextToken();
                                    String day = tokens.nextToken();
                                    String year = tokens.nextToken();


                                    boolean added = myDB.addData(year, month, day, titleS, amountS);
                                    if (added) {
                                        Toast.makeText(RegisterData.this, "Added", Toast.LENGTH_SHORT).show();

                                        startActivity(getIntent());
                                        finish();

                                    } else {
                                        Toast.makeText(RegisterData.this, "Data not Added", Toast.LENGTH_SHORT).show();
                                    }



                                    break;

                                }else {
                                    Toast.makeText(RegisterData.this, "Please, fill the required information", Toast.LENGTH_SHORT).show();
                                }

                            case DialogInterface.BUTTON_NEGATIVE:

                                Toast.makeText(RegisterData.this, "Data not Saved", Toast.LENGTH_SHORT).show();
                                break;
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterData.this, R.style.DialogTheme);



                builder.setMessage("Do you want to save?").setPositiveButton("Yes", dialogClickListener).
                        setNegativeButton("No", dialogClickListener).show();

            }
        });

    }

    public int addCurrentA(String type, String month, String year){
        Cursor getData = myDB.getData(type, month, year);
        if (getData.getCount()==0){
            return  0;
        }else {
            int value = 0;
            while (getData.moveToNext()){
                int valueI = getData.getInt(0);
                value = value + valueI;
            }
            return value;
        }
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        Date date = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        String currentDataString = sdf.format(date);
        textViewDate.setText(currentDataString);
    }

    public static void hideKeyboards (Activity activity){
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = activity.getCurrentFocus();
        if (view == null){
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

}
