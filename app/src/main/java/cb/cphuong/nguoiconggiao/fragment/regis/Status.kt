package cb.cphuong.nguoiconggiao.fragment.regis

import android.content.Context
import android.content.SharedPreferences
import android.view.View
import cb.cphuong.nguoiconggiao.R
import cb.cphuong.nguoiconggiao.common.Contans
import cb.cphuong.nguoiconggiao.fragment.BaseFragment
import com.rw.keyboardlistener.KeyboardUtils
import kotlinx.android.synthetic.main.fragment_regis_status.view.*
import android.widget.Toast
import `in`.galaxyofandroid.spinerdialog.OnSpinerItemClick
import `in`.galaxyofandroid.spinerdialog.SpinnerDialog
import kotlinx.android.synthetic.main.fragment_regis_status.*


class Status : BaseFragment() {
    var dates: ArrayList<String> = ArrayList()
    var months: ArrayList<String> = ArrayList()
    var years: ArrayList<String> = ArrayList()
    var danhSachGiaoPhan: ArrayList<String> = ArrayList()


    override val LayoutId: Int
        get() = R.layout.fragment_regis_status

    override fun injectDependence() {

    }

    override fun onCreate(v: View) {
        var pef: SharedPreferences = context.getSharedPreferences(Contans.SPF_NAME, Context.MODE_PRIVATE)
        var edtPef: SharedPreferences.Editor = context.getSharedPreferences(Contans.SPF_NAME, Context.MODE_PRIVATE).edit()
        keyBoardListener(v)
        setList();
        onClick(v)

    }

    private fun setList() {
        dates.clear()
        months.clear()
        years.clear()
        danhSachGiaoPhan.clear()
        for (date: Int in 0..31) {
            dates.add(date.toString())
        }
        for (month: Int in 1..12) {
            months.add(month.toString())
        }
        for (month: Int in 1980..2050) {
            years.add(month.toString())
        }
        val dsGP = activity.resources.getString(R.string.danhSachGiaoPhanString)
        for (giaoPhan: String in dsGP.split(",")) {
            danhSachGiaoPhan.add(giaoPhan)
        }
    }

    private val gp: Int=-1;

    private fun onClick(v: View) {
        var tag: Int=-1;
        v.btnDate.setOnClickListener {
            tag=-1;
            showDialog(dates)
            spinnerDialog.showSpinerDialog()

        }
        v.btnMonth.setOnClickListener {
            tag=-1;
            showDialog(months)
            spinnerDialog.showSpinerDialog()

        }
        v.btnYear.setOnClickListener {
            tag=-1;
            showDialog(years)
            spinnerDialog.showSpinerDialog()

        }
        v.btnDiocese.setOnClickListener {
            tag=0;
            showDialog(danhSachGiaoPhan)
            spinnerDialog.showSpinerDialog()

        }
        if(gp!=-1){
            v.btnParish.setOnClickListener {
                tag=-1;
                if(btnDiocese.getText().toString().trim().equals("Giáo Phận")){
                    var dsGH: ArrayList<String> = getDsGh(btnDiocese.getText().toString().trim())
                    showDialog(dsGH)
                    spinnerDialog.showSpinerDialog()
                }
            }
        }

    }

    private fun getDsGh(giaophan: String): ArrayList<String> {
        var i=1;
        var ghs:String=""
        for(gp :String in danhSachGiaoPhan){
            if(giaophan.equals(gp)){
                ghs=activity.resources.getString(R.string.g1)
            }
        }
        return danhSachGiaoPhan;
    }

    private lateinit var spinnerDialog: SpinnerDialog

    fun showDialog(list: ArrayList<String>) {
        spinnerDialog = SpinnerDialog(activity, list, "Ngày sinh", R.style.DialogAnimations_SmileWindow, "Hủy")// With No Animation

        spinnerDialog.bindOnSpinerListener(OnSpinerItemClick { item, position ->
            Toast.makeText(activity, item + "  " + position + "", Toast.LENGTH_SHORT).show()
            selectedItems(item, "date")
        })

    }

    private fun selectedItems(item: String?, s: String) {

    }

    private fun keyBoardListener(view: View) {
        KeyboardUtils.addKeyboardToggleListener(activity) { isVisible ->
            if (isVisible) {
                view.viewK.setVisibility(View.GONE)
            } else {
                view.viewK.setVisibility(View.VISIBLE)
            }

        }
    }

    override fun onDestroyComposi() {

    }

    companion object {
        val instance: Status
            get() = Status()

    }

}
