package es.uji.jvilar.temperatureconverter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    lateinit var celsiusEditText: EditText
    lateinit var fahrenheitEditText: EditText
    var changing = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        celsiusEditText = findViewById(R.id.celsiusEditText)
        celsiusEditText.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(p0: Editable?) {
                if (!changing)
                    onCelsiusChanged(p0.toString())
            }

        })

        fahrenheitEditText = findViewById(R.id.fahrenheitEditText)
        fahrenheitEditText.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(p0: Editable?) {
                if (!changing)
                    onFahrenheitChanged(p0.toString())
            }

        })

    }

    private fun changeText(editText: EditText, str: String) {
        changing = true
        editText.setText(str)
        changing = false
    }

    private fun onFahrenheitChanged(str: String) {
        val fahrenheit = str.toDoubleOrNull()
        if (fahrenheit == null) {
            changeText(celsiusEditText, "")
            return
        }
        val celsius = (fahrenheit - 32) / 180 * 100
        changeText(celsiusEditText, celsius.toString())
    }

    private fun onCelsiusChanged(str: String) {
        val celsius = str.toDoubleOrNull()
        if (celsius == null) {
            changeText(fahrenheitEditText, "")
            return
        }
        val fahrenheit = 32 + celsius / 100 * 180
        changeText(fahrenheitEditText, fahrenheit.toString())
    }
}