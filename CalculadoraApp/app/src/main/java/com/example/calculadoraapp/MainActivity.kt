package com.example.calculadoraapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    private var canAddOperation = false
    private var canAddDecimal = true
    private lateinit var workingTV: TextView
    private lateinit var resultTV: TextView
    private lateinit var imagen: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        workingTV = findViewById(R.id.workingsTV)
        resultTV = findViewById(R.id.resultsTV)
        imagen=findViewById(R.id.backgroundImageView)
    }

    fun numberAction(view: View)
    {
        imagen.visibility=View.INVISIBLE
        if(view is Button)
        {
            if(view.text == ".")
            {
                if(canAddDecimal)
                    workingTV.append(view.text)

                canAddDecimal = false
            }
            else
                workingTV.append(view.text)

            canAddOperation = true
        }
    }

    fun operationAction(view: View)
    {
        imagen.visibility=View.INVISIBLE
        if(view is Button && canAddOperation)
        {
            workingTV.append(view.text)
            canAddOperation = false
            canAddDecimal = true
        }
    }

    fun allClearAction(view: View)
    {
        imagen.visibility=View.INVISIBLE
        workingTV.text = ""
        resultTV.text = ""
    }
    fun noTocar(view: View)
    {
        imagen.visibility=View.VISIBLE
    }
    fun backSpaceAction(view: View)
    {
        imagen.visibility=View.INVISIBLE
        val length = workingTV.length()
        if(length > 0)
            workingTV.text = workingTV.text.subSequence(0, length - 1)
    }

    fun equalsAction(view: View)
    {
        imagen.visibility=View.INVISIBLE
        resultTV.text = calculateResults()
    }

    private fun calculateResults(): String
    {
        imagen.visibility=View.INVISIBLE
        val digitsOperators = digitsOperators()
        if(digitsOperators.isEmpty()) return ""

        val timesDivision = timesDivisionCalculate(digitsOperators)
        if(timesDivision.isEmpty()) return ""

        val result = addSubtractCalculate(timesDivision)
        return result.toString()
    }

    private fun addSubtractCalculate(passedList: MutableList<Any>): Float
    {
        imagen.visibility=View.INVISIBLE
        var result = passedList[0] as Float

        for(i in passedList.indices)
        {
            if(passedList[i] is Char && i != passedList.lastIndex)
            {
                val operator = passedList[i]
                val nextDigit = passedList[i + 1] as Float
                if (operator == '+')
                    result += nextDigit
                if (operator == '-')
                    result -= nextDigit
            }
        }

        return result
    }

    private fun timesDivisionCalculate(passedList: MutableList<Any>): MutableList<Any>
    {
        imagen.visibility=View.INVISIBLE
        var list = passedList
        while (list.contains('x') || list.contains('/'))
        {
            list = calcTimesDiv(list)
        }
        return list
    }

    private fun calcTimesDiv(passedList: MutableList<Any>): MutableList<Any>
    {
        imagen.visibility=View.INVISIBLE
        val newList = mutableListOf<Any>()
        var restartIndex = passedList.size

        for(i in passedList.indices)
        {
            if(passedList[i] is Char && i != passedList.lastIndex && i < restartIndex)
            {
                val operator = passedList[i]
                val prevDigit = passedList[i - 1] as Float
                val nextDigit = passedList[i + 1] as Float
                when(operator)
                {
                    'x' ->
                    {
                        newList.add(prevDigit * nextDigit)
                        restartIndex = i + 1
                    }
                    '/' ->
                    {
                        newList.add(prevDigit / nextDigit)
                        restartIndex = i + 1
                    }
                    else ->
                    {
                        newList.add(prevDigit)
                        newList.add(operator)
                    }
                }
            }

            if(i > restartIndex)
                newList.add(passedList[i])
        }

        return newList
    }

    private fun digitsOperators(): MutableList<Any>
    {
        imagen.visibility=View.INVISIBLE
        val list = mutableListOf<Any>()
        var currentDigit = ""
        for(character in workingTV.text)
        {
            if(character.isDigit() || character == '.')
                currentDigit += character
            else
            {
                list.add(currentDigit.toFloat())
                currentDigit = ""
                list.add(character)
            }
        }

        if(currentDigit != "")
            list.add(currentDigit.toFloat())

        return list
    }
}