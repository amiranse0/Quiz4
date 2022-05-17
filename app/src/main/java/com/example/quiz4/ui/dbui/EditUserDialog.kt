package com.example.quiz4.ui.dbui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import com.example.quiz4.R
import com.example.quiz4.data.local.User
import com.example.quiz4.databinding.EditUserDialogBinding
import ir.mohsenafshar.apps.mkbarchitecture.data.remote.model.UserReqBody
import ir.mohsenafshar.apps.mkbarchitecture.data.remote.model.UserResponse

class EditUserDialog(private val userResponse: UserResponse) :
    DialogFragment(R.layout.edit_user_dialog) {

    private lateinit var binding: EditUserDialogBinding

    private lateinit var getUser: GetUser

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = EditUserDialogBinding.bind(view)

        val hobbies = mutableListOf<String>()
        if (binding.filmCheckBox.isChecked) hobbies.add("Film")
        if (binding.sportCheckBox.isChecked) hobbies.add("Sport")
        if (binding.footballCheckBox.isChecked) hobbies.add("Football")

        binding.submitBtn.setOnClickListener {
            val user = UserReqBody(
                binding.firstNameInput.text.toString(),
                hobbies,
                binding.lastNameInput.text.toString(),
                binding.nationalCodeInput.text.toString()
            )

            getUser.getUserFromDialog(user)

            binding.user =
                User(
                    userResponse._id,
                    userResponse.firstName,
                    userResponse.hobbies.toString(),
                    "",
                    userResponse.lastName,
                    userResponse.nationalCode
                )

            dismiss()
        }
    }

    interface GetUser {
        fun getUserFromDialog(user: UserReqBody)
    }

    fun setGetUser(getUser: GetUser) {
        this.getUser = getUser
    }
}