package kr.co.so.softcapus.chapter3

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.EditText

import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.core.widget.addTextChangedListener


class DiaryActivity : AppCompatActivity() {

    private val handler = Handler( Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activite_diary)

        val dirayEdittext =  findViewById<EditText>(R.id.dirayEditText)
        val detailPreference = getSharedPreferences("diary", Context.MODE_PRIVATE)

        dirayEdittext.setText(detailPreference.getString("detail",""))

        val runnable = Runnable{
            getSharedPreferences("diary", Context.MODE_PRIVATE).edit{
                putString("detail",dirayEdittext.text.toString())
            }
            Log.d("DiaryActivity","Save ${dirayEdittext.text.toString()}")
        }

        dirayEdittext.addTextChangedListener {
            Log.d("DiaryActivity","Textchange :: $it")
            handler.removeCallbacks(runnable)
            handler.postDelayed(runnable,500)

        }
    }



}