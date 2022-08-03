package com.example.viewpager.dynamic_viewpager;

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

import com.example.viewpager.R;
import com.example.viewpager.ViewPagerUtil;
import com.example.viewpager.databinding.FragmentDynamicViewPagerBinding;

import java.util.Objects;


public class DynamicViewPagerFragment extends Fragment {

    FragmentDynamicViewPagerBinding binding;
    private NavController navController;
    private static final int NUM_PAGES = 5;
    private String[] fragmentData = {"A", "B", "C", "D", "E"};
    private DynamicViewPagerAdapter pagerAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentDynamicViewPagerBinding.inflate(inflater, container, false);
        navController = Navigation.findNavController(requireActivity(), R.id.fragmentContainerView);

        setupViewPager();
        return binding.getRoot();
    }

    private void setupViewPager() {
        pagerAdapter = new DynamicViewPagerAdapter(this, fragmentData, NUM_PAGES);
        binding.dynamicViewPager2.setAdapter(pagerAdapter);
        ViewPagerUtil.getInstance().setupIndicator(getActivity(),  binding.dynamicViewPager2,  binding.pagerDots, NUM_PAGES);
        ViewPagerUtil.getInstance().onBackPressed( binding.dynamicViewPager2, getChildFragmentManager());
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        OnBackPressedCallback onBackPressedCallback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (binding.dynamicViewPager2.getCurrentItem() == 0 && requireActivity().getOnBackPressedDispatcher().hasEnabledCallbacks()) {
                    setEnabled(false);
                    requireActivity().getOnBackPressedDispatcher().onBackPressed();
                } else {
                    binding.dynamicViewPager2.setCurrentItem(binding.dynamicViewPager2.getCurrentItem() - 1);
                }
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(requireActivity(), onBackPressedCallback);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}