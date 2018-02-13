package vn.phuongcong.fchat.model

import java.io.Serializable


/**
 * Created by Ominext on 10/13/2017.
 */
class User(val id: String="",var nickName: String="", var email: String="", var avatar: String=""
           , var christian: String="", var birthDayprefs: String="", var diocese: String="",var parish: String="", var genders: String="",var timeStamp: Long =0) :Serializable{

}
