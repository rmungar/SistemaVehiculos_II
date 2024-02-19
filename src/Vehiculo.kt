import kotlin.math.roundToInt

abstract class Vehiculo (val nombre: String, val marca: String, val modelo: String, val capacidadCombustible: Float, var combustibleActual: Float, var kilometrosActuales: Int){
    init {
        require(marca.isNotBlank()) {"El campo de marca no puede estar vacío"}
        marca.replaceFirstChar { it.uppercase() }
        require(modelo.isNotBlank()) {"Ell campo de modelo no puede estar vacío"}
        modelo.replaceFirstChar { it.uppercase() }
        require(capacidadCombustible > 0.00) {"La capacidad de combustible no puede ser menor a 0.0L"}
        require(combustibleActual >= 0.0) {"El combustible actual no puede ser menor a 0.0L"}
        require(kilometrosActuales >= 0) {"Los kilométros actuales no pueden ser menor a 0Km"}
    }
    open fun calcularAutonomia(): Int{
        return redondear(combustibleActual*10)
    }
    open fun redondear(num: Float):Int{
        return  num.roundToInt()
    }
    open fun realizaViaje(distancia: Int):Int {
        val distanciaPosible = combustibleActual*10
        if (distanciaPosible > distancia){
            combustibleActual = (distanciaPosible - distancia)/10
            return redondear(distanciaPosible-distancia)

        }
        else{
            combustibleActual = 0.0F
            return  redondear(distancia - distanciaPosible)
        }
    }
    open fun repostar(cantidad:Float): Float{
        if (cantidad <= 0.0F || cantidad > capacidadCombustible ) combustibleActual = capacidadCombustible

        if ((cantidad.toString().uppercase()) in ("A".."Z")) combustibleActual = capacidadCombustible

        if (combustibleActual + cantidad > capacidadCombustible){
            combustibleActual = capacidadCombustible
            return combustibleActual
        }
        else{
            combustibleActual += cantidad
            return combustibleActual
        }
    }
    open fun realizarDerrape():Float{
        return 0.0F
    }
    open fun realizarCaballito():Float{
        return 0.0F
    }
    open fun obtenerInformacion(): String{
        return "$marca $modelo puede recorrer ${calcularAutonomia()}Kms actualmente, ya cuenta con ${kilometrosActuales}Kms recorridos"
    }
}