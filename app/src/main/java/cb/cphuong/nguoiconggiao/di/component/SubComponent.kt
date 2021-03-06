package vn.phuongcong.fchat.di.component

import cb.cphuong.nguoiconggiao.activity.login.LoginActivity
import cb.cphuong.nguoiconggiao.activity.regis.RegisActivity
import dagger.Subcomponent

import vn.phuongcong.fchat.di.module.SharedPreference
import vn.phuongcong.fchat.di.module.ViewModule
import vn.phuongcong.fchat.di.scope.ActivityScope


/**
 * Created by Ominext on 10/12/2017.
 */
@ActivityScope
@Subcomponent(modules = arrayOf(ViewModule::class, SharedPreference::class))
interface SubComponent {
    fun injectTo(regisActivity: RegisActivity)
    fun injectTo(loginActivity: LoginActivity)
//    fun injectTo(loginActivity: LoginActivity)
//    fun injectTo(regisActivity: RegisActivity)
//    fun injectTo(groupFragment: GroupFragment)
//    fun injectTo(friendFragment: FriendFragment)
//    fun injectTo(msgFragment: MsgFragment)
//    fun injectTo(profileActivity: ProfileActivity)
//    fun injectTo(splashActivity: SplashActivity)
//    fun injectTo(chatGroupActivity: ChatGroupActivity)
//    fun injectTo(mainActivity: MainActivity)
//    fun injectTo(chatActivity: ChatActivity)

}