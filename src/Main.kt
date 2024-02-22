fun main() {
    //val coche = Automovil("¿?","Dodge", "Charger", 50.0F, 50.0F, 0, false)
    //println(coche.calcularAutonomia())
    //coche.realizaViaje(90)
    //coche.realizarDerrape()
    //coche.repostar(0.0F)
    //println(coche.obtenerInformacion())
    //Automovil.cambiarCondicionBritania(true)
    //println(coche.obtenerInformacion())
    //val moto = Motocicleta("Pinguino","Kawasaki", "Kaboom", 20.0F, 0.0F, 100, 210)
    //moto.realizaViaje(100)
    //moto.realizarCaballito()
    //moto.repostar(100.0F)
    //println(moto.obtenerInformacion())

//--------------------------------------------------------------------------------------------------------------------//
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
}