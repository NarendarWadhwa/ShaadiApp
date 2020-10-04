package com.example.shaadi.userlist

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shaadi.R
import com.example.shaadi.base.BaseActivity
import com.example.shaadi.base.BaseAdapter
import com.example.shaadi.databinding.ActivityUserListBinding
import com.example.shaadi.databinding.ItemUserListBinding
import com.example.shaadi.network.models.UserProfile
import com.example.shaadi.utils.showToast

class UserListActivity : BaseActivity<ActivityUserListBinding, UserListViewModel>() {
    override fun getLayoutId() = R.layout.activity_user_list
    override fun getViewModel() = UserListViewModel::class.java

    override fun onBinding() {
        supportActionBar!!.title = "User List"

        mViewModel.errorLiveData.observe(this, Observer {
            showToast(it)
        })

        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        val userAdapter = object : BaseAdapter<UserProfile, ItemUserListBinding>(this) {
            override fun getLayoutId() = R.layout.item_user_list

            override fun onBindView(
                binding: ItemUserListBinding,
                item: UserProfile,
                position: Int
            ) {
                binding.model = item
                binding.viewModel = mViewModel
            }

        }
        mBinding.rvUsers.apply {
            layoutManager = LinearLayoutManager(this@UserListActivity)
            adapter = userAdapter
        }
        mViewModel.getUserProfile().observe(this, Observer {
            userAdapter.updateData(it)
        })
        mViewModel.fetchUserProfile()
    }


}