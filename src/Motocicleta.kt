class Motocicleta(marca:String, modelo:String, capacidadCombustible:Float, combustibleActual: Float, kilometrosActuales: Int, val cilindrada: Int) : Vehiculo(marca,modelo, capacidadCombustible, combustibleActual, kilometrosActuales) {
    override fun calcularAutonomia(): Int {
        return redondear(combustibleActual * 20)
    }
    companion object{
        val kxl:Int = 20
    }
    override fun realizaViaje(distancia: Int): Int {
        val distanciaPosible = combustibleActual*kxl
        if (distanciaPosible > distancia){
            combustibleActual = (distanciaPosible - distancia)/kxl
            return redondear(distanciaPosible - distancia)
        }
        else{
            combustibleActual = 0.0F
            return redondear(distancia - distanciaPosible)
        }
    }
    fun realizaCaballito():Float{
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