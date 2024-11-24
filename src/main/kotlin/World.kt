data class Vector2(var x: Int, var y: Int) {
    operator fun plus(a: Int) =
        Vector2(x+ a, y + a)

    operator fun minus(a: Int) =
        Vector2(x - a, y - a)

    operator fun times(a: Int) =
        Vector2(x * a, y * a)

    operator fun div(a: Int) =
        Vector2(x / a, y / a)
}

val UP = 0
val DOWN = 1
val LEFT = 2
val RIGHT = 3


