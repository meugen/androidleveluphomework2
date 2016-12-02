package ua.meugen.android.levelup.homework2;

import android.graphics.Color;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckedTextView;

import java.lang.ref.WeakReference;

public class MainActivity extends AppCompatActivity {

    private static final String RED_TAG = "red";
    private static final String GREEN_TAG = "green";
    private static final String BLUE_TAG = "blue";

    private static final int TRANSPARENCY = 0x64 << 24;

    private final Handler handler = new Handler();

    private CheckedTextView greenCheckedTextView;
    private CheckedTextView redCheckedTextView;
    private CheckedTextView blueCheckedTextView;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.greenCheckedTextView = (CheckedTextView) findViewById(R.id.green);
        this.redCheckedTextView = (CheckedTextView) findViewById(R.id.red);
        this.blueCheckedTextView = (CheckedTextView) findViewById(R.id.blue);
    }

    private int adjustAlpha(final int color) {
        return TRANSPARENCY | color & 0x00FFFFFF;
    }

    private Fragment createFragmentByTag(final String tag) {
        if (RED_TAG.equals(tag)) {
            return ColorFragment.create(adjustAlpha(Color.RED));
        } else if (GREEN_TAG.equals(tag)) {
            return ColorFragment.create(adjustAlpha(Color.GREEN));
        } else if (BLUE_TAG.equals(tag)) {
            return ColorFragment.create(adjustAlpha(Color.BLUE));
        }
        return null;
    }

    public void onRedClick(final View view) {
        onCheckBoxClick(RED_TAG);
    }

    public void onGreenClick(final View view) {
        onCheckBoxClick(GREEN_TAG);
    }

    public void onBlueClick(final View view) {
        onCheckBoxClick(BLUE_TAG);
    }

    private void onCheckBoxClick(final String tag) {
        final FragmentManager manager = getSupportFragmentManager();

        Fragment fragment = manager.findFragmentByTag(tag);
        if (fragment == null) {
            fragment = createFragmentByTag(tag);
            manager.beginTransaction()
                    .add(R.id.container, fragment, tag)
                    .addToBackStack(tag)
                    .commit();
        } else {
            manager.popBackStack(tag, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
        updateCheckedTextViews();
    }

    private void updateCheckedTextViews() {
        this.handler.post(new CheckedTextViewsUpdater(this));
    }

    void _updateCheckedTextViews() {
        final FragmentManager manager = getSupportFragmentManager();
        greenCheckedTextView.setChecked(manager.findFragmentByTag(GREEN_TAG) != null);
        redCheckedTextView.setChecked(manager.findFragmentByTag(RED_TAG) != null);
        blueCheckedTextView.setChecked(manager.findFragmentByTag(BLUE_TAG) != null);
    }

    @Override
    public void onBackPressed() {
        final FragmentManager manager = getSupportFragmentManager();
        if (manager.getBackStackEntryCount() == 0) {
            finish();
        } else {
            manager.popBackStack();
            updateCheckedTextViews();
        }
    }
}

class CheckedTextViewsUpdater implements Runnable {

    private final WeakReference<MainActivity> ref;

    CheckedTextViewsUpdater(final MainActivity activity) {
        this.ref = new WeakReference<>(activity);
    }

    @Override
    public void run() {
        final MainActivity activity = this.ref.get();
        if (activity != null) {
            activity._updateCheckedTextViews();
        }
    }
}
