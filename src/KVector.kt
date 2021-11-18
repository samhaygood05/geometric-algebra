interface KVector {

    /**
     * Computes the squared magnitude of the k-vector
     *
     * Special cases:
     *  - `squaredMag()` is `NaN` for `this == NaN`
     *  - `squaredMag()` is `+Inf` for `this == Inf`
     * */
    fun squaredMag(): Double

    /**
     * Computes the magnitude of the k-vector
     *
     * Special cases:
     *  - `magnitude()` is `NaN` for `this == NaN`
     *  - `magnitude()` is `+Inf` for `this == Inf`
     * */
    fun magnitude(): Double

    /**
     * Computes the normalized value of the k-vector
     *
     * Special cases:
     *  - `norm()` is `NaN` for `magnitude() == 0.0` or `magnitude() == +Inf`
     * */
    fun norm(): KVector

}