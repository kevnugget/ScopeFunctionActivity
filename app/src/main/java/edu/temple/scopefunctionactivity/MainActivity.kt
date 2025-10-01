package edu.temple.scopefunctionactivity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // You can test your helper functions by  calling them from onCreate() and
        // printing their output to the Log, which is visible in the LogCat:
        // eg. Log.d("function output", getTestDataArray().toString())

        val testArray = getTestDataArray()
        Log.d("test array: ", testArray.toString())

        Log.d("averageLessThanMedian", averageLessThanMedian(testArray as List<Double>).toString())

        val root = findViewById<LinearLayout>(R.id.root)
        val data = listOf(1, 2, 3, 4)
        var index = 0
        val v0 = getView(index, null, data, this)
        root.addView(v0)

        findViewById<Button>(R.id.button).setOnClickListener {
            index += 1
            if (index >= data.size) {
                index = 0
            }
            getView(index, v0, data, this)
        }

    }


    /* Convert all the helper functions below to Single-Expression Functions using Scope Functions */
    // eg. private fun getTestDataArray() = ...

    // HINT when constructing elaborate scope functions:
    // Look at the final/return value and build the function "working backwards"

    // Return a list of random, sorted integers
//    private fun getTestDataArray() : List<Int> {
//        val testArray = MutableList(10){ Random.nextInt()}
//        testArray.sort()
//        return testArray
//    }

    private fun getTestDataArray() : List<Int> = MutableList(10){Random.nextInt()}.apply{sort()}

    // Return true if average value in list is greater than median value, false otherwise
//    private fun averageLessThanMedian(listOfNumbers: List<Double>): Boolean {
//        val avg = listOfNumbers.average()
//        val sortedList = listOfNumbers.sorted()
//        val median = if (sortedList.size % 2 == 0)
//            (sortedList[sortedList.size / 2] + sortedList[(sortedList.size - 1) / 2]) / 2
//        else
//            sortedList[sortedList.size / 2]
//
//        return avg < median
//    }

    private fun averageLessThanMedian(listOfNumbers : List<Double>) : Boolean = listOfNumbers.sorted().let {
        l ->
            val median =
                if (l.size % 2 == 0)
                    (l[l.size / 2] + l[(l.size - 1) /2]) / 2
                else
                    l[l.size/2]
        listOfNumbers.average() < median
    }

    // Create a view from an item in a collection, but recycle if possible (similar to an AdapterView's adapter)
//    private fun getView(position: Int, recycledView: View?, collection: List<Int>, context: Context): View {
//        val textView: TextView
//
//        if (recycledView != null) {
//            textView = recycledView as TextView
//        } else {
//            textView = TextView(context)
//            textView.setPadding(5, 10, 10, 0)
//            textView.textSize = 22f
//        }
//
//        textView.text = collection[position].toString()
//
//        return textView
//    }

    private fun getView(position: Int, recycledView: View?, collection: List<Int>, context: Context) : View =
        (recycledView as? TextView ?: TextView(context).apply {
            setPadding(5, 10, 10, 0)
            textSize = 22F
        }).also {
            it.text = collection[position].toString()
        }

}