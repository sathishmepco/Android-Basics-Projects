package com.cdac.texttospeechdemo;

import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    Button buttonTTS;
    EditText editTextInput;
    TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init(){
        editTextInput = findViewById(R.id.editTextInput);
        buttonTTS = findViewById(R.id.buttonSpeak);

        textToSpeech = new TextToSpeech(MainActivity.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status == TextToSpeech.SUCCESS){
                    int result = textToSpeech.setLanguage(Locale.US);
                    if(result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED ){
                        buttonTTS.setEnabled(false);
                    }else{
                        buttonTTS.setEnabled(true);
                    }
                }else{
                    buttonTTS.setEnabled(false);
                }
            }
        });

        buttonTTS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textToSpeech.setSpeechRate(2.0f);
                textToSpeech.setPitch(2.0f);
                textToSpeech.speak(editTextInput.getText().toString(),TextToSpeech.QUEUE_FLUSH,null);
            }
        });
    }
}