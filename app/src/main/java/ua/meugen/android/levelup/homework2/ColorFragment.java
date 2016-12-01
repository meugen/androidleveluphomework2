package ua.meugen.android.levelup.homework2;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/**
 * Created by meugen on 01.12.16.
 */

public class ColorFragment extends Fragment {

    private static final String COLOR_KEY = "color";

    public static ColorFragment create(final int color) {
        final Bundle arguments = new Bundle();
        arguments.putInt(COLOR_KEY, color);

        final ColorFragment fragment = new ColorFragment();
        fragment.setArguments(arguments);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(
            final LayoutInflater inflater,
            @Nullable final ViewGroup container,
            @Nullable final Bundle savedInstanceState) {
        final int color = getArguments().getInt(COLOR_KEY);

        final FrameLayout view = new FrameLayout(inflater.getContext());
        ViewCompat.setBackground(view, new ColorDrawable(color));
        return view;
    }
}
