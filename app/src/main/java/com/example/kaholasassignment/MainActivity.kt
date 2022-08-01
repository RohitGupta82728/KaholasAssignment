package com.example.kaholasassignment

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class MainActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

    private var tts: TextToSpeech? = null
    private var btnSpeak: Button? = null
    private var etSpeak: EditText? = null
    private var textSpeak: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnSpeak = findViewById(R.id.btn_speak)
        etSpeak = findViewById(R.id.et_input)
        textSpeak = findViewById(R.id.textView)


        btnSpeak!!.isEnabled = false

        tts = TextToSpeech(this, this)

      

        btnSpeak!!.setOnClickListener { speakOut() }


    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val result = tts!!.setLanguage(Locale.US)
            tts!!.speak(textSpeak!!.text.toString(),TextToSpeech.QUEUE_ADD,null)

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "The Language not supported!")
            } else {
                btnSpeak!!.isEnabled = true
            }
        }
    }



    private fun speakOut() {

        val text = etSpeak!!.text.toString()
        if (text == "4") {
            tts!!.speak("Correct", TextToSpeech.QUEUE_FLUSH, null, "")
        } else {
            tts!!.speak("InCorrect", TextToSpeech.QUEUE_FLUSH, null, "")
        }
    }

    public override fun onDestroy() {
        // Shutdown TTS when
        // activity is destroyed
        if (tts != null) {
            tts!!.stop()
            tts!!.shutdown()
        }
        super.onDestroy()
    }


}