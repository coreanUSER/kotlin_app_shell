package com.ghn.shell.utils

import android.app.Notification
import android.graphics.drawable.Icon
import android.os.Bundle
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification

class MyNotificationListener : NotificationListenerService() {

    override fun onNotificationPosted(sbn: StatusBarNotification?) {
        super.onNotificationPosted(sbn)

        var notification: Notification = sbn!!.notification
        var extras: Bundle = sbn.notification.extras

        // Data
        var packageName = sbn.packageName
        var id = sbn.id
        var postTime = sbn.postTime
        var title= extras.getCharSequence(Notification.EXTRA_TITLE)
        var text = extras.getCharSequence(Notification.EXTRA_TEXT)
        var smallIcon: Icon
        var largeIcon: Icon

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            smallIcon = notification.smallIcon
            largeIcon = notification.getLargeIcon()
        } else {
        }
    }

    override fun onNotificationRemoved(sbn: StatusBarNotification?) {
        super.onNotificationRemoved(sbn)
    }
}
