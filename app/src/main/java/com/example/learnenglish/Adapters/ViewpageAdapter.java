package com.example.learnenglish.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.learnenglish.fragment.AccountFragment;
import com.example.learnenglish.fragment.CartFragment;
import com.example.learnenglish.fragment.HomeFragment;
import com.example.learnenglish.fragment.SellFragment;

public class ViewpageAdapter extends FragmentStatePagerAdapter {

    public ViewpageAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new HomeFragment();
            case 1:
                return new SellFragment();
            case 2:
                return new CartFragment();
            case 3:
                return new AccountFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 4;
    }







////    ArrayList<Fragment>arrayList=new ArrayList<>();
////    ArrayList<String>arrayList1=new ArrayList<>();
////    public ViewpageAdapter(@NonNull FragmentManager fm) {
////        super(fm);
////    }
////
////    @NonNull
////    @Override
////    public Fragment getItem(int position) {
////        return arrayList.get(position);
////    }
////
////    @Override
////    public int getCount() {
////        return arrayList.size();
////    }
////   public void addFragment(Fragment fragment)
////    {
////        arrayList.add(fragment);
////    }
////    @Nullable
////    @Override
////    public CharSequence getPageTitle(int position) {
////        return arrayList1.get(position);
////    }
}
