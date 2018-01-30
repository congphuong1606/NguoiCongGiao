package cb.cphuong.nguoiconggiao.fragment.regis

import android.content.Context
import android.content.SharedPreferences
import android.view.View

import cb.cphuong.nguoiconggiao.R
import cb.cphuong.nguoiconggiao.common.Contans
import cb.cphuong.nguoiconggiao.fragment.BaseFragment

class InforLocation : BaseFragment() {
    override val LayoutId: Int
        get()=R.layout.fragment_infor_location

    override fun injectDependence() {

    }

    override fun onCreate(v: View) {
        var pef: SharedPreferences = context.getSharedPreferences(Contans.SPF_NAME, Context.MODE_PRIVATE)
        var edtPef: SharedPreferences.Editor = context.getSharedPreferences(Contans.SPF_NAME, Context.MODE_PRIVATE).edit()
    }





    override fun onDestroyComposi() {

    }




}
