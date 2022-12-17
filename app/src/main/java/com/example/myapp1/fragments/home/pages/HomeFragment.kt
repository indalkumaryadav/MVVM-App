package com.example.myapp1.fragments.home.pages

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapp1.R
import com.example.myapp1.constants.Constants
import com.example.myapp1.databinding.FragmentHomeBinding
import com.example.myapp1.fragments.home.adapters.HomeAdapter
import com.example.myapp1.fragments.home.adapters.MainAdapter
import com.example.myapp1.fragments.home.api.UserApi
import com.example.myapp1.fragments.home.repository.UserRepository
import com.example.myapp1.fragments.home.viewmodels.HomeViewModel
import com.example.myapp1.fragments.home.viewmodels.HomeViewModelFactory
import com.example.myapp1.retrofit.NetworkResult
import com.example.myapp1.retrofit.RetrofitHelper
import com.example.myapp1.utils.TokenManager
import com.google.android.material.bottomnavigation.BottomNavigationView


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var tokenManager: TokenManager

    private val userAPI = RetrofitHelper.getInstance().create(UserApi::class.java)
    // Create a viewModel
    private val viewModel: HomeViewModel by viewModels{ HomeViewModelFactory(UserRepository(userAPI)) }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }


    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navBar = requireActivity().findViewById<BottomNavigationView>(R.id.bottom_nav)
        navBar.isVisible = true
        tokenManager = TokenManager(requireContext())

        if (tokenManager.getToken(Constants.ACCESS_TOKEN) == null){
            findNavController().navigate(R.id.action_homeFragment_to_loginFragment)
        }

        viewModel.getAllUsers()

        val data = ArrayList<String>()
        val adapter = HomeAdapter(requireContext())
        binding.mainRecyclerView.adapter = adapter


        val adapter2 = MainAdapter(requireContext())
        binding.mainRecyclerView2.adapter = adapter2
        binding.mainRecyclerView2.layoutManager = LinearLayoutManager(requireContext())


        viewModel.userData.observe(viewLifecycleOwner, Observer {
            binding.homeProgressBar.isVisible = false
            when (it){
                is NetworkResult.Loading -> {
                    binding.homeProgressBar.isVisible = true
                }
                is NetworkResult.Success -> {
                    binding.homeProgressBar.isVisible = false
                    it.data?.let { it1 -> adapter.setUserResponse(it1)}
                    it.data?.let { it2 -> adapter2.setUserResponse(it2)}
                    adapter.notifyDataSetChanged()
                    adapter2.notifyDataSetChanged()
                }
                else ->{

                }
            }
        })

        binding.mainRecyclerView.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)


//        binding.logOutBtn.setOnClickListener {
//           tokenManager.deleteToken("token")
//            findNavController().navigate(R.id.action_homeFragment_to_loginFragment)
//        }

    }

    fun userIsLogin(flag:Boolean): Boolean {
        return flag
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}