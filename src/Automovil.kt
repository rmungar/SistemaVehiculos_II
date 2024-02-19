class Automovil(nombre: String,marca:String, modelo:String, capacidadCombustible:Float, combustibleActual: Float, kilometrosActuales: Int, val esElectrico: Boolean):Vehiculo(nombre, marca, modelo, capacidadCombustible, combustibleActual, kilometrosActuales) {

    init {
        require(nombre.isNotBlank())
        require(marca.isNotBlank())
        require(modelo.isNotBlank())
        require(nombre.isNotBlank())
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
        const val KxL:Int = 10
    }
    override fun calcularAutonomia(): Int {
        if (esElectrico) {
            return redondear(combustibleActual * (KxL+5))
        }else{
            return super.calcularAutonomia()
        }
    }

    override fun realizaViaje(distancia: Int): Int {
        val distanciaPosible = combustibleActual* KxL
        if (esElectrico) {
            if (distanciaPosible > distancia) {
                combustibleActual = ((distanciaPosible - distancia) / (KxL+5))
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
                combustibleActual = (distanciaPosible - distancia) / KxL
                kilometrosActuales += distancia
                return redondear(distanciaPosible - distancia)

            } else {
                combustibleActual = 0.0F
                kilometrosActuales += distancia
                return redondear(distancia - distanciaPosible)
            }
        }
    }

    override fun realizarDerrape(): Float{
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