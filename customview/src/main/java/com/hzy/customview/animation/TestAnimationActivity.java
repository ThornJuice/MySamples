package com.hzy.customview.animation;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.hzy.customview.R;
import com.hzy.customview.view.PointView;
import com.hzy.customview.view.ProvinceView;

import java.util.ArrayList;
import java.util.List;

public class TestAnimationActivity extends AppCompatActivity {
    ImageView imageView;
    ProvinceView provinceView;
    PointView pointView;
    List<String> provinceList = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_animation);
        imageView = findViewById(R.id.iv_image);
        provinceView = findViewById(R.id.provinceView);
        pointView = findViewById(R.id.pointView);
//        imageView.animate()
////               .alpha(0.1f)
////                .translationX(200)
//                .translationY(500)
//                .setDuration(1000)
//                .setStartDelay(100)
//                .setInterpolator(new BounceInterpolator())
//                .start();
//        Point point = new Point(200,500);
//        ObjectAnimator animator = ObjectAnimator.ofObject(pointView, "point", new PointTypeEvaluator(),point);
//        animator.setDuration(2000);
//        animator.setStartDelay(2000);
//        animator.start();

        provinceList.add("a");
        provinceList.add("b");
        provinceList.add("c");
        provinceList.add("d");
        provinceList.add("e");
        provinceList.add("f");
        provinceView.setLayerType(View.LAYER_TYPE_HARDWARE,null);
        ObjectAnimator animator = ObjectAnimator.ofObject(provinceView, "province", new ProvinceTypeEvaluator(), "d","f");
        animator.setDuration(4000);
        animator.setStartDelay(1000);
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                provinceView.setLayerType(View.LAYER_TYPE_NONE,null);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animator.start();
    }

    class PointTypeEvaluator implements TypeEvaluator<Point> {

        @Override
        public Point evaluate(float fraction, Point startValue, Point endValue) {
            float x = startValue.x + (endValue.x - startValue.x) * fraction;
            float y = startValue.x + (endValue.y - startValue.y) * fraction;
            return new Point((int) x, (int) y);
        }
    }

    class ProvinceTypeEvaluator implements TypeEvaluator<String> {

        @Override
        public String evaluate(float fraction, String startValue, String endValue) {
            float startIndex = provinceList.indexOf(startValue);
            float endIndex = provinceList.indexOf(endValue);
            Log.e("test......", "startIndex"+startIndex);
            Log.e("test......", "endIndex"+endIndex);
            int index = (int) (startIndex + (endIndex - startIndex) * fraction);
            return provinceList.get(index);
        }
    }
}