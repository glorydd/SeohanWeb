package com.seohan.common.Domain;

/**
 * Created by vivie on 2017-07-17.
 */
public enum IsUse {
	Y,N ;
    
	public IsUse inverse() {
		return this == Y ? N : Y;
	}
}
