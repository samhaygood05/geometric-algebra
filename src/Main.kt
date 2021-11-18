import MultiVector3D.Companion.E123
import kotlin.math.*

object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        val vector = BiVector3D(1.0, 1.0).norm()
        val eVector = MultiVector3DFunc.exp(vector)
        println(vector)
        println(eVector)
        println(MultiVector3DFunc.ln(eVector).biVec)
    }
}