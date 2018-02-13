package cb.cphuong.nguoiconggiao.fragment.regis


import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.provider.MediaStore
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.View

import cb.cphuong.nguoiconggiao.R
import cb.cphuong.nguoiconggiao.activity.regis.RegisActivity
import cb.cphuong.nguoiconggiao.common.Contans
import cb.cphuong.nguoiconggiao.common.DialogUtils
import cb.cphuong.nguoiconggiao.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_add_avatar.*
import vn.phuongcong.fchat.event.OnPhotoListener
import java.io.ByteArrayOutputStream
import android.util.Base64

import java.nio.charset.Charset
import android.R.array
import java.util.*


class AddAvatar : BaseFragment(), OnPhotoListener {


    override val LayoutId: Int
        get() = R.layout.fragment_add_avatar

    override fun injectDependence() {

    }

    override fun onCreate(v: View) {
        btnAddAvatar.setOnClickListener {
            DialogUtils.showDialogGetPhotoMenu(context, this)
        }

    }

    override fun onDestroyComposi() {

    }

    companion object {
        val instance: AddAvatar
            get() = AddAvatar()

    }

    private fun requestStoragePermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity,
                        android.Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
        }
        ActivityCompat.requestPermissions(activity,
                arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE),
                Contans.REQUEST_WRITE_STORAGE)
    }

    private fun isReadStorageAllowed(): Boolean {
        val result = ContextCompat.checkSelfPermission(
                context, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
        return if (result == PackageManager.PERMISSION_GRANTED) true else false
    }

    override fun onChoosePhoto() {
        if (isReadStorageAllowed()) {
            startActivityForResult(Intent(Intent.ACTION_PICK,
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI), Contans.PIC_CHOOSE_CODE)
        } else {
            requestStoragePermission()
        }

    }

    override fun onTakePhoto() {
        startActivityForResult(Intent(MediaStore.ACTION_IMAGE_CAPTURE), Contans.PIC_TAKE_CODE)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)
        var bitmap: Bitmap? = null
        if (resultCode == AppCompatActivity.RESULT_OK) {
            when (requestCode) {
                Contans.PIC_TAKE_CODE -> bitmap = (data.getExtras())!!.get("data") as Bitmap
                Contans.PIC_CHOOSE_CODE -> bitmap = MediaStore.Images.Media.getBitmap(activity.getContentResolver(), data.data)
            }
        }
        if (bitmap != null) {
            val imgB = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 60, imgB)
//            Glide.with(context).load(bitmap).asBitmap().into(imvAddAvatar)
            imvAddAvatar.setImageBitmap(bitmap)
            val newAvatar = imgB.toByteArray();
            RegisActivity().setAvatar(newAvatar)
//            val string = newAvatar.toString(Charset.defaultCharset())
            val saveThis = Base64.encodeToString(newAvatar, Base64.DEFAULT)
            val editor = activity.getSharedPreferences(Contans.SPF_NAME, Context.MODE_PRIVATE).edit()
            editor.putString(Contans.SPF_REGIS_AVATAR, saveThis)
                    .apply()

        }
    }
}
