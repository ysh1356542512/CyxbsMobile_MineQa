package com.mredrock.cyxbs.mine.page.stamp.eggshell

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieAnimationView
import com.mredrock.cyxbs.mine.R

/**
 * @Date : 2021/8/12   23:59
 * @By ysh
 * @Usage :
 * @Request : God bless my code
 **/
class EggShellActivity:AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mine_fragment_stamp_eggshell)
        val btnWithAni = findViewById<LottieAnimationView>(R.id.btn_with_ani)
        val arrowWithAni = findViewById<LottieAnimationView>(R.id.arrow_with_ani)
        btnWithAni.playAnimation()
        arrowWithAni.playAnimation()
    }
}