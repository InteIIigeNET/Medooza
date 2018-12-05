import com.intellij.testFramework.fixtures.LightPlatformCodeInsightFixtureTestCase
import java.io.File

abstract class LightExtractorTestCase(
    val projectPath: String
) : LightPlatformCodeInsightFixtureTestCase() {

    override fun getTestDataPath(): String {
        return File(projectPath).absolutePath
    }
}