package com.example.seafalltest;


import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;
import android.widget.Button;
import android.util.Log;
import android.widget.Switch;
import android.content.Intent;
import android.widget.ScrollView;
import android.view.MotionEvent;



public class MainActivity extends AppCompatActivity {

    private Button buttonCalculate;
    private TextView textViewResults;
    private EditText editTextDiceCount;
    private EditText editTextDefense;
    private EditText editTextAdditionalSuccesses;
    private String stringDiceCount;
    private String stringDefense;
    private Switch switchRerollWeak;
    private Switch switchDoubleStrong;
    private Switch switchWeaksDontCount;
    private Switch switchStrongsDefrayDamage;
    private int intDiceCount;
    private int intDefense;
    private double[] doubleProbs;
    private Button buttonDiceDec;
    private Button buttonDiceInc;
    private Button buttonDefenseDec;
    private Button buttonDefenseInc;
    private Button buttonWizard;
    private Button buttonAdditionalSuccessesDec;
    private Button buttonAdditionalSuccessesInc;

    private ScrollView scrollView2;

    public static final int DETERMINE_DICE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewResults = (TextView) findViewById(R.id.textViewResults);
        buttonCalculate = (Button) findViewById(R.id.buttonCalculate);
        editTextDiceCount = (EditText) findViewById(R.id.editTextDice);
        editTextDefense = (EditText) findViewById(R.id.editTextDefense);
        editTextAdditionalSuccesses = (EditText) findViewById(R.id.editTextAdditionalSuccesses);
        switchRerollWeak = (Switch) findViewById(R.id.switchRerollWeak);
        switchDoubleStrong = (Switch) findViewById(R.id.switchDoubleStrong);
        switchWeaksDontCount = (Switch) findViewById(R.id.switchWeaksDontCount);
        switchStrongsDefrayDamage = (Switch) findViewById(R.id.switchStrongsDefrayDamage);
        buttonDiceDec = (Button) findViewById(R.id.buttonDiceDec);
        buttonDiceInc = (Button) findViewById(R.id.buttonDiceInc);
        buttonDefenseDec = (Button) findViewById(R.id.buttonDefenseDec);
        buttonDefenseInc = (Button) findViewById(R.id.buttonDefenseInc);
        buttonWizard = (Button) findViewById(R.id.buttonWizard);
        buttonAdditionalSuccessesDec = (Button) findViewById(R.id.buttonAdditionalSuccessesDec);
        buttonAdditionalSuccessesInc = (Button) findViewById(R.id.buttonAdditionalSuccessesInc);

        scrollView2 = (ScrollView) findViewById(R.id.scrollView2);

        editTextDiceCount.setInputType(0);
        editTextDefense.setInputType(0);
        editTextAdditionalSuccesses.setInputType(0);

        scrollView2.setOnTouchListener(new View.OnTouchListener() {
           @Override
           public boolean onTouch(View arg0, MotionEvent event) {
               if (editTextDiceCount.hasFocus()) {
                   editTextDiceCount.clearFocus();
               }
               if (editTextDefense.hasFocus()) {
                   editTextDefense.clearFocus();
               }
               if(editTextAdditionalSuccesses.hasFocus()) {
                   editTextAdditionalSuccesses.clearFocus();
               }

               return false;
           }
       });

        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                stringDiceCount = editTextDiceCount.getText().toString();
                intDiceCount = Integer.parseInt(stringDiceCount);
                stringDefense = editTextDefense.getText().toString();
                intDefense = Integer.parseInt(stringDefense);
                double doubleNoDamage = 0.0;
                double doubleDamageChance = 0.0;
                double doubleOneDamage = 0.0;
                double doubleTwoDamage = 0.0;
                double doubleThreePlusDamage = 0.0;
                double dTempProb, dHitChance, dMissChance;
                int i, j;
                int iSuccessCase;
                double[] dSSProb = new double[intDiceCount + 1];

                doubleProbs = findResults(intDiceCount);
                String stringResults = "";
                int resultLength = doubleProbs.length;

                if ((intDefense - resultLength) == 1) {
                    doubleOneDamage = 100.0;
                } else if((intDefense - resultLength) == 2) {
                    doubleOneDamage = 100.0;
                    doubleTwoDamage = 100.0;
                } else if((intDefense - resultLength) > 2) {
                    doubleOneDamage = 100.0;
                    doubleTwoDamage = 100.0;
                    doubleThreePlusDamage = 100.0;
                } else {
                    for(i=0;i<intDefense;i++) {
                        //doubleDamageChance += doubleProbs[i]*100;
                        if((intDefense-i)==1) {
                            doubleOneDamage += 100.0 * doubleProbs[i];
                        }
                        if((intDefense-i)==2) {
                            doubleTwoDamage += 100.0 * doubleProbs[i];
                        }
                        if((intDefense-i)>2) {
                            doubleThreePlusDamage += 100.0 * doubleProbs[i];
                        }

                    } // end for
                    doubleNoDamage = (100.0-doubleOneDamage-doubleTwoDamage-doubleThreePlusDamage);
                } // end resultLength

                if(switchStrongsDefrayDamage.isChecked()) {
                    doubleOneDamage = 0.0;
                    doubleTwoDamage = 0.0;
                    doubleThreePlusDamage = 0.0;
                    doubleNoDamage = 0.0;

                    iSuccessCase = intDefense - 1;
                    if(switchWeaksDontCount.isChecked()) {
                        dHitChance = 1.0/3.0;
                        dMissChance = 2.0/3.0;
                    } else {
                        dHitChance = 1.0/4.0;
                        dMissChance = 3.0/4.0;
                    }

                    for(i=0;i<intDefense;i++) { // first we iterate from zero successes up to one less than site defense
                                                // this is the number of successes 'rolled'
                        for(j=0;j<(i+1);j++) { // next we iterate from 0 to i
                                                // this is the number of strong successes we'll be calculating the chances of
                            dTempProb = strongSuccessCountProb(i,j,dHitChance,dMissChance);

                            if((intDefense - i - j) > 2) {
                                if(i==0) {
                                    doubleThreePlusDamage += 100.0 * doubleProbs[0];
                                } else {
                                    doubleThreePlusDamage += 100.0 * doubleProbs[i] * dTempProb;
                                }
                            }
                            if((intDefense - i - j) == 2) {
                                if(i==0) {
                                    doubleTwoDamage += 100.0 * doubleProbs[0];
                                } else {
                                    doubleTwoDamage += 100.0 * doubleProbs[i] * dTempProb;
                                }
                            }
                            if((intDefense - i - j) == 1) {
                                if(i==0) {
                                    doubleOneDamage += 100.0 * doubleProbs[0];
                                } else {
                                    doubleOneDamage += 100.0 * doubleProbs[i] * dTempProb;
                                }
                            }

                        } // end j to i+1
                    } // end i to intDefense
                    doubleNoDamage = (100.0 - doubleOneDamage - doubleTwoDamage - doubleThreePlusDamage);
                } // end if Strongs Defray Damage is Checked

                stringResults += "Chance of No Damage: "+String.format("%2.1f",doubleNoDamage)+"%\n";
                stringResults += "Chance of 1/2/3+ Damage: "+String.format("%2.1f",doubleOneDamage)+"% / "+String.format("%2.1f",doubleTwoDamage)+"% / "+String.format("%2.1f",doubleThreePlusDamage)+"%\n\n";

                for (i = 0; i < resultLength; i++) {
                    if (i == 1) {
                        stringResults += i + " success: " + String.format("%2.1f", 100.0 * doubleProbs[i]) + "%\n";
                    } else {
                        stringResults += i + " successes: " + String.format("%2.1f", 100.0 * doubleProbs[i]) + "%\n";
                    }

                } // end for

                if(switchStrongsDefrayDamage.isChecked()) {
                    if(switchWeaksDontCount.isChecked()) {
                        for (i = 0; i < (intDiceCount + 1); i++) {
                            dSSProb[i] = strongSuccessCountProb(intDiceCount, i, 1.0 / 5.0, 4.0 / 5.0);
                        } // end loop from 0 to dice Count
                    } else {
                        for (i = 0; i < (intDiceCount + 1); i++) {
                            dSSProb[i] = strongSuccessCountProb(intDiceCount, i, 1.0 / 6.0, 5.0 / 6.0);
                        } // end loop from 0 to dice Count
                    }

                    stringResults += "\nChance of:\n";
                    for(i=0; i < (intDiceCount +1); i++) {
                        if (i==1) {
                            stringResults += i + " strong success: " + String.format("%2.1f", 100 * dSSProb[i]) + "%\n";
                        } else {
                            stringResults += i + " strong successes: " + String.format("%2.1f", 100 * dSSProb[i]) + "%\n";
                        }

                    } // end loop from 0 to dice Count

                } // end if Strongs Defray is checked

                textViewResults.setText(stringResults);
            } // end onClick
        }); // end new OnClickListener (buttonCalculate)

        buttonWizard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(MainActivity.this,DetermineDice.class);
                MainActivity.this.startActivityForResult(intent,DETERMINE_DICE);

            } // end onClick
        }); // end new OnClickListener (buttonWizard)

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

        buttonAdditionalSuccessesDec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                stringDiceCount = editTextAdditionalSuccesses.getText().toString();
                intDiceCount = Integer.parseInt(stringDiceCount);
                if (intDiceCount == 0) {
                    return;
                } else {
                    intDiceCount--;
                    stringDiceCount = Integer.toString(intDiceCount);
                    editTextAdditionalSuccesses.setText(stringDiceCount);
                }
            } // end onClick
        }); // end new OnClickListener (buttonAdditionalSuccessesDec)

        buttonAdditionalSuccessesInc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                stringDiceCount = editTextAdditionalSuccesses.getText().toString();
                intDiceCount = Integer.parseInt(stringDiceCount);
                if (intDiceCount == 100) {
                    return;
                } else {
                    intDiceCount++;
                    stringDiceCount = Integer.toString(intDiceCount);
                    editTextAdditionalSuccesses.setText(stringDiceCount);
                }
            } // end onClick
        }); // end new OnClickListener (buttonAdditionalSuccessesInc)

    } // end onCreate

    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        switch(requestCode) {
            case DETERMINE_DICE:
                if (resultCode == RESULT_OK) {

                    Bundle bundle = data.getExtras();
                    editTextDiceCount.setText(Integer.toString(bundle.getInt("diceCount")));
                    editTextDefense.setText(Integer.toString(bundle.getInt("defense")));
                    break;
                } // end if result code ok
        } // end switch

    } // end onActivityResult

    private double[] findResults ( int intDice){
        double[] localProbs;
        double[] result;
        double[] modifiedResult;
        int newlength, oldlength, iAdditionalSuccesses;
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
                if (switchWeaksDontCount.isChecked()) {
                    localProbs = new double[3];
                    localProbs[0] = 1.0 / 2.0;
                    localProbs[1] = 1.0 / 3.0;
                    localProbs[2] = 1.0 / 6.0;
                } else {
                    localProbs = new double[3];
                    localProbs[0] = 1.0 / 3.0;
                    localProbs[1] = 1.0 / 2.0;
                    localProbs[2] = 1.0 / 6.0;
                }
            } else {
                if(switchWeaksDontCount.isChecked()) {
                    localProbs[0] = 1.0 / 2.0;
                    localProbs[1] = 1.0 / 2.0;
                } else {
                    localProbs[0] = 1.0 / 3.0; // unmodified die roll
                    localProbs[1] = 2.0 / 3.0;
                }
            } // end doubleStrong
        } // end reroll weak


        oldlength = localProbs.length;

        newlength = (oldlength * intDice) - intDice + 1; // this is the number of terms in the resulting generating function polynomial

        result = new double[newlength];

        result = expPolys(localProbs, (intDice - 1));

        iAdditionalSuccesses = Integer.parseInt(editTextAdditionalSuccesses.getText().toString());

        if(iAdditionalSuccesses > 0) {
            newlength += iAdditionalSuccesses;
            modifiedResult = new double[newlength];
            int i;
            for(i=0; i<iAdditionalSuccesses;i++) {
                modifiedResult[i] = 0;
            }
            System.arraycopy(result, 0, modifiedResult, iAdditionalSuccesses, (newlength-iAdditionalSuccesses));

            return modifiedResult;
        } // end additionalSuccesses Calculation

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

   private double strongSuccessCountProb(int iDiceCount, int iSuccessCount, double dHitChance, double dMissChance) {
       int binCoeff = binomialCoeff(iDiceCount, iSuccessCount);
       double hit = Math.pow(dHitChance,iSuccessCount);
       double miss = Math.pow(dMissChance,(iDiceCount-iSuccessCount));

       return (hit * miss * binCoeff);

   } // end strongSuccessCountProb

    private int binomialCoeff(int n, int k) {
        int num;
        int dem1;
        int dem2;
        if(k == 0) {
            return 1;
        }
        if(n == k) {
            return 1;
        }
        num = factorial(n);
        dem2 = factorial(k);
        dem1 = factorial(n-k);
        return (num/(dem1 * dem2));
    } // end binomialCoeff

    private int factorial(int n) {
        int i;
        int j;
        int a = n;
        for (i=1; i< n; i++) {
            j=n-i;
            a = a * j;
        } // end for
        return a;
    } // end factorial



} // end MainActivity class extending AppCompatActivity


