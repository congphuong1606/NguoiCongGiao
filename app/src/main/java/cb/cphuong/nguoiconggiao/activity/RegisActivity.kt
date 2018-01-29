package cb.cphuong.nguoiconggiao.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.widget.Toast
import cb.cphuong.nguoiconggiao.R
import cb.cphuong.nguoiconggiao.fragment.BlankFragment
import cb.cphuong.nguoiconggiao.fragment.BlankFragment2
import cb.cphuong.nguoiconggiao.fragment.BlankFragment3
import kotlinx.android.synthetic.main.activity_regis.*

class RegisActivity : AppCompatActivity() {

    var i: Int = 1;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_regis)
        btnForwardFragment.setOnClickListener {
            var string:String=(fragment.javaClass.simpleName)
            if (string.equals("BlankFragment")) {
                addFragment(BlankFragment2())
            }
            if(string.equals("BlankFragment2")){
                addFragment(BlankFragment3())
            }
//            when(i){
//                2-> addFragment(BlankFragment2.newInstance())
//                3-> addFragment(BlankFragment3.newInstance())
//                else->Toast.makeText(applicationContext,"không thể next tiếp",Toast.LENGTH_LONG).show()
//            }

        }
        addFragment(BlankFragment())
    }

    private lateinit var fragment: Fragment


    protected fun addFragment(f: Fragment) {
        fragment = f;
        val fmgr = supportFragmentManager
        val ft = fmgr.beginTransaction()
        ft.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
        ft.replace(R.id.framelayout, fragment)
        ft.commit()
        Toast.makeText(applicationContext, fragment.javaClass.simpleName, Toast.LENGTH_LONG).show()

    }

    override fun onBackPressed() {
        var s:String=(fragment.javaClass.simpleName)
        if (s.equals("BlankFragment3")) {
            addFragment(BlankFragment2())

        }else if(s.equals("BlankFragment2")){
            addFragment(BlankFragment())
        }else{
            super.onBackPressed()
        }

    }


}
