package com.android.dango.day2ejcolores;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class Calculator extends ActionBarActivity {
    int input = 0;
    int temp = 0;
    //idOP   +:0   -:1   x:2  /:3   None:4
    int opID = 0;
    boolean negative = false;
    boolean first = true;
    boolean zeroIN = false;
    TextView resultShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        resultShow = (TextView) findViewById(R.id.resultView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_calculator, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void addNumber(View v){
        if(input < 100000000) {
            switch (v.getId()) {
                case R.id.key0:
                    input *= 10;
                    break;
                case R.id.key1:
                    if (!negative) {
                        input *= 10;
                        input += 1;
                    } else {
                        input *= 10;
                        input -= 1;
                    }
                    break;
                case R.id.key2:
                    if (!negative) {
                        input *= 10;
                        input += 2;
                    } else {
                        input *= 10;
                        input -= 2;
                    }
                    break;
                case R.id.pitton:
                    //Toast pitoast = Toast.makeText(Calculator.this, "Crimes against science", Toast.LENGTH_SHORT);
                    //LinearLayout linearLayout = (LinearLayout) pitoast.getView();
                    //TextView messageTextView = (TextView) linearLayout.getChildAt(0);
                    //messageTextView.setTextSize(25);
                    //pitoast.setGravity(Gravity.TOP | Gravity.CENTER, 0, 75);
                    //pitoast.show();
                    View.OnClickListener yesOnClickListener = new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(Calculator.this, R.string.pi_wrongChoice, Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    };

                    Snackbar.make(v, R.string.snackbar_text, Snackbar.LENGTH_LONG)
                            .setAction(R.string.snackbar_actionY, yesOnClickListener)
                            .show();
                /*Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:934137660"));
                startActivity(intent);
                break;*/
                case R.id.key3:
                    if (!negative) {
                        input *= 10;
                        input += 3;
                    } else {
                        input *= 10;
                        input -= 3;
                    }
                    break;
                case R.id.key4:
                    if (!negative) {
                        input *= 10;
                        input += 4;
                    } else {
                        input *= 10;
                        input -= 4;
                    }
                    break;
                case R.id.key5:
                    if (!negative) {
                        input *= 10;
                        input += 5;
                    } else {
                        input *= 10;
                        input -= 5;
                    }
                    break;
                case R.id.key6:
                    if (!negative) {
                        input *= 10;
                        input += 6;
                    } else {
                        input *= 10;
                        input -= 6;
                    }
                    break;
                case R.id.key7:
                    if (!negative) {
                        input *= 10;
                        input += 7;
                    } else {
                        input *= 10;
                        input -= 7;
                    }
                    break;
                case R.id.key8:
                    if (!negative) {
                        input *= 10;
                        input += 8;
                    } else {
                        input *= 10;
                        input -= 8;
                    }
                    break;
                case R.id.key9:
                    if (!negative) {
                        input *= 10;
                        input += 9;
                    } else {
                        input *= 10;
                        input -= 9;
                    }
                    break;
                default:
                    break;
            }
            resultShow.setText(String.valueOf(input));
            if (input == 0)
                zeroIN = true;
            else
                zeroIN = false;
        }

    }
    public int operate(int a, int b, int iD){
        switch(iD) {
            case 0: // sum
                a += b;
                break;
            case 1: // substract
                a -= b;
                break;
            case 2: // multiply
                a *= b;
                break;
            case 3: // divide
                if(b != 0)
                    a /= b;
                else
                    Toast.makeText(Calculator.this, "ERROR: DIV/0", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return a;
    }

    public void addOperator(View v){
        negative = false;
        switch(v.getId()){
            case R.id.keyPlus:
                if(zeroIN || input != 0) {
                    temp = operate(temp, input, opID);
                }
                input = 0;
                opID = 0;
                first = false;
                break;
            case R.id.keyMinus:
                if(zeroIN || input != 0) {
                    temp = operate(temp, input, opID);
                }
                input = 0;
                opID = 1;
                first = false;
                break;
            case R.id.keyTimes:
                if(zeroIN || input != 0) {
                    temp = operate(temp, input, opID);
                }
                input = 0;
                opID = 2;
                first = false;
                break;
            case R.id.keyDiv:
                if(zeroIN || input != 0) {
                    temp = operate(temp, input, opID);
                }
                input = 0;
                opID = 3;
                first = false;
                break;
            case R.id.keyEqual:
                temp = operate(temp, input, opID);
                input = 0;
                opID = 0;
                first = false;
                break;
            case R.id.keyClear:
                temp = 0;
                input = 0;
                first = true;
                negative = false;
                opID = 0;
                break;
            default:
                break;
        }
        if(temp < 1000000000)
            resultShow.setText(String.valueOf(temp));
        else
            resultShow.setText("MATH: Number out of bounds");
    }

    @Override
    public void onSaveInstanceState(Bundle outSave){
        super.onSaveInstanceState(outSave);
        outSave.putString("result", resultShow.getText().toString());
        outSave.putInt("input",input);
        outSave.putInt("temp",temp);
        outSave.putInt("opID", opID);
        outSave.putBoolean("negative", negative);
        outSave.putBoolean("first", first);
    }
    @Override
    public void onRestoreInstanceState(Bundle inRestore){
        super.onRestoreInstanceState(inRestore);
        resultShow.setText(inRestore.getString("result"));
        input = inRestore.getInt("input");
        temp = inRestore.getInt("temp");
        opID = inRestore.getInt("opID");
        negative = inRestore.getBoolean("negative");
        first = inRestore.getBoolean("first");
    }
}
