import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.LocalFileSystem
import com.intellij.psi.PsiExpression
import com.intellij.psi.PsiManager
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.compiler.progress.CompilerTask.getTextRange
import com.intellij.usages.ChunkExtractor.getStartOffset
import com.intellij.psi.PsiWhiteSpace
import com.intellij.psi.PsiElement



class PsiExtractor() {

    fun getPsiElements(project: Project, duplicate: Duplicate) : List<PsiExpression> {

        return duplicate.fragments.map { fragment ->

            val virtualFile = LocalFileSystem.getInstance().findFileByPath(fragment.file!!)
            val psiFile = PsiManager.getInstance(project).findFile(virtualFile!!)
            var startOffset = fragment.start!!
            var endOffset = fragment.end!!
            val startElement = psiFile!!.findElementAt(startOffset)
            val endElement = psiFile!!.findElementAt(endOffset - 1)
            if (startElement is PsiWhiteSpace) {
                startOffset = startElement.getTextRange().endOffset
            }
            if (endElement is PsiWhiteSpace) {
                endOffset = endElement.getTextRange().startOffset
            }
            val psiElement = PsiTreeUtil.findElementOfClassAtRange(
                psiFile!!,
                startOffset,
                endOffset,
                PsiExpression::class.java
            )
            psiElement!!

        }

    }
}