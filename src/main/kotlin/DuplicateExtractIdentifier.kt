import com.intellij.openapi.project.Project
import com.intellij.refactoring.extractMethod.ExtractMethodProcessor

class DuplicateExtractIdentifier {
    fun identify(project: Project, duplicates: Array<Duplicate>){
        val extractor = PsiExtractor()
        duplicates.map { duplicate ->
            val elements = extractor.getPsiElements(project, duplicate)
            val processor = ExtractMethodProcessor(project, null, elements.toTypedArray(),
                null, "", "", null)
            duplicate.extractable = processor.prepare()
        }
    }
}