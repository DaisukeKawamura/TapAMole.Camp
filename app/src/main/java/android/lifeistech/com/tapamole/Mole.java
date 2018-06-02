package android.lifeistech.com.tapamole;

import android.media.Image;
import android.os.Handler;
import android.util.AndroidException;
import android.widget.ImageView;

/**
 * Created by kawamuradaisuke on 2018/06/01.
 */

public class Mole {

    int state; //モグラの状態 0:潜っている 1:出てきている 2:叩かれている
    ImageView moleImage; //モグラのImageView

    android.os.Handler handler; //Handlerスレッド間の処理を投げる役割

    Runnable hide; //Handlerで投げる処理の中身を書くためのクラス

    public Mole(final ImageView imageView){

        state = 0;
        moleImage = imageView;
        moleImage.setImageResource(R.drawable.mole1);

        handler = new Handler();
        hide = new Runnable() {
            @Override
            public void run() {
                state = 0;

                imageView.setImageResource(R.drawable.mole1);
            }
        };
    }

    public void start(){
        if (state == 0){//モグラが引っ込んでいる状態の時
            state = 1;
            moleImage.setImageResource(R.drawable.mole2);

            handler.postDelayed(hide, 1000);
            //1000ミリ秒後にhideのなかのrun()メソッドを実行する
        }
    }

    public int tapMole(){
        if (state == 1){
            state = 2;
            moleImage.setImageResource(R.drawable.mole3);

            handler.removeCallbacks(hide);
            handler.postDelayed(hide,1000);

            return 1;
        }
        return 0;
    }



}
