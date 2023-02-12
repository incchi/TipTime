package com.example.tiptime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding   //initialize the variable before using it

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)  //view binding
        setContentView(binding.root)

        binding.calculateButton.setOnClickListener { calculateTip() }

    }
    private fun calculateTip() {
        //val stringInTextField = binding.costOfService.text
        /*
        toDouble() NEEDS to be call on a string
         and it turns out text attribute of EditText is editable
         means it represents a text and that can be changed
         and we can convert an Editable to a string
         so we use .toString()
         */
        val stringInTextField = binding.costOfService.text.toString()
        val cost = stringInTextField.toDouble()

        //to get tip percentage

        val tipPercentage = when (binding.tipOptions.checkedRadioButtonId) {
            R.id.option_twenty_percent -> 0.20
            R.id.option_eighteen_percent -> 0.18
            else -> 0.15
        }

        var tip = tipPercentage * cost
        if( binding.roundUpSwitch.isChecked) {
            tip = kotlin.math.ceil(tip)
        }
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipResult.text = getString(R.string.tip_amount,formattedTip)
    }


}