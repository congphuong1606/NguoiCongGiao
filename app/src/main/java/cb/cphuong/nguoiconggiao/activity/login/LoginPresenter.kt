package vn.phuongcong.fchat

import android.content.ContentValues.TAG
import android.util.Log
import cb.cphuong.nguoiconggiao.common.Contans
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import vn.phuongcong.fchat.model.User

import vn.phuongcong.fchat.ui.login.LoginView
import javax.inject.Inject


/**
 * Created by Ominext on 10/11/2017.
 */

class LoginPresenter @Inject constructor(var fAuth: FirebaseAuth,
                                         var dbReference: DatabaseReference,
                                         var loginView: LoginView) {

    fun onSignIn(email: String, pass: String) {
        fAuth.signInWithEmailAndPassword(email, pass)
                .addOnSuccessListener {
                    checkEmailVerified();
                }
                .addOnFailureListener { exception: Exception ->
                    loginView.onRequestFailure(exception.toString())
                }
    }


    fun checkEmailVerified() {
        val user = fAuth.currentUser
        if (user != null) {
            getUserDatabase(user.uid)
        }else{
            loginView.onViriFail()
        }

    }

    private fun getUserDatabase(uid: String) {
        val databaseUser: DatabaseReference =
                dbReference.child(Contans.USERS_PATH).child(uid)
        databaseUser.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val user = dataSnapshot.getValue(User::class.java)
                loginView.onVerified(user)

            }

            override fun onCancelled(databaseError: DatabaseError) {
                loginView.onRequestFailure(databaseError.toString())
            }
        })
    }

    fun getEmail(nickName: String,pass: String) {

                dbReference.child(Contans.USERS_PATH).orderByChild("nickName").equalTo(nickName).addValueEventListener(object : ValueEventListener {


                    override fun onCancelled(p0: DatabaseError?) {
                        val a = p0!!.toException().toString()
                        Log.w(TAG, "onCancelled", p0!!.toException());

                    }

                    override fun onDataChange(p0: DataSnapshot?) {
                        val ab="a"
                        if(p0!!.value==null){
                            val ab="b"
                            loginView.onRequestFailure("tai khoan khong ton tai")
                        }else{
                            for (snapshot: DataSnapshot in p0!!.getChildren()) {
                                val name = snapshot.child("nickName").getValue(String::class.java)
                                if(name.equals(nickName)){
                                    val email = snapshot.child("email").getValue(String::class.java)
                                    login(email!!,pass)
                                }

                            }
                        }


                    }

                })



    }

    private fun login(email: String, pass: String) {
        fAuth.signInWithEmailAndPassword(email, pass)
                .addOnSuccessListener {
                    checkEmailVerified();
                }
                .addOnFailureListener { exception: Exception ->
                    loginView.onRequestFailure(exception.toString())
                }
    }

}



