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
    private val callback: (TabItem) -> Unit
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
            callback.invoke(tabItem)
        })
    }

    override fun onBindViewHolder(holder: CascadeHolder, position: Int) {
        holder.bind(dataSet[position])
    }

    override fun getItemCount() = dataSet.size


}