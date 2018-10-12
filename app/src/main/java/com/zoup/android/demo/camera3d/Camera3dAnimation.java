package com.zoup.android.demo.camera3d;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * Created by zoup on 2018/10/12
 * E-Mail：2479008771@qq.com
 */
public class Camera3dAnimation extends Animation {
    private Camera camera;
    //3D rotate;
    private float centerX;
    private float centerY;
    private View fromView;
    private View toView;
    private boolean forward = true;

    public Camera3dAnimation(View fromView, View toView) {
        this.fromView = fromView;
        this.toView = toView;
    }

    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
        //以中心为轴
        centerX = width / 2;
        centerY = height / 2;

        camera = new Camera();

        //动画持续的时间
        setDuration(1000);
        setFillAfter(false);
        setInterpolator(new AccelerateDecelerateInterpolator());
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        super.applyTransformation(interpolatedTime, t);

        //interpolated取值为0.0到1.0
        float degrees = (float) (180.0 *interpolatedTime);

        //一半之后显示toView
        if (interpolatedTime >= 0.5f) {
            degrees -= 180.f;
            fromView.setVisibility(View.GONE);
            toView.setVisibility(View.VISIBLE);
        }
        //绕着Y轴旋转180度（逆时针方向）后下一次动画就是顺时针方向
        if (forward)
            degrees = -degrees;

        final Matrix matrix = t.getMatrix();
        camera.save();
        //以Y轴翻转
        camera.rotateY(degrees);
        camera.getMatrix(matrix);
        camera.restore();
        //preTranslate函数是在旋转前移动而postTranslate是在旋转完成后移动，主要作用是让对象围绕自己的中心二旋转
        matrix.preTranslate(-centerX, -centerY);
        matrix.postTranslate(centerX, centerY);

    }

    /**
     * 当完成一次动画后，下次动画交换正面和反面
     */
    public void reverse() {
        forward = false;
        View switchView = toView;
        toView = fromView;
        fromView = switchView;
    }

}
