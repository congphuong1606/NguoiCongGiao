package cb.cphuong.nguoiconggiao.activity.regis

import vn.phuongcong.fchat.ui.base.BaseView

/**
 * Created by Ominext on 2/12/2018.
 */
interface RegisView: BaseView {
    fun onSignUpSuccessful();
    fun onCreateUserSuccessful()
    fun updateAvatarSuccess(avatarUrl: String)
}