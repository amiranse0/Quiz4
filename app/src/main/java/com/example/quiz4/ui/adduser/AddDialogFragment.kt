package com.example.quiz4.ui.adduser

import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import com.example.quiz4.R
import com.example.quiz4.databinding.FragmentDialogAddBinding
import ir.mohsenafshar.apps.mkbarchitecture.data.remote.model.UserReqBody

class AddDialogFragment : DialogFragment(R.layout.fragment_dialog_add) {

    private lateinit var getUser: GetUser

    private lateinit var binding: FragmentDialogAddBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentDialogAddBinding.bind(view)

        createUser()
    }

    private fun createUser() {
        binding.submitButton.setOnClickListener {
            var hobbies = mutableListOf<String>()
            if (binding.filmCheckBox.isChecked) hobbies.add("Film")
            if (binding.sportCheckBox.isChecked) hobbies.add("Sport")
            if (binding.footballCheckBox.isChecked) hobbies.add("Football")

            getUser.getUser(
                UserReqBody(
                    binding.firstnameInput.text.toString(),
                    hobbies,
                    binding.lastnameInput.text.toString(),
                    binding.nationalInput.text.toString()
                )
            )

            dismiss()
        }
    }


    interface GetUser {
        fun getUser(user: UserReqBody)
    }

    fun setGetUser(getUser: GetUser) {
        this.getUser = getUser
    }
}