package com.horizon.hearttree;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.horizon.hearttree.heart.OnReadyListener;
import com.horizon.hearttree.heart.TreeView;

public class MainActivity extends Activity {
    private   Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView tipsTv =findViewById(R.id.tips_tv);
        final TextView tipsTv2 =findViewById(R.id.tips2_tv);
        TreeView treeView = findViewById(R.id.tree_view);
        treeView.setReadyListener(new OnReadyListener() {
            @Override
            public void onReady() {
               showTips(tipsTv,tipsTv2);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        intent= new Intent(MainActivity.this,MusicServer.class);
        startService(intent);
    }

    private void showTips(final TextView tipsTv, final TextView tips2){
        tipsTv.postDelayed(new Runnable() {
            @Override
            public void run() {
                tipsTv.setVisibility(View.VISIBLE);
                Animation animation= AnimationUtils.loadAnimation(MainActivity.this,
                        R.anim.fade_in);
                tipsTv.startAnimation(animation);
            }
        }, 1000L);

        tips2.postDelayed(new Runnable() {
            @Override
            public void run() {
                tips2.setVisibility(View.VISIBLE);
                Animation animation= AnimationUtils.loadAnimation(MainActivity.this,
                        R.anim.fade_in);
                tips2.startAnimation(animation);
            }
        }, 1000L);
    }

    @Override
    protected void onStop() {
        if (null!=intent){
            stopService(intent);
        }
        super.onStop();
    }
}
