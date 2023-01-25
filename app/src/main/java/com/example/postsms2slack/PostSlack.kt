package com.example.postsms2slack

import android.util.Log
import com.slack.api.Slack
import com.slack.api.methods.request.chat.ChatPostMessageRequest.ChatPostMessageRequestBuilder

class PostSlack {
    private val slack = Slack.getInstance()
    private val slackToken = Constants.SLACK_TOKEN
    private val channelId : String = Constants.SLACK_CHANNEL

    fun executeThread(message: StringBuilder) {

        Thread {
            val response =
                slack.methods(slackToken).chatPostMessage { req: ChatPostMessageRequestBuilder ->
                    req
                        .channel(channelId)
                        .text(message.toString())
                }
            if (response.isOk == false) {
                Log.e("Error", "slack responce Error")
            }
        }.start()
    }
}
