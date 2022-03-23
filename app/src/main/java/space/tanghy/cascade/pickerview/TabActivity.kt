package space.tanghy.cascade.pickerview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class TabActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tablayout)
        val tabLayout:TabLayout = findViewById(R.id.tabs)
        val viewPager:ViewPager2 = findViewById(R.id.view_pager)
        viewPager.adapter = CardViewAdapter()
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = Card.DECK[position].toString()
        }.attach()

    }
}