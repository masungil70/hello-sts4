
package org.kosa.hello.util;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

@Component
public class MapperUtil extends ModelMapper {
	
//	private ModelMapper mapper;
	
	MapperUtil() {
//		mapper = new ModelMapper();
		
		this.getConfiguration()
			.setFieldAccessLevel(AccessLevel.PRIVATE)
			.setFieldMatchingEnabled(true)
			.setMatchingStrategy(MatchingStrategies.STRICT);
	}

//	public <D> D map(Object source, Class<D> destinationType) {
//		return map(source, destinationType); 
//	}
	
}
