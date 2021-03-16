package com.example.eathealthy.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.FrameLayout
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.eathealthy.R
import com.example.eathealthy.fragment.*
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    lateinit var mainToolbar: Toolbar
    lateinit var navigationView: NavigationView
    lateinit var frameLayout: FrameLayout
    lateinit var drawerLayout : DrawerLayout
    lateinit var title : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title ="Home"
        mainToolbar = findViewById(R.id.mainToolbar)
        navigationView = findViewById(R.id.navigationView)
        frameLayout = findViewById(R.id.frameLayout)
        drawerLayout = findViewById(R.id.drawerLayout)

        setUpToolBar()

        var actionBarDrawerToggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            R.string.open,
            R.string.close
        )

        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        navigationView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.mi_home -> {
                    displayHome()
                }

                R.id.mi_favorites -> {
                    title = "Favorites" // set title of fragment
                    setUpToolBar()
                    supportFragmentManager.beginTransaction() // to begin the fragment transaction
                        .replace(frameLayout.id, FavoritesFragment())// replace the current frame with the required fragment
                        .commit()
                    drawerLayout.closeDrawers() //close the navigation drawer
                }

                R.id.mi_profile -> {
                    title = "Profile" // set title of fragment
                    setUpToolBar()
                    supportFragmentManager.beginTransaction() // to begin the fragment transaction
                        .replace(frameLayout.id, ProfileFragment()) // replace the current frame with the required fragment
                        .commit()
                    drawerLayout.closeDrawers() //close the navigation drawer
                }

                R.id.mi_faq -> {
                    title = "FAQ" // set title of fragment
                    setUpToolBar()
                    supportFragmentManager.beginTransaction() // to begin the fragment transaction
                        .replace(frameLayout.id, FAQFragment()) // replace the current frame with the required fragment
                        .commit()
                    drawerLayout.closeDrawers() //close the navigation drawer
                }
            }
            return@setNavigationItemSelectedListener true
        }
    }

    private fun displayHome(){
        title = "Home" // set title of fragment
        setUpToolBar()
        supportFragmentManager.beginTransaction() // to begin the fragment transaction
            .replace(frameLayout.id, HomeFragment()) // replace the current frame with the required fragment
            .commit()
        drawerLayout.closeDrawers() //close the navigation drawer
    }

    private fun setUpToolBar() {
        setSupportActionBar(mainToolbar)
        supportActionBar?.title = title //set the title of the toolbar
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true) // Displays the home button
    }

    // on clicking the back button from any of the fragment screen, open the home fragment. If on Home screen close the app default behaviour
    override fun onBackPressed() {
        when(supportFragmentManager.findFragmentById(R.id.frameLayout)) {
            !is HomeFragment -> displayHome()
            else -> super.onBackPressed()
        }
    }

    //To open the navigation drawer on click of hamburger icon in the toolbar
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home){
            drawerLayout.openDrawer(GravityCompat.START)
        }
        return super.onOptionsItemSelected(item)
    }
}

