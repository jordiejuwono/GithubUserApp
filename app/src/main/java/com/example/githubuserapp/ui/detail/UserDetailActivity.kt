package com.example.githubuserapp.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import com.example.githubuserapp.databinding.ActivityUserDetailsBinding
import com.example.githubuserapp.model.GithubUser

class UserDetailActivity : AppCompatActivity() {

    companion object {
        const val USER_DETAILS = "USER_DETAILS"
    }

    private lateinit var binding: ActivityUserDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.title = intent.getParcelableExtra<GithubUser>(USER_DETAILS)?.name
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setViewBinding()
        getDataFromIntent()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setViewBinding() {
        binding = ActivityUserDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun getDataFromIntent() {
        val userDetails = intent.getParcelableExtra<GithubUser>(USER_DETAILS)
        userDetails?.let {
            binding.ivAvatar.setImageResource(it.avatar)
            binding.tvName.text = it.name
            binding.tvRepositories.text = it.repository
            binding.tvUsername.text = it.userName
            binding.tvFollowers.text = it.follower
            binding.tvFollowing.text = it.Following
            binding.tvWorkplace.text = it.company
            binding.tvLocation.text = it.location
        }

        binding.llRepositories.setOnClickListener {
            Toast.makeText(this, "${userDetails?.repository} Repositories", Toast.LENGTH_SHORT).show()
        }

    }
}