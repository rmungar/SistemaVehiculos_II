import kotlin.random.Random

class Carrera {
        val nombreCarrera = "Carrera"
        val DistanciaTotal: Int = 1000
        val participantes:MutableList<Vehiculo> = mutableListOf<Vehiculo>()
        var estadoCarrera: Boolean = false
        var historialAcciones: MutableMap<String, MutableList<String>> = mutableMapOf<String, MutableList<String>>()
        val posiciones: MutableList<Pair<String, Int>> = mutableListOf<Pair<String, Int>>()

    fun iniciarCarrera(){
        estadoCarrera = true
    }
    fun avanzarVehiculo(vehiculo: Vehiculo){
        vehiculo.realizaViaje(Random.nextInt(10,200))
    }

    fun repostarVehiculo(vehiculo: Vehiculo, cantidad:Float){
        vehiculo.repostar(cantidad)
    }

    fun realizarFiligrana(vehiculo: Vehiculo){
        val hacer = Random.nextBoolean()
        if (hacer){
            if (vehiculo is Automovil){
                vehiculo.realizarDerrape()
            }
            else{
                vehiculo.realizarCaballito()
            }
        }
        else{
            println("${vehiculo.nombre} no hizo filigrana")
        }
    }

    fun actualizarPosiciones(){
        val posiciones = mutableListOf<Pair<String, Int>>()
        for (participante in participantes){
            posiciones.add(Pair(participante.nombre, participante.kilometrosActuales))
        }
        posiciones.sortBy { it.second }
    }

    fun determinarGanador(){
        for (participante in posiciones){
            if (participante.second == DistanciaTotal){
                println("Ha ganado: ${participante.first}")
                break
            }
        }
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
        val acciones: MutableList<Pair<String, MutableList<String>>>
        for (participante in participantes) {
            acciones.add(participante.nombre, mutableListOf<String>("Entra en carrera"))
        }
    }
}