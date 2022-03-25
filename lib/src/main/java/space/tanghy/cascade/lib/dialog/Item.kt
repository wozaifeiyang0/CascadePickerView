package space.tanghy.cascade.lib.dialog

/**
 * ClassName: Item
 * Description:
 * @date: 2022/3/22 9:48 上午
 * @author: tanghy
 */
data class Item(
    val name: String,
    val value: Any,
    val children: MutableList<Item>? = null,
    var isCheck:Boolean = false
)
