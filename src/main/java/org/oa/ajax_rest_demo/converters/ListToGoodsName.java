package org.oa.ajax_rest_demo.converters;

import java.util.List;

import org.apache.log4j.Logger;
import org.oa.ajax_rest_demo.model.Goods;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ListToGoodsName implements Converter<List<Goods>, String> {
	private static final Logger log = Logger.getLogger(ListToGoodsName.class);

	@Override
	public String convert(List<Goods> list) {
		if(list != null){
			if (list.iterator().hasNext()){
				Goods good = list.iterator().next();
				log.info ("Convert list to string: " + good);
				return good.getName();
			}
		}
		return null;
	}
}
