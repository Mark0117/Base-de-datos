package modelo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

public class ModeloAplicacion {
	public String[] num = {"1","2","3","4","5","6","7","8","9","10",
							"11","12","13","14","15","16","17","18","19","20",
							"21","22","23","24","25","26","27","28","29","30"};
	public ArrayList<String> nameMan = new ArrayList<String>();
	public ArrayList<String> nameWoman = new ArrayList<String>();
	public ArrayList<String> names = new ArrayList<String>();
	public ArrayList<String> lastName = new ArrayList<String>();
	public ArrayList<String> country = new ArrayList<String>();
	public ArrayList<String> mail = new ArrayList<String>();
	
	public ArrayList<String> nameAtributo = new ArrayList<String>();
	public ArrayList<String> nametypeAtributo = new ArrayList<String>();
	public ArrayList<String> typeAtributo = new ArrayList<String>();
	
	private Random rand = new Random();
	private Conexion conex;
	private ResultSet valores;
		
	
	public ModeloAplicacion(){
		conex = new Conexion();
		datos();
	}

	
	public void datos(){
		try {
	        String consulta="select * from informacion";
	        PreparedStatement cs=conex.getConexion().prepareStatement(consulta);
	        valores=cs.executeQuery();
            while (valores.next()) {
            	nameMan.add(valores.getString(2));
            	nameWoman.add(valores.getString(3));
            	names.add(valores.getString(4));
            	lastName.add(valores.getString(5));
            	country.add(valores.getString(6));
            	mail.add(valores.getString(7));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
	    }

	}
	
	public void addDate(String atributo, String nomAtributo, String tipoAtributo){
		nameAtributo.add(atributo);
		nametypeAtributo.add(nomAtributo);
		typeAtributo.add(tipoAtributo);
	}
	public void deleteDate(){
		try {
			int size = (nameAtributo.size())-1;
			if (size >=0) {
				nameAtributo.remove(size);
				nametypeAtributo.remove(size);
				typeAtributo.remove(size);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage()+ " error eliminar");
		}
		
	}
	
	public void prueba() {
		for (int i = 0; i < nameAtributo.size(); i++) {
			System.out.println(nameAtributo.get(i));
		}

	}
	
	public void fileCsv(String nomFile){
		try {
			nomFile = nomFile.replace(" ", "_");
			String ruta = "C:\\BD\\"+nomFile+".csv";
	        File archivo = new File(ruta);
	        BufferedWriter bw;
	        Random rand = new Random();
	        
	        
	        bw = new BufferedWriter(new FileWriter(archivo));
	        for (int j = 1; j < 1000001; j++) {  
	        	bw.write(String.valueOf(j)+",");
        	   	for (int i = 0; i < typeAtributo.size(); i++) { 
        	   		String c = typeAtributo.get(i);
                
        	   		switch (c) {
        	   		case "0":
                        bw.write(nameMan.get(rand.nextInt(435)));
                        break;
                    case "1":
                    	bw.write(nameWoman.get(rand.nextInt(435)));
                        break;
                    case "2":
                    	bw.write(names.get(rand.nextInt(435)));
                        break;
                    case "3":
                    	bw.write(lastName.get(rand.nextInt(435)));
                        break;
                    case "4":
                    	bw.write(country.get(rand.nextInt(435)));
                        break;
                    case "5":
                    	bw.write(names.get(rand.nextInt(435)));
                        break;
                    case "6":
                    	bw.write(String.valueOf(rand.nextInt(999999)));
                        break;
                    case "7":
                    	bw.write("55"+rand.nextInt(99999999));
                        break;
                    case "8":
                    	bw.write(names.get(rand.nextInt(435))+"@"+mail.get(rand.nextInt(435)));
                        break;
                    case "9":
                    	bw.write(String.valueOf(j));
                    	break;
                    case "10":
                    	bw.write("20"+rand.nextInt(2)+rand.nextInt(10)+"-"+num[rand.nextInt(13)]+"-"+num[rand.nextInt(30)]);
                        break;
                    case "11":
                    	bw.write(num[rand.nextInt(24)]+":"+rand.nextInt(60)+":"+rand.nextInt(60));
                        break;
        	   		}
                    if((i+1) < typeAtributo.size()){
                    	bw.write(",");
                    }
                } 
        		bw.write("\r\n");
            }
           	bw.close();
        } catch (Exception e) {
			System.out.println(e.getMessage()+" generador de archivo csv");
		}      
	
		
	}
	
	public void fileSql(String nomFile, String nomBD, String nomTable){
		try{
			nomBD = nomBD.replace(" ", "_");
			nomFile = nomFile.replace(" ", "_");
			nomTable = nomTable.replace(" ", "_");
			String ruta = "C:\\BD\\"+nomFile+".sql";
	        File archivo = new File(ruta);
	        BufferedWriter bw;
	        
	        
	        bw = new BufferedWriter(new FileWriter(archivo));
	        bw.write("create database "+nomBD+";\r\n");
	        bw.write("use "+nomBD+";\r\n\r\n");
	        
	        bw.write("create table "+nomTable+"(\r\n");
	        bw.write("id_"+nomTable+" int primary key auto_increment,\r\n");
	    	    
		   	for (int i = 0; i < typeAtributo.size(); i++) { 
		   		String c = typeAtributo.get(i);
		   			switch (c) {
		   			case "0": case "1": case "2": case "3": case "4": case "5":
		   				case "6": case "7": case "8": case "10": case "11": 
						bw.write(nameAtributo.get(i)+" varchar(250)");
						break;
					case "9":
						bw.write(nameAtributo.get(i)+" int(11)");
						break;
		   			}
		   			if((i+1) < typeAtributo.size()){
	                	bw.write(",");
	                }
		   			bw.write("\r\n");
	        } 
		   	bw.write(")engine=innodb;");
		   	bw.write("\r\n \r\n");
	        
	        for (int j = 1; j < 1000001; j++) {
	        	
	        	bw.write("insert into "+nomTable+" values("+j+",");
	        	
	    	   	for (int i = 0; i < typeAtributo.size(); i++) { 
	    	   		String c = typeAtributo.get(i);
	                switch (c) {
		                case "0":
	                        bw.write("\""+nameMan.get(rand.nextInt(435))+"\"");
	                        break;
	                    case "1":
	                    	bw.write("\""+nameWoman.get(rand.nextInt(435))+"\"");
	                        break;
	                    case "2":
	                    	bw.write("\""+names.get(rand.nextInt(435))+"\"");
	                        break;
	                    case "3":
	                    	bw.write("\""+lastName.get(rand.nextInt(435))+"\"");
	                        break;
	                    case "4":
	                    	bw.write("\""+country.get(rand.nextInt(435))+"\"");
	                        break;
	                    case "5":
	                    	bw.write("\""+names.get(rand.nextInt(435))+"\"");
	                        break;
	                    case "6":
	                    	bw.write("\""+rand.nextInt(999999)+"\"");
	                        break;
	                    case "7":
	                    	bw.write("\"55"+rand.nextInt(99999999)+"\"");
	                        break;
	                    case "8":
	                    	bw.write("\""+names.get(rand.nextInt(435))+"@"+mail.get(rand.nextInt(435))+"\"");
	                        break;
	                    case "9":
	                    	bw.write(String.valueOf(j));
	                    	break;
	                    case "10":
	                    	bw.write("\"20"+rand.nextInt(2)+rand.nextInt(10)+"-"+num[rand.nextInt(13)]+"-"+num[rand.nextInt(30)]+"\"");
	                        break;
	                    case "11":
	                    	bw.write("\""+num[rand.nextInt(24)]+":"+rand.nextInt(60)+":"+rand.nextInt(60)+"\"");
	                        break;
	                }
	                if((i+1) < typeAtributo.size()){
	                	bw.write(",");
	                }
	            } 
	    		bw.write(");\r\n");
	        }
	       	bw.close();
			} catch (IOException e){
				System.out.println(e.getMessage());
			}
	}
	
	
	


}
