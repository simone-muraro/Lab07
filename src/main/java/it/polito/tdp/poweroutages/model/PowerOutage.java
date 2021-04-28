package it.polito.tdp.poweroutages.model;

import java.time.Duration;
import java.time.LocalDateTime;

public class PowerOutage {

	int idPowerOutage;
	int idNerc;
	int customerAffected;
	LocalDateTime dateEventBegan;
	LocalDateTime dateEventFinished;
	int oreIncendio;

	public PowerOutage(int idPowerOutage, int idNerc, int customerAffected, LocalDateTime dateEventBegan,
			LocalDateTime dateEventFinished) {
		super();
		this.idPowerOutage = idPowerOutage;
		this.idNerc = idNerc;
		this.customerAffected = customerAffected;
		this.dateEventBegan = dateEventBegan;
		this.dateEventFinished = dateEventFinished;
		this.oreIncendio=this.calcolaOre();
	}

	public int getIdPowerOutage() {
		return idPowerOutage;
	}

	public void setIdPowerOutage(int idPowerOutage) {
		this.idPowerOutage = idPowerOutage;
	}

	public int getIdNerc() {
		return idNerc;
	}

	public void setIdNerc(int idNerc) {
		this.idNerc = idNerc;
	}

	public int getCustomerAffected() {
		return customerAffected;
	}

	public void setCustomerAffected(int customerAffected) {
		this.customerAffected = customerAffected;
	}

	public LocalDateTime getDateEventBegan() {
		return dateEventBegan;
	}

	public void setDateEventBegan(LocalDateTime dateEventBegan) {
		this.dateEventBegan = dateEventBegan;
	}

	public LocalDateTime getDateEventFinished() {
		return dateEventFinished;
	}

	public void setDateEventFinished(LocalDateTime dateEventFinished) {
		this.dateEventFinished = dateEventFinished;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idPowerOutage;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PowerOutage other = (PowerOutage) obj;
		if (idPowerOutage != other.idPowerOutage)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PowerOutage [idPowerOutage=" + idPowerOutage + ", idNerc=" + idNerc + ", customerAffected="
				+ customerAffected + ", dateEventBegan=" + dateEventBegan + ", dateEventFinished=" + dateEventFinished+"oreIncendio"+oreIncendio
				+ "]";
	}
	
	private int calcolaOre() {
		float f= Duration.between(dateEventBegan ,dateEventFinished).toHours();
		return (int)f;
	}
}
