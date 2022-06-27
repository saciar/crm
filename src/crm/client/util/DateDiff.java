package crm.client.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/** 
 * This class determines the difference between 2 dates not including the dates themselves, i.e. inclusive 
 * passed as java.utilCalendar.
 * Owner : Niraj Agarwal
 */
public class DateDiff  
{
    // The year difference between passed dates
    private int yearDiff = 0;
    // The month difference between passed dates, excluding years
    private int monthDiff = 0;
    // The day difference between passed dates, excluding years and months
    private int dayDiff = 0;
    // Total day difference between passed dates, including years and months 
    private int dayOnly = 0;

    private Calendar startDate = null;
    private Calendar endDate = null;

    private static final long DAY = 86400000;

    public DateDiff(Calendar pStartDate, Calendar pEndDate)  {
        startDate = Calendar.getInstance();
        endDate = Calendar.getInstance();
        
        startDate.clear();
        endDate.clear();

        startDate.set(pStartDate.get(Calendar.YEAR), pStartDate.get(Calendar.MONTH), pStartDate.get(Calendar.DATE));        
        endDate.set(pEndDate.get(Calendar.YEAR), pEndDate.get(Calendar.MONTH), pEndDate.get(Calendar.DATE));        
    }

    public void calculateDifference()
    {
        if( startDate == null || endDate == null || startDate.after(endDate) )
            return;

        dayOnly = (int) ((endDate.getTimeInMillis() - startDate.getTimeInMillis()) / DAY);

        yearDiff = endDate.get(Calendar.YEAR) - startDate.get(Calendar.YEAR);
            
        boolean bYearAdjusted = false;
        startDate.add(Calendar.YEAR, yearDiff);
        if( startDate.after(endDate) )
        {
            bYearAdjusted = true;
            startDate.add(Calendar.YEAR, -1 );
            yearDiff--;
        }

        monthDiff = endDate.get(Calendar.MONTH) - startDate.get(Calendar.MONTH);
        if( bYearAdjusted && monthDiff <= 0 )
            monthDiff = 11 + monthDiff;

        startDate.add(Calendar.MONTH, monthDiff);
        if( startDate.after(endDate) )
        {
            startDate.add(Calendar.MONTH, -1 );
            monthDiff--;
        }
            
        dayDiff = endDate.get(Calendar.DAY_OF_YEAR) - startDate.get(Calendar.DAY_OF_YEAR);
        if( dayDiff < 0 )
            dayDiff = 365 + dayDiff;

        startDate.add(Calendar.DAY_OF_YEAR, dayDiff);
    }

    public int getYear()
    {
        return yearDiff;
    }

    public int getMonth()
    {
        return monthDiff;
    }

    public int getDay()
    {
        return dayDiff;
    }

    public int getDayOnly()
    {
        return dayOnly;
    }
    
    public static DateDiff calcularFechas(String d1, String d2){
		
        
        SimpleDateFormat xlsDateFormater = new SimpleDateFormat("yyyy-MM-dd");
        Date dateDesde = null;
        Date dateHasta = null;
        try{
        	dateDesde = xlsDateFormater.parse(d1);
        	dateHasta =xlsDateFormater.parse(d2);
        }
        catch (Exception e){
        	e.printStackTrace();
        }
        
        if (dateDesde == null || dateHasta == null)
        	return null;
        
        Calendar sDate = Calendar.getInstance();
        Calendar eDate = Calendar.getInstance();
        
        sDate.setTime(dateDesde);
        eDate.setTime(dateHasta);
        
        DateDiff dateDiff = new DateDiff(sDate, eDate);
        dateDiff.calculateDifference();
        
        return dateDiff;
	}
    
    public static long calcularHoras(String d1, String d2){
		
        
        SimpleDateFormat xlsDateFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dateDesde = null;
        Date dateHasta = null;
        try{
        	dateDesde = xlsDateFormater.parse(d1);
        	dateHasta =xlsDateFormater.parse(d2);
        }
        catch (Exception e){
        	e.printStackTrace();
        }
        
        if (dateDesde == null || dateHasta == null)
        	return 0;
        
        Calendar sDate = Calendar.getInstance();
        Calendar eDate = Calendar.getInstance();
        
        sDate.setTime(dateDesde);
        eDate.setTime(dateHasta);
        
        long diferenciaHoras=0; 
		diferenciaHoras=(eDate.get(Calendar.HOUR_OF_DAY)-sDate.get(Calendar.HOUR_OF_DAY)); 
		return diferenciaHoras; 
	}
    
	public static long calcularHorasTotales(String d1, String d2){
			
	        
	        SimpleDateFormat xlsDateFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        Date dateDesde = null;
	        Date dateHasta = null;
	        try{
	        	dateDesde = xlsDateFormater.parse(d1);
	        	dateHasta =xlsDateFormater.parse(d2);
	        }
	        catch (Exception e){
	        	e.printStackTrace();
	        }
	        
	        if (dateDesde == null || dateHasta == null)
	        	return 0;
	        
	        Calendar sDate = Calendar.getInstance();
	        Calendar eDate = Calendar.getInstance();
	        
	        sDate.setTime(dateDesde);
	        eDate.setTime(dateHasta);
	        
	        long diferenciaHoras=0; 
			diferenciaHoras=(eDate.getTimeInMillis()-sDate.getTimeInMillis())/1000/60/60; 
			return diferenciaHoras;
	}
    
    public static DateDiff calcularFechas(Date dateDesde, Date dateHasta){
        
        Calendar sDate = Calendar.getInstance();
        Calendar eDate = Calendar.getInstance();
        
        sDate.setTime(dateDesde);
        eDate.setTime(dateHasta);
        
        DateDiff dateDiff = new DateDiff(sDate, eDate);
        dateDiff.calculateDifference();
        
        return dateDiff;
	}
    
    public static int calcularFechaMayor(String d1, String d2){
		
        
        SimpleDateFormat xlsDateFormater = new SimpleDateFormat("dd-MM-yyyy");
        Date date1 = null;
        Date date2 = null;
        try{
        	if (d1 != null && d2 != null){
        		date1 = xlsDateFormater.parse(d1);
        		date2 =xlsDateFormater.parse(d2);
        	}
        	else {
        		date1 = xlsDateFormater.parse("01");
        		date2 =xlsDateFormater.parse("01");
        	}
        }
        catch (Exception e){
        	e.printStackTrace();
        }
        
        Calendar sDate = Calendar.getInstance();
        Calendar eDate = Calendar.getInstance();
        
        sDate.setTime(date1);
        eDate.setTime(date2);
        
        return sDate.compareTo(eDate);
	}
    
    public static int getWeek(String d1){
		
        
        SimpleDateFormat xlsDateFormater = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try{
        		date = xlsDateFormater.parse(d1);
        }
        catch (Exception e){
        	e.printStackTrace();
        }
        
        if (date == null)
        	return 0;
        
        Calendar cal = Calendar.getInstance();
        
        cal.setTime(date);
        cal.setFirstDayOfWeek(Calendar.SUNDAY);
        cal.setMinimalDaysInFirstWeek(1);
        return cal.get(Calendar.WEEK_OF_YEAR);
	}
    
    public static ArrayList<Date> getdiasEntreFechas(Date dateDesde, Date dateHasta){
    	ArrayList<Date> dates = new ArrayList<Date>();
    	
    	Calendar sDate = Calendar.getInstance();
        Calendar eDate = Calendar.getInstance();
        sDate.setTime(dateDesde);
        eDate.setTime(dateHasta);
        
        dates.add(dateDesde);
        while(sDate.compareTo(eDate)<0){
        	sDate.add(Calendar.DATE, 1);
        	dates.add(sDate.getTime());
        }
    	return dates;
    }
    
    public static int getWeek(Date date){
        
    	if (date == null)
        	return 0;
    	
        Calendar cal = Calendar.getInstance();
        
        cal.setTime(date);
        cal.setFirstDayOfWeek(Calendar.SUNDAY);
        cal.setMinimalDaysInFirstWeek(1);
        return cal.get(Calendar.WEEK_OF_YEAR);
	}
    
    public static void main(String args[]){
/*        if( args.length != 2 ){
            System.out.println("\nInvalid usage : ");
            System.out.println(" java DateDiff [startDate - dd/mm/yyyy] [endDate - dd/mm/yyyy]\n");
            return;
        }

        Calendar sDate = Calendar.getInstance();
        Calendar eDate = Calendar.getInstance();

        sDate.set(Integer.parseInt(args[0].substring(6)), Integer.parseInt(args[0].substring(3,5))-1, Integer.parseInt(args[0].substring(0,2)));
        eDate.set(Integer.parseInt(args[1].substring(6)), Integer.parseInt(args[1].substring(3,5))-1, Integer.parseInt(args[1].substring(0,2)));

        DateDiff dateDiff = new DateDiff(sDate, eDate);
        dateDiff.calculateDifference();
        
        SimpleDateFormat xlsDateFormater = new SimpleDateFormat("dd-MMM-yyyy");
        
        System.out.println("\nStart Date : "+xlsDateFormater.format(sDate.getTime()));
        System.out.println("End Date   : "+xlsDateFormater.format(eDate.getTime()));

        System.out.println("\nDateDiff : "+dateDiff.getYear()+" Year(s), "+dateDiff.getMonth()+" Month(s), "+dateDiff.getDay()+" Day(s)    ["+dateDiff.getDayOnly()+" days(s)]\n");*/
 
    	
    	
    	
    }
}