package improve.my.city.user;
import java.util.ArrayList;

import improve.my.city.enums.*;


public class Citizen extends User {
    private String cpf;
    private Districts residence;
    private String address;
    private ArrayList<String> created;
    private ArrayList<String> confirmed;
    private static Citizen citizenInstance;
    
    
    //Implementação de Singleton
    private Citizen() {
    }

    public static Citizen getInstance (String name, int password, String cpf, String address){
        if(citizenInstance == null){
        citizenInstance = new Citizen();
        citizenInstance.name = name;
        citizenInstance.password = password;
        citizenInstance.cpf = cpf;
        citizenInstance.address = address;
        citizenInstance.created = new ArrayList<String>();
        citizenInstance.confirmed = new ArrayList<String>();
        }
        return citizenInstance;
    }


    public Districts getResidence(){
        return this.residence;
    }
    
    public void setResidence(Districts residence) {
        this.residence = residence;
    }
    public String getName(){
        return this.name;
        
    }

    public int getCreatedSize() {
        return this.created.size();
    }

    public int getConfirmedSize() {
        return this.confirmed.size();
    }


    public String getCreated(int position) {
        return created.get(position);
        
    }
    public String getConfirmed(int position) {
        return confirmed.get(position);
    }

    public void setCriados(String id) {
        this.created.add(id);
    }
    public void setConfirmados(String id) {
        this.confirmed.add(id);
    }
    public String getCpf() {
        return cpf;
    }
    @Override
    public String toString(){
        return "\nNome: " +this.name + "\n"+"CPF: " + this.cpf + "\n"+ "Bairro: "+ residence + "\n" + "Endereço: "+ address +"\n";
    }
    

    


   

    
}
