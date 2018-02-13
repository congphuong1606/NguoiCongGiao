package cb.cphuong.nguoiconggiao.common

import android.app.Activity
import android.app.ProgressDialog
import android.content.Context
import android.support.v7.app.AlertDialog
import cb.cphuong.nguoiconggiao.R
import vn.phuongcong.fchat.event.OnPhotoListener

/**
 * Created by Ominext on 2/12/2018.
 */
class DialogUtils(internal var dialog: ProgressDialog?, internal var activity: Activity) {


    fun showLoading() {
        if (dialog != null) {
            if (dialog!!.isShowing) dialog!!.dismiss()
            dialog!!.show()
            return
        }
        dialog = ProgressDialog
                .show(activity, "", Contans.LOADING, true)
    }
//
//
    fun hideLoading() {
        if (dialog != null && dialog!!.isShowing)
            dialog!!.dismiss()
    }
//    fun showInfor(context: Context, infor: String) {
//        val builder = AlertDialog.Builder(context)
//        builder.setMessage(infor)
//        builder.setIcon(R.drawable.logo_app)
//        builder.setCancelable(true)
//        val dialog = builder.create()
//        builder.setNegativeButton("ok") { dialogInterface, i -> dialog.dismiss() }
//        dialog.show()
//    }

    companion object {
//        fun showErorr(mContext: Context, msg: String) {
//            val builder = AlertDialog.Builder(mContext)
//            builder.setTitle("Thông báo")
//            builder.setMessage(msg)
//            builder.setIcon(R.drawable.logo_app)
//            builder.setCancelable(true)
//            val dialog = builder.create()
//            builder.setNegativeButton("ok") { dialogInterface, i -> dialog.dismiss() }
//            dialog.show()
//        }

        fun showDialogGetPhotoMenu(context: Context, mListener: OnPhotoListener) {
            val items = arrayOf<CharSequence>("Chọn hình", "Chụp hình")
            val builder = AlertDialog.Builder(context)
            builder.setTitle("Thêm đại diện")
            builder.setIcon(R.drawable.ic_no_image)
            builder.setItems(items) { dialogInterface, i ->
                if (items[i] == "Chọn hình") {
                    mListener.onChoosePhoto()
                } else if (items[i] == "Chụp hình") {
                    mListener.onTakePhoto()
                }
            }
            builder.setCancelable(true)
            val dialog = builder.create()
            builder.setNegativeButton("hủy") { dialogInterface, i -> dialog.dismiss() }
            dialog.show()
        }
    }
}