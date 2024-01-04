package com.empty.fragmentexample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.Fragment
import androidx.preference.PreferenceManager
import com.empty.fragmentexample.Fragments.AccountFragment
import com.empty.fragmentexample.Fragments.HomeFragment
import com.empty.fragmentexample.Fragments.InfoFragment
import com.empty.fragmentexample.Settings.SettingsActivity
import com.empty.fragmentexample.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.toolbarLay
import kotlinx.android.synthetic.main.custom_toolbar.toolbar

class MainActivity : AppCompatActivity() {

    private val viewModel : SplashViewModel by viewModels()
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            setKeepOnScreenCondition{
                viewModel.isLoading.value
            }
        }
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(toolbar)
        loadDefaults()

        binding.btmNavBar.setOnItemSelectedListener { menuItem ->
            val selectedFragment = when (menuItem.itemId) {
                R.id.nav_home -> HomeFragment()
                R.id.nav_acc -> AccountFragment()
                R.id.nav_info -> InfoFragment()
                R.id.nav_web -> WebFragment()
                else -> return@setOnItemSelectedListener false
            }

            // Check if the selected fragment is different from the current fragment
            if (selectedFragment::class.java != supportFragmentManager.findFragmentById(R.id.mainView)?.javaClass) {
                replaceFragment(selectedFragment)
            }

            true
        }
        // Add long click listener to Bottom Navigation View items
        for (i in 0 until binding.btmNavBar.menu.size()) {
            val menuItem = binding.btmNavBar.menu.getItem(i)
            val view = binding.btmNavBar.findViewById<View>(menuItem.itemId)
            view?.setOnLongClickListener {
                onBottomNavigationItemLongClick(menuItem)
                true
            }
        }
    }
    private fun onBottomNavigationItemLongClick(menuItem: MenuItem) {
        when (menuItem.itemId) {
            R.id.nav_web ->
                if (toolbarLay.visibility != View.VISIBLE) {
                toolbarLay.visibility = View.VISIBLE
            }
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
            }
            "web" -> {
                binding.btmNavBar.selectedItemId = R.id.nav_web
                replaceFragment(WebFragment())
            }else -> {
                binding.btmNavBar.selectedItemId = R.id.nav_home
                replaceFragment(HomeFragment())
            }
        }
    }
}