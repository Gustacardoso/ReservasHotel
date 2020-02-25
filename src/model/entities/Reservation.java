package model.entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import model.exceptions.DomainException;

public class Reservation {
       private Integer roomNumber;
       private Date checkIn;
       private Date checkOut;
       
      private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public Reservation(Integer roomNumber, Date checkIn, Date checkOut) {
		if(!checkOut.after(checkIn)) {
			/*verificar para o usuario  nao digitar a data de checkout 
			 com data anterior ao checkin 
			A data de check-out deve ser posterior à data de check-in*/
			  throw new DomainException("Check-out date must be after check-in date");
		}
		this.roomNumber = roomNumber;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
	}

	public Integer getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(Integer roomNumber) {
		this.roomNumber = roomNumber;
	}

	public Date getCheckIn() {
		return checkIn;
	}

	

	public Date getCheckOut() {
		return checkOut;
	}
	
	 public long duration() {
		 long diff = checkOut.getTime() - checkIn.getTime();
		 /*estams pegando os milessegundo para ver quantos dias a pessoa
		    ficou hospedada*/
		 return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
		 //convertando  a um inteiro que seria numero de dias 
		 
	 }
	 
	 public void updateDates(Date checkIn,Date chekOut) {
		   
		 Date now = new Date();/*pegado  a data atual
		 vamos verificar a data atual com as data do  check, que nao
		   pode ser data posterior a data atual*/
		 if (checkIn.before(now) || chekOut.before(now) ) {
			 throw new DomainException("Reservation dates for update must be future dates ");
		}
		 if (!chekOut.after(checkIn)) {
			 throw new DomainException("Check-out date must be after check-in date");
		}
		 this.checkIn = checkIn;
		 this.checkOut = chekOut;
		 
	 }

	@Override
	public String toString() {
		return "Room "
				+ roomNumber
				+ ", check-in: "
				+ sdf.format(checkOut)
				+ ", "
				+ duration()
				+ " nights";
	}
}
