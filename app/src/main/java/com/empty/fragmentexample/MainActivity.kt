package com.empty.fragmentexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.empty.fragmentexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btmNavBar.selectedItemId = R.id.nav_acc
        replaceFragment(AccountFragment())


        binding.btmNavBar.setOnItemSelectedListener {
            if(it.itemId == R.id.nav_home){
                replaceFragment(HomeFragment())
            }
            if(it.itemId == R.id.nav_acc){
                replaceFragment(AccountFragment())
            }
            if(it.itemId == R.id.nav_info){
                replaceFragment(InfoFragment())
            }
            true
        }
    }
    private fun replaceFragment(frag: Fragment){
        val fragManager = supportFragmentManager
        val fragTransaction = fragManager.beginTransaction()
        fragTransaction.replace(R.id.mainView, frag)
        fragTransaction.commit()
    }
}