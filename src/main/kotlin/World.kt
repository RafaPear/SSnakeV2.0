/**
 * A Vector2 is a two-dimensional type that supports
 * operations like adding a constant
 */

data class Vector2(var x: Int, var y: Int) {
    operator fun plus(a: Int) = Vector2(x + a, y + a)
    operator fun plus(a: Vector2) = Vector2(x + a.x, y + a.y)

    operator fun minus(a: Int) = Vector2(x - a, y - a)
    operator fun minus(a: Vector2) = Vector2(x - a.x, y - a.y)

    operator fun times(a: Int) = Vector2(x * a, y * a)
    operator fun times(a: Vector2) = Vector2(x * a.x, y * a.y)

    operator fun div(a: Int) = Vector2(x / a, y / a)
    operator fun div(a: Vector2) = Vector2(x / a.x, y / a.y)
}

val UP = 0
val DOWN = 1
val LEFT = 2
val RIGHT = 3


