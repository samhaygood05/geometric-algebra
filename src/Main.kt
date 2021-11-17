import KVector3D.Companion.E1
import KVector3D.Companion.E12

object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        val vector = KVector3D(1.0, Vector3D(1.0, 0.0, 1.0), BiVector3D(0.0, 0.0, 1.0), TriVector3D(1.0))
        println(vector.clifConj() * vector)
        println(vector * vector.clifConj())
    }
}