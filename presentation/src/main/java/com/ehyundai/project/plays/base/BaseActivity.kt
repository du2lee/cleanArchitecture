package com.ehyundai.project.plays.base

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<B : ViewDataBinding>(@LayoutRes private val resId: Int) :
    AppCompatActivity() {

    private var _binding: B? = null // 데이터 바인딩
    val binding get() = _binding!!
    abstract val TAG: String // 액티비티 태그

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG, "onCreate")
        DataBindingUtil.setContentView<B>(this, resId).apply {
            _binding = this
        }
        binding.lifecycleOwner = this
    }

    override fun onRestart() {
        super.onRestart()
        Log.i(TAG, "onRestart")
    }


    override fun onStart() {
        super.onStart()
        Log.i(TAG, "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.i(TAG, "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.i(TAG, "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i(TAG, "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "onDestroy")
        _binding = null
    }

    protected fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}