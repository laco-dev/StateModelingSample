package com.laco.sample.state

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.laco.sample.state.databinding.ActivityStateBinding

abstract class StateActivity : AppCompatActivity() {

    protected lateinit var binding: ActivityStateBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_state)
        binding.lifecycleOwner = this
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }
}
