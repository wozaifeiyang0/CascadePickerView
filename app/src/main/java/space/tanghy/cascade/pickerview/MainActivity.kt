package space.tanghy.cascade.pickerview

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import space.tanghy.cascade.lib.dialog.CityPickerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val showDialogBtn = findViewById<Button>(R.id.showDialogBtn)
        val showDialogTxt = findViewById<AppCompatTextView>(R.id.showDialogTxt)

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