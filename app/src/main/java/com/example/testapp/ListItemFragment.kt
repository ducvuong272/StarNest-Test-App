package com.example.testapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.HORIZONTAL
import androidx.recyclerview.widget.RecyclerView.Orientation
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.example.testapp.data.model.Theme
import com.example.testapp.data.model.Type
import com.example.testapp.databinding.FragmentListItemBinding

class ListItemFragment : Fragment() {

    private lateinit var binding: FragmentListItemBinding
    private lateinit var themes: ArrayList<Theme>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListItemBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getParcelableArrayList<Theme>(KEY_THEME)?.let{
            themes = it
        }

        val listNew = themes.filter { it.icon == Type.WHAT_NEW.toString() }
        val listOther = themes.filter { it.icon== Type.OTHER.toString() }

        binding.rvNew.adapter = ItemAdapter(listNew)
        binding.rvNew.layoutManager = GridLayoutManager(requireContext(), 2, HORIZONTAL, false)
        binding.rvSuggestions.adapter = ItemAdapter(listOther)
        binding.rvSuggestions.layoutManager = GridLayoutManager(requireContext(), 2, VERTICAL, false)
    }

    companion object {
        const val KEY_THEME = "key_theme"

        fun newInstance(themes: ArrayList<Theme>) =
            ListItemFragment().apply {
                arguments = Bundle().apply {
                    putParcelableArrayList(KEY_THEME, themes)
                }
            }
    }
}