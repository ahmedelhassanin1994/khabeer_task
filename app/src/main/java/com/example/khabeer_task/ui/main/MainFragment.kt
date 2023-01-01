package com.example.khabeer_task.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.khabeer_task.R
import com.example.khabeer_task.databinding.FragmentMainBinding
import com.example.khabeer_task.ui.main.splash.SplashDirections
import com.raminabbasiiii.movies.util.Status
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MainFragment : Fragment() {



    private lateinit var viewModel: MainViewModel
    lateinit var binding: FragmentMainBinding



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding = FragmentMainBinding.inflate(layoutInflater)
        binding.loginViewModel = viewModel
        go_home();
        return binding.getRoot()

    }

    fun go_home(){
        viewModel.movieDetails.observe(viewLifecycleOwner) { result ->
            when(result.status) {
                Status.LOADING -> {
                }
                Status.SUCCESS -> {
                    findNavController().navigate(
                        MainFragmentDirections
                            .actionLoginFragmentToEmployeeFragment("Bearer ${result.data?.Token}"))
                }
                Status.ERROR -> {

                }
            }
        }

    }

//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
//        // TODO: Use the ViewModel
//    }

}