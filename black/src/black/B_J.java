package black;



	import java.util.Scanner;
	import java.util.Vector;


	public class B_J {

	    private static Scanner scanner = new Scanner(System.in);
	    private int[]baraja;  
	    private int posicion; 
	    private Vector mano;  

	    public static void main(String[] args)
	    {
	        new B_J().run();
	    }

	    public void run()
	    {

	        int dinero;          
	        int apuesta;           
	        boolean ganador;   

	        System.out.println("bienvenido al juego blackjack.");
	        System.out.println();

	        dinero = 100; 

	        while (true)
	        {
	            System.out.println("tienes " + dinero + " dolares.");
	            do
	            {
	                System.out.println("cuanto para apostar?  (introduce 0 para finalizar.)");
	                System.out.print("? ");
	                apuesta = scanner.nextInt();
	                if (apuesta < 0 || apuesta > dinero)
	                {
	                    System.out.println("respuesta entre 0 y;  " + dinero + '.');
	                }
	            } while (apuesta < 0 || apuesta > dinero);
	            if (apuesta == 0)
	            {
	                break;
	            }
	            ganador = Jugada();
	            if (ganador)
	            {
	                dinero = dinero + apuesta;
	            } else
	            {
	                dinero = dinero - apuesta;
	            }
	            System.out.println();
	            if (dinero == 0)
	            {
	                System.out.println("¡parece que tu dinero es insuficiente!");
	                break;
	            }
	        }

	        System.out.println();
	        System.out.println("te vas con $" + dinero + '.');

	    } 
	   public boolean Jugada()
	    {
	        Vector movimiento;   
	        Vector jugada;     

	        baraja = new int[52];
	        int cardCt = 0; 
	        for (int suit = 0; suit <= 3; suit++)
	        {
	            for (int value = 1; value <= 13; value++)
	            {
	               baraja[cardCt] = value;
	                cardCt++;
	            }
	        }
	        posicion = 0;

	        movimiento = new Vector();
	        jugada = new Vector();


	        barajar();

	        movimiento.addElement(Mover());
	        movimiento.addElement(Mover());
	        jugada.addElement(Mover());
	        jugada.addElement(Mover());

	        System.out.println();
	        System.out.println();

	        if (valor(movimiento) == 21)
	        {
	            System.out.println("Agente tendra " + mostrarCarta(traerCarta(movimiento, 0)) + " y el " + mostrarCarta(traerCarta(movimiento, 1)) + ".");
	            System.out.println("Usuario tiene " + mostrarCarta(traerCarta(jugada, 0)) + " y el " + mostrarCarta(traerCarta(jugada, 1)) + ".");
	            System.out.println();
	            System.out.println("agente tiene BJ, el gana.");
	            return false;
	        }

	        if (valor(jugada) == 21)
	        {
	        	System.out.println("Agente tendra " + mostrarCarta(traerCarta(movimiento, 0)) + " y el " + mostrarCarta(traerCarta(movimiento, 1)) + ".");
	            System.out.println("Usuario tiene " + mostrarCarta(traerCarta(jugada, 0)) + " y el " + mostrarCarta(traerCarta(jugada, 1)) + ".");
	            System.out.println();
	            System.out.println("Tu tienes JB, tú ganas.");
	            return true;
	        }

	       

	        while (true)
	        {
	            System.out.println();
	            System.out.println();
	            System.out.println("tus cartas son:");
	            for (int i = 0; i < jugada.size(); i++)
	            {
	                System.out.println("    " + mostrarCarta(traerCarta(jugada, i)));
	            }
	            System.out.println("total tuyo es;  " + valor(jugada));
	            System.out.println();
	            System.out.println("Mostrado es " + mostrarCarta(traerCarta(movimiento, 0)));
	            System.out.println();
	            System.out.print("Hit (H) or Stand (S)? ");
	            char accion;  
	            do
	            {
	            	accion = Character.toUpperCase(scanner.next().charAt(0));
	                if (accion != 'H' && accion != 'S')
	                {
	                    System.out.print("responde H or S:  ");
	                }
	            } while (accion != 'H' && accion != 'S');

	            if (accion == 'S')
	            {
	               
	                break;
	            } else
	            {  
	                int nuevaCarta = Mover();
	                jugada.addElement(nuevaCarta);
	                System.out.println();
	                System.out.println("Usuario  hits.");
	                System.out.println("tu carta es;  " + mostrarCarta(nuevaCarta));
	                System.out.println("total ahora " + valor(jugada));
	                if (valor(jugada) > 21)
	                {
	                    System.out.println();
	                    System.out.println("Perdió por pasar de  21.  ");
	                    System.out.println("la tarjeta era:  " + mostrarCarta(traerCarta(movimiento, 1)));
	                    return false;
	                }
	            }

	        } 

	       

	        System.out.println();
	        System.out.println("usuario tiene: .");
	        System.out.println("carta es: ");
	        System.out.println("    " + mostrarCarta(traerCarta(movimiento, 0)));
	        System.out.println("    " + mostrarCarta(traerCarta(movimiento, 1)));
	        while (valor(movimiento) <= 16)
	        {
	            int newCard = Mover();
	            System.out.println("el agente tiene:  " + mostrarCarta(newCard));
	            movimiento.addElement(newCard);
	        }
	        System.out.println("Su total es;  " + valor(movimiento));

	       

	        System.out.println();
	        if (valor(movimiento) > 21)
	        {
	            System.out.println("agente pierde por pasar de 21.  Usted ganó.");
	            return true;
	        } else
	        {
	            if (valor(movimiento) == valor(jugada))
	            {
	                System.out.println("Agente gano.  perdió.");
	                return false;
	            } else
	            {
	                if (valor(movimiento) > valor(jugada))
	                {
	                    System.out.println("Agente gana, " + valor(movimiento) + " puntos " + valor(jugada) + ".");
	                    return false;
	                } else
	                {
	                    System.out.println("Usted gana " + valor(jugada) + " puntos " + valor(movimiento) + ".");
	                    return true;
	                }
	            }
	        }

	    }  

	    public int Mover()
	    {
	        if (posicion == 52)
	        {
	            barajar();
	        }
	        posicion++;
	        return baraja[posicion - 1];
	    }

	    public void barajar()
	    {
	      
	        for (int i = 51; i > 0; i--)
	        {
	            int rand = (int) (Math.random() * (i + 1));
	            int temp = baraja[i];
	            baraja[i] = baraja[rand];
	            baraja[rand] = temp;
	        }
	        posicion = 0;
	    }

	    public int traerCarta(Vector mano, int position)
	    {
	       
	        if (position >= 0 && position < mano.size())
	        {
	            return ((Integer)mano.elementAt(position)).intValue();
	        } else
	        {
	            return 0;
	        }
	    }

	    public int valor(Vector mano)
	    {
	        
	        int val;      
	        boolean as;  
	        
	        int cartas;    

	        val = 0;
	        as = false;
	        cartas = mano.size();

	        for (int i = 0; i < cartas; i++)
	        {
	        
	            int carta;    
	            int valorCarta;  
	            carta = traerCarta(mano, i);
	            valorCarta = valorCarta(carta);  
	            if (valorCarta > 10)
	            {
	            	valorCarta = 10;   
	            }
	            if (valorCarta == 1)
	            {
	                as = true;     
	            }
	            val = val + valorCarta;
	        }

	      

	        if (as == true && val + 10 <= 21)
	        {
	            val = val + 10;
	        }

	        return val;

	    }
	    public int valorCarta(int carta)
	    {
	        int result = carta;
	        switch (carta)
	        {
	            case 11:
	            case 12:
	            case 13:
	                result =  10;
	        }
	        return result;
	    }
	    public String mostrarCarta(int carta)
	    {
	        switch (carta)
	        {
	            case 1:
	                return "As";
	            case 2:
	                return "2";
	            case 3:
	                return "3";
	            case 4:
	                return "4";
	            case 5:
	                return "5";
	            case 6:
	                return "6";
	            case 7:
	                return "7";
	            case 8:
	              return "8";
	            case 9:
	                return "9";
	            case 10:
	                return "10";
	            case 11:
	                return "Jack";
	            case 12:
	                return "Queen";
	            case 13:
	                return "King";
	            default:
	                return "??";
	        }
	    }
	

}
