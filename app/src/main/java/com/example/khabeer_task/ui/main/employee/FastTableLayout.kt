package com.example.khabeer_task.ui.main.employee

import com.example.khabeer_task.util.BaseApplication

import android.content.Context
import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.setPadding
import com.example.khabeer_task.R


class FastTableLayout(private val context: Context,
                      private val tableLayout: TableLayout,
                      private val headers: Array<String>,
                      private val data: Array<Array<String>>) {

    var SET_DEAFULT_HEADER_BORDER = false

    var SET_DEFAULT_HEADER_BACKGROUND = true

    private var SET_CUSTOM_HEADER_BORDER = false
    private var CUSTOM_DRAWABLE_HEADER_BORDER = -1

    private var SET_CUSTOM_HEADER_BACKGROUND = true
    private var CUSTOM_HEADER_BACKGROUND_COLOR = -1

    var TABLE_TEXT_SIZE = 14f

    fun build(){
        //add headers
        val tableRow0 = TableRow(context)

        //verify if we need a default header background
        if(SET_DEFAULT_HEADER_BACKGROUND){
            tableRow0.setBackgroundResource(R.color.gray)
        }
        //verify if we need a custom header background
        if(SET_CUSTOM_HEADER_BACKGROUND){
            tableRow0.setBackgroundResource(R.color.purple_700)
        }
        headers.forEach {
            val textView = TextView(context)
            textView.text=" ${it} "
            textView.setTextColor(Color.WHITE)
            //verify if set the default border
            if(SET_DEAFULT_HEADER_BORDER){
                textView.setBackgroundResource(R.drawable.header_border_tablelayout)
            }
            //verify if set a custom border
            if(SET_CUSTOM_HEADER_BORDER){
                textView.setBackgroundResource(CUSTOM_DRAWABLE_HEADER_BORDER)
            }
            textView.textSize = TABLE_TEXT_SIZE
            tableRow0.addView(textView)
        }
        //Add headers to the tableLayout widget
        tableLayout.addView(tableRow0)

        //Add data.
        for (i in 0 until data.size) {
            val tblRow = TableRow(context)
            tblRow.setOnClickListener {
                //Add Background
                tblRow.gravity = Gravity.CENTER
                it.setBackgroundResource(R.color.gray)
            }
            tblRow.setOnLongClickListener{
                //Remove background
                it.setBackgroundResource(0)
                return@setOnLongClickListener true
            }

            for (j in 0 until data[i].size) {
                val textView = TextView(context)
                textView.gravity = Gravity.CENTER
                textView.textSize = TABLE_TEXT_SIZE

                textView.text=" ${ data[i][j]} "

                tblRow.addView(textView)
            }
            tableLayout.addView(tblRow)
        }

    }

    fun setCustomBorderToHeaders(drawable : Int){
        SET_CUSTOM_HEADER_BORDER = true
        CUSTOM_DRAWABLE_HEADER_BORDER = drawable
    }

    fun setCustomBackgroundToHeaders(color: Int){
        SET_CUSTOM_HEADER_BACKGROUND = true
        CUSTOM_HEADER_BACKGROUND_COLOR = color
    }

}