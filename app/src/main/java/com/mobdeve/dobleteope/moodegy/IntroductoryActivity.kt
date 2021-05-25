package com.mobdeve.dobleteope.moodegy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.airbnb.lottie.LottieAnimationView

class IntroductoryActivity : AppCompatActivity() {

    private lateinit var lottie: LottieAnimationView;
    private val SPLASH_SCREEN = 5000;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_introductory)

        lottie = findViewById(R.id.lottie);

        lottie.animate().translationY(1400F).setDuration(1000).setStartDelay(4000);

//        new Handler().postDelayed)(new Runnable () {
//            @Override
//            public void run() {
//
//                Intent intent = new Intent (MainActivity.this, MainActivity.class);
//                startActivity(intent);
//                finish();
//            }
//        }, SPLASH_SCREEN);

    }
}