package com.bujosa.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private TextView Textview;
    private Button C, Add, Mul, Div, Sub, One, Two, Three, Four, Five, Six, Seven, Eight, Nine, Zero, Point;
    private String input, lastOperation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Textview = findViewById(R.id.textView);
        Add = findViewById(R.id.additionButton);
        Mul = findViewById(R.id.multiplyButton);
        One = findViewById(R.id.oneButton);
        Two = findViewById(R.id.twoButton);
        Three = findViewById(R.id.threeButton);
        Four = findViewById(R.id.fourButton);
        Five = findViewById(R.id.fiveButton);
        Six = findViewById(R.id.sixButton);
        Seven = findViewById(R.id.sevenButton);
        Eight = findViewById(R.id.eightButton);
        Nine = findViewById(R.id.nineButton);
        Zero = findViewById(R.id.zeroButton);
        Point = findViewById(R.id.pointButton);
        Sub = findViewById(R.id.subtractionButton);
        Div = findViewById(R.id.divisionButton);
        C = findViewById(R.id.clearButton);
    }

    public void ButtonClick(View view){
        Button button =(Button) view;
        
        String data = button.getText().toString();

        if(input==null){
            input="0";
        }

        switch (data){
            case "c":
                input="0";
                break;
            case "×":
            case "÷":
            case "+":
            case "−":
                checkOperation(data);
                lastOperation = data;
                break;
            case ".":
                input+=".";
                break;
            case "±":
                PlusMinus();
                break;
            case "=":
                Solve();
                break;
            case "%":
                Percentage();
                break;
            default:
                lastOperation = "";
                if(input.equals("0")){
                    input = "";
                }
                input+=data;
        }

        Textview.setText(input);
    }

    @SuppressLint("DefaultLocale")
    private void Solve(){
        if(input.split("×").length==2){
            String[] value = input.split("×");
           try{
               double mul = Double.parseDouble(value[0])*Double.parseDouble(value[1]);
               input = "" + String.format("%.2f", mul);
           }
           catch (Exception ignored){}
        }
        else if(input.split("÷").length==2){
            String[] value = input.split("÷");
            try{
                double div = Double.parseDouble(value[0]) / Double.parseDouble(value[1]);
                input = "" + String.format("%.2f", div);
            }
            catch (Exception ignored){}
        }
        else if(input.split("\\+").length==2){
            String[] value=input.split("\\+");
            try{
                double add = Double.parseDouble(value[0]) + Double.parseDouble(value[1]);
                input = "" + String.format("%.2f", add);
            }
            catch (Exception ignored){}
        }
        else if(input.split("−").length==2){
            String[] value =input.split("−");
            if(value[0].equals("") && value.length==2){
                value[0]=0+"";
            }
            try{
                double sub = Double.parseDouble(value[0]) - Double.parseDouble(value[1]);
                input = "" + String.format("%.2f", sub);
            }
            catch (Exception ignored){}
        }

        String[] n = input.split("\\.");
        if(n.length>1){
            if(n[1].equals("0")){
                input=n[0];
            }
        }
        Textview.setText(input);
    }

    @SuppressLint("DefaultLocale")
    private void Percentage(){
        if(lastOperation != null){
            String lastElement = getLastElement();
            switch (lastElement){
                case "×":
                case "÷":
                case "+":
                case "−":
                    input = input.substring(0, input.length() -1);
                default:
                    Solve();
                    try{
                        double div = Double.parseDouble(input) / 100;
                        input = "" + String.format("%.2f", div);
                    }
                    catch (Exception ignored){}
                    Textview.setText(input);

            }
        }
    }

    private void PlusMinus(){
        Solve();
        String result = input.substring(0,1);
        if(result.equals("-")){
            input = input.replaceFirst("-", "");
        }else{
            input = "-" + input;
        }
    }

    private void checkOperation(String data){
        if(lastOperation != null){
            String lastElement = getLastElement();
            switch (lastElement){
                case "×":
                case "÷":
                case "+":
                case "−":
                    input = input.substring(0, input.length() -1) + data;
                    Solve();
                    break;
                default:
                    Solve();
                    input+=data;

            }
        }
    }

    private String getLastElement(){
        return input.charAt(input.length() - 1) + "";
    }
}