class Automovil(marca:String, modelo:String, capacidadCombustible:Float, combustibleActual: Float, kilometrosActuales: Int, val esElectrico: Boolean, var condicionBritania: Boolean):Vehiculo(marca, modelo, capacidadCombustible, combustibleActual, kilometrosActuales) {

    init {
        require(esElectrico || !esElectrico) {"El campo esElectrico ha de ser true o false"}
        require(condicionBritania || !condicionBritania) {"El campo condicionBritania ha de ser true o false"}
    }

    override fun calcularAutonomia(): Int {
        if (esElectrico) {
            return (combustibleActual * 5).toInt()
        }else{
            return super.calcularAutonomia()
        }
    }

    fun cambiarCondicionBritania(nuevaCondicion: Boolean){
        if (nuevaCondicion){
            condicionBritania = false
            println("Ahora $marca $modelo tiene el volante a la izquierda")
        }
        else{
            condicionBritania = true
            println("Ahora $marca $modelo tiene el volante a la derecha")
        }
    }

    fun realizarDerrape(): Float{
        if (calcularAutonomia() - 5.00 > 0.00){
            combustibleActual - 5.00
            return combustibleActual
        }
        else{
            println("No hay combustible suficiente para derrapar")
            return combustibleActual
        }
    }
}