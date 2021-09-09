package com.laco.sample.state

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.laco.sample.state.databinding.ActivityMainBinding
import com.laco.sample.state.state1.State1Activity
import com.laco.sample.state.state2.State2Activity
import com.laco.sample.state.state3.State3Activity
import com.laco.sample.state.state4.State4Activity
import dagger.hilt.android.AndroidEntryPoint
import kotlin.reflect.KClass

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this

        onClick(binding.btnState1, State1Activity::class)
        onClick(binding.btnState2, State2Activity::class)
        onClick(binding.btnState3, State3Activity::class)
        onClick(binding.btnState4, State4Activity::class)
    }

    private fun onClick(view: View, activity: KClass<out Activity>) = view.setOnClickListener {
        val intent = Intent(this, activity.java)
        startActivity(intent)
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }
}
