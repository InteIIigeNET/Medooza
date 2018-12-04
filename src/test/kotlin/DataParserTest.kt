import org.junit.Assert
import org.junit.Test
import java.io.File

class DataParserTest {

    private val data = "<root>\n" +
            "  <duplicate cost=\"565\" hash=\"-459637940\" exp='0'>\n" +
            "    <fragment file=\"test\" line=\"16\" start=\"662\" end=\"5515\"/>\n" +
            "    <fragment file=\"test2\" line=\"16\" start=\"664\" end=\"5517\"/>\n" +
            "  </duplicate>\n" +
            "  <duplicate cost=\"172\" hash=\"1760770470\" exp='0'>\n" +
            "    <fragment file=\"file://src/java/org/lwjgl/test/opengl/sprites/SpriteShootout.java\" line=\"200\" start=\"6112\" end=\"7169\"/>\n" +
            "    <fragment file=\"file://src/java/org/lwjgl/test/opengl/sprites/SpriteShootout2P.java\" line=\"198\" start=\"6386\" end=\"7443\"/>\n" +
            "    <fragment file=\"file:///src/java/org/lwjgl/test/opengl/sprites/SpriteShootoutCL.java\" line=\"253\" start=\"7529\" end=\"8586\"/>\n" +
            "  </duplicate>\n" +
            "  <duplicate cost=\"160\" hash=\"1570950581\" exp='0'>\n" +
            "    <fragment file=\"file://src/java/org/lwjgl/test/opengl/sprites/SpriteShootout.java\" line=\"618\" start=\"18268\" end=\"20773\"/>\n" +
            "    <fragment file=\"file://src/java/org/lwjgl/test/opengl/sprites/SpriteShootoutMapped.java\" line=\"689\" start=\"19465\" end=\"21970\"/>\n" +
            "  </duplicate>" +
            "</root>"

    private val filename = "test.xml"

    @Test
    fun test() {
        try {
            File(filename).createNewFile()
            File(filename).writeText(data)

            val dataParser: IDataParser = DataParser()
            val result = dataParser.Parse(filename)

            Assert.assertEquals(3, result.size)
            Assert.assertEquals(
                Duplicate(
                    cost = 565,
                    fragments = arrayOf(
                        Fragment(file = "test", line = 16, start = 662, end = 5515),
                        Fragment(file = "test2", line = 16, start = 664, end = 5517)
                    )
                ),
                result[0])

        } finally {
            File(filename).delete()
        }
    }
}
