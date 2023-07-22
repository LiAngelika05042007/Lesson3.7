package com.angelika.lesson_37

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.angelika.lesson_37.databinding.FragmentBBinding
import com.bumptech.glide.Glide

class BFragment : Fragment() {

    private var _binding: FragmentBBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getData()
    }

    private fun getData() {
        arguments?.let { args ->
            val model = args.getSerializable(FruitFragment.MODEL) as Fruit?
            if (model != null) {
                Glide.with(binding.image.context)
                    .load(model.image)
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(binding.image)
                binding.text.text = model.name
            }
        }
    }
}