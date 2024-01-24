package com.example.testapp

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.testapp.data.model.Theme

class ListItemPagerAdapter(fm: FragmentActivity): FragmentStateAdapter(fm) {

    private val fragments = ArrayList<ListItemFragment>()

    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }

    fun populateData(themes: List<Theme>) {
        fragments.add(ListItemFragment.newInstance(ArrayList(themes)))
    }
}