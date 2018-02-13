package cb.cphuong.nguoiconggiao.fragment.regis

import android.content.Context
import android.content.SharedPreferences
import android.view.View
import cb.cphuong.nguoiconggiao.R
import cb.cphuong.nguoiconggiao.common.Contans
import cb.cphuong.nguoiconggiao.base.BaseFragment
import com.rw.keyboardlistener.KeyboardUtils
import kotlinx.android.synthetic.main.fragment_regis_status.view.*
import android.widget.Toast
import `in`.galaxyofandroid.spinerdialog.OnSpinerItemClick
import `in`.galaxyofandroid.spinerdialog.SpinnerDialog
import android.app.Activity
import android.support.v4.app.Fragment
import cb.cphuong.nguoiconggiao.activity.regis.RegisActivity
import kotlinx.android.synthetic.main.fragment_regis_status.*


class Status : BaseFragment() {
    var dates: ArrayList<String> = ArrayList()
    var months: ArrayList<String> = ArrayList()
    var years: ArrayList<String> = ArrayList()
    var danhSachGiaoPhan: ArrayList<String> = ArrayList()
    var danhSachGiaoHat: ArrayList<String> = ArrayList()
    var namnu: ArrayList<String> = ArrayList()
    var curent:Int = 0
    var tenThanh:String = ""
    var ngaySinh:String = ""
    var thangSinh:String = ""
    var namSinh:String = ""
    var giaoPhan:String = ""
    var giaoHat:String = ""
    var gioiTinh:String = ""

    override val LayoutId: Int
        get() = R.layout.fragment_regis_status

    override fun injectDependence() {

    }

    override fun onCreate(v: View) {
       setText(v)
        keyBoardListener(v)
        setList();
        onClick(v)

    }

    private var prefs: SharedPreferences? = null

    private fun setText(view: View) {
        prefs = context.getSharedPreferences(Contans.SPF_NAME, Context.MODE_PRIVATE)
        view.edtChristian.setText(prefs!!.getString(Contans.SPF_REGIS_CHRISTIAN, ""))
        if(((prefs!!.getString(Contans.SPF_REGIS_BIRTHDAY, "")).split(" ").size)>2){
            view.btnDate.setText((prefs!!.getString(Contans.SPF_REGIS_BIRTHDAY, "")).split(" ")[0])
            view.btnMonth.setText((prefs!!.getString(Contans.SPF_REGIS_BIRTHDAY, "")).split(" ")[1])
            view.btnYear.setText((prefs!!.getString(Contans.SPF_REGIS_BIRTHDAY, "")).split(" ")[2])
        }
        view.btnDiocese.setText(prefs!!.getString(Contans.SPF_REGIS_DIOCESE, ""))
        view.btnParish.setText(prefs!!.getString(Contans.SPF_REGIS_PARISH, ""))
        view.btnGenders.setText(prefs!!.getString(Contans.SPF_REGIS_GENDERS, ""))
    }
    private fun setList() {
        dates.clear()
        months.clear()
        years.clear()
        namnu.clear()
        namnu.add("Nam")
        namnu.add("Nữ")
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
            danhSachGiaoPhan.add(giaoPhan.trim())
        }
    }

    private val gp: Int=-1;

    private fun onClick(v: View) {
        v.btnDate.setOnClickListener {
            curent=1;
            showDialog(dates)
            spinnerDialog.showSpinerDialog()

        }
        v.btnMonth.setOnClickListener {
            curent=2;
            showDialog(months)
            spinnerDialog.showSpinerDialog()

        }
        v.btnYear.setOnClickListener {
            curent=3;
            showDialog(years)
            spinnerDialog.showSpinerDialog()

        }
        v.btnDiocese.setOnClickListener {
            curent=4;
            showDialog(danhSachGiaoPhan)
            spinnerDialog.showSpinerDialog()

        }
        v.btnParish.setOnClickListener {
                curent=5;
                if(!btnDiocese.getText().toString().trim().equals("Giáo Phận")){
                    val dsGH: ArrayList<String> = getDsGh(btnDiocese.getText().toString().trim())
                    if(!dsGH.isEmpty()){
                        showDialog(dsGH)
                        spinnerDialog.showSpinerDialog()
                    }

                }
            }

        v.btnGenders.setOnClickListener {
            curent=6;
            showDialog(namnu)
            spinnerDialog.showSpinerDialog()

        }

    }

    private fun getDsGh(giaophan: String): ArrayList<String> {
        var i=1;
        var id:Int?=null
        var ghs:String=""
        for(gp :String in danhSachGiaoPhan){

            if(giaophan.equals(gp.trim())){
                if(i==1){
                   id=R.string.g1
                }
                if(i==2){
                    id=R.string.g2
                }
                if(i==3){
                    id=R.string.g3
                }
                if(i==4){
                    id=R.string.g4
                }
                if(i==5){
                    id=R.string.g5
                }
                if(i==6){
                    id=R.string.g6
                }
                if(i==7){
                    id=R.string.g7
                }
                if(i==8){
                    id=R.string.g8
                }
                if(i==9){
                    id=R.string.g9
                }
                if(i==10){
                    id=R.string.g10
                }
                if(i==11){
                    id=R.string.g11
                }
                if(i==12){
                    id=R.string.g12
                }
                if(i==13){
                    id=R.string.g13
                }
                if(i==14){
                    id=R.string.g14
                }
                if(i==15){
                    id=R.string.g15
                }
                if(i==16){
                    id=R.string.g16
                }
                ghs= activity.resources.getString(id!!)
            }
            i++
        }
        danhSachGiaoHat.clear()
        if(!ghs.equals("")){
            for (gh: String in ghs.split(",")) {
                danhSachGiaoHat.add(gh.trim())
            }

        }
        return danhSachGiaoHat
    }

    private lateinit var spinnerDialog: SpinnerDialog

    fun showDialog(list: ArrayList<String>) {
        spinnerDialog = SpinnerDialog(activity, list, "Tìm Nhanh", R.style.DialogAnimations_SmileWindow, "Hủy")// With No Animation

        spinnerDialog.bindOnSpinerListener(OnSpinerItemClick { item, position ->
            if(curent==1){
                btnDate.setText(item.toString().trim())
                ngaySinh=item.toString().trim()
                curent=0;
            }
            if(curent==2){
                btnMonth.setText(item.toString().trim())
                thangSinh=item.toString().trim()
                curent=0;
            }
            if(curent==3){
                btnYear.setText(item.toString().trim())
                namSinh=item.toString().trim()
                curent=0;
            }
            if(curent==4){
                btnDiocese.setText(item.toString().trim())
                giaoPhan=item.toString().trim()
                curent=0;
            }
            if(curent==5){
                btnParish.setText(item.toString().trim())
                giaoHat=item.toString().trim()
                curent=0;
            }
            if(curent==6){
                btnGenders.setText(item.toString().trim())
                gioiTinh=item.toString().trim()
                curent=0;
            }

        })

    }



    private fun keyBoardListener(view: View) {
        KeyboardUtils.addKeyboardToggleListener(activity) { isVisible ->
            if (isVisible) {

            } else {

            }

        }
    }

    override fun onDestroyComposi() {

    }

    companion object {
        val instance: Status
            get() = Status()

    }

    fun savePref(regisActivity: RegisActivity, fB: Fragment):Boolean {
        tenThanh=fB.view!!.edtChristian.getText().toString().trim()
        ngaySinh=fB.view!!.btnDate.getText().toString().trim()
        thangSinh=fB.view!!.btnMonth.getText().toString().trim()
        namSinh=fB.view!!.btnYear.getText().toString().trim()
        giaoPhan=fB.view!!.btnDiocese.getText().toString().trim()
        giaoHat=fB.view!!.btnParish.getText().toString().trim()
        gioiTinh=fB.view!!.btnGenders.getText().toString().trim()
         if(!tenThanh.equals("")){
             if(!ngaySinh.equals("")){
                 if(!thangSinh.equals("")){
                     if(!namSinh.equals("")){
                         if(!giaoPhan.equals("")){
                             if(!giaoHat.equals("")){
                                 if(!gioiTinh.equals("")){
                                     setPref(fB.view!!,regisActivity)
                                     return true
                                 }else{
                                     showToast(regisActivity,"Chưa nhập giới tính")
                                     return false
                                 }
                             }else{
                                 showToast(regisActivity,"Bạn cần nhập giáo hạt")
                                 return false
                             }
                         }else{

                             showToast(regisActivity,"Bạn chưa nhập giáo phận của mình")
                             return false
                         }
                     }else{
                         showToast(regisActivity,"Ngày sinh chưa đúng")
                         return false
                     }
                 }else{
                     showToast(regisActivity,"Ngày sinh chưa đúng")
                     return false
                 }
             }else{
                 showToast(regisActivity,"Ngày sinh chưa đúng")
                 return false
             }
         }else{
             showToast(regisActivity,"Bạn không thể thiếu tên thánh")
             return false
         }
    }

    private fun showToast(activity: Activity,s: String) {
        Toast.makeText(activity,s,Toast.LENGTH_LONG).show()
    }

    private fun setPref(v: View, activity: Activity) {
        val editor = activity.getSharedPreferences(Contans.SPF_NAME, Context.MODE_PRIVATE).edit()
        editor.putString(Contans.SPF_REGIS_CHRISTIAN, tenThanh)
        editor.putString(Contans.SPF_REGIS_BIRTHDAY, ngaySinh+" "+thangSinh+" "+namSinh)
        editor.putString(Contans.SPF_REGIS_DIOCESE, giaoPhan)
        editor.putString(Contans.SPF_REGIS_PARISH, giaoHat)
        editor.putString(Contans.SPF_REGIS_GENDERS, gioiTinh)
        editor.apply()
    }

}
