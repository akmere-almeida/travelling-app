package com.akmere.travelling_app

import android.location.Geocoder
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.akmere.travelling_app.common.AddressProvider
import com.akmere.travelling_app.common.PermissionManager
import com.akmere.travelling_app.dependencies.AppDependencies
import com.akmere.travelling_app.presentation.common.AppTheme
import com.akmere.travelling_app.presentation.home.HomeScreen
import com.akmere.travelling_app.presentation.home.model.FilterOptions
import com.akmere.travelling_app.presentation.viewmodel.HomeViewModel
import com.akmere.travelling_app.presentation.viewmodel.factory.ViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val homeViewModel: HomeViewModel by viewModels {
        ViewModelFactory(
            owner = this,
            searchOffers = AppDependencies.providesSearchOffers(),
            travelAppImageLoader = AppDependencies.providesAppImageLoader(this)
        )
    }

    private val permissionManager: PermissionManager by lazy {
        PermissionManager(this)
    }

    private val addressProvider: AddressProvider by lazy {
        AppDependencies.providesAddressProvider(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                HomeScreen(homeViewModel = homeViewModel)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        permissionManager.checkLocationPermission {
            CoroutineScope(Dispatchers.Main).launch {
                val address = addressProvider.loadAddress(it)
                val filters = FilterOptions(address.city, address.state)
                homeViewModel.loadPopularOffers(filters)
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        permissionManager.requestPermissions(requestCode, permissions, grantResults)
    }
}
