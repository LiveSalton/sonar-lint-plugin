@file:kotlin.jvm.JvmName("GradleUtils")

package tv.athena.sonar.util

import com.android.build.gradle.BaseExtension
import org.gradle.api.Project

inline fun <reified T : BaseExtension> Project.getAndroid(): T =
    extensions.getByName("android") as T

fun Project.isAndroidApp(): Boolean =
    plugins.hasPlugin("com.android.application") || plugins.hasPlugin("com.android.dynamic-feature")

fun Project.isAndroidLib(): Boolean = plugins.hasPlugin("com.android.library")

fun Any.log(msg: String) {
    println("[DisplaySonarIssue] $msg")
}

const val IS_DEBUG = true
fun Any.debug(msg: String) {
    if (IS_DEBUG) {
        println("[DisplaySonarIssue] $msg")
    }
}