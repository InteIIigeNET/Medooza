import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent

class FindExtractableDuplicatesAction : AnAction("Hello") {

    val xmlPath : String = ""

    override fun actionPerformed(event: AnActionEvent) {
        val project = event.project!!
        val duplicates = DataParser().Parse(xmlPath)
        DuplicateExtractIdentifier().identify(project, duplicates)
    }
}