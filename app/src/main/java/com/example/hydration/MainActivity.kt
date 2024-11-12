package com.example.hydration

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class MainActivity : AppCompatActivity() {

    private lateinit var waterViewPager: ViewPager2
    private lateinit var waterTabLayout: TabLayout

    private val waterViewModel by lazy {
        WaterViewModel.WaterViewModelFactory((application as HydrationApplication).repository) // WaterViewModelFactory is given an application context.  In this case a HydrationApplication.  Cast of a child class to the superclass.
            .create(WaterViewModel::class.java)  // gives MainActivity a reference to the WaterViewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        waterViewPager = findViewById(R.id.water_view_pager)
        waterTabLayout = findViewById(R.id.water_days_tab_layout)

        val days = resources.getStringArray(R.array.days)

        val pagerAdapter = WaterViewPagerAdapter(this, days)
        waterViewPager.adapter = pagerAdapter

        TabLayoutMediator(waterTabLayout, waterViewPager) { tab, position ->
            tab.text = days[position]
        }.attach()

//        val tuesday = WaterRecord("Tuesday", 4)
//        waterViewModel.insertNewRecord(tuesday)
//
//        val wednesday = WaterRecord("Wednesday", 2)
//        waterViewModel.insertNewRecord(wednesday)

        waterViewModel.allRecords.observe(this) { records ->
            Log.d("MAIN_ACTIVITY", "$records")
        }


//        supportFragmentManager.beginTransaction()
//            .add(R.id.content, HydrationFragment.newInstance("Tuesday"))
//            .commit()

    }
}