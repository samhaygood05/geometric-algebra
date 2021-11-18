import MultiVector3D.Companion.E123
import Vector3D.Companion.NaN
import kotlin.math.*

operator fun Double.plus(that: Vector3D) = MultiVector3D(scalar = this, vec = that)
operator fun Double.plus(that: BiVector3D) = MultiVector3D(scalar = this, biVec = that)
operator fun Double.plus(that: TriVector3D) = MultiVector3D(scalar = this, triVec = that)
operator fun Double.plus(that: MultiVector3D) = that + this

operator fun Double.minus(that: Vector3D) = this + -that
operator fun Double.minus(that: BiVector3D) = this + -that
operator fun Double.minus(that: TriVector3D) = this + -that
operator fun Double.minus(that: MultiVector3D) = this + -that

operator fun Double.times(that: Vector3D) = Vector3D(this * that.e1, this * that.e2, this * that.e3)
operator fun Double.times(that: BiVector3D) = BiVector3D(this * that.e12, this * that.e23, this * that.e31)
operator fun Double.times(that: TriVector3D) = TriVector3D(this * that.e123)
operator fun Double.times(that: MultiVector3D) = MultiVector3D(this * that.scalar, this * that.vec, this * that.biVec, this * that.triVec)

operator fun Double.div(that: Vector3D) = this * (that / that.squaredMag())
operator fun Double.div(that: BiVector3D) = this * (that / that.squaredMag())
operator fun Double.div(that: TriVector3D) = this * (that / that.squaredMag())
operator fun Double.div(that: MultiVector3D) = this * (that / that.squaredMag())

operator fun Int.plus(that: Vector3D) = MultiVector3D(scalar = this.toDouble(), vec = that)
operator fun Int.plus(that: BiVector3D) = MultiVector3D(scalar = this.toDouble(), biVec = that)
operator fun Int.plus(that: TriVector3D) = MultiVector3D(scalar = this.toDouble(), triVec = that)
operator fun Int.plus(that: MultiVector3D) = that + this

operator fun Int.minus(that: Vector3D) = this + -that
operator fun Int.minus(that: BiVector3D) = this + -that
operator fun Int.minus(that: TriVector3D) = this + -that
operator fun Int.minus(that: MultiVector3D) = this + -that

operator fun Int.times(that: Vector3D) = Vector3D(this * that.e1, this * that.e2, this * that.e3)
operator fun Int.times(that: BiVector3D) = BiVector3D(this * that.e12, this * that.e23, this * that.e31)
operator fun Int.times(that: TriVector3D) = TriVector3D(this * that.e123)
operator fun Int.times(that: MultiVector3D) = MultiVector3D(this * that.scalar, this * that.vec, this * that.biVec, this * that.triVec)

operator fun Int.div(that: Vector3D) = this * (that / that.squaredMag())
operator fun Int.div(that: BiVector3D) = this * (that / that.squaredMag())
operator fun Int.div(that: TriVector3D) = this * (that / that.squaredMag())
operator fun Int.div(that: MultiVector3D) = this * (that / that.squaredMag())

class MultiVector3D(var scalar: Double = 0.0, var vec: Vector3D = Vector3D(), var biVec: BiVector3D = BiVector3D(), var triVec: TriVector3D = TriVector3D()): KVector {

    companion object {
        @JvmField
        val E1 = Vector3D(e1 = 1.0)
        val E2 = Vector3D(e2 = 1.0)
        val E3 = Vector3D(e3 = 1.0)

        val E12 = BiVector3D(e12 = 1.0)
        val E23 = BiVector3D(e23 = 1.0)
        val E31 = BiVector3D(e31 = 1.0)

        val E123 = TriVector3D(e123 = 1.0)

        val NaN = MultiVector3D(Double.NaN, Vector3D.NaN, BiVector3D.NaN, TriVector3D.NaN)
    }

    operator fun plus(that: Double) = MultiVector3D(scalar + that, vec, biVec, triVec)
    operator fun plus(that: Int) = MultiVector3D(scalar + that, vec, biVec, triVec)
    operator fun plus(that: Vector3D) = MultiVector3D(scalar, vec + that, biVec, triVec)
    operator fun plus(that: BiVector3D) = MultiVector3D(scalar, vec, biVec + that, triVec)
    operator fun plus(that: TriVector3D) = MultiVector3D(scalar, vec, biVec, triVec + that)
    operator fun plus(that: MultiVector3D) = MultiVector3D(scalar + that.scalar, vec + that.vec, biVec + that.biVec, triVec + that.triVec)

    operator fun minus(that: Double) = this + -that
    operator fun minus(that: Int) = this + -that
    operator fun minus(that: Vector3D) = this + -that
    operator fun minus(that: BiVector3D) = this + -that
    operator fun minus(that: TriVector3D) = this + -that
    operator fun minus(that: MultiVector3D) = this + -that

    operator fun times(that: Double) = that * this
    operator fun times(that: Int) = that * this
    operator fun times(that: Vector3D) = scalar * that + vec * that + biVec * that + triVec * that
    operator fun times(that: BiVector3D) = scalar * that + vec * that + biVec * that + triVec * that
    operator fun times(that: TriVector3D) = scalar * that + vec * that + biVec * that + triVec * that
    operator fun times(that: MultiVector3D) = scalar * that + vec * that + biVec * that + triVec * that

    operator fun div(that: Double) = this * (1/that)
    operator fun div(that: Int) = this * (1/that)
    operator fun div(that: Vector3D) = this * (1/that)
    operator fun div(that: BiVector3D) = this * (1/that)
    operator fun div(that: TriVector3D) = this * (1/that)
    operator fun div(that: MultiVector3D) = this * (1/that)

    fun leftDiv(that: Vector3D) = (1 / that) * this
    fun leftDiv(that: BiVector3D) = (1 / that) * this
    fun leftDiv(that: TriVector3D) = (1 / that) * this
    fun leftDiv(that: MultiVector3D) = (1 / that) * this


    operator fun unaryPlus() = this
    operator fun unaryMinus() = -1.0 * this

    fun reversion() = MultiVector3D(scalar, vec, -biVec, -triVec)

    override fun squaredMag() = (reversion() * this).scalar
    override fun magnitude() = sqrt(squaredMag())
    override fun norm() = this / magnitude()

    override fun toString(): String {
        return "$scalar + ($vec) + ($biVec) + ($triVec)"
    }
}
class Vector3D(var e1: Double = 0.0, var e2: Double = 0.0, var e3: Double = 0.0): KVector {

    companion object {
        @JvmField
        val NaN = Vector3D(Double.NaN, Double.NaN, Double.NaN)
    }

    operator fun plus(that: Double) = that + this
    operator fun plus(that: Int) = that + this
    operator fun plus(that: Vector3D) = Vector3D(e1 + that.e1, e2 + that.e2, e3 + that.e3)
    operator fun plus(that: BiVector3D) = MultiVector3D(vec = this, biVec = that)
    operator fun plus(that: TriVector3D) = MultiVector3D(vec = this, triVec = that)
    operator fun plus(that: MultiVector3D) = that + this

    operator fun minus(that: Double) = this + -that
    operator fun minus(that: Int) = this + -that
    operator fun minus(that: Vector3D) = this + -that
    operator fun minus(that: BiVector3D) = this + -that
    operator fun minus(that: TriVector3D) = this + -that
    operator fun minus(that: MultiVector3D) = this + -that

    operator fun times(that: Double) = that * this
    operator fun times(that: Int) = that * this
    operator fun times(that: Vector3D) = MultiVector3D(
        scalar = e1 * that.e1 + e2 * that.e2 + e3 * that.e3,
        biVec = BiVector3D(
            e12 = e1 * that.e2 - e2 * that.e1,
            e23 = e2 * that.e3 - e3 * that.e2,
            e31 = e3 * that.e1 - e1 * that.e3
        )
    )
    operator fun times(that: BiVector3D) = MultiVector3D(
        vec = Vector3D(
            e1 = e3 * that.e31 - e2 * that.e12,
            e2 = e1 * that.e12 - e3 * that.e23,
            e3 = e2 * that.e23 - e1 * that.e31
        ),
        triVec = TriVector3D(e1 * that.e23 + e2 * that.e31 + e3 * that.e12)
    )
    operator fun times(that: TriVector3D) = BiVector3D(
            e12 = e3 * that.e123,
            e23 = e1 * that.e123,
            e31 = e2 * that.e123
    )
    operator fun times(that: MultiVector3D) = this * that.scalar + this * that.vec + this * that.biVec + this * that.triVec

    infix fun dot(that: Vector3D) = e1 * that.e1 + e2 * that.e2 + e3 * that.e3
    infix fun dot(that: BiVector3D) = Vector3D(e3 * that.e31 - e2 * that.e12, e1 * that.e12 - e3 * that.e23, e2 * that.e23 - e1 * that.e31)

    infix fun cross(that: Vector3D) = -(this wedge that) * E123

    infix fun wedge(that: Vector3D) = BiVector3D(e1 * that.e2 - e2 * that.e1, e2 * that.e3 - e3 * that.e2, e3 * that.e1 - e1 * that.e3)
    infix fun wedge(that: BiVector3D) = TriVector3D(e1 * that.e23 + e2 * that.e31 + e3 * that.e12)

    operator fun div(that: Double) = this * (1/that)
    operator fun div(that: Int) = this * (1/that)
    operator fun div(that: Vector3D) = this * (1/that)
    operator fun div(that: BiVector3D) = this * (1/that)
    operator fun div(that: TriVector3D) = this * (1/that)
    operator fun div(that: MultiVector3D) = this * (1/that)

    fun leftDiv(that: Vector3D) = (1/that) * this
    fun leftDiv(that: BiVector3D) = (1/that) * this
    fun leftDiv(that: TriVector3D) = (1/that) * this
    fun leftDiv(that: MultiVector3D) = (1/that) * this

    operator fun unaryPlus() = this
    operator fun unaryMinus() = -1.0 * this

    override fun squaredMag() = this dot this
    override fun magnitude() = sqrt(squaredMag())
    override fun norm(): Vector3D {
        return if (magnitude() == 0.0) NaN
        else this / magnitude()
    }

    fun cosine(that: Vector3D) = (this dot that) / (this.magnitude() * that.magnitude())
    fun sineSquared(that: Vector3D) = 1 - cosine(that).pow(2)
    fun sine(that: Vector3D) = sqrt(sineSquared(that))
    fun tangent(that: Vector3D) = cosine(that)/sine(that)
    fun angle(that: Vector3D) = acos(cosine(that))

    override fun toString(): String {
        return if (isNaN()) "NaN"
        else "$e1\u200Eσ1 + $e2\u200Eσ2 + $e3\u200Eσ3"
    }

    fun isNaN() = (e1.isNaN() || e2.isNaN() || e3.isNaN())
}
class BiVector3D(var e12: Double = 0.0, var e23: Double = 0.0, var e31: Double = 0.0): KVector {

    companion object {
        @JvmField
        val NaN = BiVector3D(Double.NaN, Double.NaN, Double.NaN)
    }

    operator fun plus(that: Double) = that + this
    operator fun plus(that: Int) = that + this
    operator fun plus(that: Vector3D) = that + this
    operator fun plus(that: BiVector3D) = BiVector3D(e12 + that.e12, e23 + that.e23, e31 + that.e31)
    operator fun plus(that: TriVector3D) = MultiVector3D(biVec = this, triVec = that)
    operator fun plus(that: MultiVector3D) = that + this

    operator fun minus(that: Double) = this + -that
    operator fun minus(that: Int) = this + -that
    operator fun minus(that: Vector3D) = this + -that
    operator fun minus(that: BiVector3D) = this + -that
    operator fun minus(that: TriVector3D) = this + -that
    operator fun minus(that: MultiVector3D) = this + -that

    operator fun times(that: Double) = that * this
    operator fun times(that: Int) = that * this
    operator fun times(that: Vector3D) = MultiVector3D(
        vec = Vector3D(
        e1 = e12 * that.e2 - e31 * that.e3,
        e2 = e23 * that.e3 - e12 * that.e1,
        e3 = e31 * that.e1 - e23 * that.e2
        ),
        triVec = TriVector3D(that.e1 * e23 + that.e2 * e31 + that.e3 * e12)
    )
    operator fun times(that: BiVector3D) = MultiVector3D(
        scalar = -e12 * that.e12 - e23 * that.e23 - e31 * that.e31,
        biVec = BiVector3D(
            e12 = e31 * that.e23 - e23 * that.e31,
            e23 = e12 * that.e31 - e31 * that.e12,
            e31 = e23 * that.e12 - e12 * that.e23
        )
    )
    operator fun times(that: TriVector3D) = Vector3D(
        e1 = -e23 * that.e123,
        e2 = -e31 * that.e123,
        e3 = -e12 * that.e123
    )
    operator fun times(that: MultiVector3D) = this * that.scalar + this * that.vec + this * that.biVec + this * that.triVec

    infix fun dot(that: Vector3D) = Vector3D(e12 * that.e2 - e31 * that.e3, e23 * that.e3 - e12 * that.e1, e31 * that.e1 - e23 * that.e2)
    infix fun dot(that: BiVector3D) = -e12 * that.e12 - e23 * that.e23 - e31 * that.e31

    infix fun cross(that: BiVector3D) = BiVector3D(e31 * that.e23 - e23 * that.e31, e12 * that.e31 - e31 * that.e12, e23 * that.e12 - e12 * that.e23)

    infix fun wedge(that: Vector3D) = TriVector3D(that.e1 * e23 + that.e2 * e31 + that.e3 * e12)

    operator fun div(that: Double) = this * (1/that)
    operator fun div(that: Int) = this * (1/that)
    operator fun div(that: Vector3D) = this * (1/that)
    operator fun div(that: BiVector3D) = this * (1/that)
    operator fun div(that: TriVector3D) = this * (1/that)
    operator fun div(that: MultiVector3D) = this * (1/that)

    fun leftDiv(that: Vector3D) = (1/that) * this
    fun leftDiv(that: BiVector3D) = (1/that) * this
    fun leftDiv(that: TriVector3D) = (1/that) * this
    fun leftDiv(that: MultiVector3D) = (1/that) * this

    operator fun unaryPlus() = this
    operator fun unaryMinus() = -1.0 * this

    override fun squaredMag(): Double = -(this dot this)
    override fun magnitude() = sqrt(squaredMag())
    override fun norm(): BiVector3D {
        return if (magnitude() == 0.0) NaN
        else this / this.magnitude()
    }

    override fun toString(): String {
        return if (isNaN()) "NaN"
        else "$e12\u200Eσ12 + $e23\u200Eσ23 + $e31\u200Eσ31"
    }

    fun isNaN() = (e12.isNaN() || e23.isNaN() || e31.isNaN())
}
class TriVector3D(var e123: Double = 0.0): KVector {

    companion object {
        val NaN = TriVector3D(Double.NaN)
    }

    operator fun plus(that: Double) = that + this
    operator fun plus(that: Int) = that + this
    operator fun plus(that: Vector3D) = that + this
    operator fun plus(that: BiVector3D) = that + this
    operator fun plus(that: TriVector3D) = TriVector3D(e123 + that.e123)
    operator fun plus(that: MultiVector3D) = that + this

    operator fun minus(that: Double) = this + -that
    operator fun minus(that: Int) = this + -that
    operator fun minus(that: Vector3D) = this + -that
    operator fun minus(that: BiVector3D) = this + -that
    operator fun minus(that: TriVector3D) = this + -that
    operator fun minus(that: MultiVector3D) = this + -that

    operator fun times(that: Double) = that * this
    operator fun times(that: Int) = that * this
    operator fun times(that: Vector3D) = that * this
    operator fun times(that: BiVector3D) = that * this
    operator fun times(that: TriVector3D) = -e123 * that.e123
    operator fun times(that: MultiVector3D) = this * that.scalar + this * that.vec + this * that.biVec + this * that.triVec

    operator fun div(that: Double) = this * (1/that)
    operator fun div(that: Int) = this * (1/that)
    operator fun div(that: Vector3D) = this * (1/that)
    operator fun div(that: BiVector3D) = this * (1/that)
    operator fun div(that: TriVector3D) = this * (1/that)
    operator fun div(that: MultiVector3D) = this * (1/that)

    fun leftDiv(that: Vector3D) = (1/that) * this
    fun leftDiv(that: BiVector3D) = (1/that) * this
    fun leftDiv(that: TriVector3D) = (1/that) * this
    fun leftDiv(that: MultiVector3D) = (1/that) * this

    operator fun unaryPlus() = this
    operator fun unaryMinus() = -1.0 * this

    override fun squaredMag() = e123.pow(2)
    override fun magnitude() = abs(e123)
    override fun norm():TriVector3D {
        return if (magnitude() == 0.0 || magnitude().isNaN()) NaN
        else this / magnitude()
    }

    override fun toString(): String {
        return if (isNaN()) "NaN"
        else "$e123\u200Eσ123"
    }

    fun isNaN() = this.e123.isNaN()
}