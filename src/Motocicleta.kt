class Motocicleta(nombre:String, marca:String, modelo:String, capacidadCombustible:Float, combustibleActual: Float, kilometrosActuales: Float, val cilindrada: Int) : Vehiculo(nombre, marca,modelo, capacidadCombustible, combustibleActual, kilometrosActuales) {

    init {
        comprobarNombreUnico(nombre)
    }

    override fun calcularAutonomia(): Float {
        if (cilindrada == 1000) return combustibleActual * KILOMETROS_POR_LITRO_MOTOS
        else {
            return (combustibleActual * KILOMETROS_POR_LITRO_MOTOS)-(cilindrada/1000)
        }
    }
    override fun realizaViaje(distancia: Float): Float {
        val distanciaPosible = calcularAutonomia()
        if (distanciaPosible > distancia){
            combustibleActual = (distanciaPosible - distancia) / KILOMETROS_POR_LITRO_MOTOS
            return distancia
        }
        else{
            combustibleActual = 0.0F
            return distancia - distanciaPosible
        }
    }
    override fun realizarCaballito():Float{
        if (cilindrada == 1000) {
            if (calcularAutonomia() - 6.5 > 0.00) {
                combustibleActual - 0.325
                return combustibleActual
            }else {
                println("No hay combustible suficiente para hacer un caballito")
                return combustibleActual
            }
        }
        else if (cilindrada in 500..1000) {
            if (calcularAutonomia() - 6.5 > 0.00) {
                combustibleActual - 0.333
                return combustibleActual
            } else {
                println("No hay combustible suficiente para hacer un caballito")
                return combustibleActual
            }
        }
        else{
            if (cilindrada in 125..1000) {
                if (calcularAutonomia() - 6.5 > 0.00) {
                    combustibleActual - 0.339
                    return combustibleActual
                } else {
                    println("No hay combustible suficiente para hacer un caballito")
                    return combustibleActual
                }
            }
            else return 0.0F
        }
    }

    override fun obtenerInformacion(): String {
        return super.obtenerInformacion() + ", cilindradas: $cilindrada"
    }
}