package space.tanghy.cascade.lib.dialog

/**
 * ClassName: TabItem
 * Description:
 * @date: 2022/3/23 9:41 上午
 * @author: tanghy
 */
data class TabItem(
    var name: String = "请选择",
    val data: MutableList<Item>,
    val index: Int = 0,
    var currentItem: Item? = null
)