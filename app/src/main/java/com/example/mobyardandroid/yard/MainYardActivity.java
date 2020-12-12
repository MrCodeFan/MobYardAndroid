package com.example.mobyardandroid.yard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.drawable.RotateDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import android.graphics.Matrix;
import android.widget.ImageView;


import com.example.mobyardandroid.R;

public class MainYardActivity extends Activity implements View.OnTouchListener {
    private ImageView image2,image4,image5,image6,image7,image8,image9,image10,image11, image12,image13,image14,image15,
            image16,back,collision,collision1,bench,bench2,slide,slide2,alcove,alcove2,tree,tree1,
            bycycleparking,bycycleparking2,flowerbed,flowerbed2,flowerbed3,flowerbed4;
    private ViewGroup mMoveLayout;
    private int mX;
    private int mY;
    public int q = 0,w=0,e=0,r=0,t=0,y=0,u=0,i=0,o=0,p=0,a=0,s=0,d=0,f=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_yard);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        mMoveLayout = (ViewGroup) findViewById(R.id.mainlayout);

        image2 = (ImageView)mMoveLayout.findViewById(R.id.imageView2);
        image4 = (ImageView)mMoveLayout.findViewById(R.id.imageView4);
        image5 = (ImageView)mMoveLayout.findViewById(R.id.imageView5);
        image6 = (ImageView)mMoveLayout.findViewById(R.id.imageView6);
        image7 = (ImageView)mMoveLayout.findViewById(R.id.imageView7);
        image8 = (ImageView)mMoveLayout.findViewById(R.id.imageView8);
        image9 = (ImageView)mMoveLayout.findViewById(R.id.imageView9);
        image10 = (ImageView)mMoveLayout.findViewById(R.id.imageView10);
        image11 = (ImageView)mMoveLayout.findViewById(R.id.imageView11);
        image12 = (ImageView)mMoveLayout.findViewById(R.id.imageView12);
        image13 = (ImageView)mMoveLayout.findViewById(R.id.imageView13);
        image14= (ImageView)mMoveLayout.findViewById(R.id.imageView14);
        image15= (ImageView)mMoveLayout.findViewById(R.id.imageView15);
        image16= (ImageView)mMoveLayout.findViewById(R.id.imageView16);


        back = (ImageView) mMoveLayout.findViewById(R.id.imageback) ;

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

        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                q += 90;
                flowerbed3.animate().rotation(q);

            }
        });
        image4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                w += 90;
                flowerbed.animate().rotation(w);

            }
        });
        image5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                e += 90;
                flowerbed4.animate().rotation(e);

            }
        });
        image6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                r += 90;
                flowerbed2.animate().rotation(r);

            }
        });
        image7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                t += 90;
                bycycleparking2.animate().rotation(t);

            }
        });
        image8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                y += 90;
                bycycleparking.animate().rotation(y);

            }
        });
        image9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                u += 90;
                tree.animate().rotation(u);

            }
        });
        image10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i += 90;
                tree1.animate().rotation(i);

            }
        });
        image11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                o += 90;
                bench2.animate().rotation(o);

            }
        });
        image12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p += 90;
                bench.animate().rotation(p);

            }
        });
        image13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a += 90;
                alcove2.animate().rotation(a);

            }
        });
        image14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s += 90;
                alcove.animate().rotation(s);

            }
        });
        image15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d += 90;
                slide2.animate().rotation(d);

            }
        });
        image16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                f += 90;
                slide.animate().rotation(f);
                
            }
        });

        //Создаем программно RelativeLayout с параметрами 100*100:
        ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(100, 100);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        });



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



