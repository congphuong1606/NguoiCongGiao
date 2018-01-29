package cb.cphuong.nguoiconggiao.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import cb.cphuong.nguoiconggiao.R

class BlankFragment3 : Fragment() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_blank_fragment3, container, false)
    }



    companion object {


        // TODO: Rename and change types and number of parameters
        fun newInstance(): BlankFragment3 {
            val fragment = BlankFragment3()
            return fragment
        }
    }
}// Required empty public constructor
