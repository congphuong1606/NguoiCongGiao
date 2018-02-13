package cb.cphuong.nguoiconggiao.activity.regis

import cb.cphuong.nguoiconggiao.common.Contans
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.storage.StorageReference
import vn.phuongcong.fchat.model.User
import javax.inject.Inject

/**
 * Created by Ominext on 2/12/2018.
 */
class RegisPresenter @Inject constructor(var regisView: RegisView,
                                         var firebaseAuth: FirebaseAuth,
                                         var storage: StorageReference,
                                         var databaseReference: DatabaseReference){
    fun onSignUp(email: String, pass: String) {
        firebaseAuth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener { task: Task<AuthResult> ->
                    if (task.isSuccessful) {
                        val firebaseUser: FirebaseUser = task.result.user
                        firebaseUser.let { firebaseUser ->
                            sendVeriEmail(firebaseUser)
                        }
                    }
                }.addOnFailureListener { exception ->
                    regisView.onRequestFailure(exception.toString())
                }
    }

    private fun sendVeriEmail(firebaseUser: FirebaseUser) {

        val user = firebaseAuth.getCurrentUser()
        if (user != null) {
            user.sendEmailVerification().addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    regisView.onSignUpSuccessful()
                } else {
                    regisView.onRequestFailure("lỗi")
                }
            }
        }
    }


    fun onCreatUserDatabase(email: String, user: User) {
        val id = firebaseAuth.currentUser!!.uid
        val userName = (email.split("@".toRegex()))[0]
        val currentUser =User(id,user.nickName,user.email,user.avatar,user.christian,user.birthDayprefs,user.diocese,user.parish,user.genders,System.currentTimeMillis())
        databaseReference.child(Contans.USERS_PATH).child(id).setValue(currentUser).addOnSuccessListener {
            regisView.onCreateUserSuccessful()
        }

    }


    fun updateAvatar(bytes: ByteArray) {
        firebaseAuth.signInAnonymously().addOnCompleteListener { task: Task<AuthResult> ->
            if(task.isSuccessful){
                val picName = System.currentTimeMillis().toString()
                val mSto = storage.child(Contans.IMAGE_PATH).child(picName + ".jpg")
                val uploadTask = mSto.putBytes(bytes)
                uploadTask.addOnSuccessListener { taskSnapshot ->
                    val avatarUrl = taskSnapshot.downloadUrl.toString()
                    firebaseAuth.signOut()
                    regisView.updateAvatarSuccess(avatarUrl)
                }.addOnFailureListener{ exception ->
                            regisView.onRequestFailure(exception.toString())
                        }
            }else{
                val string ="aaaa"
                regisView.onRequestFailure(string)
            }
        }


    }

}