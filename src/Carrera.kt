import kotlin.random.Random

class Carrera(val nombreCarrera: String, val distanciaTotal: Float, val participantes:List<Vehiculo>) {
        var estadoCarrera: Boolean = false
        var historialAcciones: MutableMap<String, MutableList<String>> = mutableMapOf()
        val posiciones: MutableList<Vehiculo> = mutableListOf()
    init {
        require(nombreCarrera.isNotBlank()) {"El nombre de la carrera no puede estar vacío"}
        require(distanciaTotal > 0) {"Longitud de la carrera invalidad"}
        for (participante in participantes) {
            historialAcciones.put(participante.nombre, mutableListOf<String>("Entra en carrera"))
        }
        for (participante in participantes){
            posiciones.add(participante)
        }
    }
    companion object{
        const val TRAMO = 20F
    }
    fun iniciarCarrera(){
        estadoCarrera = true
        println("${nombreCarrera.uppercase()} EMPIEZA")
        println("------------------------------------")
        println("${nombreCarrera.uppercase()} HA TERMINIADO")
        println()
        while (true){
            val turno = participantes.random()
            while (true){
                avanzarVehiculo(turno)
                actualizarPosiciones()
                break
            }
            if (determinarGanador()) break
        }
        println()
        println("Clasificación")
        mostrarPosiciones()
        println()
        obtenerResultados()
        repeat(2){
            println()
        }
        println("Historial detallado ->")
        mostrarAcciones()
    }
    fun obtenerDistancia(): Int{
        return Random.nextInt(10,200)
    }
    fun avanzarVehiculo(vehiculo: Vehiculo){
        val distanciaArecorrer = obtenerDistancia()
        val tramosArecorrer = distanciaArecorrer/20
        val tramoReducido = distanciaArecorrer%20
        repeat(tramosArecorrer){
            val distanciaRestante = vehiculo.realizaViaje(TRAMO)
            registrarAccion(vehiculo.nombre, "Ha recorrido ${TRAMO-distanciaRestante}kms")
            if (distanciaRestante > 0){
                repostarVehiculo(vehiculo)
                vehiculo.realizaViaje(distanciaRestante)
                registrarAccion(vehiculo.nombre, "Ha recorrido $TRAMO kms")
            }
            realizarFiligrana(vehiculo)
        }
        if (tramoReducido > 0){
            vehiculo.realizaViaje(tramoReducido.toFloat())
            registrarAccion(vehiculo.nombre, "Ha recorrido $tramoReducido kms")
        }
    }

    fun repostarVehiculo(vehiculo: Vehiculo){
        vehiculo.repostar()
        registrarAccion(vehiculo.nombre, "Ha repostado")
    }

    fun obtenerCombustibleNecesario(vehiculo: Vehiculo):Float{
        return if (vehiculo is Automovil){
            if (vehiculo.esElectrico) 0.417F
            else 0.750F
        } else if (vehiculo is Motocicleta){
            when (vehiculo.cilindrada) {
                1000 -> 0.650F
                in 500..1000 -> 0.333F
                else -> 0.339F
            }
        } else{
            0.0F
        }
    }

    fun realizarFiligrana(vehiculo: Vehiculo){
        val combustibleNecesario = obtenerCombustibleNecesario(vehiculo)
        if (vehiculo.combustibleActual > combustibleNecesario){
            val hacer = Random.nextBoolean()
            if (hacer){
                if (vehiculo.combustibleActual < 5.0) {
                    if (vehiculo is Automovil) {
                        vehiculo.realizarDerrape()
                        registrarAccion(vehiculo.nombre, "Derrapa")

                    } else {
                        vehiculo.realizarCaballito()
                        registrarAccion(vehiculo.nombre, "Hace un caballito")
                    }
                }
            }
            else{
                registrarAccion(vehiculo.nombre, "No hace nada")
            }
        }
        else{
            val hacer = Random.nextBoolean()
            if (hacer){
                repostarVehiculo(vehiculo)
                if (vehiculo is Automovil) {
                    vehiculo.realizarDerrape()
                    registrarAccion(vehiculo.nombre, "Derrapa")

                } else {
                    vehiculo.realizarCaballito()
                    registrarAccion(vehiculo.nombre, "Hace un caballito")
                }
            }
            else {
                registrarAccion(vehiculo.nombre, "No hace nada")
            }
        }
    }



    fun determinarGanador(): Boolean{
        for (participante in participantes){
            if (participante.kilometrosActuales >= distanciaTotal.toInt()){
                println("Ha ganado: ${participante.nombre}")
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
    fun actualizarPosiciones(){
        posiciones.sortBy { it.kilometrosActuales }
    }
    fun mostrarPosiciones(){
        var cont = 1
        posiciones.forEach {
            println("$cont -> ${it.nombre}")
            cont++
        }
    }
    fun registrarAccion(vehiculo: String, accion: String) {
        val evento = historialAcciones.get(vehiculo)
        evento?.add(accion)
    }
    fun mostrarAcciones(){
        historialAcciones.forEach{ println("${it.key} = ${(it.value.forEach { println(it) })}") }
    }

    data class resultados(val vehiculo: Vehiculo, val posicion: Int, val kilometraje: Int, val paradasRepostaje: Int, val historialAcciones: List<String>)

}