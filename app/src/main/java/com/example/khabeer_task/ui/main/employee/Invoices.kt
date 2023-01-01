package com.example.khabeer_task.ui.main.employee
import android.util.Log
import java.math.BigDecimal;
import java.util.Date;
class Invoices {

    private  val TAG = "Invoices"
    fun getInvoices(): Array<InvoiceData?>? {
        val data = arrayOfNulls<InvoiceData>(40)
        for (i in 0..39) {
            val row = InvoiceData()
            row.id = i + 1
            row.invoiceNumber = row.id
            row.amountDue = BigDecimal.valueOf(20.00 * i)
            row.invoiceAmount = BigDecimal.valueOf(120.00 * (i + 1))
            row.invoiceDate = Date()
            row.customerName = "Yuvan Shankar Raja"
            row.customerAddress = "1112, Chennai, India"
            data[i] = row
            Log.d(TAG, "getInvoices: ")
        }
        return data
    }
}