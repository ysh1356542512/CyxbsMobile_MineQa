package com.mredrock.cyxbs.mine.page.stamp.shop.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.TypedValue
import android.view.*
import androidx.fragment.app.DialogFragment
import com.mredrock.cyxbs.mine.databinding.MineDialogBuyProductDoubleChooseBinding


/**
 *@author ZhiQiang Tu
 *@time 2021/8/4  9:39
 *@signature 我们不明前路，却已在路上
 */

/*
val dialog = DoubleCheckDialog()
               .setContent("确认要用100邮票兑换PM名片吗兑换成功!")
               .setNegativeButtonText("再想想")
               .setPositiveButtonText("好的")
               .setNegativeButtonClick {
                   Toast.makeText(requireContext(), "我还没悟透", Toast.LENGTH_SHORT).show()
               }
               .setPositiveButtonClick {
                   Toast.makeText(requireContext(), "我想通了", Toast.LENGTH_SHORT).show()
               }
           dialog.show(childFragmentManager,"dialog")
           */

class DoubleCheckDialog : DialogFragment() {
    //内部可更改的属性
    private var positiveText: String? = null
    private var negativeText: String? = null
    private var content: String? = null

    //点击监听
    private var positiveButtonClick: ((View) -> Unit)? = null
    private var negativeButtonClick: ((View) -> Unit)? = null

    private var binding: MineDialogBuyProductDoubleChooseBinding? = null

    //配置布局并返回view
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val params = dialog?.window?.attributes
        params?.gravity = Gravity.CENTER
        //params?.windowAnimations = R.style.bottomSheet_animation
        dialog?.apply {
            window?.apply {
                attributes = params
                requestFeature(Window.FEATURE_NO_TITLE)
                setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            }
            setCancelable(true)
        }

        binding = MineDialogBuyProductDoubleChooseBinding.inflate(layoutInflater)
        return binding?.root
    }

    //设置宽高
    override fun onResume() {
        super.onResume()
        val width = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, 255f,
            resources.displayMetrics
        )
        val height = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, 178F,
            resources.displayMetrics
        )
        dialog?.window?.setLayout(width.toInt(), height.toInt())
    }

    //传入事件和更改View的属性
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //事件
        binding?.btConfirm?.setOnClickListener{
            positiveButtonClick?.invoke(it)
            dismiss()
        }
        binding?.btConfuse?.setOnClickListener{
            negativeButtonClick?.invoke(it)
            dismiss()
        }
        //Text
        binding?.btConfuse?.text = negativeText
        binding?.btConfirm?.text = positiveText
        binding?.tvContent?.text = content
    }

    //确认按钮
    fun setPositiveButtonClick(click: (View) -> Unit): DoubleCheckDialog {
        this.positiveButtonClick = click
        return this
    }

    fun setPositiveButtonText(positiveText: String): DoubleCheckDialog {
        this.positiveText = positiveText
        return this
    }

    //内容
    fun setContent(content: String): DoubleCheckDialog {
        this.content = content
        return this
    }

    //取消按钮
    fun setNegativeButtonText(negativeText: String): DoubleCheckDialog {
        this.negativeText = negativeText
        return this
    }

    fun setNegativeButtonClick(click: (View) -> Unit): DoubleCheckDialog {
        this.negativeButtonClick = click
        return this
    }


}
