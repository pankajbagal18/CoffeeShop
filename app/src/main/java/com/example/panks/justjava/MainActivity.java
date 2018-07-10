package com.example.panks.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    private int quantity = 0;
    private boolean isWhippedCream = false;
    private boolean isChocolate = false;
    private String Name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        display(quantity);
        displayPrice(quantity * 5);
        EditText editText = (EditText)findViewById(R.id.name_edit_text);
        Name = editText.getText().toString();
        //displayMessage(orderSummary(calculatePrice()));
//        Intent intent = new Intent(Intent.ACTION_VIEW);
//        intent.setData(Uri.parse("geo:47.6,-122.3"));
//        if (intent.resolveActivity(getPackageManager()) != null) {
//            startActivity(intent);
//        }
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        //intent.setType("text/plain");
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL,"pankajsbagal@gmail.com");
        intent.putExtra(Intent.EXTRA_SUBJECT,"Coffee Bill");
        intent.putExtra(Intent.EXTRA_TEXT,orderSummary(calculatePrice()));
        if(intent.resolveActivity(getPackageManager())!=null)
        {
            startActivity(intent);
        }
    }
    //@org.jetbrains.annotations.Contract(pure = true)
    private int calculatePrice()
    {
        int price = quantity*5;
        if(isWhippedCream)
            price += quantity;
        if(isChocolate)
            price += 2*quantity;
        return price;
    }
    private String orderSummary(int price) {
        String stg;
        stg = "Name"+" :- "+Name+"\n";
        stg += "Phone no :- 7840999039\n";
        stg += "Price :- " + price + "\n";
        stg += "Whipped Cream ? " + isWhippedCream+"\n";
        stg += "Chocolate ? "+isChocolate+"\n";
        stg += "Thank You\n";
        return stg;
    }

    /**
     * This method is called when the plus + button is clicked.
     */
    public void inc(View view) {
        quantity++;
        display(quantity);
        displayPrice(quantity * 5);
    }

    /**
     * This method is called when the plus - button is clicked.
     */
    public void dec(View view) {
        quantity--;
        display(quantity);
        displayPrice(quantity * 5);
    }

    /*
        This method shows the state of whipped cream check box.
        True for whipped cream taken
        False for whipped cream not taken.
     */
    public void onCheckWhipped(View view) {
        CheckBox checkBox = (CheckBox) findViewById(R.id.whipped_checkBox);
        isWhippedCream = checkBox.isChecked();
    }

    public void onCheckChocolate(View view)
    {
        CheckBox checkBox = (CheckBox) findViewById(R.id.chocolate_checkBox);
        isChocolate = checkBox.isChecked();
    }
    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }
    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.order_summary_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }
    private void displayMessage(String message)
    {
        TextView orderSummary = (TextView)findViewById(R.id.order_summary_text_view);
        orderSummary.setText(message);
    }
}
/*
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}*/
