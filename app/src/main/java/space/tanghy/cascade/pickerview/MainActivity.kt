package space.tanghy.cascade.pickerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.widget.AppCompatTextView
import space.tanghy.cascade.lib.dialog.CascadePickerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val showDialogBtn = findViewById<Button>(R.id.showDialogBtn)
        val showDialogTxt = findViewById<AppCompatTextView>(R.id.showDialogTxt)
        val dialog = CascadePickerView(
            this,
        )
        dialog.setData("[\n" +
                "  {\n" +
                "    \"value\": \"1401\",\n" +
                "    \"name\": \"太原市\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"value\": \"1402\",\n" +
                "    \"name\": \"大同市\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"value\": \"1403\",\n" +
                "    \"name\": \"阳泉市\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"value\": \"1404\",\n" +
                "    \"name\": \"长治市\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"value\": \"1405\",\n" +
                "    \"name\": \"晋城市\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"value\": \"1406\",\n" +
                "    \"name\": \"朔州市\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"value\": \"1407\",\n" +
                "    \"name\": \"晋中市\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"value\": \"1408\",\n" +
                "    \"name\": \"运城市\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"value\": \"1409\",\n" +
                "    \"name\": \"忻州市\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"value\": \"1410\",\n" +
                "    \"name\": \"临汾市\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"value\": \"1411\",\n" +
                "    \"name\": \"吕梁市\"\n" +
                "  }\n" +
                "]")
//        val dialog = CityPickerView(this)

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