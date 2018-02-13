package cb.cphuong.nguoiconggiao.fragment.regis

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.support.v4.app.Fragment
import android.view.View
import cb.cphuong.nguoiconggiao.R
import cb.cphuong.nguoiconggiao.common.Contans
import cb.cphuong.nguoiconggiao.base.BaseFragment
import com.rw.keyboardlistener.KeyboardUtils
import kotlinx.android.synthetic.main.fragment_infor_login.view.*


class InforLogin : BaseFragment() {


    private var v: View? = null
    var prefs: SharedPreferences? = null
    override val LayoutId: Int
        get() = R.layout.fragment_infor_login

    override fun injectDependence() {

    }

    override fun onStart() {
        super.onStart()


    }

    override fun onCreate(view: View) {
        setText(view)
        keyBoardListener(view)


    }


    private fun keyBoardListener(view: View) {
        KeyboardUtils.addKeyboardToggleListener(activity) { isVisible ->
            if (isVisible) {
                view.viewF.setVisibility(View.GONE)
            } else {
                view.viewF.setVisibility(View.VISIBLE)
            }

        }
    }

    private fun setText(view: View) {
        prefs = context.getSharedPreferences(Contans.SPF_NAME, Context.MODE_PRIVATE)
        view.edtNickName.setText(prefs!!.getString(Contans.SPF_REGIS_NICK_NAME, ""))
        view.edtEmail.setText(prefs!!.getString(Contans.SPF_REGIS_EMAIL, ""))
        view.edtPass.setText(prefs!!.getString(Contans.SPF_REGIS_PASS, ""))
        view.edtConfirmPass.setText(prefs!!.getString(Contans.SPF_REGIS_CONFIRM_PASS, ""))
    }


    override fun onDestroyComposi() {

    }

    fun savePref(activity: Activity, fA: Fragment): Boolean {
        val v: View? = fA.view
        if (v != null) {
            val name = v.edtNickName.text.toString().trim()
            val email = v.edtEmail.text.toString().trim()
            val pass = v.edtPass.text.toString().trim()
            val confirmPass = v.edtConfirmPass.text.toString().trim()
            if (isValidEmail(email)) {
                if (name.length >= 6 && name.length <= 15) {
                    if (pass.length >= 6) {
                        if (confirmPass.equals(pass)) {
                            setPref(v,activity, name, email, pass, confirmPass)
                            return true
                        } else {
                            v.edtConfirmPass.requestFocus()
                            v.edtConfirmPass.setError("Mật khẩu chưa đúng")
                            return false
                        }
                    } else {
                        v.edtPass.requestFocus()
                        v.edtPass.setError("mật khẩu > 6 ký tự")
                        return false
                    }


                } else {
                    v.edtNickName.requestFocus()
                    v.edtNickName.setError("Quá ngắn hoặc quá dài")
                    return false
                }


            } else {
                v.edtEmail.requestFocus()
                v.edtEmail.setError("Email không đúng")
                return false
            }



        } else {
            return false
        }

    }
    fun isValidEmail(target: CharSequence?): Boolean {
        return if (target == null) {
            false
        } else {
            android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches()
        }
    }
    private fun setPref(v: View,activity: Activity, name: String, email: String, pass: String, confirmPass: String) {
        val editor = activity.getSharedPreferences(Contans.SPF_NAME, Context.MODE_PRIVATE).edit()
        editor.putString(Contans.SPF_REGIS_NICK_NAME, name)
        editor.putString(Contans.SPF_REGIS_EMAIL, email)
        editor.putString(Contans.SPF_REGIS_PASS, pass)
        editor.putString(Contans.SPF_REGIS_CONFIRM_PASS, confirmPass)
        editor.apply()
    }



    companion object {
        val instance: InforLogin
            get() = InforLogin()

    }




}

