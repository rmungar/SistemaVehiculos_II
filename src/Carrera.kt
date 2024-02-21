import kotlin.random.Random

class Carrera(val nombreCarrera: String, val distanciaTotal: Float, var participantes:List<Vehiculo>) {
        var estadoCarrera: Boolean = false
        var historialAcciones: MutableMap<String, MutableList<String>> = mutableMapOf()
        val posiciones: MutableList<Pair<String, Int>> = mutableListOf()
    init {
        require(nombreCarrera.isNotBlank()) {"El nombre de la carrera no puede estar vacío"}
        require(distanciaTotal > 0) {"Longitud de la carrera invalidad"}
        for (participante in participantes) {
            historialAcciones.put(participante.nombre, mutableListOf<String>("Entra en carrera"))
        }
    }
    fun iniciarCarrera(){
        estadoCarrera = true
        println("${nombreCarrera.uppercase()} EMPIEZA")
        while (true){
            val turno = participantes.random()
            while (true){
                avanzarVehiculo(turno)
                realizarFiligrana(turno)
                actualizarPosiciones()
                break
            }
            if (determinarGanador()) break
        }
        obtenerResultados()
    }
    fun obtenerDistancia(): Int{
        return Random.nextInt(10,200)
    }
    fun avanzarVehiculo(vehiculo: Vehiculo){
        var distanciaArecorrer = obtenerDistancia()
        while (distanciaArecorrer > 0) {
            if (vehiculo.combustibleActual == 0.0F){
                repostarVehiculo(vehiculo)
            }
            val distanciaRecorrida = vehiculo.realizaViaje(distanciaArecorrer)
            registrarAccion(vehiculo.nombre, "Avanza $distanciaRecorrida Kms")
            distanciaArecorrer -= distanciaRecorrida.toInt()
            if (distanciaArecorrer > 0) {
                repostarVehiculo(vehiculo)
            }
            else break
        }
    }

    fun repostarVehiculo(vehiculo: Vehiculo){
        vehiculo.repostar()
        registrarAccion(vehiculo.nombre, "Ha repostado")
    }

    fun realizarFiligrana(vehiculo: Vehiculo){
        val hacer = Random.nextBoolean()
        if (hacer){
            if (vehiculo.combustibleActual < 5.0)
            if (vehiculo is Automovil){
                vehiculo.realizarDerrape()
                registrarAccion(vehiculo.nombre, "Derrapa")
            }
            else{
                vehiculo.realizarCaballito()
                registrarAccion(vehiculo.nombre, "Hace un caballito")
            }
        }
        else{
            registrarAccion(vehiculo.nombre, "No hace nada")

        }
    }

    fun actualizarPosiciones(){
        val posiciones = mutableListOf<Pair<String, Int>>()
        for (participante in participantes){
            posiciones.add(Pair(participante.nombre, participante.kilometrosActuales))
        }
        posiciones.sortBy { it.second }
    }

    fun determinarGanador(): Boolean{
        for (participante in posiciones){
            if (participante.second == distanciaTotal.toInt()){
                println("Ha ganado: ${participante.first}")
                return true
            }
        }
        return false
    }

    fun obtenerResultados(){
        val resultados = mutableListOf<Vehiculo>()
        for (participante in participantes){
            resultados.add(participante)
        }
        resultados.sortBy { it.kilometrosActuales }
        for (participante in resultados){
            println(participante.obtenerInformacion())
        }
    }
    fun registrarAccion(vehiculo: String, accion: String) {
        val evento = historialAcciones.get(vehiculo)
        evento?.add(accion)
    }
    fun mostrarAcciones(){
        historialAcciones.forEach{ println("${it.key} = ${it.value}") }
    }

    data class resultados(val vehiculo: Vehiculo, val posicion: Int, val kilometraje: Int, val paradasRepostaje: Int, val historialAcciones: List<String>)

}