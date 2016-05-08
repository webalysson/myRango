
package com.alysson.myrango.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

/**

 */
public class Datas {
	String data = "";
	Calendar cal = null;
	Date date = null;
	SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
	
	
	public  Datas() {
		cal = Calendar.getInstance();
		format.setLenient(false);
		
	}
        
        public Calendar getDataHora(){
        //operacao.setDataCad( new SimpleDateFormat("yyyy-MM-dd").parse("1986-01-02") );
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        return Calendar.getInstance();              
        }
        
        public Calendar getDataAtual(){
        //operacao.setDataCad( new SimpleDateFormat("yyyy-MM-dd").parse("1986-01-02") );
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        return Calendar.getInstance();              
        }        


	public String revestresData(String dt){
		if ((dt!=null)&&(!dt.equals(""))){	
		return (getDiaMes(dt)+"/"+getMes(dt)+"/"+getAno(dt));
		}else return "";
	}

	public String converte2Banco(String dt){
		if ((dt!=null)&&(!dt.equals(""))){	
		return (getAno(dt)+"-"+getMes(dt)+"-"+getDiaMes(dt));
		}else return "";
	}	

	public String converte2Banco(Date dt){
		if ((dt!=null)){	
		return (getAno(dt)+"-"+getMes(dt)+"-"+getDiaMes(dt));
		}else return "";
	}	
        
        
	
	public String getDiaAndMes(){
		return (getDiaMes()+"/"+getMes());
	}

	public String getDiaAndMes(String dt){
		if ((dt!=null)&&(!dt.equals(""))){		
		return (getDiaMes(dt)+"/"+getMes(dt));
	}else return ""; 
	}

	public String getDiaMes(){
		date = new Date();
		cal = Calendar.getInstance();
		cal.setTime(date);
		int dia = cal.get(Calendar.DAY_OF_MONTH);
		if(dia<10){
			return ("0"+(String.valueOf(dia)));
		}else
			return (String.valueOf(dia));
	}

	public String getDiaMes(Date data){
		cal = Calendar.getInstance();
		cal.setTime(data);
		int dia = cal.get(Calendar.DAY_OF_MONTH);
		if(dia<10){
			return ("0"+(String.valueOf(dia)));
		}else
			return (String.valueOf(dia));
	}

	public String getDiaMes(String dt){
		if ((dt!=null)&&(!dt.equals(""))){	
		try {date = format.parse(dt);} catch (Exception e) {e.printStackTrace();}
		cal = Calendar.getInstance();
		cal.setTime(date);
		int dia = cal.get(Calendar.DAY_OF_MONTH);

		if(dia<10){
			return ("0"+(String.valueOf(dia)));
		}else
			return (String.valueOf(dia));
	}else 	return ("");			
	}
	
	public String getHora(){
			date = new Date();
			cal = Calendar.getInstance();
			cal.setTime(date);
			int hora = cal.get(Calendar.HOUR_OF_DAY);
			return (String.valueOf(hora));
		}

	public String getMinuto(){
			date = new Date();
			cal = Calendar.getInstance();
			cal.setTime(date);
			int mim = cal.get(Calendar.MINUTE);
			return (String.valueOf(mim));
		}

	public String getHoraMinuto(){
	return this.getHora()+":"+this.getMinuto();
	}



	public String getNomeMes(){
		String Mes = null;
		date = new Date();
		cal = Calendar.getInstance();
		cal.setTime(date);
		int mes = cal.get(Calendar.MONTH)+1;
		switch(mes){
			case 1: Mes="Janeiro";break;
			case 2: Mes="Fevereiro";break;
			case 3: Mes="Mar�o";break;
			case 4: Mes="Abril";break;
			case 5: Mes="Maio";break;
			case 6: Mes="Junho";break;
			case 7: Mes="Julho";break;
			case 8: Mes="Agosto";break;
			case 9: Mes="Setembro";break;
			case 10: Mes="Outubro";break;
			case 11: Mes="Novembro";break;
			case 12: Mes="Dezembro";break;
		}
		return Mes;
	}

	public String getNomeMes(String dt){
	 if ((dt!=null)&&(!dt.equals(""))){		
		String Mes = null;
		try {date = format.parse(dt);} catch (Exception e) {e.printStackTrace();}
		cal = Calendar.getInstance();
		cal.setTime(date);
		int mes = cal.get(Calendar.MONTH)+1;
		switch(mes){
			case 1: Mes="Janeiro";break;
			case 2: Mes="Fevereiro";break;
			case 3: Mes="Mar�o";break;
			case 4: Mes="Abril";break;
			case 5: Mes="Maio";break;
			case 6: Mes="Junho";break;
			case 7: Mes="Julho";break;
			case 8: Mes="Agosto";break;
			case 9: Mes="Setembro";break;
			case 10: Mes="Outubro";break;
			case 11: Mes="Novembro";break;
			case 12: Mes="Dezembro";break;
		}
		return Mes;
	 }else return "";	
	}

	public String getMes(){
		date = new Date();
		cal = Calendar.getInstance();
		cal.setTime(date);
		int mes = cal.get(Calendar.MONTH)+1;

		if(mes<10){
			return ("0"+(String.valueOf(mes)));
		}else
			return (String.valueOf(mes));
	}

	public String getMes(Date data){
		cal = Calendar.getInstance();
		cal.setTime(data);
		int mes = cal.get(Calendar.MONTH)+1;

		if(mes<10){
			return ("0"+(String.valueOf(mes)));
		}else
			return (String.valueOf(mes));
	}

	
	public String getMes(String dt){
	if ((dt!=null)&&(!dt.equals(""))){		
		try {date = format.parse(dt);} catch (Exception e) {e.printStackTrace();}
		cal = Calendar.getInstance();
		cal.setTime(date);
		int mes = cal.get(Calendar.MONTH)+1;

		if(mes<10){
			return ("0"+(String.valueOf(mes)));
		}else
			return (String.valueOf(mes));
	}else return "";		
	}

	public String getAno(){
		date = new Date();
		cal = Calendar.getInstance();
		cal.setTime(date);
		int ano = cal.get(Calendar.YEAR);
		return (String.valueOf(ano));
	}
	
	public String getAno(Date data){
		cal = Calendar.getInstance();
		cal.setTime(data);

		int ano = cal.get(Calendar.YEAR);

		return (String.valueOf(ano));
	}


	public String getAno(String dt){
	 if ((dt!=null)&&(!dt.equals(""))){		
		try {date = format.parse(dt);} catch (Exception e) {e.printStackTrace();}
		cal = Calendar.getInstance();
		cal.setTime(date);

		int ano = cal.get(Calendar.YEAR);

		return (String.valueOf(ano));
	 }else return "";	
	}

	public String getDiaSemana(){
		String DiaSemana=null;
		date = new Date();

		cal = Calendar.getInstance();
		cal.setTime(date);
		int diaSemana = cal.get(Calendar.DAY_OF_WEEK);

		switch(diaSemana){
			case 1: DiaSemana="Domingo"; break;
			case 2: DiaSemana="Segunda Feira"; break;
			case 3: DiaSemana="Ter�a Feira"; break;
			case 4: DiaSemana="Quarta Feira"; break;
			case 5: DiaSemana="Quinta Feira"; break;
			case 6: DiaSemana="Sexta Feira"; break;
			case 7: DiaSemana="S�bado";break;
		}
		return (DiaSemana);
	}



	public String getDiaSemana(String dt){
	  if ((dt!=null)&&(!dt.equals(""))){		
		String DiaSemana=null;
		try {date = format.parse(dt);} catch (Exception e) {e.printStackTrace();}

		cal = Calendar.getInstance();
		cal.setTime(date);
		int diaSemana = cal.get(Calendar.DAY_OF_WEEK);

		switch(diaSemana){
			case 1: DiaSemana="Domingo"; break;
			case 2: DiaSemana="Segunda Feira"; break;
			case 3: DiaSemana="Ter�a Feira"; break;
			case 4: DiaSemana="Quarta Feira"; break;
			case 5: DiaSemana="Quinta Feira"; break;
			case 6: DiaSemana="Sexta Feira"; break;
			case 7: DiaSemana="S�bado";break;
		}
		return (DiaSemana);
	  }else return "";	
	}
 
 public Date Convertlocale(String dt){
	Date data = null;
	String dataTexto = new String(dt);
	SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
	format.setLenient(false);
	try {
		data = format.parse(dataTexto);
	} catch (ParseException e) {
		// TODO Bloco de captura gerado automaticamente
		e.printStackTrace();
	}
   return data;
 }

 public String toString(Date data){
	    String s_data="";
	    if(data!=null)
	    	s_data = format.format(data);
	   return s_data;
	 }
 
 public Vector getPeriodo(String dtini, String dtfim){
	    date = Convertlocale(dtini);
	    Vector vetdias = new Vector();
		cal = Calendar.getInstance();
		cal.setTime(date);
	     
	    //SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy"); 
	    SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		long dif_dias=this.compararDatas(dtini,dtfim);
		
		for(int i=0;i<=dif_dias-1;i++){
			cal.setTime(date);
			cal.add(Calendar.DAY_OF_MONTH, i);
		    vetdias.addElement(sd.format(cal.getTime()));
		}
		
	return vetdias;	
	
 }

 public Date somaMes(Date hoje,int meses){
	    Date amanha = null;
	    if(hoje!=null){
	    	try {
	    		Calendar cal = Calendar.getInstance();
	    		cal.setTime(hoje);
	    		cal.add(Calendar.MONTH, meses); // adiciona meses a data
	    		amanha = cal.getTime(); 
	    	}catch (Exception e) {e.printStackTrace();}
	    }
	 return amanha;
	 }

 
 public String getAmanha(String dtini){
	String amanha=null;
	SimpleDateFormat sd = new SimpleDateFormat ("dd/MM/yyyy");
	try {
		Date hoje = format.parse(dtini);
		Calendar cal = Calendar.getInstance();
		cal.setTime(hoje);
		cal.add(Calendar.DAY_OF_MONTH, 1); // adiciona um dia na data
		amanha = sd.format(cal.getTime());
	} catch (Exception e) {e.printStackTrace();}	
 return amanha;
 }

 public Date getAmanha(Date hoje){
	 return getAmanha(hoje,1);
 }
 public Date getAmanha(Date hoje,int dias){
	    Date amanha = null;
	    if(hoje!=null){
	    	try {
	    		Calendar cal = Calendar.getInstance();
	    		cal.setTime(hoje);
	    		cal.add(Calendar.DAY_OF_MONTH, dias); // adiciona dias a data
	    		amanha = cal.getTime(); 
	    	}catch (Exception e) {e.printStackTrace();}
	    }
	 return amanha;
	 }
 
 public Date getOntem(Date hoje){
	 return getOntem(hoje,1);
 }

 public Date getOntem(Date hoje,int dias){
	    Date ontem = null;
	    if(hoje!=null){
	    	try {
	    		Calendar cal = Calendar.getInstance();
	    		cal.setTime(hoje);
	    		cal.add(Calendar.DAY_OF_MONTH, dias*(-1)); // subtrai dias a data
	    		ontem = cal.getTime(); 
	    	}catch (Exception e) {e.printStackTrace();}
	    }
	 return ontem;
	 }

 
 
 public String getFuturo(String dtini,int dias){
		String amanha=null;
		SimpleDateFormat sd = new SimpleDateFormat ("dd/MM/yyyy");
		try {
			Date hoje = format.parse(dtini);
			Calendar cal = Calendar.getInstance();
			cal.setTime(hoje);
			cal.add(Calendar.DAY_OF_MONTH, dias); // soma n dias a data
			amanha = sd.format(cal.getTime());
		} catch (Exception e) {e.printStackTrace();}	
	 return amanha;
	 }

 
  
 public String getProxMes(String dtini){
	String mes = this.getMes(dtini);
	int mes_ = Integer.parseInt(mes)+1;
	if(mes_>12)mes_=1;
	mes=String.valueOf(mes_);
	if(mes.length()<2)mes="0"+mes;
 return mes;
 }
 
 public String getDataMesAnterior(String dtini){
		String mes = this.getMes(dtini);
		String ano = this.getAno(dtini);
		int ano_ = Integer.parseInt(ano);
		int mes_ = Integer.parseInt(mes)-1;
		if(mes_==0){
			mes_=12;
			ano_-=1;
		}
		mes=String.valueOf(mes_);
		if(mes.length()<2)mes="0"+mes;
	 return "01/"+mes+"/"+ano;
	 }
 
 
 public String getDiaProxMes(String dtini){
	String mes = this.getMes(dtini);
	int ano = Integer.parseInt(this.getAno(dtini)); 
	int mes_ = Integer.parseInt(mes)+1;
	if(mes_>12){
		mes_=1;
		ano++;
	}
	mes=String.valueOf(mes_);
	
	if(mes.length()<2)mes="0"+mes;
	
 return this.getDiaMes(dtini)+"/"+mes+"/"+ano;
 }

 public String getDiaProxMes(){
	String mes = this.getMes();
	int ano = Integer.parseInt(this.getAno()); 
	int mes_ = Integer.parseInt(mes)+1;
	if(mes_>12){
		mes_=1;
		ano++;
	}
	mes=String.valueOf(mes_);
	if(mes.length()<2)mes="0"+mes;
	
 return this.getDiaMes()+"/"+mes+"/"+ano;
 }
 
 public Date getPrimeiroDiaMes(){
		String mes = this.getMes();
		int ano = Integer.parseInt(this.getAno()); 
		int mes_ = Integer.parseInt(mes);
		mes=String.valueOf(mes_);
		if(mes.length()<2)mes="0"+mes;
	 return montaDate("01"+"/"+mes+"/"+ano);
 }
 
 public long compararDatas(String data1,String data2){
 SimpleDateFormat df = new SimpleDateFormat ("dd/MM/yyyy");
 Date d1 = null; 
 Date d2 = null; 
 try { 
		 d1 = df.parse (data1);
		 d2 = df.parse (data2);
   } catch (java.text.ParseException evt ) {}

   long dt = (d2.getTime() - d1.getTime()) + 3600000;      
   long dias = (dt / 86400000L) + 1;

   return dias;
 }
 

 public long compararDatas(Date dataMaior,Date dataMenor){
	   long dt = 0l;
	   dt = (dataMaior.getTime() - dataMenor.getTime()) + 3600000;
	   if(converte2Banco(dataMaior).equals(converte2Banco(dataMenor)))
		   return 0l;
	   return (dt / 86400000L);
	 }
 
 
 public Date getDataIni(String mes, String ano){
	return Convertlocale("01/"+mes+"/"+ano);
 }
 public Date getDataFim(String mes, String ano){
		return getOntem(Convertlocale(getDiaProxMes("01/"+mes+"/"+ano)));
	 }

/*
 	Calendar c = new GregorianCalendar(2004, 9, 2); 
    
		System.out.println("Data: " + sd.format(c.getTime())); 
    
		c.add(Calendar.DAY_OF_MONTH, 60);
		System.out.println("Sessenta dias depois: " + sd.format(c.getTime()));
    
		c.add(Calendar.DAY_OF_MONTH, -1); 
		System.out.println("Um dia antes: " + sd.format(c.getTime())); 
    
		c.add(Calendar.MONTH, -1); 
		System.out.println("Um m�s antes: " + sd.format(c.getTime())); 
    
		c = new GregorianCalendar(2003, Calendar.MARCH, 3); 
		System.out.println("Data: " + sd.format(c.getTime())); 
    
		c.add(Calendar.DAY_OF_MONTH, -1); 
		System.out.println("Um dia antes: " + sd.format(c.getTime())); 
    
		c.add(Calendar.MONTH, -1); 
		System.out.println("Um m�s antes: " + sd.format(c.getTime()));
*/
 public Date montaDate(String dtini){
		SimpleDateFormat sd = new SimpleDateFormat ("dd/MM/yyyy");
		Date hoje=null;
		try {
			hoje = format.parse(dtini);
		} catch (Exception e) {e.printStackTrace();}	
	 return hoje;
	 }
 
  public Date montaDateBanco(String dtini){
		SimpleDateFormat sd = new SimpleDateFormat ("yyyy-MM-dd");
		Date dataBanco=null;
		try {
			dataBanco = sd.parse(dtini);
		} catch (Exception e) {e.printStackTrace();}	
	 return dataBanco;
	 }

}
