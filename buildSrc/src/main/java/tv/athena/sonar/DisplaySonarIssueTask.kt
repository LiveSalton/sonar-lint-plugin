package tv.athena.sonar

import org.gradle.api.internal.ConventionTask
import org.gradle.api.tasks.TaskAction
import org.sonar.scanner.protocol.output.ScannerReportReader
import tv.athena.sonar.util.log
import java.io.File

/**
 * Time:2021/10/14 10:05
 * Author:
 * Description:
 */
open class DisplaySonarIssueTask : ConventionTask() {

    @TaskAction
    fun run() {
        val reader =
            ScannerReportReader(File("H:\\sonar\\athena-sonar-lint-plugin\\app\\build\\sonar\\scanner-report"))
        reader.apply {
            log("start run DisplaySonarIssueTask")
            readActiveRules().forEachRemaining {
               log("readActiveRules:$it")
            }
           readAdHocRules().forEachRemaining {
               log("readAdHocRules:$it")
           }
           readContextProperties().forEachRemaining {
               log("readContextProperties:$it")
           }
//           readMetadata().forEachRemaining {
//               log("readActiveRules:$it")
//           }
           readAnalysisWarnings().forEachRemaining {
               log("readAnalysisWarnings:$it")
           }

            readComponentMeasures(3).forEachRemaining {
                log("readComponentMeasures:$it")
            }
            log("readComponent:${readComponent(3)}")
            log("readComponentChangedLines:${readComponentChangedLines(3)}")
            log("readChangesets:${readChangesets(3)}")
            readComponentCoverage(3).forEachRemaining {
                log("readComponentCoverage:$it")
            }
            readComponentDuplications(3).forEachRemaining {
                log("readComponentDuplications:$it")
            }
            readComponentExternalIssues(3).forEachRemaining {
                log("readComponentExternalIssues:$it")
            }
            readComponentIssues(3).forEachRemaining {
                log("readComponentIssues:$it")
            }

            readComponentSignificantCode(3)?.forEachRemaining {
                log("readComponentSignificantCode:$it")
            }
            readComponentSymbols(3).forEachRemaining {
                log("readComponentSymbols:$it")
            }
            readComponentSyntaxHighlighting(3).forEachRemaining {
                log("readComponentSyntaxHighlighting:$it")
            }
            readCpdTextBlocks(3).forEachRemaining {
                log("readCpdTextBlocks:$it")
            }
            log("readFileSource:${readFileSource(3)}")
            log("end run DisplaySonarIssueTask")
        }

    }
}