package com.kfu.itis.androidpracticeproject.view.fragments

import androidx.fragment.app.Fragment
import com.kfu.itis.androidpracticeproject.R

open class BaseFragment : Fragment() {

//    fun initViewModel(viewModel: ViewModel): ViewModel {
//        val resultViewModel by lazy {
//            ViewModelProvider(
//                this,
//                viewModelFactory
//            ).get(viewModel::class.java)
//        }
//        return resultViewModel
//    }

    fun navigateToFragment(fragment: Fragment) {
        val transaction = childFragmentManager.beginTransaction().apply { }
        transaction.add(R.id.nav_host_fragment, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}