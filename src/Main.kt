fun main() {
    println("//-------------------------------------------------------------------------------------------------------//")
    try {
        val participantes = listOf(
            Automovil("Aurora", "Seat", "Panda", 50f, 50f * 0.1f, 0F, true),
            Automovil("Boreal", "BMW", "Boeal", 80f, 80f * 0.1f, 0F, false),
            Motocicleta("Céfiro", "Derbi", "Motoreta", 15f, 15f * 0.1f, 0F, 500),
            Automovil("Dinamo", "Cintroen", "Sor", 70f, 70f * 0.1f, 0F, true),
            Automovil("Eclipse", "Renault", "Espacio", 60f, 60f * 0.1f, 0F, false),
            Motocicleta("Fénix", "Honda", "Vital", 20f, 20f * 0.1f, 0F, 250)
        )
        val carrera = Carrera("PRUEBA 1", 1000.0F, participantes)
        carrera.iniciarCarrera()
    }
    catch (e:Exception){
        println(e.message)
    }
    //EL HISTORIAL AMPLIADO ESTÁ DESACTIVADO EN EL FLUJO DE LA CARRERA
}