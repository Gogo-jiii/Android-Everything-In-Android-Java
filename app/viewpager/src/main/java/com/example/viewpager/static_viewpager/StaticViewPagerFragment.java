package com.example.viewpager.static_viewpager;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.viewpager.R;
import com.example.viewpager.ViewPagerUtil;
import com.example.viewpager.databinding.FragmentStaticViewPagerBinding;

import java.util.ArrayList;
import java.util.Objects;

public class StaticViewPagerFragment extends Fragment {

    FragmentStaticViewPagerBinding binding;
    private NavController navController;
    private FragmentStateAdapter pagerAdapter;
    private ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
    private boolean isEnabled;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentStaticViewPagerBinding.inflate(inflater, container, false);
        navController = Navigation.findNavController(requireActivity(), R.id.fragmentContainerView);

        setupViewPager();
        return binding.getRoot();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        OnBackPressedCallback onBackPressedCallback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (binding.viewPager2.getCurrentItem() == 0 && requireActivity().getOnBackPressedDispatcher().hasEnabledCallbacks()) {
                    setEnabled(false);
                    requireActivity().getOnBackPressedDispatcher().onBackPressed();
                } else {
                    binding.viewPager2.setCurrentItem(binding.viewPager2.getCurrentItem() - 1);
                }
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(requireActivity(), onBackPressedCallback);
    }

    private void setupViewPager() {
        fragmentArrayList.add(new StaticFragmentOne());
        fragmentArrayList.add(new StaticFragmentTwo());
        fragmentArrayList.add(new StaticFragmentThree());

        pagerAdapter = new StaticViewPagerAdapter(this, fragmentArrayList);
        binding.viewPager2.setAdapter(pagerAdapter);
        //binding.viewPager2.setPageTransformer(new TossTransformation());
        ViewPagerUtil.getInstance().setupIndicator(getActivity(), binding.viewPager2, binding.pagerDots, fragmentArrayList.size());
        ViewPagerUtil.getInstance().onBackPressed(binding.viewPager2, getChildFragmentManager());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}