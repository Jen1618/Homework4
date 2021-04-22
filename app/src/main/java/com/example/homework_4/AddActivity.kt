package com.example.homework_4

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity

class AddActivity : AppCompatActivity() {

    private lateinit var title: EditText
    private lateinit var context: EditText
    private lateinit var interpretation: EditText
    private lateinit var feeling: Spinner
    private lateinit var button: Button
    private val dreamViewModel: DreamViewModel by viewModels{
        DreamViewModelFactory((application as DreamApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle? ) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_two)
        title = findViewById(R.id.editText_title)
        context = findViewById(R.id.editText_context)
        interpretation = findViewById(R.id.editText_interpretation)
        val feelings = resources.getStringArray(R.array.feelings_array)
        button = findViewById(R.id.button_save)

        if(!intent.hasExtra("idUpdate")){
            feeling = findViewById(R.id.spinner_feeling)
            if (feeling != null) { val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, feelings)
                feeling.adapter = adapter


                button.setOnClickListener{
                    if(TextUtils.isEmpty(title.text) ||TextUtils.isEmpty(context.text) ||TextUtils.isEmpty(interpretation.text) ||TextUtils.isEmpty(feeling.selectedItem.toString())){
                        ToastError("Missing fields")
                    }
                    else{
                        var dreamVal = Dream(null,title.text.toString(), context.text.toString(), interpretation.text.toString(), feeling.selectedItem.toString())
                        dreamViewModel.insert(dreamVal)
                        finish()
                    }
                }
            }
        }
        else{
            val id = intent.getIntExtra("idUpdate",0)
            val compareVal = dreamViewModel.getDream(id).emotion;
            feeling = findViewById(R.id.spinner_feeling)
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, feelings)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
            feeling.setAdapter(adapter)
            if (compareVal != null) {
                val spinnerPosition = adapter.getPosition(compareVal)
                feeling.setSelection(spinnerPosition)
            }
            title.setText( dreamViewModel.getDream(intent.getIntExtra("idUpdate",0)).title)
            context.setText( dreamViewModel.getDream(intent.getIntExtra("idUpdate",0)).content)
            interpretation.setText( dreamViewModel.getDream(intent.getIntExtra("idUpdate",0)).reflection)
            button.setOnClickListener {
                dreamViewModel.UpdateDream(id, title.text.toString(),context.text.toString(),interpretation.text.toString(),feeling.selectedItem.toString())
                intent = Intent(this,MainActivity::class.java)
                startActivity(intent)
            }
        }
    }
    private fun ToastError(text:String){
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

}