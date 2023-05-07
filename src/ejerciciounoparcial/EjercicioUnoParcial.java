/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejerciciounoparcial;

import java.io.*;

/**
 *
 * @author suare
 */
public class EjercicioUnoParcial {

    class Nodo
    {
        String info;
        int rep=1;
        Nodo izq, der;
    }
    Nodo raiz;    
    int cant;
    int altura;
    
    public EjercicioUnoParcial() {
        raiz = null;
    }
    
    public void insertar (String info) {
        if(!existe(info)) {
            Nodo nuevo;
            nuevo = new Nodo();
            nuevo.info = info;
            nuevo.izq = null;
                nuevo.der = null;
            if (raiz == null)
                raiz = nuevo;
            else {
                Nodo anterior = null, reco;
                reco = raiz;
                while (reco != null) {
                    anterior = reco;
                    if(info.compareTo(reco.info) < 0)
                        reco = reco.izq;
                    else
                        reco = reco.der;
                }
                if (info.compareTo(anterior.info) < 0)
                    anterior.izq = nuevo;
                else
                    anterior.der = nuevo;
            }
        }
    }
    
    private void imprimirRep (Nodo reco, int nivel) {
       if (reco != null) {
           imprimirRep(reco.izq,nivel+1);
           System.out.println(reco.info+" "+reco.rep);
           imprimirRep(reco.der, nivel+1);
       }
   }
   public void imprimirRep() {
       imprimirRep(raiz,1);
       System.out.println("");
   }
    
    public boolean existe(String info) {
        Nodo reco = raiz;
        while(reco!=null) {
            if(info.equalsIgnoreCase(reco.info)){
                reco.rep++;
                return true;
            }
            else
                if(info.compareTo(reco.info)>0)
                    reco=reco.der;
                else
                    reco=reco.izq;
        }
        return false;
    }
    private void imprimirEntre (Nodo reco) {
        if (reco != null) {
            imprimirEntre(reco.izq);
            System.out.println(reco.info + "");
            imprimirEntre (reco.der);
        }
    }
    
    public void imprimirEntre () {
        imprimirEntre(raiz);
        System.out.println();
    }
    private void cantidad (Nodo reco) {
        if (reco!=null) {
            cant++;
            cantidad(reco.izq);
            cantidad(reco.der);
        }       
    }
    public int cantidad() {
        cant=0;
        cantidad(raiz);
        return cant;
    }
    private void cantidadNodosHoja(Nodo reco) {
        if (reco!=null) {
            if (reco.izq==null && reco.der ==null) 
                cant++;
            cantidadNodosHoja(reco.izq);
            cantidadNodosHoja(reco.der);
        }
    }
    
    public int cantidadNodosHoja() {
        cant=0;
        cantidadNodosHoja(raiz);
        return cant;
    }
    
   private void imprimirEntreConNivel (Nodo reco, int nivel) {
       if (reco != null) {
           imprimirEntreConNivel(reco.izq,nivel+1);
           System.out.println(reco.info + " ("+nivel+") -");
           imprimirEntreConNivel(reco.der, nivel+1);
       }
   }
   public void imprimirEntreConNivel() {
       imprimirEntreConNivel(raiz,1);
       System.out.println("");
   }
   
   private void retornarAltura (Nodo reco, int nivel) {
       if(reco != null) {
           retornarAltura(reco.izq, nivel+1);
           if (nivel>altura)
               altura=nivel;
           retornarAltura(reco.der,nivel+1);           
       }
   }
   public int retornarAltura() {
       altura = 0;
       retornarAltura(raiz,1);
       return altura;
   }
   public void mayorValor() {
       if (raiz!=null) {
           Nodo reco=raiz;
           while (reco.der!=null)
               reco=reco.der;
           System.out.println("Mayor valor del arbol:" +reco.info);
       }
   }
   
   public void borrarMenor() {
       if (raiz!=null) {
           if(raiz.izq==null)
               raiz=raiz.der;
           else {
               Nodo atras = raiz;
               Nodo reco =raiz.izq;
               while (reco.izq!=null) {
                   atras = reco;
                   reco = reco.izq;                           
               }
               atras.izq = reco.der;
           }
       }
   }
    public static void main(String[] args) throws IOException {
        String palabra, palabra2;
        int bandera=0;
        EjercicioUnoParcial abo = new EjercicioUnoParcial();
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Inserte una palabra: ");
        palabra=br.readLine();
        for (int i=0;i<palabra.length();i++) {            
            if(palabra.charAt(i)==' ')
            {
                abo.insertar(palabra.substring(bandera, i));
                bandera=i+1;
            }
            else if(i==palabra.length()-1) {
                abo.insertar(palabra.substring(bandera, i+1));
            }
        }        
        System.out.println("Impresion entreorden: ");
        abo.imprimirEntre();
        System.out.println("Cantidad de nodos del arbol: "+abo.cantidad());
        System.out.println("Cantidad de nodos hoja: "+abo.cantidadNodosHoja());
        System.out.println("Impresion repetitividad.");
        abo.imprimirRep();
        System.out.println("Altura del arbol:");
        System.out.println(abo.retornarAltura());
        abo.mayorValor();
    }
    
}