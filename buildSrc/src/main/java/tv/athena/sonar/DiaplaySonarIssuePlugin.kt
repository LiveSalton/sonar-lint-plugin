package tv.athena.sonar

import org.gradle.api.Project
import org.gradle.api.plugins.JavaBasePlugin
import org.sonarqube.gradle.ActionBroadcast
import org.sonarqube.gradle.SonarQubePlugin
import org.sonarqube.gradle.SonarQubeProperties
import org.sonarqube.gradle.SonarQubeTask

/**
 * @Time:2021/3/11 17:06
 * @Author:wujinsheng
 * @Description:
 */
open class DiaplaySonarIssuePlugin : SonarQubePlugin() {
    companion object {
        private const val TASK_NAME = "displaySonarIssue"
    }

    override fun configureTask(
        sonarQubeTask: SonarQubeTask,
        project: Project,
        actionBroadcastMap: MutableMap<String, ActionBroadcast<SonarQubeProperties>>
    ) {

        super.configureTask(sonarQubeTask, project, actionBroadcastMap)
        val task: DisplaySonarIssueTask =
            project.tasks.create(TASK_NAME, DisplaySonarIssueTask::class.java)
        task.description = "display issue from $project with SonarQube."
        task.group = JavaBasePlugin.VERIFICATION_GROUP
        task.mustRunAfter(sonarQubeTask)
        task.dependsOn(sonarQubeTask)
    }
}