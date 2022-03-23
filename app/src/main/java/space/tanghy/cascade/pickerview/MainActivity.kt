package space.tanghy.cascade.pickerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.widget.AppCompatTextView
import space.tanghy.cascade.lib.dialog.CascadePickerView
import space.tanghy.cascade.lib.dialog.CityPickerView
import space.tanghy.cascade.lib.dialog.Item

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val showDialogBtn = findViewById<Button>(R.id.showDialogBtn)
        val showDialogTxt = findViewById<AppCompatTextView>(R.id.showDialogTxt)
//        val dialog = CascadePickerView(
//            this,
//            mutableListOf(
//                Item(
//                    "北京", "北京", mutableListOf(
//                        Item("朝阳区", "朝阳区"),
//                        Item("东城区", "东城区"),
//                        Item("西城区", "西城区"),
//                    )
//                ),
//                Item("天津市", "天津市"),
//                Item("河北省", "河北省"),
//                Item("山西省", "山西省"),
//                Item("内蒙古", "内蒙古"),
//                Item("辽宁省", "辽宁省"),
//                Item("吉林省", "吉林省"),
//                Item("黑龙江省", "黑龙江省"),
//                Item("上海市", "上海市"),
//            )
//        )
        val dialog = CityPickerView(this)

        dialog.lastItemCloseDialog = false

        showDialogBtn.setOnClickListener {
            dialog.show()
        }

        dialog.setSelectItemListener { tabItem ->
            val txt = dialog.selectItems?.map { it.name }?.joinToString(",")
            showDialogTxt.text = txt
        }
    }
}