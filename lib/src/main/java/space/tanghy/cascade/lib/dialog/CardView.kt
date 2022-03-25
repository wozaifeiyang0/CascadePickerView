package space.tanghy.cascade.lib.dialog

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import space.tanghy.cascade.lib.R

/**
 * ClassName: ItemView
 * Description:
 * @date: 2022/3/22 5:00 下午
 * @author: tanghy
 */
class CardView(
    layoutInflater: LayoutInflater,
    container: ViewGroup?,
    private val callback: (TabItem) -> Unit
) {

    val view: View = layoutInflater.inflate(R.layout.tanghy_view_cascade_dialog_content_layout, container, false)
    var tabItem: TabItem? = null

    private val recyclerView: RecyclerView = view.findViewById(R.id.list)

    init {
        recyclerView.layoutManager = LinearLayoutManager(container?.context)


    }

    fun bind(tabItem: TabItem) {
        this.tabItem = tabItem
        val itemAdapter = ItemAdapter(tabItem.data) {
            tabItem.currentItem = it
            callback.invoke(tabItem)
        }
        recyclerView.adapter = itemAdapter
    }

}