package tv.athena.sonar

import org.apache.commons.io.FileUtils
import org.gradle.api.Project
import org.gradle.api.plugins.JavaBasePlugin
import org.sonarqube.gradle.ActionBroadcast
import org.sonarqube.gradle.SonarQubePlugin
import org.sonarqube.gradle.SonarQubeProperties
import org.sonarqube.gradle.SonarQubeTask
import org.sonarsource.sonarlint.core.StandaloneSonarLintEngineImpl
import org.sonarsource.sonarlint.core.client.api.common.Language
import org.sonarsource.sonarlint.core.client.api.common.LogOutput
import org.sonarsource.sonarlint.core.client.api.common.ProgressMonitor
import org.sonarsource.sonarlint.core.client.api.common.analysis.IssueListener
import org.sonarsource.sonarlint.core.client.api.standalone.StandaloneAnalysisConfiguration
import org.sonarsource.sonarlint.core.client.api.standalone.StandaloneGlobalConfiguration
import org.sonarsource.sonarlint.core.client.api.standalone.StandaloneSonarLintEngine
import tv.athena.sonar.util.log
import java.nio.file.Path
import java.nio.file.Paths
import java.util.*
import org.sonarsource.sonarlint.core.client.api.common.analysis.AnalysisResults

import org.sonarsource.sonarlint.core.client.api.common.analysis.ClientInputFile
import java.io.File
import java.io.FileInputStream
import java.io.InputStream
import java.net.URI
import java.nio.charset.Charset
import java.nio.charset.StandardCharsets


/**
 * @Time:2021/3/11 17:06
 * @Author:wujinsheng
 * @Description:
 */
open class DiaplaySonarIssuePlugin : SonarQubePlugin() {
    companion object {
        private const val TASK_NAME = "displaySonarIssue"
    }

    override fun apply(project: Project) {
        super.apply(project)
        test(project)
    }

    private val STANDALONE_LANGUAGES: Set<Language> = EnumSet.of(
        Language.HTML,
        Language.JS,
        Language.KOTLIN,
        Language.PHP,
        Language.PYTHON,
        Language.RUBY,
        Language.SECRETS,
        Language.TS
    )

    fun test(project: Project) {

        val configBuilder = StandaloneGlobalConfiguration.builder()
//            .addPlugins(plugins)
            .addEnabledLanguages(Language.KOTLIN, Language.JAVA)
//            .setModulesProvider(modulesRegistry::getStandaloneModules)
        val sonarlint: StandaloneSonarLintEngine =
            StandaloneSonarLintEngineImpl(configBuilder.build())
        val baseDir: Path = Paths.get(project.path)
//        val inputFiles = InputFile()
//        val config: StandaloneAnalysisConfiguration = StandaloneAnalysisConfiguration.builder()
//            .setBaseDir(baseDir)
//            .addInputFiles(inputFiles)
//            .putAllExtraProperties(props)
//            .addExcludedRules(excluded)
//            .addIncludedRules(included)
//            .addRuleParameters(params)
//            .setModuleKey(module)
//            .build()
//        val analysisResults = sonarlint.analyze(
//            config,
//            issueListener,
//            logout,
//            progressMonitor
//        )
//
//        val inputFile: ClientInputFile =
//            prepareInputFile("ParseError.java", "class ParseError {", false)
//        val issues: List<Issue> = ArrayList()
//        val standaloneAnalysisConfiguration = StandaloneAnalysisConfiguration.builder()
//            .setBaseDir(baseDir.toPath())
//            .addInputFile(inputFile)
//            .build()
//        val analysisResults: AnalysisResults =
//            sonarlintEngine.analyze(standaloneAnalysisConfiguration, issues::add, null, null)
    }
//
//    private fun prepareInputFile(
//        baseDir: Path,
//        relativePath: String,
//        content: String,
//        isTest: Boolean
//    ): ClientInputFile? {
//        val file = File(baseDir, relativePath)
//        FileUtils.write(file, content, StandardCharsets.UTF_8)
//        return createInputFile(file.toPath(), isTest)
//    }
//    private fun createInputFile(path: Path, isTest: Boolean): ClientInputFile? {
//        return object : ClientInputFile {
//            override fun getPath(): String {
//                return path.toString()
//            }
//
//            override fun relativePath(): String {
//                return baseDir.toPath().relativize(path).toString()
//            }
//
//            override fun uri(): URI {
//                return path.toUri()
//            }
//
//            override fun isTest(): Boolean {
//                return isTest
//            }
//
//            override fun getCharset(): Charset? {
//                return StandardCharsets.UTF_8
//            }
//
//            override fun <G> getClientObject(): G? {
//                return null
//            }
//
//            override fun inputStream(): InputStream {
//                return FileInputStream(path.toFile())
//            }
//
//            @kotlin.Throws(IOException::class)
//            override fun contents(): String {
//                return Files.toString(path.toFile(), StandardCharsets.UTF_8)
//            }
//        }
//    }
//    val progressMonitor = object : ProgressMonitor() {
//        override fun setMessage(msg: String) {
//            super.setMessage(msg)
//        }
//    }
//    val logout =
//        LogOutput { formattedMessage, level ->
//            log(formattedMessage)
//        }
//    val issueListener = IssueListener {
//        log(it.toString())
//    }

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