package com.ip.Library_management_system.responsehandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseStructure<T> {
	private int statuscode;
	private String msg;
	private T data;
}
