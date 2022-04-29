package kr.co.songjava.framework.util;

import org.apache.commons.lang3.ObjectUtils;

import kr.co.songjava.mvc.domain.BaseCodeLabelEnum;


/*
 * 
 * @param  values 파라메터로 넘어온 선택된 값들
 * @param codeEnum 현재 출력하고 있는 code
 * @return
 */

public class EnumUtils {
	
	public static boolean isSelected(BaseCodeLabelEnum[] values, BaseCodeLabelEnum codeEnum) {
		if(ObjectUtils.isEmpty(values)) {
			return false;
		}
		for (BaseCodeLabelEnum value : values) {
			// 동일하면
			if (value.code().equals(codeEnum.code())) {
				return true;
			}
		}
		return false;
	}

}
