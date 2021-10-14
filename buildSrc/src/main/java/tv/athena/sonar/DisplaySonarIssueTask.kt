package tv.athena.sonar

import org.gradle.api.internal.ConventionTask
import org.gradle.api.tasks.TaskAction
import tv.athena.sonar.util.log

/**
 * Time:2021/10/14 10:05
 * Author:
 * Description:
 */
open class DisplaySonarIssueTask : ConventionTask() {

    @TaskAction
    fun run() {
        log("start run DisplaySonarIssueTask")
    }
}