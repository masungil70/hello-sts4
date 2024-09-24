package org.kosa.hello.mapper;


//XML의 namespace에 사용할 때는 해당 파일을 전체 클래스 경로  
//org.kosa.hello.mapper.TimeMapper2

public interface TimeMapper2 {
	//@Select("select now()") 
	String getTime();
}
