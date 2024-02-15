import kotlin.math.roundToInt

abstract class Vehiculo (val marca: String, val modelo: String, val capacidadCombustible: Float, var combustibleActual: Float, var kilometrosActuales: Int){
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
        return (combustibleActual*10).roundToInt()
    }
    fun realizaViaje(distancia: Int):Int {
        var distanciaRestante: Int = 0
        if (calcularAutonomia() > distancia){
            if ((calcularAutonomia() - distancia) > 0){
                return distanciaRestante
            }
            else if ((calcularAutonomia() - distancia) < 0){
                distanciaRestante =(distancia - calcularAutonomia()).redondear()
                return distanciaRestante
            }
        }
        else{
            distanciaRestante =(distancia - calcularAutonomia()).redondear()
            return distanciaRestante
        }
    }
    fun repostar(cantidad:Float): Float{
        if (combustibleActual + cantidad > capacidadCombustible){
            combustibleActual = capacidadCombustible
            return combustibleActual
        }
        else{
            combustibleActual += cantidad
            return combustibleActual
        }
    }
    open fun obtenerInformacion(): String{
        return "$marca $modelo puede recorrer ${calcularAutonomia()}Kms actualmente, ya cuenta con ${kilometrosActuales}Kms recorridos"
    }
}