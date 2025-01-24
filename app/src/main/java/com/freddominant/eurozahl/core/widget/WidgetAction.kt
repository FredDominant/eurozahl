package com.freddominant.eurozahl.core.widget

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.glance.GlanceId
import androidx.glance.action.ActionParameters
import androidx.glance.appwidget.action.ActionCallback

class OpenLotto24Action : ActionCallback {
    override suspend fun onAction(
        context: Context,
        glanceId: GlanceId,
        parameters: ActionParameters
    ) {
        val url = parameters[OpenUrlKey] ?: return
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url)).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }
        try {
            context.startActivity(intent)
        } catch (_: Exception) {
        }
    }

    companion object {
        val OpenUrlKey = ActionParameters.Key<String>("openUrlKey")
        val URL = "https://www.lotto24.de/"
    }
}