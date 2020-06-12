package com.example.expensemanager;

public class ItemsRegister {

    String monthName, year, foodV, transportationV, clothesV, snacksV, billsV, travelsV;
    String educationV, ticketsV, healthV;

    public ItemsRegister(String monthName, String year, String foodV, String transportationV, String clothesV, String snacksV, String billsV, String travelsV, String educationV, String ticketsV, String healthV) {
        this.monthName = monthName;
        this.year = year;
        this.foodV = foodV;
        this.transportationV = transportationV;
        this.clothesV = clothesV;
        this.snacksV = snacksV;
        this.billsV = billsV;
        this.travelsV = travelsV;
        this.educationV = educationV;
        this.ticketsV = ticketsV;
        this.healthV = healthV;
    }
}
