class Motocicleta(nombre:String, marca:String, modelo:String, capacidadCombustible:Float, combustibleActual: Float, kilometrosActuales: Int, val cilindrada: Int) : Vehiculo(nombre, marca,modelo, capacidadCombustible, combustibleActual, kilometrosActuales) {
    override fun calcularAutonomia(): Int {
        return redondear(combustibleActual * 20)
    }
    companion object{
       const val KxL:Int = 20
    }
    override fun realizaViaje(distancia: Int): Int {
        val distanciaPosible = combustibleActual * KxL
        if (distanciaPosible > distancia){
            combustibleActual = (distanciaPosible - distancia) / KxL
            return redondear(distanciaPosible - distancia)
        }
        else{
            combustibleActual = 0.0F
            return redondear(distancia - distanciaPosible)
        }
    }
    override fun realizarCaballito():Float{
        if (combustibleActual - 0.25 > 0.00){
            combustibleActual - 0.25
            return combustibleActual
        }
        else{
            println("No hay combustible suficiente para hacer un caballito")
            return combustibleActual
        }
    }

    override fun obtenerInformacion(): String {
        return super.obtenerInformacion() + ", cilindradas: $cilindrada"
    }
}