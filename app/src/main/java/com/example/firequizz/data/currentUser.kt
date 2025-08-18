package com.example.firequizz.data

import com.example.firequizz.leaderboard.UserModel

object currentUser {
    var id: String = ""
    var name: String = ""
    var pic: String = ""
    var score: Int = 0

    fun setUserData(user: UserModel) {
        this.id = user.id
        this.name = user.name
        this.pic = user.pic
        this.score = user.score

    }
}