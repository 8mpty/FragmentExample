package com.empty.fragmentexample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.preference.PreferenceManager
import com.empty.fragmentexample.Settings.SettingsActivity
import com.empty.fragmentexample.Settings.SettingsFragment
import com.empty.fragmentexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadDefaults()


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
            if(it.itemId == R.id.nav_web){
                replaceFragment(WebFragment())
            }
            true
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.settings -> {
                val intent = Intent(this, SettingsActivity::class.java)
                startActivity(intent)
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    private fun replaceFragment(frag: Fragment){
        val fragManager = supportFragmentManager
        val fragTransaction = fragManager.beginTransaction()
        fragTransaction.replace(R.id.mainView, frag)
        fragTransaction.commit()
    }
    private fun loadDefaults(){
        val sharedPref = PreferenceManager.getDefaultSharedPreferences(baseContext)

        val default_screen = sharedPref.getString("default_main","")

        if(default_screen == "home"){
            binding.btmNavBar.selectedItemId = R.id.nav_home
            replaceFragment(HomeFragment())
        }else if(default_screen == "accounts"){
            binding.btmNavBar.selectedItemId = R.id.nav_acc
            replaceFragment(AccountFragment())
        }else if(default_screen == "info"){
            binding.btmNavBar.selectedItemId = R.id.nav_info
            replaceFragment(InfoFragment())
        }
    }
}