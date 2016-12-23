package com.yundin.pesn;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

public class MainActivity extends AppCompatActivity {

    Drawer result;
    int number = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        final TextView text = (TextView) findViewById(R.id.text);

        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.header)
                .withCompactStyle(true)
                .build();


        result = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .addDrawerItems(
                        new PrimaryDrawerItem().withIdentifier(2).withName(R.string.first).withIcon(GoogleMaterial.Icon.gmd_queue_music),
                        new PrimaryDrawerItem().withIdentifier(3).withName(R.string.second).withIcon(GoogleMaterial.Icon.gmd_queue_music),
                        new PrimaryDrawerItem().withIdentifier(4).withName(R.string.third).withIcon(GoogleMaterial.Icon.gmd_queue_music),
                        new PrimaryDrawerItem().withIdentifier(5).withName("Четвертая категория").withIcon(GoogleMaterial.Icon.gmd_queue_music),
                        new DividerDrawerItem(),
                        new SecondaryDrawerItem().withIdentifier(1).withName(R.string.add).withIcon(GoogleMaterial.Icon.gmd_add)
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        // do something with the clicked item :D
                        if (drawerItem.getIdentifier() == 1) {
                            number++;
                            result.addItemAtPosition(new PrimaryDrawerItem().withIdentifier(number).withName("Добавленная категория").withIcon(GoogleMaterial.Icon.gmd_queue_music), (number-1));

                        }
                        else{
                            result.closeDrawer();
                            String str = "Это активити номер " + (drawerItem.getIdentifier()-1);
                            text.setText(str);
                            Toast.makeText(getApplication(), "Hello", Toast.LENGTH_SHORT).show();
                        }
                        return true;
                    }
                })
                .withAccountHeader(headerResult)
                .withSavedInstance(savedInstanceState)
                .build();

        result.getActionBarDrawerToggle().setDrawerIndicatorEnabled(true);




    }

    @Override
    public void onBackPressed() {
        if (result != null && result.isDrawerOpen()) {
            result.closeDrawer();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState = result.saveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }
}