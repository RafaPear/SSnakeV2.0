import pt.isel.canvas.Canvas
import kotlin.collections.plus

// TODO: Wall class
data class Walls(
    val walls: List<Vector2>,
    val availablePlaces: List<Vector2>,
    val snake: Snake,
    val cells: Cells,
    val screen: Canvas
){

    // TODO: Wall generation code (keep in mind that it has levels)
    fun newWall(): Walls{
        var tempWalls = walls
        var tempAvailablePlaces = availablePlaces

        while(tempAvailablePlaces.size-snake.totalPos().size != 0){
            val i = availablePlaces.indices.random()
            //screen.drawRect(tempAvailablePlaces[i].x,tempAvailablePlaces[i].y,cells.size,cells.size,RED,5)
            if (availablePlaces[i] !in snake.totalPos()) {
                tempWalls += availablePlaces[i]
                tempAvailablePlaces -= availablePlaces[i]
                break
            }
        }
        return Walls(tempWalls,tempAvailablePlaces,this.snake,this.cells,this.screen)
    }

    // TODO: Wall draw code
    fun draw(){
        for (pos in walls) {
            screen.drawImage(
                "bricks|0,0,118,118",
                pos.x,pos.y,CELLS.size,CELLS.size)
        }
    }
}