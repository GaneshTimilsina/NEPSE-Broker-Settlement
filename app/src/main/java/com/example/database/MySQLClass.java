package com.example.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.class_model.DemoGetterSetterClass;

import java.util.ArrayList;

public class MySQLClass extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "TransactionData";
    public static final int DATABASE_VERSION = 1;
    public static final String BUYTABLE = "BuyData";
    public static final String SELLTABLE = "SellData";
    public static final String LEDGERTABLE = "LedgerTable";
    public static final String STOCKINVENTORYTABLE = "InventoryTable";

    public static final String UID = "_id";
    public static final String STOCK = "Stock_Name";
    public static final String PRICE = "Price";
    public static final String UNITS = "Units";
    public static final String WACC = "WACC";
    public static final String BUYSELL = "Buy_Sell";
    public static final String TOTALAMOUT = "Total_Amount";
    public static final String BROKERTABLE = "BrokerDetails";
    public static final String PARTICULAR = "Particulars";
    public static final String PAYABLE = "Payable_Amount";
    public static final String RECEIVABLE = "Receivable_Amount";
    public static final String REMARK = "Remark";
    public static final String CASHPAID = "Cash_Paid";

    public static final String BUYINGPRICE = "B_Price";
    public static final String SELLINGPRICE = "S_Price";
    public static final String PROFITLOSS = "Profit_Loss";

    //crating a BuyingTable;
    public static final String BUYTABLEQUERY = "CREATE TABLE " + BUYTABLE + "(" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
            + BUYSELL + " VARCHAR(255) ," + STOCK + " VARCHAR(255) ," +
            PRICE + " INTEGER ," + UNITS + " INTEGER ," + WACC + " DOUBLE ," + TOTALAMOUT + " DOUBLE);";

    //crating a SellingTable;
    public static final String SELLTABLEQUERY = "CREATE TABLE " + SELLTABLE + "(" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
            + BUYSELL + " VARCHAR(255) ," + STOCK + " VARCHAR(255) ," +
            BUYINGPRICE + " INTEGER ," + SELLINGPRICE + " INTEGER ," + UNITS + " INTEGER ," + PROFITLOSS + " DOUBLE ," + TOTALAMOUT + " DOUBLE);";

    //creating a LedgerTable
    public static final String LEDGERTABLEQUERY = "CREATE TABLE " + LEDGERTABLE + "(" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
            + PARTICULAR + " VARCHAR(255) ," +
            PAYABLE + " DOUBLE ," + RECEIVABLE + " DOUBLE ," + CASHPAID + " DOUBLE ," + REMARK + " VARCHAR(225));";

    public static final String STOCKINVENTORYTABLEQUERY = "CREATE TABLE " + STOCKINVENTORYTABLE + "(" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
            + STOCK + " VARCHAR(255) ," +
            BUYINGPRICE + " DOUBLE ," + UNITS + " DOUBLE);";

    //Query For Broker Details

    Context context;


    public MySQLClass(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        this.context = context;

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        try{
            Toast.makeText(context.getApplicationContext(), "Table Created",Toast.LENGTH_SHORT).show();
            sqLiteDatabase.execSQL(BUYTABLEQUERY);
            sqLiteDatabase.execSQL(SELLTABLEQUERY);
            sqLiteDatabase.execSQL(LEDGERTABLEQUERY);
            sqLiteDatabase.execSQL(STOCKINVENTORYTABLEQUERY);

        } catch (Exception e) {
            Toast.makeText(context.getApplicationContext(), "Table Not Created",Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

    }

    public void addStockDetails(String stock, double price, int units, double wacc, double totalmoney){
        SQLiteDatabase db = this.getWritableDatabase();

        //String query = "INSERT INTO " + BUYTABLE + "VALUES(" 50,50,50,60,50 ");"

        ContentValues contentValues = new ContentValues();

        contentValues.put(BUYSELL,"Buy");
        contentValues.put(STOCK,stock);
        contentValues.put(PRICE,price);
        contentValues.put(UNITS,units);
        contentValues.put(WACC,wacc);
        contentValues.put(TOTALAMOUT,totalmoney);

        //db.insert(BUYTABLE,null,contentValues);
        if (db.insert(BUYTABLE,null,contentValues)>0){
        Toast.makeText(context.getApplicationContext(), "Value Inserted!!",Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(context.getApplicationContext(), "Failed to Upload!!",Toast.LENGTH_SHORT).show();

        }
        db.close();

    }
    public void insertBuyIntoLedger(String Stock, double amount, String Remark){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(PARTICULAR,Stock);
        contentValues.put(REMARK,Remark);
        if(Remark.equals("Buying Details")){
            contentValues.put(PAYABLE,amount);
        }
        else if (Remark.equals("Cash Received")){
            contentValues.put(PAYABLE,amount);
        }
        else {
            contentValues.put(RECEIVABLE,amount);
        }

        database.insert(LEDGERTABLE,null,contentValues);
        database.close();

    }
    public void insertintoInventory(String Stock, double invent_price, int units){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(STOCK,Stock);
        contentValues.put(BUYINGPRICE,invent_price);
        contentValues.put(UNITS,units);

        database.insert(STOCKINVENTORYTABLE,null,contentValues);
        database.close();

    }

    public void sellStockDetails(String Stock, double buyPrice, double sellPrice, int units, double profitloss, double receivableAmount){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(BUYSELL,"Sell");
        contentValues.put(STOCK,Stock);
        contentValues.put(BUYINGPRICE,buyPrice);
        contentValues.put(SELLINGPRICE,sellPrice);
        contentValues.put(UNITS,units);
        contentValues.put(PROFITLOSS,profitloss);
        contentValues.put(TOTALAMOUT,receivableAmount);

        if (database.insert(SELLTABLE,null,contentValues)>0){
            Toast.makeText(context.getApplicationContext(), "Sell Hisotry Recorded!!",Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(context.getApplicationContext(), "Failed to Upload!!",Toast.LENGTH_SHORT).show();

        }
        database.close();
    }


    public ArrayList<DemoGetterSetterClass> loadalldata(){
        ArrayList<DemoGetterSetterClass> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        String readall = "SELECT * FROM " + BUYTABLE;
        Cursor cursor = sqLiteDatabase.rawQuery(readall,null);
        if (cursor.moveToFirst()){
            do {
                list.add(new DemoGetterSetterClass(cursor.getInt(0), cursor.getInt(3),cursor.getInt(4)
                        , cursor.getDouble(5), cursor.getInt(6), cursor.getString(2), cursor.getString(1)));


            }
            while (cursor.moveToNext());
        }
        cursor.close();
        sqLiteDatabase.close();

        return list;

    }
    public double[] calculateNetAmount(){
        ArrayList<DemoGetterSetterClass> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        double[] netAmount = new double[2];

        String readall = "SELECT * FROM " + LEDGERTABLE;
        Cursor cursor = sqLiteDatabase.rawQuery(readall,null);
        if (cursor.moveToFirst()){
            do {
                netAmount[0] = netAmount[0] + cursor.getDouble(2);
                netAmount[1] = netAmount[1] + cursor.getDouble(3) + cursor.getDouble(4);
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        sqLiteDatabase.close();

        return netAmount;

    }
    public ArrayList<DemoGetterSetterClass> loadallledgerdata(){
        ArrayList<DemoGetterSetterClass> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        String readall = "SELECT * FROM " + LEDGERTABLE;
        Cursor cursor = sqLiteDatabase.rawQuery(readall,null);
        if (cursor.moveToFirst()){
            do {
                list.add(new DemoGetterSetterClass(cursor.getInt(0), cursor.getDouble(2),0
                        , cursor.getDouble(3), cursor.getDouble(4), cursor.getString(1), cursor.getString(5)));

            }
            while (cursor.moveToNext());
        }
        cursor.close();
        sqLiteDatabase.close();

        return list;

    }
    public ArrayList<DemoGetterSetterClass> loadallselldata(){
        ArrayList<DemoGetterSetterClass> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        String readall = "SELECT * FROM " + SELLTABLE;
        Cursor cursor = sqLiteDatabase.rawQuery(readall,null);
        if (cursor.moveToFirst()){
            do {
                list.add(new DemoGetterSetterClass(cursor.getInt(0), cursor.getDouble(3),cursor.getDouble(4)
                        , cursor.getDouble(7), cursor.getInt(5), cursor.getString(2), cursor.getString(1)));

            }
            while (cursor.moveToNext());
        }
        cursor.close();
        sqLiteDatabase.close();

        return list;

    }

    public ArrayList<DemoGetterSetterClass> readInventoryTable(){
        ArrayList<DemoGetterSetterClass> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        String readall = "SELECT * FROM " + STOCKINVENTORYTABLE;
        Cursor cursor = sqLiteDatabase.rawQuery(readall,null);
        if (cursor.moveToFirst()){
            do {
                list.add(new DemoGetterSetterClass(cursor.getInt(0), cursor.getDouble(2),cursor.getInt(3)
                        , 0, 0, cursor.getString(1), null));

            }
            while (cursor.moveToNext());
        }
        cursor.close();
        sqLiteDatabase.close();

        return list;

    }

    public void deleteData(int id){
        SQLiteDatabase database = this.getWritableDatabase();
        //deleting from table where id is matches
        database.delete(BUYTABLE,"_id="+id, null);
        database.close();

    }
    public int[] findStock(String Name){
        int[] id={0,0};
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String ReadQuery = "Select * FROM " + BUYTABLE;
        Cursor cursor = sqLiteDatabase.rawQuery(ReadQuery, null);

        if (cursor.moveToFirst()){
            do {
                String Stock = cursor.getString(2);
                if (Stock.equals(Name)) {
                    id[0] = cursor.getInt(3);
                    id[1] = cursor.getInt(4);
                    //System.out.println("Found: " + Stock);
                    //Toast.makeText(context.getApplicationContext(), "" + Stock, Toast.LENGTH_SHORT).show();
                }
            }
            while (cursor.moveToNext());
        }
        return id;

    }
    public double[] findStockAvaialbeOrNot(String StockName){
        SQLiteDatabase database = this.getReadableDatabase();
        String FindStockQuery = "SELECT * FROM " + STOCKINVENTORYTABLE;
        double[] details=new double[2];

        Cursor cursor = database.rawQuery(FindStockQuery,null);
        if (cursor.moveToFirst()){
            do {
                String Stock = cursor.getString(1);
                if (Stock.equals(StockName)) {
                    details[0] = cursor.getDouble(2);
                    details[1] = cursor.getDouble(3);
                    System.out.println("Display1: "+details[0]+details[1]);
                    //Toast.makeText(context.getApplicationContext(),"Stock Found"+details[0]+details[1],Toast.LENGTH_SHORT).show();

                }
            }
            while (cursor.moveToNext());
        }
        return details;

    }
    public void updateInventoryOnSellStock(String StockName, int StockUnits) {
        SQLiteDatabase database = this.getReadableDatabase();
        int units=0;
        String FindStockQuery = "SELECT * FROM " + STOCKINVENTORYTABLE;

        Cursor cursor = database.rawQuery(FindStockQuery, null);
        if (cursor.moveToFirst()) {
            do {
                String Stock = cursor.getString(1);
                if (Stock.equals(StockName)) {
                    units = cursor.getInt(3);
                    //Toast.makeText(context.getApplicationContext(),"Stock Found"+details[0]+details[1],Toast.LENGTH_SHORT).show();
                }
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        database.close();

        SQLiteDatabase database1 = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        String[] whereArgs = {StockName};
        if (StockUnits < units){
            units = units-StockUnits;
            contentValues.put(UNITS,units);

            database1.update(STOCKINVENTORYTABLE,contentValues,"Stock_Name = ?",whereArgs);

        }
        if (StockUnits==units){
            database1.delete(STOCKINVENTORYTABLE,"Stock_Name = ?",whereArgs);


        }

    }

    public void updateAddedStock(String StockName, double new_Price, double new_units){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(BUYINGPRICE,new_Price);
        contentValues.put(UNITS,new_units);
        String whereArgs[] = {StockName};

        database.update(STOCKINVENTORYTABLE,contentValues,"Stock_Name = ?",whereArgs);
        database.close();

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS contacts");
        onCreate(sqLiteDatabase);


    }
}
