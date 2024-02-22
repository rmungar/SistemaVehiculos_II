class Automovil(nombre: String,marca:String, modelo:String, capacidadCombustible:Float, combustibleActual: Float, kilometrosActuales: Float, val esElectrico: Boolean):Vehiculo(nombre, marca, modelo, capacidadCombustible, combustibleActual, kilometrosActuales) {

    init {
        comprobarNombreUnico(nombre)
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

    }
    override fun calcularAutonomia(): Float {
        if (esElectrico) {
            return combustibleActual * (KILOMETROS_POR_LITRO_HIBRIDO)
        }else{
            return super.calcularAutonomia()
        }
    }

    override fun realizaViaje(distancia: Float): Float {
        if (esElectrico) {
            val distanciaPosible = calcularAutonomia()
            if (distanciaPosible > distancia) {
                combustibleActual = ((distanciaPosible - distancia) / (KILOMETROS_POR_LITRO_HIBRIDO))
                var distanciaAderrapar = distancia
                if (distanciaAderrapar % 20 == 0F && distanciaAderrapar > 0){
                    kilometrosActuales += distanciaAderrapar
                    distanciaAderrapar-=20
                }
                return distancia.toFloat()

            } else {
                combustibleActual = 0.0F
                var distanciaAderrapar  = distancia
                if (distanciaAderrapar % 20 == 0F && distanciaAderrapar > 0){
                    kilometrosActuales += distanciaAderrapar
                    distanciaAderrapar -= 20
                }
                return distancia - distanciaPosible
            }
        }
        else {
            return super.realizaViaje(distancia)
        }
    }


    override fun realizarDerrape(): Float{
        if (esElectrico){
            if (calcularAutonomia() - 6.25 > 0.00) {
                combustibleActual - 0.417
                return combustibleActual
            } else {
                println("No hay combustible suficiente para derrapar")
                return combustibleActual
            }
        }
        else {
            if (calcularAutonomia() - 7.5 > 0.00) {
                combustibleActual - 0.75
                return combustibleActual
            } else {
                println("No hay combustible suficiente para derrapar")
                return combustibleActual
            }
        }
    }

    override fun obtenerInformacion(): String {
        return super.obtenerInformacion() + ", británico: $condicionBritania, eléctrico: $esElectrico"
    }
}