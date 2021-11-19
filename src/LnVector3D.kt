import kotlin.math.PI

operator fun MultiVector3D.plus(that: LnVector3D) = LnMultiVector3D(this, that)
operator fun Vector3D.plus(that: LnVector3D) = LnMultiVector3D(MultiVector3D(vec = this), that)
operator fun BiVector3D.plus(that: LnVector3D) = LnMultiVector3D(MultiVector3D(biVec = this), that)
operator fun TriVector3D.plus(that: LnVector3D) = LnMultiVector3D(MultiVector3D(triVec = this), that)
operator fun MultiVector3D.plus(that: LnMultiVector3D) = that + this
operator fun Vector3D.plus(that: LnMultiVector3D) = that + this
operator fun BiVector3D.plus(that: LnMultiVector3D) = that + this
operator fun TriVector3D.plus(that: LnMultiVector3D) = that + this

operator fun MultiVector3D.minus(that: LnVector3D) = LnMultiVector3D(this, -that)
operator fun Vector3D.minus(that: LnVector3D) = LnMultiVector3D(MultiVector3D(vec = this), -that)
operator fun BiVector3D.minus(that: LnVector3D) = LnMultiVector3D(MultiVector3D(biVec = this), -that)
operator fun TriVector3D.minus(that: LnVector3D) = LnMultiVector3D(MultiVector3D(triVec = this), -that)
operator fun MultiVector3D.minus(that: LnMultiVector3D) = that - this
operator fun Vector3D.minus(that: LnMultiVector3D) = that - this
operator fun BiVector3D.minus(that: LnMultiVector3D) = that - this
operator fun TriVector3D.minus(that: LnMultiVector3D) = that - this

class LnMultiVector3D(var multiVec: MultiVector3D, var lnVec: LnVector3D) {

    operator fun plus(that: Double) = LnMultiVector3D(multiVec + that, lnVec)
    operator fun plus(that: Vector3D) = LnMultiVector3D(multiVec + that, lnVec)
    operator fun plus(that: BiVector3D) = LnMultiVector3D(multiVec + that, lnVec)
    operator fun plus(that: TriVector3D) = LnMultiVector3D(multiVec + that, lnVec)
    operator fun plus(that: MultiVector3D) = LnMultiVector3D(multiVec + that, lnVec)
    operator fun plus(that: LnVector3D) = multiVec + (lnVec + that)
    operator fun plus(that: LnMultiVector3D) = multiVec + that.multiVec + (lnVec + that.lnVec)

    operator fun minus(that: Double) = LnMultiVector3D(multiVec - that, lnVec)
    operator fun minus(that: Vector3D) = LnMultiVector3D(multiVec - that, lnVec)
    operator fun minus(that: BiVector3D) = LnMultiVector3D(multiVec - that, lnVec)
    operator fun minus(that: TriVector3D) = LnMultiVector3D(multiVec - that, lnVec)
    operator fun minus(that: MultiVector3D) = LnMultiVector3D(multiVec - that, lnVec)
    operator fun minus(that: LnVector3D) = multiVec + (lnVec - that)
    operator fun minus(that: LnMultiVector3D) = multiVec - that.multiVec + (lnVec - that.lnVec)

    override fun toString(): String {
        return "$multiVec + $lnVec"
    }

}
class LnVector3D(var vec: Vector3D) {

    operator fun plus(that: LnVector3D) = MultiVector3DFunc.ln(vec * that.vec)
    operator fun plus(that: Double) = LnMultiVector3D(MultiVector3D(scalar = that), this)
    operator fun plus(that: Vector3D) = LnMultiVector3D(MultiVector3D(vec = that), this)
    operator fun plus(that: BiVector3D) = LnMultiVector3D(MultiVector3D(biVec = that), this)
    operator fun plus(that: TriVector3D) = LnMultiVector3D(MultiVector3D(triVec = that), this)
    operator fun plus(that: MultiVector3D) = that + this

    operator fun minus(that: LnVector3D) = MultiVector3DFunc.ln(vec / that.vec)
    operator fun minus(that: Double) = LnMultiVector3D(MultiVector3D(scalar = -that), this)
    operator fun minus(that: Vector3D) = LnMultiVector3D(MultiVector3D(vec = -that), this)
    operator fun minus(that: BiVector3D) = LnMultiVector3D(MultiVector3D(biVec = -that), this)
    operator fun minus(that: TriVector3D) = LnMultiVector3D(MultiVector3D(triVec = -that), this)
    operator fun minus(that: MultiVector3D) = that - this

    operator fun unaryPlus() = this
    operator fun unaryMinus() = LnVector3D(1 / vec)

    override fun toString(): String {
        return "ln(${vec})"
    }
}