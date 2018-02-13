package vn.phuongcong.fchattranslate.ui.base

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity


/**
 * Created by Ominext on 10/9/2017.
 */

abstract class BaseActivity : AppCompatActivity() {
    protected abstract val contentLayoutID: Int
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(contentLayoutID)
        injectDependence()
        onCreateData()
        onClick()
    }

    protected abstract fun onClick()


    override fun onDestroy() {
        super.onDestroy()
    }

    protected abstract fun injectDependence()
    protected abstract fun onCreateData()


    fun onStartActivity(b: Class<*>) {
        startActivity(Intent(this, b))
    }


}
