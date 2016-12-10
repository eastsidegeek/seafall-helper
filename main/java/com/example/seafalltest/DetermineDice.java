package com.example.seafalltest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Switch;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.content.Intent;
import android.widget.ScrollView;
import android.view.MotionEvent;

/**
 * Created by staffd2 on 12/7/2016.
 */

public class DetermineDice extends AppCompatActivity {

    private EditText editTextFlagshipPrimaryStat;
    private EditText editTextAdvisorBonus;
    private EditText editTextFlagshipDamage;
    private EditText editTextSupportShipPrimaryStat;
    private EditText editTextSupportShipUpgrade;
    private EditText editTextSupportShipDamage;
    private EditText editTextFlagshipUpgrade;
    private EditText editTextGarrison;
    private EditText editTextDefense;
    private EditText editTextSupportShipUpgradeDice;
    private EditText editTextSelfHeldEnmity;
    private EditText editTextDefHeldEnmity;
    private Button buttonBack;
    private Button buttonCalculateDicePool;
    private Switch switchHomeProvinceAttacker;
    private Switch switchHomeProvinceDefender;
    private Switch switchGarrisonExplore;
    private RadioGroup radioGroupEndeavourType;
    private RadioButton radioButtonExplore;
    private RadioButton radioButtonRaid;
    private RadioButton radioButtonSail;
    private Button buttonFlagshipPrimaryStatInc;
    private Button buttonFlagshipUpgradeInc;
    private Button buttonFlagshipDamageInc;
    private Button buttonSupportShipPrimaryStatInc;
    private Button buttonSupportShipUpgradeInc;
    private Button buttonSupportShipDamageInc;
    private Button buttonSupportShipUpgradeDiceInc;
    private Button buttonAdvisorBonusInc;
    private Button buttonGarrisonInc;
    private Button buttonDefenseInc;
    private Button buttonFlagshipPrimaryStatDec;
    private Button buttonFlagshipUpgradeDec;
    private Button buttonFlagshipDamageDec;
    private Button buttonSupportShipPrimaryStatDec;
    private Button buttonSupportShipUpgradeDec;
    private Button buttonSupportShipDamageDec;
    private Button buttonSupportShipUpgradeDiceDec;
    private Button buttonAdvisorBonusDec;
    private Button buttonGarrisonDec;
    private Button buttonDefenseDec;
    private Button buttonSelfHeldEnmityInc;
    private Button buttonSelfHeldEnmityDec;
    private Button buttonDefHeldEnmityInc;
    private Button buttonDefHeldEnmityDec;
    private ScrollView scrollView1;

    private TextView textViewTempResults;

    private String stringTemp;
    private int intTemp;

    private int intEndeavourType;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dice_determiner);

        editTextFlagshipPrimaryStat = (EditText) findViewById(R.id.editTextFlagshipPrimaryStat);
        editTextAdvisorBonus = (EditText) findViewById(R.id.editTextAdvisorBonus);
        editTextFlagshipDamage = (EditText) findViewById(R.id.editTextFlagshipDamage);
        editTextSupportShipPrimaryStat = (EditText) findViewById(R.id.editTextSupportShipPrimaryStat);
        editTextSupportShipDamage = (EditText) findViewById(R.id.editTextSupportShipDamage);
        editTextSupportShipUpgrade = (EditText) findViewById(R.id.editTextSupportShipUpgrade);
        editTextFlagshipUpgrade = (EditText) findViewById(R.id.editTextFlagshipUpgrade);
        editTextGarrison = (EditText) findViewById(R.id.editTextGarrison);
        editTextDefense = (EditText) findViewById(R.id.editTextDefense);
        editTextSupportShipUpgradeDice = (EditText) findViewById(R.id.editTextSupportShipDiceBonus);
        editTextSelfHeldEnmity = (EditText) findViewById(R.id.editTextSelfHeldEnmity);
        editTextDefHeldEnmity = (EditText) findViewById(R.id.editTextDefHeldEnmity);

        editTextFlagshipPrimaryStat.setInputType(0);
        editTextAdvisorBonus.setInputType(0);
        editTextFlagshipDamage.setInputType(0);
        editTextSupportShipPrimaryStat.setInputType(0);
        editTextSupportShipDamage.setInputType(0);
        editTextSupportShipUpgrade.setInputType(0);
        editTextFlagshipUpgrade.setInputType(0);
        editTextGarrison.setInputType(0);
        editTextDefense.setInputType(0);
        editTextSupportShipUpgradeDice.setInputType(0);
        editTextSelfHeldEnmity.setInputType(0);
        editTextDefHeldEnmity.setInputType(0);

        scrollView1 = (ScrollView) findViewById(R.id.scrollView1);

        textViewTempResults = (TextView) findViewById(R.id.textViewTempResults);

        switchHomeProvinceAttacker = (Switch) findViewById(R.id.switchHomeProvinceAttacker);
        switchHomeProvinceDefender = (Switch) findViewById(R.id.switchHomeProvinceDefender);
        switchGarrisonExplore = (Switch) findViewById(R.id.switchGarrisonExplore);

        stringTemp = "";
        intTemp = 0;

        intEndeavourType = 1;

        buttonFlagshipPrimaryStatInc = (Button) findViewById(R.id.buttonFlagshipPrimaryStatInc);
        buttonFlagshipUpgradeInc = (Button) findViewById(R.id.buttonFlagshipUpgradeInc);
        buttonFlagshipDamageInc = (Button) findViewById(R.id.buttonFlagshipDamageInc);
        buttonSupportShipPrimaryStatInc = (Button) findViewById(R.id.buttonSupportShipPrimaryStatInc);
        buttonSupportShipUpgradeInc = (Button) findViewById(R.id.buttonSupportShipUpgradeInc);
        buttonSupportShipDamageInc = (Button) findViewById(R.id.buttonSupportShipDamageInc);
        buttonSupportShipUpgradeDiceInc = (Button) findViewById(R.id.buttonSupportShipUpgradeDiceInc);
        buttonAdvisorBonusInc = (Button) findViewById(R.id.buttonAdvisorBonusInc);
        buttonGarrisonInc = (Button) findViewById(R.id.buttonGarrisonInc);
        buttonDefenseInc = (Button) findViewById(R.id.buttonDefenseInc);
        buttonFlagshipPrimaryStatDec = (Button) findViewById(R.id.buttonFlagshipPrimaryStatDec);
        buttonFlagshipUpgradeDec = (Button) findViewById(R.id.buttonFlagshipUpgradeDec);
        buttonFlagshipDamageDec = (Button) findViewById(R.id.buttonFlagshipDamageDec);
        buttonSupportShipPrimaryStatDec = (Button) findViewById(R.id.buttonSupportShipPrimaryStatDec);
        buttonSupportShipUpgradeDec = (Button) findViewById(R.id.buttonSupportShipUpgradeDec);
        buttonSupportShipDamageDec = (Button) findViewById(R.id.buttonSupportShipDamageDec);
        buttonSupportShipUpgradeDiceDec = (Button) findViewById(R.id.buttonSupportShipUpgradeDiceDec);
        buttonAdvisorBonusDec = (Button) findViewById(R.id.buttonAdvisorBonusDec);
        buttonGarrisonDec = (Button) findViewById(R.id.buttonGarrisonDec);
        buttonDefenseDec = (Button) findViewById(R.id.buttonDefenseDec);
        buttonSelfHeldEnmityDec = (Button) findViewById(R.id.buttonSelfHeldEnmityDec);
        buttonSelfHeldEnmityInc = (Button) findViewById(R.id.buttonSelfHeldEnmityInc);
        buttonDefHeldEnmityDec = (Button) findViewById(R.id.buttonDefHeldEnmityDec);
        buttonDefHeldEnmityInc = (Button) findViewById(R.id.buttonDefHeldEnmityInc);

        buttonBack = (Button) findViewById(R.id.buttonBack);
        buttonCalculateDicePool = (Button) findViewById(R.id.buttonCalculateDicePool);

        scrollView1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent event) {
                if (editTextGarrison.hasFocus()) {
                    editTextGarrison.clearFocus();
                }
                if(editTextFlagshipPrimaryStat.hasFocus()) {
                    editTextFlagshipPrimaryStat.clearFocus();
                }
                if(editTextFlagshipUpgrade.hasFocus()) {
                    editTextFlagshipUpgrade.clearFocus();
                }
                if(editTextFlagshipDamage.hasFocus()) {
                    editTextFlagshipDamage.clearFocus();
                }
                if(editTextSupportShipPrimaryStat.hasFocus()) {
                    editTextSupportShipPrimaryStat.clearFocus();
                }
                if(editTextSupportShipUpgrade.hasFocus()) {
                    editTextSupportShipUpgrade.clearFocus();
                }
                if(editTextSupportShipDamage.hasFocus()) {
                    editTextSupportShipDamage.clearFocus();
                }
                if(editTextSupportShipUpgradeDice.hasFocus()) {
                    editTextSupportShipUpgradeDice.clearFocus();
                }
                if(editTextAdvisorBonus.hasFocus()) {
                    editTextAdvisorBonus.clearFocus();
                }
                if(editTextDefense.hasFocus()) {
                    editTextDefense.clearFocus();
                }
                if(editTextDefHeldEnmity.hasFocus()) {
                    editTextDefHeldEnmity.clearFocus();
                }
                if(editTextSelfHeldEnmity.hasFocus()) {
                    editTextSelfHeldEnmity.clearFocus();
                }
                return false;
            }
        }); // end setOnTouchListener

        // ************ Start increment and decrement functions  *******************

        buttonSelfHeldEnmityDec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                stringTemp = editTextSelfHeldEnmity.getText().toString();
                intTemp = Integer.parseInt(stringTemp);
                if (intTemp == 0) {
                    return;
                } else {
                    intTemp--;
                    stringTemp = Integer.toString(intTemp);
                    editTextSelfHeldEnmity.setText(stringTemp);
                }
            } // end onClick
        }); // end new OnClickListener

        buttonSelfHeldEnmityInc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                stringTemp = editTextSelfHeldEnmity.getText().toString();
                intTemp = Integer.parseInt(stringTemp);
                if (intTemp == 100) {
                    return;
                } else {
                    intTemp++;
                    stringTemp = Integer.toString(intTemp);
                    editTextSelfHeldEnmity.setText(stringTemp);
                }
            } // end onClick
        }); // end new OnClickListener

        buttonDefHeldEnmityDec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                stringTemp = editTextDefHeldEnmity.getText().toString();
                intTemp = Integer.parseInt(stringTemp);
                if (intTemp == 0) {
                    return;
                } else {
                    intTemp--;
                    stringTemp = Integer.toString(intTemp);
                    editTextDefHeldEnmity.setText(stringTemp);
                }
            } // end onClick
        }); // end new OnClickListener

        buttonDefHeldEnmityInc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                stringTemp = editTextDefHeldEnmity.getText().toString();
                intTemp = Integer.parseInt(stringTemp);
                if (intTemp == 100) {
                    return;
                } else {
                    intTemp++;
                    stringTemp = Integer.toString(intTemp);
                    editTextDefHeldEnmity.setText(stringTemp);
                }
            } // end onClick
        }); // end new OnClickListener

        buttonDefenseDec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                stringTemp = editTextDefense.getText().toString();
                intTemp = Integer.parseInt(stringTemp);
                if (intTemp == 0) {
                    return;
                } else {
                    intTemp--;
                    stringTemp = Integer.toString(intTemp);
                    editTextDefense.setText(stringTemp);
                }
            } // end onClick
        }); // end new OnClickListener

        buttonDefenseInc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                stringTemp = editTextDefense.getText().toString();
                intTemp = Integer.parseInt(stringTemp);
                if (intTemp == 100) {
                    return;
                } else {
                    intTemp++;
                    stringTemp = Integer.toString(intTemp);
                    editTextDefense.setText(stringTemp);
                }
            } // end onClick
        }); // end new OnClickListener

        buttonGarrisonDec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                stringTemp = editTextGarrison.getText().toString();
                intTemp = Integer.parseInt(stringTemp);
                if (intTemp == 0) {
                    return;
                } else {
                    intTemp--;
                    stringTemp = Integer.toString(intTemp);
                    editTextGarrison.setText(stringTemp);
                }
            } // end onClick
        }); // end new OnClickListener

        buttonGarrisonInc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                stringTemp = editTextGarrison.getText().toString();
                intTemp = Integer.parseInt(stringTemp);
                if (intTemp == 100) {
                    return;
                } else {
                    intTemp++;
                    stringTemp = Integer.toString(intTemp);
                    editTextGarrison.setText(stringTemp);
                }
            } // end onClick
        }); // end new OnClickListener

        buttonAdvisorBonusDec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                stringTemp = editTextAdvisorBonus.getText().toString();
                intTemp = Integer.parseInt(stringTemp);
                if (intTemp == 0) {
                    return;
                } else {
                    intTemp--;
                    stringTemp = Integer.toString(intTemp);
                    editTextAdvisorBonus.setText(stringTemp);
                }
            } // end onClick
        }); // end new OnClickListener

        buttonAdvisorBonusInc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                stringTemp = editTextAdvisorBonus.getText().toString();
                intTemp = Integer.parseInt(stringTemp);
                if (intTemp == 100) {
                    return;
                } else {
                    intTemp++;
                    stringTemp = Integer.toString(intTemp);
                    editTextAdvisorBonus.setText(stringTemp);
                }
            } // end onClick
        }); // end new OnClickListener

        buttonSupportShipUpgradeDiceDec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                stringTemp = editTextSupportShipUpgradeDice.getText().toString();
                intTemp = Integer.parseInt(stringTemp);
                if (intTemp == 0) {
                    return;
                } else {
                    intTemp--;
                    stringTemp = Integer.toString(intTemp);
                    editTextSupportShipUpgradeDice.setText(stringTemp);
                }
            } // end onClick
        }); // end new OnClickListener

        buttonSupportShipUpgradeDiceInc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                stringTemp = editTextSupportShipUpgradeDice.getText().toString();
                intTemp = Integer.parseInt(stringTemp);
                if (intTemp == 100) {
                    return;
                } else {
                    intTemp++;
                    stringTemp = Integer.toString(intTemp);
                    editTextSupportShipUpgradeDice.setText(stringTemp);
                }
            } // end onClick
        }); // end new OnClickListener

        buttonSupportShipDamageDec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                stringTemp = editTextSupportShipDamage.getText().toString();
                intTemp = Integer.parseInt(stringTemp);
                if (intTemp == 0) {
                    return;
                } else {
                    intTemp--;
                    stringTemp = Integer.toString(intTemp);
                    editTextSupportShipDamage.setText(stringTemp);
                }
            } // end onClick
        }); // end new OnClickListener

        buttonSupportShipDamageInc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                stringTemp = editTextSupportShipDamage.getText().toString();
                intTemp = Integer.parseInt(stringTemp);
                if (intTemp == 100) {
                    return;
                } else {
                    intTemp++;
                    stringTemp = Integer.toString(intTemp);
                    editTextSupportShipDamage.setText(stringTemp);
                }
            } // end onClick
        }); // end new OnClickListener

        buttonSupportShipUpgradeDec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                stringTemp = editTextSupportShipUpgrade.getText().toString();
                intTemp = Integer.parseInt(stringTemp);
                if (intTemp == 0) {
                    return;
                } else {
                    intTemp--;
                    stringTemp = Integer.toString(intTemp);
                    editTextSupportShipUpgrade.setText(stringTemp);
                }
            } // end onClick
        }); // end new OnClickListener

        buttonSupportShipUpgradeInc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                stringTemp = editTextSupportShipUpgrade.getText().toString();
                intTemp = Integer.parseInt(stringTemp);
                if (intTemp == 100) {
                    return;
                } else {
                    intTemp++;
                    stringTemp = Integer.toString(intTemp);
                    editTextSupportShipUpgrade.setText(stringTemp);
                }
            } // end onClick
        }); // end new OnClickListener

        buttonSupportShipPrimaryStatDec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                stringTemp = editTextSupportShipPrimaryStat.getText().toString();
                intTemp = Integer.parseInt(stringTemp);
                if (intTemp == 0) {
                    return;
                } else {
                    intTemp--;
                    stringTemp = Integer.toString(intTemp);
                    editTextSupportShipPrimaryStat.setText(stringTemp);
                }
            } // end onClick
        }); // end new OnClickListener

        buttonSupportShipPrimaryStatInc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                stringTemp = editTextSupportShipPrimaryStat.getText().toString();
                intTemp = Integer.parseInt(stringTemp);
                if (intTemp == 100) {
                    return;
                } else {
                    intTemp++;
                    stringTemp = Integer.toString(intTemp);
                    editTextSupportShipPrimaryStat.setText(stringTemp);
                }
            } // end onClick
        }); // end new OnClickListener

        buttonFlagshipDamageDec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                stringTemp = editTextFlagshipDamage.getText().toString();
                intTemp = Integer.parseInt(stringTemp);
                if (intTemp == 0) {
                    return;
                } else {
                    intTemp--;
                    stringTemp = Integer.toString(intTemp);
                    editTextFlagshipDamage.setText(stringTemp);
                }
            } // end onClick
        }); // end new OnClickListener

        buttonFlagshipDamageInc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                stringTemp = editTextFlagshipDamage.getText().toString();
                intTemp = Integer.parseInt(stringTemp);
                if (intTemp == 100) {
                    return;
                } else {
                    intTemp++;
                    stringTemp = Integer.toString(intTemp);
                    editTextFlagshipDamage.setText(stringTemp);
                }
            } // end onClick
        }); // end new OnClickListener

        buttonFlagshipUpgradeDec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                stringTemp = editTextFlagshipUpgrade.getText().toString();
                intTemp = Integer.parseInt(stringTemp);
                if (intTemp == 0) {
                    return;
                } else {
                    intTemp--;
                    stringTemp = Integer.toString(intTemp);
                    editTextFlagshipUpgrade.setText(stringTemp);
                }
            } // end onClick
        }); // end new OnClickListener

        buttonFlagshipUpgradeInc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                stringTemp = editTextFlagshipUpgrade.getText().toString();
                intTemp = Integer.parseInt(stringTemp);
                if (intTemp == 100) {
                    return;
                } else {
                    intTemp++;
                    stringTemp = Integer.toString(intTemp);
                    editTextFlagshipUpgrade.setText(stringTemp);
                }
            } // end onClick
        }); // end new OnClickListener

        buttonFlagshipPrimaryStatDec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                stringTemp = editTextFlagshipPrimaryStat.getText().toString();
                intTemp = Integer.parseInt(stringTemp);
                if (intTemp == 0) {
                    return;
                } else {
                    intTemp--;
                    stringTemp = Integer.toString(intTemp);
                    editTextFlagshipPrimaryStat.setText(stringTemp);
                }
            } // end onClick
        }); // end new OnClickListener

        buttonFlagshipPrimaryStatInc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                stringTemp = editTextFlagshipPrimaryStat.getText().toString();
                intTemp = Integer.parseInt(stringTemp);
                if (intTemp == 100) {
                    return;
                } else {
                    intTemp++;
                    stringTemp = Integer.toString(intTemp);
                    editTextFlagshipPrimaryStat.setText(stringTemp);
                }
            } // end onClick
        }); // end new OnClickListener

        // ************ End increment and decrement functions  ********************

                buttonBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                finish();
            } // end onClick
        }); // end new onclicklistener for back button

        buttonCalculateDicePool.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                //stringTemp = "";
                int dice[];
                Bundle b = new Bundle();
                Intent intent = new Intent();

                dice = calculateDicePool();
                //stringTemp = "Dice: "+dice[0]+"\nDefense: "+dice[1];
                //textViewTempResults.setText(stringTemp);

                b.putInt("diceCount",dice[0]);
                b.putInt("defense",dice[1]);
                intent.putExtras(b);

                setResult(RESULT_OK,intent);

                finish();
            } // end onClick
        }); // end new onclicklistener for back button
    } // end Oncreate

    public void onRadioButtonEndeavourClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radioButtonExplore:
                if (checked) {
                    intEndeavourType = 1;
                    break;
                }
            case R.id.radioButtonRaid:
                if (checked) {
                    intEndeavourType = 2;
                    break;
                }
            case R.id.radioButtonSail:
                if (checked) {
                    intEndeavourType = 3;
                    break;
                }
        }
    }

    public int[] calculateDicePool () {
        int dice[];
        int iFlagshipPrimaryStat;
        int iFlagshipUpgrade;
        int iFlagshipDamage;
        int iSupportShipPrimaryStat;
        int iSupportShipUpgrade;
        int iSupportShipDamage;
        int iAdvisorBonus;
        int iGarrison;
        int iDefense;
        int iSupportShipUpgradeDice;
        int iSelfHeldEnmity;
        int iDefHeldEnmity;

        boolean bGarrisonExplore;
        boolean bHomeProvinceDefender;
        boolean bHomeProvinceAttacker;

        dice = new int[2];
        dice[0] = 0; // Main dice
        dice[1] = 0; // Defense

        iFlagshipPrimaryStat = (int) Integer.parseInt(editTextFlagshipPrimaryStat.getText().toString());
        iFlagshipUpgrade = (int) Integer.parseInt(editTextFlagshipUpgrade.getText().toString());
        iFlagshipDamage = (int) Integer.parseInt(editTextFlagshipDamage.getText().toString());
        iSupportShipPrimaryStat = (int) Integer.parseInt(editTextSupportShipPrimaryStat.getText().toString());
        iSupportShipUpgrade = (int) Integer.parseInt(editTextSupportShipUpgrade.getText().toString());
        iSupportShipDamage = (int) Integer.parseInt(editTextSupportShipDamage.getText().toString());
        iAdvisorBonus = (int) Integer.parseInt(editTextAdvisorBonus.getText().toString());
        iGarrison = (int) Integer.parseInt(editTextGarrison.getText().toString());
        iDefense = (int) Integer.parseInt(editTextDefense.getText().toString());
        iSupportShipUpgradeDice = (int) Integer.parseInt(editTextSupportShipUpgradeDice.getText().toString());
        iSelfHeldEnmity = (int) Integer.parseInt(editTextSelfHeldEnmity.getText().toString());
        iDefHeldEnmity = (int) Integer.parseInt(editTextDefHeldEnmity.getText().toString());

        bHomeProvinceAttacker = (boolean) switchHomeProvinceAttacker.isChecked();
        bHomeProvinceDefender = (boolean) switchHomeProvinceDefender.isChecked();
        bGarrisonExplore = (boolean) switchGarrisonExplore.isChecked();

        dice[1] = iDefense;
        dice[0] += iFlagshipPrimaryStat + iFlagshipUpgrade - iFlagshipDamage + iAdvisorBonus; // The basics

        if((iSupportShipPrimaryStat + iSupportShipUpgrade - iSupportShipDamage) > 0) {
            dice[0] += 1 + iSupportShipUpgradeDice; // 1 die for being a valid support ship, additional for having Nimble, etc

        } // end Support Ship Check

        // Explore 1, Raid 2, Sail 3
        if((intEndeavourType == 2) || (bGarrisonExplore && (intEndeavourType == 1))) {
            dice[0] -= iGarrison; // Add logic for explore - don't subtract
        } // end if endeavour is raid or garrison applies to explore

        if(intEndeavourType==2) {
            if(bHomeProvinceAttacker) {
                dice[0]++;
            }
            if(bHomeProvinceDefender) {
                dice[0]--;
            }
            dice[0] += iSelfHeldEnmity;
            dice[0] -= iDefHeldEnmity;
        } // end if endeavour is raid

        if(dice[0] < 0) {
            dice[0] = 0;
        } // end check for less than 0


        return dice;
    } // end calculateDicePool
} // end class definition
