package com.sir.jarvis;

import android.app.Activity;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.Locale;

public class MainActivity extends Activity {

    private TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(30, 60, 30, 30);

        TextView title = new TextView(this);
        title.setText("JARVIS Sir");
        title.setTextSize(28);
        layout.addView(title);

        Button speak = new Button(this);
        speak.setText("Speak JARVIS");
        layout.addView(speak);

        setContentView(layout);

        tts = new TextToSpeech(this, status -> {
            if (status == TextToSpeech.SUCCESS) {
                tts.setLanguage(new Locale("te", "IN"));
            }
        });

        speak.setOnClickListener(v -> {
            if (tts != null) {
                tts.speak(
                    "Hello Sir. JARVIS is ready.",
                    TextToSpeech.QUEUE_FLUSH,
                    null,
                    "jarvis"
                );
            }
        });
    }

    @Override
    protected void onDestroy() {
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }
}

