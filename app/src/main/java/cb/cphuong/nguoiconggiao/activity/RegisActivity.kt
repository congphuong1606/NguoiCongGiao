package cb.cphuong.nguoiconggiao.activity

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.View
import cb.cphuong.nguoiconggiao.R
import cb.cphuong.nguoiconggiao.common.Contans
import cb.cphuong.nguoiconggiao.fragment.regis.Status
import cb.cphuong.nguoiconggiao.fragment.regis.InforLogin
import cb.cphuong.nguoiconggiao.fragment.regis.AddAvatar
import com.rw.keyboardlistener.KeyboardUtils
import kotlinx.android.synthetic.main.activity_regis.*



class RegisActivity : AppCompatActivity() {
    private lateinit var pef: SharedPreferences


    private lateinit var fA: Fragment
    private lateinit var fB: Fragment
    private lateinit var fC: Fragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_regis)



        pef = getSharedPreferences(Contans.SPF_NAME, Context.MODE_PRIVATE)
        fA = InforLogin.instance
        fB = Status.instance
        fC = AddAvatar.instance
        keyBoardListener()


        onClick()

        addFragment(fA, 0);


    }


    private fun onClick() {
        btn_back_regis.setOnClickListener { onBackPressed() }
        btnForwardFragment.setOnClickListener {
            var string: String = (fragment.javaClass.simpleName)
            if (string.equals("InforLogin")) {
               if( InforLogin.instance.savePref(this,fA)){
                   addFragment(fB, 1)
               }

            }
            if (string.equals("Status")) {
                addFragment(fC, 1)
            }

        }
    }


    private fun keyBoardListener() {
        KeyboardUtils.addKeyboardToggleListener(this) { isVisible ->
            if (isVisible) {
                findViewById<View>(R.id.vi).setVisibility(View.GONE)
            } else {
                findViewById<View>(R.id.vi).setVisibility(View.VISIBLE)
            }

        }
    }

    private lateinit var fragment: Fragment


    protected fun addFragment(f: Fragment, nothing: Int?) {
        fragment = f;
        val fmgr = supportFragmentManager
        val ft = fmgr.beginTransaction()
        if (nothing == 1) {
            ft.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left)
        } else if (nothing == -1) {
            ft.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)

        }
        ft.replace(R.id.framelayout, fragment)
        ft.commit()


    }

    override fun onBackPressed() {
        var s: String = (fragment.javaClass.simpleName)
        if (s.equals("AddAvatar")) {
            addFragment(fB, -1)

        } else if (s.equals("Status")) {
            addFragment(fA, -1)
        } else {
            super.onBackPressed()
        }

    }


}
