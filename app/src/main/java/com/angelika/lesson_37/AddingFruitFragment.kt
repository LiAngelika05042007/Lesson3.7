package com.angelika.lesson_37

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.angelika.lesson_37.databinding.FragmentAddingFruitBinding

class AddingFruitFragment : Fragment() {

    private var _binding: FragmentAddingFruitBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddingFruitBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        buttonColor()
        btnAddClick()
    }

    private fun buttonColor() = with(binding) {
        etLinkToPhoto.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s != null) {
                    if (s.isNotEmpty()) {
                        btnAdd.setBackgroundColor(resources.getColor(R.color.burgundy));
                    } else {
                        btnAdd.setBackgroundColor(resources.getColor(R.color.gray));
                    }
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })

        etName.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s != null) {
                    if (s.isNotEmpty()) {
                        btnAdd.setBackgroundColor(resources.getColor(R.color.burgundy));
                    } else {
                        btnAdd.setBackgroundColor(resources.getColor(R.color.gray));
                    }
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
    }

    private fun btnAddClick() = with(binding) {
        btnAdd.setOnClickListener {
            val url = etLinkToPhoto.text.toString()
            val newNameFruit = etName.text.toString()

            if (url.isNotEmpty() && newNameFruit.isNotEmpty()) {
                val arguments = Bundle()
                arguments.putString(FruitFragment.URL_KEY, url)
                arguments.putString(FruitFragment.NAME_KEY, newNameFruit)

                parentFragmentManager.beginTransaction()
                    .add(R.id.fragment_container, FruitFragment::class.java, arguments)
                    .commit()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}