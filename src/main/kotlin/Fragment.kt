import com.fasterxml.jackson.annotation.JsonRootName

@JsonRootName("fragment")
data class Fragment(
    var file : String? = null,
    var line : Long?   = null,
    var start: Int?   = null,
    var end  : Int?   = null
)
