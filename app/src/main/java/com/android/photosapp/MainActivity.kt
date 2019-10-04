package com.android.photosapp

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import com.android.photosapp.data.viewModelPackage.MainViewModel
import com.android.photosapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    lateinit var activityMainBinding: ActivityMainBinding
    lateinit var navigator: NavController
    lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        navigator = Navigation.findNavController(this, R.id.main_fragment_container)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        navigationSetup(navigator, activityMainBinding)
        setSupportActionBar(activityMainBinding.toolbar)
        activityMainBinding.toolbar.setNavigationOnClickListener { navigator.navigateUp() }


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return super.onCreateOptionsMenu(menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.delete_all) {
            viewModel.deleteAll()
        }
        return item.onNavDestinationSelected(navigator) || super.onOptionsItemSelected(item)
    }

}

fun navigationSetup(navigator: NavController, activityMainBinding: ActivityMainBinding) {
    val appBarConfiguration = AppBarConfiguration(navigator.graph)
    activityMainBinding.toolbar.setupWithNavController(navigator, appBarConfiguration)
    activityMainBinding.collapsingLayout.setupWithNavController(
        activityMainBinding.toolbar,
        navigator,
        appBarConfiguration
    )


}
