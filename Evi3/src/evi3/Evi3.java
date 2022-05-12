/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evi3;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author Fredo
 */
public class Evi3 {

    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        final String PACIENTES_FILE = "listaPacientes.txt";
        final String DOCTORES_FILE = "listaDoctores.txt";
        final String CITAS_FILE = "listaCitas.txt";
        final String PASS_ADMIN_FILE = "listaPassAdmin.txt";
        final String CITAS_RELACIONADAS_FILE = "listaCitasRelacionadas.txt";

        ArrayList<Paciente> listaPacientes = new ArrayList();
        ArrayList<Doctor> listaDoctores = new ArrayList();
        ArrayList<Cita> listaCitas = new ArrayList();
        HashMap<String, String> passAdmin = new HashMap();
        HashMap<Cita, Doctor> citasRelacionadas = new HashMap();

        WriterReader writerReader = new WriterReader();

        int opc;
        boolean flag = false;

        if (new File(PACIENTES_FILE).exists()) {
            listaPacientes = (ArrayList<Paciente>) writerReader.leerObjeto(PACIENTES_FILE);
            System.out.println("Se cargo el fichero: " + PACIENTES_FILE);
        }
        if (new File(DOCTORES_FILE).exists()) {
            listaDoctores = (ArrayList<Doctor>) writerReader.leerObjeto(DOCTORES_FILE);
            System.out.println("Se cargo el fichero: " + DOCTORES_FILE);
        }
        if (new File(CITAS_FILE).exists()) {
            listaCitas = (ArrayList<Cita>) writerReader.leerObjeto(CITAS_FILE);
            System.out.println("Se cargo el fichero: " + CITAS_FILE);
        }
        if (new File(PASS_ADMIN_FILE).exists()) {
            passAdmin = (HashMap<String, String>) writerReader.leerObjeto(PASS_ADMIN_FILE);
            System.out.println("Se cargo el fichero: " + PASS_ADMIN_FILE);
        }
        if (new File(PASS_ADMIN_FILE).exists() == false) {
            System.out.println("Registre un usuario");
            String usuario = scanner.nextLine();
            System.out.println("Registre password");
            String contrasena = scanner.nextLine();
            passAdmin.put(usuario, contrasena);
            writerReader.escribirObjeto(passAdmin, PASS_ADMIN_FILE);
            System.out.println("Se ha guaradado");
            System.exit(0);

        }
        if (new File(CITAS_RELACIONADAS_FILE).exists()) {
            citasRelacionadas = (HashMap<Cita, Doctor>) writerReader.leerObjeto(CITAS_RELACIONADAS_FILE);
            System.out.println("Se cargo el fichero: " + CITAS_RELACIONADAS_FILE);
        }

        if (solicitarCredenciales(passAdmin) == true) {
            do {

                opc = desplegarMenu();
                switch (opc) {

                    case 1:
                        System.out.println("Doctores en lista: " + listaDoctores.toString());
                        listaDoctores.add(altaDoctor());
                        writerReader.escribirObjeto(listaDoctores, DOCTORES_FILE);
                        break;
                    case 2:
                        System.out.println("Pacientes en lista: " + listaPacientes.toString());
                        listaPacientes.add(altaPaciente());
                        writerReader.escribirObjeto(listaPacientes, PACIENTES_FILE);
                        break;
                    case 3:
                        System.out.println("Citas en lista: " + listaCitas.toString());
                        listaCitas.add(crearCita());
                        writerReader.escribirObjeto(listaCitas, CITAS_FILE);
                        break;
                        case 4:
                           System.out.println("Citas relacionadas con Docs:" + citasRelacionadas.toString());
                        Cita cita = relacionarCita(listaCitas);
                        Doctor doctor = relacionarDoctor(listaDoctores);
                        if (cita != null && doctor != null) {
                            citasRelacionadas.put(cita, doctor);
                            System.out.println("Relacion exitosa");
                            writerReader.escribirObjeto(citasRelacionadas, CITAS_RELACIONADAS_FILE);

                        } else {
                            System.out.println("Relacion fallida");
                        }
                        break;

                    case 5:
                        flag = true;
                        break;

                }

                
            }while (flag == false);
 
            }
           
        }
    
    
    public static int desplegarMenu(){
        
        String entrada;
        System.out.println("\n Hola\n" 
                + "1. Alta Docs \n"
                + "2.Alta pacientes \n"
                + "3. Crear Cita \n"
                + "4. Relacionar Cita \n"
                + "5. Salir");
        
        entrada = scanner.nextLine();
        if (procesarEntrada(entrada)== true){
            return Integer.parseInt(entrada);
        }
        else{
            
            return 0;
        }
    }
    
    public static boolean procesarEntrada(String opc){
        try{
            
            Integer.parseInt(opc);
            return true;

        } catch (NumberFormatException ex){
            System.out.println("Ingresa un numero");
            return false;
        }
    }
    public static Doctor altaDoctor(){
        Doctor doctor = new Doctor();
        System.out.println("Ingresa ID");
        doctor.setId(scanner.nextLine());
        System.out.println("Ingresa Nombre");
        doctor.setNombre(scanner.nextLine());
        System.out.println("Ingresa Especialidad");
        doctor.setEspecialidad(scanner.nextLine());
        System.out.println("Alta correcta");
        return doctor;
 
    }
    public static Paciente altaPaciente(){
        Paciente paciente = new Paciente();
        System.out.println("Ingresa ID");
        paciente.setId(scanner.nextLine());
        System.out.println("Ingresa Nombre");
        paciente.setNombre(scanner.nextLine());
        System.out.println("Alta correcta");
        return paciente;
 
    }
    public static Cita crearCita(){
        Cita cita = new Cita();
        System.out.println("Ingresa ID");
        cita.setId(scanner.nextLine());
        System.out.println("Ingresa la fecha");
        cita.setFecha(scanner.nextLine());
        System.out.println("Ingresa la hora");
        cita.setHora(scanner.nextLine());
        System.out.println("Ingresa el motivo");
        cita.setMotivo(scanner.nextLine());
        System.out.println("Alta correcta");
        return cita;
 
    }
    
    public static Cita relacionarCita(ArrayList<Cita> listaCitas){
        Cita cita;
        
        System.out.println("Citas agendadas: " +listaCitas.toString());
        System.out.println("id Citas");
        String entrada = scanner.nextLine();
        for (int i =0;i<listaCitas.size();i++){
            cita = listaCitas.get(i);
            if (cita.getId().equals(entrada)){
                System.out.println("Encontrada");
                return cita;
            }
 
        }
        System.out.println("No se encontro");
        return null;
    }
    
    public static Doctor relacionarDoctor(ArrayList<Doctor> listaDoctores){
        Doctor doctor;
        
        System.out.println("Lista docs : " +listaDoctores.toString());
        System.out.println("id Doc");
        String entrada = scanner.nextLine();
        for (int i =0;i<listaDoctores.size();i++){
            doctor = listaDoctores.get(i);
            if (doctor.getId().equals(entrada)){
                System.out.println("Encontrada");
                return doctor;
            }
 
        }
        System.out.println("No se encontro");
        return null;
    }
    
    public static boolean solicitarCredenciales(HashMap passAdmin){
        String usuario;
        String contrasena;
        
        System.out.println("Introduce un usuario");
        usuario = scanner.nextLine();
        System.out.println("Introduce un contra");
        contrasena = scanner.nextLine();
        if(passAdmin.containsKey(usuario) == true){
            if(passAdmin.containsValue(contrasena) == true){
                System.out.println("Acceso correcto");
                return true;}
            else{
            
           System.out.println("No se encontro");
            return false;
            }
        } else{
                    System.out.println("incorrecto");
                    return false;
  
        }
        
    }
                

    }


