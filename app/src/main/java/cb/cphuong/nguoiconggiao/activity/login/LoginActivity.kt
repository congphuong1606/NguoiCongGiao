package cb.cphuong.nguoiconggiao.activity.login

import android.app.ProgressDialog
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import cb.cphuong.nguoiconggiao.App
import com.rw.keyboardlistener.KeyboardUtils
import cb.cphuong.nguoiconggiao.R
import cb.cphuong.nguoiconggiao.activity.regis.RegisActivity
import cb.cphuong.nguoiconggiao.common.DialogUtils
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.fragment_infor_login.*
import vn.phuongcong.fchat.LoginPresenter
import vn.phuongcong.fchat.di.module.ViewModule
import vn.phuongcong.fchat.model.User
import vn.phuongcong.fchat.ui.login.LoginView
import vn.phuongcong.fchattranslate.ui.base.BaseActivity
import javax.inject.Inject


class LoginActivity : BaseActivity() , LoginView {

    @Inject
    lateinit var mPresenter: LoginPresenter
    @Inject
    lateinit var prefs: SharedPreferences
    @Inject
    lateinit var prefsEditor: SharedPreferences.Editor


    override fun onRequestFailure(string: String) {

    }

    override fun showToast(msg: String) {

    }

    override fun onLoginSuccessfull() {

    }

    override fun onViriFail() {

    }

    override fun onVerified(user: User?) {
  val a="a"
    }

    override val contentLayoutID: Int
        get() = R.layout.activity_login

    override fun onClick() {
        btn_into_create_acc_activity.setOnClickListener {
            startActivity(Intent(applicationContext, RegisActivity::class.java))
        }
        btn_login.setOnClickListener {
            loginAction()
        }
    }
    private var email: String? = null
    private var pass: String? = null
    private fun loginAction() {
        val nickName=edtNickNameLogin.text.toString().trim()
        val pass=edtPassLogin.text.toString().trim()
        mPresenter.getEmail(nickName,pass)
        email=""

//
//        if (CheckInput.checkInPutLogin(edtLoginEmail,
//                        edtLoginPass, this)) {
//            dialogUtils.showLoading()
//            email = edtLoginEmail.text.toString().trim();
//            pass = edtLoginPass.text.toString().trim();
//            if (Remember.isChecked) {
//                prefsEditor.putString(Contans.LOGIN_EMAIL,email)
//                        .putString(Contans.LOGIN_PASS, pass)
//                        .commit()
//
//            }else{
//                prefsEditor.clear().commit()
//            }
//            mPresenter.onSignIn(email!!, pass!!)
//        }
    }
    fun isValidEmail(target: CharSequence?): Boolean {
        return if (target == null) {
            false
        } else {
            android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches()
        }
    }
    override fun injectDependence() {
        (application as App).component
                .plus(ViewModule(this))
                .injectTo(this)
    }
    private lateinit var dialogUtils: DialogUtils
    private var dialog: ProgressDialog? = null
    override fun onCreateData() {
        dialogUtils = DialogUtils(dialog, this)
    }



    private fun keyboard() {
        KeyboardUtils.addKeyboardToggleListener(this) { isVisible ->
            if (isVisible) {

            } else {


            }
        }
    }
}
