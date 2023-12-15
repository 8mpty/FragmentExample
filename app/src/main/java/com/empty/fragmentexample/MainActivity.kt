package com.empty.fragmentexample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.Fragment
import androidx.preference.PreferenceManager
import com.empty.fragmentexample.Settings.SettingsActivity
import com.empty.fragmentexample.Settings.SettingsFragment
import com.empty.fragmentexample.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.toolbarLay
import kotlinx.android.synthetic.main.custom_toolbar.toolbar
import kotlinx.android.synthetic.main.fragment_web.view.webview
import kotlinx.android.synthetic.main.fragment_web.webview

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(toolbar)

        Thread.sleep(3000)
        installSplashScreen()
        toolbarLay.visibility = View.GONE

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
        return when (item.itemId) {
            R.id.settings -> {
                Intent(this, SettingsActivity::class.java).apply {
                    startActivity(this)
                }
                true
            }
            R.id.hd_toolbar -> {
                if(toolbarLay.visibility != View.GONE){
                    toolbarLay.visibility = View.GONE
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun replaceFragment(frag: Fragment){
        val fragManager = supportFragmentManager
        fragManager.beginTransaction().apply {
            replace(R.id.mainView, frag)
            commit()
        }
    }
    private fun loadDefaults(){
        val sharedPref = PreferenceManager.getDefaultSharedPreferences(baseContext)
        val default_screen = sharedPref.getString("default_main","")

        when(default_screen){
            "home" -> {
                binding.btmNavBar.selectedItemId = R.id.nav_home
                replaceFragment(HomeFragment())
            }
            "accounts" -> {
                binding.btmNavBar.selectedItemId = R.id.nav_acc
                replaceFragment(AccountFragment())
            }
            "info" -> {
                binding.btmNavBar.selectedItemId = R.id.nav_info
                replaceFragment(InfoFragment())
            }else -> {
                binding.btmNavBar.selectedItemId = R.id.nav_home
                replaceFragment(HomeFragment())
            }
        }
    }
}