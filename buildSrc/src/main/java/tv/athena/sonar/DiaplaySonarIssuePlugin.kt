package tv.athena.sonar

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.api.plugins.JavaBasePlugin
import tv.athena.sonar.util.debug
import tv.athena.sonar.util.isAndroidApp
import tv.athena.sonar.util.isAndroidLib
import tv.athena.sonar.util.log
import java.util.concurrent.Callable
import java.util.stream.Collectors

/**
 * @Time:2021/3/11 17:06
 * @Author:wujinsheng
 * @Description:
 */
open class DiaplaySonarIssuePlugin : Plugin<Project> {
    companion object {
        private const val TASK_NAME = "displaySonarIssue"
        private const val TASK_NAME_SONAR_QUBE = "sonarqube"
    }

    override fun apply(project: Project) {
        if (!project.isAndroidApp() && !project.isAndroidLib()) {
            log("======not android, project = $project")
            return
        }
        val task: DisplaySonarIssueTask =
            project.tasks.create(TASK_NAME, DisplaySonarIssueTask::class.java)
        task.description = "display issue from $project with SonarQube."
        task.group = JavaBasePlugin.VERIFICATION_GROUP
        val sonarQubeTask = getSonarQubeTasks(project)
        log("find sonarqube task:${sonarQubeTask?.call()}")
        task.mustRunAfter(sonarQubeTask)
        task.dependsOn(sonarQubeTask)
    }

    private fun getSonarQubeTasks(project: Project): Callable<Iterable<Task>>? {
        return Callable {
            project.allprojects.stream()
                .filter { p: Project ->
                    debug("projects:${p.plugins.size}")
                    p.plugins.forEach {
                        debug("project:${it}")
                    }
                    val ret = p.plugins.hasPlugin("org.sonarqube")
                    log("find sonarqube plugin?:$ret")
                    ret
                }
                .map { p: Project ->
                    debug("taskname:${p.tasks.namer}")
                    p.tasks.getByName(TASK_NAME_SONAR_QUBE)
                }
                .collect(Collectors.toList())
        }
    }
}