package ir.mohsenafshar.apps.mkbarchitecture.data.remote.model

data class UserReqBody(
    val firstName: String,
    val hobbies: List<String>,
    val lastName: String,
    val nationalCode: String
)