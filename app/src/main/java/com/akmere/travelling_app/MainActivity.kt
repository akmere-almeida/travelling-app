package com.akmere.travelling_app

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.compose.rememberNavController
import com.akmere.travelling_app.common.provider.AddressProvider
import com.akmere.travelling_app.common.PermissionManager
import com.akmere.travelling_app.dependencies.AppDependencies
import com.akmere.travelling_app.presentation.common.AppTheme
import com.akmere.travelling_app.presentation.screen.home.components.ScreenNavigator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val permissionManager: PermissionManager by lazy {
        PermissionManager(this)
    }

    private val addressProvider: AddressProvider by lazy {
        AppDependencies.providesAddressProvider(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            AppTheme {
                ScreenNavigator(navController = navController) { viewModel ->
                    permissionManager.checkLocationPermission { location ->
                        CoroutineScope(Dispatchers.Main).launch {
                            val address = addressProvider.loadAddress(location)
//                            viewModel.setUserAddress(address)
                        }
                    }
                }
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
