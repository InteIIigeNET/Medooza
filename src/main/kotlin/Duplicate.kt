import com.fasterxml.jackson.annotation.JsonProperty

data class Duplicate(
    @set:JsonProperty("fragment")
    var fragments: Array<Fragment> = emptyArray(),
    var cost     : Long?           = null
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Duplicate

        if (!fragments.contentEquals(other.fragments)) return false
        if (cost != other.cost) return false

        return true
    }

    override fun hashCode(): Int {
        var result = fragments.contentHashCode()
        result = 31 * result + (cost?.hashCode() ?: 0)
        return result
    }
}