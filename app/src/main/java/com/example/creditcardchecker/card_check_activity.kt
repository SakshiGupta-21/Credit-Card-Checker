package com.example.creditcardchecker

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity


class card_check_activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_check_activity)
    }

    fun submit(view: View) {

        visibleOK()
        var cnt=0 // if this cnt is 5 then payment is successful


        //Check for card number validty
        val edtext_cardno = findViewById<View>(R.id.edtext_cardno) as EditText
        val cardnum = edtext_cardno.text.toString()
        var checkCardNo:Boolean
        if(cardnum.length==15||cardnum.length==16){

            checkCardNo= luhncheck(cardnum)

            if(!checkCardNo){
            var invalid = findViewById<View>(R.id.invalid_cardno) as TextView
            invalid.visibility= TextView.VISIBLE
            }
            else
                cnt++
        }
        else{
            var invalid = findViewById<View>(R.id.invalid_cardno) as TextView
            invalid.visibility= TextView.VISIBLE
        }

        //Check for date validty
        val edtext_date = findViewById<View>(R.id.edtext_date) as EditText
        val value2 = edtext_date.text.toString()


        if(value2.length==5&&value2.substring(0, 2).toInt()<=12&&value2.substring(0, 2).toInt()>=1&&value2[2]=='/'){
            cnt++;
        }
        else{
            var invalid = findViewById<View>(R.id.invalid_date) as TextView
            invalid.visibility= TextView.VISIBLE
        }


        //Check for CVV validty
        val edtext_cvv = findViewById<View>(R.id.edtext_cvv) as EditText
        val value3 = edtext_cvv.text.toString()
        if((cardnum.length==15&&value3.length==4)||(cardnum.length==16&&value3.length==3)){
            cnt++;
        }
        else{
            var invalid = findViewById<View>(R.id.invalid_cvv) as TextView
            invalid.visibility= TextView.VISIBLE
        }

        //Check for First Name validty
        val edtext_fname = findViewById<View>(R.id.edtext_fname) as EditText
        val value4 = edtext_fname.text.toString()

        if(checkNameValidity(value4)){
            cnt++
        }
        else{
            var invalid = findViewById<View>(R.id.invalid_fname) as TextView
            invalid.visibility= TextView.VISIBLE
        }

        //Check for Last Name validty
        val edtext_lname = findViewById<View>(R.id.edtext_lname) as EditText
        val value5 = edtext_lname.text.toString()
        if(checkNameValidity(value5)){
            cnt++
        }
        else{
            var invalid = findViewById<View>(R.id.invalid_lname) as TextView
            invalid.visibility= TextView.VISIBLE
        }

        if(cnt==5){
            openAlertDialogue()
        }


    }

    // function to check name validity
    private fun checkNameValidity(value4: String): Boolean {
    for(i in value4){
        if((i.toInt()>=65&&i.toInt()<=90)||(i.toInt()>=97&&i.toInt()<=122)||(i.toInt()==32)){

        }
        else
        return false
    }
       return true
    }

    // function to hide invalid texts
    private fun visibleOK() {
        //Toast.makeText(this,"visibleOk called",Toast.LENGTH_LONG).show()
        var  vis = findViewById<View>(R.id.invalid_cardno) as TextView
        vis.visibility= TextView.GONE
         vis = findViewById<View>(R.id.invalid_date) as TextView
        vis.visibility= TextView.GONE
        vis = findViewById<View>(R.id.invalid_cvv) as TextView
        vis.visibility= TextView.GONE
        vis = findViewById<View>(R.id.invalid_fname) as TextView
        vis.visibility= TextView.GONE
        vis = findViewById<View>(R.id.invalid_lname) as TextView
        vis.visibility= TextView.GONE
    }
    // function to open Alert Dialogue
    private fun openAlertDialogue() {
        //alertdialogue

        var builder: AlertDialog.Builder
        builder = AlertDialog.Builder(this)
        builder.setMessage(getString(R.string.em))
                .setCancelable(false)
                .setPositiveButton(getString(R.string.ok)) { dialog, id ->
                    finish()

                }
        val alert = builder.create()
        alert.setTitle(R.string.Payment_Successful)
        alert.show()
        //finish
    }

// function to valid Credit card number according to Luhn's Algorithm
    private fun luhncheck(s: String): Boolean {
        var sum=0
        for(i in s.length-2 downTo 0 step 2){
            var x= s[i].toInt()-48
            x=2*x
            if(x>9)
                sum+=x-9
            else
                sum+=x
        }

        for(i in s.length-1 downTo 0 step 2)
                sum+=s[i].toInt()-48
        return sum%10==0
    }
}
