package com.example.artbooktesting.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.bumptech.glide.RequestManager
import com.example.artbooktesting.adapter.ArtRecylerAdapter
import com.example.artbooktesting.adapter.ImageRecylerAdapter
import com.example.artbooktesting.roomdb.Art
import javax.inject.Inject

class ArtFragmentFactory @Inject constructor(
    private val glide : RequestManager,
    private val artRecylerAdapter: ArtRecylerAdapter,
    private val imageRecylerAdapter: ImageRecylerAdapter
 ): FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {

        return when(className) {
            ArtFragment::class.java.name->ArtFragment(artRecylerAdapter)
            ArtDetailsFragment::class.java.name->ArtDetailsFragment(glide)
            ImageApiFragment::class.java.name-> ImageApiFragment(imageRecylerAdapter)
            else-> super.instantiate(classLoader, className)
        }

    }

}