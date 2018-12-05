import com.intellij.testFramework.fixtures.IdeaProjectTestFixture
import org.junit.Assert
import org.junit.Test
import java.io.File


class DuplicateExtractorIdentifierTest : LightExtractorTestCase("src/test/resources/testproject/lwjgl") {
    private val data = "<root>\n" +
            "  <duplicate cost=\"172\" hash=\"1760770470\" exp='0'>\n" +
            "    <fragment file=\"$projectPath/src/java/org/lwjgl/test/opengl/sprites/SpriteShootout.java\" line=\"200\" start=\"6112\" end=\"7169\"/>\n" +
            "    <fragment file=\"$projectPath/src/java/org/lwjgl/test/opengl/sprites/SpriteShootout2P.java\" line=\"198\" start=\"6386\" end=\"7443\"/>\n" +
            "    <fragment file=\"$projectPath/src/java/org/lwjgl/test/opengl/sprites/SpriteShootoutCL.java\" line=\"253\" start=\"7529\" end=\"8586\"/>\n" +
            "  </duplicate>\n" +
            "  <duplicate cost=\"160\" hash=\"1570950581\" exp='0'>\n" +
            "    <fragment file=\"$projectPath/src/java/org/lwjgl/test/opengl/sprites/SpriteShootout.java\" line=\"618\" start=\"18268\" end=\"20773\"/>\n" +
            "    <fragment file=\"$projectPath/src/java/org/lwjgl/test/opengl/sprites/SpriteShootoutMapped.java\" line=\"689\" start=\"19465\" end=\"21970\"/>\n" +
            "  </duplicate>" +
            "</root>"

    private val filename = "test.xml"

    @Test
    fun test() {
        try {
            File(filename).createNewFile()
            File(filename).writeText(data)

            val dataParser = DataParser()
            val duplicates = dataParser.Parse(filename)

            DuplicateExtractIdentifier().identify(project, duplicates)

            Assert.assertEquals(false, duplicates[0].extractable)
            Assert.assertEquals(false, duplicates[1].extractable)

        } finally {
            File(filename).delete()
        }
    }
}