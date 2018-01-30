package cb.cphuong.nguoiconggiao.activity

import android.content.Context
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.widget.Toast
import cb.cphuong.nguoiconggiao.R
import cb.cphuong.nguoiconggiao.common.Contans
import cb.cphuong.nguoiconggiao.fragment.regis.InforLogin
import cb.cphuong.nguoiconggiao.fragment.regis.InforLocation
import cb.cphuong.nguoiconggiao.fragment.regis.InforStatus
import kotlinx.android.synthetic.main.activity_regis.*

class RegisActivity : AppCompatActivity() {
    private lateinit var pef: SharedPreferences
    private lateinit var edtPef: SharedPreferences.Editor
    var i: Int = 1;
    private lateinit var fA: Fragment
    private lateinit var fB: Fragment
    private lateinit var fC: Fragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_regis)
        pef = getSharedPreferences(Contans.SPF_NAME, Context.MODE_PRIVATE)
        edtPef = getSharedPreferences(Contans.SPF_NAME, Context.MODE_PRIVATE).edit()
        fA = InforLogin()
        fB = InforLocation()
        fC = InforStatus()


        btnForwardFragment.setOnClickListener {
            var string: String = (fragment.javaClass.simpleName)
            if (string.equals("InforLogin")) {
                InforLogin().savePref(edtPef)
                addFragment(fB, 1)
            }
            if (string.equals("InforLocation")) {
                addFragment(fC, 1)
            }
//            when(i){
//                2-> addFragment(InforLocation.newInstance())
//                3-> addFragment(InforStatus.newInstance())
//                else->Toast.makeText(applicationContext,"không thể next tiếp",Toast.LENGTH_LONG).show()
//            }

        }
        addFragment(fA, 0);
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
        Toast.makeText(applicationContext, fragment.javaClass.simpleName, Toast.LENGTH_LONG).show()

    }

    override fun onBackPressed() {
        var s: String = (fragment.javaClass.simpleName)
        if (s.equals("InforStatus")) {
            addFragment(fB, -1)

        } else if (s.equals("InforLocation")) {
            addFragment(fA, -1)
        } else {
            super.onBackPressed()
        }

    }


}
