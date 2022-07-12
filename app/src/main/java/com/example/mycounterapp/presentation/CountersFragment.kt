package com.example.mycounterapp.presentation

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.mycounterapp.databinding.FragmentCountersBinding
import com.example.mycounterapp.domain.model.Counter
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

    private val incrementOnClick: () -> Unit = {
        Log.i("Onclick", "inc")
    }

    private val decrementOnClick: () -> Unit = {
        Log.i("Onclick", "dec")
    }

    private val counterAdapter: CounterAdapter = CounterAdapter(
        incrementOnClick,
        decrementOnClick
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
    }

    private fun setupCounters(counter: List<Counter>) {
        counterAdapter.submitList(counter)
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
}