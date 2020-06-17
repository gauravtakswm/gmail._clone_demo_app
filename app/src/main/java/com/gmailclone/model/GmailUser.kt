package com.gmailclone.model

import java.io.Serializable

data class GmailUser(var username: String, var password: String, var full_name: String, var first_name: String, var last_name: String, var gmail_social_id: String):Serializable {
}