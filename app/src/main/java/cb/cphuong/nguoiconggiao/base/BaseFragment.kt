package cb.cphuong.nguoiconggiao.base

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
        viewBase = inflater.inflate(LayoutId, container, false)
        injectDependence()
        onCreate(viewBase!!)
        return viewBase;
    }


    override fun onDetach() {
        super.onDetach()

    }

    override fun getView(): View? {
        return viewBase
    }

    override fun onDestroyView() {
        super.onDestroyView()
        onDestroyComposi()
    }

    var viewBase: View?=null


}//