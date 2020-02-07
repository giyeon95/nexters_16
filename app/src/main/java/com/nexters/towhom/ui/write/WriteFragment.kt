package com.nexters.towhom.ui.write

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.nexters.towhom.R
import com.nexters.towhom.core.BindingFragment
import com.nexters.towhom.databinding.FragmentWriteBinding
import org.koin.androidx.viewmodel.ext.android.getViewModel

class WriteFragment : BindingFragment<FragmentWriteBinding>() {
    override fun getLayoutResId(): Int = R.layout.fragment_write

    private val viewPager by lazy { binding.contentVp }
    private val indicator by lazy { binding.contentIndicator }
    /**sticker**/
    /**sticker_add**/
    private val mstickerLinear by lazy { binding.stickerLinear }
    private val add_sticker_btn by lazy { binding.stickerBtn }
    //val stickerImage = LayoutInflater.from(context).inflate(R.layout.sticker,mstickerLinear,false)

    /**sticker_drag**/
    private val mainLayout by lazy { binding.mainConstraint }

    /** Test Button */
    private val testBt by lazy { binding.testBtn }

    var testList = mutableListOf<String>("list1", "list2", "list3", "list4")

    private var ACTIVATE_PAGE_NUM = 0


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this
        binding.viewModel = getViewModel()

        viewPager.adapter = ContentAdapter(testList)
        indicator.createDotPanel(testList.size, R.drawable.indicator_dot_off, R.drawable.indicator_dot_on, 0)


    }

    override fun bindingView() {
    }

    override fun bindingEventListener() {
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                ACTIVATE_PAGE_NUM = position
                indicator.selectDot(position)
            }
        })

        testBt.setOnClickListener {
            testList.add("list5")

            (viewPager.adapter as ContentAdapter).apply {
                setUpdateItems(testList)
                notifyDataSetChanged()
            }

            indicator.createDotPanel(testList.size, R.drawable.indicator_dot_off, R.drawable.indicator_dot_on, ACTIVATE_PAGE_NUM)
        }

        add_sticker_btn.setOnClickListener {
           // mstickerLinear.addView(stickerImage)
        }


    }

    override fun bindingObserver() {
    }

}