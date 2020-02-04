package com.man.hellosport.ui.dashboard.favorite


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.man.hellosport.R
import kotlinx.android.synthetic.main.fragment_container_favorite.*

/**
 * A simple [Fragment] subclass.
 */
class ContainerFavorite : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_container_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }


    private  fun init() {
        viewpagerFavorite.adapter = object : FragmentStateAdapter(this) {
            override fun createFragment(position: Int): Fragment {
                return when (position) {
                    0 -> FavoriteMatchFragment()
                    else -> FavoriteTeamsFragment()
                }
            }
            override fun getItemCount(): Int = 2
        }
        TabLayoutMediator(tabsFavorite, viewpagerFavorite) { tab, position ->
            tab.text = when (position) {
                0 -> "Favorite Match"
                else -> "Favorite Teams"
            }
        }.attach()
    }
}
