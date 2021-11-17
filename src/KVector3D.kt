import KVector3D.Companion.E123
import kotlin.math.*

operator fun Double.plus(that: Vector3D) = KVector3D(scalar = this, vec = that)
operator fun Double.plus(that: BiVector3D) = KVector3D(scalar = this, biVec = that)
operator fun Double.plus(that: TriVector3D) = KVector3D(scalar = this, triVec = that)
operator fun Double.plus(that: KVector3D) = that + this

operator fun Double.minus(that: Vector3D) = this + -that
operator fun Double.minus(that: BiVector3D) = this + -that
operator fun Double.minus(that: TriVector3D) = this + -that
operator fun Double.minus(that: KVector3D) = this + -that

operator fun Double.times(that: Vector3D) = Vector3D(this * that.e1, this * that.e2, this * that.e3)
operator fun Double.times(that: BiVector3D) = BiVector3D(this * that.e12, this * that.e23, this * that.e31)
operator fun Double.times(that: TriVector3D) = TriVector3D(this * that.e123)
operator fun Double.times(that: KVector3D) = KVector3D(this * that.scalar, this * that.vec, this * that.biVec, this * that.triVec)

operator fun Double.div(that: Vector3D) = this * (that / that.squaredMag())
operator fun Double.div(that: BiVector3D) = this * (that / (that dot that))
operator fun Double.div(that: TriVector3D) = this * (that / (that * that))
operator fun Double.div(that: KVector3D) = (this * that.clifConj()) / (that * that.clifConj()).scalar

operator fun Int.plus(that: Vector3D) = KVector3D(scalar = this.toDouble(), vec = that)
operator fun Int.plus(that: BiVector3D) = KVector3D(scalar = this.toDouble(), biVec = that)
operator fun Int.plus(that: TriVector3D) = KVector3D(scalar = this.toDouble(), triVec = that)
operator fun Int.plus(that: KVector3D) = that + this

operator fun Int.minus(that: Vector3D) = this + -that
operator fun Int.minus(that: BiVector3D) = this + -that
operator fun Int.minus(that: TriVector3D) = this + -that
operator fun Int.minus(that: KVector3D) = this + -that

operator fun Int.times(that: Vector3D) = Vector3D(this * that.e1, this * that.e2, this * that.e3)
operator fun Int.times(that: BiVector3D) = BiVector3D(this * that.e12, this * that.e23, this * that.e31)
operator fun Int.times(that: TriVector3D) = TriVector3D(this * that.e123)
operator fun Int.times(that: KVector3D) = KVector3D(this * that.scalar, this * that.vec, this * that.biVec, this * that.triVec)

operator fun Int.div(that: Vector3D) = this * (that / that.squaredMag())
operator fun Int.div(that: BiVector3D) = this * (that / (that dot that))
operator fun Int.div(that: TriVector3D) = this * (that / (that * that))
operator fun Int.div(that: KVector3D) = (this * that.clifConj()) / (that * that.clifConj()).scalar

class KVector3D(var scalar: Double = 0.0, var vec: Vector3D = Vector3D(), var biVec: BiVector3D = BiVector3D(), var triVec: TriVector3D = TriVector3D()) {

    companion object {
        @JvmField
        val E1 = Vector3D(e1 = 1.0)
        val E2 = Vector3D(e2 = 1.0)
        val E3 = Vector3D(e3 = 1.0)

        val E12 = BiVector3D(e12 = 1.0)
        val E23 = BiVector3D(e23 = 1.0)
        val E31 = BiVector3D(e31 = 1.0)

        val E123 = TriVector3D(e123 = 1.0)
    }

    operator fun plus(that: Double) = KVector3D(scalar + that, vec, biVec, triVec)
    operator fun plus(that: Int) = KVector3D(scalar + that, vec, biVec, triVec)
    operator fun plus(that: Vector3D) = KVector3D(scalar, vec + that, biVec, triVec)
    operator fun plus(that: BiVector3D) = KVector3D(scalar, vec, biVec + that, triVec)
    operator fun plus(that: TriVector3D) = KVector3D(scalar, vec, biVec, triVec + that)
    operator fun plus(that: KVector3D) = KVector3D(scalar + that.scalar, vec + that.vec, biVec + that.biVec, triVec + that.triVec)

    operator fun minus(that: Double) = this + -that
    operator fun minus(that: Int) = this + -that
    operator fun minus(that: Vector3D) = this + -that
    operator fun minus(that: BiVector3D) = this + -that
    operator fun minus(that: TriVector3D) = this + -that
    operator fun minus(that: KVector3D) = this + -that

    operator fun times(that: Double) = that * this
    operator fun times(that: Int) = that * this
    operator fun times(that: Vector3D) = scalar * that + vec * that + biVec * that + triVec * that
    operator fun times(that: BiVector3D) = scalar * that + vec * that + biVec * that + triVec * that
    operator fun times(that: TriVector3D) = scalar * that + vec * that + biVec * that + triVec * that
    operator fun times(that: KVector3D) = scalar * that + vec * that + biVec * that + triVec * that

    operator fun div(that: Double) = this * (1/that)
    operator fun div(that: Int) = this * (1/that)
    operator fun div(that: Vector3D) = this * (1/that)
    operator fun div(that: BiVector3D) = this * (1/that)
    operator fun div(that: TriVector3D) = this * (1/that)
    operator fun div(that: KVector3D) = this * (1/that)

    fun leftDiv(that: Vector3D) = (1 / that) * this
    fun leftDiv(that: BiVector3D) = (1 / that) * this
    fun leftDiv(that: TriVector3D) = (1 / that) * this
    fun leftDiv(that: KVector3D) = (1 / that) * this

    fun conj() = KVector3D(scalar, -vec, -biVec, -triVec)
    fun clifConj() = this.conj() * (this * this.conj()).conj()

    operator fun unaryPlus() = this
    operator fun unaryMinus() = -1.0 * this

    override fun toString(): String {
        return "$scalar + ($vec) + ($biVec) + ($triVec)"
    }
}

class Vector3D(var e1: Double = 0.0, var e2: Double = 0.0, var e3: Double = 0.0) {

    operator fun plus(that: Double) = that + this
    operator fun plus(that: Int) = that + this
    operator fun plus(that: Vector3D) = Vector3D(e1 + that.e1, e2 + that.e2, e3 + that.e3)
    operator fun plus(that: BiVector3D) = KVector3D(vec = this, biVec = that)
    operator fun plus(that: TriVector3D) = KVector3D(vec = this, triVec = that)
    operator fun plus(that: KVector3D) = that + this

    operator fun minus(that: Double) = this + -that
    operator fun minus(that: Int) = this + -that
    operator fun minus(that: Vector3D) = this + -that
    operator fun minus(that: BiVector3D) = this + -that
    operator fun minus(that: TriVector3D) = this + -that
    operator fun minus(that: KVector3D) = this + -that

    operator fun times(that: Double) = that * this
    operator fun times(that: Int) = that * this
    operator fun times(that: Vector3D) = KVector3D(
        scalar = e1 * that.e1 + e2 * that.e2 + e3 * that.e3,
        biVec = BiVector3D(
            e12 = e1 * that.e2 - e2 * that.e1,
            e23 = e2 * that.e3 - e3 * that.e2,
            e31 = e3 * that.e1 - e1 * that.e3
        )
    )
    operator fun times(that: BiVector3D) = KVector3D(
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
    operator fun times(that: KVector3D) = this * that.scalar + this * that.vec + this * that.biVec + this * that.triVec

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
    operator fun div(that: KVector3D) = this * (1/that)

    fun leftDiv(that: Vector3D) = (1/that) * this
    fun leftDiv(that: BiVector3D) = (1/that) * this
    fun leftDiv(that: TriVector3D) = (1/that) * this
    fun leftDiv(that: KVector3D) = (1/that) * this

    operator fun unaryPlus() = this
    operator fun unaryMinus() = -1.0 * this

    fun squaredMag() = this dot this
    fun magnitude() = sqrt(squaredMag())
    fun norm() = this / magnitude()

    fun cosine(that: Vector3D) = (this dot that) / (this.magnitude() * that.magnitude())
    fun sineSquared(that: Vector3D) = 1 - cosine(that).pow(2)
    fun sine(that: Vector3D) = sqrt(sineSquared(that))
    fun tangent(that: Vector3D) = cosine(that)/sine(that)
    fun angle(that: Vector3D) = acos(cosine(that))

    override fun toString(): String {
        return "$e1\u200Ee1 + $e2\u200Ee2 + $e3\u200Ee3"
    }
}

class BiVector3D(var e12: Double = 0.0, var e23: Double = 0.0, var e31: Double = 0.0) {

    operator fun plus(that: Double) = that + this
    operator fun plus(that: Int) = that + this
    operator fun plus(that: Vector3D) = that + this
    operator fun plus(that: BiVector3D) = BiVector3D(e12 + that.e12, e23 + that.e23, e31 + that.e31)
    operator fun plus(that: TriVector3D) = KVector3D(biVec = this, triVec = that)
    operator fun plus(that: KVector3D) = that + this

    operator fun minus(that: Double) = this + -that
    operator fun minus(that: Int) = this + -that
    operator fun minus(that: Vector3D) = this + -that
    operator fun minus(that: BiVector3D) = this + -that
    operator fun minus(that: TriVector3D) = this + -that
    operator fun minus(that: KVector3D) = this + -that

    operator fun times(that: Double) = that * this
    operator fun times(that: Int) = that * this
    operator fun times(that: Vector3D) = KVector3D(
        vec = Vector3D(
        e1 = e12 * that.e2 - e31 * that.e3,
        e2 = e23 * that.e3 - e12 * that.e1,
        e3 = e31 * that.e1 - e23 * that.e2
        ),
        triVec = TriVector3D(that.e1 * e23 + that.e2 * e31 + that.e3 * e12)
    )
    operator fun times(that: BiVector3D) = KVector3D(
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
    operator fun times(that: KVector3D) = this * that.scalar + this * that.vec + this * that.biVec + this * that.triVec

    infix fun dot(that: Vector3D) = Vector3D(e12 * that.e2 - e31 * that.e3, e23 * that.e3 - e12 * that.e1, e31 * that.e1 - e23 * that.e2)
    infix fun dot(that: BiVector3D) = -e12 * that.e12 - e23 * that.e23 - e31 * that.e31

    infix fun cross(that: BiVector3D) = BiVector3D(e31 * that.e23 - e23 * that.e31, e12 * that.e31 - e31 * that.e12, e23 * that.e12 - e12 * that.e23)

    infix fun wedge(that: Vector3D) = TriVector3D(that.e1 * e23 + that.e2 * e31 + that.e3 * e12)

    operator fun div(that: Double) = this * (1/that)
    operator fun div(that: Int) = this * (1/that)
    operator fun div(that: Vector3D) = this * (1/that)
    operator fun div(that: BiVector3D) = this * (1/that)
    operator fun div(that: TriVector3D) = this * (1/that)
    operator fun div(that: KVector3D) = this * (1/that)

    fun leftDiv(that: Vector3D) = (1/that) * this
    fun leftDiv(that: BiVector3D) = (1/that) * this
    fun leftDiv(that: TriVector3D) = (1/that) * this
    fun leftDiv(that: KVector3D) = (1/that) * this

    operator fun unaryPlus() = this
    operator fun unaryMinus() = -1.0 * this

    fun squaredMag(): Double {
        return if (e12 != 0.0) Vector3D(e1 = e12, e3 = -e23).squaredMag() * Vector3D(e2 = 1.0, e3 = -e31 / e12).squaredMag() * Vector3D(e1 = e12, e3 = -e23).sineSquared(Vector3D(e2 = 1.0, e3 = -e31 / e12))
        else Vector3D(e1 = -e31, e2 = e23).squaredMag() * Vector3D(e1 = -e31, e2 = e23).sineSquared(Vector3D(e3 = 1.0))
    }
    fun magnitude() = sqrt(squaredMag())
    fun norm() = this / this.magnitude()

    override fun toString(): String {
        return "$e12\u200Ee12 + $e23\u200Ee23 + $e31\u200Ee31"
    }
}

class TriVector3D(var e123: Double = 0.0) {

    operator fun plus(that: Double) = that + this
    operator fun plus(that: Int) = that + this
    operator fun plus(that: Vector3D) = that + this
    operator fun plus(that: BiVector3D) = that + this
    operator fun plus(that: TriVector3D) = TriVector3D(e123 + that.e123)
    operator fun plus(that: KVector3D) = that + this

    operator fun minus(that: Double) = this + -that
    operator fun minus(that: Int) = this + -that
    operator fun minus(that: Vector3D) = this + -that
    operator fun minus(that: BiVector3D) = this + -that
    operator fun minus(that: TriVector3D) = this + -that
    operator fun minus(that: KVector3D) = this + -that

    operator fun times(that: Double) = that * this
    operator fun times(that: Int) = that * this
    operator fun times(that: Vector3D) = that * this
    operator fun times(that: BiVector3D) = that * this
    operator fun times(that: TriVector3D) = -e123 * that.e123
    operator fun times(that: KVector3D) = this * that.scalar + this * that.vec + this * that.biVec + this * that.triVec

    operator fun div(that: Double) = this * (1/that)
    operator fun div(that: Int) = this * (1/that)
    operator fun div(that: Vector3D) = this * (1/that)
    operator fun div(that: BiVector3D) = this * (1/that)
    operator fun div(that: TriVector3D) = this * (1/that)
    operator fun div(that: KVector3D) = this * (1/that)

    fun leftDiv(that: Vector3D) = (1/that) * this
    fun leftDiv(that: BiVector3D) = (1/that) * this
    fun leftDiv(that: TriVector3D) = (1/that) * this
    fun leftDiv(that: KVector3D) = (1/that) * this

    operator fun unaryPlus() = this
    operator fun unaryMinus() = -1.0 * this

    fun squaredMag() = this.e123.pow(2)
    fun magnitude() = abs(e123)
    fun norm() = this / magnitude()

    override fun toString(): String {
        return "$e123\u200Ee123"
    }
}