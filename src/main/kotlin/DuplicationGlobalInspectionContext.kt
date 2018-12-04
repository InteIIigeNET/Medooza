import com.intellij.codeInspection.GlobalInspectionContext
import com.intellij.codeInspection.InspectionEP
import com.intellij.codeInspection.InspectionProfileEntry
import com.intellij.codeInspection.ex.InspectionToolWrapper
import com.intellij.codeInspection.ex.Tools
import com.intellij.codeInspection.lang.GlobalInspectionContextExtension
import com.intellij.openapi.util.Key
import com.intellij.refactoring.extractMethod.ExtractMethodProcessor


class DuplicationGlobalInspectionContext : GlobalInspectionContextExtension<DuplicationGlobalInspectionContext> {

    companion object {
        val KEY = Key.create<DuplicationGlobalInspectionContext>("DuplicationGlobalInspectionContext")
    }


    override fun performPreRunActivities(
        globalTools: MutableList<Tools>,
        localTools: MutableList<Tools>,
        context: GlobalInspectionContext
    ) {

//        val processor = ExtractMethodProcessor(context.project, null, elements,
//        null, "", "", null)
//        processor.prepare(null)
    }


    override fun cleanup() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getID(): Key<DuplicationGlobalInspectionContext> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun performPostRunActivities(
        inspections: MutableList<InspectionToolWrapper<InspectionProfileEntry, InspectionEP>>,
        context: GlobalInspectionContext
    ) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}