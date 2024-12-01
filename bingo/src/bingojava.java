import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
import java.util.HashSet;

public class bingojava {
    public static void main(String[] args){
        Scanner entradaUsuario = new Scanner(System.in);  //Para leer la entrada del usuario
        Random random = new Random();         //Para generar numeros aleatorios
        ArrayList<Integer> carton = new ArrayList<>();   //Lista para almacenar los 10 numeros del carton
        HashSet<Integer> numerosRepetidos = new HashSet<>();  //Para evitar numeros repetidos
        boolean bingoCantado = false;  //booleano que controla si ya se cantó bingo
        boolean lineaCantada = false;  //booleano que controla si ya se cantó línea
        int numerosParaLinea= 0; //contador para regitrar cuando se cantó linea

    //GENERAMOS CARTON BINGO

        while (carton.size() < 10 ) { //mientras no haya 10 numeros en el carton
            int numero= random.nextInt(99)+1; //genera un numero entre el 1 y 99. +1 porque si no sería entre 0 y 98
            if (!carton.contains(numero)) { //verifica que el numero no este en el carton
                carton.add(numero); //añade el numero al carton

            }

        }
        System.out.println("Tu cartón de bingo es: " + carton); //mostramos el carton al jugador

    //SOLICITAR APUESTA
        System.out.println("Introduce tu apuesta: ");
        double apuesta = 0; //variable para almacenar apuesta
        while (true) {   //bucle para asegurar entrada valida
            if (entradaUsuario.hasNextDouble()) {  //verifica que la entrada sea un numero decimal
                apuesta = entradaUsuario.nextDouble();

                if (apuesta > 0) break; //la apuesta debe ser positiva
            }
            System.out.println("Por favor, introduce un número positivo");
            entradaUsuario.nextLine(); //limpia la entrada incorrecta
        }


    //SOLICITAR PREDICCION
        System.out.print("¿En cuántos números crees que acertarás el bingo? ");
        int prediccion = 0; // Variable para almacenar la predicción
        while (true) { // Bucle para asegurar entrada válida
            if (entradaUsuario.hasNextInt()) { // Verifica que la entrada sea un número entero
                prediccion = entradaUsuario.nextInt();
                if (prediccion > 0) break; // La predicción debe ser positiva
            } else {
                System.out.println("Por favor, introduce una cantidad válida para tu predicción.");
                entradaUsuario.nextLine(); // Limpia la entrada incorrecta
            }
        }

    //sacar numeros hasta cantar bingo
        int numerosSacados = 0; //contador de numeros generados
        int aciertos = 0;    //contador de aciertos en el carton


        while (!bingoCantado){  //mientras no se haya cantado bingo
            int numeroGenerado;

        //generar numeros unicos
            do {
                numeroGenerado= random.nextInt(99)+1; //genera un numero entre el 1 y 99
            } while (numerosRepetidos.contains(numeroGenerado));  //repite si el numero ya fue generado

            numerosRepetidos.add(numeroGenerado); //agrega el numero al conjunto de numeros generados
            numerosSacados++;  //incrementa el contador de numeros generados

        //verificar si el numero esta en el carton
            if (carton.contains(numeroGenerado)) {  //si el numero esta en el carton
                aciertos++;  //incrementa del contador de aciertos
                System.out.println("¡Acertaste! Llevas " + aciertos + " aciertos."); //muestra el progreso de aciertos


        //cantar línea
            if (aciertos ==5 && !lineaCantada) {  //si hay 5 aciertos y aun no se ha cantado linea
                System.out.println("¡Línea! Han salido " + numerosSacados + " números."); //muestra que se cantó en linea
                numerosParaLinea = numerosSacados;  //registro para saber cuantos numeros se necesitaron para cantar linea
                lineaCantada=true; //marca que la linea ya fue cantada
            }
        //cantar bingo
            if (aciertos==10){  //si hay 10 aciertos (todos los numeros del carton
                System.out.println("¡Bingo! Han salido "+ numerosSacados + "números.");
                bingoCantado=true;
            }
            }
        }

        //MOSTRAR RESULTADOS
        System.out.println("\nResultados:");
        System.out.println("Números necesarios para cantar línea: " + numerosParaLinea + "(5 aciertos)");
        System.out.println("Números necesarios para cantar bingo: " + numerosSacados + " (10 aciertos)");

        //CALCULAR PRECIO SI SE GANÓ PREMIO
        if (prediccion == numerosSacados){  //si el jugador acertó en su prediccion
            double premio = apuesta*10; //calcular el premio (apuesta * 10)
            System.out.printf("¡Felicidades! Has acertado en tu apuesta. Ganaste: %.2f\n", premio); //muestra el valor del premio

        } else {
            System.out.println("No acertaste en tu predicción. Suerte la próxima vez");  //mensaje si no se acierta la apuesta
        }
        entradaUsuario.close();  //cerramos el scanner
    }


}

