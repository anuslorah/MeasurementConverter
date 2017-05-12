package slorah.com.measurementconverter;

import android.os.Bundle;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;


public class MainActivity extends Activity implements TextView.OnEditorActionListener, AdapterView.OnItemSelectedListener{
    //variables for the widgets

    private EditText from;
    private TextView out;
    private SpinnerAdapter spinner1;

    //define SharedPreferences object
    private SharedPreferences savedValues;

    //array index for spinner
    private int convertSelection =0;

    private float milesNum = 0;
    private float kmNum = 0;
    private float inchesNum = 0;
    private float cmNum = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //spinner element
        Spinner spinner = (Spinner) findViewById(R.id.spinner1);

        //spinner click listener
        spinner.setOnItemSelectedListener(this);

//        //spinner dropdown elements
//        List<String> conversions = new ArrayList<String>();
//        conversions.add("Miles to Kilometers");
//        conversions.add("Kilometers to Miles");
//        conversions.add("Inches to Centimeters");
//        conversions.add("Centimeters to Inches");

        //creating adapter for spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.convert_array, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        //get references to the widgets
        from = (EditText) findViewById(R.id.fromEditText);
        out = (TextView) findViewById(R.id.outputTextView);

        //set the listeners
        from.setOnEditorActionListener(this);

        //get SharedPreferences object
        savedValues = getSharedPreferences("SavedValues", MODE_PRIVATE);
    }
    @Override
    public void onPause() {

        float numToConvert = Float.parseFloat(from.getText().toString());

        //save the instance variables
        Editor editor = savedValues.edit();

        editor.commit();

        super.onPause();
    }



    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_DONE || actionId ==EditorInfo.IME_ACTION_UNSPECIFIED) {
            convertMeasurement();
        }
        // hide soft keyboard
        return false;
    }

    private void convertMeasurement(){
        NumberFormat number = NumberFormat.getNumberInstance();

        switch(convertSelection) {
            case 0:
                float m2k = Float.parseFloat(from.getText().toString()) * 0.6214f;
                out.setText(number.format(m2k));
                break;
            case 1:
                float k2m = Float.parseFloat(from.getText().toString()) * 1.6093f;
                out.setText(number.format(k2m));
                break;
            case 2:
                float i2c = Float.parseFloat(from.getText().toString()) * 2.54f;
                out.setText(number.format(i2c));
                break;
            case 3:
                float c2i = Float.parseFloat(from.getText().toString()) * .3937f;
                out.setText(number.format(c2i));
                break;



        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
/*
package slorah.com.celcius;

import android.os.Bundle;
import java.text.DecimalFormat;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class ConvertToCelcius  extends Activity implements TextView.OnEditorActionListener{
    //variables for the widgets
    private EditText fahrenheitvalue;
    private TextView celciusvalue;

    //define SharedPreferences object
    private SharedPreferences savedValues;

    private String fahrenheitString = "";
    private float celciusdegrees;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convert_to_celcius);

        //get references to the widgets
        fahrenheitvalue = (EditText) findViewById(R.id.fahrenheitDegree);
        celciusvalue = (TextView) findViewById(R.id.celciusDegree);

        //set the listeners
        fahrenheitvalue.setOnEditorActionListener(this);

        //get SharedPreferences object
        savedValues = getSharedPreferences("SavedValues", MODE_PRIVATE);
    }

    @Override
    public void onPause() {

        //save the instance variables
        Editor editor = savedValues.edit();
        editor.putString("fahrenheitString", fahrenheitvalue.getText().toString());
        editor.putFloat("celsiusdegrees", celciusdegrees);

        editor.commit();

        super.onPause();
    }

    @Override
    public void onResume(){

        super.onResume();

        //get the instance variables
        fahrenheitvalue.setText(savedValues.getString("fahrenheitString", "0"));
        celciusdegrees = savedValues.getFloat("celciusdegrees", celciusdegrees);

        celciusvalue.setText(fahrenheitString);

        //convert and display temperature
        convertTemperature();
    }

    public void convertTemperature() {

        //get fahrehheit value
        fahrenheitString = fahrenheitvalue.getText().toString();
        float fahrenheit = Float.parseFloat(fahrenheitString);

        //calculate celcius value
        celciusdegrees =  ((fahrenheit-32) * 5f/9f);

        //display data on the layout
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        celciusvalue.setText(df.format(celciusdegrees));
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_DONE || actionId ==EditorInfo.IME_ACTION_UNSPECIFIED) {
            convertTemperature();
        }
        // hide soft keyboard
        return false;
    }
}

 */