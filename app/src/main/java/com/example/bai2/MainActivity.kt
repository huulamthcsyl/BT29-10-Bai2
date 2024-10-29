package com.example.bai2

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.RadioButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {

    lateinit var btnShow: Button;
    lateinit var numberTxt: TextView;
    var option: Int = -1;
    lateinit var list: ListView;
    lateinit var errorTxt: TextView;
    var adpater: ArrayAdapter<String>? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        btnShow = findViewById(R.id.btnShow);
        numberTxt = findViewById(R.id.numberTxt);
        list = findViewById(R.id.list);
        errorTxt = findViewById(R.id.errorTxt);

        findViewById<RadioButton>(R.id.evenRadio).setOnClickListener() {
            option = 0;
        }
        findViewById<RadioButton>(R.id.oddRadio).setOnClickListener() {
            option = 1;
        }
        findViewById<RadioButton>(R.id.sqrRadio).setOnClickListener() {
            option = 2;
        }

        btnShow.setOnClickListener(){
            errorTxt.visibility = TextView.GONE;
            var number: Int;
            try {
                number = numberTxt.text.toString().toInt();
            } catch (e: NumberFormatException) {
                errorTxt.text = "Nhập giá trị ban đầu";
                errorTxt.visibility = TextView.VISIBLE;
                return@setOnClickListener;
            }
            if(option == -1) {
                errorTxt.text = "Chọn một lựa chọn";
                errorTxt.visibility = TextView.VISIBLE;
                return@setOnClickListener;
            }
            var listData = mutableListOf<String>();
            if (option == 0) {
                for (i in 0..number) {
                    if (i % 2 == 0) {
                        listData.add(i.toString());
                    }
                }
            } else if (option == 1) {
                for (i in 1..number) {
                    if (i % 2 != 0) {
                        listData.add(i.toString());
                    }
                }
            } else {
                for (i in 0..sqrt(number.toDouble()).toInt()) {
                    listData.add((i * i).toString());
                }
            }
            adpater = ArrayAdapter(this, android.R.layout.simple_list_item_1, listData);
            list.adapter = adpater;
        }
    }
}