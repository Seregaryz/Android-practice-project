package com.kfu.itis.androidpracticeproject.view.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kfu.itis.androidpracticeproject.Injector
import com.kfu.itis.androidpracticeproject.R

class ShopFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_disputes, container, false)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Injector.plusShopComponent().inject(this)
    }
}
