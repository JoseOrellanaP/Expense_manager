package com.example.expensemanager;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.List;

public class AdapterRegister extends BaseAdapter {

    private List<ItemsRegister> listItems;
    Context context;

    public AdapterRegister(List<ItemsRegister> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listItems.size();
    }

    @Override
    public Object getItem(int i) {
        return listItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View view1 = view;
        AdapterRegister.ViewHolder viewHolder = new AdapterRegister.ViewHolder();

        if (view1 == null){
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            view1 = inflater.inflate(R.layout.adapter_register, null);

            viewHolder.monthName = view1.findViewById(R.id.textViewMonth);
            viewHolder.year = view1.findViewById(R.id.textViewYear);

            viewHolder.foodV = view1.findViewById(R.id.textViewFoodV);
            viewHolder.transportationV = view1.findViewById(R.id.textViewTransV);
            viewHolder.clothesV = view1.findViewById(R.id.textViewClothesV);
            viewHolder.snacksV = view1.findViewById(R.id.textViewSnacksV);
            viewHolder.billsV = view1.findViewById(R.id.textViewBillsV);
            viewHolder.travelsV = view1.findViewById(R.id.textViewTravelsV);
            viewHolder.educationV = view1.findViewById(R.id.textViewEducationV);
            viewHolder.ticketsV = view1.findViewById(R.id.textViewTicketsV);
            viewHolder.healthV = view1.findViewById(R.id.textViewHealthV);

            view1.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view1.getTag();
        }

        viewHolder.monthName.setText(listItems.get(i).monthName);
        viewHolder.year.setText(listItems.get(i).year);

        viewHolder.foodV.setText(listItems.get(i).foodV);
        viewHolder.transportationV.setText(listItems.get(i).transportationV);
        viewHolder.clothesV.setText(listItems.get(i).clothesV);
        viewHolder.snacksV.setText(listItems.get(i).snacksV);
        viewHolder.billsV.setText(listItems.get(i).billsV);
        viewHolder.travelsV.setText(listItems.get(i).travelsV);
        viewHolder.educationV.setText(listItems.get(i).educationV);
        viewHolder.ticketsV.setText(listItems.get(i).ticketsV);
        viewHolder.healthV.setText(listItems.get(i).healthV);

        return view1;
    }

    static class ViewHolder{
        TextView monthName, foodV, transportationV, clothesV, snacksV, billsV, travelsV;
        TextView educationV, ticketsV, healthV, year;
    }
}
