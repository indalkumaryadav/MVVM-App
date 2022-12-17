package com.example.myapp1.fragments.login.pages

import android.app.Activity
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.myapp1.R
import com.example.myapp1.constants.Constants
import com.example.myapp1.databinding.FragmentLoginBinding
import com.example.myapp1.fragments.login.api.LoginApi
import com.example.myapp1.fragments.login.models.LoginRequest
import com.example.myapp1.fragments.login.repository.LoginRepository
import com.example.myapp1.fragments.login.viewmodels.LoginViewModel
import com.example.myapp1.fragments.login.viewmodels.LoginViewModelFactory
import com.example.myapp1.retrofit.NetworkResult
import com.example.myapp1.retrofit.RetrofitHelper
import com.example.myapp1.utils.TokenManager
import com.google.android.material.bottomnavigation.BottomNavigationView


class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    lateinit var tokenManager: TokenManager

    private val loginAPI = RetrofitHelper.getInstance().create(LoginApi::class.java)
    // Create a viewModel
    private val viewModel: LoginViewModel by activityViewModels{LoginViewModelFactory(LoginRepository(loginAPI))}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater,container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navBar = requireActivity().findViewById<BottomNavigationView>(R.id.bottom_nav)
        navBar.isVisible = false
        tokenManager = TokenManager(requireContext())
//        if (tokenManager.getToken() !=  null ){
//            findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
//        }
       val currentTheme = isDarkTheme(requireActivity())
        onLogin()

        binding.signUpBtn.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }


        viewModel.userResponseLiveData.observe(viewLifecycleOwner, Observer {
            binding.loginProgressBarIndicator.isVisible = false
            when (it){
                is NetworkResult.Loading ->{
                    binding.loginBtn.text = ""
                    binding.loginProgressBarIndicator.isVisible = true

                }
                is NetworkResult.Success -> {
                    Toast.makeText(requireContext(),"Success",Toast.LENGTH_SHORT).show()
                    it.data?.let { it1 -> tokenManager.saveToken(Constants.ACCESS_TOKEN, it1.token) }
                    findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                }
                is NetworkResult.Error ->{
                    Toast.makeText(requireContext(),"Error",Toast.LENGTH_SHORT).show()
                }

                else -> {
                    Toast.makeText(requireContext(),"Error",Toast.LENGTH_SHORT).show()
                }
            }

        })
    }

    private fun onLogin() {
        binding.loginBtn.setOnClickListener {
            val loginRequest = LoginRequest(username = "kminchelle", password = "0lelplR")
            viewModel.login(loginRequest)
        }


    }

    private fun isDarkTheme(activity: Activity): Boolean {
        return activity.resources.configuration.uiMode and
                Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}