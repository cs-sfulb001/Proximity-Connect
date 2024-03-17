package com.example.proximity_connect

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView

class homeMain : AppCompatActivity() {
    lateinit var bottomNav : BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_main)
        bottomNav = findViewById(R.id.bottomNavigationView)


        val homefrag = homefra()
        val blutofrag = blutofrag()
        val transfrag = transfrag()
        val groupfrag = groupfra()
        val calendfrag = calendfrag()

        setCurrentFragment(homefrag)


//        BottomNavigationView.OnNavigationItemSelectedListener {
//        NavigationBarView.OnItemReselectedListener {
//        bnv.setOnItemSelectedListener { item ->
        bottomNav.setOnItemSelectedListener {
        when(it.itemId){
            R.id.ihome -> setCurrentFragment(homefrag)
            R.id.ibluto -> setCurrentFragment(blutofrag)
            R.id.itrans -> setCurrentFragment(transfrag)
            R.id.igroup -> setCurrentFragment(groupfrag)
            R.id.iCalen -> setCurrentFragment(calendfrag)
}
            true
        }

    }

    private fun setCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment, fragment)
            commit()
        }


}