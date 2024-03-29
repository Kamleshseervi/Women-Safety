package com.example.womensafetyapplication.guardiandetail

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.womensafetyapplication.R
import com.example.womensafetyapplication.databinding.FragmentGuardianInfoBinding
import com.google.android.material.snackbar.Snackbar


class GuardianInfo : Fragment() {

    private lateinit var binding: FragmentGuardianInfoBinding
    private lateinit var model: GuardianInfoViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_guardian_info,container,false)

        // Get the view model
        model = ViewModelProviders.of(this).get(GuardianInfoViewModel::class.java)

        // Specify layout for recycler view
        val linearLayoutManager = LinearLayoutManager(
            activity!!, RecyclerView.VERTICAL,false)
        binding.guardianList.layoutManager = linearLayoutManager

        // Observe the model
        model.allGuardians.observe(this, Observer{ guardians->
            // Data bind the recycler view
            binding.guardianList.adapter = context?.let { GuardianAdapter(guardians, it) }

        })

        binding.addGuardian.setOnClickListener { openAddGuardian() }

        model.showSnackBarEvent.observe(this, Observer {
            if (it == true) {
                Snackbar.make(
                    activity!!.findViewById(android.R.id.content),
                    getString(R.string.cleared_message),
                    Snackbar.LENGTH_LONG
                ).show()
                model.doneShowingSnackbar()
            }
        })

        setHasOptionsMenu(true)
        return binding.root
    }

    fun openAddGuardian(){
        findNavController().navigate(GuardianInfoDirections.actionGuardianInfoToAddGuardian())
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.options_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item!!.itemId) {
            R.id.delete_item -> model.onClear()
        }
        return super.onOptionsItemSelected(item)
    }
}
