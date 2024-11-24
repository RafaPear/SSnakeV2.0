import pt.isel.canvas.Canvas

data class SnakePart(val pos: Vector2, val direction: Int)

data class Snake(val body: List<SnakePart>, val screen: Canvas){
    private fun getSprite(i: Int): Vector2 {
        return when(body[i].direction){
            UP -> if (i == 0) Vector2(3,0).times(64)
                else if (i == body.lastIndex) Vector2(3,2).times(64)
                else if (body[i].direction == body[i+1].direction) Vector2(2,1).times(64)
                else if (body[i+1].direction == LEFT) Vector2(0,1).times(64)
                else if (body[i+1].direction == RIGHT) Vector2(2,2).times(64)
                else Vector2(2,1).times(64)

            DOWN -> if (i == 0) Vector2(4,1).times(64)
                else if (i == body.lastIndex) Vector2(4,3).times(64)
                else if (body[i].direction == body[i+1].direction) Vector2(2,1).times(64)
                else if (body[i+1].direction == LEFT) Vector2(0,0).times(64)
                else if (body[i+1].direction == RIGHT) Vector2(2,2).times(64)
                else Vector2(2,1).times(64)

            LEFT -> if (i == 0) Vector2(3,1).times(64)
                else if (i == body.lastIndex) Vector2(3,3).times(64)
                else if (body[i].direction == body[i+1].direction) Vector2(1,0).times(64)
                else if (body[i+1].direction == UP) Vector2(2,0).times(64)
                else if (body[i+1].direction == DOWN) Vector2(2,2).times(64)
                else Vector2(1,0).times(64)

            RIGHT -> if (i == 0) Vector2(4,0).times(64)
                else if (i == body.lastIndex) Vector2(4,2).times(64)
                else if (body[i].direction == body[i+1].direction) Vector2(1,0).times(64)
                else if (body[i+1].direction == UP) Vector2(0,0).times(64)
                else if (body[i+1].direction == DOWN) Vector2(0,1).times(64)
                else Vector2(1,0).times(64)

            else -> Vector2(1,0).times(64)
        }
    }

    // TODO: Snake fun move


    fun draw(){
        for(p in 0..body.indices.last){
            val sprite = getSprite(p)
            screen.drawImage(
                "snake|${sprite.x},${sprite.y},64,64",
                body[p].pos.x,body[p].pos.y,CELLS.size,CELLS.size)
        }
    }

}