package space.tanghy.cascade.lib.dialog

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * ClassName: CascadeAdapter
 * Description:
 * @date: 2022/3/22 11:39 上午
 * @author: tanghy
 */
class CascadeAdapter(
    private val dataSet: MutableList<TabItem>,
    private var searchChildren:((Item)-> MutableList<Item>)? = null,
    private val callback: (TabItem, Boolean, Int) -> Unit
) :
    RecyclerView.Adapter<CascadeAdapter.CascadeHolder>() {

    class CascadeHolder internal constructor(private val cascadeView: CardView) :
        RecyclerView.ViewHolder(cascadeView.view) {

        internal fun bind(item: TabItem) {
            cascadeView.bind(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CascadeHolder {
        return CascadeHolder(CardView(LayoutInflater.from(parent.context), parent) { tabItem ->
            // 当前选择的列表
            val currentItem = tabItem.currentItem
            // 更改列表名称
            tabItem.name = currentItem!!.name
            // 清理该位置后面的所有数据
            val toMutableList = dataSet.filter {
                it.index <= tabItem.index
            }.toMutableList()
            dataSet.clear()
            dataSet.addAll(toMutableList)
            var tabIndex: Int = -1
            if (currentItem.children != null) {
                tabIndex = dataSet.size
                dataSet.add(
                    TabItem(
                        index = dataSet.size,
                        data = currentItem.children
                    ),
                )

            }else if (searchChildren != null) {
                val list = searchChildren!!.invoke(currentItem)
                if (list.isNotEmpty()) {
                    tabIndex = dataSet.size
                    dataSet.add(
                        TabItem(
                            index = dataSet.size,
                            data = list
                        ),
                    )
                }
            }
            notifyItemChanged(tabIndex)
            callback.invoke(tabItem, tabIndex > -1, tabIndex)
        })
    }

    override fun onBindViewHolder(holder: CascadeHolder, position: Int) {
        holder.bind(dataSet[position])
    }

    override fun getItemCount() = dataSet.size


}