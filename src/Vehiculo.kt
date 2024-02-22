import kotlin.math.roundToInt

abstract class Vehiculo (val nombre: String, val marca: String, val modelo: String, val capacidadCombustible: Float, var combustibleActual: Float, var kilometrosActuales: Float){
    init {
        require(comprobarNombreUnico(nombre)) {"El nombre no puede estar repetido"}
        require(marca.isNotBlank()) {"El campo de marca no puede estar vacío"}
        marca.replaceFirstChar { it.uppercase() }
        require(modelo.isNotBlank()) {"Ell campo de modelo no puede estar vacío"}
        modelo.replaceFirstChar { it.uppercase() }
        require(capacidadCombustible > 0.00) {"La capacidad de combustible no puede ser menor a 0.0L"}
        require(combustibleActual >= 0.0) {"El combustible actual no puede ser menor a 0.0L"}
        require(kilometrosActuales >= 0) {"Los kilométros actuales no pueden ser menor a 0Km"}
    }
    companion object{
        val nombres: MutableList<String> = mutableListOf()
        fun comprobarNombreUnico(nombre: String):Boolean{
            if(nombres.contains(nombre)) {
                return false
            }
            else{
                nombres.add(nombre)
                return true
            }
        }
        const val KILOMETROS_POR_LITRO:Int = 10
        const val KILOMETROS_POR_LITRO_HIBRIDO: Int = 15
        const val KILOMETROS_POR_LITRO_MOTOS:Int = 20
    }
    open fun calcularAutonomia(): Float{
        return combustibleActual* KILOMETROS_POR_LITRO
    }
    open fun redondear(num: Float):Int{
        return  num.roundToInt()
    }
    open fun realizaViaje(distancia: Float):Float {
        val distanciaPosible = calcularAutonomia()
        if (distanciaPosible > distancia){
            combustibleActual = (distanciaPosible - distancia)/ KILOMETROS_POR_LITRO
            kilometrosActuales += distancia
            return distancia

        }
        else{
            combustibleActual = 0.0F
            kilometrosActuales += distancia
            return  distancia - distanciaPosible
        }
    }
    open fun repostar(cantidad:Float = 0.0F): Float{
        if (cantidad <= 0.0F) combustibleActual = capacidadCombustible

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