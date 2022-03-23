package space.tanghy.cascade.lib.dialog

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import space.tanghy.cascade.lib.R

/**
 * ClassName: CascadeAdapter
 * Description:
 * @date: 2022/3/22 11:39 上午
 * @author: tanghy
 */
class ItemAdapter(var dataSet: MutableList<Item>, private val callback: (Item) -> Unit) :
    RecyclerView.Adapter<ItemAdapter.ItemHolder>() {
    var currentItemView:ItemView? = null

    class ItemHolder internal constructor(val cascadeView: ItemView) :
        RecyclerView.ViewHolder(cascadeView.view) {
        fun bind(item: Item) {
            cascadeView.bind(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return ItemHolder(ItemView(view))
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bind(dataSet[position])
        val cascadeView = holder.cascadeView
        cascadeView.view.setOnClickListener {
            cascadeView.check()
            currentItemView?.check()
            currentItemView = cascadeView
            callback.invoke(dataSet[position])
        }
    }

    override fun getItemCount() = dataSet.size


}