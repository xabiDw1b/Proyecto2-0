package com.aar.app.proyectoLlodio;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

public class Actividad3 extends AppCompatActivity {

    private ImageView lobo,bocadillo;
    private TextView texto;
    private ObjectAnimator animatorLobo,animatorBocadillo,animatorLoboRotation,animatorLobo1,animatorLobo2;
    private long animationLoboDuration = 1000;
    private long animationBocadilloDuration = 1500;
    private  TypeWriter tw;
    public static ScrollView scrollView;
    private Button buttonEmpezar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.actividad3);

        lobo = findViewById(R.id.lobo);
        bocadillo = findViewById(R.id.bocadillo);
        tw = (TypeWriter) findViewById(R.id.tv);
        scrollView = findViewById(R.id.scrollView);

        buttonEmpezar = findViewById(R.id.buttonEmpezar);

        DisplayMetrics metrics = new DisplayMetrics();

        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int width = metrics.widthPixels;

        animatorLoboRotation = ObjectAnimator.ofFloat(lobo, "rotation",0f, 360f);
        animatorLoboRotation.setDuration(animationLoboDuration);

        animatorLobo = ObjectAnimator.ofFloat(lobo, "x", 0.0f,(width-400));
        animatorLobo.setDuration(animationLoboDuration);
        AnimatorSet animatorSetX = new AnimatorSet();
        animatorSetX.playTogether(animatorLoboRotation, animatorLobo);
        animatorSetX.start();

        animatorBocadillo = ObjectAnimator.ofFloat(bocadillo, View.ALPHA,0.0f, 1.0f);
        animatorBocadillo.setDuration(animationBocadilloDuration);
        AnimatorSet animatorSetAlpha = new AnimatorSet();
        animatorSetAlpha.playTogether(animatorBocadillo);
        animatorSetAlpha.start();

        scrollView.fullScroll(View.FOCUS_DOWN);
        tw.setMovementMethod(new ScrollingMovementMethod());

        sicronizarTexto1();
        MediaPlayer mediaPlayer2 = MediaPlayer.create(this, R.raw.audioa_kondaira);
        buttonEmpezar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String texto =getString(R.string.texto2_a3);

                long saltoLobo = 50;
                animatorLobo2 = ObjectAnimator.ofFloat(lobo, "y", (lobo.getY()));
                animatorLobo1 = ObjectAnimator.ofFloat(lobo, "y", (lobo.getY()-30f));

                animatorLobo1.setDuration(saltoLobo);
                animatorLobo2.setDuration(saltoLobo);
                AnimatorSet animatorSetY1 = new AnimatorSet();
                AnimatorSet animatorSetY2 = new AnimatorSet();
                animatorSetY1.play(animatorLobo1);
                animatorSetY1.start();
                animatorSetY2.setStartDelay(50);
                animatorSetY2.play(animatorLobo2);
                animatorSetY2.start();

                buttonEmpezar.setText("Kondaira");
                buttonEmpezar.setVisibility(View.INVISIBLE);

                scrollView.fullScroll(View.FOCUS_DOWN);
                mediaPlayer2.start();
                tw.setmTypeSpeed(60);
                tw.setText("");
                tw.pause(1000);
                tw.type(texto).pause(1300)
                    .run(new Runnable() {
                        @Override
                        public void run() {
                            // Finalize the text if user fiddled with it during animation.
                            mediaPlayer2.stop();
                        }
                    });
            }
        });
    }

    public void  sicronizarTexto1() {

        String texto1 =getString(R.string.texto1_a3);

        MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.audioa_santa_apolonia);

        mediaPlayer.start();

        tw.setmTypeSpeed(55);
        tw.setText("");
        tw.pause(1500);
        tw.type(texto1).pause(1300)
            .run(new Runnable() {
                @Override
                public void run() {
                    // Finalize the text if user fiddled with it during animation.
                    tw.setText("Jarduera erabilgarria");
                    mediaPlayer.stop();
                    buttonEmpezar.setVisibility(View.VISIBLE);
                }
            });
    }
}
