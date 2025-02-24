package com.example.verify_me;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.example.verify_me.utils.NetworkUtils;

public class MainActivity extends AppCompatActivity {
    private boolean isDiscoveryStarted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (!isDiscoveryStarted) {
            startServerDiscovery();
        }

        BottomNavigationView navView = findViewById(R.id.nav_view);
        
        // Configure the navigation with only five items
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_dashboard,
                R.id.navigation_users,
                R.id.navigation_employees,
                R.id.navigation_learners,
                R.id.navigation_visitors)
                .build();
                
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }

    private void startServerDiscovery() {
        isDiscoveryStarted = true;
        NetworkUtils.discoverServer(this, new NetworkUtils.ServerDiscoveryListener() {
            @Override
            public void onServerFound(String serverUrl) {
                Config.setBaseUrl(serverUrl);
                Toast.makeText(MainActivity.this, "Server found: " + serverUrl, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onServerNotFound() {
                Toast.makeText(MainActivity.this, "Using fallback server address", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        NetworkUtils.stopDiscovery();
        isDiscoveryStarted = false;
    }
}