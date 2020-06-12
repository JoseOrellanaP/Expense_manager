package com.example.expensemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ListView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.TimeZone;

public class ExpensesRegister extends AppCompatActivity {

    List<ItemsRegister> listItems;
    ListView listView;
    AdapterRegister adapter;
    DataBaseHelper myBD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.expenses_register);

        listView = findViewById(R.id.listViewExpenses);
        myBD = new DataBaseHelper(this);
        try {
            initiate();
        }catch (Exception e){
            Toast.makeText(this, "There is not enties", Toast.LENGTH_SHORT).show();
        }
        adapter = new AdapterRegister(listItems, this);
        listView.setAdapter(adapter);

    }

    public void initiate (){

        listItems = new ArrayList<>();
        List<Integer> month = new ArrayList<>();
        month = getMonthList(month);

        if (month.size()==0){
            Toast.makeText(this, "There is not register", Toast.LENGTH_SHORT).show();
        }else {
            Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
            int year = calendar.get(Calendar.YEAR);
            int h = 0;

            for (int i=0; i<month.size();i++){
                int monthS = month.get(i);


                String mon = getMonthName(monthS);
                int foodV = getTotalV("Food",""+monthS,""+year);
                int transpV = getTotalV("Transportation", ""+monthS, ""+year);
                int clothesV = getTotalV("Clothes", ""+monthS, ""+year);
                int snacksV = getTotalV("Snacks", ""+monthS, ""+year);
                int bills = getTotalV("Bills", ""+monthS, ""+year);
                int travels = getTotalV("Travel", ""+monthS, ""+year);
                int educataionV = getTotalV("Education", ""+monthS, ""+year);
                int tickets = getTotalV("Tickets", ""+monthS, ""+year);
                int health = getTotalV("Health", ""+monthS, ""+year);

                ItemsRegister listI = new ItemsRegister(""+mon,""+year,""+foodV,""+transpV,""+clothesV,""+snacksV,""+bills,""+travels,""+educataionV,""+tickets,""+health);
                listItems.add(listI);

              }

        }
    }

    public String getMonthName(int monthN){
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat month_date = new SimpleDateFormat("MMMM");
        cal.set(Calendar.MONTH, monthN-1);
        String month_name = month_date.format(cal.getTime());
        return month_name;
    }



    public int getTotalV(String type, String month, String year){
        Cursor getData = myBD.getData(type, month, year);
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


    public List<Integer> getMonthList(List<Integer> month){

        Cursor date = myBD.getDataM();
        if (date.getCount()==0){
            month = null;
        }else {
            List<Integer> monthR = new ArrayList<>();
            while (date.moveToNext()){
                monthR.add(date.getInt(0));
            }

            HashSet<Integer> hashSet = new HashSet<>();
            hashSet.addAll(monthR);
            month.clear();
            month.addAll(hashSet);


        }

        return month;
    }
}
