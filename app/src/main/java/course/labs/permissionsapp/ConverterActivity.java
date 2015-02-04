package course.labs.permissionsapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.view.inputmethod.InputMethodManager;

public class ConverterActivity extends Activity implements OnClickListener{

    private static final String TAG = "Lab-Converter";
    private EditText textTemperature;
    private char temperatureChosen = 'F'; // Default temperature to convert is Fahrenheit
    private TextView textResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.converter_activity);

        RadioButton fahrenheitRadio = (RadioButton) findViewById(R.id.radioFahrenheit);
        RadioButton celsiusRadio = (RadioButton) findViewById(R.id.radioCelsius);

        Button convertButton = (Button) findViewById(R.id.convert_button);
        Button clearButton = (Button) findViewById(R.id.clear_button);

        // set onClickListener to all the radio buttons and normal buttons
        fahrenheitRadio.setOnClickListener(this);
        celsiusRadio.setOnClickListener(this);
        convertButton.setOnClickListener(this);
        clearButton.setOnClickListener(this);
    }

    // TODO - Implement the temperature conversion logic and other behavior here
    @Override
    public void onClick(View view) {
        textTemperature = (EditText) findViewById(R.id.editTextTemperature);
        textResult = (TextView) findViewById(R.id.textView);

        RadioButton fahrenheitRadio = (RadioButton) findViewById(R.id.radioFahrenheit);

        final String outputMessage;

        switch (view.getId()) {
            case R.id.radioFahrenheit:
                Log.i(TAG, "Fahrenheit radio button clicked");
                temperatureChosen = 'F';
                break;

            case R.id.radioCelsius:
                Log.i(TAG, "Celsius radio button clicked");
                temperatureChosen = 'C';
                break;

            case R.id.convert_button:
                Log.i(TAG, "Convert button clicked");

                // display error message if there is no user value input
                if( textTemperature.getText().toString().length() == 0 ) {
                    textTemperature.setError("At least a single digit is required!");
                }
                // otherwise perform the temperature conversion
                else {
                    float inputFloatValue = Float.parseFloat(textTemperature.getText().toString());
                    float outputFloatValue = 0;

                    // if temperature chosen on radiobox is Fahrenheit
                    if (temperatureChosen == 'F') {
                        outputFloatValue = convertCelsiusToFahrenheit(inputFloatValue);
                        outputMessage = textTemperature.getText() + " Celsius is " + String
                                .valueOf(outputFloatValue) + " Fahrenheit";
                    }
                    // else temperature chosen on radiobox is Celsius
                    else {
                        outputFloatValue = convertFahrenheitToCelsius(inputFloatValue);
                        outputMessage = textTemperature.getText() + " Fahrenheit is " + String
                                .valueOf(outputFloatValue) + " Celsius";
                    }

                    // finally display the result message on screen
                    textResult.setText(outputMessage);
                }

                break;

            // clear all editText and textView
            case R.id.clear_button:
                Log.i(TAG, "Clear button clicked");
                //clear the editText error validation message if any
                textTemperature.setError(null);

                textTemperature.getText().clear();
                textResult.setText(null);

                //default temperate to Fahrenheit
                fahrenheitRadio.setChecked(true);
                temperatureChosen = 'F';

                break;

            // this will not occur unless there is a missing case statement handling the button
            default:
                Log.e(TAG, "Unknown button is clicked.");
                break;
        }

        // dismiss the keyboard whenever there's a button click
        hideKeybord(view);
    }

    // converts to celsius
    public static float convertFahrenheitToCelsius(float fahrenheit) {
        return ((fahrenheit - 32) * 5 / 9);
    }

    // converts to fahrenheit
    public static float convertCelsiusToFahrenheit(float celsius) {
        return ((celsius * 9) / 5) + 32;
    }

    // dismiss keyboard
    public void hideKeybord(View view) {
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(getApplicationContext().INPUT_METHOD_SERVICE);

        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
    }
}
