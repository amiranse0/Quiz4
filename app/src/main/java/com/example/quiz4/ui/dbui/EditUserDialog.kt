package com.example.quiz4.ui.dbui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import com.example.quiz4.R
import com.example.quiz4.databinding.EditUserDialogBinding
import ir.mohsenafshar.apps.mkbarchitecture.data.remote.model.UserReqBody

class EditUserDialog : DialogFragment(R.layout.edit_user_dialog) {

    private lateinit var binding: EditUserDialogBinding

    private lateinit var getUser: GetUser

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = EditUserDialogBinding.bind(view)

        binding.submitBtn.setOnClickListener {
            val user = UserReqBody(
                binding.firstNameInput.text.toString(),
                emptyList(),
                binding.lastNameInput.text.toString(),
                binding.nationalCodeInput.text.toString()
            )

            getUser.getUserFromDialog(user)

            dismiss()
        }
    }

    interface GetUser{
        fun getUserFromDialog(user:UserReqBody)
    }

    fun setGetUser(getUser: GetUser){
        this.getUser = getUser
    }
}