package com.example.postsms2slack

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.SmsMessage
import android.telephony.SmsMessage.createFromPdu
import android.util.Log

class SMSReceiver() :BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val bundle = intent.extras
        if (bundle == null) {
            Log.e("bundle error:", "bundle is null")
            return
        }
        val pdus = bundle.get("pdus") as Array<ByteArray>?
        val sb = StringBuilder()
        if (pdus == null) {
            Log.e("Error pdus", "pdus is null")
            return
        }
        for (pdu in pdus) {
            val message : SmsMessage = createFromPdu(pdu,"3gpp2")
            sb.append("address: "+ message.originatingAddress.toString() + "\n")
            sb.append("message: "+ message.displayMessageBody + "\n")
        }
        val postslack = PostSlack()
        postslack.executeThread(sb)
    }
}