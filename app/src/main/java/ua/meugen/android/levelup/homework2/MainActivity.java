package ua.meugen.android.levelup.homework2;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private static final String RED_TAG = "red";
    private static final String GREEN_TAG = "green";
    private static final String BLUE_TAG = "blue";

    private static final int RED   = Color.parseColor("#7FFF0000");
    private static final int GREEN = Color.parseColor("#7F00FF00");
    private static final int BLUE  = Color.parseColor("#7F0000FF");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(final Menu menu) {
        final FragmentManager manager = getSupportFragmentManager();
        menu.findItem(R.id.red).setChecked(manager.findFragmentByTag(RED_TAG) != null);
        menu.findItem(R.id.green).setChecked(manager.findFragmentByTag(GREEN_TAG) != null);
        menu.findItem(R.id.blue).setChecked(manager.findFragmentByTag(BLUE_TAG) != null);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        final FragmentManager manager = getSupportFragmentManager();

        final int itemId = item.getItemId();
        if (itemId == R.id.red) {
            Fragment fragment = manager.findFragmentByTag(RED_TAG);
            if (fragment == null) {
                fragment = ColorFragment.create(RED);
                manager.beginTransaction()
                        .add(R.id.container, fragment, RED_TAG)
                        .addToBackStack(null)
                        .commit();
            } else {
                manager.beginTransaction().remove(fragment).commit();
            }
            return true;
        } else if (itemId == R.id.green) {
            Fragment fragment = manager.findFragmentByTag(GREEN_TAG);
            if (fragment == null) {
                fragment = ColorFragment.create(GREEN);
                manager.beginTransaction()
                        .add(R.id.container, fragment, GREEN_TAG)
                        .addToBackStack(null)
                        .commit();
            } else {
                manager.beginTransaction().remove(fragment).commit();
            }
            return true;
        } else if (itemId == R.id.blue) {
            Fragment fragment = manager.findFragmentByTag(BLUE_TAG);
            if (fragment == null) {
                fragment = ColorFragment.create(BLUE);
                manager.beginTransaction()
                        .add(R.id.container, fragment, BLUE_TAG)
                        .addToBackStack(null)
                        .commit();
            } else {
                manager.beginTransaction().remove(fragment).commit();
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        final FragmentManager manager = getSupportFragmentManager();
        if (manager.getBackStackEntryCount() == 0) {
            finish();
        } else {
            manager.popBackStack();
        }
    }
}
