package com.angelika.lesson_37

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.angelika.lesson_37.databinding.FragmentFruitBinding

class FruitFragment : Fragment() {

    private var _binding: FragmentFruitBinding? = null
    private val binding get() = _binding!!
    private val fruitAdapter = FruitAdapter(this::onItemClick)

    private fun onItemClick(fruit: Fruit) {
        val args = Bundle().apply {
            putSerializable(MODEL, fruit)
        }
        parentFragmentManager.beginTransaction()
            .add(R.id.fragment_container, BFragment::class.java, args)
            .addToBackStack("Fruit Fragment")
            .commit()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFruitBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        click()
        initRecycle()
        dataTransfer()
    }

    private fun click() = with(binding) {
        btnAddFruit.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .add(R.id.fragment_container, AddingFruitFragment::class.java, null)
                .addToBackStack("Fruit Fragment")
                .commit()
        }
    }

    private fun initRecycle() {
        binding.rvFruit.adapter = fruitAdapter
    }

    private fun dataTransfer() {
        arguments?.let {
            val url = it.getString(URL_KEY)
            val newNameFruit = it.getString(NAME_KEY)

            if (url != null && newNameFruit != null) {
                val fruit = Fruit(newNameFruit, url)
                fruitAdapter.addImage(fruit)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val NAME_KEY = "name"
        const val URL_KEY = "url"
        const val MODEL = "fruitData"
    }
}