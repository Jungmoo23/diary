package kr.co.so.softcapus.chapter3

import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.NumberPicker
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.edit

class MainActivity : AppCompatActivity() {

    private val numberPicker1: NumberPicker by lazy{
    findViewById<NumberPicker>(R.id.numberPicker1)
        .apply {
            minValue = 0
            maxValue = 9
        }
    }
    private val numberPicker2: NumberPicker by lazy{
        findViewById<NumberPicker>(R.id.numberPicker2)
            .apply {
                minValue = 0
                maxValue = 9
            }
    }
    private val numberPicker3: NumberPicker by lazy{
        findViewById<NumberPicker>(R.id.numberPicker3)
            .apply {
                minValue = 0
                maxValue = 9
            }
    }

    private val openOption: AppCompatButton by lazy {
        findViewById(R.id.Openbutton)
    }
    private val changePassWordbtn: AppCompatButton by lazy {
        findViewById(R.id.changePasswordBtn)
    }

    private var changePasswordBtnMode=false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        numberPicker1
        numberPicker2
        numberPicker3

        openOption.setOnClickListener{

            if(changePasswordBtnMode){
                Toast.makeText(this,"비밀번호 변경 중입니다.",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val passwordPrefereneces = getSharedPreferences("password", Context.MODE_PRIVATE)

            val passwordFromuser = "${numberPicker1.value}${numberPicker2.value}${numberPicker3.value}"

            if(passwordPrefereneces.getString("password","000").equals(passwordFromuser)){
                // 패스워드 성공
                //TODO 다이어리 페이지 작성 후에 넘겨 주어야 함
                startActivity(Intent(this,DiaryActivity::class.java))
            }
            else{
                // 실패
                showErrorAlertDialog()
            }
        }

        changePassWordbtn.setOnClickListener{
            val passwordPrefereneces = getSharedPreferences("password", Context.MODE_PRIVATE)

            val passwordFromuser = "${numberPicker1.value}${numberPicker2.value}${numberPicker3.value}"
            if(changePasswordBtnMode){
                //번호를 저장하는 기능

                passwordPrefereneces.edit() {
                    putString("password", passwordFromuser)
                }
                changePasswordBtnMode = false
                changePassWordbtn.setBackgroundColor(Color.BLACK)

            }
            else{
                // chamngePasswordMode가 활성화 :: 비밀번호가 맞는지를 체크
                if(passwordPrefereneces.getString("password","000").equals(passwordFromuser)){
                    Toast.makeText(this,"변경할 패스워드를 입력해주세요",Toast.LENGTH_SHORT).show()
                    changePasswordBtnMode = true
                    Log.d("MainActivity" ,"확인")
                    changePassWordbtn.setBackgroundColor(Color.RED)
                }
                else{
                    showErrorAlertDialog()
                }

            }
        }

    }
    private fun showErrorAlertDialog(){
        AlertDialog.Builder(this).setTitle("실패").setMessage("비밀번호가 잘못 되었습니다.").setPositiveButton("확인"){
                _,_->
        }.create().show()
    }


}