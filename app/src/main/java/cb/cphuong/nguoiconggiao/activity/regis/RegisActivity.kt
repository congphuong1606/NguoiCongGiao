package cb.cphuong.nguoiconggiao.activity.regis

import android.app.ProgressDialog
import android.content.Context
import android.content.SharedPreferences
import android.support.v4.app.Fragment
import android.util.Base64
import android.view.View
import cb.cphuong.nguoiconggiao.App
import cb.cphuong.nguoiconggiao.R
import cb.cphuong.nguoiconggiao.common.Contans
import cb.cphuong.nguoiconggiao.common.DialogUtils
import cb.cphuong.nguoiconggiao.fragment.regis.Status
import cb.cphuong.nguoiconggiao.fragment.regis.InforLogin
import cb.cphuong.nguoiconggiao.fragment.regis.AddAvatar
import com.rw.keyboardlistener.KeyboardUtils
import kotlinx.android.synthetic.main.activity_regis.*
import vn.phuongcong.fchat.di.module.ViewModule
import vn.phuongcong.fchat.model.User
import vn.phuongcong.fchattranslate.ui.base.BaseActivity
import javax.inject.Inject


class RegisActivity() : BaseActivity(), RegisView {

    private var prefs: SharedPreferences? = null

    public var email: String? = null
    public var pass: String? = null
    public var nickName: String? = null
    public var christian: String? = null
    public var birthDayprefs: String? = null
    public var diocese: String? = null
    public var parish: String? = null
    public var genders: String? = null
    public var avatar: String? = null
    var dialog: ProgressDialog? = null
    var dialogutils: DialogUtils? = null


    private lateinit var pef: SharedPreferences
    @Inject
    lateinit var regisPresenter: RegisPresenter

    private lateinit var fA: Fragment
    private lateinit var fB: Fragment
    private lateinit var fC: Fragment
    override val contentLayoutID: Int
        get() = R.layout.activity_regis

    override fun injectDependence() {
        (application as App).component
                .plus(ViewModule(this))
                .injectTo(this)
    }

    override fun onCreateData() {
        dialogutils = DialogUtils(dialog, this)
        pef = getSharedPreferences(Contans.SPF_NAME, Context.MODE_PRIVATE)
        fA = InforLogin.instance
        fB = Status.instance
        fC = AddAvatar.instance
        keyBoardListener()
        addFragment(fA, 0);
    }


    override fun onClick() {
        btn_back_regis.setOnClickListener { onBackPressed() }
        btnForwardFragment.setOnClickListener {
            var string: String = (fragment.javaClass.simpleName)
            if (string.equals("InforLogin")) {
                if (InforLogin.instance.savePref(this, fA)) {
                    addFragment(fB, 1)
                }

            }
            if (string.equals("Status")) {
                if (Status.instance.savePref(this, fB)) {
                    addFragment(fC, 1)
                }
            }
            if (string.equals("AddAvatar")) {
                regis();
            }

        }
    }

    var newAvatar: ByteArray? = null
    fun setAvatar(newAvatar: ByteArray) {
        this.newAvatar = newAvatar
    }

    private fun regis() {
        dialogutils!!.showLoading()
        prefs = getSharedPreferences(Contans.SPF_NAME, Context.MODE_PRIVATE)
        email = prefs!!.getString(Contans.SPF_REGIS_EMAIL, "")
        nickName = prefs!!.getString(Contans.SPF_REGIS_NICK_NAME, "")
        christian = prefs!!.getString(Contans.SPF_REGIS_CHRISTIAN, "")
        birthDayprefs = prefs!!.getString(Contans.SPF_REGIS_BIRTHDAY, "")
        diocese = prefs!!.getString(Contans.SPF_REGIS_DIOCESE, "")
        parish = prefs!!.getString(Contans.SPF_REGIS_PARISH, "")
        genders = prefs!!.getString(Contans.SPF_REGIS_GENDERS, "")
        pass = prefs!!.getString(Contans.SPF_REGIS_PASS, "")
        avatar = prefs!!.getString(Contans.SPF_REGIS_AVATAR, "")
        var array:ByteArray?=null
        try {
            array = Base64.decode(avatar, Base64.DEFAULT)
        } catch (e: Exception) {
        }
        if (array != null){
            regisPresenter.updateAvatar(array)
        }else{
            regisPresenter.onSignUp(email!!, pass!!)
        }


    }

    override fun updateAvatarSuccess(avatarUrl: String) {
        prefs!!.edit().putString(Contans.SPF_REGIS_AVATAR, avatarUrl).apply()
        avatar = avatarUrl
        regisPresenter.onSignUp(email!!, pass!!)
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
        val s: String = (fragment.javaClass.simpleName)
        if (s.equals("AddAvatar")) {
            addFragment(fB, -1)

        } else if (s.equals("Status")) {
            addFragment(fA, -1)
        } else {
            super.onBackPressed()
        }

    }

    override fun showToast(msg: String) {

    }

    override fun onSignUpSuccessful() {
        val currentUser = User("", nickName!!, email!!, avatar!!, christian!!, birthDayprefs!!, diocese!!, parish!!, genders!!, System.currentTimeMillis())
        regisPresenter.onCreatUserDatabase(email!!, currentUser)

    }

    override fun onCreateUserSuccessful() {
        dialogutils!!.hideLoading()
        finish()

    }


    override fun onRequestFailure(string: String) {
        dialogutils!!.hideLoading()
    }
}
