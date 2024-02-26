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
                obtenerCajaSorpresa(turno)
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
        mostrarResultados()
        repeat(2){
            println()
        }
        println("Historial detallado ->")
        //mostrarAcciones()
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


    fun obtenerCajaSorpresa(vehiculo: Vehiculo){
        val powerup = Random.nextInt(0,8)
        val item = CajaSorpresa.entries[powerup]
        when (item){
            CajaSorpresa.SUMAR10 -> { if (vehiculo.kilometrosActuales + 10 > distanciaTotal) {
                                            vehiculo.kilometrosActuales = distanciaTotal
                                            registrarAccion(vehiculo.nombre, "Avanzó hasta el final")
                                    }
                                    else {
                                            vehiculo.kilometrosActuales += 10F
                                            registrarAccion(vehiculo.nombre, "Avanzó 10 km")
                                    }
            }
            CajaSorpresa.TP -> {if (vehiculo.kilometrosActuales + 100 > distanciaTotal){
                                    vehiculo.kilometrosActuales = distanciaTotal
                                    registrarAccion(vehiculo.nombre, "Avanzó hasta el final")
                                }
                                else {
                                    vehiculo.kilometrosActuales += 100F
                                    registrarAccion(vehiculo.nombre, "Avanzó 100 km")
                                }
            }
            CajaSorpresa.CASILLAINICIO -> vehiculo.kilometrosActuales = 0F
            CajaSorpresa.RETRASAR -> for (participante in participantes){
                                        if (participante.nombre != vehiculo.nombre) {
                                            if (participante.kilometrosActuales - 100 > 0) {
                                                participante.kilometrosActuales -= 100F
                                                registrarAccion(participante.nombre, "Retrocedió 100 km")
                                            }
                                            else{ participante.kilometrosActuales = 0F
                                                registrarAccion(participante.nombre, "No pudo retroceder más")
                                            }
                                        }
            }
            CajaSorpresa.ALINICIO -> for (participante in participantes) {
                                        participante.kilometrosActuales = 0F
                                        registrarAccion(participante.nombre, "Volvió al inicio")
            }
            CajaSorpresa.VACIO1,CajaSorpresa.VACIO2,CajaSorpresa.VACIO3,CajaSorpresa.VACIO4 -> registrarAccion(vehiculo.nombre, "La caja no hizo nada")
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
        for (participante in resultados.reversed()){
            println(participante.obtenerInformacion())
        }
    }
    fun obtenerParadas(participante: Vehiculo): Int{
        var recuento = 0
        val historial = historialAcciones.get(participante.nombre)
        historial!!.forEach {
                if (it == "Ha repostado") recuento++
            }
        return recuento
    }
    fun mostrarResultados(){
       var cont = 1
        for (participante in posiciones.sortedBy { it.kilometrosActuales }.reversed()){
            val resultado = resultados(participante, cont, participante.kilometrosActuales.toInt(),obtenerParadas(participante),historialAcciones.get(participante.nombre))
            println(resultado.toString())
            cont++
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

    data class resultados(val vehiculo: Vehiculo, val posicion: Int, val kilometraje: Int, var paradasRepostaje: Int,val historialAcciones:
    MutableList<String>?){
        override fun toString(): String {
            return "Vehículo = ${vehiculo.nombre}, posición = $posicion, kilometraje = $kilometraje, paradas = $paradasRepostaje, historial de acciones = $historialAcciones"
        }
    }
}

