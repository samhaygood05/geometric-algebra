import MultiVector3D.Companion.E123
import kotlin.math.*

object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        val vector = BiVector3D(1.0)
        val eVector = MultiVector3DFunc.exp(vector)
        println(MultiVector3DFunc.ln(vector))
    }
}