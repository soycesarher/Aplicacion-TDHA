package com.example.tdah.psicologo.ayudaPsicologo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.tdah.databinding.FragmentAyudaPsicologoBinding;

public class ayudaPsicologoFragment extends Fragment {

    private ayudaPsicologoViewModel ayudaPsicologoViewModel;
    private FragmentAyudaPsicologoBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ayudaPsicologoViewModel =
                new ViewModelProvider(this).get(ayudaPsicologoViewModel.class);

        binding = FragmentAyudaPsicologoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textSlideshow;
        ayudaPsicologoViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}