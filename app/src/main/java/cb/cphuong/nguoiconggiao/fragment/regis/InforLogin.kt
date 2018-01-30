package cb.cphuong.nguoiconggiao.fragment.regis

import android.content.Context
import android.content.SharedPreferences
import android.view.View

import cb.cphuong.nguoiconggiao.R
import cb.cphuong.nguoiconggiao.R.id.*
import cb.cphuong.nguoiconggiao.common.Contans
import cb.cphuong.nguoiconggiao.fragment.BaseFragment
import kotlinx.android.synthetic.main.fragment_infor_login.view.*

class InforLogin : BaseFragment() {
    private  var v:View?=null
    private lateinit var pef: SharedPreferences

    private lateinit var edtPef: SharedPreferences.Editor
    override val LayoutId: Int
        get() = R.layout.fragment_infor_login

    override fun injectDependence() {

    }



    override fun onCreate(view: View) {
        pef= context.getSharedPreferences(Contans.SPF_NAME, Context.MODE_PRIVATE)
        edtPef = context.getSharedPreferences(Contans.SPF_NAME, Context.MODE_PRIVATE).edit()
        view.edt_ConfirmPass.text

    }



    override fun onDestroyComposi() {

    }

    fun savePref(edtPef: SharedPreferences.Editor?) {
//        edtPef.putString(Contans.SPF_REGIS_NICK_NAME, "d")
    }


//    companion object {
//
//        private val ARG_PARAM1 = "param1"
//        private val ARG_PARAM2 = "param2"
//
//        fun savePef(edtPef: SharedPreferences.Editor) {
//            edtPef.putString(Contans.SPF_REGIS_NICK_NAME, "d")
//            view.edtNickName
//            edtNumber
//            edtPass
//
//        }
//
//        fun newInstance(param1: String, param2: String): InforLogin {
//            val fragment = InforLogin()
//
//            return fragment
//        }
//
//    }
}
