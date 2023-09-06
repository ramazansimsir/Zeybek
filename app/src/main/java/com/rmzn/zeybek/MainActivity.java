package com.rmzn.zeybek;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.aldebaran.qi.Future;
import com.aldebaran.qi.sdk.QiContext;
import com.aldebaran.qi.sdk.QiSDK;
import com.aldebaran.qi.sdk.RobotLifecycleCallbacks;
import com.aldebaran.qi.sdk.builder.AnimateBuilder;
import com.aldebaran.qi.sdk.builder.AnimationBuilder;
import com.aldebaran.qi.sdk.builder.HolderBuilder;
import com.aldebaran.qi.sdk.design.activity.RobotActivity;
import com.aldebaran.qi.sdk.object.actuation.Animate;
import com.aldebaran.qi.sdk.object.actuation.Animation;
import com.aldebaran.qi.sdk.object.holder.AutonomousAbilitiesType;
import com.aldebaran.qi.sdk.object.holder.Holder;

public class MainActivity extends RobotActivity implements RobotLifecycleCallbacks {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Register the RobotLifecycleCallbacks to this Activity.
        QiSDK.register(this, this);
    }

    @Override
    protected void onDestroy() {
        // Unregister the RobotLifecycleCallbacks for this Activity.
        QiSDK.unregister(this, this);
        super.onDestroy();
    }

    @Override
    public void onRobotFocusGained(QiContext qiContext) {
        // The robot focus is gained.
        // Create an animation object.
        // Build the holder for the ability.
        Holder holder = HolderBuilder.with(qiContext)
                .withAutonomousAbilities(AutonomousAbilitiesType.BACKGROUND_MOVEMENT,AutonomousAbilitiesType.BASIC_AWARENESS)
                .build();
        // Release the ability asynchronously.

        Animation myAnimation = AnimationBuilder.with(qiContext)
                .withResources(R.raw.animation_00)
                .build();

// Build the action.
        Animate animate = AnimateBuilder.with(qiContext)
                .withAnimation(myAnimation)
                .build();

        Future<Void> animateFuture=animate.async().run();

        holder.async().hold();


        Animation myAnimation2 = AnimationBuilder.with(qiContext)
                .withResources(R.raw.trajectory_00)
                .build();

// Build the action.
        Animate animate2 = AnimateBuilder.with(qiContext)
                .withAnimation(myAnimation2)
                .build();

        animate2.async().run();



    }

    @Override
    public void onRobotFocusLost() {
        // The robot focus is lost.
    }

    @Override
    public void onRobotFocusRefused(String reason) {
        // The robot focus is refused.
    }
}