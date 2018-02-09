package cb.cphuong.nguoiconggiao.fragment.regis


import android.view.View

import cb.cphuong.nguoiconggiao.R
import cb.cphuong.nguoiconggiao.fragment.BaseFragment

class AddAvatar : BaseFragment() {
    override val LayoutId: Int
        get() = R.layout.fragment_add_avatar

    override fun injectDependence() {

    }

    override fun onCreate(v: View) {

    }

    override fun onDestroyComposi() {

    }

    companion object {
        val instance: AddAvatar
            get() = AddAvatar()

    }

}
