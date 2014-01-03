package com.qr.enums;

import com.qr.dinamic.ArCondicionado;

@SuppressWarnings("rawtypes")
public enum EnumTypeJson {
	
	DEFAULT(null),
	AR_CONDICIONADO(ArCondicionado.class);
	
	private Class clazz;
	
	private EnumTypeJson( Class clazz){
		this.clazz = clazz;
	}
	
	public Class getClassJson() {
		return clazz;
	}

}
