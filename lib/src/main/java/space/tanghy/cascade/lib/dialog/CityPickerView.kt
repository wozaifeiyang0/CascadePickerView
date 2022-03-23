package space.tanghy.cascade.lib.dialog

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException
import java.nio.charset.Charset

/**
 * ClassName: CityPickerView
 * Description:
 * @date: 2022/3/23 7:22 下午
 * @author: tanghy
 */
class CityPickerView(context: Context):CascadePickerView(context) {

    init {
        setSearchChildren{
            parseJsonFile(it.value.toString())
        }
        setData(parseJsonFile("province"))
    }

    fun parseJsonFile(fileName: String): MutableList<Item>{
        return try {
            val input = context.assets.open("${fileName}.json")
            val size = input.available()
            val buffer = ByteArray(size)
            input.read(buffer)
            input.close()
            val jsonStr = String(buffer, Charset.forName("utf-8"))

            Gson().fromJson(jsonStr, object : TypeToken<MutableList<Item>>() {}.type)
        } catch (e: IOException) {
            mutableListOf()
        }

    }

}