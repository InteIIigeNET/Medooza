import com.intellij.codeInspection.GlobalInspectionContext
import com.intellij.openapi.vfs.LocalFileSystem
import com.intellij.psi.PsiExpression
import com.intellij.psi.PsiManager
import com.intellij.psi.util.PsiTreeUtil

class PsiExtractor(val duplicate: Duplicate) {

    fun getPsiElements(context: GlobalInspectionContext) : List<PsiExpression> {

        return duplicate.fragments.map { fragment ->
            val virtualFile = LocalFileSystem.getInstance().findFileByPath(fragment.file)
            val psiFile = PsiManager.getInstance(context.project).findFile(virtualFile!!)
            val psiElement = PsiTreeUtil.findElementOfClassAtRange(psiFile!!, fragment.start, fragment.end, PsiExpression::class.java)
             psiElement!!
        }

    }
}