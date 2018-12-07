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
            "<duplicate cost=\"150\" hash=\"-250739912\" exp='1'>\n" +
            "    <fragment file=\"$projectPath/src/java/org/lwjgl/util/generator/opengl/GLCapabilitiesGenerator.java\" line=\"274\" start=\"12368\" end=\"13647\">\n" +
            "      <offset start=\"12371\" end=\"12433\"/>\n" +
            "      <offset start=\"12437\" end=\"12522\"/>\n" +
            "      <offset start=\"12526\" end=\"13565\"/>\n" +
            "      <offset start=\"13569\" end=\"13647\"/>\n" +
            "    </fragment>\n" +
            "    <fragment file=\"$projectPath/src/java/org/lwjgl/util/generator/opengl/GLESCapabilitiesGenerator.java\" line=\"252\" start=\"10914\" end=\"12193\">\n" +
            "      <offset start=\"10917\" end=\"10979\"/>\n" +
            "      <offset start=\"10983\" end=\"11068\"/>\n" +
            "      <offset start=\"11072\" end=\"12111\"/>\n" +
            "      <offset start=\"12115\" end=\"12193\"/>\n" +
            "    </fragment>\n" +
            "  </duplicate>" +
            "<duplicate cost=\"70\" hash=\"-461962377\" exp='1'>\n" +
            "    <fragment file=\"$projectPath/src/java/org/lwjgl/test/opengles/util/Sphere.java\" line=\"340\" start=\"9559\" end=\"9861\">\n" +
            "      <offset start=\"9563\" end=\"9578\"/>\n" +
            "      <offset start=\"9583\" end=\"9861\"/>\n" +
            "    </fragment>\n" +
            "    <fragment file=\"$projectPath/src/java/org/lwjgl/test/opengles/util/Sphere.java\" line=\"379\" start=\"10742\" end=\"11044\">\n" +
            "      <offset start=\"10746\" end=\"10761\"/>\n" +
            "      <offset start=\"10766\" end=\"11044\"/>\n" +
            "    </fragment>\n" +
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
            Assert.assertEquals(true, duplicates[2].extractable)
            Assert.assertEquals(true, duplicates[3].extractable)

        } finally {
            File(filename).delete()
        }
    }
}