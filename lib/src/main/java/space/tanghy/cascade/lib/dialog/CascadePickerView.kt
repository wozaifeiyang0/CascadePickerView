package space.tanghy.cascade.lib.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.WindowManager
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import space.tanghy.cascade.lib.R
import space.tanghy.cascade.lib.utils.ScreenUtil

/**
 * ClassName: CascadePickerView
 * Description:
 * @date: 2022/3/22 9:00 上午
 * @author: tanghy
 */
open class CascadePickerView(context: Context) : Dialog(context, R.style.dialogTheme) {

    private var tabLayout: TabLayout? = null
    private var viewPager: ViewPager2? = null
    private var data: MutableList<Item> = mutableListOf()
    private var tabData: MutableList<TabItem> = mutableListOf()
    private var selectLastListener: ((TabItem, List<Item>) -> Unit)? = null
    private var selectItemListener: ((TabItem) -> Unit)? = null
    private var searchChildren: ((Item) -> MutableList<Item>)? = null
    private var displayLevel: Int = Int.MAX_VALUE

    // 已经选择的数据
    var selectItems: List<Item>? = null

    // 如果选择最后一个节点时是否关闭dialog，默认关闭
    var lastItemCloseDialog = true

    constructor(
        context: Context,
        data: MutableList<Item>,
        searchChildren: ((Item) -> MutableList<Item>)? = null
    ) : this(context) {
        this.data = data
        this.searchChildren = searchChildren
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 设置布局文件
        setContentView(R.layout.dialog_layout)
        // 设置底部显示
        window?.setGravity(Gravity.BOTTOM)
        // 设置动画
        window?.setWindowAnimations(R.style.dialog_animStyle)
        // 获取屏幕高度
        val height = ScreenUtil.getHeight(context)
        // 设置对话框大小
        window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            height / 3
        )
        // 初始化组件
        initView()
        // 初始化数据
        setData(data)

    }

    private fun initView() {
        tabLayout = findViewById(R.id.auto_tab_layout)
        tabLayout?.isTabIndicatorFullWidth = false
        viewPager = findViewById(R.id.pager)
        viewPager?.requestDisallowInterceptTouchEvent(true)
    }

    fun setGravity(gravity: Int): CascadePickerView {
        window?.setGravity(gravity)
        return this
    }

    /**
     * 设置选择监听当子列表不存在时出发事件
     */
    fun setSelectLastListener(selectLastListener: ((TabItem, List<Item>) -> Unit)?) {
        this.selectLastListener = selectLastListener
    }

    /**
     * 设置选择监听当子列表不存在时出发事件
     */
    fun setSelectItemListener(selectItemListener: ((TabItem) -> Unit)?) {
        this.selectItemListener = selectItemListener
    }

    /**
     * 设置搜索子列表函数，子列表可通过该方法返回
     */
    fun setSearchChildren(searchChildren: ((Item) -> MutableList<Item>)? = null) {
        this.searchChildren = searchChildren
    }

    fun setData(dataJson: String) {
        val dataList = Gson().fromJson<MutableList<Item>>(
            dataJson,
            object : TypeToken<MutableList<Item>>() {}.type
        )
        setData(dataList)
    }

    fun setData(
        data: MutableList<Item>
    ): CascadePickerView {
        this.data = data
        if (tabLayout != null) {
            // 移除所有tab
            tabLayout?.removeAllTabs()
            tabData.clear()
            tabData.add(TabItem("请选择", data))
            val adapter = CascadeAdapter(tabData) { tabItem ->
                val tabIndex = changeTabItem(tabItem)
                handlerOnClick(tabItem, tabIndex)
            }
            viewPager?.adapter = adapter
            // 管理tab和viewPager
            TabLayoutMediator(tabLayout!!, viewPager!!) { tab, position ->
                tab.text = tabData[position].name
            }.attach()
        }
        return this
    }

    /**
     * 设置级联显示级别
     */
    fun setLevel(level: Int) {
        this.displayLevel = level
    }

    private fun handlerOnClick(tabItem: TabItem, tabIndex: Int) {
        //  tabIndex大于-1，代表是否创建了新的tab页，如果是，tabIndex代表位置
        selectItems =
            tabData.filter { it.currentItem != null }.map { it.currentItem!! }.toList()
        // 选择item的监听
        selectItemListener?.invoke(tabItem)
        if (tabIndex > -1) {
            // 跳转到新页面上
            viewPager?.setCurrentItem(tabIndex, true)
        } else {
            // 最终结果监听
            selectLastListener?.invoke(tabItem, selectItems!!)
            if (lastItemCloseDialog)
                hide()
        }
    }

    /**
     * 点击选项切换tab的处理方法
     */
    private fun changeTabItem(tabItem: TabItem): Int {
        // 当前选择的列表
        val currentItem = tabItem.currentItem
        // 更改列表名称
        tabItem.name = currentItem!!.name
        // 清理该位置后面的所有数据
        val toMutableList = tabData.filter {
            it.index <= tabItem.index
        }.toMutableList()
        // 原数据的长度
        val oldLen = tabData.size
        tabData.clear()
        tabData.addAll(toMutableList)
        // 关键代码当数据变化时刷新adapter
        viewPager?.adapter?.notifyItemRangeRemoved(tabItem.index + 1, oldLen - tabData.size)

        var tabIndex: Int = -1
        if (currentItem.children != null && tabData.size < displayLevel) {
            tabIndex = tabData.size
            tabData.add(
                TabItem(
                    index = tabData.size,
                    data = currentItem.children
                ),
            )

        } else if (searchChildren != null && tabData.size < displayLevel) {
            val list = searchChildren!!.invoke(currentItem)
            if (list.isNotEmpty()) {
                tabIndex = tabData.size
                tabData.add(
                    TabItem(
                        index = tabData.size,
                        data = list
                    ),
                )
            }
        }

        if (tabIndex > -1)
            viewPager?.adapter?.notifyItemChanged(tabIndex)

        return tabIndex
    }


}