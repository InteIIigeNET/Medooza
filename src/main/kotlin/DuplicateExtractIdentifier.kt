import com.intellij.openapi.project.Project
import com.intellij.refactoring.extractMethod.ExtractMethodProcessor

class DuplicateExtractIdentifier {
    fun identify(project: Project, duplicates: Array<Duplicate>){
        val extractor = PsiExtractor()
        duplicates.map { duplicate ->
            try {
                duplicate.fragments.forEach { fragment ->
                    val elements = extractor.getPsiElements(project, fragment)
                    val processor = ExtractMethodProcessor(
                        project, null, elements.toTypedArray(),
                        null, "", "", null
                    )
                    processor.prepare()
                    processor.testPrepare(null, true)
                    duplicate.extractable = true
                }
            }
            catch(t: Throwable) {
                duplicate.extractable = false
            }
        }
    }
}