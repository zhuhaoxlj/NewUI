package com.liudum.newui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.IOException;

import soup.neumorphism.NeumorphCardView;
import soup.neumorphism.NeumorphFloatingActionButton;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    MediaPlayer mediaPlayer;
    NeumorphCardView neumorphCardView;
    NeumorphCardView neumorphCardView2;
    NeumorphFloatingActionButton playButton;
    ImageView playButtonImage;
    ImageView pauseButtonImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        {
            imageView = findViewById(R.id.image);
            neumorphCardView = findViewById(R.id.menuButton);
            neumorphCardView2 = findViewById(R.id.selectButton);
            playButton = findViewById(R.id.play);
            playButtonImage = findViewById(R.id.playButtonImage);
            pauseButtonImage = findViewById(R.id.pauseButtonImage);
        }
        playButton.setOnClickListener(view -> {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
                pauseButtonImage.setVisibility(View.INVISIBLE);
                playButtonImage.setVisibility(View.VISIBLE);
            } else {
                pauseButtonImage.setVisibility(View.VISIBLE);
                playButtonImage.setVisibility(View.INVISIBLE);
                mediaPlayer.start();
            }
        });
        neumorphCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "点击了菜单", Toast.LENGTH_SHORT).show();
            }
        });
        neumorphCardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "点击了菜单", Toast.LENGTH_SHORT).show();

            }
        });
        // 隐藏状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //动画
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.img_animation);
        LinearInterpolator lin = new LinearInterpolator();//设置动画匀速运动
        animation.setInterpolator(lin);
        imageView.startAnimation(animation);

        // 音乐播放
        try {
            //播放 assets/a2.mp3 音乐文件
            AssetFileDescriptor fd = getAssets().openFd("Taylor Swift - Lover.mp3");
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setDataSource(fd.getFileDescriptor(), fd.getStartOffset(), fd.getLength());
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    Handler mHandler = new Handler();
    Runnable r = new Runnable() {
        @Override
        public void run() {
            //do something
            //每隔1s循环执行run方法
            mHandler.postDelayed(this, 1000);
        }
    };
}