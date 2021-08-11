package com.mredrock.cyxbs.mine.page.stamp.shop.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.TypedValue
import android.view.*
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.mredrock.cyxbs.common.BaseApp.Companion.context
import com.mredrock.cyxbs.mine.databinding.MineDialogBuyProductNoneBinding

/**
 *@author ZhiQiang Tu
 *@time 2021/8/4  11:24
 *@signature 我们不明前路，却已在路上
 */

/*
val dialog = NoneProductDialog()
    .setContent("啊哦！手慢了！下次再来吧！")
    .setPositiveButtonText("2")
    .setPositiveButtonClick {
        Toast.makeText(requireContext(), "确认", Toast.LENGTH_SHORT).show()
    }
dialog.show(childFragmentManager,"dialog")
*/

class NoneProductDialog : DialogFragment() {
    //内部可更改的属性
    private var content: String? = null
    private var positiveText: String? = null
    private var positiveButtonClick: ((View) -> Unit)? = null

    //点击监听
    private var binding: MineDialogBuyProductNoneBinding? = null

    companion object{
        fun showDialog(supportFragment: FragmentManager,content:String,btnText:String,
                       func:()->Unit){
            val dialog = NoneProductDialog()
                    .setContent(content)
                    .setPositiveButtonText(btnText)
                    .setPositiveButtonClick {
                        func()
                    }
            dialog.show(supportFragment,"dialog")
        }
    }

    //创建了Dialog并设置了一些布局属性
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val params = dialog?.window?.attributes
        //居中
        params?.gravity = Gravity.CENTER
        //params?.windowAnimations = R.style.bottomSheet_animation
        dialog?.apply {
            window?.apply {
                attributes = params
                //无title
                requestFeature(Window.FEATURE_NO_TITLE)
                //背景为透明
                setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            }
            //点击背景取消
            setCancelable(true)
        }
        //获取Binding
        binding = MineDialogBuyProductNoneBinding.inflate(layoutInflater)
        return binding?.root
    }

    //设置布局的宽高
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

    //对布局的view事件和属性进行设置
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding?.btConfirm?.setOnClickListener {
            positiveButtonClick?.invoke(it)
            dismiss()
        }
        binding?.btConfirm?.text = positiveText
        binding?.tvContentNone?.text = content
    }

    //添加点击按钮的监听
    fun setPositiveButtonClick(click: (View) -> Unit): NoneProductDialog {
        this.positiveButtonClick = click
        return this
    }

    //设置内容
    fun setContent(content: String): NoneProductDialog {
        this.content = content
        return this
    }

    //设置确定按钮的Text
    fun setPositiveButtonText(positiveText: String): NoneProductDialog {
        this.positiveText = positiveText
        return this
    }
}