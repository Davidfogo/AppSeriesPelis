package com.example.appseriespelis;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.appseriespelis.Botons_Fragment.Fragment_Buscar;
import com.example.appseriespelis.Botons_Fragment.Fragment_MisListas;
import com.example.appseriespelis.Botons_Fragment.Fragment_Social;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class BottomNavigation extends AppCompatActivity {
    Fragment_Buscar firstFragment= new Fragment_Buscar();
   // Fragment_Llista secondFragment= new Fragment_Llista();
    Fragment_MisListas thirdFragment = new Fragment_MisListas();
    Fragment_Social fourFragment= new Fragment_Social();
    private String TAG = "firebaseTest";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation);



        BottomNavigationView navigation = findViewById(R.id.bottom_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        //fetchData process = new fetchData("titanic");
        //process.execute();

        String urlDB = "https://app-series-y-peliculasdfg-default-rtdb.europe-west1.firebasedatabase.app/";

    }
    private final BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.firstFragment:
                    loadFragment(firstFragment);
                    return true;
              /*  case R.id.secondFragment:
                    loadFragment(secondFragment);
                    return true;*/
                case R.id.thirdFragment:
                    loadFragment(thirdFragment);
                    return true;
                case R.id.fourFragment:
                    loadFragment(fourFragment);
                    return true;

            }
            return false;
        }
    };

    public void loadFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();

    }

}