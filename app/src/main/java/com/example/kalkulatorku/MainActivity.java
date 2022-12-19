package com.example.kalkulatorku;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView Perhitungan, hasil_perhitungan;
    MaterialButton buttonC, buttonKurungBuka, buttonKurungTutup;
    MaterialButton buttonBagi, buttonKali, buttonTambah, buttonKurang, buttonEnter;
    MaterialButton button0, button1, button2, button3, button4, button5, button6, button7, button8, button9;
    MaterialButton buttonAC, buttonTitik;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hasil_perhitungan = findViewById(R.id.proses);
        Perhitungan = findViewById(R.id.hasil);

        assignId(buttonC,R.id.button_C);
        assignId(buttonKurungBuka,R.id.button_kurungbuka);
        assignId(buttonKurungTutup,R.id.button_kurungtutup);
        assignId(buttonBagi,R.id.button_bagi);
        assignId(buttonKali,R.id.button_kali);
        assignId(buttonTambah,R.id.button_tambah);
        assignId(buttonKurang,R.id.button_kurang);
        assignId(buttonEnter,R.id.button_enter);
        assignId(button0,R.id.button_0);
        assignId(button1,R.id.button_1);
        assignId(button2,R.id.button_2);
        assignId(button3,R.id.button_3);
        assignId(button4,R.id.button_4);
        assignId(button5,R.id.button_5);
        assignId(button6,R.id.button_6);
        assignId(button7,R.id.button_7);
        assignId(button8,R.id.button_8);
        assignId(button9,R.id.button_9);
        assignId(buttonAC,R.id.button_AC);
        assignId(buttonTitik,R.id.button_titik);



    }
    void assignId(MaterialButton btn, int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view){
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();
//        hasil_perhitungan.setText(buttonText);
        String dataToCalculate = hasil_perhitungan.getText().toString();

        if(buttonText.equals("AC")){
            hasil_perhitungan.setText("");
            Perhitungan.setText("0");
            return;
        }

        if(buttonText.equals("=")){
            hasil_perhitungan.setText(Perhitungan.getText());
            return;
        }

        if(buttonText.equals("C")){
            dataToCalculate = dataToCalculate.substring(0,dataToCalculate.length()-1);
        }else{
            dataToCalculate = dataToCalculate+buttonText;
        }
        hasil_perhitungan.setText(dataToCalculate);

        String finalResult = getResult(dataToCalculate);

        if(!finalResult.equals("Err")){
            hasil_perhitungan.setText(finalResult);
        }
    }
        String getResult(String data){
        try{
            Context context = Context.enter();
            context.setOptimizationLevel(-1);;
            Scriptable scriptable = context.initStandardObjects();
            String finalResult = context.evaluateString(scriptable,data,"Javascript", 1, null).toString();
            if(finalResult.endsWith(".0")){
                finalResult = finalResult.replace(".0", "");
            }
            return finalResult;
        }catch (Exception e){
            return "Err";
        }
        }
}