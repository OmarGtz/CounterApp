package com.example.mycounterapp.presentation

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.mycounterapp.databinding.FragmentAddCounterBinding
import com.example.mycounterapp.databinding.FragmentCountersBinding
import com.example.mycounterapp.domain.model.Counter
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint

/**
 * CountersFragment
 *
 * @author (c) 2022
 */
@AndroidEntryPoint
class CountersFragment: Fragment() {

    lateinit var binding: FragmentCountersBinding
    val viewModel by viewModels<CounterViewModel>()

    private val incrementOnClick: (Counter) -> Unit = {
        viewModel.updateCounter(it.id, isIncrement = true)
    }

    private val decrementOnClick: (Counter) -> Unit = {
        viewModel.updateCounter(it.id, isIncrement = false)
    }

    private val removeListener: (Counter) -> Unit = {
        viewModel.removeCounter(it)
    }

    private val shareListener: (Counter) -> Unit = {
        shareData(data = "${it.title} Counter: ${it.count}")
    }

    private val counterAdapter: CounterAdapter = CounterAdapter(
        incrementOnClick,
        decrementOnClick,
        removeListener,
        shareListener
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCountersBinding.inflate(inflater, container, false)
        with(binding) {
            counters.adapter = counterAdapter
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeLoadCounters()
        subscribeLoading()
        viewModel.getCounters()
        binding.apply {
            addCounterButton.setOnClickListener {
                showAddCounterDialog()
            }
        }
    }

    private fun shareData(data: String) {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, data)
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }

    private fun setupCounters(counter: List<Counter>) {
        counterAdapter.submitList(counter)
    }

    private fun showAddCounterDialog() {
        AddCounterDialog(viewModel::saveCounter)
            .show(childFragmentManager, "AddDialog")
    }

    private fun showLoading(show: Boolean) {
        with(binding.loading) {
            if (show) {
                playAnimation()
                visibility = View.VISIBLE
            } else {
                visibility = View.GONE
                pauseAnimation()
            }
        }
    }

    private fun subscribeLoading() {
        viewModel.loading.observe(viewLifecycleOwner) {
            showLoading(it)
        }
    }

    private fun subscribeLoadCounters() {
        viewModel.counters.observe(viewLifecycleOwner) {
                setupCounters(it)
        }
    }

    class ConfirmRemoveItem(val retry:() -> Unit) : DialogFragment() {
        override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
            return MaterialAlertDialogBuilder(requireContext())
                .setMessage("Confirmar eliminacion")
                .setPositiveButton("si, cancelar") { _, _ ->
                    dismiss()
                    retry()
                }
                .setNegativeButton("No") { _, _ ->

                }
                .setCancelable(false)
                .create()
        }
    }

    class AddCounterDialog(val onClick: (String) -> Unit) : BottomSheetDialogFragment() {
        lateinit var binding: FragmentAddCounterBinding
        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            binding = FragmentAddCounterBinding.inflate(inflater).apply {
                addButton.setOnClickListener {
                    onClick(inputTitle.text.toString())
                }
            }
            return binding.root
        }
        }
    }
