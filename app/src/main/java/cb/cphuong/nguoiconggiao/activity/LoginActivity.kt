package cb.cphuong.nguoiconggiao.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.rw.keyboardlistener.KeyboardUtils
import cb.cphuong.nguoiconggiao.R
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        btn_into_create_acc_activity.setOnClickListener {
            startActivity(Intent(applicationContext, RegisActivity::class.java))
        }


    }

    private fun keyboard() {
        KeyboardUtils.addKeyboardToggleListener(this) { isVisible ->
            if (isVisible) {

            } else {
//                val params = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT)
//                params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.FALSE)
//                ln.setLayoutParams(params)

            }
            //                Toast.makeText(ChapterActivity.this, "keyboard visible: "+isVisible,Toast.LENGTH_LONG).show();
        }
    }
}
