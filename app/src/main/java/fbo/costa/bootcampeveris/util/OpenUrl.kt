package fbo.costa.bootcampeveris.util

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.annotation.IntRange

object OpenUrl {
    fun start(context: Context, @IntRange(from = 1, to = 11) number: Int) {
        val url = "https://github.com/F4bioo/BootcampEveris/blob/master/" +
                "app/src/main/java/fbo/costa/bootcampeveris/repository/" +
                "RepositoryChallenge$number.kt"

        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        context.startActivity(intent)
    }
}
