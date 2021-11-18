import kotlin.math.PI

class LnMultiVector3D(multiVec: MultiVector3D, lnVec: LnVector3D) {
    operator fun MultiVector3D.plus(that: LnVector3D) = LnMultiVector3D(this, that)
}
class LnVector3D(var e1: Double = 0.0, var e2: Double = 0.0, var e3: Double = 0.0) {

    operator fun plus(that: LnVector3D) = MultiVector3DFunc.ln(Vector3D(e1, e2, e3) * Vector3D(that.e1, that.e2, that.e3))
    operator fun minus(that: LnVector3D) = MultiVector3DFunc.ln(Vector3D(e1, e2, e3) / Vector3D(that.e1, that.e2, that.e3))

    override fun toString(): String {
        return "ln(${Vector3D(e1, e2, e3)})"
    }
}