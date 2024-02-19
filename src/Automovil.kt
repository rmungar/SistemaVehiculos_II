class Automovil(marca:String, modelo:String, capacidadCombustible:Float, combustibleActual: Float, kilometrosActuales: Int, val esElectrico: Boolean):Vehiculo(marca, modelo, capacidadCombustible, combustibleActual, kilometrosActuales) {

    init {
        require(esElectrico || !esElectrico) {"El campo esElectrico ha de ser true o false"}
    }
    companion object{
        var condicionBritania: Boolean = false
        fun cambiarCondicionBritania(nuevaCondicion: Boolean){
            if (nuevaCondicion){
                condicionBritania = true
                println("Ahora todos tienen el volante a la derecha")
            }
            else{
                condicionBritania = false
                println("Ahora todos tiene el volante a la izquierda")
            }
        }
        val kxl:Int = 10
    }
    override fun calcularAutonomia(): Int {
        if (esElectrico) {
            return redondear(combustibleActual * kxl-5)
        }else{
            return super.calcularAutonomia()
        }
    }

    override fun realizaViaje(distancia: Int): Int {
        val distanciaPosible = combustibleActual* kxl
        if (esElectrico) {
            if (distanciaPosible > distancia) {
                combustibleActual = ((distanciaPosible - distancia) / (kxl-5))
                kilometrosActuales += distancia
                return redondear(distanciaPosible - distancia)

            } else {
                combustibleActual = 0.0F
                kilometrosActuales += distancia
                return redondear(distancia - distanciaPosible).toInt()
            }
        }
        else{
            if (distanciaPosible > distancia) {
                combustibleActual = (distanciaPosible - distancia) / kxl
                kilometrosActuales += distancia
                return redondear(distanciaPosible - distancia)

            } else {
                combustibleActual = 0.0F
                kilometrosActuales += distancia
                return redondear(distancia - distanciaPosible)
            }
        }
    }

    fun realizarDerrape(): Float{
        if (combustibleActual - 0.5 > 0.00){
            combustibleActual - 0.5
            return combustibleActual
        }
        else{
            println("No hay combustible suficiente para derrapar")
            return combustibleActual
        }
    }

    override fun obtenerInformacion(): String {
        return super.obtenerInformacion() + ", británico: $condicionBritania, eléctrico: $esElectrico"
    }
}