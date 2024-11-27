import pt.isel.canvas.Canvas

/**
 * Class that represents every cell of the snake's body,
 * containing a position [Vector2] and a direction [Direction].
 */

data class SnakePart(val pos: Vector2, val direction: Direction)

/**
 * The Snake class deals with every [SnakePart] contained in a
 * list and calculates how to draw it on the screen and how to move it.
 */

data class Snake(val body: List<SnakePart>, val screen: Canvas){

    fun totalPos(): List<Vector2>{
        var tempList = emptyList<Vector2>()
        for(i in 0 until body.size)
            tempList += body[i].pos
        return tempList
    }

    /**
     * Calculates what sprite a certain part of the snake should be,
     * depending on the direction of the current sprite (i) in relation
     * to the previous sprite (i+1).
     */

    private fun getSprite(i: Int): Vector2 {
        return when(body[i].direction){
            Direction.UP -> if (i == 0) Vector2(3,0).times(64)
                else if (i == body.lastIndex) Vector2(3,2).times(64)
                else if (body[i].direction == body[i+1].direction) Vector2(2,1).times(64)
                else if (body[i+1].direction == Direction.LEFT) Vector2(0,1).times(64)
                else if (body[i+1].direction == Direction.RIGHT) Vector2(2,2).times(64)
                else Vector2(2,1).times(64)

            Direction.DOWN -> if (i == 0) Vector2(4,1).times(64)
                else if (i == body.lastIndex) Vector2(4,3).times(64)
                else if (body[i].direction == body[i+1].direction) Vector2(2,1).times(64)
                else if (body[i+1].direction == Direction.LEFT) Vector2(0,0).times(64)
                else if (body[i+1].direction == Direction.RIGHT) Vector2(2,2).times(64)
                else Vector2(2,1).times(64)

            Direction.LEFT -> if (i == 0) Vector2(3,1).times(64)
                else if (i == body.lastIndex) Vector2(3,3).times(64)
                else if (body[i].direction == body[i+1].direction) Vector2(1,0).times(64)
                else if (body[i+1].direction == Direction.UP) Vector2(2,0).times(64)
                else if (body[i+1].direction == Direction.DOWN) Vector2(2,2).times(64)
                else Vector2(1,0).times(64)

            Direction.RIGHT -> if (i == 0) Vector2(4,0).times(64)
                else if (i == body.lastIndex) Vector2(4,2).times(64)
                else if (body[i].direction == body[i+1].direction) Vector2(1,0).times(64)
                else if (body[i+1].direction == Direction.UP) Vector2(0,0).times(64)
                else if (body[i+1].direction == Direction.DOWN) Vector2(0,1).times(64)
                else Vector2(1,0).times(64)

            else -> Vector2(1,0).times(64)
        }
    }

    /**
     * Draws the snake on the screen (its just that lol)
     */

    fun draw(){
        for(p in 0..body.indices.last){
            val sprite = getSprite(p)
            screen.drawImage(
                "snake|${sprite.x},${sprite.y},64,64",
                body[p].pos.x,body[p].pos.y,CELLS.size,CELLS.size)
        }
    }

    // TODO: Snake fun move
}