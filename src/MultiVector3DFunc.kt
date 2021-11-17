import MultiVector3D.Companion.E123
import kotlin.math.*

object MultiVector3DFunc {

    fun exp(that: Vector3D) = exp(that.magnitude())
    fun exp(that: BiVector3D) = cos(that.magnitude()) + (sin(that.magnitude()) * that.norm())
    fun exp(that: TriVector3D) = cos(that.magnitude()) + (sin(that.magnitude()) * E123)
    fun exp(that: MultiVector3D) = exp(that.scalar) * exp(that.vec) * exp(that.biVec) * exp(that.triVec)

    fun Double.pow(that: Vector3D) = exp(that * ln(this))
    fun Double.pow(that: BiVector3D) = exp(that * ln(this))
    fun Double.pow(that: TriVector3D) = exp(that * ln(this))
    fun Double.pow(that: MultiVector3D) = exp(that * ln(this))

    fun cosh(that: Vector3D) = cosh(that.magnitude())
    fun cosh(that: BiVector3D) = cos(that.magnitude())
    fun cosh(that: TriVector3D) = cos(that.magnitude())
    fun cosh(that: MultiVector3D) =
        sinh(that.scalar) * sinh(that.vec) * sinh(that.biVec) * sinh(that.triVec) +
        cosh(that.scalar) * cosh(that.vec) * cosh(that.biVec) * cosh(that.triVec) +
        sinh(that.scalar) * sinh(that.vec) * cosh(that.biVec) * cosh(that.triVec) +
        sinh(that.scalar) * cosh(that.vec) * sinh(that.biVec) * cosh(that.triVec) +
        cosh(that.scalar) * sinh(that.vec) * sinh(that.biVec) * cosh(that.triVec) +
        sinh(that.scalar) * cosh(that.vec) * cosh(that.biVec) * sinh(that.triVec) +
        cosh(that.scalar) * sinh(that.vec) * cosh(that.biVec) * sinh(that.triVec) +
        cosh(that.scalar) * cosh(that.vec) * sinh(that.biVec) * sinh(that.triVec)

    fun sinh(that: Vector3D) = sinh(that.magnitude())
    fun sinh(that: BiVector3D) = sin(that.magnitude()) * that.norm()
    fun sinh(that: TriVector3D) = sin(that.magnitude()) * that.norm()
    fun sinh(that: MultiVector3D) =
        sinh(that.scalar) * cosh(that.vec) * cosh(that.biVec) * cosh(that.triVec) +
        sinh(that.scalar) * sinh(that.vec) * sinh(that.biVec) * cosh(that.triVec) +
        sinh(that.scalar) * sinh(that.vec) * cosh(that.biVec) * sinh(that.triVec) +
        sinh(that.scalar) * cosh(that.vec) * sinh(that.biVec) * sinh(that.triVec) +
        cosh(that.scalar) * sinh(that.vec) * cosh(that.biVec) * cosh(that.triVec) +
        cosh(that.scalar) * cosh(that.vec) * sinh(that.biVec) * cosh(that.triVec) +
        cosh(that.scalar) * cosh(that.vec) * cosh(that.biVec) * sinh(that.triVec) +
        cosh(that.scalar) * sinh(that.vec) * sinh(that.biVec) * sinh(that.triVec)

    fun tanh(that: Vector3D) = sinh(that) / cosh(that)
    fun tanh(that: BiVector3D) = sinh(that) / cosh(that)
    fun tanh(that: TriVector3D) = sinh(that) / cosh(that)
    fun tanh(that: MultiVector3D) = sinh(that) / cosh(that)

    fun cos(that: Vector3D, basis: BiVector3D) = sinh((that dot basis.norm())) * sinh((that wedge basis.norm())) + cosh((that dot basis.norm())) * cosh((that wedge basis.norm()))
    fun cos(that: Vector3D, basis: TriVector3D) = cosh(that * basis.norm())
    fun cos(that: BiVector3D, basis: BiVector3D) = sinh((that dot basis.norm())) * sinh((that cross basis.norm())) + cosh((that dot basis.norm())) * cosh((that cross basis.norm()))
    fun cos(that: BiVector3D, basis: TriVector3D) = cosh(that * basis.norm())
    fun cos(that: TriVector3D, basis: BiVector3D) = cosh(that * basis.norm())
    fun cos(that: TriVector3D, basis: TriVector3D) = cosh(that * basis.norm())
    fun cos(that: MultiVector3D, basis: BiVector3D) =
        sin(that.scalar) * sin(that.vec, basis.norm()) * sin(that.biVec, basis.norm()) * sin(that.triVec, basis.norm()) +
        cos(that.scalar) * cos(that.vec, basis.norm()) * cos(that.biVec, basis.norm()) * cos(that.triVec, basis.norm()) -
        sin(that.scalar) * sin(that.vec, basis.norm()) * cos(that.biVec, basis.norm()) * cos(that.triVec, basis.norm()) -
        sin(that.scalar) * cos(that.vec, basis.norm()) * sin(that.biVec, basis.norm()) * cos(that.triVec, basis.norm()) -
        cos(that.scalar) * sin(that.vec, basis.norm()) * sin(that.biVec, basis.norm()) * cos(that.triVec, basis.norm()) -
        sin(that.scalar) * cos(that.vec, basis.norm()) * cos(that.biVec, basis.norm()) * sin(that.triVec, basis.norm()) -
        cos(that.scalar) * sin(that.vec, basis.norm()) * cos(that.biVec, basis.norm()) * sin(that.triVec, basis.norm()) -
        cos(that.scalar) * cos(that.vec, basis.norm()) * sin(that.biVec, basis.norm()) * sin(that.triVec, basis.norm())
    fun cos(that: MultiVector3D, basis: TriVector3D) =
        sin(that.scalar) * sin(that.vec, basis.norm()) * sin(that.biVec, basis.norm()) * sin(that.triVec, basis.norm()) +
        cos(that.scalar) * cos(that.vec, basis.norm()) * cos(that.biVec, basis.norm()) * cos(that.triVec, basis.norm()) -
        sin(that.scalar) * sin(that.vec, basis.norm()) * cos(that.biVec, basis.norm()) * cos(that.triVec, basis.norm()) -
        sin(that.scalar) * cos(that.vec, basis.norm()) * sin(that.biVec, basis.norm()) * cos(that.triVec, basis.norm()) -
        cos(that.scalar) * sin(that.vec, basis.norm()) * sin(that.biVec, basis.norm()) * cos(that.triVec, basis.norm()) -
        sin(that.scalar) * cos(that.vec, basis.norm()) * cos(that.biVec, basis.norm()) * sin(that.triVec, basis.norm()) -
        cos(that.scalar) * sin(that.vec, basis.norm()) * cos(that.biVec, basis.norm()) * sin(that.triVec, basis.norm()) -
        cos(that.scalar) * cos(that.vec, basis.norm()) * sin(that.biVec, basis.norm()) * sin(that.triVec, basis.norm())

    fun sin(that: Vector3D, basis: BiVector3D) = (sinh((that dot basis.norm())) * cosh((that wedge basis.norm())) + cosh((that dot basis.norm())) * sinh((that wedge basis.norm()))) / basis.norm()
    fun sin(that: Vector3D, basis: TriVector3D) = sinh(that * basis.norm())
    fun sin(that: BiVector3D, basis: BiVector3D) = (sinh((that dot basis.norm())) * cosh((that cross basis.norm())) + cosh((that dot basis.norm())) * sinh((that cross basis.norm()))) / basis.norm()
    fun sin(that: BiVector3D, basis: TriVector3D) =  sinh(that * basis.norm()) / basis.norm()
    fun sin(that: TriVector3D, basis: BiVector3D) = sinh(that * basis.norm()) / basis.norm()
    fun sin(that: TriVector3D, basis: TriVector3D) = sinh(that * basis.norm()) / basis.norm()
    fun sin(that: MultiVector3D, basis: BiVector3D) =
        sin(that.scalar) * cos(that.vec, basis.norm()) * cos(that.biVec, basis.norm()) * cos(that.triVec, basis.norm()) -
        sin(that.scalar) * sin(that.vec, basis.norm()) * sin(that.biVec, basis.norm()) * cos(that.triVec, basis.norm()) -
        sin(that.scalar) * sin(that.vec, basis.norm()) * cos(that.biVec, basis.norm()) * sin(that.triVec, basis.norm()) -
        sin(that.scalar) * cos(that.vec, basis.norm()) * sin(that.biVec, basis.norm()) * sin(that.triVec, basis.norm()) +
        cos(that.scalar) * sin(that.vec, basis.norm()) * cos(that.biVec, basis.norm()) * cos(that.triVec, basis.norm()) +
        cos(that.scalar) * cos(that.vec, basis.norm()) * sin(that.biVec, basis.norm()) * cos(that.triVec, basis.norm()) +
        cos(that.scalar) * cos(that.vec, basis.norm()) * cos(that.biVec, basis.norm()) * sin(that.triVec, basis.norm()) -
        cos(that.scalar) * sin(that.vec, basis.norm()) * sin(that.biVec, basis.norm()) * sin(that.triVec, basis.norm())
    fun sin(that: MultiVector3D, basis: TriVector3D) =
        sin(that.scalar) * cos(that.vec, basis.norm()) * cos(that.biVec, basis.norm()) * cos(that.triVec, basis.norm()) -
        sin(that.scalar) * sin(that.vec, basis.norm()) * sin(that.biVec, basis.norm()) * cos(that.triVec, basis.norm()) -
        sin(that.scalar) * sin(that.vec, basis.norm()) * cos(that.biVec, basis.norm()) * sin(that.triVec, basis.norm()) -
        sin(that.scalar) * cos(that.vec, basis.norm()) * sin(that.biVec, basis.norm()) * sin(that.triVec, basis.norm()) +
        cos(that.scalar) * sin(that.vec, basis.norm()) * cos(that.biVec, basis.norm()) * cos(that.triVec, basis.norm()) +
        cos(that.scalar) * cos(that.vec, basis.norm()) * sin(that.biVec, basis.norm()) * cos(that.triVec, basis.norm()) +
        cos(that.scalar) * cos(that.vec, basis.norm()) * cos(that.biVec, basis.norm()) * sin(that.triVec, basis.norm()) -
        cos(that.scalar) * sin(that.vec, basis.norm()) * sin(that.biVec, basis.norm()) * sin(that.triVec, basis.norm())

    fun tan(that: Vector3D, basis: BiVector3D) = sin(that, basis) / cos(that, basis)
    fun tan(that: Vector3D, basis: TriVector3D) = sin(that, basis) / cos(that, basis)
    fun tan(that: BiVector3D, basis: BiVector3D) = sin(that, basis) / cos(that, basis)
    fun tan(that: BiVector3D, basis: TriVector3D) = sin(that, basis) / cos(that, basis)
    fun tan(that: TriVector3D, basis: BiVector3D) = sin(that, basis) / cos(that, basis)
    fun tan(that: TriVector3D, basis: TriVector3D) = sin(that, basis) / cos(that, basis)
    fun tan(that: MultiVector3D, basis: BiVector3D) = sin(that, basis) / cos(that, basis)
    fun tan(that: MultiVector3D, basis: TriVector3D) = sin(that, basis) / cos(that, basis)

    fun ln(that: Double, basis: BiVector3D): MultiVector3D {
        return if (that >= 0) {
            MultiVector3D( scalar = ln(that) )
        } else {
            ln(abs(that)) + PI * basis.norm()
        }
    }
    fun ln(that: Double, basis: TriVector3D): MultiVector3D {
        return if (that >= 0) {
            MultiVector3D( scalar = ln(that) )
        } else {
            ln(abs(that)) + PI * basis.norm()
        }
    }
    fun ln(that: Vector3D) = ln(that.magnitude()) - 2 // This definitely is not correct
    fun ln(that: BiVector3D): MultiVector3D = ln(that.magnitude(), that) + (PI / 2) * that.norm()
    fun ln(that: TriVector3D): MultiVector3D = ln(that.magnitude(), that) + (PI / 2) * that.norm()
}