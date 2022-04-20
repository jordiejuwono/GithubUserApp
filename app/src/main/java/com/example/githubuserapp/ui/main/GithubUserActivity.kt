package com.example.githubuserapp.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubuserapp.R
import com.example.githubuserapp.databinding.ActivityGithubUserBinding
import com.example.githubuserapp.model.GithubUser
import com.example.githubuserapp.ui.detail.UserDetailActivity
import com.example.githubuserapp.ui.main.adapter.GithubUserAdapter

class GithubUserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGithubUserBinding
    private lateinit var adapter: GithubUserAdapter
    private val userList: ArrayList<GithubUser> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.title = getString(R.string.text_github_users)
        setViewBinding()
        insertDataToAdapter()
        setRecyclerView()
    }

    private fun setViewBinding(){
        binding = ActivityGithubUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun insertDataToAdapter() {
        val name = resources.getStringArray(R.array.name)
        val username = resources.getStringArray(R.array.username)
        val avatar = resources.obtainTypedArray(R.array.avatar)
        val company = resources.getStringArray(R.array.company)
        val location = resources.getStringArray(R.array.location)
        val repository = resources.getStringArray(R.array.repository)
        val following = resources.getStringArray(R.array.following)
        val followers = resources.getStringArray(R.array.followers)
        for (i in name.indices){
            val user = GithubUser(username[i], name[i], avatar.getResourceId(i, 0), company[i], location[i], repository[i], followers[i], following[i])
            userList.add(user)
        }
    }

    private fun setRecyclerView() {
        adapter = GithubUserAdapter(object : GithubUserAdapter.IntentToProfileDetails {
            override fun intentToDetails(githubUser: GithubUser) {
                val intent = Intent(this@GithubUserActivity, UserDetailActivity::class.java)
                intent.putExtra(UserDetailActivity.USER_DETAILS, githubUser)
                startActivity(intent)
            }

        })
        adapter.items.addAll(userList)
        binding.rvGithubUsers.apply {
            adapter = this@GithubUserActivity.adapter
            layoutManager = LinearLayoutManager(this@GithubUserActivity, LinearLayoutManager.VERTICAL, false)
        }
    }
}