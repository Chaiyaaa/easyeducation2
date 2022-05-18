package app.easyeducation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class FragmentsHolder extends AppCompatActivity {

    BottomNavigationView Navbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragments_holder);

       //initWidget
        Navbar=findViewById(R.id.bottomNav);

        Navbar.setOnNavigationItemSelectedListener(navListener);

        //this one is to open cour as first fragment
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new CourFragment()).commit();
    }


    private  BottomNavigationView.OnNavigationItemSelectedListener navListener=new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {


            Fragment selectedFragment=null; //this store the selected fragment for the item giving as parameter


            switch (item.getItemId())
            {

                //here we pass all items we created in our menu
                //in each case (each item we open the fragment related)


                case R.id.nav_cours:
                    selectedFragment=new CourFragment();
                    break;

                case R.id.nav_devoir:
                    selectedFragment=new DevoirFragment();
                    break;

                case R.id.nav_test:
                    selectedFragment=new TestFragment();
                    break;

                case R.id.nav_affichage:
                    selectedFragment=new AffichageFragment();
                    break;

            }
            //here we are opening the fragment selected
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();
        return true;
        }
    };



}