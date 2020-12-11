package com.example.mobyardandroid.yard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;


import com.example.mobyardandroid.R;

public class MainYardActivity extends Activity implements View.OnTouchListener {
    private ImageView back,collision,collision1,bench,bench2,slide,slide2,alcove,alcove2,tree,tree1,bycycleparking,bycycleparking2,flowerbed,flowerbed2,flowerbed3,flowerbed4;
    private ViewGroup mMoveLayout;
    private int mX;
    private int mY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_yard);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        mMoveLayout = (ViewGroup) findViewById(R.id.mainlayout);

        back = (ImageView) mMoveLayout.findViewById(R.id.imageback) ;
        collision = (ImageView) mMoveLayout.findViewById(R.id.collision);
        collision1 = (ImageView) mMoveLayout.findViewById(R.id.collision1);
        bench = (ImageView) mMoveLayout.findViewById(R.id.imageBench);
        bench2 = (ImageView) mMoveLayout.findViewById(R.id.imageBench2);
        slide = (ImageView) mMoveLayout.findViewById(R.id.imageslide);
        slide2 = (ImageView) mMoveLayout.findViewById(R.id.imageslide2);
        alcove = (ImageView) mMoveLayout.findViewById(R.id.imagealcove);
        alcove2 = (ImageView) mMoveLayout.findViewById(R.id.imagealcove2);
        tree = (ImageView) mMoveLayout.findViewById(R.id.imageTree);
        tree1 = (ImageView) mMoveLayout.findViewById(R.id.imageTree1);
        bycycleparking = (ImageView) mMoveLayout.findViewById(R.id.bycycleparking);
        bycycleparking2 = (ImageView) mMoveLayout.findViewById(R.id.bycycleparking2);
        flowerbed = (ImageView) mMoveLayout.findViewById(R.id.flowerbed);
        flowerbed2 = (ImageView) mMoveLayout.findViewById(R.id.flowerbed2);
        flowerbed3 = (ImageView) mMoveLayout.findViewById(R.id.flowerbed3);
        flowerbed4 = (ImageView) mMoveLayout.findViewById(R.id.flowerbed4);



        //Создаем программно RelativeLayout с параметрами 100*100:
        ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(100, 100);



        //И настраиваем ему слушателя (обработчик) прикосновений:
        bench.setOnTouchListener(this);
        bench2.setOnTouchListener(this);
        slide.setOnTouchListener(this);
        slide2.setOnTouchListener(this);
        tree.setOnTouchListener(this);
        tree1.setOnTouchListener(this);
        bycycleparking.setOnTouchListener(this);
        bycycleparking2.setOnTouchListener(this);
        alcove.setOnTouchListener(this);
        alcove2.setOnTouchListener(this);
        tree.setOnTouchListener(this);
        tree1.setOnTouchListener(this);
        flowerbed.setOnTouchListener(this);
        flowerbed2.setOnTouchListener(this);
        flowerbed3.setOnTouchListener(this);
        flowerbed4.setOnTouchListener(this);

        back.setOnClickListener(v->{
            finish();
        });


        Rect rc1 = new Rect();
        tree.getDrawingRect(rc1);
        Rect rc2 = new Rect();
        bench.getDrawingRect(rc2);
        boolean qwe = false;



        if (Rect.intersects(rc1, rc2) ){
            Toast.makeText(getApplicationContext(), "куда полез", Toast.LENGTH_LONG).show();


        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        final int X = (int) event.getRawX();
        final int Y = (int) event.getRawY();
        switch (event.getAction() & MotionEvent.ACTION_MASK) {

            //ACTION_DOWN срабатывает при прикосновении к экрану,
            //здесь определяется начальное стартовое положение объекта:
            case MotionEvent.ACTION_DOWN:
                ConstraintLayout.LayoutParams lParams = (ConstraintLayout.LayoutParams) v.getLayoutParams();
                mX = X - lParams.leftMargin;
                mY = Y - lParams.topMargin;
                break;

            //ACTION_MOVE обрабатывает случившиеся в процессе прикосновения изменения, здесь
            //содержится информация о последней точке, где находится объект после окончания действия прикосновения ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) v.getLayoutParams();
                layoutParams.leftMargin = X - mX;
                layoutParams.topMargin = Y - mY;
                layoutParams.rightMargin = -250;
                layoutParams.bottomMargin = -250;
                v.setLayoutParams(layoutParams);
                break;
        }
        mMoveLayout.invalidate();
        return true;
    }

}



