package com.kfu.itis.androidpracticeproject.view.fragments

import androidx.fragment.app.Fragment
import com.kfu.itis.androidpracticeproject.R

open class BaseFragment : Fragment() {

    fun navigateToFragment(fragment: Fragment) {
        val transaction = childFragmentManager.beginTransaction().apply { }
        transaction.add(R.id.nav_host_fragment, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}
