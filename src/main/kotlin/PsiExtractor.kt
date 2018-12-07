import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.LocalFileSystem
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.compiler.progress.CompilerTask.getTextRange
import com.intellij.psi.*
import com.intellij.usages.ChunkExtractor.getStartOffset


class PsiExtractor {

    fun getPsiElements(project: Project, fragment: Fragment) : List<PsiElement> {
        val virtualFile = LocalFileSystem.getInstance().findFileByPath(fragment.file!!)
        val psiFile = PsiManager.getInstance(project).findFile(virtualFile!!)

        return getPsiElementFromRange(psiFile!!, fragment.start!!, fragment.end!!)
    }

    private fun getPsiElementFromRange(psiFile: PsiFile, startOffset: Int, endOffset: Int) : List<PsiElement>{
        var element = psiFile.findElementAt(startOffset)

        var psiElements : MutableList<PsiElement> = mutableListOf(element!!)

        var end = element.textRange.endOffset

        while(end < endOffset)
        {
            element = psiFile.findElementAt(end)
            if (element != null) {
                psiElements.add(element)
                end = element.textRange.endOffset
            }
            else{
                ++end
            }
        }

        return psiElements
    }
}