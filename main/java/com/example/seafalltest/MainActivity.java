package com.example.seafalltest;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;
import android.widget.Button;
import android.util.Log;
import android.widget.Switch;



public class MainActivity extends AppCompatActivity {

    private Button buttonCalculate;
    private TextView textViewResults;
    private EditText editTextDiceCount;
    private EditText editTextDefense;
    private String stringDiceCount;
    private String stringDefense;
    private Switch switchRerollWeak;
    private Switch switchDoubleStrong;
    private int intDiceCount;
    private int intDefense;
    private double[] doubleProbs;
    private Button buttonDiceDec;
    private Button buttonDiceInc;
    private Button buttonDefenseDec;
    private Button buttonDefenseInc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewResults = (TextView) findViewById(R.id.textViewResults);
        buttonCalculate = (Button) findViewById(R.id.buttonCalculate);
        editTextDiceCount = (EditText) findViewById(R.id.editTextDice);
        editTextDefense = (EditText) findViewById(R.id.editTextDefense);
        switchRerollWeak = (Switch) findViewById(R.id.switchRerollWeak);
        switchDoubleStrong = (Switch) findViewById(R.id.switchDoubleStrong);
        buttonDiceDec = (Button) findViewById(R.id.buttonDiceDec);
        buttonDiceInc = (Button) findViewById(R.id.buttonDiceInc);
        buttonDefenseDec = (Button) findViewById(R.id.buttonDefenseDec);
        buttonDefenseInc = (Button) findViewById(R.id.buttonDefenseInc);

        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                stringDiceCount = editTextDiceCount.getText().toString();
                intDiceCount = Integer.parseInt(stringDiceCount);
                stringDefense = editTextDefense.getText().toString();
                intDefense = Integer.parseInt(stringDefense);
                double doubleDamageChance = 0.0;
                int i;

                doubleProbs = findResults(intDiceCount);

                String stringResults = "";
                int resultLength = doubleProbs.length;

                if(resultLength < intDefense) {
                    doubleDamageChance = 100.0;
                } else {
                    for(i=0;i<intDefense;i++) {
                        doubleDamageChance += doubleProbs[i]*100;
                    }
                } // end resultLength

                stringResults += "Chance of Damage: "+String.format("%2.1f",doubleDamageChance)+"%\n\n";

                for (i = 0; i < resultLength; i++) {
                    if (i == 1) {
                        stringResults += i + " success: " + String.format("%2.1f", 100.0 * doubleProbs[i]) + "%\n";
                    } else {
                        stringResults += i + " successes: " + String.format("%2.1f", 100.0 * doubleProbs[i]) + "%\n";
                    }

                } // end for


                textViewResults.setText(stringResults);
            } // end onClick
        }); // end new OnClickListener (buttonCalculate)

        buttonDiceDec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                stringDiceCount = editTextDiceCount.getText().toString();
                intDiceCount = Integer.parseInt(stringDiceCount);
                if (intDiceCount == 0) {
                    return;
                } else {
                    intDiceCount--;
                    stringDiceCount = Integer.toString(intDiceCount);
                    editTextDiceCount.setText(stringDiceCount);
                }
            } // end onClick
        }); // end new OnClickListener (buttonDiceDec)

        buttonDiceInc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                stringDiceCount = editTextDiceCount.getText().toString();
                intDiceCount = Integer.parseInt(stringDiceCount);
                if (intDiceCount == 100) {
                    return;
                } else {
                    intDiceCount++;
                    stringDiceCount = Integer.toString(intDiceCount);
                    editTextDiceCount.setText(stringDiceCount);
                }
            } // end onClick
        }); // end new OnClickListener (buttonDiceInc)
        buttonDefenseDec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                stringDiceCount = editTextDefense.getText().toString();
                intDiceCount = Integer.parseInt(stringDiceCount);
                if (intDiceCount == 0) {
                    return;
                } else {
                    intDiceCount--;
                    stringDiceCount = Integer.toString(intDiceCount);
                    editTextDefense.setText(stringDiceCount);
                }
            } // end onClick
        }); // end new OnClickListener (buttonDefenseDec)

        buttonDefenseInc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                stringDiceCount = editTextDefense.getText().toString();
                intDiceCount = Integer.parseInt(stringDiceCount);
                if (intDiceCount == 100) {
                    return;
                } else {
                    intDiceCount++;
                    stringDiceCount = Integer.toString(intDiceCount);
                    editTextDefense.setText(stringDiceCount);
                }
            } // end onClick
        }); // end new OnClickListener (buttonDefenseInc)

    } // end onCreate


    private double[] findResults ( int intDice){
        double[] localProbs;
        double[] result;
        int newlength, oldlength;
        localProbs = new double[2];

        if (switchRerollWeak.isChecked()) {
            if (switchDoubleStrong.isChecked()) {
                localProbs = new double[3];
                localProbs[0] = 2.0 / 5.0; // Three possible die states, effective 1/5 per side
                localProbs[1] = 2.0 / 5.0;
                localProbs[2] = 1.0 / 5.0;
            } else {
                localProbs[0] = 2.0 / 5.0; // Two possible die states, effective 1/5 per side
                localProbs[1] = 3.0 / 5.0;
            } // end doubleStrong
        } else {
            if (switchDoubleStrong.isChecked()) {
                localProbs = new double[3];
                localProbs[0] = 1.0 / 3.0;
                localProbs[1] = 1.0 / 2.0;
                localProbs[2] = 1.0 / 6.0;
            } else {
                localProbs[0] = 1.0 / 3.0; // unmodified die roll
                localProbs[1] = 2.0 / 3.0;
            } // end doubleStrong
        } // end reroll weak


        oldlength = localProbs.length;

        newlength = (oldlength * intDice) - intDice + 1; // this is the number of terms in the resulting generating function polynomial

        result = new double[newlength];

        result = expPolys(localProbs, (intDice - 1));

        return result;
    } // end calculateProbabilityArray

    private double[] expPolys ( double[] f1, int exp){
        int i;
        double[] product, f2;
        if (exp == 0) {
            return f1;
        } // end if exp is 0
        Log.d("expPolys", "Length of f1 is " + f1.length);
        Log.d("expPolys", "f1[0] is " + f1[0]);
        Log.d("expPolys", "f1[1] is " + f1[1]);
        f2 = new double[f1.length];
        System.arraycopy(f1, 0, f2, 0, f2.length);
        product = new double[(f1.length + f2.length - 1)];

        for (i = 0; i < exp; i++) {
            product = new double[(f1.length + f2.length - 1)];
            product = multiplyPolys(f1, f2);
            f2 = new double[product.length];
            System.arraycopy(product, 0, f2, 0, product.length);
        } // end for i
        return product;
    }   // end expPolys

    private double[] multiplyPolys ( double[] f1, double[] f2){
        double[] product;
        int i, j;
        int f1length = f1.length;
        int f2length = f2.length;
        int newlength = f1length + f2length - 1;
        product = new double[newlength];

        for (i = 0; i < f1length; i++) {
            for (j = 0; j < f2length; j++) {
                product[(i + j)] += ((f1[i]) * (f2[j]));
            } // end for j
        } // end for i
        return product;
    } // end multiplyPolys


} // end MainActivity

