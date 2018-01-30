package cb.cphuong.nguoiconggiao.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cb.cphuong.nguoiconggiao.R

/**
 * Created by Ominext on 1/30/2018.
 */
abstract class  BaseFragment : Fragment() {
    protected abstract val LayoutId: Int
    protected abstract fun injectDependence()
    protected abstract fun onCreate(v: View)
    protected abstract fun onDestroyComposi()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        var v: View = inflater.inflate(LayoutId, container, false)
        injectDependence()
        onCreate(v)
        return v;
    }


    override fun onDetach() {
        super.onDetach()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        onDestroyComposi()
    }



}//