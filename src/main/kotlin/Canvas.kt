import pt.isel.canvas.*

const val GREY = 0x848484

fun Canvas.drawRect(pos: Vector2, size: Vector2, thickness: Int? = null, color: Int? = null) {
    val newColor: Int = color ?: WHITE
    val newThickness: Int = thickness ?: 0
    drawRect(pos.x, pos.y, size.x, size.y, newColor, newThickness)
}

fun Canvas.drawImage(fileName: String, pos: Vector2, size: Vector2) {
    drawImage(fileName, pos.x, pos.y, size.x, size.y)
}

fun Canvas.drawLine(origin: Vector2, destination: Vector2, thickness: Int? = null, color: Int? = null) {
    val newColor: Int = color ?: WHITE
    val newThickness: Int = thickness ?: 0
    drawLine(origin.x,origin.y,destination.x,destination.y,newColor,newThickness)
}

fun Canvas.drawText(pos: Vector2, text: String, color: Int? = null, fontSize: Int? = null) {
    val newColor: Int = color ?: WHITE
    val newFontSize: Int = fontSize ?: 10
    drawText(pos.x,pos.y,text,newColor,newFontSize)
}